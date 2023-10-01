package e.network;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample sample = new SocketClientSample();
        sample.sendSocketSample();
    }
    public void sendSocketSample() {
        for(int loop=0;loop<3;loop++) {
            sendAndReceiveSocketData("I liked java at "+ new Date());
        }
        sendAndReceiveSocketData("EXIT");
    }

    public void sendAndReceiveSocketData(String data) {
        Socket socket = null;
        try {
            System.out.println("Client:Connecting");
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("Client:Connect status="+socket.isConnected());
            Thread.sleep(1000);
            Socket finalSocket = socket;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream stream = finalSocket.getOutputStream();
                        BufferedOutputStream out = new BufferedOutputStream(stream);
                        byte[] bytes = data.getBytes();
                        out.write(bytes);
                        System.out.println("Client:Sent data");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inStream = finalSocket.getInputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
                        String inData = null;
                        StringBuilder receivedData = new StringBuilder();
                        while ((inData = in.readLine()) != null) {
                            receivedData.append(inData);
                        }
                        System.out.println("Received data:" + receivedData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if(socket != null) {
//                try {
//                    socket.close();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
