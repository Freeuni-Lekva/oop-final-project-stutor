package Service;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private List<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;
    public Server(){
        connections = new ArrayList<>();
        done = false;
    }
    @Override
    public void run() {
        try {
            server = new ServerSocket(1235);
            pool = Executors.newCachedThreadPool();
            while(!done){
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }

        } catch (IOException e) {
            try {
                shutdown();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void broadcast(String msg){
        for (ConnectionHandler connection : connections) {
            if (connection != null) connection.sendMessage(msg);
        }
    }

    public void shutdown() throws IOException {
        done = true;
        if(!server.isClosed()) server.close();
        for (ConnectionHandler connection : connections) {
            if (connection != null) connection.shutdown();
        }
    }

    private class ConnectionHandler implements Runnable{
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;
        public ConnectionHandler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try{
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("enter username: ");
                nickname = in.readLine();
                System.out.println("nickname ready");
                broadcast(nickname + " joined the chat");
                String msg;
                while((msg = in.readLine()) != null){
                    if(msg.startsWith("/quit")){
                        broadcast(nickname + " left the chat");
                        shutdown();
                    }else{
                        broadcast(nickname + ": " + msg);
                    }
                }
            }catch (IOException e){
                try {
                    shutdown();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        public void shutdown() throws IOException {
            in.close();
            out.close();
            pool.shutdown();
            if(!client.isClosed()){
                client.close();
            }
        }

        public void sendMessage(String msg){
            out.println(msg);
        }
    }

    public static void main(String[] main){
        Server server = new Server();
        server.run();
    }
}
