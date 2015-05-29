package org.aashiz.main;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.aashiz.server.ServerMain;

public class MyMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				ServerMain server=  new ServerMain(5555);
				server.startNow();
		
		
		int i = 0;
		while( ( i = System.in.read()) != -1){
			System.out.println(i);
		}
	}

}
