package DAO.Interfaces;

import Model.Message;

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
     * @param first username of the first one in conversation
     * @param second username of the second one in conversation
     * @return null if some kind of error occurred. returns true otherwise.
     */
    public boolean deleteConversation(String first, String second) throws SQLException;

    /**
     * searches the users which had a conversation with user_id.
     * @param user which users conversations we are looking at
     * @return null if some kind of error occurred. returns users list otherwise.
     */
    public List<String> getUsers(String user) throws SQLException;

    /**
     * gets conversation between two human beings.
     * @param first username of the first one in conversation
     * @param second username of the second one in conversation
     * @return null if some kind of error occurred. returns list of messages otherwise.
     */
    public List<Message> getConversation(String first, String second) throws SQLException;

    /**
     * deletes chat.
     */
    public void deleteChat() throws  SQLException;
}
