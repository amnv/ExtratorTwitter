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

public class Classifier {
	 
	 private NaiveBayes classifier;
	 private List<Attribute> attributes;
	 private Instances instances;
	 private Instances trainInstances;
	 private Instances testInstances;
	//Tweets positivos e negativos
	 public static final String NEGATIVE = "n";
	 public static final String POSITIVE = "p";
	    
	 public static final List<String> CLASSES = Collections.unmodifiableList(Stream.of(NEGATIVE,POSITIVE).collect(Collectors.toList()));
	 
	    public Classifier(List<String> negatives, List<String> positives, float trainRatio) throws Exception {
	    	
	    	List<String> twetts = new ArrayList<>();
	    	twetts.addAll(negatives);
	        twetts.addAll(positives);

	        attributes = getAllAttributes(twetts);
	        //o ultimo atribuito representa a classe
	        attributes.add(new Attribute("-class-", CLASSES));
	        
	        //Cria a instância passando os atributos
	        instances = new Instances("Links", (ArrayList<Attribute>) attributes, twetts.size());
	        instances.setClassIndex(attributes.size() - 1);
	        
	        //Cria uma instância pros tweets negativos e positivos
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
	    
	    private List<Attribute> getAllAttributes(List<String> twetts) {
	        Set<String> stringAttributes = new HashSet<>();
	        for (String tweet : twetts) {
	        	//tratar os tweets
	            String[] linkAttributes = getTweetAttributes(tweet);
	            for (String linkAttribute : linkAttributes) {
	                stringAttributes.add(linkAttribute);
	            }
	        }
	        List<Attribute> attributes = new ArrayList<>();
	        for (String stringAttribute : stringAttributes) {
	            attributes.add(new Attribute(stringAttribute));
	        }
	        return attributes;
	    }
	    
}
