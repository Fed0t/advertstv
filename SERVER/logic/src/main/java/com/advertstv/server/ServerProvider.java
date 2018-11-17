package com.advertstv.server;

import simplenet.Server;
import com.advertstv.server.GuiCallback;

public class ServerProvider {
	private static Server server;
	public static void connect(GuiCallback callback) {
		// Instantiate a new Server.
		server = new Server();
		// Register one connection listener.
		
		server.onConnect(client -> {
			  callback.callback(client + " has connected!");
			    /*
			     * When one byte arrives from the client, switch on it.
			     * If the byte equals 1, then print an int when it arrives.
			     *
			     * Because `readByteAlways` is used, this will loop when
			     * the callback completes, but does not hang the main thread.
			     */
			    client.readByteAlways(opcode -> {
			        switch (opcode) {
			            case 1:
			                client.readInt(System.out::println);
			                callback.callback("Code 42 received from client "+client);
			        }
			    });

			    client.preDisconnect(() -> callback.callback(client + " is about to disconnect!"));
			    client.postDisconnect(() -> callback.callback(client +  " disconnected!"));
		});


		server.bind("localhost", 43594);
		callback.callback("Successfully bound to localhost:43594!");
	}
	
	public static void disconnect(GuiCallback callback) {
		server.close();
//		callback.callback("Server safe closed!");
	}
}
