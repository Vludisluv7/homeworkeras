package server.server;

import server.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Client> connectedClients;
    private String chatHistory;

    public Server() {
        connectedClients = new ArrayList<>();
        chatHistory = "";
    }

    public boolean connectUser(Client client) {
        if (connectedClients.contains(client)) {
            return false; // Client is already connected
        } else {
            connectedClients.add(client);
            return true;
        }
    }

    public String getHistory() {
        return chatHistory;
    }

    public void disconnectUser(Client client) {
        connectedClients.remove(client);
    }

    public void sendMessage(String s) {
        chatHistory += s + "\n";
        for (Client client : connectedClients) {
            client.receiveMessage(s);
        }
    }
}

public class Client {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void connect() {
        if (server.connectUser(this)) {
            System.out.println("Connected to server.");
        } else {
            System.out.println("Already connected to server.");
        }
    }

    public void disconnect() {
        server.disconnectUser(this);
        System.out.println("Disconnected from server.");
    }

    public void sendMessage(String s) {
        server.sendMessage(s);
    }

    public void receiveMessage(String s) {
        System.out.println("Received message: " + s);
    }
}