package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Comment;
import com.itgate.tunijobs.models.User;
import com.itgate.tunijobs.repository.CommentRepo;
import com.itgate.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepository userRepo;

    public List<Comment> getall(){
        return commentRepo.findAll() ;    }

    public Comment creatCommentService ( Comment c ,  Long id_user){
        User u = userRepo.findById(id_user).orElse(null);
        c.setUser(u);
        return commentRepo.save(c);
    }
    public ResponseEntity deleteCommentService (Long id) {
        commentRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public Comment updateCommentService ( Comment c,  Long id){
        Comment c1 = commentRepo.findById(id).orElse(null);
        if (c1 !=null){
            c.setId(id);
            return commentRepo.saveAndFlush(c);
        }
        else{
            throw new RuntimeException("fail");
        }

    }





}
