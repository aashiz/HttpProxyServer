package org.util;

public class Utilities {

	public static void printn(Object o){
		System.out.println(o);
	}
	
	public static void print(Object o){
		System.out.print(o);
	}
	
	public static String ToString(byte[] arr){
		StringBuilder str= new StringBuilder();
		for(byte b: arr){
			str.append((char)b);
		}
		return str.toString();
	}
}
