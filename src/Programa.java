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

			System.out.println("Digite uma das opcoes: \n S: Samsung; \n A: Apple; \n L: LG\n E: Exit");
			pesquisa = in.nextLine();
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
			}
			
			

		}while(!pesquisa.equalsIgnoreCase("E"));
		in.close();
		
	}
}
