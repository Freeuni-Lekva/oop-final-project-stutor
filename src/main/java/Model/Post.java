package Model;

public class Post {
    private String username;

    private String subject;

    private String text;

    private int id;

    public Post(String text, String username, String subject){
        this.text = text;
        this.username = username;
        this.subject = subject;
    }
    
    public int getUsername() {
        return this.username;
    }

    public String getText(){
        return this.text;
    }


    public int getSubject() {return this.subject;}

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
