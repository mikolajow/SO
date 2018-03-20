
public class test {

	public static void main(String[] args) {
		
		
		dysk nowy = new dysk(3);
		
		for (zgloszenie z : nowy.getLista()) {
			System.out.println(z);
		}
		
		FCFS f = new FCFS(nowy);
		System.out.println(f.run());
		
			
		
		
		
	}//koniec main
}//koniec klasy test






























