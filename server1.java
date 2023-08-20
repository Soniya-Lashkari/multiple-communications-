
package groupchat;
import java .net.*;
import java.io.*;
import java.util.*;

public class server1 implements Runnable {
    
    Socket socket;
    public static Vector  client = new Vector();
    
    public server1(Socket socket){
    try {
        
    this.socket=socket;}catch (Exception e){
    e.printStackTrace();
    }
    }
    
    public void run() //overwrite run method 
    { 
           /*client send msg it goes to server and server read and write that
            so bufferread and bufferwrite used}*/
    
    try {
    BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    
    client.add(writer);
    while(true){
    String data = reader.readLine();
    System.out.println("received"+data);
    for(int i=0 ; i< client.size(); i++){
    try {
    BufferedWriter bw = ( BufferedWriter )client.get(i);
    bw.write(data);
    bw.write("\r\n");
    bw.flush();
    }
    catch(Exception e){e.printStackTrace();
    }
        
    }
    }
    
    }catch(Exception e){
     e.printStackTrace();  
    
    }
  
   }
    
    
    public static void main(String[]args) throws Exception {
    ServerSocket s = new ServerSocket (6006                                                                                                                        );
    while (true){
    
    Socket socket = s.accept();
  
    server1 server=new server1(socket);
    Thread thread =new  Thread(server);// thread making  server is class object
    thread .start(); // start run method in thread
    }
    }
    
}
