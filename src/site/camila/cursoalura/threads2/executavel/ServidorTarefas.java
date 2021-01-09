package site.camila.cursoalura.threads2.executavel;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import site.camila.cursoalura.threads2.classes.DistribuirTarefas;

public class ServidorTarefas {
	public static void main(String[] args) throws Exception {
		System.out.println("--- Iniciando o Servidor ---");
		ServerSocket servidor = new ServerSocket(1235);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Aceitando novo cliente na porta " + socket.getPort());
			
			DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);
			threadPool.execute(distribuirTarefas);
		}
	}
}
