import java.util.ArrayList;
import java.util.List;

public class StudentAccount implements Account{
    private String username;
    private String password;
    private int userType;
    private String bio;
    private ArrayList<String> reviews;
    private int userId;
    private AvailableTimes times;
    private boolean inGroup;
    private boolean remote;
    private String location;

    public StudentAccount(String username, String password, int userType, int userId){
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.reviews = new ArrayList<String>();
        this.userId = userId;
        inGroup = false;
        times = new AvailableTimes();
        remote = true;
    }

    public int getUserId(){
        return userId;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserTypes() {
        return userType;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<String> getReviews() { return reviews; }

    public AvailableTimes getTimes() {
        return times;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setBio(String bio) { this.bio = bio; }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void addAvaialbleTimes(Time time) {
        times.addTime(time);
    }

    public void removeAvailableTimes(Time time) {
        times.removeTime(time);
    }

    public void setInGroup(boolean state) { inGroup = state; }

    public boolean getInGroup(){ return inGroup; }

    public void setRemote(boolean state) { remote = state; }

    public boolean getRemote(){ return remote; }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
