package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.ProduitService;
import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.Services.VendeurService;
import com.itgate.tunijobs.payload.request.LoginRequest;
import com.itgate.tunijobs.payload.request.SignupRequest;
import com.itgate.tunijobs.payload.response.JwtResponse;
import com.itgate.tunijobs.payload.response.MessageResponse;
import com.itgate.tunijobs.repository.RoleRepository;
import com.itgate.tunijobs.repository.UserRepository;
import com.itgate.tunijobs.repository.VendeurRepo;
import com.itgate.tunijobs.security.jwt.JwtUtils;
import com.itgate.tunijobs.security.services.RefreshTokenService;
import com.itgate.tunijobs.security.services.UserDetailsImpl;
import com.itgate.tunijobs.utils.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Vendeur")
@CrossOrigin("*")
public class VendeurController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    VendeurRepo vendeurRepo;
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
    VendeurService vendeurService;
    @Autowired
    ProduitService produitService;

    @GetMapping
    public List<Vendeur> getall(){
        return vendeurService.getall() ;    }

    @GetMapping("/{id}")
    public Vendeur getOneVendeur(@PathVariable Long id){
        return vendeurService.getOneVendeurService(id);
    }

    @PostMapping
    public Vendeur createVendeur (@RequestBody Vendeur v){
        return vendeurService.createVendeurService(v);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVendeur (@PathVariable Long id) {
        return vendeurService.deleteVendeurService(id);

    }
    @PutMapping("/{id}")
    public Vendeur updateVendeur (@RequestBody Vendeur v, @PathVariable Long id){

        return vendeurService.updateVendeurService(v,id);

    }


    @GetMapping("/{vendeurId}/Produits")
    public List<Produit> getProduitsByIdVendeur(@PathVariable Long vendeurId) {
        return produitService.getProduitsByIdVendeur(vendeurId);
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
        Vendeur vendeur = new Vendeur(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getSoldeVendeur(),signUpRequest.getNomboutique(),signUpRequest.getMatriculefiscale());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


                        Role provRole = roleRepository.findByName(ERole.ROLE_VENDEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(provRole);


        vendeur.setRoles(roles);
        vendeurService.createVendeurService(vendeur);

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

        return ResponseEntity.ok(new MessageResponse("Vendeur registered successfully!, Cheak your email for COMFIRMATION"));
    }


}
