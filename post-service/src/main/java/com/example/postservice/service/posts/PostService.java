package com.example.postservice.service.posts;

import com.example.postservice.controller.posts.requests.CreatePostRequest;
import com.example.postservice.controller.posts.response.CreatePostResponse;
import com.example.postservice.model.posts.Post;
import com.example.postservice.persistence.posts.IPostRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PostService implements IPostService {

    private final IPostRepo postRepository;
   // private final WebClient webClient;
//    private final Auth authorizationService;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public CreatePostResponse createPost(CreatePostRequest request){
        Post newPost = savePost(request);
        return CreatePostResponse.builder()
                .postID(UUID.randomUUID())
                .postAuthor(request.getPostAuthor())
                .topic(request.getTopic())
                .content(request.getContent())
                .likeCount(request.getLikeCount())
                .postedAt(request.getPostedAt())
                .isInactive(request.getIsInactive())
                .build();
    }

    @Override
    public Post savePost(CreatePostRequest request) {
        Post newPost = Post.builder()
                .postID(request.getPostAuthor())
                .authorID(request.getPostAuthor())
                .topic(request.getTopic())
                .content(request.getContent())
                .likeCount(request.getLikeCount())
                .postedAt(request.getPostedAt())
                .isInactive(request.getIsInactive())
                .build();
        return postRepository.save(newPost);

                //Request from messaging user profile
//                .postAuthor(request.getPostAuthor())
    //        Post newPost = savePost(request);
    //        private String postAuthor;
    //    private String topic;
    //    private String content;
    //    private LocalDateTime postedAt;
    //        return newPost;
    }


    public void getUserInformation(){
        String endPointUser;
    }
    public ResponseEntity deleteAllPostsWithAuthorID(UUID authorID){
        postRepository.deleteAllByAuthorID(authorID);
        return ResponseEntity.ok("all posts by this user has been deleted");
    }

    public ResponseEntity deactivateAllPostsWithAuthorID(UUID authorID) {
        return null;
    }
    public List<Post> getAllPostWithAuthorID(UUID authorID){
        List<Post> authorPostID = postRepository.getAllByAuthorID(authorID);
        if(!authorPostID.isEmpty()){
            return authorPostID;
        }
        return null;
    }



    public Post findPostWithPostID(UUID postID) {
       Post post = postRepository.findPostByPostID(postID);
        if(post != null){
            return post;
        }
        return null;
    }

    @Override
    public Optional<Post> findPostInformation(String username) {
        return Optional.empty();
    }

    @Override
    public void deletePost(UUID postID) {
        postRepository.deleteByPostID(postID);
    }


}
