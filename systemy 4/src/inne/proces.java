package inne;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class proces {
	
	private strona[] tablicaStron;
	private int iloscStron;
	private int numerPrierwszejStrony;
	private int numerOstatniejStrony;
	private ArrayList<Integer> odwolania;
	private ArrayList<Integer> zbiorRoboczy;; 
	private int iloscBledowStrony;
	
	
	//KONSTRUKTOR RANODMOWY
	
	public proces(int numerPierwszejStrony, int iloscStron, int iloscOdwolan) {
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
		
		
		//LOKALNOSC ODWO£AÑ
		
		
		
		this.odwolania = new ArrayList<>();
		
		//bo strony maj¹ numery od NumerPierwszejStrony do NumerOstatniejStrony a nextInt zwraca inta bez górnej granicy
		int pierwszaStrona = ThreadLocalRandom.current().nextInt(numerPierwszejStrony, numerOstatniejStrony+1);
		odwolania.add(pierwszaStrona);
		
		int skokSredni = iloscStron/4;		// 9% szans na to ze natepne odwolanie bedzie oddalone o skok sredni - okolo 25% liczby odwolan
		int skokDuzy = iloscStron/2;		// 1% szans na to ze natepne odwolanie bedzie oddalone o skok duzy - okolo 50% liczby odwolan
		
		int ostatniaDodanaStrona = pierwszaStrona;
		
		for( int i= 1; i < iloscOdwolan; i++ ) {	//i = 1 bo pierwsza strona juz dodana
			
			double prawdopodobienstwo = Math.random()*100;
			double prawdopodobienstwo2 = Math.random()*100;	
			// jesli od 0 do 50 to idziemy w gore ( kolejna ramka wieksza od ostatniej ) else idziemy w dó³ ( kolejna ramka mniejsza od ostatniej )
			
			if ( prawdopodobienstwo2 <= 50 ) {
			
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					//np gdy ma 5 stron od 9 do 13 ( 9,10,11,12,13 )  5+9-1=13
					if ( ostatniaDodanaStrona == iloscStron + numerPierwszejStrony -1 ) {
						odwolania.add(numerPierwszejStrony);	//zapêtlamy
						ostatniaDodanaStrona = numerPierwszejStrony;
					}//koniec if
					else {
						ostatniaDodanaStrona++;				//dodajemy nastêpna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona + skokSredni >  iloscStron + numerPierwszejStrony -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokSredni - (iloscStron - 1);
						odwolania.add(ileDoPrzejsciaOdZera - 1);
						ostatniaDodanaStrona = ileDoPrzejsciaOdZera - 1;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona + skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
				
					if ( ostatniaDodanaStrona + skokDuzy > iloscStron + numerPierwszejStrony -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokDuzy - (iloscStron - 1);
						odwolania.add(ileDoPrzejsciaOdZera - 1);
						ostatniaDodanaStrona = ileDoPrzejsciaOdZera - 1;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona + skokDuzy;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else ( duzy skok )
			}// koniec if prawdopodobienstwo 2
			else {
				
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					if ( ostatniaDodanaStrona == numerPierwszejStrony ) {
						odwolania.add(iloscStron + numerPierwszejStrony -1 );	//zapêtlamy
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1;
					}//koniec if
					else {
						ostatniaDodanaStrona--;				//dodajemy nastêpna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona - skokSredni < numerPierwszejStrony ) {
						int ileDoPrzejsciaOdKonca = numerPierwszejStrony + skokSredni - 1 - ostatniaDodanaStrona;
						odwolania.add(iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona - skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
					if ( ostatniaDodanaStrona - skokDuzy < numerPierwszejStrony ) {
						int ileDoPrzejsciaOdKonca = numerPierwszejStrony + skokDuzy - 1 - ostatniaDodanaStrona;
						odwolania.add(iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = iloscStron + numerPierwszejStrony -1 - ileDoPrzejsciaOdKonca;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona - skokDuzy;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else ( duzy skok )
				
			}//koniec else prawdopodobienstwo2
			
			
		}//koniec for
		
	// KONIEC LOKALNOSCI ODWOLAN ----------------------------------
	
	}//koniec konstruktora
	
	
	
	
	
	
	
	public int getIloscBledowStrony() {
		return iloscBledowStrony;
	}
	public void setIloscBledowStrony(int iloscBledowStrony) {
		this.iloscBledowStrony = iloscBledowStrony;
	}

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























