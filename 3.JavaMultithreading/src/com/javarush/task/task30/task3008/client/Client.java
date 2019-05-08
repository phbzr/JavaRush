package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        synchronized (this) {
            socketThread.start();
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("ERROR!");
                return;
            }
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        while (clientConnected){
            String data = ConsoleHelper.readString();
            if(data.equals("exit")){
                break;
            }else{
                if(shouldSendTextFromConsole())sendTextMessage(data);
            }
        }
    }

    protected Connection connection;

    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread{

        @Override
        public void run() {

            try {
                Socket socket = new Socket(getServerAddress(),getServerPort());
                Connection connection = new Connection(socket);
                Client.this.connection = connection;
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e){
                notifyConnectionStatusChanged(false);
            }


        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (true) {
                Message ms = connection.receive();
                if (ms.getType() == MessageType.NAME_REQUEST) {
                    Message nameres = new Message(MessageType.USER_NAME, getUserName());
                    connection.send(nameres);
                } else if (ms.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true) {
                Message ms = connection.receive();
                if (ms.getType() == MessageType.TEXT) {
                    processIncomingMessage(ms.getData());
                } else if (ms.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(ms.getData());
                } else if (ms.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(ms.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }

        }

    }

    protected String getServerAddress(){
        String addres;
        addres = ConsoleHelper.readString();
        return addres;
    }

    protected int getServerPort(){
        int port;
        port = ConsoleHelper.readInt();
        return port;
    }

    protected String getUserName(){
        String name;
        name = ConsoleHelper.readString();
        return name;
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        }catch (IOException e){
            ConsoleHelper.writeMessage("Возникла ошибка ввода");
            clientConnected = false;
        }


    }
}
