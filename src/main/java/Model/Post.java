package Model;

import javax.ws.rs.POST;

public class Post {
    private String username;

    private String subject;

    private String text;

    private int id;

    private POSTTYPE type;

    public Post(String username, String subject, String type, String text){
        this.text = text;
        this.username = username;
        this.subject = subject;
        this.type = POSTTYPE.toPostType(type);
    }
    
    public String getUsername() {
        return this.username;
    }

    public String getText(){
        return this.text;
    }

    public POSTTYPE getType(){
        return this.type;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }
}
