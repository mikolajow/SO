import java.util.ArrayList;
import java.util.Random;

public class dysk {
	
	private int rozmiar;
	private ArrayList<zgloszenie> lista;
	
	public dysk( int rozmiar ) {
		this.rozmiar = rozmiar;
		this.lista = new ArrayList<>();
		
		Random generator = new Random();
		
		for ( int i = 0; i < rozmiar*2/3 ; i++ ) {	//wype³niamy 2/3 dysku ( oko³o )
			this.lista.add(new zgloszenie(generator.nextInt(rozmiar *2 ), generator.nextInt(rozmiar), generator.nextInt(20) ));
																	//od 0 do rozmiar ale bez rozmiar ¿eby mieci³o sie w indeksie
		}//koniec for
	}//koniec konstruktora
	
	//GETERY
	
	public int getRozmiar() { return rozmiar; }
	public ArrayList<zgloszenie> getLista() { return lista; }
	
	public void setRozmiar(int rozmiar) { this.rozmiar = rozmiar; }
	public void setLista(ArrayList<zgloszenie> lista) { this.lista = lista; }
	
	//I SETERY
	
	
	
}//koniec klasy dysk








































