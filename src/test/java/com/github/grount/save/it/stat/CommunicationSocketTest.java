package com.github.grount.save.it.stat;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class CommunicationSocketTest {
    private static final int PORT_NUMBER = 5000;
    private static final String HOST_NAME = "127.0.0.1";

//    @Test
//    void initialize_shouldCommunicateToTheServer() throws IOException {
//        try (
//                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
//                Socket clientSocket = serverSocket.accept();
//                PrintWriter out =
//                        new PrintWriter(clientSocket.getOutputStream(), true);
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(clientSocket.getInputStream()))
//        ) {
//            CommunicationSocket communicationSocket = new CommunicationSocket();
//            communicationSocket.initialize();
//            String input = in.readLine();
//        }
//    }
}