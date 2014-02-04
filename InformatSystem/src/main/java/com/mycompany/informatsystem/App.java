package com.mycompany.informatsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private ServerSocket ss; 
    private Thread serverThread;
    private int port;
    BlockingQueue<SocketProcessor> q = new LinkedBlockingQueue<SocketProcessor>();
    SController controller = new SController();
    
    
    public App(int port) throws IOException {
         ss = new ServerSocket(port);
        this.port = port; 
         System.out.println(">>SERVER:Server create");
        
    }
    
    void run() {
        serverThread = Thread.currentThread(); // со старта сохраняем нить (чтобы можно ее было interrupt())
        while (true) { //бесконечный цикл, типа...
            Socket s = getNewConn(); // получить новое соединение или фейк-соедиение
            if (serverThread.isInterrupted()) { // если это фейк-соединение, то наша нить была interrupted(),
                // надо прерваться
                break;
            } else if (s != null){ // "только если коннект успешно создан"...
                try {
                    final SocketProcessor processor = new SocketProcessor(s); // создаем сокет-процессор
                    final Thread thread = new Thread(processor); // создаем отдельную асинхронную нить чтения из сокета
                    thread.setDaemon(true); //ставим ее в демона (чтобы не ожидать ее закрытия)
                    thread.start(); 
                    q.offer(processor); 
                    System.out.println(">>SERVER:SocetProcessor create");
                } 
                
                catch (IOException ignored) {}  
            }
        }
    }
    private Socket getNewConn() {
        Socket s = null;
        try {
            s = ss.accept();
        } catch (IOException e) {
            shutdownServer(); // если ошибка в момент приема - "гасим" сервер
        }
        return s;
    }
 
    /**
     * метод "глушения" сервера
     */
    private synchronized void shutdownServer() {
        // обрабатываем список рабочих коннектов, закрываем каждый
        for (SocketProcessor s: q) {
            s.close();
        }
        if (!ss.isClosed()) {
            try {
                ss.close();
            } catch (IOException ignored) {}
        }
    }
    public static void main( String[] args ) throws IOException
    {
         new App(45000).run();
    }
    private class SocketProcessor implements Runnable{
        Socket s; // наш сокет
        ObjectInputStream br; // буферизировнный читатель сокета
        ObjectOutputStream bw; // буферизированный писатель в сокет
        Object object = new Object();
 
        /**
         * Сохраняем сокет, пробуем создать читателя и писателя. Если не получается - вылетаем без создания объекта
         * @param socketParam сокет
         * @throws IOException Если ошибка в создании br || bw
         */
        SocketProcessor(Socket socketParam) throws IOException {
            s = socketParam;
             bw = new ObjectOutputStream(s.getOutputStream());
            br = new ObjectInputStream(s.getInputStream()) ;  
            System.out.println(">>SPR:SocetProcessor create");
            for (SocketProcessor sp:q) {
                        sp.send(controller.empModel.getEmployees());
                        sp.send(controller.depModel.getDepartments());
                    }
        }
 
        /**
         * Главный цикл чтения сообщений/рассылки
         */
        @Override
        public void run() {
            
            while (!s.isClosed()) { // пока сокет не закрыт...
                String[] line = null;
                try {
                    line =(String[]) br.readObject(); // пробуем прочесть.
                     System.out.println(">>SPR:object read");
                } catch (IOException e) {
                    close(); // если не получилось - закрываем сокет.
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
 
                if (line == null) { // если строка null - клиент отключился в штатном режиме.
                    close(); // то закрываем сокет
                } 
                
                else { // иначе - банальная рассылка по списку сокет-процессоров
                    object = controller.convertData(line);
                    for (SocketProcessor sp:q) {
                        sp.send(object);
                    }
                }
            }
        }
 
        /**
         * Метод посылает в сокет полученную строку
         * @param line строка на отсылку
         */
        public synchronized void send(Object objectForDelivery) {
            try {
                bw.writeObject(objectForDelivery); // пишем строку
               // bw.write("\n"); // пишем перевод строки
                bw.flush(); // отправляем
                 System.out.println(">>SPR: Object write and flush");
            } catch (IOException e) {
                close(); //если глюк в момент отправки - закрываем данный сокет.
            }
        }
 
        /**
         * метод аккуратно закрывает сокет и убирает его со списка активных сокетов
         */
        public synchronized void close() {
            q.remove(this); //убираем из списка
            if (!s.isClosed()) {
                try {
                    s.close(); // закрываем
                     System.out.println(">>SPR:Socet close");
                } catch (IOException ignored) {}
            }
        }
 
        /**
         * финализатор просто на всякий случай.
         * @throws Throwable
         */
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            close();
        }
    }
}
