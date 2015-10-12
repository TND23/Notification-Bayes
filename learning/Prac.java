package learning;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Prac{

	// public static HashMap<String, Integer> myMap = new HashMap<String, Integer>();
	public static String a = "hello DELIM this is a DELIM thing that is DELIM for practice";
	public static void main(String args[]){
		String[] str = a.split("DELIM", 2);
		for (String s : str){
			System.out.println(s);			
		}

	}

	// public static void addToIgnoreList(String[] args){
	// 	for (String token : args){
	// 		if (!ignoreList.get(token)){
	// 			ignoreList.put(token, true);
	// 		}
	// 	}
	// }

	// public void iterateThruList(String[] args){
	// 	for (String el : args){
	// 		if (ignoreList.get(el)) continue;
	// 		System.out.println(el);
	// 	}
	// }

	// private String longestSubstring(String a, String b){
	// 	for (int i = 0 ; i < a.length; i++){
	// 		char aChar = a.charAt(i);
	// 	}
	// }

	// compare this users spam preferences with other users

	// spam-icity of a word
	//

	// combine all the different spam documents for a given user
	private void amalgamateSpam(){

	}


	private void preferences(){

	}

	public HashMap<String, Boolean> ignoreList;
}
