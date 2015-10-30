package test;

import static org.junit.Assert.*;
import learning.Notification;
import org.junit.Before;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;


public class NotificationTest{
	Notification notification;
	@Before
	public void initialize(){
		notification = new Notification("Foo", "C:/Users/tommy/workspace/Notification-Bayes/sample_docs/ex1.txt", "TestApp");
	}
	
	@Test
	public void getVocabularyShouldFindAllUniqueWords() {
		assertTrue(Notification.getVocabulary().contains("tell"));
		assertTrue(Notification.getVocabulary().contains("army"));
		assertTrue(Notification.getVocabulary().contains("selfemployed"));
		assertTrue(Notification.getVocabulary().contains("im"));
	}
	@Test
	@SuppressWarnings("unused")
	public void getVocabularyShouldHaveWordsFromManyNotifications() {
		Notification notificationTwo = new Notification("Bar", "C:/Users/tommy/workspace/Notification-Bayes/sample_docs/ex2.txt", "TestApp");
		assertTrue(Notification.getVocabulary().contains("selfemployed"));
		assertTrue(Notification.getVocabulary().contains("receding"));
	}
	

}
