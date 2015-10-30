package learning;
import java.util.Vector;
import java.util.HashMap;
import learning.Notification;
import java.util.ArrayList;

public abstract class NaiveBayesClassification{

	public static Vector<NaiveBayesClassification> instances = new Vector<NaiveBayesClassification>(5);
	private HashMap<String, Boolean> vocabulary = new HashMap<String, Boolean>();
	protected ArrayList<Notification> notifications = new ArrayList<Notification>();
	protected String name;
	protected String uuid;
	
	public static void main(String[] args){

	}

	// faster search for words in vocabulary this way than if it were a list
	public void addTokens(String[] tokens){
		for (int i = 0; i < tokens.length; i++){
			if (vocabulary.get(tokens[i])){
				continue;
			} else {
				vocabulary.put(tokens[i], true);
			}
		} 
	}

	public void addNotification(Notification notification){
		this.notifications.add(notification);
	}

}
