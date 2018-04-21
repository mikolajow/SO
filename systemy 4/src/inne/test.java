package inne;

import java.util.ArrayList;

import sposoby.*;

public class test {

	public static void main(String[] args) {
		
		
		
		// TESTY CIAGU GLOBALNEGO
		
		
		proces p1 = new proces(0,  10, 1000);
		proces p8 = new proces(20, 20, 1000);
		proces p7 = new proces(50, 20, 1000);
		proces p2 = new proces(100, 30, 1000);
		proces p3 = new proces(180, 55, 1000);
		proces p4 = new proces(300, 60, 1000);
		proces p9 = new proces(400, 70, 1000);
		proces p5 = new proces(500, 100, 1000);
		proces p6 = new proces(800, 150, 1000);
		proces p10 = new proces(1500, 300, 1000);
		
		
		ArrayList<proces> lista = new ArrayList<>();
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		lista.add(p4);
		lista.add(p5);
		lista.add(p6);
		lista.add(p7);
		lista.add(p8);
		lista.add(p9);
		lista.add(p10);
		
		ciagGlobalny c = new ciagGlobalny(lista);
		ciagGlobalny c2 = new ciagGlobalny(lista);
		ciagGlobalny c3 = new ciagGlobalny(lista);
		
		/*
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
		*/
		
		//test równego przydzia³u ramek
		System.out.println("rowny");
		rowny r = new rowny(c, 29);
		int[] wyniki = r.run();
		System.out.println();
		for( int i=0; i<wyniki.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wyniki[i]);
		}//koniec for
		
		
		
		//test proporcjonalnego przydzia³u
		System.out.println("proporcjonalny");
		proporcjonalny pro = new proporcjonalny(c2, 29);
		int[] wyniki1 = pro.run();
		System.out.println();
		for( int i=0; i<wyniki1.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wyniki1[i]);
		}//koniec for
		
		
		
		//test strefowego przydzia³u
		System.out.println("strefowy");
		strefowy stref = new strefowy(c3, 29);
		int[] wyniki2 = stref.run();
		System.out.println();
		for( int i=0; i<wyniki2.length;i++ ) {
			System.out.println( "bledy procesu o numerze " + i + " równe s¹ = " + wyniki2[i]);
		}//koniec for
		
		
		
		
		
		
		
		
		
		
		
		
	}//koniec main
}//koniec klasy


























