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
		this.odwolania = new ArrayList<>();
		Random generator =  new Random();
		for( int i= 0; i<iloscOdwolan;i++ ) {
			odwolania.add(generator.nextInt(liczbaStron-1));		//bo strony maj¹ numery od 0
		}//koniec for
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
	
	
	// GETERY SETTERY
	public strona[] getStrony() { return strony; }
	public int getLiczbaRamek() { return liczbaRamek; }
	public ArrayList<Integer> getOdwolania() { return odwolania; }
	
	public void setStrony(strona[] strony) { this.strony = strony; }
	public void setLiczbaRamek(int liczbaRamek) { this.liczbaRamek = liczbaRamek; }
	public void setOdwolania(ArrayList<Integer> odwolania) { this.odwolania = odwolania; }
	
	
	
	
	
	
	
	
}//koniec klasy





















