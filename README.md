### Notification-Bayes

This project is an attempt to create a Naive bayesian text filter in Java with some bonus functionality. 

#### Structure

As of now this is the general layout of how the pieces are supposed to fit together now:

1. **Bernouli**
  * The class containing the actual filtering algorithm.
2. **Notifications**
  * Each notification/document has a **Klass** which describes how a user classified it or how they would classify it.
  * Each notification is composed of words, which together form a vocabulary.
3. **Klass**
  * An abstract class extended by any number of actual classifications such as "Spam" or "High-Priority"
  
#### Todos 

Nice things to add once the basics are finished.

1. Add a **UserGroup** class which can be used to categorize the similarities between how different users categorize notifications. 
2. Add a **HeuristicBernouli** class which tries to quickly identify how a user will categorize notifications by matching the users behavior to the behavior patterns in **UserGroup**. 

#### Changes

The following are recommendations of ways to improve the existing code and other useful suggestions.

1. Add a test suite.
