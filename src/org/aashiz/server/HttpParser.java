package org.aashiz.server;
import static org.util.Utilities.* ;
public class HttpParser {
String headers ;
byte[] postdata ;
enum METHOD {
	POST,GET,CONNECT,OTHERS
}
String[] lines ;
	public HttpParser(String toparse){
		this.headers = toparse;
		Tokenize();
	}
	
	private void Tokenize() {
		// TODO Auto-generated method stub
		lines = this.headers.split("\r\n");
		printn(lines.length);
	}

	public boolean isPost() {
		if(lines.length != 0){
			return lines[0].contains("POST");
		}
	
	return false;
	}
	
	public METHOD getMethod(){
		if(lines.length != 0){
			String ss = lines[0].split("\\s")[0];
			if(ss.equalsIgnoreCase("POST")){
				return METHOD.POST;
			}else if (ss.equals("GET")){
				return METHOD.GET ;
			}else if( ss.equals("CONNECT")){
				return METHOD.CONNECT ;
			}
		}
		
		return METHOD.OTHERS;
	}
	
	public void addPostData(byte[] buff){
		this.postdata = buff;
	}
	
	
	public void getContentLength(){
		
	}

	public String getHost() {
		// TODO Auto-generated method stub
		String host = null ;
		if(this.getMethod() == METHOD.CONNECT){
			host = lines[0].split("\\s")[1].split(":")[0] ; 
		}
		return host;
	}
}
