package com.socialmedia.socialapp.DbEntity.Post.DTO;

public class UpdatePostDTO {

    private String title;

    private String content;
    private String img_url;

    public UpdatePostDTO() {
    }

    public UpdatePostDTO(String title, String content, String img_url) {
        this.title = title;
        this.content = content;
        this.img_url = img_url;
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
        return "UpdatePostDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
