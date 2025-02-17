package com.socialmedia.socialapp.DbEntity.Notifications.DTO;

import java.util.List;

public class MentionNotificationRequest {
    private Long comment_id;
    private Long post_id;
    private Long commenter_id;
    private List<String> mentioned_users;

    public MentionNotificationRequest() {
    }

    public MentionNotificationRequest(Long comment_id, Long post_id, Long commenter_id, List<String> mentioned_users) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.commenter_id = commenter_id;
        this.mentioned_users = mentioned_users;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getCommenter_id() {
        return commenter_id;
    }

    public void setCommenter_id(Long commenter_id) {
        this.commenter_id = commenter_id;
    }

    public List<String> getMentioned_users() {
        return mentioned_users;
    }

    public void setMentioned_users(List<String> mentioned_users) {
        this.mentioned_users = mentioned_users;
    }

    @Override
    public String toString() {
        return "MentionNotificationRequest{" +
                "comment_id=" + comment_id +
                ", post_id=" + post_id +
                ", commenter_id=" + commenter_id +
                ", mentioned_users=" + mentioned_users +
                '}';
    }
}
