# RemoteEXECution
The folder contains com/cn/client1.Client and com/cn/Server
execute server:
javac com/cn/Server.java
java com/cn/Server

execute multiple clients on different terminal windows: 
javac com/cn/client1.Client.java 
java com/cn/client client1 8000 user1 pass1 prog1

if the client is valid and has valid access then he gets congratulations if he's allowed to execute that program
as of now I created a map of username password and program where the validity of user is checked

Current list is hardcoded in server but can be made by using a file, where username, password, and program allowed to execute are stored !
