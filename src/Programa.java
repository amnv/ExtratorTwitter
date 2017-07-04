import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import twitter4j.TwitterException;

public class Programa {

	public static void main(String[] args) throws Exception {
		String file;
		String negative;
		String positive;
		String stopWords = "resultados/stopWordsList.txt";
		RemoverStopWords rsw = new RemoverStopWords();
		ConfAndResourcesTwitter twitter = new ConfAndResourcesTwitter();
		String pesquisa = "";
		String textoTwitter;
		Classificador classificador = new Classificador();
		Scanner in = new Scanner(System.in);

		do{
<<<<<<< HEAD
			System.out.println("Digite uma das opcoes: \n S: Samsung; \n A: Apple; \n L: LG\n E: Exit");
			pesquisa = in.nextLine();
=======
			System.out.println("Digite uma das opcoes: \n S: Samsung; \n A: Apple; \n L: LG\n");
			pesquisa = a.nextLine();
>>>>>>> branch 'master' of https://github.com/amnv/ExtratorTwitter.git
			if(pesquisa.equalsIgnoreCase("S")){
				textoTwitter = "resultados/resultadosSamsung.txt";
				negative = "resultados/negatives_samsung.txt";
				file = "resultados/attributesNegatives.txt";
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_samsung.txt";
				file = "resultados/attributesPositives.txt";
				rsw.removerStopWords(positive, stopWords, file);
				
				twitter.search("samsung","pt",textoTwitter);
				file = "resultados/toClassifier.txt";
				rsw.removerStopWords(textoTwitter, stopWords, file);
				
<<<<<<< HEAD
				classificador.classificador();
			}
			else if (pesquisa.equalsIgnoreCase("A")){
				negative = "resultados/negatives_apple.txt";
				file = "attributesNegatives";
				textoTwitter = "resultados/resultadosApple.txt";
				
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_apple.txt";
				file = "resultados/attributesPositives.txt";
				rsw.removerStopWords(positive, stopWords, file);
				
				twitter.search("apple", "pt", textoTwitter);
				file = "resultados/toClassifier.txt";
				rsw.removerStopWords(textoTwitter, stopWords, file);
				
				classificador.classificador();				
=======
				classificador();
			}else if(pesquisa.equalsIgnoreCase("A")){
				negative = "resultados/negatives_apple.txt";
				file = "attributesNegatives";
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_apple.txt";
				file = "attributesPositives";
				rsw.removerStopWords(positive, stopWords, file);
				
				t.search("apple","pt",dir);
				file = "toClassifier";
				rsw.removerStopWords(dir, stopWords, file);
				
				classificador();
			}else if(pesquisa.equalsIgnoreCase("L")){
				negative = "resultados/negatives_lg.txt";
				file = "attributesNegatives";
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_lg.txt";
				file = "attributesPositives";
				rsw.removerStopWords(positive, stopWords, file);
				
				t.search("lg","pt",dir);
				file = "toClassifier";
				rsw.removerStopWords(dir, stopWords, file);
				
				classificador();
			}else{
				System.out.println("Nao e opcao valida!!!");
>>>>>>> branch 'master' of https://github.com/amnv/ExtratorTwitter.git
			}
			
			
<<<<<<< HEAD
		}while(!pesquisa.equalsIgnoreCase("E"));
		in.close();
=======
		}while(pesquisa.equalsIgnoreCase("S"));
		a.close();
	
	}

	private static void classificador() throws Exception {
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
			//	System.out.println(result);
				if(result=="bom"){
					System.out.println(linha+": E BOM");
					bom++;
				}
				if(result=="ruim"){
					System.out.println(linha+": E RUIM");
					ruim++;
				}
				
		}
		
		System.out.println("Total de Twitters positivos: " +bom);
		System.out.println("Total de Twitters negativos: " +ruim);
		
>>>>>>> branch 'master' of https://github.com/amnv/ExtratorTwitter.git
		
	}
}
