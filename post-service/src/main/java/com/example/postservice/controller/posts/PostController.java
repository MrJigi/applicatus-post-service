package com.example.postservice.controller.posts;

import com.example.postservice.controller.posts.requests.CreatePostRequest;
import com.example.postservice.controller.posts.response.CreatePostResponse;
import com.example.postservice.model.posts.Post;
import com.example.postservice.service.posts.PostService;
import lombok.AllArgsConstructor;
//import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
       return postService.getAllPosts();
    }

    @GetMapping("/getAll/{authorID}")
    public List<Post> getAllUserPosts(@PathVariable UUID authorID){
        List<Post> userPosts = postService.getAllPostWithAuthorID(authorID);
        if(!userPosts.isEmpty()){
            return userPosts;
        }
        return null;
    }
    @GetMapping("/get/{postID}")
    public Post getUserPost(@PathVariable UUID postID) {
    Post userPost = postService.findPostWithPostID(postID);
        if (userPost != null ){
            return userPost;
        }
        return null;
    }


    @DeleteMapping("/deleteAllUserPosts/{AuthorId}")
    public void deleteAllUserPosts(@PathVariable UUID AuthorId){
        postService.deleteAllPostsWithAuthorID(AuthorId);
    }

    @DeleteMapping("/deactivatePosts/{AuthorId}")
    public ResponseEntity<String> setPostsInactive(@PathVariable UUID AuthorId) {
        return ResponseEntity.ok().body(postService.deleteAllPostsWithAuthorID(AuthorId)).getBody();
    }

    @PostMapping("/createPost")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request){
//        Post response = postService.savePost(request);
        CreatePostResponse response1 = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response1);
    }



}
