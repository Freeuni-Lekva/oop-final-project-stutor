package DAO.Interfaces;

import Model.Message;
import Model.User;

import javax.mail.MessageAware;
import java.sql.SQLException;
import java.util.List;

public interface ChatDAO {
    /**
     * adds the new message into the database
     * @param message message to be added
     * @return false if some kind of error occurred or message could not be sent. returns true otherwise.
     */
    public boolean sendMessage(Message message) throws SQLException;

    /**
     * deletes conversation between two human beings.
     * @param first id of the first one in conversation
     * @param second id of the second one in conversation
     * @return null if some kind of error occurred. returns true otherwise.
     */
    public boolean deleteConversation(int first, int second) throws SQLException;

    /**
     * searches the users which had a conversation with user_id.
     * @param user_id which users conversations we are looking at
     * @return null if some kind of error occurred. returns users list otherwise.
     */
    public List<Integer> getUsers(int user_id) throws SQLException;

    /**
     * gets conversation between two human beings.
     * @param first id of the first one in conversation
     * @param second id of the second one in conversation
     * @return null if some kind of error occurred. returns list of messages otherwise.
     */
    public List<Message> getConversation(int first, int second) throws SQLException;

    /**
     * delets chat.
     */
    public void deleteChat() throws  SQLException;
}
