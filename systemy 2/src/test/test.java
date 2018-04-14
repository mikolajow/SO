package test;
import java.util.Scanner;

import algorytmy_podstawowe.*;
import algorytmy_z_edf.*;
import algorytmy_z_fd_scanem.*;

public class test {

	public static void main(String[] args) {
		
		int rozmiarDysku=0;
		int liczbaPowtorzen=0;
		
		
		int srednia = 0;
		
		try ( Scanner s = new Scanner(System.in) ){
			
			System.out.println("Wprowadz rozmiar dysku ");
			
			rozmiarDysku = s.nextInt();
			
			System.out.println("Wprowadz iloœæ powtórzeñ");
			
			liczbaPowtorzen = s.nextInt();
			
		}catch (Exception e) {
			System.out.println("NIEPOPRAWNE DANE");
		}
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			FCFS f = new FCFS(nowy);
			srednia = srednia + f.run();
		}
		System.out.println("Prezmieszczenia FCFS = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			SSTF ss = new SSTF(nowy);
			srednia = srednia + ss.run();
		}
		System.out.println( "Prezmieszczenia SSTF = " +  srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			SCAN scan = new SCAN(nowy);
			srednia = srednia + scan.run();
		}
		System.out.println( "Prezmieszczenia SCAN = " + srednia/liczbaPowtorzen);
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			C_SCAN C_scan = new C_SCAN(nowy);;
			srednia = srednia + C_scan.run();
		}
		System.out.println( "Prezmieszczenia C_SCAN = " + srednia/liczbaPowtorzen);
		srednia = 0;

		System.out.println();
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			fcfs_edf fcfsedf = new fcfs_edf(nowy);
			srednia = srednia + fcfsedf.run();
		}
		System.out.println( "Prezmieszczenia fcfs z edf = " + srednia/liczbaPowtorzen);
		srednia = 0;
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			sstf_edf sstfedf = new sstf_edf(nowy);
			srednia = srednia + sstfedf.run();
		}
		System.out.println( "Prezmieszczenia sstf z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			scan_edf scanedf = new scan_edf(nowy);
			srednia = srednia + scanedf.run();
		}
		System.out.println( "Prezmieszczenia scan z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			c_scan_edf cscanedf = new c_scan_edf(nowy);
			srednia = srednia + cscanedf.run();
		}
		System.out.println( "Prezmieszczenia c-scan z edf = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		System.out.println();
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			fcfc_z_fc_scan fcfsfdsan = new fcfc_z_fc_scan(nowy);
			srednia = srednia + fcfsfdsan.run();
		}
		System.out.println( "Prezmieszczenia fcfs z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			ssjf_z_fc_scan sstffdscan = new ssjf_z_fc_scan(nowy);
			srednia = srednia + sstffdscan.run();
		}
		System.out.println( "Prezmieszczenia sstf z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
			scan_z_fc_scan scanfdscan = new scan_z_fc_scan(nowy);
			srednia = srednia + scanfdscan.run();
		}
		System.out.println( "Prezmieszczenia scan z fd-scan = " + srednia/liczbaPowtorzen );
		srednia = 0;
		
		
		
		
		//
		for ( int i = 0; i <=liczbaPowtorzen; i++ ) {
			dysk nowy = new dysk(rozmiarDysku);
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






























