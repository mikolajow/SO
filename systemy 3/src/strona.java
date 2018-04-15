
public class strona {
	
	private int numer;
	private int czasWprowadzenia;
	private int czasOstatniegoUzycia;
	private boolean czyOstatnioU퓓wana;
	
	public strona(int numer) {
		this.numer = numer;
		this.czasWprowadzenia = 0;
		this.czasOstatniegoUzycia = 0;
		this.czyOstatnioU퓓wana = false;
	}//koniec konstruktora 

	public boolean getCzyOstatnioU퓓wana() {
		return czyOstatnioU퓓wana;
	}

	public void setCzyOstatnioU퓓wana(boolean czyOstatnioU퓓wana) {
		this.czyOstatnioU퓓wana = czyOstatnioU퓓wana;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public int getCzasWprowadzenia() {
		return czasWprowadzenia;
	}

	public void setCzasWprowadzenia(int czasWprowadzenia) {
		this.czasWprowadzenia = czasWprowadzenia;
	}

	public int getCzasOstatniegoUzycia() {
		return czasOstatniegoUzycia;
	}

	public void setCzasOstatniegoUzycia(int czasOstatniegoUzycia) {
		this.czasOstatniegoUzycia = czasOstatniegoUzycia;
	}

	@Override
	public String toString() {
		return "[numer=" + numer + ", czasWprowadzenia=" + czasWprowadzenia + ", czasOstatniegoUzycia="
				+ czasOstatniegoUzycia + "]";
	}//koniec toStringa

	
	
}//koniec klasy










