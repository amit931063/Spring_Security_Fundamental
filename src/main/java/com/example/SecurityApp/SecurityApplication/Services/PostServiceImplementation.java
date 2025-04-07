package com.example.SecurityApp.SecurityApplication.Services;

import com.example.SecurityApp.SecurityApplication.DTO.PostDTO;
import com.example.SecurityApp.SecurityApplication.Entities.PostEntity;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Exceptions.ResourceNotFoundException;
import com.example.SecurityApp.SecurityApplication.Repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class PostServiceImplementation  implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImplementation(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public PostDTO createNewPost(PostDTO inputDTO) {

        User user= (User) SecurityContextHolder.getContext().getAuthentication();
        PostEntity postEntity=modelMapper.map(inputDTO,PostEntity.class );
        postEntity.setAuthor(user);
        return  modelMapper.map(postRepository.save(postEntity),PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long   postId) {

//        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("user {}",user);
        PostEntity postEntity= postRepository
                .findById(postId).orElseThrow(()->new ResourceNotFoundException("post was not found with id :"+postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost =postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post is not found with this : "+postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost,olderPost);
        PostEntity savedPost=postRepository.save(olderPost);
        return modelMapper.map(savedPost,PostDTO.class);

    }
}
