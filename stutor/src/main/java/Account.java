import java.util.ArrayList;
import java.util.List;

public interface Account {
    //gets userId
    public int getUserId();

    //gets username
    public String getUsername();

    //gets password
    public String getPassword();

    //gets user types
    public int getUserTypes();

    //gets user bio
    public String getBio();

    //gets reviews
    public ArrayList<String> getReviews();

    //gets users Available times;
    public AvailableTimes getTimes();

    public void setUsername(String username);

    public void setPassword(String password);

    public void setUserType(int userType);

    public void setBio(String bio);

    public void addReview(String review);

    public void addAvaialbleTimes(Time time);

    public void removeAvailableTimes(Time time);
}
