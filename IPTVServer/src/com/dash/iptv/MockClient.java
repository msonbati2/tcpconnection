package com.dash.iptv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MockClient {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9999;

	public MockClient() throws UnknownHostException, IOException {
		Socket socket = new Socket(SERVER_IP,SERVER_PORT);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);// auto flush is true
		
		
		while(true) {
			String command  = keyboard.readLine();
			if(command.equals("exit"))break;
			out.println(command);
			String serverResponse = input.readLine();
			System.out.println(serverResponse);
		}
	
	}
	public static void main(String[] args) {
		try {
			new MockClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
