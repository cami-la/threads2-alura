package site.camila.cursoalura.threads2.executavel;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 1235);
		System.out.println("Conexão estabelecida!");
		
//		OutputStream outputStreamCliente = socket.getOutputStream();
		
		
		Thread threadEnviaComando = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Pode enviar comandos!");
					PrintStream saida = new PrintStream(socket.getOutputStream());
					Scanner teclado = new Scanner(System.in);
//					System.out.print("Entrada: ");
					while(teclado.hasNextLine()) {
						String linha = teclado.nextLine();
						
						if(linha.trim().equals("")) break;
						
//						System.out.print("Entrada: ");
						
						saida.println(linha);
					}
					saida.close();
					teclado.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		Thread threadRecebeResposta = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Recebendo dados do servidor!");
					Scanner respostaServidor = new Scanner(socket.getInputStream());
					while(respostaServidor.hasNextLine()) {
						String linha = respostaServidor.nextLine();
						System.out.println(linha);
					}
					respostaServidor.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		threadRecebeResposta.start();
		threadEnviaComando.start();
		
		threadEnviaComando.join();
		
		System.out.println("Fechando socket do cliente.");
		socket.close();
	}
	

}
