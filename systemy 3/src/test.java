import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		zalozenia test4 = new zalozenia(strony, 3, odwolania);
		
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
		
		zalozenia symulacja = new zalozenia(15, 3, 1000);
		zalozenia symulacja1 = new zalozenia(15, 3, 1000);
		zalozenia symulacja2 = new zalozenia(15, 3, 1000);
		zalozenia symulacja3 = new zalozenia(15, 3, 1000);
		
		System.out.println("SYMULACJE");
		
		FIFO fif1 = new FIFO(symulacja);
		System.out.println("wynik FIFO = " + fif1.run());
		
		OPT opt1 = new OPT(symulacja1);
		System.out.println("wynik OPT = " + opt1.run());
		
		RANDOM random1 = new RANDOM(symulacja2);
		System.out.println("wynik RANDOM = " + random1.run());
		
		LRU lru1 = new LRU(symulacja3);
		System.out.println("wynik LRU = " + lru1.run());
		
		
	}//koniec main
}//koniec klasy































