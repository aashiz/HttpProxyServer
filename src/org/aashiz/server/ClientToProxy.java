package org.aashiz.server;

import static org.util.Utilities.printn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.aashiz.server.HttpParser.METHOD;

public class ClientToProxy {

	Socket customer;
	public ClientToProxy(Socket Clients){
	this.customer = Clients;	
	}
	
	
	public void receiveData() throws Exception {
		
		InputStream in = customer.getInputStream();
		byte[] buff;
		StringBuilder str = new StringBuilder();
		int i ;
		boolean NewLineEncountered  = false ;
		int count = 0;
		boolean Continue = true;
		while((i =  in.read() )!= -1 ){
			switch (i){
			case 13:
				NewLineEncountered = true ;
				break;
			case 10:
				if( NewLineEncountered){
					count += 1 ;
				}
				if(count == 2 ){
					Continue = false;
				}
				break;
			default:
				NewLineEncountered = false;
				count = 0;
				}
			str.append((char)i);
			if(!Continue){
				HttpParser parser = new HttpParser(str.toString());
				if(parser.getMethod() == METHOD.POST){
					printn("IS POST");
				}
				printn(str.toString());
				ProxyToServer s= new ProxyToServer(parser, this);
				s.connect();
			}
		}
		System.out.println(str.toString());
	}


	public void sendData(String string) throws IOException {
		// TODO Auto-generated method stub
		OutputStream out = this.customer.getOutputStream();
		out.write(string.getBytes());
		out.flush();
	}


	
}
