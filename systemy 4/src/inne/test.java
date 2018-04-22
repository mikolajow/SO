package inne;

import java.util.ArrayList;
import java.util.Scanner;

import sposoby.*;

public class test {

	public static void main(String[] args) {
		
		
		proces p1 = new  proces(500,  10,  1000  );
		proces p8 = new  proces(1000, 10,  1000  );
		proces p7 = new  proces(1500, 20,  1000 );
		proces p2 = new  proces(2000, 20,  1000  );
		proces p3 = new  proces(2500, 40,  1000  );
		proces p4 = new  proces(3000, 100, 1000  );
		proces p9 = new  proces(3500, 150, 1000  );
		proces p5 = new  proces(4000, 200, 1000 );
		proces p6 = new  proces(4500, 250, 1000  );
		proces p10 = new proces(5000, 400, 1000  );
		
		
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
		
		ciagGlobalny c1 = new ciagGlobalny(lista);
		ciagGlobalny c2 = new ciagGlobalny(lista);
		ciagGlobalny c3 = new ciagGlobalny(lista);
		ciagGlobalny c4 = new ciagGlobalny(lista);
		
		
		//test sterowania czêstoœci¹
		System.out.println("sterowanie czestoœci¹");
		SterowanieCzestoscia czestosc = new SterowanieCzestoscia(c4, 69);
		int[] wyniki3 = czestosc.run();
		for( int i=0; i<wyniki3.length;i++ ) {
			System.out.println( "bledy procesu o numerze " + i + " równe s¹ = " + wyniki3[i]);
		}//koniec for
		
		System.out.println();
		//test strefowego przydzia³u
		System.out.println("strefowy");
		strefowy stref = new strefowy(c3, 69);
		int[] wyniki2 = stref.run();
		for( int i=0; i<wyniki2.length;i++ ) {
			System.out.println( "bledy procesu o numerze " + i + " równe s¹ = " + wyniki2[i]);
		}//koniec for
		
		
		System.out.println();
		//test proporcjonalnego przydzia³u
		System.out.println("proporcjonalny");
		proporcjonalny pro = new proporcjonalny(c2, 69);
		int[] wyniki1 = pro.run();
		for( int i=0; i<wyniki1.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wyniki1[i]);
		}//koniec for
		
		
		System.out.println();
		//test równego przydzia³u ramek
		System.out.println("rowny");
		rowny r = new rowny(c1, 69);
		int[] wyniki = r.run();
		for( int i=0; i<wyniki.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wyniki[i]);
		}//koniec for
		
		
		
		
		// DANE WCZYTYWANE
		
		
		
		
		/*
		int liczbaProcesów = 0;
		int liczbaStron = 0;
		int liczbaOdwolan = 0;
		int liczbaPowtorzen = 0;
		ArrayList<proces> lista = new ArrayList<>();
		int numerPierwszejStrony = 0;
		int liczbaRamek = 0;
		
		try ( Scanner s = new Scanner(System.in) ){
			System.out.println("Podaj Liczbê Powtórzeñ");
			liczbaPowtorzen = s.nextInt();
			System.out.println("Podaj Liczbê Ramek");
			liczbaRamek = s.nextInt();
			System.out.println("Podaj Liczbê Procesów");
			liczbaProcesów = s.nextInt();
			System.out.println("Podaj Liczbê Odwo³añ");
			liczbaOdwolan = s.nextInt();
			for ( int i = 0; i < liczbaProcesów; i++ ) {
				System.out.println("Podaj Liczbê Stron dla procesu o numerze = " + i );
				liczbaStron = s.nextInt();
				lista.add(new proces(numerPierwszejStrony, liczbaStron, liczbaOdwolan));
				numerPierwszejStrony = numerPierwszejStrony + liczbaStron + 100;
			}//koniec for
		}catch (Exception e) {
			System.out.println("podano z³e wartoœci");
		}//koniec catch
		
		
		int[] wynikiRownego = new int[liczbaProcesów + 1];
		int[] wynikiProporcjonalnego = new int[liczbaProcesów + 1];
		int[] wynikiSterCzestoscia = new int[liczbaProcesów + 1];
		int[] wynikiStrefowego = new int[liczbaProcesów + 1];
		
		
		for ( int banan = 0; banan < liczbaPowtorzen; banan++ ) {
			ciagGlobalny c1 = new ciagGlobalny(lista);
			ciagGlobalny c2 = new ciagGlobalny(lista);
			ciagGlobalny c3 = new ciagGlobalny(lista);
			ciagGlobalny c4 = new ciagGlobalny(lista);
			rowny row = new rowny(c1, liczbaRamek);
			proporcjonalny prop = new proporcjonalny(c2, liczbaRamek);
			strefowy stref = new strefowy(c3, liczbaRamek);
			SterowanieCzestoscia czest = new SterowanieCzestoscia(c4, liczbaRamek);
			
			int[] wynikiRownego2 = row.run();
			int[] wynikiProporcjonalnego2 = prop.run();
			int[] wynikiSterCzestoscia2 = czest.run();
			int[] wynikiStrefowego2 = stref.run();
			
			for ( int g = 0; g < wynikiRownego.length; g++ ) {
				wynikiRownego[g] = wynikiRownego[g] + wynikiRownego2[g]; 
			}//koniec for
			for ( int g = 0; g < wynikiProporcjonalnego.length; g++ ) {
				wynikiProporcjonalnego[g] = wynikiProporcjonalnego[g] + wynikiProporcjonalnego2[g]; 
			}//koniec for
			for ( int g = 0; g < wynikiSterCzestoscia.length; g++ ) {
				wynikiSterCzestoscia[g] = wynikiSterCzestoscia[g] + wynikiSterCzestoscia2[g]; 
			}//koniec for
			for ( int g = 0; g < wynikiStrefowego.length; g++ ) {
				wynikiStrefowego[g] = wynikiStrefowego[g] + wynikiStrefowego2[g]; 
			}//koniec for
		}//koniec for
		
		
		for ( int i = 0; i < wynikiRownego.length; i++ ) {
			wynikiRownego[i] = wynikiRownego[i] / liczbaPowtorzen;
			wynikiProporcjonalnego[i] = wynikiProporcjonalnego[i] / liczbaPowtorzen;
			wynikiSterCzestoscia[i] = wynikiSterCzestoscia[i] / liczbaPowtorzen;
			wynikiStrefowego[i] = wynikiStrefowego[i] / liczbaPowtorzen;
		}//koniec for
		
		
		System.out.println();
		System.out.println("rowny");
		for( int i=0; i< wynikiRownego.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wynikiRownego[i]);
		}//koniec for
		
		
		System.out.println();
		System.out.println("proporcjonalny");
		for( int i=0; i< wynikiProporcjonalnego.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wynikiProporcjonalnego[i]);
		}//koniec for
		
		System.out.println();
		System.out.println("strefowy");
		for( int i=0; i< wynikiStrefowego.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wynikiStrefowego[i]);
		}//koniec for
		
		System.out.println();
		System.out.println("Sterowanie Czêstoœci¹");
		for( int i=0; i< wynikiSterCzestoscia.length;i++ ) {
			System.out.println("bledy procesu o numerze " + i + " równe s¹ = " + wynikiSterCzestoscia[i]);
		}//koniec for
		*/
		
		
		
		
		
		
	}//koniec main
}//koniec klasy


























