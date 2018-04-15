package test;
import java.util.Scanner;

import algorytmy_podstawowe.*;
import algorytmy_z_edf.*;
import algorytmy_z_fd_scanem.*;

public class test {

	public static void main(String[] args) {
		
		int rozmiarDysku=0;
		int liczbaPowtorzen=0;
		
		System.out.println("Za³o¿enia symulacji : ");
		System.out.println("Liczba zg³oszeñ = oko³o 2/3 rozmiaru dysku");
		System.out.println("Priorytety stanowi¹ oko³o 5% zg³oszeñ priorytetowych");
		System.out.println();
		
		try ( Scanner s = new Scanner(System.in) ){
			
			System.out.println("Wprowadz rozmiar dysku ");
			
			rozmiarDysku = s.nextInt();
			
			System.out.println("Wprowadz iloœæ powtórzeñ");
			
			liczbaPowtorzen = s.nextInt();
			
		}catch (Exception e) {
			System.out.println("NIEPOPRAWNE DANE");
		}
		
		
		long sredniafcfs = 0;
		long sredniascan = 0;
		long sredniacscan = 0;
		long sredniasstf = 0;
		
		long sredniafcfse = 0;
		long sredniascane = 0;
		long sredniacscane = 0;
		long sredniasstfe = 0;
		
		long sredniafcfsf = 0;
		long sredniascanf = 0;
		long sredniacscanf = 0;
		long sredniasstff = 0;
		
		
		
		for (int i = 0; i<liczbaPowtorzen; i++) {
			
			dysk nowy = new dysk(rozmiarDysku);
			
			FCFS f = new FCFS(nowy);
			sredniafcfs = sredniafcfs + f.run();
			SSTF ss = new SSTF(nowy);
			sredniasstf = sredniasstf + ss.run();
			SCAN scan = new SCAN(nowy);
			sredniascan = sredniascan + scan.run();
			C_SCAN C_scan = new C_SCAN(nowy);;
			sredniacscan = sredniacscan + C_scan.run();
			
			fcfs_edf fcfsedf = new fcfs_edf(nowy);
			sredniafcfse = sredniafcfse + fcfsedf.run();
			sstf_edf sstfedf = new sstf_edf(nowy);
			sredniasstfe = sredniasstfe + sstfedf.run();
			scan_edf scanedf = new scan_edf(nowy);
			sredniascane = sredniascane + scanedf.run();
			c_scan_edf cscanedf = new c_scan_edf(nowy);
			sredniacscane = sredniacscane + cscanedf.run();
			
			fcfc_z_fc_scan fcfsfdsan = new fcfc_z_fc_scan(nowy);
			sredniafcfsf = sredniafcfsf + fcfsfdsan.run();
			ssjf_z_fc_scan sstffdscan = new ssjf_z_fc_scan(nowy);
			sredniasstff = sredniasstff + sstffdscan.run();
			scan_z_fc_scan scanfdscan = new scan_z_fc_scan(nowy);
			sredniascanf = sredniascanf + scanfdscan.run();
			c_scan_z_fc_scan cscanfdscan = new c_scan_z_fc_scan(nowy);
			sredniacscanf = sredniacscanf + cscanfdscan.run();
			
		}//koniec for
		
		System.out.println();
		System.out.println("Prezmieszczenia FCFS = " + sredniafcfs/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia SSTF = " +  sredniasstf/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia SCAN = " + sredniascan/liczbaPowtorzen);
		System.out.println( "Prezmieszczenia C_SCAN = " + sredniacscan/liczbaPowtorzen);
		
		System.out.println();
		System.out.println( "Prezmieszczenia fcfs z edf = " + sredniafcfse/liczbaPowtorzen);
		System.out.println( "Prezmieszczenia sstf z edf = " + sredniasstfe/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia scan z edf = " + sredniascane/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia c-scan z edf = " + sredniacscane/liczbaPowtorzen );
		
		System.out.println();
		System.out.println( "Prezmieszczenia fcfs z fd-scan = " + sredniafcfsf/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia sstf z fd-scan = " + sredniasstff/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia scan z fd-scan = " + sredniascanf/liczbaPowtorzen );
		System.out.println( "Prezmieszczenia c-scan z fd-scan = " + sredniacscanf/liczbaPowtorzen );
		
		
		/*
		dysk nowy = new dysk(rozmiarDysku);
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			FCFS f = new FCFS(nowy);
			srednia = srednia + f.run();
		}
		System.out.println("Prezmieszczenia FCFS = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			SSTF ss = new SSTF(nowy);
			srednia = srednia + ss.run();
		}
		System.out.println( "Prezmieszczenia SSTF = " +  srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			SCAN scan = new SCAN(nowy);
			srednia = srednia + scan.run();
		}
		System.out.println( "Prezmieszczenia SCAN = " + srednia/liczbaPowtorzen);
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			C_SCAN C_scan = new C_SCAN(nowy);;
			srednia = srednia + C_scan.run();
		}
		System.out.println( "Prezmieszczenia C_SCAN = " + srednia/liczbaPowtorzen);
		srednia = 0;

		System.out.println();
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			fcfs_edf fcfsedf = new fcfs_edf(nowy);
			srednia = srednia + fcfsedf.run();
		}
		System.out.println( "Prezmieszczenia fcfs z edf = " + srednia/liczbaPowtorzen);
		srednia = 0;
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			sstf_edf sstfedf = new sstf_edf(nowy);
			srednia = srednia + sstfedf.run();
		}
		System.out.println( "Prezmieszczenia sstf z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			scan_edf scanedf = new scan_edf(nowy);
			srednia = srednia + scanedf.run();
		}
		System.out.println( "Prezmieszczenia scan z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			c_scan_edf cscanedf = new c_scan_edf(nowy);
			srednia = srednia + cscanedf.run();
		}
		System.out.println( "Prezmieszczenia c-scan z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		System.out.println();
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			fcfc_z_fc_scan fcfsfdsan = new fcfc_z_fc_scan(nowy);
			srednia = srednia + fcfsfdsan.run();
		}
		System.out.println( "Prezmieszczenia fcfs z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			ssjf_z_fc_scan sstffdscan = new ssjf_z_fc_scan(nowy);
			srednia = srednia + sstffdscan.run();
		}
		System.out.println( "Prezmieszczenia sstf z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			scan_z_fc_scan scanfdscan = new scan_z_fc_scan(nowy);
			srednia = srednia + scanfdscan.run();
		}
		System.out.println( "Prezmieszczenia scan z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			c_scan_z_fc_scan cscanfdscan = new c_scan_z_fc_scan(nowy);
			srednia = srednia + cscanfdscan.run();
		}
		System.out.println( "Prezmieszczenia c-scan z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		
		//TESTY
		/*
		dysk nowy = new dysk(99);
		
		FCFS f = new FCFS(nowy);
		System.out.println("Prezmieszczenia FCFS = " + f.run());
		
		SSTF ss = new SSTF(nowy);
		System.out.println( "Prezmieszczenia SSTF = " + ss.run());
		
		SCAN scan = new SCAN(nowy);
		System.out.println( "Prezmieszczenia SCAN = " + scan.run());
		
		C_SCAN C_scan = new C_SCAN(nowy);
		System.out.println( "Prezmieszczenia C_SCAN = " + C_scan.run());
		
		
		
		
		System.out.println();
		
		// ALGORYTMY Z EDF
		fcfs_edf fcfsedf = new fcfs_edf(nowy);
		System.out.println( "Prezmieszczenia fcfs z edf = " + fcfsedf.run());
		
		sstf_edf sstfedf = new sstf_edf(nowy);
		System.out.println( "Prezmieszczenia sstf z edf = " + sstfedf.run() );
		
		scan_edf scanedf = new scan_edf(nowy);
		System.out.println( "Prezmieszczenia scan z edf = " + scanedf.run() );
		
		c_scan_edf cscanedf = new c_scan_edf(nowy);
		System.out.println( "Prezmieszczenia c-scan z edf = " + cscanedf.run() );
		
		
		System.out.println();
		
		
		
		
		// ALGORYTMY Z FD-SCANEM
		fcfc_z_fc_scan fcfsfdsan = new fcfc_z_fc_scan(nowy);
		System.out.println( "Prezmieszczenia fcfs z fd-scan = " + fcfsfdsan.run());
		
		ssjf_z_fc_scan sstffdscan = new ssjf_z_fc_scan(nowy);
		System.out.println( "Prezmieszczenia sstf z fd-scan = " + sstffdscan.run() );
		
		scan_z_fc_scan scanfdscan = new scan_z_fc_scan(nowy);
		System.out.println( "Prezmieszczenia scan z fd-scan = " + scanfdscan.run() );
		
		c_scan_z_fc_scan cscanfdscan = new c_scan_z_fc_scan(nowy);
		System.out.println( "Prezmieszczenia c-scan z fd-scan = " + cscanfdscan.run() );
		
		*/
		
		
		
		
		
		
		
		
	}//koniec main
}//koniec klasy test






























