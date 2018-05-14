package paliet_1;

import java.util.ArrayList;


public class SrodowiskoOperacyjne {
	
	private  ArrayList<Procesor> listaWszystkichProcesorow;
	private  int ileNowychProcesow = 450;		//ile procesow pojawi sie jeszcze na procesorach
	private boolean czyWszystkieProcesoryWolne;
	
	
	public SrodowiskoOperacyjne() {
		this.czyWszystkieProcesoryWolne = false;
		this.listaWszystkichProcesorow = new ArrayList<>();
		for (int i = 0; i < Procesor.getIloscProcesorowWSystemie(); i++) {
			listaWszystkichProcesorow.add(new Procesor());
		}//koniec for
	}//koniec konstruktora
	
	
	
	public void czyWszystkieProcesoryWolne() {
		int ileProcesorowWolnych = 0;
		for (Procesor p : listaWszystkichProcesorow) {
			if ( p.getAktualneObciazenie() == 0 ) {
				ileProcesorowWolnych++;
			}//koniec if
			if ( ileProcesorowWolnych == Procesor.getIloscProcesorowWSystemie() ) {
				czyWszystkieProcesoryWolne = true;
			}//koniec if
		}//koniec for
	}//koniec czy
	
	
	//MIEJSCE NA STRATEGIE ZARZADZANIA PROCESAMI 
	//plus metoda bedzie redukowac dliugosc potrzebnych cykli do wykonania procesu
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//GETTERS AND SETTERS
	public ArrayList<Procesor> getListaWszystkichProcesorow() {
		return listaWszystkichProcesorow;
	}

	public void setListaWszystkichProcesorow(ArrayList<Procesor> listaWszystkichProcesorow) {
		this.listaWszystkichProcesorow = listaWszystkichProcesorow;
	}

	public int getIleNowychProcesow() {
		return ileNowychProcesow;
	}
	public void setIleNowychProcesow(int ileNowychProcesow) {
		this.ileNowychProcesow = ileNowychProcesow;
	}
	public boolean getCzyWszystkieProcesoryWolne() {
		return czyWszystkieProcesoryWolne;
	}
	public void setCzyWszystkieProcesoryWolne(boolean czyWszystkieProcesoryWolne) {
		this.czyWszystkieProcesoryWolne = czyWszystkieProcesoryWolne;
	}
}//koniec klasy
























