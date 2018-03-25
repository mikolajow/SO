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
		
		FIFO fif = new FIFO(test);
		System.out.println(fif.run());
		
		
		
		
		
		
	}//koniec main
}//koniec klasy































