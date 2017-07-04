import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.*;

public class TweetClassifier {
	 
	 private Classifier classifier;
	 private List<Attribute> attributes;
	 private Instances instances;
	 private Instances trainInstances;
	 private Instances testInstances;
	 
	//Tweets positivos e negativos
	 public static final String NEGATIVE = "ruim";
	 public static final String POSITIVE = "bom";
	    
	 public static final List<String> CLASSES = Collections.unmodifiableList(Stream.of(NEGATIVE,POSITIVE).collect(Collectors.toList()));
	 
	    public TweetClassifier(List<String> negatives, List<String> positives, float trainRatio) throws Exception {
	    	
	    	List<String> tweets = new ArrayList<>();
	    	tweets.addAll(negatives);
	        tweets.addAll(positives);

	        attributes = getAllAttributes(tweets);
	        //o ultimo atribuito representa a classe
	        attributes.add(new Attribute("-class-", CLASSES));
	        
	        //Cria a instancia passando os atributos
	        instances = new Instances("Links", (ArrayList<Attribute>) attributes, tweets.size());
	        instances.setClassIndex(attributes.size() - 1);
	        
	        //Cria uma instancia pros tweets negativos e positivos
	        for (String tweet : negatives) {
	            instances.add(createTwettsInstance(tweet, NEGATIVE));
	        }
	        
	        for (String tweet : positives) {
	            instances.add(createTwettsInstance(tweet, POSITIVE));
	        }
	        
	        
	        //Divide as instancias em treinamento e teste
	        int trainingSize = Math.round(instances.size() * trainRatio);
	        int testSize = instances.size() - trainingSize;
	        instances.randomize(new Random());
	        trainInstances = new Instances(instances, 0, trainingSize);
	        testInstances = new Instances(instances, trainingSize, testSize);

	        classifier = new NaiveBayes();
	        classifier.buildClassifier(trainInstances);
	    }
	    
	    private List<Attribute> getAllAttributes(List<String> tweets) {
	        Set<String> stringAttributes = new HashSet<>();
	        
	        for (String tweet : tweets) {
	           String[] tweetsAttributes = getTweetAttributes(tweet);
	           for(String tweetAttribute: tweetsAttributes){
		           stringAttributes.add(tweetAttribute);
	           }
	        }
	        
	        List<Attribute> attributes = new ArrayList<>();
	        for (String stringAttribute : stringAttributes) {
	            attributes.add(new Attribute(stringAttribute));
	        }
	        return attributes;
	    }
	    
	    private String[] getTweetAttributes(String tweet){
	    	String[] parts = tweet.split(" ");
	    	return parts;
	    }
	    
	    private Instance createTwettsInstance(String tweet, String clazz) {

	        Instance linkInstance = new DenseInstance(attributes.size());
	        linkInstance.setDataset(instances);

	        for (int i = 0; i < attributes.size() - 1; i++) {
	            boolean attributeContained = false;
	            String[] linkAttributes = getTweetAttributes(tweet);
	            for (String linkAttribute : linkAttributes) {
	                if (linkAttribute.equals(attributes.get(i).name())) {
	                    attributeContained = true;
	                    break;
	                }
	            }
	            linkInstance.setValue(i, attributeContained ? 1 : 0);
	        }
	        if (clazz != null) {
	            linkInstance.setClassValue(clazz);
	        } else {
	            linkInstance.setClassMissing();
	        }

	        return linkInstance;
	    }
	    
	    public int classifyIndex(String tweet) throws Exception {
	        return (int) classifier.classifyInstance(createTwettsInstance(tweet, null));
	    }

	    public String classify(String tweet) throws Exception {
	        return CLASSES.get(classifyIndex(tweet));
	    }
	    
}
