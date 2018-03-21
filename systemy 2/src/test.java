
public class test {

	public static void main(String[] args) {
		
		
		dysk nowy = new dysk(99);
		
		//for (zgloszenie z : nowy.getLista()) {
			//System.out.println(z);
		//}
		
		
		FCFS f = new FCFS(nowy);
		System.out.println("Prezmieszczenia FCFS = " + f.run());
		
		SSTF ss = new SSTF(nowy);
		System.out.println( "Prezmieszczenia SSTF = " + ss.run());
		
		SCAN scan = new SCAN(nowy);
		System.out.println( "Prezmieszczenia SCAN = " + scan.run());
		
		C_SCAN C_scan = new C_SCAN(nowy);
		System.out.println( "Prezmieszczenia C_SCAN = " + C_scan.run());
		
		
		EDF edf = new EDF(nowy);
		System.out.println( "Prezmieszczenia EDF = " + edf.run());
		
		
		FD_SCAN fd_scan = new FD_SCAN(nowy);
		System.out.println( "Prezmieszczenia FD-SCAN = " + fd_scan.run());
		
		
	}//koniec main
}//koniec klasy test






























