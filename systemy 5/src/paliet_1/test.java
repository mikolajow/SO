package paliet_1;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		/*
		try ( Scanner s = new Scanner(System.in)){
			int temp = 0;
			
			System.out.println( "Ile procesor�w jest w systemie? " );
			temp = s.nextInt();
			Procesor.setIloscProcesorowWSystemie(temp);
			
			System.out.println( "G�rny pr�g obci��enia Procesora (0-100) " );
			temp = s.nextInt();
			Procesor.setMaxymalneObciazenie(temp);
			
			System.out.println( "Dolny pr�g obci��enia Procesora " );
			temp = s.nextInt();
			Procesor.setMinimalneObciazenie(temp);
			
			System.out.println( "Ile razy losujemy inny procesor w strategii 1? " );
			temp = s.nextInt();
			Procesor.setIleRazyLosujemyInnyProcesor(temp);
			
			System.out.println( "Ile procesor�w pytamy o ich aktualne obci��enie w strategii 3? " );
			temp = s.nextInt();
			Procesor.setIleProcesorowPytamyOAktualneObciazenie(temp);
			
			System.out.println( "Ile proces�w ��cznie znajdzie si� w systemie? " );
			temp = s.nextInt();
			SrodowiskoOperacyjne.setIleNowychProcesow(temp);
			
		} catch ( Exception e ) {
			System.out.println( "Podano z�e warto�ci" );
		}//koniec try catch
		*/
		
		
		Runner r  = new Runner();
		
		r.run();
		
		
	}//koniec main
	
}//koniec klasy
























