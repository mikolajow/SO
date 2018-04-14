package algorytmy_z_fd_scanem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.dysk;
import test.zgloszenie;

public class fcfc_z_fc_scan {

	private ArrayList<zgloszenie> lista;
	
	public fcfc_z_fc_scan( dysk d ) {
		this.lista = d.getLista();
	}//koniec konstruktora
	
	
	public int run () {
		
		int przemieszczenie = 0;
		
		ArrayList<zgloszenie> listaAktualna = new ArrayList<>();
		ArrayList<zgloszenie> listaPriorytetowa = new ArrayList<>();
		
		
		//int aktualnaPozycja;
		
		for ( zgloszenie z : lista ) {
			listaAktualna.add(z);
		}
		//System.out.println("rozmiar aktualnej listy z fcfs przed fd-scanem = "+ listaAktualna.size() );
		//fd-scan
		
		
		//dodaje priorytety
		for ( int z = 0 ; z < listaAktualna.size(); z++ ) {
			
			if ( listaAktualna.get(z).getPriorytet() ) {
				listaPriorytetowa.add(listaAktualna.get(z));
				listaAktualna.remove(z);
				z--;
			}//koniec if
		}//koniec for
		
		int ostatniePolozenie= listaPriorytetowa.get(0).getMiejsceNaDysku() ;
		
		//sort po czsie wejscia, najstarsze priorytety na pocz¹tku
		Collections.sort(listaPriorytetowa, new Comparator<zgloszenie>() {
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				// TODO Auto-generated method stub
				return o1.getCzasWejscia()-o2.getCzasWejscia();
			}//koniec compare to
		});
		
		for ( int z = 0; z < listaPriorytetowa.size()-1; z++ ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - listaPriorytetowa.get(z).getMiejsceNaDysku());
			
			
			//usówam wszystkie zgloszenia zwyk³e, które sa pomiêdzy priorytetowymi
			for ( int i = 0; i <listaAktualna.size()-1; i++ ) {
				if ( ostatniePolozenie < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
					if ( listaAktualna.get(i).getMiejsceNaDysku() > ostatniePolozenie && listaAktualna.get(i).getMiejsceNaDysku() < listaPriorytetowa.get(z).getMiejsceNaDysku()) {
						listaAktualna.remove(i);
						i--;
						//System.out.println("usuniêto");
					}//koniec if wewnetrznego
				}//koniec if
				else {
					if ( listaAktualna.get(i).getMiejsceNaDysku() < ostatniePolozenie && listaAktualna.get(i).getMiejsceNaDysku() > listaPriorytetowa.get(z).getMiejsceNaDysku()) {
						listaAktualna.remove(i);
						//System.out.println("usuniêto");
						i--;
					}//koniec if wewnetrznego
				}//koniec else ( aktualna pozycja jest mniejsza niz miejsce w którym jest z
			}//koniec for
			
			
			
			ostatniePolozenie = listaPriorytetowa.get(z).getMiejsceNaDysku();
			listaPriorytetowa.remove(z);
			z--;
		}//koniec for
			
		
		//zwyk³y fcfs
		//System.out.println("rozmiar aktualnej listy z fcfs po fd-scanie = "+ listaAktualna.size() + "\n");
		
		
		Collections.sort(listaAktualna, new Comparator<zgloszenie>() {	//sortuje po czasie wejscia
			@Override
			public int compare(zgloszenie o1, zgloszenie o2) {
				return o1.getCzasWejscia() - o2.getCzasWejscia();
			}//koniec compare
		});//koniec klasy zagniezdzonej
		
		
		for ( zgloszenie z : listaAktualna ) {
			przemieszczenie = przemieszczenie + Math.abs(ostatniePolozenie - z.getMiejsceNaDysku());
			ostatniePolozenie = z.getMiejsceNaDysku();
		}//koniec for
		
		return przemieszczenie;
	}//koniec run
	//for (zgloszenie z : lista) {
	//	System.out.println(z);
	//}
}//koniec klasy






























