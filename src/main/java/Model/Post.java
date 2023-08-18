package Model;

public class Post {
    private int userId;

    private int subId;

    private String text;

    private int id;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public Post(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text) {this.text = text;}

    public void setSubId(int subId) {this.subId = subId;}

    public int getSubId() {return this.subId;}

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
