package paliet_1;

public class Runner {
	
	private SrodowiskoOperacyjne so1 = new SrodowiskoOperacyjne();
	private SrodowiskoOperacyjne so2 = new SrodowiskoOperacyjne();
	private SrodowiskoOperacyjne so3 = new SrodowiskoOperacyjne(); 
	
	public Runner() {
	}//koniec konstruktora
	

	public void run() {
		
		
		while( !so1.getCzyWszystkieProcesoryWolne() && !so2.getCzyWszystkieProcesoryWolne()  && !so3.getCzyWszystkieProcesoryWolne() ){
			
			dodajProcesy(); //dodaje te same procesy na te same procesory w so1,2,3 tak zeby kazda strategia miala te sama sytuacje
			
			//wykonaj inna strategie dla kazdego srodowiska operacyjnego
			so1.metoda1();
			so2.metoda2();
			so3.metoda3();
			
			
			so1.redukujIloscCykliProcesowDoZakonczenia();
			so2.redukujIloscCykliProcesowDoZakonczenia();
			so3.redukujIloscCykliProcesowDoZakonczenia();
			
			//usun skonczone procesy
			usunWykonaneProcesy(so1);
			usunWykonaneProcesy(so2);
			usunWykonaneProcesy(so3);
			
			
			//aktualizuj wyniki
			aktualizujHistorieObiazeniaProcesorow(so1);
			aktualizujHistorieObiazeniaProcesorow(so2);
			aktualizujHistorieObiazeniaProcesorow(so3);
			
		}//koniec while
		
		
		//obliczenia
		double srednieObciazenieDlaSo1 = liczSrednieObciazenieProcesorow(so1);
		double srednieObciazenieDlaSo2 = liczSrednieObciazenieProcesorow(so2);
		double srednieObciazenieDlaSo3 = liczSrednieObciazenieProcesorow(so3);
		
		//wyswietl wyniki dla so1 so2 so3
		System.out.println("Srenide obcioazenie dla so1 = " + srednieObciazenieDlaSo1);
		wyswietlLiczbeZapytanIPrzemieszczen(so1);
		wyswietlSrednieOdchylenieStandardowe(so1, srednieObciazenieDlaSo1);
		System.out.println();
		
		System.out.println("Srenide obcioazenie dla so2 = " + srednieObciazenieDlaSo2);
		wyswietlLiczbeZapytanIPrzemieszczen(so2);
		wyswietlSrednieOdchylenieStandardowe(so2, srednieObciazenieDlaSo2);
		System.out.println();

		System.out.println("Srenide obcioazenie dla so3 = " + srednieObciazenieDlaSo3);
		wyswietlLiczbeZapytanIPrzemieszczen(so3);
		wyswietlSrednieOdchylenieStandardowe(so3 , srednieObciazenieDlaSo3);
				
	}//koniec run
	
	
	
	private void dodajProcesy() {
		for( int i = 0; i < so1.getListaWszystkichProcesorow().size(); i++ ) {
			double prawdopodobienstwoWystapieniaNowegoProcesu = 100*Math.random();
			if ( prawdopodobienstwoWystapieniaNowegoProcesu < 15 && so1.getIleNowychProcesow() > 0) {
				Proces nowy1 = new Proces();
				Proces nowy2 = new Proces(nowy1);
				Proces nowy3 = new Proces(nowy1);
				
				so1.getListaWszystkichProcesorow().get(i).setNowoDodany(nowy1);
				so2.getListaWszystkichProcesorow().get(i).setNowoDodany(nowy2);
				so3.getListaWszystkichProcesorow().get(i).setNowoDodany(nowy3);
				
				so1.setIleNowychProcesow(so1.getIleNowychProcesow() - 1);
			}//konniec if
		}//koniec for
	}//koniec dodaj procesy
	
	private void usunWykonaneProcesy( SrodowiskoOperacyjne s ) {
		for ( Procesor p : s.getListaWszystkichProcesorow() ) {
			p.usunWykonaneProcesy();
			s.czyWszystkieProcesoryWolne();
		}//koniec for
	}//koniec usun wykonane
	
	private void aktualizujHistorieObiazeniaProcesorow( SrodowiskoOperacyjne so) {
		for ( Procesor p : so.getListaWszystkichProcesorow() ) {
			p.aktualizujHistorieObciazeniaProcesora();
		}//koniec for
	}//koniec aktualizuj wyniki
	
	
	private double liczSrednieObciazenieProcesorow(SrodowiskoOperacyjne so) {
		
		double srednieObcizenieWszystkichProcesorow = 0;
		
		for ( Procesor p : so.getListaWszystkichProcesorow() ) {
			double srednieObciazenieJednegoProcesora = 0;
			
			for( Integer i : p.getWyniki().getHistoriaObciazeniaProcesora() ) {
				srednieObciazenieJednegoProcesora = srednieObciazenieJednegoProcesora + i;
			}//koniec for
			srednieObciazenieJednegoProcesora = srednieObciazenieJednegoProcesora/p.getWyniki().getHistoriaObciazeniaProcesora().size();
			srednieObcizenieWszystkichProcesorow = srednieObcizenieWszystkichProcesorow + srednieObciazenieJednegoProcesora;
		}//koniec for
		
		return srednieObcizenieWszystkichProcesorow/so.getListaWszystkichProcesorow().size();
		
	}//koniec licz srednie obciazenie procesorow
	
	
	private void wyswietlLiczbeZapytanIPrzemieszczen(SrodowiskoOperacyjne so) {
		int liczbaZapytan = 0;
		int liczbaPrzemieszczen = 0;
		
		for ( Procesor p : so.getListaWszystkichProcesorow() ) {
			liczbaZapytan = liczbaZapytan + p.getWyniki().getIloscZapytanOObciazenie();
			liczbaPrzemieszczen = liczbaPrzemieszczen + p.getWyniki().getIloscPrzemieszczenProcesow();
		}//koniec for
		
		System.out.println("Liczba Zapytan = " + liczbaZapytan + "	Liczba Przemieszczen Procesow = " + liczbaPrzemieszczen );
		
	}//koniec wyswietl
	
	
	
	private void wyswietlSrednieOdchylenieStandardowe(SrodowiskoOperacyjne so , double srednia) {
		
		double srednieOdchylenieStandardowe = 0;
		double sumaOdchylen = 0;
		
		for ( Procesor p : so.getListaWszystkichProcesorow() ) {
			double srednieObciazenie = 0;
			
			for (Integer i : p.getWyniki().getHistoriaObciazeniaProcesora()) {
				srednieObciazenie = srednieObciazenie + i;
			}//koniec for
			
			srednieObciazenie = srednieObciazenie/p.getWyniki().getHistoriaObciazeniaProcesora().size();
			sumaOdchylen = sumaOdchylen + Math.pow(srednieObciazenie - srednia, 2);
			
		}//koniec for
		
		srednieOdchylenieStandardowe = Math.sqrt( sumaOdchylen/so.getListaWszystkichProcesorow().size() );
		System.out.println( "Srednie odchylenie Standardowe = " + srednieOdchylenieStandardowe);
	}//koniec wyswietl srednie odchylenie standardowe
	
	
}//koniec klasy






















