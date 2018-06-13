package br.com.ormel.view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
	
	public static String sendTCP(String host, String content) {
		
	    try {
	        System.out.println("Mensageiro de pacotes enviando o pacote TCP: " + content);
	        String serverResponse;
	        Socket socket = new Socket(host, 1337);
	        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
	        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        saida.writeBytes(content + '\n');
	
	        System.out.println("Enviando pacote pro servidor. Esperando resposta.");
	
	        serverResponse = entrada.readLine();
	        socket.close();
	
	        System.out.println("Resposta do servidor recebida. Resultado " + serverResponse + " foi retornado ao requisitante.");
	
	        return serverResponse;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	    return "";
	}
}
