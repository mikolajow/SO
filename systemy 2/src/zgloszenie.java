
public class zgloszenie {
	
	private int id;
	private int miejsceNaDysku;
	private int czasWejscia;
	private boolean priorytet;
	
	public zgloszenie( int id, int miejsce, int czasWej ) {
		this.id = id;
		this.miejsceNaDysku = miejsce;
		this.czasWejscia = czasWej;
	}//koniec konstruktora
	
	
	@Override
	public String toString() {
		return "[id=" + id + ", miejsceNaDysku=" + miejsceNaDysku + ", czasWejscia=" + czasWejscia + "]";
	}//koniec to stringa
	
	//GETTERS AND SETTERS
	
	public int getId() { return id; }
	public int getMiejsceNaDysku() { return miejsceNaDysku; }
	public int getCzasWejscia() { return czasWejscia; }
	
	public void setId(int id) { this.id = id; }
	public void setMiejsceNaDysku(int miejsceNaDysku) { this.miejsceNaDysku = miejsceNaDysku; }
	public void setCzasWejscia(int czasWejscia) { this.czasWejscia = czasWejscia; }
	
	//KONIEC------------------
	
	public void setPriorytet( boolean b ) {
		this.priorytet = b;
	}//koniec set priorytet
	
	public boolean getPriorytet() {
		return priorytet;
	}//koniec get priorytet
	
	
}//koniec klasy zgloszenie


























