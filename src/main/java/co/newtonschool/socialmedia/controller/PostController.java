package co.newtonschool.socialmedia.controller;

import co.newtonschool.socialmedia.request.PostRequest;
import co.newtonschool.socialmedia.response.PostResponse;
import co.newtonschool.socialmedia.response.PostResponseList;
import co.newtonschool.socialmedia.service.PostService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static co.newtonschool.socialmedia.SocialMediaApplication.getPostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @PostConstruct
    void init() {
        postService = getPostService();
    }

    @GetMapping("")
    public ModelAndView readPosts() {
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<?> responseEntity = postService.readPosts();
        PostResponseList postResponseList = (PostResponseList) responseEntity.getBody();
        List<PostResponse> postResponses = postResponseList.getPostResponseList();
    
        // Sort the posts in descending order by creation date
        Collections.sort(postResponses, Comparator.comparing(PostResponse::getCreatedAt).reversed());
    
        modelAndView.addObject("postList", postResponses);
        modelAndView.setViewName("post-list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-post");
        return modelAndView;
    }

    @GetMapping("/{postId}/edit")
    public ModelAndView editPost(@PathVariable("postId") int postId) {
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<?> responseEntity = postService.getPostById(postId);
        PostResponse postResponse = (PostResponse) responseEntity.getBody();
        modelAndView.addObject("post", postResponse);
        modelAndView.setViewName("edit-post");
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView createPosts(PostRequest postRequest) {
        postService.createPost(postRequest);
        return new ModelAndView("redirect:" + "/posts");
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable("postId") int postId) {
        return postService.likePost(postId);
    }

    @PostMapping("/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@PathVariable("postId") int postId) {
        return postService.unlikePost(postId);
    }

    @PostMapping("/{postId}")
    public ModelAndView updatePost(@PathVariable("postId") int postId, PostRequest postRequest) {
        postService.updatePost(postId, postRequest);
        return new ModelAndView("redirect:/posts");
    }

    
}