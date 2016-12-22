/**
 * Created by Маша on 22.12.2016.
 */

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        InetAddress addr = InetAddress.getByName("localhost");

        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, Server.PORT);
        try {
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            out.println("GET /ex.html /HTTP/1.1\r\n\r\n ");
            out.flush();

            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
        } finally {
            socket.close();
        }
    }
}

