package Model;

import java.util.Objects;

public class Post {
    private String username;
    private int postId;
    private String text;
    private Subject subject;

    public Post(String username, Subject subject, String text){
        this.subject = subject;
        this.username = username;
        this.text = text;
    }

    public String getUsername(){
        return this.username;
    }

    public Subject getSubject(){
        return this.subject;
    }

    public String getText(){
        return this.text;
    }

    public int getPostId(){
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Post)) return false;

        Post a = (Post)other;
        return Objects.equals(a.postId, this.postId);
    }
}
