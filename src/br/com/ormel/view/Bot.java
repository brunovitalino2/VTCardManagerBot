package br.com.ormel.view;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.ormel.utils.ParameterStringBuilder;

public class Bot {

	public static void main(String[] args) {
		String host = "http://localhost:8080/SimpleWebPage";
		Map<String, String> parametros = new HashMap<>();
		
	    try {
	        System.out.println("Enviando request HTTP\n");
	        
			URL url = new URL(host);
			HttpURLConnection socket = (HttpURLConnection) url.openConnection();
			
			//Tipo de request
			socket.setRequestMethod("GET");
			
			//Habilita recebimento de parametros
			socket.setDoOutput(true);
			
//			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//			BufferedOutputStream saida = new BufferedOutputStream(dos);
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
			
	        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        parametros.put("valorA", "3");
	        parametros.put("valorB", "5");
	        
//	        dos.writeBytes(ParameterStringBuilder.getParamsString(parametros));
	        saida.writeBytes(ParameterStringBuilder.getParamsString(parametros));
	        
	        saida.flush();
	
	        System.out.println("Aguardando response....\n");	

			int status = socket.getResponseCode();
	        String entradaLinha;
			StringBuffer conteudo = new StringBuffer();
			
			while ((entradaLinha = entrada.readLine()) != null) {
			    conteudo.append(entradaLinha);
			}

			System.out.println("COD.RESP: " + status);
			System.out.println("HTML: " + conteudo.toString());	
			
			entrada.close();
	        socket.disconnect();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	}
}
