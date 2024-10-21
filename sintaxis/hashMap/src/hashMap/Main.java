package hashMap;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<String, String> historial = new HashMap<String, String>();
		
		historial.put("Google", "http://www.google.com");
		historial.put("San Luis", "http://www.centrosanluis.com");
		
		System.out.println(historial.get("Google"));
	}
}
