package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.Offre_emploiService;
import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.Services.RecruteurService;
import com.itgate.tunijobs.payload.request.SignupRequest;
import com.itgate.tunijobs.payload.response.MessageResponse;
import com.itgate.tunijobs.repository.RoleRepository;
import com.itgate.tunijobs.repository.UserRepository;
import com.itgate.tunijobs.security.jwt.JwtUtils;
import com.itgate.tunijobs.security.services.RefreshTokenService;
import com.itgate.tunijobs.utils.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Recruteur")
@CrossOrigin("*")
public class RecruteurController {

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
    RecruteurService recruteurService;

    @Autowired
    Offre_emploiService offre_emploiService;

    @GetMapping
    public List<Recruteur> getall(){
        return recruteurService.getall() ;    }

    @GetMapping("/{id}")
    public Recruteur getOneRecruteur(@PathVariable Long id){
        return recruteurService.getOneRecruteurService(id);
    }

    @GetMapping("/{recruteurId}/Offre_emploi")
    public List<Offre_emploi> getOffre_emploiByIdRecruteur(@PathVariable Long recruteurId) {
        return offre_emploiService.getOffre_emploiByIdRecruteur(recruteurId);
    }


    @PostMapping
    public Recruteur createRecruteur (@RequestBody Recruteur r){
        return recruteurService.createRecruteurService(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecruteur (@PathVariable Long id) {
        return recruteurService.deleteRecruteurService(id);

    }
    @PutMapping("/{id}")
    public Recruteur updateRecruteur (@RequestBody Recruteur r, @PathVariable Long id) {
        return recruteurService.updateRecruteurService(r,id);
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
        Recruteur recruteur = new Recruteur(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getTelephonerec(),signUpRequest.getAdresse(),signUpRequest.getProjet());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


                        Role provRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(provRole);

        recruteur.setRoles(roles);
        recruteurService.createRecruteurService(recruteur);

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

        return ResponseEntity.ok(new MessageResponse("Recruteur registered successfully!, Cheak your email for COMFIRMATION"));
    }

}
