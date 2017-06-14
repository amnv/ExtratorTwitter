import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
	static List<String> negatives = new ArrayList<String>();
    static List<String> positives = new ArrayList<String>();

    
	public static void main(String[] args) throws Exception{
        arquivo();
		TweetClassifier classifier = new TweetClassifier(negatives, positives, 0.75f);
		
		String teste = "ruim de matar";
		String teste2 = "Tô ouvindo haha";
		String clazz;
		
		clazz = classifier.classify(teste);
		System.out.println(clazz);
		
		clazz = classifier.classify(teste2);
		System.out.println(clazz);
		
	}
	
	public static void arquivo() throws IOException{
      
		BufferedReader br = new BufferedReader(new FileReader("Tweets\\negatives.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("Tweets\\positives.txt"));
        String linha;

		while (br.ready()) {
			linha = br.readLine();
			negatives.add(linha);
		}
		
		while (br2.ready()) {
			linha = br2.readLine();
			positives.add(linha);
		}
		
		br.close();
		br2.close();				
}
	

	
}


