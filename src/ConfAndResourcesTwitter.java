
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import java.io.*;


public class ConfAndResourcesTwitter {
	private ConfigurationBuilder configuracao;
	private TwitterFactory twitterFactory;
	private Twitter twitter;
	private Query query;
	
	public ConfAndResourcesTwitter (){
		configuracao = new ConfigurationBuilder();
		configuracao.setDebugEnabled(true).
		setOAuthConsumerKey("adicione um que funcione").
		setOAuthConsumerSecret("adicione um que funcione").
		setOAuthAccessToken("adicione um que funcione").
		setOAuthAccessTokenSecret("adicione um que funcione");
		twitterFactory = new TwitterFactory(configuracao.build());
		twitter = twitterFactory.getInstance();
		query = new Query();
		query.setCount(100);
	
	}
	
	public void search(String pesquisa, String lingua, String dir) throws IOException, TwitterException{
		String aux;
		query.setQuery(pesquisa+"-@ -filter:retweets -filter:links");
		query.setLang(lingua);
		FileWriter arq = new FileWriter(dir);
	    PrintWriter gravarArq = new PrintWriter(arq);
	    QueryResult result = twitter.search(query);
	    
	    for (Status status : result.getTweets()) {
	    	aux = status.getText();
	        System.out.println(aux);
	        gravarArq.println(aux);
	    }
	    gravarArq.close();
	    arq.close();
	}
	

	public ConfigurationBuilder getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfigurationBuilder configuracao) {
		this.configuracao = configuracao;
	}

	public TwitterFactory getTf() {
		return twitterFactory;
	}

	public void setTf(TwitterFactory tf) {
		this.twitterFactory = tf;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public TwitterFactory getTwitterFactory() {
		return twitterFactory;
	}

	public void setTwitterFactory(TwitterFactory twitterFactory) {
		this.twitterFactory = twitterFactory;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}  
}
