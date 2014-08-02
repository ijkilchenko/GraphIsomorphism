package com.gi.exp;

import org.junit.Assert;
import org.junit.Test;

public class DegreeSequenceTest {
	
	double prob = (double)1/2;
	
	//@Test
	public void pTest() {
		
		//double p1 = DegreeSequence.p(3, 3, prob);
		//System.out.println(p1);
		
		for (int i = 1; i <= 100; i++) {
			double totalP = 0;
			for (int j = 0; j < i; j++) {
				totalP += DegreeSequence.p(j, i, prob);
			}
			Assert.assertTrue(totalP > 0.999 && totalP < 1.001);
		}
		
	}
	
	//@Test
	public void G_0Test() {
		
		for (int i = 1; i <= 1000; i++) {
			double G = DegreeSequence.G_0(1, i, prob);
			//System.out.println(G);
			Assert.assertTrue(G > 0.9 && G < 1.1);
		}
		
		for (int i = 1; i <= 1000; i++) {
			double G = DegreeSequence.G_0(0, i, prob);
			double p = DegreeSequence.p(0, i, prob);
			
			Assert.assertTrue(Math.abs(G -p) < 0.001);
		}
		
	}
	
	//@Test
	public void Gprime_0Test() {
		Assert.assertTrue(Math.pow(0, 0) == 1);
		
		for (int i = 2; i <= 1000; i++){
			double Gprime = DegreeSequence.Gprime_0(0, i, prob);
			double p = DegreeSequence.p(1, i, prob);
			Assert.assertTrue(Math.abs(Gprime - p) < 0.001);
			//System.out.println(Gprime + "\t" + p);
		}
		
		for (int i = 2; i <= 1000; i++){
			double Gprime = DegreeSequence.Gprime_0(1, i, prob);
			Assert.assertTrue(Math.abs(Gprime - (i-1)*prob) < 0.001);
			//System.out.println(Math.abs(Gprime - (i-1)*prob));
		}
		
	}
	@Test
	public void GmTest() {
		
		//Assert.assertTrue(DegreeSequence.GprimeM(1, 5, 10, prob) == DegreeSequence.G_0(5, 10, prob));
		
		//System.out.println(DegreeSequence.Gm(3, 0, 4, prob));
		
/*		int n = 7;
		for (int L = 1 ; L < n; L++) {
			System.out.println(DegreeSequence.GprimeM(L, 1, n, prob));
		}*/	
		int n = 4;
		
		System.out.println(DegreeSequence.GprimeM(1, 0, n, prob));
		System.out.println(DegreeSequence.Gprime_0(0, n, prob));
		
		System.out.println(DegreeSequence.GprimeM(4, 0, n, prob));
		System.out.println(DegreeSequence.GPrimeM(4, 0, n, prob));
		
		System.out.println(DegreeSequence.Gm(1, 0, 10, prob));
		//Gm doesn't make sense...
	}

}
