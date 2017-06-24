import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoverStopWords {
	
	public String[] lerStop(String stopWordsList) throws IOException{
		int aux = 0;
		ArrayList<String> stopWordLista = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(stopWordsList));
		while(br.ready()){
		   stopWordLista.add(br.readLine());
		//   System.out.println(stopWordLista.get(aux));
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

	public void removerStopWords(String texto,String fileStopWordsList) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(texto));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("resultados/resultadoSemStopWords.txt"));
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
	    String tweet = frase;
	    String aux= "";
	            tweet = tweet.trim().replaceAll("\\s+", " ");
	            System.out.println("After trim:  " + tweet);
	            String[] words = tweet.split(" ");


	            for (String word : words) {
	                wordsList.add(word);
	            }
	            System.out.println("After for loop:  " + wordsList);

	            //remove stop words here from the temp list
	            for (int i = 0; i < wordsList.size(); i++) {
	                // get the item as string
	                for (int j = 0; j < stopwords.length; j++) {
	                    if (stopwords[j].contains(wordsList.get(i))) {
	                        wordsList.remove(i);
	                    }
	                }
	            }
	            for (String str : wordsList) {
	            	aux = aux+str+" ";
	              //  System.out.print(str + " ");
	            }
	            aux = aux+ "\n";
	            System.out.println(aux);
	            
	return aux;

	}
	
	
	
		
}


