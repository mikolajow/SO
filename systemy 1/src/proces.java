
public class proces {
	
	private int id;
	private int czasTrwania;
	private int czasTrwania1;
	private int czasWejscia;
	private int czasOczekiwania;
	private int czasZakonczenia;
	private int czasTAT;
	
	public proces( int id, int czasT, int czasW ) {	//konstruktor
		this.id=id;
		this.czasTrwania=czasT;
		this.czasWejscia=czasW;
		this.czasOczekiwania=0;
		this.czasZakonczenia = 0;
		this.czasTAT = 0;
		this.czasTrwania1 = czasT;
	}
	
	public int getCzasTrwania1() {return czasTrwania1;}
	public void setCzasTrwania1(int czasTrwania1) {this.czasTrwania1 = czasTrwania1;}
	
	public proces ( proces p) {
		this.id = p.getId();
		this.czasTrwania=p.getCzasTrw();
		this.czasWejscia=p.getCzasWej();
		this.czasOczekiwania=p.getCzasOczek();
		this.czasZakonczenia = 0;
		this.czasTAT = 0;
		this.czasTrwania1 = p.getCzasTrw();
	}
	
	
	public int getCzasZakonczenia() {return czasZakonczenia;}
	public void setCzasZakonczenia(int czasZakonczenia) {this.czasZakonczenia = czasZakonczenia;}
	
	public int getCzasTAT() {return czasTAT;}
	public void setCzasTAT(int czasTAT) {this.czasTAT = czasTAT;}
	//GETTERY
	public int getCzasOczek() { return czasOczekiwania; }
	public int getId() {return id;}
	public int getCzasWej() {return czasWejscia;}
	public int getCzasTrw() {return czasTrwania;}
	
	//SETTERY
	public void setCzasOczek(int czasOczek) { this.czasOczekiwania = czasOczek; }
	public void setId(int id) {this.id = id;}
	public void setCzasTrw(int czasT) {this.czasTrwania = czasT;}
	public void setCzasWej(int czasW) {this.czasWejscia = czasW;}
	
	//METODY
	public void inkrementujCzasOczek() { this.czasOczekiwania = czasOczekiwania + 1; }
	
	public void zwiekszCzasOczekiwania(int oIle) { czasOczekiwania = czasOczekiwania + oIle; }
	public void zmniejszCzasOczekiwania(int oIle) { czasOczekiwania = czasOczekiwania - oIle; }
	
	public void zmniejszCzasTrwaniaOJeden() { this.czasTrwania = czasTrwania -1; }
	public void zmniejszCzasTrwania( int oIle ) { this.czasTrwania = czasTrwania - oIle; }

	@Override
	public String toString() {
		return "proces [id=" + id + ", czasTrwania=" + czasTrwania + ", czasWejscia=" + czasWejscia
				+ ", czasOczekiwania=" + czasOczekiwania + "]";
	}
	
}//koniec klasy
