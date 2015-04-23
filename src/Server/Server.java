package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
public static void main(String[] args) throws IOException
{
	int port = 8899;
	
	ServerSocket server = new ServerSocket(port);
	
	Socket socket = server.accept();
	
	Reader reader = new InputStreamReader(socket.getInputStream());
	
	char[] cha = new char[256];
	
	int len;
	
	StringBuilder sb = new StringBuilder();
	String temp;
	int index;
	while((len = reader.read(cha))!=-1)
	{
		temp = new String(cha,0,len);
		if((index = temp.indexOf("eof"))!=-1)
		{
			sb.append(temp.substring(0, index));
			break;
		}
		sb.append(temp);
	}
	System.out.println("from client" + sb);
	
	Writer writer = new OutputStreamWriter(socket.getOutputStream());
	writer.write("Hello, I'm Server");
	writer.flush();
	writer.close();
	reader.close();
	socket.close();
	server.close();
	
	
}
	

}
