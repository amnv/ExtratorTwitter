import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Classificador {
	public void classificador() throws Exception {
		List<String> negativos = new ArrayList<>();
		List<String> positivos = new ArrayList<>();
		List<String> pClassificar = new ArrayList<>();

		BufferedReader brN = new BufferedReader(new FileReader("resultados/attributesNegatives.txt"));		
		BufferedReader brP = new BufferedReader(new FileReader("resultados/attributesPositives.txt"));
		BufferedReader br = new BufferedReader(new FileReader("resultados/toClassifier.txt"));

		while(brN.ready()){
			String linha = brN.readLine();
			negativos.add(linha);
		}
		//
		while(brP.ready()){
			String linha = brP.readLine();
			positivos.add(linha);
		}
		
		while(br.ready()){
			String linha = br.readLine();
			pClassificar.add(linha);
			
		}
			brP.close();
			brN.close();
			br.close();
			
			
		TweetClassifier tweets = new TweetClassifier(negativos, positivos, 0.75f);	
		int bom = 0;
		int ruim = 0;
		for(String linha:pClassificar){
				String result;
				result = tweets.classify(linha);
				System.out.println(linha + " " + result);
				if(result=="bom")  bom++;
				if(result=="ruim") ruim++;
				
		}
		
		System.out.println("Total de Twitters positivos: " +bom);
		System.out.println("Total de Twitters negativos: " +ruim);
	}		
}
