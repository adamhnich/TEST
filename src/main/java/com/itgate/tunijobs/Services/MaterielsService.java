package com.itgate.tunijobs.Services;


import com.itgate.tunijobs.models.Locataire;
import com.itgate.tunijobs.models.Materiels;
import com.itgate.tunijobs.models.ResponseMessage;
import com.itgate.tunijobs.models.Souscategorie;
import com.itgate.tunijobs.repository.LocataireRepo;
import com.itgate.tunijobs.repository.MaterielsRepo;
import com.itgate.tunijobs.repository.SouscategorieRepo;
import com.itgate.tunijobs.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
public class MaterielsService {

    @Autowired
    MaterielsRepo materielsRepo;
    @Autowired
    LocataireRepo locataireRepo;
    @Autowired
    SouscategorieRepo souscategorieRepo;

    private StorageService storage;
    private final Path rootLocation = Paths.get("upload");

    public List<Materiels> getall(){
        return materielsRepo.findAll() ;
    }

    public Materiels getOneMaterielsService( Long id){
        return materielsRepo.findById(id).orElse(null);
    }

    public Materiels createMaterielsService (Materiels m){
        return materielsRepo.save(m);
    }

    public ResponseEntity deleteMaterielsService (Long id) {
        materielsRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Materiels updateMaterielsService ( Materiels m, Long id){
        Materiels m1 = materielsRepo.findById(id).orElse(null);
        if (m1 !=null){
            m.setId(id);
            return materielsRepo.saveAndFlush(m);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public ResponseEntity<ResponseMessage> uploadfilesService(MultipartFile[] files, Materiels m, Long id_souscategorie, Long id_locataire) {
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
                    m.setImage(fileNames);
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });

            Locataire lt = locataireRepo.findById(id_locataire).orElse(null);
            m.setLocataire(lt);
            Souscategorie sub = souscategorieRepo.findById(id_souscategorie).orElse(null);
            m.setSouscategorie(sub);

            materielsRepo.save(m);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    public Materiels savematerielsService(MultipartFile file,Materiels materiels, Long id_souscategorie, Long id_locataire) {
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                    file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = name + fileName + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
            Locataire lt = locataireRepo.findById(id_locataire).orElse(null);
            materiels.setLocataire(lt);
            Souscategorie sub = souscategorieRepo.findById(id_souscategorie).orElse(null);
            materiels.setSouscategorie(sub);
            materiels.setImage(original);
            return materielsRepo.save(materiels);

        } catch (Exception e) {
            throw new RuntimeException("fail file problem backend");
        }
    }

    public ResponseEntity<Resource>getFileService(String filename){
        Resource file=storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=\""+file.getFilename()+"\"")
                .body(file);
    }



}
