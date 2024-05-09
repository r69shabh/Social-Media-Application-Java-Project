package co.newtonschool.socialmedia.service;

import co.newtonschool.socialmedia.request.PostRequest;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<?> readPosts();
    ResponseEntity<?> createPost(PostRequest postRequest);
    ResponseEntity<?> likePost(int postId);
    ResponseEntity<?> unlikePost(int postId);
    ResponseEntity<?> getPostById(int postId);
    void updatePost(int postId, PostRequest postRequest); 
}
