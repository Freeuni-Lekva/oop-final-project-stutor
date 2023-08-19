package Model;

public class Message {
    private int message_id;
    private final int sender;
    private final int receiver;
    private final String message;

    public Message(int sender, int receiver, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public int getSender(){
        return this.sender;
    }

    public int getReceiver(){
        return this.receiver;
    }

    public String getMessage(){
        return this.message;
    }


    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id){
        this.message_id = message_id;
    }
}
