package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.ClientService;
import com.itgate.tunijobs.Services.CommandeService;
import com.itgate.tunijobs.Services.LivraisonService;
import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.payload.request.SignupRequest;
import com.itgate.tunijobs.payload.response.MessageResponse;
import com.itgate.tunijobs.repository.ClientRepo;
import com.itgate.tunijobs.repository.RoleRepository;
import com.itgate.tunijobs.repository.UserRepository;
import com.itgate.tunijobs.security.jwt.JwtUtils;
import com.itgate.tunijobs.security.services.RefreshTokenService;
import com.itgate.tunijobs.utils.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Client")
@CrossOrigin("*")
public class ClientControllerr {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    ClientService clientService;

    @Autowired
    CommandeService commandeService;


    @PostMapping("/save")
    public Client saveclient(@RequestBody Client c) {
        return clientRepo.save(c);
    }

    @GetMapping("/getall")
    public List<Client> getall(){
        return clientService.getall() ;    }

    @GetMapping ("/getone/{id}")
    public Client getOneclient(@PathVariable Long id){
        return clientRepo.findById(id).orElse(null);
    }

    @GetMapping("/{clientId}/Commande")
    public List<Commande> getCommandesByIdClient(@PathVariable Long clientId) {
        return commandeService.getCommandesByIdClient(clientId);
    }



    @PutMapping("/update/{Id}")
    public Client update(@RequestBody Client c, @PathVariable Long Id) {
        Client c1 = clientRepo.findById(Id).orElse(null);
        if (c1 != null) {
            c.setId(Id);
            return clientRepo.saveAndFlush(c);
       }
        else{
            throw new RuntimeException("FAIL!");
        }

    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteClient(@PathVariable Long Id) {
        HashMap message= new HashMap();
        try{
            clientRepo.deleteById(Id);
            message.put("etat","client deleted");
           return message;
       }
        catch (Exception e) {
            message.put("etat","client not deleted");
            return message;
       }
   }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Client client = new Client(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getAdress(),signUpRequest.getCity(),signUpRequest.getImage());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


                        Role provRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(provRole);

        client.setRoles(roles);
        clientService.createClientService(client);

        String from ="admin@gmail.com" ;
        String to = signUpRequest.getEmail();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Complete Registration!");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText("<HTML><body>" +
                " <a href=\"http://localhost:8086/api/auth/confirrm?email="
                +signUpRequest.getEmail()+"\">VERIFY</a></body></HTML>",true);
        mailSender.send(message);

        return ResponseEntity.ok(new MessageResponse("Client registered successfully!, Cheak your email for COMFIRMATION"));
    }


    @GetMapping("/count")
    public Long countClientProfiles() {
        return clientService.countClientProfiles();
    }


    @PostMapping("/{id}/bloquer")
    public Client bloquerClient(@PathVariable Long id) {
        return clientService.bloquerClient(id);
    }

    @PostMapping("/{id}/debloquer")
    public Client debloquerClient(@PathVariable Long id) {
        return clientService.debloquerClient(id);
    }

}
