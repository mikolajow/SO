package paliet_1;

public class Runner {
	
	private SrodowiskoOperacyjne so1 = new SrodowiskoOperacyjne();
	private SrodowiskoOperacyjne so2 = new SrodowiskoOperacyjne();
	private SrodowiskoOperacyjne so3 = new SrodowiskoOperacyjne(); 
	
	public Runner() {
	}//koniec konstruktora
	

	public void run() {
		
		
		while( !so1.getCzyWszystkieProcesoryWolne() && !so2.getCzyWszystkieProcesoryWolne() && !so3.getCzyWszystkieProcesoryWolne() ){
			
			dodajProcesy();
			
			/*
			so1.metoda1
			so2.metoda2
			so3.metoda3
			
			usun skoncone procesy
			aktualizuj wyniki
			
			*/
			
		}//koniec while
		
		//wyswietl wyniki dla so1 so2 so3
		//plus obliczenia
		
	}//koniec run
	
	
	
	private void dodajProcesy() {
		for( int i = 0; i < so1.getListaWszystkichProcesorow().size(); i++ ) {
			double prawdopodobienstwoWystapieniaNowegoProcesu = 100*Math.random();
			if ( prawdopodobienstwoWystapieniaNowegoProcesu < 15 && so1.getIleNowychProcesow() > 0) {
				Proces nowy1 = new Proces();
				Proces nowy2 = new Proces(nowy1);
				Proces nowy3 = new Proces(nowy1);
				
				so1.getListaWszystkichProcesorow().get(i).getListaProcesow().add(nowy1);
				so1.getListaWszystkichProcesorow().get(i).getListaProcesow().add(nowy2);
				so1.getListaWszystkichProcesorow().get(i).getListaProcesow().add(nowy3);
				
				so1.setIleNowychProcesow(so1.getIleNowychProcesow() - 1);
			}//konniec if
		}//koniec for
	}//koniec dodaj procesy
	
}//koniec klasy






















