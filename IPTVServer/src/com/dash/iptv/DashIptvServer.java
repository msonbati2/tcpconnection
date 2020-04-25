package com.dash.iptv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Server class opens socket for any IPTV client to send and receive data.
 */
public class DashIptvServer {
	public static final int PORT = 9999;
	public DashIptvServer() throws IOException {
		System.out.println("Server is waiting for IPTV to connect");
		
		ServerSocket listener = new ServerSocket(PORT);	
		
		Socket client = listener.accept();
		
		System.out.println("Server is connected to IPTV");
		
		PrintWriter out = new PrintWriter(client.getOutputStream(),true);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		try {
			while(true) {
				String token = in.readLine();
				
				out.println("server: "+token);
				System.out.println(token);
				if(token.equals("exit"))break;
				
			}
		}catch(SocketException e) {
			System.out.println(e.getMessage());
				
		}
			
		finally {
			out.close();
			in.close();
		}
		
		
	}
	
	public static void main(String[] args) {
		try {
			new  DashIptvServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
