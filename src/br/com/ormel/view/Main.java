package br.com.ormel.view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.ormel.utils.ParameterStringBuilder;

public class Main {

	public static void main(String[] args) {
		HttpURLConnection conexao = null;
		URL url = null;
		
		try {
			
			// REQUEST
			
//			url = new URL("http://localhost:8080/SimpleWebPage");
			url = new URL("http://localhost:8080/SimpleWebPage");
			conexao = (HttpURLConnection) url.openConnection();
//			ServerSocket socketInit = new ServerSocket(1337);
//      Socket socket = welcomeSocket.accept();
			
			//Tipo de request
			conexao.setRequestMethod("GET");
			
			//Habilita recebimento de parametros
			conexao.setDoOutput(true);
			
			//Classe responsavel em enviar o request
			DataOutputStream out = new DataOutputStream(conexao.getOutputStream());
//			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			//Adding Request Parameters
			Map<String, String> parameters = new HashMap<>();
			parameters.put("valorA", "3");
			parameters.put("valorB", "5");
			
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();			
			
			
			// RESPONSE
			
			//Codigo do response //200-OK
			int status = conexao.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
//			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			
			in.close();

			System.out.println("COD.RESP: " + status);
			System.out.println("HTML: " + content.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			// url = new URL("http://localhost:8080/SimpleWebPage");
			e.printStackTrace();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			// con.setRequestMethod("GET");
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// con = (HttpURLConnection) url.openConnection();
			e.printStackTrace();
		} finally {
			conexao.disconnect();
		}
	  
	  // POST
//	  con = (HttpURLConnection) url.openConnection();
//	  con.setRequestMethod("POST");
		
		//Setting Request Headers
//	  con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		Or
//		con.setRequestProperty("Content-Type", "application/json");
		//read the value of a header from a connection
		//String contentType = con.getHeaderField("Content-Type");
		
//	  con.setUseCaches (false);
//	  con.setDoInput(true);
//	  con.setDoOutput(true);
	}
	
}
