# RemoteEXECution
The folder contains com/cn/client1.Client and com/cn/Server


execute server on terminal:
javac com/cn/Server.java
java com/cn/Server

execute multiple clients on different terminal windows: 
javac com/cn/client1.Client.java 
java com/cn/client client1 8000 user1 pass1 prog1

The client inputs client portno username passwrod and program it wants to execute, the server checks the valdiity of the data and if the data is valid then the then server sends congratulations , implying the client can execute the program

as of now I created a map of username password and program where the validity of user is checked

Current list is hardcoded in server but can be made by using a file, where username, password, and program allowed to execute are stored !

