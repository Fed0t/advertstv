package com.advertstv.client;

import simplenet.Client;
import simplenet.packet.Packet;

public class ClientTest {
	
	private static  Client client;
	public static void connectClient() {
		client = new Client();

		// Register a connection listener.
		client.onConnect(() -> {
		    System.out.println(client + " has connected to the client!\n");
		    // Builds a packet and sends it to the client immediately.
		    Packet.builder().putByte(1).putInt(42).writeAndFlush(client);
		});

		// Register an optional pre-disconnection listener.
		client.preDisconnect(() -> System.out.println(client + " is about to disconnect from the client!"));

		// Register an optional post-disconnection listener.
		client.postDisconnect(() -> System.out.println(client + " successfully disconnected from the client!"));

		// Attempt to connect to a client AFTER registering listeners.
		client.connect("localhost", 43594);
	}
	
	public static void disconnect() {
		client.close();
	}
}
