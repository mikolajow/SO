import java.util.ArrayList;

public class OPT {
	//wywal strone która nie bedzie uzywana w najbli¿szym czasie
	//algorytm co jakiœ czas musi zrobic sobie przerwe i machn¹æ tarota...
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> ListaOdwolan;
	
	public OPT(zalozenia z) {
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
					
					int zaIleSiePowtorzy = 0;
					int czajnik = 0;
					int odwolanieNajpozniejsze = 0;
					
					
					
					for ( strona s : stronyWRamie ) {
						if ( ListaOdwolan.contains(s.getNumer()) ) {
							czajnik = ListaOdwolan.indexOf(s.getNumer());
							if ( czajnik > zaIleSiePowtorzy ) { 
								zaIleSiePowtorzy = czajnik; 
								odwolanieNajpozniejsze = s.getNumer();
							}//koniec if wew
						}//koniec if
						else {
							odwolanieNajpozniejsze = s.getNumer();
							break;
						}//jak sie nie powtarza wcale to usówamy w³asnie to
					}//koniec for
					
					
					
					//szukam i usówam 
					for ( strona s : stronyWRamie ) {
						if ( s.getNumer() == odwolanieNajpozniejsze ) {
							stronyWRamie.remove(s);
							break;
						}//koniec if
					}//koniec for
					
				}//koniec if wewnatrznego
				stronyWRamie.add(strony[aktualneOdwolanie]);	//dodaje ramke potrzebna do aktualnego odwlania
			}//koniec else
			
		}//koniec while
		
		
		return bledyStrony;
	}//koniec run
	
	
	
}//koniec klasy

























