package org.aashiz.server;

import static org.util.Utilities.printn;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReceiver {
	Socket ReceiveFrom;
	HttpParser parse; 
	public ClientReceiver(Socket tekcos){
		this.ReceiveFrom = tekcos ;
	}
	
	public void receive() throws IOException{
		InputStream in = ReceiveFrom.getInputStream() ;
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
				if(parser.isPost()){
					printn("IS POST");
				}
				printn(str.toString());
			}
		}
		System.out.println(str.toString());

		
	}
	
}
