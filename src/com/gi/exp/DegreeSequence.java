package com.gi.exp;

import org.apache.commons.math3.*;
import org.apache.commons.math3.util.ArithmeticUtils;

public class DegreeSequence {

	public static int n = 10;
	public static double p = 1 / 2;

	public static double p(int k) {
		return p(k, n, p);
	}

	public static double p(int k, int n, double p) {
		n = n - 1;

		double binomial = ArithmeticUtils.binomialCoefficientDouble(n, k);
		double probabilities = Math.pow(p, (double) k) * Math.pow((1.0 - p), (double) n - k);

		return binomial * probabilities;
	}

	public static double G_0(double x) {
		return G_0(x, n, p);
	}

	public static double G_0(double x, int n, double p) {
		double totalP = 0;
		for (int k = 0; k < n; k++) {
			totalP += p(k, n, p) * Math.pow(x, k);
		}

		return totalP;
	}

	public static double G_1(double x) {
		return G_1(x, n, p);
	}

	public static double G_1(double x, int n, double p) {

		return Gprime_0(x, n, p) / Gprime_0(1, n, p);

	}

	public static double Gprime_0(double x, int n, double p) {
		double totalP = 0;
		for (int k = 1; k < n; k++) {
			totalP += k * p(k, n, p) * Math.pow(x, k - 1);
		}

		return totalP;
	}

	public static double Gm(int m, double x) {
		return Gm(m, x, n, p);
	}

	public static double Gm(int m, double x, int n, double p) {

		if (m == 1) {
			return G_0(x, n, p);
		} else {
			double Gone = G_1(x, n, p);
			return Gm(m - 1, Gone, n, p);
		}
	}

	public static double Gprime_1(double x) {
		return Gprime_1(x, n, p);
	}

	public static double Gprime_1(double x, int n, double p) {
		double totalP = 0;
		for (int k = 2; k < n; k++) {
			totalP += k * (k-1) * p(k, n, p) * Math.pow(x, k - 2);
		}

		return totalP/ Gprime_0(1, n, p);
	}
	
	public static double GprimeM(int m, double x){
		return GprimeM(m, x, n, p);
	}
	
	public static double GprimeM(int m, double x, int n, double p) {

		if (m == 1) {
			return Gprime_0(x, n, p);
		} else {
			double totalP = 1;
			for (int i = 1; i <= m; i++) {
				double currentP = x;
				
				for (int j = m - 1; j > 0; j--) {
					currentP = G_1(currentP, n, p);
				}
				
				if (i == 1) {
					currentP = Gprime_0(currentP, n, p); 
				}
				else {
					currentP = Gprime_1(currentP, n, p);
				}
				
				totalP *= currentP;
			}	
			return totalP;			
		}
	}
	
	public static double GPrimeM(int m, double x) {
		return GPrimeM(m, x, n, p);
	}
	
	public static double GPrimeM(int m, double x, int n, double p){ 
		double eps = 0.0000001;
		double rise = Gm(m, x + eps, n, p) - Gm(m, x, n, p);
		double run = eps;
		
		return rise/run;		
	}

}
