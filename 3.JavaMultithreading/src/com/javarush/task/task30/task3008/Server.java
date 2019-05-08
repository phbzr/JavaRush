package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for (Connection cont : connectionMap.values()){
            try {
                cont.send(message);
            } catch (IOException e) {
                System.out.println("Не удалось отправить сообщение");
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSock = new ServerSocket(ConsoleHelper.readInt())){
            System.out.println("Сервер запущен.");
            while (true) {
                Socket socket = serverSock.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static class Handler extends Thread{

        @Override
        public void run() {
            String clientName = "";
            if (socket.getRemoteSocketAddress()!= null) {
                ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
                try (Connection connection = new Connection(socket)) {
                    clientName = serverHandshake(connection);
                    Server.sendBroadcastMessage(new Message(MessageType.USER_ADDED,clientName));
                    notifyUsers(connection, clientName);
                    serverMainLoop(connection, clientName);
                } catch (IOException | ClassNotFoundException e) {
                    ConsoleHelper.writeMessage("Произошла ошибка при обмене данными.");
                }
                connectionMap.remove(clientName);
                Server.sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                ConsoleHelper.writeMessage("Соединение закрыто");
            }
        }

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message msg = connection.receive();
                if (msg.getType() == MessageType.TEXT) {
                    String text = String.format("%s: %s",userName,msg.getData());
                    Message tmp =new Message(MessageType.TEXT,text);
                    Server.sendBroadcastMessage(tmp);
                } else {
                    ConsoleHelper.writeMessage("Error!");
                }
            }

        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()){
                String usName = pair.getKey();
                Connection adres = pair.getValue();
                if (!userName.equals(usName)) {
                    connection.send(new Message(MessageType.USER_ADDED, usName));
                }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            Message mes = null;
            boolean flag = false;
            while (!flag) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                mes = connection.receive();
                if (mes.getType() == MessageType.USER_NAME) {
                    if (!mes.getData().isEmpty() && !connectionMap.containsKey(mes.getData())) {
                        connectionMap.put(mes.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        flag = true;
                    }
                }
            }
            return mes.getData();
        }
    }
}


