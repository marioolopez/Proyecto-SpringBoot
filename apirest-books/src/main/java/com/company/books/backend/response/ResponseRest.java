package com.company.books.backend.response;
import java.util.ArrayList;
import java.util.HashMap;

//ES UNA CLASE QUE CONTROLA EL ESTADO DE LA RESPUESTA (SI A SALIDO TODO OK O NO)

public class ResponseRest{ 

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;
	}

	public void setMetada(String tipo,String codigo, String date){ 
		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("tipo", tipo);
		mapa.put("codigo", codigo);
		mapa.put("date", date);
		
		metadata.add(mapa);
	}
	
}