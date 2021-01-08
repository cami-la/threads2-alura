package site.camila.cursoalura.threads2.executavel;

import java.net.ServerSocket;
import java.net.Socket;

import site.camila.cursoalura.threads2.classes.DistribuirTarefas;

public class ServidorTarefas {
	public static void main(String[] args) throws Exception {
		System.out.println("--- Iniciando o Servidor ---");
		ServerSocket servidor = new ServerSocket(1235);
		
		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());
			
			DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);
			Thread threadCliente = new Thread(distribuirTarefas);
			
			threadCliente.start();
			
			Thread.sleep(2000);
		}
	}
}
