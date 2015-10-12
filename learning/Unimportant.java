package learning;
import java.util.Vector;
import java.util.HashMap;
import learning.Klass;
import java.util.ArrayList;

public class Unimportant extends Klass{

	public static Vector<Klass> instances = new Vector<Klass>(5);
	public static final String identifier = "UNI";
	private HashMap<String, Boolean> vocabulary = new HashMap<String, Boolean>();
	protected ArrayList<Notification> notifications = new ArrayList<Notification>();
	protected String name;
	protected String uuid;


	public Unimportant(){
				
	}

	public static void main(String[] args){

	}

	public void addNotification(Notification notification){
		this.notifications.add(notification);
	}

}
