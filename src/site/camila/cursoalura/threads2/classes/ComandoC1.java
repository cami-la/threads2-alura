package site.camila.cursoalura.threads2.classes;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	private PrintStream saida;

	public ComandoC1(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public void run() {
		System.out.println("Executando comando C1 ");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		saida.println("Comando C1 executado com sucesso!");
	}

}
