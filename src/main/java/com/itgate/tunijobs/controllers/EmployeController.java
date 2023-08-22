package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.ProfilService;
import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.Services.EmployeService;
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
@RequestMapping("/Employe")
@CrossOrigin("*")
public class EmployeController {

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
    EmployeService employeService;

    @Autowired
    ProfilService profilService;


    @GetMapping("/getallemploye")
    public List<Employe> getall(){
        return employeService.getall() ;    }

    @GetMapping("/getoneemploye/{id}")
    public Employe getOneEmploye(@PathVariable Long id){
        return employeService.getOneEmployeService(id);
    }

    @GetMapping("/{employeId}/Profil")
    public Profil getProfilByIdemploye(@PathVariable Long employeId) {
        return profilService.getProfilByIdemploye(employeId);
    }


    @PostMapping
    public Employe createEmploye (@RequestBody Employe e){
        return employeService.createEmployeService(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmploye (@PathVariable Long id) {
        return employeService.deleteEmployeService(id);

    }
    @PutMapping("/{id}")
    public Employe updateEmploye (@RequestBody Employe e, @PathVariable Long id) {
        return employeService.updateEmployeService(e,id);
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
        Employe employe = new Employe(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getNom(),signUpRequest.getTelephone(),signUpRequest.getAdress());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


                        Role provRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(provRole);


        employe.setRoles(roles);
        employeService.createEmployeService(employe);

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

        return ResponseEntity.ok(new MessageResponse("Employe registered successfully!, Cheak your email for COMFIRMATION"));
    }
}
