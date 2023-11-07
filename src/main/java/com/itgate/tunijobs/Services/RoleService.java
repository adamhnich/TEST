package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Profil;
import com.itgate.tunijobs.models.Role;
import com.itgate.tunijobs.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getall(){
        return roleRepository.findAll() ;
    }

    public Role getOneRole( Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public ResponseEntity deleteRole (Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Role updateRole (Role r, Long id){
        Role r1 = roleRepository.findById(id).orElse(null);
        if (r1 !=null){
            r.setId(id);
            return roleRepository.saveAndFlush(r);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public Role createRole(Role r) {
        return roleRepository.save(r);
    }
}
