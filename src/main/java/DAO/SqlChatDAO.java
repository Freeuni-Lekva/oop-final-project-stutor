package DAO;

import DAO.Interfaces.ChatDAO;
import Model.Message;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlChatDAO implements ChatDAO {
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "chat";

    public SqlChatDAO() throws ClassNotFoundException {

    }

    @Override
    public boolean sendMessage(Message message) throws SQLException {
        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (sender_id, receiver_id, message) VALUES (?, ?, ? ); ");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setInt(1, message.getSender());
        statement.setInt(2, message.getReceiver());
        statement.setString(3, message.getMessage());

        int check = statement.executeUpdate();

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public boolean deleteConversation(int first, int second) throws SQLException {
        StringBuilder code = new StringBuilder();

        code.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        code.append("(sender_id = ? and receiver_id = ?) or (sender_id = ? and receiver_id = ?)\n");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setInt(1, first);
        statement.setInt(2, second);
        statement.setInt(3, second);
        statement.setInt(4, first);

        int check = statement.executeUpdate();

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public List<Integer> getUsers(int user_id) throws SQLException {
        List<Integer> res = new ArrayList<>();
        StringBuilder code = new StringBuilder();

        code.append("Select * FROM ").append(TABLENAME).append(" WHERE ");
        code.append("sender_id = ? or receiver_id = ?\n");
        code.append("order by message_id desc;");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setInt(1, user_id);
        statement.setInt(2, user_id);

        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            int id1 = rs.getInt("sender_id");
            int id2 = rs.getInt("receiver_id");
            if(user_id != id1 && !res.contains(id1)) res.add(id1);
            if(user_id != id2 && !res.contains(id2)) res.add(id2);
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);
        return res;
    }

    @Override
    public List<Message> getConversation(int first, int second) throws SQLException {
        List<Message> res = new ArrayList<>();
        StringBuilder code = new StringBuilder();

        code.append("Select * FROM ").append(TABLENAME).append(" WHERE ");
        code.append("(sender_id = ? and receiver_id = ?) or (sender_id = ? and receiver_id = ?)\n");
        code.append("order by message_id desc;");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setInt(1, first);
        statement.setInt(2, second);
        statement.setInt(3, second);
        statement.setInt(4, first);

        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            res.add(new Message(rs.getInt("sender_id"), rs.getInt("receiver_id"), rs.getString("message")));
            res.get(res.size() - 1).setMessage_id(rs.getInt("message_id"));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    public void deleteChat() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM ").append(TABLENAME).append(";");

        statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);
    }
}
