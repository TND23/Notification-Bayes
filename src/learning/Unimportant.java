package learning;
import java.util.Vector;
import java.util.HashMap;
import learning.NaiveBayesClassification;
import java.util.ArrayList;

public class Unimportant extends NaiveBayesClassification{

	public static Vector<NaiveBayesClassification> instances = new Vector<NaiveBayesClassification>(5);
	public static final String identifier = "UNI";
	private HashMap<String, Boolean> vocabulary = new HashMap<String, Boolean>();
	protected ArrayList<Notification> notifications = new ArrayList<Notification>();
	protected String name;
	protected String uuid;


	public Unimportant(){
				
	}

	public void addNotification(Notification notification){
		this.notifications.add(notification);
	}

}
