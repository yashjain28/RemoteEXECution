# RemoteEXECution
The folder contains com/cn/client1.Client and com/cn/Server


execute server on terminal:
javac com/cn/Server.java
java com/cn/Server

execute multiple clients on different terminal windows: 
javac com/cn/client1.Client.java 
java com/cn/client client1 8000 user1 pass1 prog1

The client inputs client portno username passwrod and program it wants to execute, the server checks the validity of the data and if the data is valid then the then server sends congratulations , implying the client can execute the program

As of now I created a map of username password and program where the validity of user is checked, read from file can be implemented but I just did it using a HashMap in the server code because initially I was testing my code, then due to lack of time I didn't change it.  

Rest I have added comments in my client and server code to make it more readable. 
I have established normal socket connections. And created send and receive threads.

At the server, I have created new thread for every client. Every client has its own sendmessage thread and receive message thread. 

I am creating new threads again and again, which is not a good practice because every new thread created requires resources which needs to instantiated and thus making it a costly task, resuing the same thread is more good at the server. New to thread programming so, tried my best to implement as much I could in the given time.  
