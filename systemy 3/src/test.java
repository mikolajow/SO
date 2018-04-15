import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		strona[] strony = new strona[8];
		
		for(int i = 0; i<strony.length; i++) {
			strony[i] = new strona(i);
		}//koniec for
		
		ArrayList<Integer> odwolania = new ArrayList<>();
		odwolania.add(7);
		odwolania.add(0);
		odwolania.add(1);
		odwolania.add(2);
		odwolania.add(0);
		odwolania.add(3);
		odwolania.add(0);
		odwolania.add(4);
		odwolania.add(2);
		odwolania.add(3);
		odwolania.add(0);
		odwolania.add(3);
		odwolania.add(2);
		odwolania.add(1);
		odwolania.add(2);
		odwolania.add(0);
		odwolania.add(1);
		odwolania.add(7);
		odwolania.add(0);
		odwolania.add(1);
		
		zalozenia test = new zalozenia(strony, 3, odwolania);
		zalozenia test1 = new zalozenia(strony, 3, odwolania);
		zalozenia test2 = new zalozenia(strony, 3, odwolania);
		zalozenia test3 = new zalozenia(strony, 3, odwolania);
		
		System.out.println("TESTY");
		
		FIFO fif = new FIFO(test);
		System.out.println("wynik FIFO = " + fif.run());
		
		OPT opt = new OPT(test1);
		System.out.println("wynik OPT = " + opt.run());
		
		RANDOM random = new RANDOM(test2);
		System.out.println("wynik RANDOM = " + random.run());
		
		LRU lru = new LRU(test3);
		System.out.println("wynik LRU = " + lru.run());
		
		System.out.println("KONIEC TESTÓW");
		System.out.println();
		*/
		
		
		zalozenia symulacja = new zalozenia(15, 5, 1000);
		zalozenia symulacja1 = new zalozenia(symulacja);
		zalozenia symulacja2 = new zalozenia(symulacja);
		zalozenia symulacja3 = new zalozenia(symulacja);
		zalozenia symulacja4 = new zalozenia(symulacja);
		
		/*
		for ( Integer s : symulacja.getOdwolania() ) {
			System.out.print( s + ",");
		}
		*/
		
		
		/*
		System.out.println("SYMULACJE");
		
		FIFO fif1 = new FIFO(symulacja);
		System.out.println("wynik FIFO = " + fif1.run());
		
		OPT opt1 = new OPT(symulacja1);
		System.out.println("wynik OPT = " + opt1.run());
		
		RANDOM random1 = new RANDOM(symulacja2);
		System.out.println("wynik RANDOM = " + random1.run());
		
		LRU lru1 = new LRU(symulacja3);
		System.out.println("wynik LRU = " + lru1.run());
		
		LRU_aproksymowany lruA = new LRU_aproksymowany(symulacja4);
		System.out.println("wynik drugiej szansy = " + lruA.run());
		
		
		*/
		
		int liczbaStron = 0;
		int liczbaRamek = 0;
		int liczbaOdwolan = 0;
		
		try ( Scanner s = new Scanner(System.in) ){
			System.out.println("Podaj Liczbê Stron");
			liczbaStron = s.nextInt();
			System.out.println("Podaj Liczbê Ramek");
			liczbaRamek = s.nextInt();
			System.out.println("Podaj Liczbê Odwo³añ");
			liczbaOdwolan = s.nextInt();
		} catch (Exception e) {
			System.out.println("Podano Z³e Dane");
		}
		
		zalozenia zal1 = new zalozenia(liczbaStron, liczbaRamek, liczbaOdwolan);
		zalozenia zal2 = new zalozenia(zal1);
		zalozenia zal3 = new zalozenia(zal1);
		zalozenia zal4 = new zalozenia(zal1);
		zalozenia zal5 = new zalozenia(zal1);
		
		
		System.out.println("SYMULACJE");
		
		FIFO fif12 = new FIFO(zal1);
		System.out.println("wynik FIFO = " + fif12.run());
		
		OPT opt12 = new OPT(zal2);
		System.out.println("wynik OPT = " + opt12.run());
		
		RANDOM random12 = new RANDOM(zal3);
		System.out.println("wynik RANDOM = " + random12.run());
		
		LRU lru12 = new LRU(zal4);
		System.out.println("wynik LRU = " + lru12.run());
		
		LRU_aproksymowany lruA2 = new LRU_aproksymowany(zal5);
		System.out.println("wynik drugiej szansy = " + lruA2.run());
		
		
		
		
		
		
	}//koniec main
}//koniec klasy































