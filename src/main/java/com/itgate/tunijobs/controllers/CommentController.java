package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Comment;
import com.itgate.tunijobs.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    CommentService commentService;
    @GetMapping
    public List<Comment> getall(){
        return commentService.getall() ;    }


    @PostMapping("/{id_user}")
    public Comment creatRComment (@RequestBody Comment c , @PathVariable Long id_user){
        return commentService.creatCommentService(c,id_user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment (@PathVariable Long id) {
        return commentService.deleteCommentService(id);


    }
    @PutMapping("/{id}")
    public Comment updateComment (@RequestBody Comment c, @PathVariable Long id){
        return commentService.updateCommentService(c,id);
    }
}
