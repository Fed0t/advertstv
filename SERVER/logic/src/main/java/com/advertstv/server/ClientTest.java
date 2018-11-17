package com.advertstv.server;

import simplenet.Client;
import simplenet.packet.Packet;
import com.advertstv.server.GuiCallback;;

public class ClientTest {
	
	private static  Client client;
	public static void connectClient() {
		 client = new Client();

		// Register a connection listener.
		client.onConnect(() -> {
		    System.out.println(client + " has connected to the server!\n");
		    
		    // Builds a packet and sends it to the server immediately.
		    Packet.builder().putByte(1).putInt(42).writeAndFlush(client);
		});

		// Register an optional pre-disconnection listener.
		client.preDisconnect(() -> System.out.println(client + " is about to disconnect from the server!"));

		// Register an optional post-disconnection listener.
		client.postDisconnect(() -> System.out.println(client + " successfully disconnected from the server!"));

		// Attempt to connect to a server AFTER registering listeners.
		client.connect("localhost", 43594);
	}
	
	public static void disconnect(GuiCallback callback) {
		client.close();
		callback.callback("Client disconnected");
	}
}
