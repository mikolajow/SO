import java.util.ArrayList;

public class LRU_aproksymowany {
	// algorytm "w przyblizeniu lru"
	//algorytm drugiej szansy
	//kazda strona ma przypisany bit odniesienia kt�ry m�wi czy by�a ostatnio u�ywana 
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> ListaOdwolan;
	
	public LRU_aproksymowany(zalozenia z) {
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
					s.setCzyOstatnioU�ywana(true); 				//zapisuje ze strona by�a ostatnio uzywana
				}//koniec if
			}//koniec for
			
			if ( czyJuzJest ) {
				ListaOdwolan.remove(0);
			}//jak jest to us�wam odwolanie z listy i przechodze do nastepnego
			else {//jesli nie ma
				bledyStrony++;
				if ( stronyWRamie.size() == liczbaRamek ) {//jesli wszystkie ramki zaj�te
					//miejsce na algorytm us�wania strony
					
					
					
					int numerStronyDoUsuniecia = stronyWRamie.get(0).getNumer(); 
					
					//przeszukuje pamiec fizyczna aby znalezc najdawniej uzywana strone
					for ( int s = 0; s < stronyWRamie.size()-1; s++) {
						if ( stronyWRamie.get(s).getCzyOstatnioU�ywana() ) { 	//je�li by�a ostatnio u�ywana ( bit odniesienia na 1 ) to
							stronyWRamie.get(s).setCzyOstatnioU�ywana(false);	//zeruje bit odniesienia
							strona czajnik = stronyWRamie.get(s);				//zapisuje w zmiennej pomocniczej
							stronyWRamie.remove(s);								//ustawiam na ostatniej pozyjci
							stronyWRamie.add(czajnik);
							s--;
						}//koniec if
						else {//jesli nie by�a ostatnio uzywana ( bit odniesienia r�wny 0 )
							numerStronyDoUsuniecia = stronyWRamie.get(s).getNumer();
						}//koniec else
					}//koniec for
					
					//szukam i us�wam 
					for ( strona s : stronyWRamie ) {
						if ( s.getNumer() == numerStronyDoUsuniecia ) {
							stronyWRamie.remove(s);
							break;
						}//koniec if
					}//koniec for
				}//koniec if wewnetrznego
				
				
				//------------------------------------------------------
				
				
				
				stronyWRamie.add(strony[aktualneOdwolanie]);	//dodaje ramke potrzebna do aktualnego odwlania
				stronyWRamie.get(stronyWRamie.size()-1).setCzyOstatnioU�ywana(true); //i zapisuje ze by�a ostatnio u�ywana
			}//koniec else
			
		}//koniec while
		
		
		return bledyStrony;
	}//koniec run
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//koniec klasy




























