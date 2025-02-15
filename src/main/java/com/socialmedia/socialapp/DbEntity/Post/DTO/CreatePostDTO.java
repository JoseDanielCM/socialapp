package com.socialmedia.socialapp.DbEntity.Post.DTO;

public class CreatePostDTO {
    private Long user_id;
    private String title;
    private String content;
    private String img_url;

    public CreatePostDTO() {
    }

    public CreatePostDTO(Long user_id, String title, String content, String img_url) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.img_url = img_url;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "CreatePostDTO{" +
                "user_id=" + user_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
