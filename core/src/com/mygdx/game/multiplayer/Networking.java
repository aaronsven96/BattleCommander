package com.mygdx.game.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.mygdx.game.screens.AbstractScreen;
import com.mygdx.game.screens.MultiplayerLobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class Networking {
    private static Set<String[]> listenLog = new HashSet<>();
    private static Set<String[]> serverLog = new HashSet<>();
    private static ArrayBlockingQueue<String[]> serverQueue = new ArrayBlockingQueue<>(99);
    private static MultiplayerLobby screen;
    private static Socket serverConnection;
    private static ServerSocket serverSocket;
    private static String roomOwner;
    private static String roomName;
    private static String roomPassword;
    private static String roomIp;
    private static String roomPort;
    private static String localUser;
    private static String localIp;

    // All client names, so we can check for duplicates upon registration.
    private static Set<String> names = new HashSet<>();

    // The set of all the print writers for all the clients, used for broadcast.
//    private static Set<Socket> writers = new HashSet<>();
    private static HashMap<String, Socket> clientSockets = new HashMap<>();

    public String getLocalIp() {
        // The following code loops through the available network interfaces
        // Keep in mind, there can be multiple interfaces per device, for example
        // one per NIC, one per active wireless and the loopback
        // In this case we only care about IPv4 address ( x.x.x.x format )
        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        String ipAddress = "";
        for(String str:addresses) {
            if (str.equals("127.0.0.1")) {
                continue;
            }
            else if (ipAddress.equals("")) {
                ipAddress = str;
            }
            else {
                ipAddress = ipAddress + ", " + str;
            }
        }

        return ipAddress;
    }

    public static AbstractScreen createServer(String[] args) {
        screen = new MultiplayerLobby();
        serverListener();
        roomIp = args[0];
        localIp = args[0];
        roomName = args[1];
        roomOwner = args[2];
        localUser = args[2];
        roomPassword = args[3];
        screen.addChat("\n\nRoom Name: " + roomName + "\nRoom Owner: " + roomOwner + "\nRoom IP: " + roomIp);
        names.add(roomOwner);
        System.out.println("The server is running...");
        return screen;
    }

    public static AbstractScreen joinServer(String[] args) {
        screen = new MultiplayerLobby();
        roomIp = args[0];
        localUser = args[1];
        roomPassword = args[2];
        localIp = new Networking().getLocalIp();
        clientListener(new String[] {roomIp, "joinServer", localUser, roomPassword});
        screen.addChat("Joined Room IP: " + roomIp);
        names.add(localUser);
        return screen;
    }

    public static void serverListener() {
        // Now we create a thread that will listen for incoming socket connections
        new Thread(() -> {
            ServerSocketHints serverSocketHint = new ServerSocketHints();
            // 0 means no timeout.  Probably not the greatest idea in production!
            serverSocketHint.acceptTimeout = 0;

            // Create the socket server using TCP protocol and listening on 9021
            // Only one app can listen to a port at a time, keep in mind many ports are reserved
            // especially in the lower numbers ( like 21, 80, etc )
            for (int port = 49153; port < 65536; port++) {
                try {
                    serverSocket = Gdx.net.newServerSocket(Protocol.TCP, port, serverSocketHint);
                    roomPort = String.valueOf(port);
                    screen.addChat("Room Port: " + roomPort + "\n");
                    break;
                } catch (GdxRuntimeException e) {
                    continue;
                }
            }

            // Loop forever
            while(true){
                // Create a socket
                Socket socket = serverSocket.accept(null);
                clientSockets.put(socket.getRemoteAddress(), socket);

                // Read data from the socket into a BufferedReader
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                try {
                    // Read to the next newline (\n) and display that text on labelMessage
                    parseCommand(new String[] {"server", "in", input.readLine()});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // And, start the thread running
    }

    private static boolean clientListener(String[] data) {
        new Thread(() -> {
            SocketHints socketHints = new SocketHints();
            // Socket will time our in 4 seconds
            socketHints.connectTimeout = 0;
            String[] ipInfo = data[0].split(":");
            data[0] = new Networking().getLocalIp();
            String sendData = String.join("<:.:>", data);
            System.out.println(Arrays.toString(ipInfo));
            //create the socket and connect to the server entered in the text box ( x.x.x.x format ) on port
            serverConnection = Gdx.net.newClientSocket(Protocol.TCP, ipInfo[0], Integer.parseInt(ipInfo[1]), socketHints);
            try {
                serverConnection.getOutputStream().write(sendData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){
                // Read data from the socket into a BufferedReader
                BufferedReader input = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

                try {
                    // Read to the next newline (\n) and display that text on labelMessage
                    parseCommand(new String[] {"client", "in", input.readLine()});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (serverConnection.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean parseCommand(String[] command) {
        listenLog.add(new String[] {String.valueOf(java.time.LocalDateTime.now()), String.valueOf(command)});
//        String[] inputArray = input.split("<:.:>");

        if (command[0].equals("server")) {
            return (parseServer(command));
        }
        else if (command[0].equals("client")) {
            return (parseClient(command));
        }
        return false;
    }

    private static boolean parseServer(String[] command) {
        String[] commandArray = command[2].split("<:.:>");
        System.out.println(command);

        if (commandArray[1].equals("lobbyMessage")) {
            return (sendAll(commandArray[2]));
        }
        else if (commandArray[1].equals("joinServer")) {
            return (join(command));
        }
        return false;
    }

    private static boolean parseClient(String[] command) {
        return false;
    }

    private static boolean sendAll(String s) {
        return false;
    }

    private static boolean join(String[] inputArray) {
        System.out.println(Arrays.toString(inputArray));
        if (inputArray[3].equals(roomPassword)) {

        }
        return false;
    }
}
