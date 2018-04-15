import java.util.ArrayList;
import java.util.Random;

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
		
		//LOKALNOSC ODWO£AÑ
		
		
		
		this.odwolania = new ArrayList<>();
		Random generator =  new Random();
		
		int pierwszaStrona = generator.nextInt(liczbaStron);		//bo strony maj¹ numery od 0  generator gerenuje inty bez górnej wartosci granicy
		odwolania.add(pierwszaStrona);
		
		int skokSredni = liczbaStron/4;		// 9% szans na to ze natepne odwolanie bedzie oddalone o skok sredni - okolo 25% liczby odwolan
		int skokDuzy = liczbaStron/2;		// 1% szans na to ze natepne odwolanie bedzie oddalone o skok duzy - okolo 50% liczby odwolan
		
		int ostatniaDodanaStrona = pierwszaStrona;
		
		for( int i= 1; i <= iloscOdwolan; i++ ) {	//i = 1 bo pierwsza strona juz dodana
			
			double prawdopodobienstwo = Math.random()*100;
			double prawdopodobienstwo2 = Math.random()*100;	
			// jesli od 0 do 50 to idziemy w gore ( kolejna ramka wieksza od ostatniej ) else idziemy w dó³ ( kolejna ramka mniejsza od ostatniej )
			
			if ( prawdopodobienstwo2 <= 50 ) {
			
				if ( prawdopodobienstwo >= 0 && prawdopodobienstwo < 90 ) {
					if ( ostatniaDodanaStrona == liczbaStron -1 ) {
						odwolania.add(0);	//zapêtlamy
						ostatniaDodanaStrona = 0;
					}//koniec if
					else {
						ostatniaDodanaStrona++;				//dodajemy nastêpna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona + skokSredni > liczbaStron -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokSredni - (liczbaStron - 1);
						odwolania.add(ileDoPrzejsciaOdZera - 1);
						ostatniaDodanaStrona = ileDoPrzejsciaOdZera - 1;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona + skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
				
					if ( ostatniaDodanaStrona + skokDuzy > liczbaStron -1 ) {
						int ileDoPrzejsciaOdZera = ostatniaDodanaStrona + skokDuzy - (liczbaStron - 1);
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
					if ( ostatniaDodanaStrona == 0 ) {
						odwolania.add(liczbaStron-1);	//zapêtlamy
						ostatniaDodanaStrona = liczbaStron-1;
					}//koniec if
					else {
						ostatniaDodanaStrona--;				//dodajemy nastêpna strone
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}// konied if ( bez skoku, nastepna strona )
				else if ( prawdopodobienstwo >= 90 && prawdopodobienstwo < 99 ) {
					
					if ( ostatniaDodanaStrona - skokSredni < 0 ) {
						int ileDoPrzejsciaOdKonca = skokSredni - ostatniaDodanaStrona;
						odwolania.add(liczbaStron - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = liczbaStron - ileDoPrzejsciaOdKonca;
					}//koniec if ( jesli "skacz¹c wyjde poza zakres" )
					else {	//jesli "skacz¹c" pozostane w zakresie
						ostatniaDodanaStrona = ostatniaDodanaStrona - skokSredni;
						odwolania.add(ostatniaDodanaStrona);
					}//koniec else
				}//koniec else if ( sredni skok )
				else if ( prawdopodobienstwo >= 99 && prawdopodobienstwo < 100 ) {
					if ( ostatniaDodanaStrona - skokDuzy < 0 ) {
						int ileDoPrzejsciaOdKonca = skokDuzy - ostatniaDodanaStrona;
						odwolania.add(liczbaStron - ileDoPrzejsciaOdKonca);
						ostatniaDodanaStrona = liczbaStron - ileDoPrzejsciaOdKonca;
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
	
	// KONSTRUKTOR TESTOWY
	
	public zalozenia(strona[] strony , int liczbaRamek, ArrayList<Integer> odwolania) {
		this.liczbaRamek = liczbaRamek;
		this.strony = strony;
		this.odwolania = new ArrayList<>();
		for ( Integer i : odwolania ) {
			this.odwolania.add(i);
		}//koniec for
	}//koniec konstruktora
	
	// KONSTRUKTOR KOPIUJ¥CY
	
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





















