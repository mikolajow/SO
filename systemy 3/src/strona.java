
public class strona {
	
	private int numer;
	private int czasWprowadzenia;
	private int czasOstatniegoUzycia;
	private boolean czyOstatnioU�ywana;
	
	public strona(int numer) {
		this.numer = numer;
		this.czasWprowadzenia = 0;
		this.czasOstatniegoUzycia = 0;
		this.czyOstatnioU�ywana = false;
	}//koniec konstruktora 

	public boolean getCzyOstatnioU�ywana() {
		return czyOstatnioU�ywana;
	}

	public void setCzyOstatnioU�ywana(boolean czyOstatnioU�ywana) {
		this.czyOstatnioU�ywana = czyOstatnioU�ywana;
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










