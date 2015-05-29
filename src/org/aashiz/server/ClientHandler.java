package org.aashiz.server;

import java.io.InputStream;
import java.net.Socket;

import static org.util.Utilities.*;

public class ClientHandler implements Runnable {

	Socket Client ;
	
	
	public ClientHandler(Socket clients){
		Client = clients;
		printn("A client has been connected== " + Client.getInetAddress() + "::"+ Client.getPort());
	}
	
	@Override
	
	
	public void run() {
		// TODO Auto-generated method stub
		/*
		StringBuilder str = new StringBuilder();
		try {
			//ConnectionManager cm = new ConnectionManager();
			//cm.start();
			InputStream reader = Client.getInputStream();
			
			int i ;
			int j = 0 ;
			while( (i = reader.read() )!= -1){
				switch(i) {
				case 13:
					j = 13 ;
				case 10:
					if( j == 13){
						str.append((char)i);
						//cm.lock = str.toString();
						//cm.lock.notify();
						//cm.bott();
						break;
					}
				default:
					str.append((char)i);
				}
				print( i + ",") ;
				
				//str.append((char)i);
				
			}
			System.out.println(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			receiveData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void receiveData() throws Exception {
	
	InputStream in = Client.getInputStream();
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
