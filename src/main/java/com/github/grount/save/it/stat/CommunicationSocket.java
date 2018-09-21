package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class CommunicationSocket {
    private static final int PORT_NUMBER = 5000;
    private static final String HOST_NAME = "127.0.0.1";
    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);

    void initialize() {
        try (
                Socket echoSocket = new Socket(HOST_NAME, PORT_NUMBER);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            out.println("content changed");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to complete socket communication: {0}", e.getMessage());
        }
    }

    public boolean sendData(JSONObject loadedKinds) {
        try (
                Socket echoSocket = new Socket(HOST_NAME, PORT_NUMBER);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            out.println(loadedKinds.toString());
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to complete socket communication: {0}", e.getMessage());
        }
        return false;
    }
}
