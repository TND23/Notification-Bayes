package learning;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import learning.Klass;
import learning.Notification;
import java.util.Set;

public class Bernouli implements TrainingBackend{
		
	private HashMap<String[], Double> conditionalProbability = new HashMap<String[], Double>();
	/* 
		REFACTOR: NAME CHANGE 
		what is prior?
	*/
	private HashMap<String, Double> prior = new HashMap<String, Double>();

	public Bernouli(){

	}

	public static void main(String[] args){
		
	}
	
	/* 
		Loop through the klasses, and for each find what ratio of all 
		notifications are this klass type. Then for each token in the vocabulary
		find how often token appears in notifications of the given klass type
	*/
	@Override
	public void train(){
		doTraining();
	}

	protected Void doTraining(){
		int notificationCount = Notification.instances.size();
		if (notificationCount == 0) return null;
		for (Klass klass : Klass.instances){
			int klassNotificationCount = countNotificationsOfKlass(klass.name);
			setKlassNotificationRatio(klass, notificationCount, klassNotificationCount);
			for (String token : Notification.vocabulary){
				setConditionalProbability(token, klass, klassNotificationCount);
			}
		}
		return null;
	}

	public void clearModel(){

	}

	// FiltrateNotification.NotificationText is going to be the variable type eventually
	public void applyBernouli(Notification notification){
		HashMap<String, Boolean> notificationTokens = notification.tokens;
		// put a Klass of type k as a key pointing to some double value
		HashMap<Klass, Double> score = new HashMap<Klass, Double>();
		for (Klass klass : Klass.instances){
			score.put(klass, Math.log(prior.get(klass.name)));
			for (String token : Notification.vocabulary){
				updateKlassScore(score, token, klass);		
			}
		}		
		Klass maxKlass = klassWithMaxScore(score);
		notification.klass = maxKlass;
	}

	private void updateKlassScore(HashMap<Klass, Double> score, String token, Klass klass){
		String[] condProbKey = {token, klass.name};
		double scoreDelta = conditionalProbability.get(condProbKey);
		if (notificationTokens.get(token)){
			score.put(klass, score.get(klass.name) + Math.log(scoreDelta));
		} else {
			score.put(klass, score.get(klass.name) + Math.log(1.0 - scoreDelta));
		}		
	}

	private Klass klassWithMaxScore(HashMap<Klass, Double> score){
		Klass maxKlass = Klass.instances.firstElement();
		for (Klass klass : Klass.instances){
			if (score.get(klass) >= score.get(maxKlass)){
				maxKlass = klass; 
			}
		}
		return maxKlass;
	}

	private int countKlassNotificationsWithToken(Klass k, String token){
		int count = 0;
		for (Notification notification : k.notifications){
			if (notification.tokens.get(token)) count++;
		}
		return count;
	}

	private int countNotificationsOfKlass(String name){
		return Notification.instances.get(name).length;
	}
	
	private void setKlassNotificationRatio(Klass klass, int notificationCount, int klassNotificationCount){
		double thisKlassRatio = (double)klassNotificationCount / notificationCount;
		prior.put(klass.name, thisKlassRatio);
	}

	// probability will lie between N-1/N and 1/N
	private void setConditionalProbability(String token, Klass klass, int klassNotificationCount){
		String[] condProbKey = {token, klass.name}; 
		int klassNotificationsWithToken = countKlassNotificationsWithToken(klass, token);
		double klassNotificationsWithTokenRatio = (double)klassNotificationsWithToken + 1.0 / klassNotificationCount + 2.0;
		conditionalProbability.put(condProbKey, klassNotificationsWithTokenRatio);		
	}

	// find words that are extremely common in important or unimportant email 
	private void getOutliars(double probabilityCutOff){

	}

}
