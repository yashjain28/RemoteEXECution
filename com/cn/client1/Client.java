package com.cn.client1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import com.cn.*;

public class Client {
	Socket requestSocket;           //socket connect to the server
	ObjectOutputStream out;         //stream write to the socket
 	ObjectInputStream in;          //stream read from the socket
	String message;                //message send to the server
	String MESSAGE;                //capitalized message read from the server
	BufferedReader br;
	String clientName;
	int portNo;
	String username,password,data;
	public Client(String clientName, int portNo) {
		this.clientName = clientName;
		this.portNo = portNo;
	}

	
	void run()
	{
		try{
			//create a socket to connect to the server
			requestSocket = new Socket("localhost", portNo);
			System.out.println("Connected to localhost in port " + portNo);
			//initialize inputStream and outputStream
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			//get Input from standard input
			br = new BufferedReader(new InputStreamReader(System.in));
			
			final Thread sendThread = new Thread(new Runnable(){

				@Override
				public void run() {
						while(true)
						{
							sendThreadMethod();
						}	
					
				}
				
			});
			sendThread.start();
			final Thread receiveThread = new Thread(new Runnable(){

				@Override
				public void run() {
				
						while(true){
							receiveThreadMethod();
							break;
						}
					
				}
				
			});
			receiveThread.start();
			
			TimeUnit.MINUTES.sleep(20);
			
		}
		catch (ConnectException e) {
    			System.err.println("Connection refused. You need to initiate a server first.");
		} 
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		
		catch(IOException ioException){
			ioException.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//Close connections
			try{
				
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	//send a message to the output stream
	
	private void sendThreadMethod(){
		try{
			
			//Send the sentence to the server
			out.writeObject(message);
			out.flush();
		}
		catch(Exception e){
			
		}
	}
	
	private void receiveThreadMethod(){
		try{
			Object o = in.readObject();
			if(o instanceof String)
			{
				String msg = (String)o;
				 System.out.println("Message from server: "+msg);
			}
			else{
				 System.out.println("Message from server: "+"Not instanceof string");	
			}
		}
		catch(Exception e){

		}
	}

	//main method
	public static void main(String args[])
	{
		Client client = new Client(args[0],Integer.parseInt(args[1]));
		client.message=args[2]+","+args[3]+","+args[4]; //taking user input from client
		client.run();
	}

}
