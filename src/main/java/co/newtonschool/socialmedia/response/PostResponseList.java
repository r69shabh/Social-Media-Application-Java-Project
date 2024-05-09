package co.newtonschool.socialmedia.response;

import java.util.List;

public class PostResponseList {
    private List<PostResponse> postResponseList;

    public PostResponseList(List<PostResponse> postResponseList) {
        this.postResponseList = postResponseList;
    }

    public List<PostResponse> getPostResponseList() {
        return postResponseList;
    }

    public void setPostResponseList(List<PostResponse> postResponseList) {
        this.postResponseList = postResponseList;
    }
}