import java.io.IOException;
import java.util.Scanner;

import twitter4j.TwitterException;

public class Programa {

	public static void main(String[] args) throws IOException, TwitterException {
		String texto = "resultados/resultados.txt";
		String stopWords = "resultados/stopWordsList.txt";
		RemoverStopWords rsw = new RemoverStopWords();
		//rsw.removerStopWords(texto, stopWords);
		ConfAndResourcesTwitter t = new ConfAndResourcesTwitter();
		String pesquisa = "";
		String lingua = "";
		String dir = "resultados/resultados.txt";
		Scanner a = new Scanner(System.in);
		do{
			System.out.println("Digite uma das opções: \n P: Para pesquisar; \n S: Para sair; \n R: Para remover SotpWords\n");
			pesquisa = a.nextLine();
			if(pesquisa.equalsIgnoreCase("P")){
				System.out.println("Digite sua pesquisa: ");
				pesquisa = a.nextLine();
				System.out.println("Escolha a lingua(en, pt): ");
				lingua = a.nextLine();
				if(lingua.equals("en")||lingua.equals("pt")){
					t.search(pesquisa, lingua, dir);
				}
			}else if(pesquisa.equalsIgnoreCase("R")){
				rsw.removerStopWords(texto, stopWords);
			}else if(pesquisa.equalsIgnoreCase("S")){
				
			}else{
				System.out.println("Nenhuma das opcoes validas foram digitadas!!!");
			}
			
		}while(pesquisa.equalsIgnoreCase("S"));
		a.close();
	
	}

}
