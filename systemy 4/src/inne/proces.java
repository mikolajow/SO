package inne;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class proces {
	
	private strona[] tablicaStron;				//numery stron danego procesu
	private int iloscStron;						//ilosc r�znych numer�w stron
	private int numerPrierwszejStrony;
	private int numerOstatniejStrony;
	private ArrayList<Integer> odwolania;		//odwolania procesu generowane z zasada lokalnosci
	private ArrayList<Integer> zbiorRoboczy;; 	//wykorzystywany przy modelu strefowym
	private int iloscBledowStrony;				//ile bledow ztrony wystapi przy obslydze procesu
	private int aktualnaLiczbaRamek;
	private ArrayList<Boolean> historiaBledow;	//uzywane w sterowaniu cz�sto�ci� b��d�w, je�li ostatnie odwolanie wywo�a�o b��d strony dodaje warto�� false
												//cz�sto�c b��d�w b�dzie liczona = ilo�c warto�ci false w li�cie / ilo�� wszystkich warto�ci w li�cie * 100
	//KONSTRUKTOR RANODMOWY
	
	
	
	
	
	
	public proces(int numerPierwszejStrony, int iloscStron, int iloscOdwolan) {
		this.historiaBledow = new ArrayList<>();
		this.zbiorRoboczy = new ArrayList<>();
		this.aktualnaLiczbaRamek = 0;
		this.iloscBledowStrony = 0;
		this.numerPrierwszejStrony = numerPierwszejStrony;
		this.iloscStron = iloscStron;
		this.tablicaStron = new strona[iloscStron];
		
		//nie od zera tylko od numer pierwszej strony
		int a = numerPierwszejStrony;
		for(int j = 0 ; j<tablicaStron.length; j++) {
			tablicaStron[j] = new strona(a);
			a++;
		}//koniec for
		this.numerOstatniejStrony = tablicaStron[tablicaStron.length-1].getNumer();
		
		
		//LOKALNOSC ODWO�A�
		
		
		
		this.odwolania = new ArrayList<>();
		
		//bo strony maj� numery od NumerPierwszejStrony do NumerOstatniejStrony a nextInt zwraca inta bez g�rnej granicy
		int pierwszaStrona = ThreadLocalRandom.current().nextInt(numerPierwszejStrony, numerOstatniejStrony+1);
		odwolania.add(pierwszaStrona);
		
		int skokSredni = iloscStron/4;		// 9% szans na to ze natepne odwolanie bedzie oddalone o skok sredni - okolo 25% liczby odwolan
		int skokDuzy = iloscStron/2;		// 1% szans na to ze natepne odwolanie bedzie oddalone o skok duzy - okolo 50% liczby odwolan
		
		int ostatniaDodanaStrona = pierwszaStrona;
		
		for( int i= 1; i < iloscOdwolan; i++ ) {	//i = 1 bo pierwsza strona juz dodana
			
			double prawdopodobienstwo = Math.random()*100;
			double prawdopodobienstwo2 = Math.random()*100;	
			// jesli od 0 do 50 to idziemy w gore ( kolejna ramka wieksza od ostatniej ) else idziemy w d� ( kolejna ramka mniejsza od ostatniej )
			
			if ( prawdopodobienstwo2 <= 50 ) {
			
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					//np gdy ma 5 stron od 9 do 13 ( 9,10,11,12,13 )  5+9-1=13
					if ( ostatniaDodanaStrona == iloscStron + numerPierwszejStrony -1 ) {
						odwolania.add(numerPierwszejStrony);	//zap�tlamy
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
	
	//konstruktor kopiuj�cy
	public proces(proces p) {
		this.tablicaStron = p.getStrony();
		this.iloscStron = p.getIloscStron();
		this.numerPrierwszejStrony = p.getNumerPrierwszejStrony();
		this.numerOstatniejStrony = p.getNumerOstatniejStrony();
		this.iloscBledowStrony = 0;
		this.aktualnaLiczbaRamek = 0;
		this.odwolania = new ArrayList<>();
		this.zbiorRoboczy = new ArrayList<>();
		this.historiaBledow = new ArrayList<>();
		for ( Integer i : p.getOdwolania() ) {
			this.odwolania.add(i);
		}//koniec for
	}//koniec kopiuj�cego
	
	
	public ArrayList<Boolean> getHistoriaBledow() {return historiaBledow;}
	public void setHistoriaBledow(ArrayList<Boolean> historiaBledow) {this.historiaBledow = historiaBledow;}
	
	public int getAktualnaLiczbaRamek() {return aktualnaLiczbaRamek;}
	public void setAktualnaLiczbaRamek(int aktualnaLiczbaRamek) {this.aktualnaLiczbaRamek = aktualnaLiczbaRamek;}
	
	public int getIloscBledowStrony() {return iloscBledowStrony;}
	public void setIloscBledowStrony(int iloscBledowStrony) {this.iloscBledowStrony = iloscBledowStrony;}

	// GETERY
	public strona[] getStrony() { return tablicaStron; }
	public ArrayList<Integer> getOdwolania() { return odwolania; }
	public int getIloscStron() {return iloscStron;}
	public int getNumerPrierwszejStrony() {return numerPrierwszejStrony;}
	public int getNumerOstatniejStrony() {return numerOstatniejStrony;}
	public ArrayList<Integer> getZbiorRoboczy() {return zbiorRoboczy;}
	
	/*
	// SETTERY
	public void setStrony(strona[] strony) { this.tablicaStron = strony; }
	public void setOdwolania(ArrayList<Integer> odwolania) { this.odwolania = odwolania; }
	public void setIloscStron(int iloscStron) {this.iloscStron = iloscStron;}
	public void setNumerPrierwszejStrony(int numerPrierwszejStrony) {this.numerPrierwszejStrony = numerPrierwszejStrony;}
	public void setNumerOstatniejStrony(int numerOstatniejStrony) {this.numerOstatniejStrony = numerOstatniejStrony;}
	public void setZbiorRoboczy(ArrayList<Integer> zbiorRoboczy) {this.zbiorRoboczy = zbiorRoboczy;}
	*/
	
	@Override
	public String toString() {
		return "proces [iloscStron= " + iloscStron + ", numerPrierwszejStrony= " + numerPrierwszejStrony + "]";
	}//koniec to string
	
	
	
	
}//koniec klasy























