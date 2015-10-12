package learning;
import learning.Klass;
import learning.Important;
import learning.Unimportant;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Notification{
	// this should probably have a superclass other than Object but idk what
 	// tokenHash is a map from a lower cased word to the number of instances of that word in a notification
  private	HashMap<String, Integer> tokenHash = new HashMap<String, Integer>();
  // this is map with a notification klass type and all instance of Notification of that klass type
	public static HashMap<String, Notification[]> instances;

	protected static Set<String> vocabulary = new HashSet<String>();

	protected HashMap<String, Boolean> tokens = new HashMap<String, Boolean>();
	// the title of the notification
	private String title; 
	// no idea what this is.
	private String app;
	// important, not important, etc.
	protected Klass klass;
	// want this to belong to a user properly.
	private String userName;    

	protected Important imp;
	// will want to change String path to content and update logic accordingly
	// FiltrateNotification.NotificationText is going to be the variable type eventually
	public Notification(String title, String path, String app){
		title = title;
		app = app;
		String content = readFile(path);
		tokens = parseContent(content);
		updateVocabulary();
		populateTokenHash();	
	}
   
  // pretend the 2nd arg (content) is a file for now
	public static void main(String[] args){
		Notification docA = new Notification("aa", "/home/tommy/Desktop/bayes/main/sample_docs/ex1.txt", "bar");
		Notification docB = new Notification("ba", "/home/tommy/Desktop/bayes/main/sample_docs/ex2.txt", "bar");
		Notification docC = new Notification("ca", "/home/tommy/Desktop/bayes/main/sample_docs/ex3.txt", "bar");
		Notification docD = new Notification("da", "/home/tommy/Desktop/bayes/main/sample_docs/ex4.txt", "bar");
		Notification docE = new Notification("ea", "/home/tommy/Desktop/bayes/main/sample_docs/ex5.txt", "bar");
		Important i = new Important();
		docA.markAsImportant(i);
		System.out.println(docA.klass);
	}

	// I am using files to simulate the text that will be passed in from the 
	// application
	private static void getPath(){
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}

	// look into throwing
	private String readFile(String path){
		String content = null;
		String line;
		try{
			FileReader notificationReader = new FileReader(path);

			BufferedReader notificationBuffer = new BufferedReader(notificationReader);
			while ((line = notificationBuffer.readLine()) != null){
				content += line + " ";
			}
			notificationBuffer.close();
			return content;
		}
		catch(FileNotFoundException fnf){
			System.out.println("Shoot " + path + " can't be found");
			return "ERROR";
		}
		catch(IOException ioe){
			System.out.println("Shoot there was an ioexception " + path);
			return "ERROR";
		}
	}

	// this seems terrible
	private HashMap<String, Boolean> parseContent(String content){
		content = snipString(content);
		String[] tokensArray = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+|-");
		for (String token : tokensArray){
			tokens.put(token, true);
		}
		return tokens;
	}
	// this is to be removed once no longer dealing with files
	private String snipString(String content){
		if (content.startsWith("null")){
			content = content.substring(4);
		}
		return content;
	}

	private void updateVocabulary(){
		for (String token : tokens.keySet()){
			Notification.vocabulary.add(token);
		}
	}

	private void populateTokenHash(){
		for (String token : tokens.keySet()){
			if (tokenHash.containsKey(token)){
				int previousVal = tokenHash.get(token);
				tokenHash.put(token, previousVal+1);
			} else {
				tokenHash.put(token, 1);
			}
		}
	}

	// would like to be able to pass in a klass type to a setKlass method
	// and set it to that: 
	
	// protected <Klass> void setKlass(Klass k){
	// 	this.klass = k;
	// }

	protected void markAsImportant(Important i){
		this.klass = i;
	}

	protected void markAsUnimportant(Unimportant u){
		this.klass = u;
	}

}
