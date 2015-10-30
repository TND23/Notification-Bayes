package learning;
import learning.NaiveBayesClassification;
import learning.Important;
import learning.Unimportant;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

public class Notification{
	
 	// Map from a lower cased word to the number of occurrences of that word in a Notification instance.
	private HashMap<String, Integer> tokenHash = new HashMap<String, Integer>();
	
	// Map a notification type to all instances of Notification of that type.
	public static HashMap<String, Notification[]> instances;

	protected static Set<String> vocabulary = new HashSet<String>();

	protected HashMap<String, Boolean> tokens = new HashMap<String, Boolean>();
	private String title; 
	private String app;
	protected NaiveBayesClassification naiveBayesClassification;
	private String userName;    

	protected Important imp;
	// Will want to change String path to content.
	// FiltrateNotification.NotificationText is going to be the variable type eventually
	public Notification(String title, String path, String app){
		this.title = title;
		this.app = app;
		String content = readFile(path);
		tokens = parseContent(content);
		updateVocabulary();
		populateTokenHash();	
	}

	// Simulate FiltrateNotification.NotificationText
	@SuppressWarnings("unused")
	private static void getPath(){
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}

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

	// Make all words lower case, concatenating together any words separated by one or more spaces or a dash.
	private HashMap<String, Boolean> parseContent(String content){
		content = snipString(content);
		String[] tokensArray = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+|-");
		for (String token : tokensArray){
			tokens.put(token, true);
		}
		return tokens;
	}
	// Remove when no longer dealing with files.
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
	
	public static Set<String> getVocabulary(){
		return vocabulary;
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

	protected void markAsImportant(Important i){
		this.naiveBayesClassification  = i;
	}

	protected void markAsUnimportant(Unimportant u){
		this.naiveBayesClassification = u;
	}
}
