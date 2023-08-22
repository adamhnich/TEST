//package com.itgate.springjwt.controllers;
//
//
//import com.itgate.springjwt.models.Client;
//import com.itgate.springjwt.models.ERole;
//import com.itgate.springjwt.models.Role;
//import com.itgate.springjwt.payload.request.SignupRequest;
//import com.itgate.springjwt.payload.response.MessageResponse;
//import com.itgate.springjwt.repository.ClientRepository;
//import com.itgate.springjwt.repository.RoleRepository;
//import com.itgate.springjwt.repository.UserRepository;
//import com.itgate.springjwt.utils.EmailService;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.*;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/client")
//public class ClientController {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    private EmailService emailService;
//    private final Path rootLocation = Paths.get("upload-dir");
//
//    @Autowired
//    private JavaMailSender mailSender;
//    @GetMapping("/all")
//    public List<Client> getAllclient() {
//        return clientRepository.findAll();
//    }
//
//    @PostMapping("/save")
//    public Client saveclient(@RequestBody Client c) {
//        return clientRepository.save(c);
//    }
//
//
//    @GetMapping ("/getone/{id}")
//    public Client getOneclient(@PathVariable Long id){
//        return clientRepository.findById(id).orElse(null);
//    }
//
//
//    @PutMapping("/update/{Id}")
//    public Client update(@RequestBody Client c, @PathVariable Long Id) {
//        Client c1 = clientRepository.findById(Id).orElse(null);
//        if (c1 != null) {
//            c.setId(Id);
//            return clientRepository.saveAndFlush(c);
//        }
//        else{
//            throw new RuntimeException("FAIL!");
//        }
//
//    }
//
//    @DeleteMapping("/delete/{Id}")
//    public HashMap<String,String> deleteClient(@PathVariable Long Id) {
//        HashMap message= new HashMap();
//        try{
//            clientRepository.deleteById(Id);
//            message.put("etat","client deleted");
//            return message;
//        }
//        catch (Exception e) {
//            message.put("etat","client not deleted");
//            return message;
//        }
//    }
//
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerClient(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        Client client = new Client(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()),signUpRequest.getAdress(),signUpRequest.getCity());
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//
//            Role custRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(custRole);
//
//        }
//        client.setRoles(roles);
//
//
//
//
//        client.setConfirm(false);
//        String from ="admin@gmail.com" ;
//        String to = signUpRequest.getEmail();
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setSubject("Complete Registration!");
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setText("<HTML><body> <a href=\"http://localhost:8083/client/updateconfirm?email="+signUpRequest.getEmail()+"\">VERIFY</a></body></HTML>",true);
//        mailSender.send(message);
//
//
//        userRepository.save(client);
//
//        return ResponseEntity.ok(new MessageResponse("Client registered successfully and mail confirmation is sent!"));
//    }
//
//
//
//    @GetMapping("/updateconfirm")
//    public String updateconfirm(String email) {
//
//      Client c1=clientRepository.findByEmail(email);
//        if (c1 != null) {
//          c1.setConfirm(true);
//         clientRepository.saveAndFlush(c1);
//         return "congratulations email confirm welcome to my app";
//        }
//        else{
//            throw new RuntimeException("FAIL!");
//        }
//
//
//    }
//
//
//
//
//    @PostMapping("/signupwithimage")
//    public ResponseEntity<?> registerClientwithimage(@Valid  SignupRequest signUpRequest,@RequestParam("file") MultipartFile file) throws MessagingException {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        Client client = new Client(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()),signUpRequest.getAdress(),signUpRequest.getCity());
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//
//            Role custRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(custRole);
//
//        }
//        //image
//        try {
//            String fileName = Integer.toString(new Random().nextInt(1000000000));
//            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
//            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
//            String original = name + fileName + ext;
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
//            client.setImage(original);
//        } catch (Exception e) {
//            throw new RuntimeException("FAIL File Prolem BackEnd !");
//        }
//
//
//        //mail confirmation
//        client.setConfirm(false);
//        String from ="admin@gmail.com" ;
//        String to = signUpRequest.getEmail();
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setSubject("Complete Registration!");
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setText("<HTML><body> <a href=\"http://localhost:8083/client/updateconfirm?email="+signUpRequest.getEmail()+"\">VERIFY</a></body></HTML>",true);
//        mailSender.send(message);
//
//
//
//        client.setRoles(roles);
//        userRepository.save(client);
//
//        return ResponseEntity.ok(new MessageResponse("Client registered successfully and mail confirmation is send !"));
//    }
//
//
//
//
//
//}
