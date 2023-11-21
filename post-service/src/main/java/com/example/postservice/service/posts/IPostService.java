package com.example.postservice.service.posts;

import com.example.postservice.controller.posts.requests.CreatePostRequest;
import com.example.postservice.model.posts.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPostService {

    List<Post> getAllPosts();

    Post savePost(CreatePostRequest request);

    Optional<Post> findPostInformation(String username);
    void deletePost(UUID postID);
}
