package com.example.SecurityApp.SecurityApplication.Repositories;

import com.example.SecurityApp.SecurityApplication.Entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface PostRepository  extends JpaRepository<PostEntity , Long> {
}
