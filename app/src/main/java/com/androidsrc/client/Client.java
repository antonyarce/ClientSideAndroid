package com.androidsrc.client;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.widget.TextView;



public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Socket socket;


    Client(String addr, int port,TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse=textResponse;
    }


    @Override
    protected Void doInBackground(Void... arg0) {

        socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            //Recibe mensaje del servidor
            DataInputStream istream = new DataInputStream(socket.getInputStream());
            response = istream.readUTF();

            // Envia mensaje al servidor
            OutputStream ostream = socket.getOutputStream();
            ObjectOutput s = new ObjectOutputStream(ostream);
            s.writeUTF("Hola desde el cliente");
            s.flush();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}


/*
public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Socket socket;


    Client(String addr, int port,TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse=textResponse;
    }


    @Override
    protected Void doInBackground(Void... arg0) {

        socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();


while ((bytesRead = inputStream.read(buffer)) != -1) {
        byteArrayOutputStream.write(buffer, 0, bytesRead);
        response += byteArrayOutputStream.toString("UTF-8");
        }

        //response += ", ip: "+socket.getInetAddress();


        } catch (UnknownHostException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        response = "IOException: " + e.toString();
        } finally {
        if (socket != null) {
        try {
        socket.close();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        }
        }
        return null;
        }

@Override
protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
        }

        }
 */

