package site.camila.cursoalura.threads2.classes;

import java.net.Socket;

public class DistribuirTarefas implements Runnable {

	private Socket socket;

	public DistribuirTarefas(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefas para " + this.socket);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
