package paliet_1;

import java.util.ArrayList;

public class Wyniki {
	
	private ArrayList<Integer> historiaObciazeniaProcesora;
	private int iloscZapytanOObciazenie;
	private int iloscPrzemieszczenProcesow;
	
	public Wyniki() {
		this.historiaObciazeniaProcesora = new ArrayList<>();
		this.iloscPrzemieszczenProcesow = 0;
		this.iloscZapytanOObciazenie = 0;
	}//koniec konstruktora
	
	
	
	//GETERY I SETTERY
	public ArrayList<Integer> getHistoriaObciazeniaProcesora() {
		return historiaObciazeniaProcesora;
	}

	public void setHistoriaObciazeniaProcesora(ArrayList<Integer> historiaObciazeniaProcesora) {
		this.historiaObciazeniaProcesora = historiaObciazeniaProcesora;
	}

	public int getIloscZapytanOObciazenie() {
		return iloscZapytanOObciazenie;
	}

	public void setIloscZapytanOObciazenie(int iloscZapytanOObciazenie) {
		this.iloscZapytanOObciazenie = iloscZapytanOObciazenie;
	}

	public int getIloscPrzemieszczenProcesow() {
		return iloscPrzemieszczenProcesow;
	}

	public void setIloscPrzemieszczenProcesow(int iloscPrzemieszczenProcesow) {
		this.iloscPrzemieszczenProcesow = iloscPrzemieszczenProcesow;
	}
	
	
	
}//koniec klasy



















