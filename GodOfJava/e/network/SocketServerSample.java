package e.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample sample = new SocketServerSample();
        sample.startReplyServer();
    }
    public void startServer() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(9999);
            while(true) {
                System.out.println("Server:Waiting for request.");
                client = server.accept();
                System.out.println("Server:Accepted.");
                InputStream stream = client.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;
                StringBuilder receivedData = new StringBuilder();
                while((data=in.readLine()) != null) {
                    receivedData.append(data);
                }
                System.out.println("Received data:"+receivedData);
                in.close();
                stream.close();
                client.close();
                if(receivedData != null && "EXIT".equals(receivedData.toString())) {
                    System.out.println("Stop SocketServer");
                    break;
                }
                System.out.println("-----------------------");
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void startReplyServer() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(9999);
            while(true) {
                System.out.println("Server:Waiting for request.");
                client = server.accept();
                System.out.println("Server:Accepted.");
                Socket finalClient = client;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream stream = finalClient.getInputStream();
                            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                            String data = null;
                            StringBuilder receivedData = new StringBuilder();
                            while ((data = in.readLine()) != null) {
                                receivedData.append(data);
                            }
                            System.out.println("Received data:" + receivedData);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OutputStream outStream = finalClient.getOutputStream();
                            BufferedOutputStream out = new BufferedOutputStream(outStream);
                            out.write("OK".getBytes());
                            System.out.println("Send Data");
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
