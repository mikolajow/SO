import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zalozenia {
	
	private strona[] strony;
	private int liczbaRamek;
	private ArrayList<Integer> odwolania;
	
	//KONSTRUKTOR RANODMOWY
	public zalozenia(int liczbaStron, int liczbaRamek, int iloscOdwolan) {
		this.liczbaRamek = liczbaRamek;
		this.strony = new strona[liczbaStron];
		for(int i = 0; i<strony.length; i++) {
			strony[i] = new strona(i);
		}//koniec for
		
		//LOKALNOSC ODWO�A�
		
		int numerPierwszejStrony = 0;
		int iloscStron = liczbaStron;
		int numerOstatniejStrony = strony[strony.length-1].getNumer();
		
		this.odwolania = new ArrayList<>();
		
		//bo strony maj� numery od NumerPierwszejStrony do NumerOstatniejStrony a nextInt zwraca inta bez g�rnej granicy
		int pierwszaStrona = ThreadLocalRandom.current().nextInt(numerPierwszejStrony, numerOstatniejStrony+1);
		odwolania.add(pierwszaStrona);
		
		int skokSredni = iloscStron/4;		// 9% szans na to ze natepne odwolanie bedzie oddalone o skok sredni - okolo 25% liczby odwolan
		int skokDuzy = iloscStron/2;		// 1% szans na to ze natepne odwolanie bedzie oddalone o skok duzy - okolo 50% liczby odwolan
		
		int ostatniaDodanaStrona = pierwszaStrona;
		
		for( int i= 1; i <= iloscOdwolan; i++ ) {	//i = 1 bo pierwsza strona juz dodana
			
			double prawdopodobienstwo = Math.random()*100;
			double prawdopodobienstwo2 = Math.random()*100;	
			// jesli od 0 do 50 to idziemy w gore ( kolejna ramka wieksza od ostatniej ) else idziemy w d� ( kolejna ramka mniejsza od ostatniej )
			
			if ( prawdopodobienstwo2 <= 50 ) {
			
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					//np gdy ma 5 stron od 9 do 13 ( 9,10,11,12,13 )  5+9-1=13
					if ( ostatniaDodanaStrona == iloscStron + numerPierwszejStrony -1 ) {
						odwolania.add(0);	//zap�tlamy
						ostatniaDodanaStrona = numerPierwszejStrony;
					}//koniec if
					else {
						ostatniaDodanaStrona++;				//dodajemy nast�pna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona + skokSredni >  iloscStron + numerPierwszejStrony -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokSredni - (iloscStron - 1);
						odwolania.add(ileDoPrzejsciaOdZera - 1);
						ostatniaDodanaStrona = ileDoPrzejsciaOdZera - 1;
					}//koniec if ( jesli "skacz�c wyjde poza zakres" )
					else {	//jesli "skacz�c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona + skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
				
					if ( ostatniaDodanaStrona + skokDuzy > iloscStron + numerPierwszejStrony -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokDuzy - (iloscStron - 1);
						odwolania.add(ileDoPrzejsciaOdZera - 1);
						ostatniaDodanaStrona = ileDoPrzejsciaOdZera - 1;
					}//koniec if ( jesli "skacz�c wyjde poza zakres" )
					else {	//jesli "skacz�c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona + skokDuzy;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else ( duzy skok )
			}// koniec if prawdopodobienstwo 2
			else {
				
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					if ( ostatniaDodanaStrona == numerPierwszejStrony ) {
						odwolania.add(iloscStron + numerPierwszejStrony -1 );	//zap�tlamy
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1;
					}//koniec if
					else {
						ostatniaDodanaStrona--;				//dodajemy nast�pna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona - skokSredni < numerPierwszejStrony ) {
						int ileDoPrzejsciaOdKonca = numerPierwszejStrony + skokSredni - 1 - ostatniaDodanaStrona;
						odwolania.add(iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca;
					}//koniec if ( jesli "skacz�c wyjde poza zakres" )
					else {	//jesli "skacz�c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona - skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
					if ( ostatniaDodanaStrona - skokDuzy < numerPierwszejStrony ) {
						int ileDoPrzejsciaOdKonca = numerPierwszejStrony + skokDuzy - 1 - ostatniaDodanaStrona;
						odwolania.add(iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca;
					}//koniec if ( jesli "skacz�c wyjde poza zakres" )
					else {	//jesli "skacz�c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona - skokDuzy;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else ( duzy skok )
				
			}//koniec else prawdopodobienstwo2
			
			
		}//koniec for
		
	// KONIEC LOKALNOSCI ODWOLAN ----------------------------------
	
		
	}//koniec konstruktora
	
	// KONSTRUKTOR TESTOWY
	
	public zalozenia(strona[] strony , int liczbaRamek, ArrayList<Integer> odwolania) {
		this.liczbaRamek = liczbaRamek;
		this.strony = strony;
		this.odwolania = new ArrayList<>();
		for ( Integer i : odwolania ) {
			this.odwolania.add(i);
		}//koniec for
	}//koniec konstruktora
	
	// KONSTRUKTOR KOPIUJ�CY
	
	public zalozenia( zalozenia dane) {
		this.liczbaRamek = dane.getLiczbaRamek();
		this.strony = dane.getStrony();
		
		this.odwolania = new ArrayList<>();
		
		for ( Integer i : dane.getOdwolania() ) {
			odwolania.add(i);
		}//koniec for
	}//koniec konstruktora
	
	
	// GETERY SETTERY
	public strona[] getStrony() { return strony; }
	public int getLiczbaRamek() { return liczbaRamek; }
	public ArrayList<Integer> getOdwolania() { return odwolania; }
	
	public void setStrony(strona[] strony) { this.strony = strony; }
	public void setLiczbaRamek(int liczbaRamek) { this.liczbaRamek = liczbaRamek; }
	public void setOdwolania(ArrayList<Integer> odwolania) { this.odwolania = odwolania; }
	
	
	
	
	
	
	
	
}//koniec klasy





















