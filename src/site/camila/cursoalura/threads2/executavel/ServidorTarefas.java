package site.camila.cursoalura.threads2.executavel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import site.camila.cursoalura.threads2.classes.DistribuirTarefas;

public class ServidorTarefas {
	//atributos
	private ServerSocket servidor;
	private ExecutorService threadPool;
	private AtomicBoolean estaRodando; //volatile boolean

	public ServidorTarefas() throws IOException {
		System.out.println("--- Iniciando o Servidor ---");
		this.servidor = new ServerSocket(1235);
		this.threadPool = Executors.newFixedThreadPool(4);    //newCachedThreadPool();
		this.estaRodando = new AtomicBoolean(true);
	}

	public void rodar() throws IOException {
		while(this.estaRodando.get()) {
			try {
				Socket socket = servidor.accept();
				System.out.println("Aceitando novo cliente na porta " + socket.getPort());
				
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, socket, this);
				threadPool.execute(distribuirTarefas);
			} catch (SocketException e) {
				System.out.println("SocketException, está rodando? " + estaRodando);
			}
		}
	}

	public void parar() throws IOException {
		estaRodando.set(false);;
		servidor.close();
		threadPool.shutdown();
	}


	public static void main(String[] args) throws Exception {
		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();
		servidor.parar();
	}
}
