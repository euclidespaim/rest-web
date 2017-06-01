package com.euclidespaim.hackathon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VanHack {

	public static void main(String[] args) {
		JSONObject jsonObject;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();
		
		//Variaveis que irao armazenar os dados do arquivo JSON
		String id;
		String title;
		String total_votes;
		String intro;
		String body;
		String tasks;

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader("C:/Users/Kid/workspace/nodeJS/vanhack.json"));
			
			//Salva nas variaveis os dados retirados do arquivo
			id = (String) jsonObject.get("id");
			title = (String) jsonObject.get("title");
			total_votes = (String) jsonObject.get("total_votes");
			intro = (String) jsonObject.get("intro");

			System.out.printf(
					"Data: %s\ntitle: %s\nid: %s\nintro: %s\n",
					id, title, intro, total_votes);
		} 
		//Trata as exceptions que podem ser lançadas no decorrer do processo
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}