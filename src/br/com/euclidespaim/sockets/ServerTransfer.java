package br.com.euclidespaim.sockets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTransfer {
	public static void main(String[] args) {

		// Criando servidor
		ServerTransfer server = new ServerTransfer();

		// Aguardar conexao de cliente para transferencia
		server.waitForClient();
		
	}

	public void waitForClient() {
		// Checa se a transferencia foi completada com sucesso
		OutputStream socketOut = null;
		ServerSocket servsock = null;
		FileInputStream fileIn = null;

		try {
			// Abrindo porta para conexao de clientes
			servsock = new ServerSocket(13267);
			System.out.println("Porta de conexao aberta 13267");

			// Cliente conectado
			Socket sock = servsock.accept();
			System.out.println("Conexao recebida pelo cliente");

			// Criando tamanho de leitura
			byte[] cbuffer = new byte[1024];
			int bytesRead;

			// Criando arquivo que sera transferido pelo servidor
			File file = new File("/home/ec2-user/up/teste.zip");
			fileIn = new FileInputStream(file);
			System.out.println("Lendo arquivo...");
			
			// Criando canal de transferencia
			socketOut = sock.getOutputStream();

			// Lendo arquivo criado e enviado para o canal de transferencia
			System.out.println("Enviando Arquivo...");
			while ((bytesRead = fileIn.read(cbuffer)) != -1) {
				socketOut.write(cbuffer, 0, bytesRead);
				socketOut.flush();
			}

			System.out.println("Arquivo Enviado!");
		} catch (Exception e) {
			// Mostra erro no console
			e.printStackTrace();
		} finally {
			if (socketOut != null) {
				try {
					socketOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (servsock != null) {
				try {
					servsock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
