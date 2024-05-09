package co.newtonschool.socialmedia.model;

import java.time.LocalDateTime;

public class Post {
    private int postId;
    private String content;
    private LocalDateTime createdAt;
    private int likeCount;
    private int unlikeCount;

    // Constructor
    public Post(String content) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.likeCount = 0;
        this.unlikeCount = 0;
    }

    // Getter and Setter for postId
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and Setter for likeCount
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    // Getter and Setter for unlikeCount
    public int getUnlikeCount() {
        return unlikeCount;
    }

    public void setUnlikeCount(int unlikeCount) {
        this.unlikeCount = unlikeCount;
    }
}
