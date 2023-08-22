package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.ResponseMessage;
import com.itgate.tunijobs.models.User;
import com.itgate.tunijobs.repository.ReclamationRepo;
import com.itgate.tunijobs.repository.UserRepository;
import com.itgate.tunijobs.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    ReclamationRepo reclamationRepo;
    @Autowired
    private StorageService storage;
    private final Path rootLocation = Paths.get("upload");

    public List<User> getall() {
        return userRepo.findAll();
    }

    public User getOneUserService(Long id) {
        return userRepo.findById(id).orElse(null);
    }


    public User createUserService(User u) {
        return userRepo.save(u);
    }

    public ResponseEntity deleteUserService(Long id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public User updateUserService(User u, Long id) {
        User u1 = userRepo.findById(id).orElse(null);
        if (u1 != null) {
            u.setId(id);
            return userRepo.saveAndFlush(u);
        } else {
            throw new RuntimeException("fail");
        }
    }

    public ResponseEntity<ResponseMessage> uploadfilesService(MultipartFile[] files, User u) {
        String message = "";
        try {
            ArrayList<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    String fileName = Integer.toString(new Random().nextInt(1000000));
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                            file.getOriginalFilename().length());
                    String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                    String original = name + fileName + ext;
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
                    fileNames.add(original);
//                    u.setImage(fileNames);
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });

            userRepo.save(u);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


}
