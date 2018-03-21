
public class test {

	public static void main(String[] args) {
		
		
		dysk nowy = new dysk(9);
		
		//for (zgloszenie z : nowy.getLista()) {
			//System.out.println(z);
		//}
		
		//FCFS f = new FCFS(nowy);
		//System.out.println(f.run());
		
		//SSTF ss = new SSTF(nowy);
		//System.out.println( "Prezmieszczenia = " + ss.run());
		
		SCAN scan = new SCAN(nowy);
		System.out.println( "Prezmieszczenia = " + scan.run());
		
	}//koniec main
}//koniec klasy test






























