package Model;

public class Message {
    private int message_id;
    private final String sender;
    private final String receiver;
    private final String message;

    public Message(String sender, String receiver, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSender(){
        return this.sender;
    }

    public String getReceiver(){
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message msg = (Message) o;
        return message_id == msg.message_id;
    }
}
