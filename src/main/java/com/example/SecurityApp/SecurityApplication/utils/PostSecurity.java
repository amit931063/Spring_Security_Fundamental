package com.example.SecurityApp.SecurityApplication.utils;

import com.example.SecurityApp.SecurityApplication.DTO.PostDTO;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {
    private final PostService postService;

     public boolean isOwnerOfPost(Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return  postDTO.getAuthor().getId().equals(user.getId());
    }
}



