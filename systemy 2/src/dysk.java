import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	
	public void sortujPoCzasieWejscia() {
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia

			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
	}//koniec sortuj po czasie wejscia
	
	
	public void sortujPoMiejscuNaDysku() {
		Collections.sort(lista, new Comparator<zgloszenie>() {	//sortuje po miejscu na dysku

			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getMiejsceNaDysku() - o2.getMiejsceNaDysku();
			}//koniec compare
		});//koniec klasy zagniezdzonej
	}//koniec sortuj po miejscu na dysku
	
	
}//koniec klasy dysk








































