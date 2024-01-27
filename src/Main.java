import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{

    private static final int TIMEOUT = 1000;

    public static void main(String[] args) {
        String host = "localhost";
        int minPort = 0;
        int maxPort = 65535;

        System.out.println("Rozpoczynam skanowanie portów dla hosta: " + host);

        long millisActualTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int port = minPort; port <= maxPort; port++) {
            final int currentPort = port;
            executorService.execute(() -> scanPort(host, currentPort, TIMEOUT));
        }

        executorService.shutdown();


        while (!executorService.isTerminated()) {

        }

        long executionTime = System.currentTimeMillis() - millisActualTime;
        System.out.println("Czas wykonania programu: " + executionTime + " ms");
    }

    private static void scanPort(String host, int port, int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.close();
            System.out.println("Port " + port + " jest otwarty");
        } catch (Exception e) {

        }
    }
}