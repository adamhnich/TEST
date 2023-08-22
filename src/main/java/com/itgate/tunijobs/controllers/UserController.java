package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.UserService;
import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.payload.request.SignupRequest;
import com.itgate.tunijobs.payload.response.MessageResponse;
import com.itgate.tunijobs.repository.RoleRepository;
import com.itgate.tunijobs.repository.UserRepository;
import com.itgate.tunijobs.security.jwt.JwtUtils;
import com.itgate.tunijobs.security.services.RefreshTokenService;
import com.itgate.tunijobs.utils.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/User")
@CrossOrigin("*")
public class UserController {

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
    UserService userService;


    @GetMapping
    public List<User> getall(){
        return userService.getall() ;
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Long id){
        return userService.getOneUserService(id);
    }

    @PostMapping
    public User createUser (@RequestBody User u){
        return userService.createUserService(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id) {
        userService.deleteUserService(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public User updateUser (@RequestBody User u, @PathVariable Long id){
        return userService.updateUserService(u,id);
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfiles(@RequestParam("files") MultipartFile[] files, User u) {
        return userService.uploadfilesService(files,u);
    }


    /*@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {

                    case "user":
                        Role provRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(provRole);

                        break;


                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }*/



}
