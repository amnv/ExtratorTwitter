import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTest {
	
public static void main (String[] args){
	
	ConfigurationBuilder cb = new ConfigurationBuilder();
	
	cb.setDebugEnabled(true).setOAuthConsumerKey("kok7v6LyQMOPGj9EAupE4Eae1");
	cb.setDebugEnabled(true).setOAuthConsumerSecret("31JPXq08LuYb5cxVNFRoSWWsCPdmDrO1vfeGcbEt2FDorKAB2T");
	cb.setDebugEnabled(true).setOAuthAccessToken("109446571-44LPd2E5qOzNMbzaXr8CWnfXi7An8JP0a9MRfBaU");
	cb.setDebugEnabled(true).setOAuthAccessTokenSecret("ZLP6Tzqt8rcJSrRGLMJbjpEGlxbvYQRBmtl8ZXMHoRiI5");
	
	TwitterFactory teste = new TwitterFactory(cb.build());
    Twitter twitter = teste.getInstance();
	
	
	Query query = new Query("troinha");
    QueryResult result = null;
	try {
		result = twitter.search(query);
	} catch (TwitterException e) {
		e.printStackTrace();
	}
    for (Status status : result.getTweets()) {
        System.out.println(status.getText());
    }
}


}
