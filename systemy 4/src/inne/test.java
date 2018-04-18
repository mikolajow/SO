package inne;

import java.util.ArrayList;

import sposoby.*;

public class test {

	public static void main(String[] args) {
		
		
		
		// TESTY CIAGU GLOBALNEGO
		
		
		proces p1 = new proces(0,  3, 6);
		proces p2 = new proces(3, 3, 6);
		
		ArrayList<proces> lista = new ArrayList<>();
		lista.add(p1);
		lista.add(p2);
		
		
		ciagGlobalny c = new ciagGlobalny(lista);
		
		System.out.println();
		
		System.out.println(p1);
		for ( Integer i : p1.getOdwolania() ) {
			System.out.print(i + ",");
		}
		
		System.out.println();
		
		System.out.println(p2);
		for ( Integer i : p2.getOdwolania() ) {
			System.out.print(i + ",");
		}
		
		System.out.println();
		System.out.println("ciag odwolan");
		System.out.println();
		
		for ( Integer i : c.getListaWszystkichOdwolan() ) {
			System.out.print(i + ",");
		}
		
		/*
		//test równego przydzia³u ramek
		rowny r = new rowny(c, 4);
		int[] wyniki = r.run();
		System.out.println();
		for( int i=0; i<wyniki.length;i++ ) {
			System.out.println(wyniki[i]);
		}//koniec for
		*/
		
		/*
		//test proporcjonalnego przydzia³u
		proporcjonalny pro = new proporcjonalny(c, 4);
		int[] wyniki = pro.run();
		System.out.println();
		for( int i=0; i<wyniki.length;i++ ) {
			System.out.println(wyniki[i]);
		}//koniec for
		*/
		
		
		
		
		
		
		
		
	}//koniec main
}//koniec klasy


























