package com.example.SecurityApp.SecurityApplication.Controllers;

import com.example.SecurityApp.SecurityApplication.DTO.PostDTO;
import com.example.SecurityApp.SecurityApplication.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/posts")
public class PostController {
    private  final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService .getAllPost();

    }
    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }
    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputDTO){
        return postService.createNewPost(inputDTO);

    }

    @PutMapping("/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost, @PathVariable Long postId){
        return postService.updatePost(inputPost, postId);

    }
}
