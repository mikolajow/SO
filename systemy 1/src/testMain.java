import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class testMain {

	public static void main(String[] args) {
		
		/*
		Scanner sc = new Scanner(System.in);
		ArrayList<proces> doKol = new ArrayList<>();
		
		try {
			System.out.println("Wprowadz czas trwania potem czas wejœcia lub 'koniec' aby zakoñczyæ");
			while ( true ) {
				System.out.print("Wprowadz czas trwania ");
				int czasT = sc.nextInt();
				System.out.print("Wprowadz czas wejœcia ");
				int czasW = sc.nextInt();
				Random gen = new Random();
				proces nowy = new proces(gen.nextInt(200)+1,czasT,czasW);
				doKol.add(nowy);	
			}
		} catch (Exception e) {
			sc.close();
			System.out.println("koniec wpisywania danych");
		}
		
		for (proces p : doKol) {
		System.out.println(p);
		}
		
		kolejka kol1 = new kolejka(doKol);
		kolejka kol2 = new kolejka(kol1);
		kolejka kol3 = new kolejka(kol1);
		kolejka kol4 = new kolejka(kol1);
		
		*/
		
		
		kolejka kol1 = new kolejka(80);
		kolejka kol2 = new kolejka(kol1);
		kolejka kol3 = new kolejka(kol1);
		kolejka kol4 = new kolejka(kol1);
		
		/*
		System.out.println("Kolejka 1 nie posortowana");
		for ( proces p : kol1.getLista()) {
			System.out.println(p);
		}
		kol1.sortujPoCzasieWejscia();
		
		System.out.println("\n");
		
		kol1.sortujPoCzasieWejscia();
		System.out.println("Kolejka 1 posortowana");
		for ( proces p : kol1.getLista()) {
			System.out.println(p);
		}
		
		kol3.sortujPoCzasieWejscia();
		System.out.println("Kolejka 3 posortowana");
		for ( proces p : kol3.getLista()) {
			System.out.println(p);
		}
		
		System.out.println("\n");
		
		
		System.out.println("Kolejka 2 nie posortowana");
		for ( proces p : kol2.getLista()) {
			System.out.println(p);
		}
		System.out.println("\n");
		
		
		//kolejka kol1 = new kolejka();
		//kolejka kol2 = new kolejka(kol1);
		*/
		
		
		FCFS algFcfs = new FCFS(kol1);
		System.out.println("fcfs " + algFcfs.run());
		
		
		RR alg4 = new RR(kol4 , 300);
		System.out.println("RR " + alg4.run());
	
		
		SJFz algSJFz = new SJFz(kol2);
		System.out.println("SJFz " + algSJFz.run());
		
		SJFbez alg3 = new SJFbez(kol3);
		System.out.println("SJFbez " + alg3.run());
		

		
	}//koniec main
}
