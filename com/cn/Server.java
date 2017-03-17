package com.cn;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.cn.*;
import java.util.*;

public class Server {

	private static final int sPort = 8000;   //The server will be listening on this port number
	private static final ArrayList<Integer> clientNumbers=new ArrayList<Integer>();
	private static final ArrayList<ObjectOutputStream> clientStreams = new ArrayList<ObjectOutputStream>();
	//private static final String fileRoot="D:\\Suyog_Java\\SP\\src\\com\\cn\\";
	static HashMap<String,String> map=new HashMap<>();
	public static void main(String[] args) throws Exception {
		System.out.println("The server is running."); 			
        	ServerSocket listener = new ServerSocket(sPort);
		map.put("user1","pass1,prog1");
		map.put("user2","pass2,prog2");
		map.put("user3","pass3,prog1");
		int clientNum = 1;
        	try {
            		while(true) {
            			clientNumbers.add(clientNum);
                		new Handler(listener.accept(),clientNum).start();
				System.out.println("Client "  + clientNum + " is connected!");
				clientNum++;
            			}
        	} finally {
            		listener.close();
        	} 
 
    	}

	/**
     	* A handler thread class.  Handlers are spawned from the listening
     	* loop and are responsible for dealing with a single client's requests.
     	*/
    	private static class Handler extends Thread {
        	private String message;    //message received from the client
        	private Socket connection;
        	private ObjectInputStream in;	//stream read from the socket
        	private ObjectOutputStream out;    //stream write to the socket
        	private int no;		//The index number of the client
      //  	private MessageType messageType;
        	public Handler(Socket connection, int no) {
            		this.connection = connection;
            		this.no = no;
            		
        	}

        public void run() {
 		try{
			//initialize Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			clientStreams.add(out);
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			TimeUnit.SECONDS.sleep(2);
			while(true){
				try{
					String message = (String)in.readObject();
					String[] arr=message.split(",");
					if(map.containsKey(arr[0])){
						String passprog=map.get(arr[0]);
						String ans[] = passprog.split(",");
						if(ans[0].equals(arr[1]) && ans[1].equals(arr[2])){
								//System.out.println("valid user");
								sendMessage(out,"Congratulations");
						}
						else{
						    sendMessage(out,"No Access");					
						}
					}
					else{
						sendMessage(out,"Not Valid Username");
					}
				}catch(Exception e){
					
				}
			}
			
		}
		catch(IOException | InterruptedException ioException){
			System.out.println("Disconnect with Client " + no);
		}
		finally{
			//Close connections
			try{
				in.close();
				out.close();
				connection.close();
			}
			catch(IOException ioException){
				System.out.println("Closing connection Disconnect with Client " + no);
			}
		}
	}

	
	

		private void sendMessage(ObjectOutputStream out, String message2){
			try{
				
				out.writeObject(message2);
				out.flush();
			}catch(Exception e){
				
			}
		}
		
	
		
    }
    	
    	//send a message to the output stream
    	public void sendMessage(ObjectOutputStream out,ArrayList<ObjectOutputStream> clientStreams,String msg)
    	{
    		try{
    			out.writeObject(msg);
    			out.flush();
    			
    		}
    		catch(IOException ioException){
    			ioException.printStackTrace();
    			}
    	}
    	

}