import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import weka.core.expressionlanguage.common.Operators;

public class RemoverStopWords {
	
	public String[] lerStop(String stopWordsList) throws IOException{
		int aux = 0;
		ArrayList<String> stopWordLista = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(stopWordsList));
		while(br.ready()){
		   stopWordLista.add(br.readLine());
		   aux++;
		}
		
		aux = stopWordLista.size();
		String a[] = new String[aux]; 
		for(int i = 0;i<aux;i++){
			a[i] = stopWordLista.get(i);
		}
		
		br.close();
		return a;
	}

	public void removerStopWords(String texto, String fileStopWordsList, String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(texto));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file));
		while(br.ready()){
		   String linha = br.readLine();
		   System.out.println(linha);
		   buffWrite.append(removeStopWords(fileStopWordsList,linha));
		}
		br.close();
		buffWrite.close();
	}
	
	public String removeStopWords(String stopWordsList, String frase) throws IOException{
		ArrayList<String> wordsList = new ArrayList<String>();
		String[] stopwords = lerStop(stopWordsList);
	    String tweet = frase.replaceAll("#", "").replaceAll("//?", "");
	    String aux= "";
	            tweet = tweet.trim().replaceAll("\\s+", " ");
	            System.out.println("After trim:  " + tweet);
	            String[] words = tweet.split(" ");


	            for (String word : words) {
	                wordsList.add(word);
	            }
	            System.out.println("After for loop:  " + wordsList);

	            for (int i = 0; i < stopwords.length; i++) {
	                if(wordsList.contains(stopwords[i])){
	                	wordsList.removeAll(Collections.singleton(stopwords[i]));;
	                }
	            }
	            for (String str : wordsList) {
	            	aux = aux+str+" ";
	            }
	            aux = aux.toUpperCase()+ "\n";
	            System.out.println(aux);
	            
	return aux;

	}	
}


