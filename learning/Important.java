package learning;
import java.util.Vector;
import java.util.HashMap;
import learning.Klass;
import java.util.ArrayList;

public class Important extends Klass{

	public static Vector<Klass> instances = new Vector<Klass>(5);
	public static final String identifier = "IMP";
	private HashMap<String, Boolean> vocabulary = new HashMap<String, Boolean>();
	protected ArrayList<Notification> notifications = new ArrayList<Notification>();
	protected String name;
	protected String uuid;


	public Important(){
				
	}

	public static void main(String[] args){

	}

	public void addNotification(Notification notification){
		this.notifications.add(notification);
	}

}
/*

"SOME SORT OF FILTER_PREFcolumndelimTITLE1columndelimThis is all the content -- the part we are most likely to be interested incolumndelim"

cursor.getString(cursor.getColumnIndexOrThrow(FiltratingNotificationEntry.COLUMN_NAME_FILTER_PREF)) + DELIM_SEP +
cursor.getString(cursor.getColumnIndexOrThrow(FiltratingNotificationEntry.COLUMN_NAME_TITLE)) + DELIM_SEP +
cursor.getString(cursor.getColumnIndexOrThrow(FiltratingNotificationEntry.COLUMN_NAME_CONTENT)) + DELIM_SEP +
cursor.getString(cursor.getColumnIndexOrThrow(FiltratingNotificationEntry.COLUMN_NAME_PACKAGE)) + DELIM_SEP +
cursor.getString(cursor.getColumnIndexOrThrow(FiltratingNotificationEntry.COLUMN_NAME_TICKER))

ContentValues contentValues = new ContentValues();
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_UUID, notification.getUuid());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_TIMESTAMP, notification.getTimestamp());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_TITLE, notification.text().getTitle());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_CONTENT, notification.text().getContent());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_PACKAGE, notification.text().getPackageName());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_TICKER, notification.text().getTicker());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_AGE, AgeTypes.RECENT.name());
contentValues.put(FiltratingNotificationEntry.COLUMN_NAME_FILTER_PREF, FilterPrefTypes.UNSPECIFIED.name())

*/