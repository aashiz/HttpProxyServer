package org.aashiz.server;


import static org.util.Utilities.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ServerMain {

	
	int PORT = 5555;
	String HOST ;
	ExecutorService ThreadPool = Executors.newFixedThreadPool(500);
	boolean Continue = true ;
	public ServerMain(int PORT){
		this.PORT = PORT;
	}
	
	
	public void startNow() throws IOException {
		ServerSocket socket = new ServerSocket(PORT);
		printn("The server has been started!!! " + socket.getInetAddress() + " ::" + socket.getLocalPort());
		
		while(Continue){
			Socket clientsocket = socket.accept();
			ClientHandler hand =  new ClientHandler(clientsocket);
			ThreadPool.execute(hand);
			}
		
		
		try {
			socket.close();
		}catch(Exception ex){
			
		}
	}
}
