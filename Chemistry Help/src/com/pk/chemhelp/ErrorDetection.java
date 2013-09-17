package com.pk.chemhelp;

public class ErrorDetection
{
	protected DataStorage appState;
	
	public ErrorDetection()
	{
		
	}
	
	public int getEmptyFieldBoyles(int V1, int P1, int V2, int P2)
	{
		int empty;
		if(V1 != 0 && P1 != 0 && V2 != 0 && P2 != 0)
			empty = -1;
		else if(V1 == 0 && P1 == 0 && V2 == 0 && P2 == 0)
			empty = 0;
		else if(V1 == 0 && P1 != 0 && V2 != 0 && P2 != 0)
			empty = 1;
		else if(P1 == 0 && V1 != 0 && V2 != 0 && P2 != 0)
			empty = 2;
		else if(V2 == 0 && V1 != 0 && P1 != 0 && P2 != 0)
			empty = 3;
		else if(P2 == 0 && V1 != 0 && P1 != 0 && V2 != 0)
			empty = 4;
		else if(V1 == 0 && P1 == 0 && V2 != 0 && P2 != 0)
			empty = 5;
		else if(V1 == 0 && V2 == 0 && P1 != 0 && P2 != 0)
			empty = 6;
		else if(V1 == 0 && P2 == 0 && V2 != 0 && P1 != 0)
			empty = 7;
		else if(P1 == 0 && V2 == 0 && P2 != 0 && V1 != 0)
			empty = 8;
		else if(P1 == 0 && P2 == 0 && V1 != 0 && V2 != 0)
			empty = 9;
		else if(V2 == 0 && P2 == 0 && V1 != 0 && P1 != 0)
			empty = 10;
		else if(V1 == 0 && V2 == 0 && P1 == 0 && P2 != 0)
			empty = 11;
		else if(P1 == 0 && P2 == 0 && V2 == 0 && V1 != 0)
			empty = 12;
		else if(V2 == 0 && P2 == 0 && V1 == 0 && P1 != 0)
			empty = 13;
		else if(V1 == 0 && P1 == 0 && P2 == 0 && V2 != 0)
			empty = 14;
		else if(V1 == 0 && P2 == 0 && V2 != 0 && P1 != 0)
			empty = 15;
		else
		    empty = 16;
		return empty;
	}
	
