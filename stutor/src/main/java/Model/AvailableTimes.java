package Model;
import java.sql.Time;
import java.util.ArrayList;


public class AvailableTimes {
    private ArrayList<Time> times;

    public AvailableTimes(){
        times = new ArrayList<Time>();
    }

    public ArrayList<Time> getAvailableTimes(){
        return times;
    }

    public void addTime(Time time){
        times.add(time);
    }

    public void removeTime(Time time){
        times.remove(time);
    }
}
