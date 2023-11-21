package com.example.postservice.persistence.posts;

import com.example.postservice.model.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPostRepo extends JpaRepository<Post, UUID> {
     List<Post> getAllByAuthorID(UUID authorID);
     void deleteAllByAuthorID(UUID authorID);

     List<Post> findAllByAuthorIDAndIsInactiveFalse(UUID authorID);

     //Set post inactive by authorID

//     void findByPostIDAndIsInactive();


     void deleteByPostID(UUID authorID);
     Post findPostByPostID(UUID postId);
}
