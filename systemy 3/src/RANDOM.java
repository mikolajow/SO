import java.util.ArrayList;
import java.util.Random;

public class RANDOM {
	//wywal losow¹ strone
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> ListaOdwolan;
	
	public RANDOM(zalozenia z) {
		this.liczbaRamek = z.getLiczbaRamek();
		this.strony = z.getStrony();
		this.ListaOdwolan = z.getOdwolania();
	}//koniec konstruktora
	
	public int run() {
		int bledyStrony = 0;
		
		ArrayList<strona> stronyWRamie = new ArrayList<>();
		
		while ( !ListaOdwolan.isEmpty() ) {
			int aktualneOdwolanie = ListaOdwolan.get(0);
			boolean czyJuzJest = false;
			
			for ( strona s : stronyWRamie ) {
				if( s.getNumer() == aktualneOdwolanie ) {		//sprawdzam czy aktualnie potrzebna strona juz jest w pamieci fizycznej
					czyJuzJest = true;
				}//koniec if
			}//koniec for
			
			if ( czyJuzJest ) {
				ListaOdwolan.remove(0);
			}//jak jest to usówam odwolanie z listy i przechodze do nastepnego
			else {
				bledyStrony++;
				if ( stronyWRamie.size() == liczbaRamek ) {
					//miejsce na algorytm usówania strony
					
					Random generator = new Random();
					int indexDoUsuniecia = generator.nextInt(liczbaRamek);
					stronyWRamie.remove(indexDoUsuniecia);
					
					
				}//koniec if wewnatrznego
				stronyWRamie.add(strony[aktualneOdwolanie]);	//dodaje ramke potrzebna do aktualnego odwlania
			}//koniec else
			
		}//koniec while
		
		
		return bledyStrony;
	}//koniec run
	
	
	
	
	
	
	
	
	
	
}//koniec klasy










