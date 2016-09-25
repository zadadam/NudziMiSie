package com.example.marcin.nudzimisie.data;
public class PostItem {
    private int post_id;
    private String post_title, post_user_name, post_img_url, post_timestamp, post_tags;
    public PostItem() {
    }
    public PostItem(int post_id, String post_title, String post_user_name, String
            post_img_url, String post_timestamp, String post_tags) {
        super();
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_user_name = post_user_name;
        this.post_tags = post_tags;
        this.post_img_url = post_img_url;
        this.post_timestamp = post_timestamp;
    }
    public int getPostId() {
        return post_id;
    }
    public void setPostId(int post_id) {
        this.post_id = post_id;
    }
    public String getPostTitle() {
        return post_title;
    }
    public void setPostTitle(String post_title) {
        this.post_title = post_title;
    }
    public void setPost_user_name(String post_user_name){
        this.post_user_name = post_user_name;
    }
    public String getPost_user_name(){
        return post_user_name;
    }
    public String getImageURL() {
        return post_img_url;
    }
    public void setImageURL(String imageURL) {
        this.post_img_url = imageURL;
    }
    public String getPostTimestamp() {
        return post_timestamp;
    }
    public void setPostTimestamp(String post_timestamp) {
        this.post_timestamp = post_timestamp;
    }
    public void setPost_tags(String post_tags){
        this.post_tags = post_tags;
    }
    public String getPost_tags(){
        return post_tags;
    }

}