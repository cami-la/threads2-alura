package site.camila.cursoalura.threads2.executavel;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 12345);
		System.out.println("Conexão estabelecida!");
		socket.close();

	}
	

}
