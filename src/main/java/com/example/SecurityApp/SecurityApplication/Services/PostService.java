package com.example.SecurityApp.SecurityApplication.Services;


import com.example.SecurityApp.SecurityApplication.DTO.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPost();



    PostDTO createNewPost(PostDTO inputDTO);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost,Long postId);
}
