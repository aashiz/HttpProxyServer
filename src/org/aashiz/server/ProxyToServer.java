package org.aashiz.server;

import java.io.IOException;
import java.net.Socket;

import org.aashiz.server.HttpParser.METHOD;

public class ProxyToServer {
HttpParser doc  ;
ClientToProxy ss ;
	public ProxyToServer(HttpParser parser,ClientToProxy client){
		this.doc = parser ;
		this.ss =client;
	}
	
	public void connect(){
		if(doc.getMethod() ==METHOD.CONNECT){
			String host = doc.getHost() ;
			notifyOfConnect();
		}
		
	}

	private void notifyOfConnect() {
		// TODO Auto-generated method stub
		try {
			ss.sendData("HTTP/1.1 CONNECT OK\r\n\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
