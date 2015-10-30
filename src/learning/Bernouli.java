package learning;
import java.util.HashMap;
import java.lang.Math;
import learning.NaiveBayesClassification;
import learning.Notification;

public class Bernouli implements TrainingBackend{
		
	private HashMap<String[], Double> conditionalProbability = new HashMap<String[], Double>();
	/* 
		REFACTOR: NAME CHANGE 
		what is prior?
	*/
	private HashMap<String, Double> prior = new HashMap<String, Double>();

	public Bernouli(){

	}

	/* 
		Loop through the naiveBayesClassifications, and for each find what ratio of all 
		notifications are this naiveBayesClassifications type. Then for each token in the vocabulary
		find how often token appears in notifications of the given naiveBayesClassification type
	*/
	public void train(){
		doTraining();
	}

	protected void doTraining(){
		int notificationCount = Notification.instances.size();
		if (notificationCount != 0){
			for (NaiveBayesClassification nbc : NaiveBayesClassification.instances){
				int nbcCount = countNotificationsOfClassification(nbc.name);
				setNaiveBayesClassificationNotificationRatio(nbc, notificationCount, nbcCount);
				for (String token : Notification.vocabulary){
					setConditionalProbability(token, nbc, nbcCount);
				}
			}			
		}
	}

	public void clearModel(){

	}

	// FiltrateNotification.NotificationText is going to be the variable type eventually
	public void applyBernouli(Notification notification){
		HashMap<String, Boolean> notificationTokens = notification.tokens;
		// put a Klass of type k as a key pointing to some double value
		HashMap<NaiveBayesClassification, Double> score = new HashMap<NaiveBayesClassification, Double>();
		for (NaiveBayesClassification naiveBayesClassification : NaiveBayesClassification.instances){
			score.put(naiveBayesClassification, Math.log(prior.get(naiveBayesClassification.name)));
			for (String token : Notification.vocabulary){
				updateNaiveBayesClassificationScore(score, token, naiveBayesClassification, notificationTokens);		
			}
		}		
		NaiveBayesClassification maxNaiveBayesClassification = naiveBayesClassificationWithMaxScore(score);
		notification.naiveBayesClassification = maxNaiveBayesClassification;
	}

	private void updateNaiveBayesClassificationScore(HashMap<NaiveBayesClassification, Double> score, String token, NaiveBayesClassification naiveBayesClassification, HashMap<String, Boolean> notificationTokens){
		String[] condProbKey = {token, naiveBayesClassification.name};
		double scoreDelta = conditionalProbability.get(condProbKey);
		if (notificationTokens.get(token)){
			score.put(naiveBayesClassification, score.get(naiveBayesClassification.name) + Math.log(scoreDelta));
		} else {
			score.put(naiveBayesClassification, score.get(naiveBayesClassification.name) + Math.log(1.0 - scoreDelta));
		}		
	}

	private NaiveBayesClassification naiveBayesClassificationWithMaxScore(HashMap<NaiveBayesClassification, Double> score){
		NaiveBayesClassification maxNaiveBayesClassification = NaiveBayesClassification.instances.firstElement();
		for (NaiveBayesClassification naiveBayesClassification : NaiveBayesClassification.instances){
			if (score.get(naiveBayesClassification) >= score.get(maxNaiveBayesClassification)){
				maxNaiveBayesClassification = naiveBayesClassification; 
			}
		}
		return maxNaiveBayesClassification;
	}

	private int countNaiveBayesClassificationNotificationsWithToken(NaiveBayesClassification k, String token){
		int count = 0;
		for (Notification notification : k.notifications){
			if (notification.tokens.get(token)) count++;
		}
		return count;
	}

	private int countNotificationsOfClassification(String name){
		return Notification.instances.get(name).length;
	}
	
	private void setNaiveBayesClassificationNotificationRatio(NaiveBayesClassification naiveBayesClassification, int notificationCount, int klassNotificationCount){
		double thisKlassRatio = (double)klassNotificationCount / notificationCount;
		prior.put(naiveBayesClassification.name, thisKlassRatio);
	}

	// probability will lie between N-1/N and 1/N
	private void setConditionalProbability(String token, NaiveBayesClassification naiveBayesClassification, int klassNotificationCount){
		String[] condProbKey = {token, naiveBayesClassification.name}; 
		int klassNotificationsWithToken = countNaiveBayesClassificationNotificationsWithToken(naiveBayesClassification, token);
		double klassNotificationsWithTokenRatio = (double)klassNotificationsWithToken + 1.0 / klassNotificationCount + 2.0;
		conditionalProbability.put(condProbKey, klassNotificationsWithTokenRatio);		
	}

	// find words that are extremely common in important or unimportant email 

}
