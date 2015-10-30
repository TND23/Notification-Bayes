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
		assertTrue(notification.getVocabulary().contains("tell"));
		assertTrue(notification.getVocabulary().contains("army"));
		assertTrue(notification.getVocabulary().contains("selfemployed"));
		assertTrue(notification.getVocabulary().contains("im"));
	}
	
}
