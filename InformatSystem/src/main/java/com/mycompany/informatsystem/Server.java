/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.informatsystem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Server {
     ArrayList<ObjectOutputStream> clientstreams = new ArrayList<ObjectOutputStream>();
     public Server(int port){
        try {
            ServerSocket mySocket = new ServerSocket(port);
            waitForClients(mySocket);
        } catch (IOException e) {
            System.out.println("Unable to start.");
            e.printStackTrace();
        }
    }
 public static void main( String[] args ) throws IOException
    {
         new Server(45000);
    }
    private void waitForClients(ServerSocket mySocket) {
        while(true){
            try {
                System.out.println("Ready to receive");
                Socket client = mySocket.accept();
                clientstreams.add(new ObjectOutputStream(client.getOutputStream()));
                System.out.println(client.getInetAddress().getHostAddress()+" connected to the Server");
                Thread t = new Thread(new ClientHandler(client));
                t.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void shareToAll(Object objectToSchare){
        for(ObjectOutputStream stream:clientstreams){
            try {
                stream.writeObject(objectToSchare);
                stream.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable{
        Socket clientSocket;

        public ClientHandler(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                while(true){
                    try {
                        ois.readObject();

                    } catch (ClassNotFoundException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }catch(SocketException e){
                System.out.println(clientSocket.getInetAddress().getHostAddress()+" disconnected from the Server");
                clientstreams.remove(clientSocket);
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }  
}
