import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EDF {
	
	private ArrayList<zgloszenie> lista;
	
	public EDF( dysk d ) {
		d.generujPriorytetowe();
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run () {
		
		int przemieszczenia = 0;
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		ArrayList<zgloszenie> listaPriorytetowa = new ArrayList<>();
		
		//nie ma sensu "dosypywa�" zg�osze� bo tak czy siak by�yby wykonywane jako ostatnie
		//je�li dosypaliby�my zg�oszenie priorytetowe w czasie to nie wp�yne�oby to znacz�co na wynik jako �e g�owica i tak znajdowa�aby si� w nieprzewidywalnym miejscu
		
		for ( zgloszenie z : lista ) {
			if ( z.getPriorytet() == true ) {
				listaPriorytetowa.add(z);
				//System.out.println("dodano priorytet");
			}//koniec if
			else {
				listaAktualna.add(z);
			}//koniec else
		}//koniec for
		
		Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		int miejscePoczatkowe = listaPriorytetowa.get(0).getMiejsceNaDysku();		//zaczniemy od piewszego zgloszenia prorytetowego
		//gdyby sie okazalo ze jest tylko 1 priorytet to zaczniemy od tego miejsca przechodzic liste zwyk�ych zg�osze�
		
		for ( int i = 0; i < listaPriorytetowa.size()-1; i++ ) { // gdy wi�cej niz 1 priorytet
			przemieszczenia = przemieszczenia + Math.abs(listaPriorytetowa.get(i).getMiejsceNaDysku() - listaPriorytetowa.get(i + 1).getMiejsceNaDysku());
			miejscePoczatkowe = listaPriorytetowa.get(i+1).getMiejsceNaDysku();
		}//koniec for
		
		//System.out.println( "Przemieszczenia po priorytetach = " + przemieszczenia);
		
		//zwyk�y fcfs dla normalnych proces�w
		for ( zgloszenie z : listaAktualna ) {
			przemieszczenia = przemieszczenia + Math.abs(miejscePoczatkowe - z.getMiejsceNaDysku());
			miejscePoczatkowe = z.getMiejsceNaDysku();
		}//koniec for
		return przemieszczenia;
	}//koniec run	
}//koniec klasy




