	public int getEmptyFieldCharles(int V1, int T1, int V2, int T2)
	{
		int empty;
		if(V1 != 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = -1;
		else if(V1 == 0 && T1 == 0 && V2 == 0 && T2 == 0)
			empty = 0;
		else if(V1 == 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = 1;
		else if(T1 == 0 && V1 != 0 && V2 != 0 && T2 != 0)
			empty = 2;
		else if(V2 == 0 && V1 != 0 && T1 != 0 && T2 != 0)
			empty = 3;
		else if(T2 == 0 && V1 != 0 && T1 != 0 && V2 != 0)
			empty = 4;
		else if(V1 == 0 && T1 == 0 && V2 != 0 && T2 != 0)
			empty = 5;
		else if(V1 == 0 && V2 == 0 && T1 != 0 && T2 != 0)
			empty = 6;
		else if(V1 == 0 && T2 == 0 && V2 != 0 && T1 != 0)
			empty = 7;
		else if(T1 == 0 && V2 == 0 && T2 != 0 && V1 != 0)
			empty = 8;
		else if(T1 == 0 && T2 == 0 && V1 != 0 && V2 != 0)
			empty = 9;
		else if(V2 == 0 && T2 == 0 && V1 != 0 && T1 != 0)
			empty = 10;
		else if(V1 == 0 && V2 == 0 && T1 == 0 && T2 != 0)
			empty = 11;
		else if(T1 == 0 && T2 == 0 && V2 == 0 && V1 != 0)
			empty = 12;
		else if(V2 == 0 && T2 == 0 && V1 == 0 && T1 != 0)
			empty = 13;
		else if(V1 == 0 && T1 == 0 && T2 == 0 && V2 != 0)
			empty = 14;
		else if(V1 == 0 && T2 == 0 && V2 != 0 && T1 != 0)
			empty = 15;
		else
		    empty = 16;
		return empty;
	}
	
	public int getEmptyFieldIdealGas(int N, int P, int V, int T)
	{
		int empty;
		if(N != 0 && P != 0 && V != 0 && T != 0)
			empty = -1;
		else if(N == 0 && P == 0 && V == 0 && T == 0)
			empty = 0;
		else if(N == 0 && P != 0 && V != 0 && T != 0)
			empty = 1;
		else if(P == 0 && N != 0 && V != 0 && T != 0)
			empty = 2;
		else if(V == 0 && N != 0 && P != 0 && T != 0)
			empty = 3;
		else if(T == 0 && N != 0 && P != 0 && V != 0)
			empty = 4;
		else if(N == 0 && P == 0 && V != 0 && T != 0)
			empty = 5;
		else if(N == 0 && V == 0 && P != 0 && T != 0)
			empty = 6;
		else if(N == 0 && T == 0 && V != 0 && P != 0)
			empty = 7;
		else if(P == 0 && V == 0 && T != 0 && N != 0)
			empty = 8;
		else if(P == 0 && T == 0 && N != 0 && V != 0)
			empty = 9;
		else if(V == 0 && T == 0 && N != 0 && P != 0)
			empty = 10;
		else if(N == 0 && V == 0 && P == 0 && T != 0)
			empty = 11;
		else if(P == 0 && T == 0 && V == 0 && N != 0)
			empty = 12;
		else if(V == 0 && T == 0 && N == 0 && P != 0)
			empty = 13;
		else if(N == 0 && P == 0 && T == 0 && V != 0)
			empty = 14;
		else if(N == 0 && T == 0 && V != 0 && P != 0)
			empty = 15;
		else
		    empty = 16;
		return empty;
	}
	
	public int getEmptyFieldGayLussacs(int P1, int T1, int P2, int T2)
	{
		int empty;
		if(P1 != 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = -1;
		else if(P1 == 0 && T1 == 0 && P2 == 0 && T2 == 0)
			empty = 0;
		else if(P1 == 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = 1;
		else if(T1 == 0 && P1 != 0 && P2 != 0 && T2 != 0)
			empty = 2;
		else if(P2 == 0 && P1 != 0 && T1 != 0 && T2 != 0)
			empty = 3;
		else if(T2 == 0 && P1 != 0 && T1 != 0 && P2 != 0)
			empty = 4;
		else if(P1 == 0 && T1 == 0 && P2 != 0 && T2 != 0)
			empty = 5;
		else if(P1 == 0 && P2 == 0 && T1 != 0 && T2 != 0)
			empty = 6;
		else if(P1 == 0 && T2 == 0 && P2 != 0 && T1 != 0)
			empty = 7;
		else if(T1 == 0 && P2 == 0 && T2 != 0 && P1 != 0)
			empty = 8;
		else if(T1 == 0 && T2 == 0 && P1 != 0 && P2 != 0)
			empty = 9;
		else if(P2 == 0 && T2 == 0 && P1 != 0 && T1 != 0)
			empty = 10;
		else if(P1 == 0 && P2 == 0 && T1 == 0 && T2 != 0)
			empty = 11;
		else if(T1 == 0 && T2 == 0 && P2 == 0 && P1 != 0)
			empty = 12;
		else if(P2 == 0 && T2 == 0 && P1 == 0 && T1 != 0)
			empty = 13;
		else if(P1 == 0 && T1 == 0 && T2 == 0 && P2 != 0)
			empty = 14;
		else if(P1 == 0 && T2 == 0 && P2 != 0 && T1 != 0)
			empty = 15;
		else
		    empty = 16;
		return empty;
	}
	
	public int getEmptyFieldCombined(int V1, int P1, int T1, int V2, int P2, int T2)
	{
		int empty;
		if(V1 != 0 && P1 != 0 && T1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = -1;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && T2 == 0)
			empty = 0;
		else if(V1 == 0 && P1 != 0 && T1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 1;
		else if(P1 == 0 && V1 != 0 && T1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 2;
		else if(T1 == 0 && V1 != 0 && P1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 3;
		else if(V2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = 4;
		else if(P2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = 5;
		else if(T2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && V2 != 0 && P2 != 0)
			empty = 6;
		else if(V1 == 0 && P1 == 0 && T1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 7;
		else if(V1 == 0 && T1 == 0 && P1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 8;
		else if(V1 == 0 && V2 == 0 && P1 != 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = 9;
		else if(V1 == 0 && P2 == 0 && P1 != 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = 10;
		else if(V1 == 0 && T2 == 0 && P1 != 0 && T1 != 0 && V2 != 0 && P2 != 0)
			empty = 11;
		else if(P1 == 0 && T1 == 0 && V1 != 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 12;
		else if(P1 == 0 && V2 == 0 && V1 != 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = 13;
		else if(P1 == 0 && P2 == 0 && V1 != 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = 14;
		else if(P1 == 0 && T2 == 0 && V1 != 0 && T1 != 0 && V2 != 0 && P2 != 0)
			empty = 15;
		else if(T1 == 0 && V2 == 0 && V1 != 0 && P1 != 0 && P2 != 0 && T2 != 0)
			empty = 16;
		else if(T1 == 0 && P2 == 0 && V1 != 0 && P1 != 0 && V2 != 0 && T2 != 0)
			empty = 17;
		else if(T1 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && V2 != 0 && P2 != 0)
			empty = 18;
		else if(V2 == 0 && P2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && T2 != 0)
			empty = 19;
		else if(V2 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && P2 != 0)
			empty = 20;
		else if(P2 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && T1 != 0 && V2 != 0)
			empty = 21;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && V2 != 0 && P2 != 0 && T2 != 0)
			empty = 22;
		else if(V1 == 0 && P1 == 0 && V2 == 0 && T1 != 0 && P2 != 0 && T2 != 0)
			empty = 23;
		else if(V1 == 0 && P1 == 0 && P2 == 0 && T1 != 0 && V2 != 0 && T2 != 0)
			empty = 24;
		else if(V1 == 0 && P1 == 0 && T2 == 0 && T1 != 0 && V2 != 0 && P2 != 0)
			empty = 25;
		else if(V1 == 0 && T1 == 0 && V2 == 0 && P1 != 0 && P2 != 0 && T2 != 0)
			empty = 26;
		else if(V1 == 0 && T1 == 0 && P2 == 0 && P1 != 0 && V2 != 0 && T2 != 0)
			empty = 27;
		else if(V1 == 0 && T1 == 0 && T2 == 0 && P1 != 0 && V2 != 0 && P2 != 0)
			empty = 28;
		else if(V1 == 0 && V2 == 0 && P2 == 0 && P1 != 0 && T1 != 0 && T2 != 0)
			empty = 29;
		else if(V1 == 0 && V2 == 0 && T2 == 0 && P1 != 0 && T1 != 0 && P2 != 0)
			empty = 30;
		else if(V1 == 0 && P2 == 0 && T2 == 0 && P1 != 0 && T1 != 0 && V2 != 0)
			empty = 31;
		else if(P1 == 0 && T1 == 0 && V2 == 0 && V1 != 0 && P2 != 0 && T2 != 0)
			empty = 32;
		else if(P1 == 0 && T1 == 0 && P2 == 0 && V1 != 0 && V2 != 0 && T2 != 0)
			empty = 33;
		else if(P1 == 0 && T1 == 0 && T2 == 0 && V1 != 0 && V2 != 0 && P2 != 0)
			empty = 34;
		else if(P1 == 0 && V2 == 0 && P2 == 0 && V1 != 0 && T1 != 0 && T2 != 0)
			empty = 35;
		else if(P1 == 0 && V2 == 0 && T2 == 0 && V1 != 0 && T1 != 0 && P2 != 0)
			empty = 36;
		else if(P1 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && T1 != 0 && V2 != 0)
			empty = 37;
		else if(T1 == 0 && V2 == 0 && P2 == 0 && V1 != 0 && P1 != 0 && T2 != 0)
			empty = 38;
		else if(T1 == 0 && V2 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && P2 != 0)
			empty = 39;
		else if(T1 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && V2 != 0)
			empty = 40;
		else if(V2 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && P1 != 0 && T1 != 0)
			empty = 41;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && V2 == 0 && P2 != 0 && T2 != 0)
			empty = 42;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && P2 == 0 && V2 != 0 && T2 != 0)
			empty = 43;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && T2 == 0 && V2 != 0 && P2 != 0)
			empty = 44;
		else if(V1 == 0 && P1 == 0 && V2 == 0 && P2 == 0 && T1 != 0 && T2 != 0)
			empty = 45;
		else if(V1 == 0 && P1 == 0 && V2 == 0 && T2 == 0 && T1 != 0 && P2 != 0)
			empty = 46;
		else if(V1 == 0 && P1 == 0 && P2 == 0 && T2 == 0 && T1 != 0 && V2 != 0)
			empty = 47;
		else if(V1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && P1 != 0 && T2 != 0)
			empty = 48;
		else if(V1 == 0 && T1 == 0 && V2 == 0 && T2 == 0 && P1 != 0 && P2 != 0)
			empty = 49;
		else if(V1 == 0 && T1 == 0 && P2 == 0 && T2 == 0 && P1 != 0 && V2 != 0)
			empty = 50;
		else if(V1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && P1 != 0 && T1 != 0)
			empty = 51;
		else if(P1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && V1 != 0 && T2 != 0)
			empty = 52;
		else if(P1 == 0 && T1 == 0 && V2 == 0 && T2 == 0 && V1 != 0 && P2 != 0)
			empty = 53;
		else if(P1 == 0 && T1 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && V2 != 0)
			empty = 54;
		else if(P1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && T1 != 0)
			empty = 55;
		else if(T1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && V1 != 0 && P1 != 0)
			empty = 56;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && T2 != 0)
			empty = 57;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && V2 == 0 && T2 == 0 && P2 != 0)
			empty = 58;
		else if(V1 == 0 && P1 == 0 && T1 == 0 && P2 == 0 && T2 == 0 && V2 != 0)
			empty = 59;
		else if(V1 == 0 && P1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && T1 != 0)
			empty = 60;
		else if(V1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && P1 != 0)
			empty = 61;
		else if(P1 == 0 && T1 == 0 && V2 == 0 && P2 == 0 && T2 == 0 && V1 != 0)
			empty = 62;
		else
		    empty = 63;
		return empty;
	}
	
	public String detectErrorBoyles(int error)
	{
		String errorMSG = "";
		if(error == -1)
			errorMSG = "No errors found";
		else if(error == 0)
			errorMSG = "Please enter your numbers.";
		else if(error == 1)
			errorMSG = "V1 is empty";
		else if(error == 2)
			errorMSG = "P1 is empty";
		else if(error == 3)
			errorMSG = "V2 is empty";
		else if(error == 4)
			errorMSG = "P2 is empty";
		else if(error == 5)
			errorMSG = "V1 and P1 are empty";
		else if(error == 6)
			errorMSG = "V1 and V2 are empty";
		else if(error == 7)
			errorMSG = "V1 and P2 are empty";
		else if(error == 8)
			errorMSG = "P1 and V2 are empty";
		else if(error == 9)
			errorMSG = "P1 and P2 are empty";
		else if(error == 10)
			errorMSG = "V2 and P2 are empty";
		else if(error == 11)
			errorMSG = "V1, P1, and V2 are empty";
		else if(error == 12)
			errorMSG = "P1, P2, and V2 are empty";
		else if(error == 13)
			errorMSG = "V1, V2, and P2 are empty";
		else if(error == 14)
			errorMSG = "V1, P1, and P2 are empty";
		else if(error == 15)
			errorMSG = "V1 and P2 are empty";
		else
			errorMSG = "Unknown Error Ocurred!";
		return errorMSG;
	}
	
	public String detectErrorCharles(int error)
	{
		String errorMSG = "";
		if(error == -1)
			errorMSG = "No errors found";
		else if(error == 0)
			errorMSG = "Please enter your numbers.";
		else if(error == 1)
			errorMSG = "V1 is empty";
		else if(error == 2)
			errorMSG = "T1 is empty";
		else if(error == 3)
			errorMSG = "V2 is empty";
		else if(error == 4)
			errorMSG = "T2 is empty";
		else if(error == 5)
			errorMSG = "V1 and T1 are empty";
		else if(error == 6)
			errorMSG = "V1 and T2 are empty";
		else if(error == 7)
			errorMSG = "V1 and T2 are empty";
		else if(error == 8)
			errorMSG = "T1 and V2 are empty";
		else if(error == 9)
			errorMSG = "T1 and T2 are empty";
		else if(error == 10)
			errorMSG = "V2 and T2 are empty";
		else if(error == 11)
			errorMSG = "V1, T1, and V2 are empty";
		else if(error == 12)
			errorMSG = "T1, T2, and V2 are empty";
		else if(error == 13)
			errorMSG = "V1, V2, and T2 are empty";
		else if(error == 14)
			errorMSG = "V1, T1, and T2 are empty";
		else if(error == 15)
			errorMSG = "V1 and T2 are empty";
		else
			errorMSG = "Unknown Error Ocurred!";
		return errorMSG;
	}
	
	public String detectErrorIdealGas(int error)
	{
		String errorMSG = "";
		if(error == -1)
			errorMSG = "No errors found";
		else if(error == 0)
			errorMSG = "Please enter your numbers.";
		else if(error == 1)
			errorMSG = "V1 is empty";
		else if(error == 2)
			errorMSG = "T1 is empty";
		else if(error == 3)
			errorMSG = "V2 is empty";
		else if(error == 4)
			errorMSG = "T2 is empty";
		else if(error == 5)
			errorMSG = "V1 and T1 are empty";
		else if(error == 6)
			errorMSG = "V1 and T2 are empty";
		else if(error == 7)
			errorMSG = "V1 and T2 are empty";
		else if(error == 8)
			errorMSG = "T1 and V2 are empty";
		else if(error == 9)
			errorMSG = "T1 and T2 are empty";
		else if(error == 10)
			errorMSG = "V2 and T2 are empty";
		else if(error == 11)
			errorMSG = "V1, T1, and V2 are empty";
		else if(error == 12)
			errorMSG = "T1, T2, and V2 are empty";
		else if(error == 13)
			errorMSG = "V1, V2, and T2 are empty";
		else if(error == 14)
			errorMSG = "V1, T1, and T2 are empty";
		else if(error == 15)
			errorMSG = "V1 and T2 are empty";
		else
			errorMSG = "Unknown Error Ocurred!";
		return errorMSG;
	}
	
	public String detectErrorGayLussacs(int error)
	{
		String errorMSG = "";
		if(error == -1)
			errorMSG = "No errors found";
		else if(error == 0)
			errorMSG = "Please enter your numbers.";
		else if(error == 1)
			errorMSG = "P1 is empty";
		else if(error == 2)
			errorMSG = "T1 is empty";
		else if(error == 3)
			errorMSG = "P2 is empty";
		else if(error == 4)
			errorMSG = "T2 is empty";
		else if(error == 5)
			errorMSG = "P1 and T1 are empty";
		else if(error == 6)
			errorMSG = "P1 and P2 are empty";
		else if(error == 7)
			errorMSG = "P1 and T2 are empty";
		else if(error == 8)
			errorMSG = "T1 and P2 are empty";
		else if(error == 9)
			errorMSG = "T1 and T2 are empty";
		else if(error == 10)
			errorMSG = "P2 and T2 are empty";
		else if(error == 11)
			errorMSG = "P1, T1, and P2 are empty";
		else if(error == 12)
			errorMSG = "T1, T2, and P2 are empty";
		else if(error == 13)
			errorMSG = "P1, P2, and T2 are empty";
		else if(error == 14)
			errorMSG = "P1, T1, and T2 are empty";
		else if(error == 15)
			errorMSG = "P1 and T2 are empty";
		else
			errorMSG = "Unknown Error Ocurred!";
		return errorMSG;
	}
	
	public String detectErrorCombined(int error)
	{
		String errorMSG = "";
		if(error == -1)
			errorMSG = "No errors found";
		else if(error == 0)
			errorMSG = "Please enter your numbers.";
		else if(error == 1)
			errorMSG = "V1 is empty";
		else if(error == 2)
			errorMSG = "P1 is empty";
		else if(error == 3)
			errorMSG = "T1 is empty";
		else if(error == 4)
			errorMSG = "V2 is empty";
		else if(error == 5)
			errorMSG = "P2 is empty";
		else if(error == 6)
			errorMSG = "T2 is empty";
		else if(error == 7)
			errorMSG = "V1 and P1 are empty";
		else if(error == 8)
			errorMSG = "V1 and T1 are empty";
		else if(error == 9)
			errorMSG = "V1 and V2 are empty";
		else if(error == 10)
			errorMSG = "V1 and P2 are empty";
		else if(error == 11)
			errorMSG = "V1 and T2 are empty";
		else if(error == 12)
			errorMSG = "P1 and T1 are empty";
		else if(error == 13)
			errorMSG = "P1 and V2 are empty";
		else if(error == 14)
			errorMSG = "P1 and P2 are empty";
		else if(error == 15)
			errorMSG = "P1 and T2 are Empty";
		else if(error == 16)
			errorMSG = "T1 and V2 are Empty";
		else if(error == 17)
			errorMSG = "T1 and P2 are Empty";
		else if(error == 18)
			errorMSG = "T1 and T2 are Empty";
		else if(error == 19)
			errorMSG = "V2 and P2 are Empty";
		else if(error == 20)
			errorMSG = "V2 and T2 are Empty";
		else if(error == 21)
			errorMSG = "P2 and T2 are Empty";
		else if(error == 22)
			errorMSG = "V1, P1, and T1 are Empty";
		else if(error == 23)
			errorMSG = "V1, P1, and V2 are Empty";
		else if(error == 24)
			errorMSG = "V1, P1, and P2 are Empty";
		else if(error == 25)
			errorMSG = "V1, P1, and T2 are Empty";
		else if(error == 26)
			errorMSG = "V1, T1, and V2 are Empty";
		else if(error == 27)
			errorMSG = "V1, T1, and P2 are Empty";
		else if(error == 28)
			errorMSG = "V1, T1, and T2 are Empty";
		else if(error == 29)
			errorMSG = "V1, V2, and P2 are Empty";
		else if(error == 30)
			errorMSG = "V1, V2, and T2 are Empty";
		else if(error == 31)
			errorMSG = "V1, P2, and T2 are Empty";
		else if(error == 32)
			errorMSG = "P1, T1, and V2 are Empty";
		else if(error == 33)
			errorMSG = "P1, T1, and P2 are Empty";
		else if(error == 34)
			errorMSG = "P1, T1, and T2 are Empty";
		else if(error == 35)
			errorMSG = "P1, V2, and P2 are Empty";
		else if(error == 36)
			errorMSG = "P1, V2, and T2 are Empty";
		else if(error == 37)
			errorMSG = "P1, P2, and T2 are Empty";
		else if(error == 38)
			errorMSG = "T1, V2, and P2 are Empty";
		else if(error == 39)
			errorMSG = "T1, V2, and T2 are Empty";
		else if(error == 40)
			errorMSG = "T1, P2, and T2 are Empty";
		else if(error == 41)
			errorMSG = "V2, P2, and T2 are Empty";
		else if(error == 42)
			errorMSG = "V1, P1, T1, and V2 are Empty";
		else if(error == 43)
			errorMSG = "V1, P1, T1, and P2 are Empty";
		else if(error == 44)
			errorMSG = "V1, P1, T1, and T2 are Empty";
		else if(error == 45)
			errorMSG = "V1, P1, V2, and P2 are Empty";
		else if(error == 46)
			errorMSG = "V1, P1, V2, and T2 are Empty";
		else if(error == 47)
			errorMSG = "V1, P1, P2, and T2 are Empty";
		else if(error == 48)
			errorMSG = "V1, T1, V2, and P2 are Empty";
		else if(error == 49)
			errorMSG = "V1, T1, V2, and T2 are Empty";
		else if(error == 50)
			errorMSG = "V1, T1, P2, and T2 are Empty";
		else if(error == 51)
			errorMSG = "V1, V2, P2, and T2 are Empty";
		else if(error == 52)
			errorMSG = "P1, T1, V2, and P2 are Empty";
		else if(error == 53)
			errorMSG = "P1, T1, V2, and T2 are Empty";
		else if(error == 54)
			errorMSG = "P1, T1, P2, and T2 are Empty";
		else if(error == 55)
			errorMSG = "P1, V2, P2, and T2 are Empty";
		else if(error == 56)
			errorMSG = "T1, V2, P2, and T2 are Empty";
		else if(error == 57)
			errorMSG = "V1, P1, T1, V2, and P2 are Empty";
		else if(error == 58)
			errorMSG = "V1, P1, T1, V2, and T2 are Empty";
		else if(error == 59)
			errorMSG = "V1, P1, T1, P2, and T2 are Empty";
		else if(error == 60)
			errorMSG = "V1, P1, V2, P2, and T2 are Empty";
		else if(error == 61)
			errorMSG = "V1, T1, V2, P2, and T2 are Empty";
		else if(error == 62)
			errorMSG = "P1, T1, V2, P2, and T2 are Empty";
		else
			errorMSG = "Unknown Error Ocurred!";
		return errorMSG;
	}
}

//-1 = No Empty
// 0 = All Empty
// 1 = V1 Empty
// 2 = P1 Empty
// 3 = V2 Empty
// 4 = P2 Empty
// 5 = V1 AND P1 Empty
// 6 = V1 AND V2 Empty
// 7 = V1 AND P2 Empty
// 8 = P1 AND V2 Empty
// 9 = P1 AND P2 Empty
// 10 = V2 AND P2 Empty
// 11 = V1 AND V2 AND P1 Empty
// 12 = P1 AND P2 AND V2 Empty
// 13 = V2 AND P2 AND V1 Empty
// 14 = V1 AND P1 AND P2 Empty
// 15 = V1 AND P2 Empty
// 16 = Other

//-1 = No Empty
//0 = All Empty
//1 = V1 Empty
//2 = P1 Empty
//3 = T1 Empty
//4 = V2 Empty
//5 = P2 Empty
//6 = T2 Empty
//7 = V1 AND P1 Empty
//8 = V1 AND T1 Empty
//9 = V1 AND V2 Empty
//10 = V1 AND P2 Empty
//11 = V1 AND T2 Empty
//12 = P1 AND T1 Empty
//13 = P1 AND V2 Empty
//14 = P1 AND P2 Empty
//15 = P1 AND T2 Empty
//16 = T1 AND V2 Empty
//17 = T1 AND P2 Empty
//18 = T1 AND T2 Empty
//19 = V2 AND P2 Empty
//20 = V2 AND T2 Empty
//21 = P2 AND T2 Empty
//22 = V1 AND P1 AND T1 Empty
//23 = V1 AND P1 AND V2 Empty
//24 = V1 AND P1 AND P2 Empty
//25 = V1 AND P1 AND T2 Empty
//26 = V1 AND T1 AND V2 Empty
//27 = V1 AND T1 AND P2 Empty
//28 = V1 AND T1 AND T2 Empty
//29 = V1 AND V2 AND P2 Empty
//30 = V1 AND V2 AND T2 Empty
//31 = V1 AND P2 AND T2 Empty
//32 = P1 AND T1 AND V2 Empty
//33 = P1 AND T1 AND P2 Empty
//34 = P1 AND T1 AND T2 Empty
//35 = P1 AND V2 AND P2 Empty
//36 = P1 AND V2 AND T2 Empty
//37 = P1 AND P2 AND T2 Empty
//38 = T1 AND V2 AND P2 Empty
//39 = T1 AND V2 AND T2 Empty
//40 = T1 AND P2 AND T2 Empty
//41 = V2 AND P2 AND T2 Empty
//42 = V1 AND P1 AND T1 AND V2 Empty
//43 = V1 AND P1 AND T1 AND P2 Empty
//44 = V1 AND P1 AND T1 AND T2 Empty
//45 = V1 AND P1 AND V2 AND P2 Empty
//46 = V1 AND P1 AND V2 AND T2 Empty
//47 = V1 AND P1 AND P2 AND T2 Empty
//48 = V1 AND T1 AND V2 AND P2 Empty
//49 = V1 AND T1 AND V2 AND T2 Empty
//50 = V1 AND T1 AND P2 AND T2 Empty
//51 = V1 AND V2 AND P2 AND T2 Empty
//52 = P1 AND T1 AND V2 AND P2 Empty
//53 = P1 AND T1 AND V2 AND T2 Empty
//54 = P1 AND T1 AND P2 AND T2 Empty
//55 = P1 AND V2 AND P2 AND T2 Empty
//56 = T1 AND V2 AND P2 AND T2 Empty
//57 = V1 AND P1 AND T1 AND V2 AND P2
//58 = V1 AND P1 AND T1 AND V2 AND T2
//59 = V1 AND P1 AND T1 AND P2 AND T2
//60 = V1 AND P1 AND V2 AND P2 AND T2
//61 = V1 AND T1 AND V2 AND P2 AND T2
//62 = P1 AND T1 AND V2 AND P2 AND T2
//63 = Other