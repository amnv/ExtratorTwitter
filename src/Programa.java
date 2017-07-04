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
		String dir = "resultados/";
		Classificador c = new Classificador();
		Scanner a = new Scanner(System.in);

		do{
			System.out.println("Digite uma das opcoes: \n S: Samsung; \n A: Apple; \n L: LG\n E: Exit");
			pesquisa = a.nextLine();
			if(pesquisa.equalsIgnoreCase("S")){
				dir += "resultadosSamsung.txt";
				negative = "resultados/negatives_samsung.txt";
				file = "attributesNegatives";
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_samsung.txt";
				file = "attributesPositives";
				rsw.removerStopWords(positive, stopWords, file);
				
				twitter.search("samsung","pt",dir);
				file = "toClassifier";
				rsw.removerStopWords(dir, stopWords, file);
				
				c.classificador();
			}
			else if (pesquisa.equalsIgnoreCase("A")){
				negative = "resultados/negatives_apple.txt";
				file = "attributesNegatives";
				dir+= "resultadosApple.txt";
				
				rsw.removerStopWords(negative, stopWords, file);
				
				positive = "resultados/positives_apple.txt";
				file = "attributesPositives";
				rsw.removerStopWords(positive, stopWords, file);
				
				twitter.search("apple", "pt", dir);
				file = "toClassifier";
				rsw.removerStopWords(dir, stopWords, file);
				
				c.classificador();
				
			}
			
		}while(!pesquisa.equalsIgnoreCase("E"));
		a.close();
		
	}
}
