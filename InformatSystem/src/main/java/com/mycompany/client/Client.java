/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Receiver;

/**
 *
 * @author Admin
 */
public class Client {
    boolean isString = false;
    Socket s; // наш сокет
        ObjectInputStream br; // буферизировнный читатель сокета
        ObjectOutputStream bw; // буферизированный писатель в сокет
        ClientController controller;
    public Client(String host, int port) throws IOException {
         s = new Socket(host, port);
       br = new ObjectInputStream(s.getInputStream()) ;  
            bw = new ObjectOutputStream(s.getOutputStream());
            new Thread(new Receiver() {}).start();// создаем и запускаем нить асинхронного чтения из сокета
            controller = new ClientController(this);
            System.out.println(">>Client create");
    }    

    public void setIsString(boolean isString) {
        this.isString = isString;
    }
    
     public void runSend(String[] str) {
         
     }
      public void run() {
          while (true) {
              if(isString == true){
                  try {
                      bw.writeObject( controller.getStringForSend());
                  } catch (IOException ex) {
                      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  isString = false;
              }
          }
     }
         public synchronized void close() {//метод синхронизирован, чтобы исключить двойное закрытие.
        if (!s.isClosed()) { // проверяем, что сокет не закрыт...
            try {
                s.close(); // закрываем...
                System.exit(0); // выходим!
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }
//         void setEmp () throws IOException, ClassNotFoundException{
//             Object object = br.readObject();
//             controller.setList(object);
//         }

    public static void main(String[] args)  { // входная точка программы
        try {
            new Client("192.168.1.143", 45000).run(); // Пробуем приконнетиться...
            System.out.println("Client"); 
        } catch (IOException e) { // если объект не создан...
            System.out.println("Unable to connect. Server not running?"); // сообщаем...
        }
    }
    
       private class Receiver implements Runnable{
        /**
         * run() вызовется после запуска нити из конструктора клиента чата.
         */
        public void run() {
            while (!s.isClosed()) { //сходу проверяем коннект.
                Object line = null;
                try {
                    line = br.readObject(); // пробуем прочесть
                } catch (IOException e) { // если в момент чтения ошибка, то...
                    // проверим, что это не банальное штатное закрытие сокета сервером
                    if ("Socket closed".equals(e.getMessage())) {
                        break;
                    }
                    System.out.println("Connection lost"); // а сюда мы попадем в случае ошибок сети.
                    close(); // ну и закрываем сокет (кстати, вызвается метод класса ChatClient, есть доступ)
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (line == null) {  // строка будет null если сервер прикрыл коннект по своей инициативе, сеть работает
                    System.out.println("Server has closed connection");
                    close(); // ...закрываемся
                } else { // иначе печатаем то, что прислал сервер.
                    System.out.println("Server:" + line);
                }
            }
        }
    }     
    
        
        
}
