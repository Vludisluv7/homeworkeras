 package server.server;

 import server.client.Client;

 public interface Controller {
    void connectUser(Client client);
    void getHistory();
     void disconnectUser(Client client);
    void sendMessage(String s);
}
