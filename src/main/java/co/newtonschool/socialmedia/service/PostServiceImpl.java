package co.newtonschool.socialmedia.service;

import co.newtonschool.socialmedia.model.Post;
import co.newtonschool.socialmedia.repository.PostRepository;
import co.newtonschool.socialmedia.request.PostRequest;
import co.newtonschool.socialmedia.response.GenericResponse;
import co.newtonschool.socialmedia.response.PostResponse;
import co.newtonschool.socialmedia.response.PostResponseList;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static co.newtonschool.socialmedia.SocialMediaApplication.getPostRepository;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private List<PostResponse> posts = new ArrayList<>();

    public PostServiceImpl() {
        postRepository = getPostRepository();
        initializePosts();
    }

    private void initializePosts() {
        List<Post> postList = postRepository.getAllPosts();
        for (Post post : postList) {
            PostResponse postResponse = new PostResponse(
                    post.getPostId(),
                    post.getContent(),
                    post.getCreatedAt(),
                    post.getLikeCount(),
                    post.getUnlikeCount()
            );
            posts.add(postResponse);
        }
    }

    public ResponseEntity<?> readPosts() {
        Collections.sort(posts, Comparator.comparing(PostResponse::getCreatedAt).reversed());
        return ResponseEntity.ok(new PostResponseList(posts));
    }

    public ResponseEntity<?> createPost(PostRequest postRequest) {
        Post post = new Post(postRequest.getContent());
        postRepository.savePost(post);

        PostResponse newPostResponse = new PostResponse(
                post.getPostId(),
                post.getContent(),
                post.getCreatedAt(),
                post.getLikeCount(),
                post.getUnlikeCount()
        );
        posts.add(newPostResponse);

        return ResponseEntity.ok(new GenericResponse("Post Added Successfully"));
    }

    public ResponseEntity<?> likePost(int postId) {
        Post post = postRepository.getPostById(postId);
        int likeCount = post.getLikeCount();
        likeCount++;
        post.setLikeCount(likeCount);
        postRepository.savePost(post);

        updatePostResponse(postId, post);

        return ResponseEntity.ok(new GenericResponse("Liked"));
    }

    public ResponseEntity<?> unlikePost(int postId) {
        Post post = postRepository.getPostById(postId);
        int unlikeCount = post.getUnlikeCount();
        unlikeCount++;
        post.setUnlikeCount(unlikeCount);
        postRepository.savePost(post);

        updatePostResponse(postId, post);

        return ResponseEntity.ok(new GenericResponse("Unliked"));
    }

    public void updatePost(int postId, PostRequest postRequest) {
        Post post = postRepository.getPostById(postId);
        post.setContent(postRequest.getContent());
        postRepository.savePost(post);

        // Update the corresponding PostResponse object in the posts list
        for (PostResponse postResponse : posts) {
            if (postResponse.getPostId() == postId) {
                postResponse.setContent(postRequest.getContent());
                break;
            }
        }
    }

    public ResponseEntity<?> getPostById(int postId) {
        Post post = postRepository.getPostById(postId);
        PostResponse postResponse = new PostResponse(
                post.getPostId(),
                post.getContent(),
                post.getCreatedAt(),
                post.getLikeCount(),
                post.getUnlikeCount()
        );
        return ResponseEntity.ok(postResponse);
    }

    private void updatePostResponse(int postId, Post post) {
        for (PostResponse postResponse : posts) {
            if (postResponse.getPostId() == postId) {
                postResponse.setLikeCount(post.getLikeCount());
                postResponse.setUnlikeCount(post.getUnlikeCount());
                break;
            }
        }
    }
}
