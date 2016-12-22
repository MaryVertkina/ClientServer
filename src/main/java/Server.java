import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Маша on 22.12.2016.
 */

public class Server {
    public static final int PORT = 8080;

    public static void main(String[] args)  {
        ServerSocket s = null;
        try {
            s = new ServerSocket(PORT);

            System.out.println("Started: " + s);
            try {
                System.out.println("Waiting for a client...");
                while (true) {
                    Socket socket = s.accept();
                    System.out.println("Connection accepted: " + socket);
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                        String str = in.readLine();
                        System.out.println(str);


                        try {
                            String adress = parse(str);
                            out.print("HTTP/1.1 200 OK \r\n\r\n");
                            BufferedReader reader = new BufferedReader(new FileReader(adress));
                            String b;
                            b = reader.readLine();
                            while (b != null) {
                                System.out.println(b);
                                out.println(b);
                                b = reader.readLine();

                            }
                            out.flush();
                            reader.close();
                        } catch (Exception ex) {
                            System.out.println("Couldn't read file");
                        }
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                s.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't listen to port" + PORT);
        }
    }

    public static String parse(String str) {
        String[] arr = str.split(" ");
        String a = arr[1].substring(1);
        return a;
    }
}
