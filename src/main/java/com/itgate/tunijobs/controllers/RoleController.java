package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.RoleService;
import com.itgate.tunijobs.models.Employe;
import com.itgate.tunijobs.models.Produit;
import com.itgate.tunijobs.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getall(){
        return roleService.getall() ;
    }

    @GetMapping("/{id}")
    public Role getOneRole(@PathVariable Long id){
        return roleService.getOneRole(id);
    }

    @PostMapping
    public Role createRole (@RequestBody Role r){
        return roleService.createRole(r);
    }


    @PutMapping("/{id}")
    public Role updateRole (@RequestBody Role r, @PathVariable Long id) {
        return roleService.updateRole(r,id);
    }

}
