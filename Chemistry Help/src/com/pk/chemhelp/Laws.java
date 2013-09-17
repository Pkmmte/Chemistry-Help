package com.pk.chemhelp;

public class Laws extends Conversions
{
	protected DataStorage appState;

	private static String SelectV;
	private static String SelectV1;
	private static String SelectV2;
	private static String SelectP;
	private static String SelectP1;
	private static String SelectP2;
	private static String SelectT;
	private static String SelectT1;
	private static String SelectT2;

	public Laws()
	{

	}

	/**
	 * These methods find the answer for a specific law.
	 */
	public double Boyles_Law(double V1, double P1, double V2, double P2)
	{
		double Result = 0;
		boolean Converted = false;

		if (P1 == -9876.5)
		{
			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law: Gas Unit Logic Error (Volume)");
			}
			
			Result = (V2 * P2) / V1;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			
			Result = convertPressure(Result, P1, P2);

			return Result;
		}
		else if (P2 == -9876.5)
		{
			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law: Gas Unit Logic Error (Volume)");
			}
			
			Result = (V1 * P1) / V2;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			
			Result = convertPressure(Result, P1, P2);
			
			return Result;
		}
		else if (V1 == -9876.5)
		{
			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law: Gas Unit Logic Error (Pressure)");
			}
			
			Result = (V2 * P2) / P1;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			Result = convertVolume(Result, V1, V2);

			return Result;
		}
		else if (V2 == -9876.5)
		{
			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law: Gas Unit Logic Error (Pressure)");
			}
			
			Result = (V1 * P1) / P2;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);
			
			Result = convertVolume(Result, V1, V2);

			return Result;
		}
		else
			return Result;
	}

	public double Charles_Law(double V1, double T1, double V2, double T2)
	{
		double Result = 0;
		boolean Converted = false;
		
		if (V1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);
			
			
			Result = (V2 * T1) / T2;

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);
			
			Result = convertVolume(Result, V1, V2);
			
			return Result;
		}

		else if (T1 == -9876.5)
		{
			T2 = Misc.toKelvins(T2, 2);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Charle's Law: Gas Unit Logic Error (Volume)");
			}
			
			// Solve
			Result = (V1 * T2) / V2;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			
			T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);
			
			return Result;
		}

		else if (V2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			// Solve
			Result = (V1 * T2) / T1;

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);
			
			Result = convertVolume(Result, V1, V2);

			return Result;
		}

		else if (T2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Charle's Law: Gas Unit Logic Error (Volume)");
			}

			// Solve
			Result = (T1 * V2) / V1;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			T1 = Misc.revertTemp(T1, 1);
			
			Result = convertTemperature(Result, T1, T2);

			return Result;
		}
		else
			return Result;
	}
	
	public double GayLussac_Law(double P1, double T1, double P2, double T2)
	{
		double Result = 0;
		boolean Converted = false;
		
		if (P1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			Result = (P2 * T1) / T2;

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);
			
			Result = convertPressure(Result, P1, P2);

			return Result;
		}
		else if (T1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = (P1 * T2) / P2;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);
			
			return Result;
		}
		else if (P2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			Result = (P1 * T2) / T1;

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);
			
			Result = convertPressure(Result, P1, P2);
			
			return Result;
		}
		else if (T2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = (P2 * T1) / P1;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);
			
			Result = convertTemperature(Result, T1, T2);
			
			return Result;
		}
		else
			return Result;
	}
	
	public double IdealGas_Law(double n, double P, double V, double T)
	{
		SelectV = MySingleton.getInstance().getSelectV();
		SelectP = MySingleton.getInstance().getSelectP();
		SelectT = MySingleton.getInstance().getSelectT();
		
		double R = 0.08206;
		double Result = 0;
		
		if (n == -9876.5)
		{
			T = Misc.toKelvins(T, 0);
			P = Misc.toAtmospheres(P, 0);
			V = Misc.toLiters(V, 0);

			Result = (P * V) / (R * T);

			T = Misc.revertTemp(T, 0);
			P = Misc.revertPressure(P, 0);
			V = Misc.revertVolume(V, 0);

			return Result;
		}
		else if (P == -9876.5)
		{
			T = Misc.toKelvins(T, 0);
			V = Misc.toLiters(V, 0);

			Result = (n * R * T) / V;

			T = Misc.revertTemp(T, 0);
			V = Misc.revertVolume(V, 0);
			
			if(!SelectP.equals("atm"))
			{
				if (SelectP.equals("mmHg"))
					Result = Convert2MMHGfromATM(Result);
				else if (SelectP.equals("torr"))
					Result = Convert2TORRfromATM(Result);
				else if (SelectP.equals("kPa"))
					Result = Convert2KPAfromATM(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law: Error converting P");
			}

			return Result;
		}
		else if (V == -9876.5)
		{
			T = Misc.toKelvins(T, 0);
			P = Misc.toAtmospheres(P, 0);

			Result = (n * R * T) / P;

			T = Misc.revertTemp(T, 0);
			P = Misc.revertPressure(P, 0);
			
			if(!SelectV.equals("L"))
			{
				if (SelectP.equals("mL"))
					Result = Convert2MLfromL(Result);
				else if (SelectP.equals("g"))
					Result = Convert2GfromL(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law: Error converting V");
			}

			return Result;
		}
		else if (T == -9876.5)
		{
			if(!SelectP.equals("atm"))
				P = Misc.toAtmospheres(P, 0);
			if(!SelectV.equals("L"))
				V = Misc.toLiters(V, 0);
				
			Result = (P * V) / (n * R);

			if(!SelectP.equals("atm"))
				P = Misc.revertPressure(P, 0);
			if(!SelectV.equals("L"))
				V = Misc.revertVolume(V, 0);
			    
			if(!SelectT.equals("K"))
			{
				if (SelectT.equals("C"))
					Result = Convert2CfromK(Result);
				else if (SelectT.equals("F"))
					Result = Convert2FfromK(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law: Error converting T");
			}
			
			return Result;
		}
		else
			return Result;
	}
	
	public double CombinedGas_Law(double V1, double P1, double T1, double V2, double P2, double T2)
	{
		double Result = 0;
		boolean Converted = false;
		
		if (P1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law: Gas Unit Logic Error (Volume)");
			}

			Result = (P2 * V2 * T1) / (V1 * T2);

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertPressure(Result, P1, P2);
			
			return Result;
		}
		else if (V1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = V2 * (P2 / P1) * (T1 * T2);

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertVolume(Result, V1, V2);

			return Result;
		}
		else if (T1 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law: Gas Unit Logic Error (Volume)");
			}
			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = (P1 / P2) / (V1 / V2) * T2;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);

			return Result;
		}
		else if (P2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law: Gas Unit Logic Error (Volume)");
			}

			Result = (P1 * V1 * T2) / (T1 * V2);

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertPressure(Result, P1, P2);

			return Result;
		}
		else if (V2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = (P1 * V1 * T2) / (P2 * T1);

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertVolume(Result, V1, V2);

			return Result;
		}
		else if (T2 == -9876.5)
		{
			T1 = Misc.toKelvins(T1, 1);
			T2 = Misc.toKelvins(T2, 2);

			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law: Gas Unit Logic Error (Volume)");
			}
			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law: Gas Unit Logic Error (Pressure)");
			}

			Result = (P2 * V2 * T1) / (P1 * V1);

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			T1 = Misc.revertTemp(T1, 1);
			T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);

			return Result;
		}
		else
			return Result;
	}

	/*
	 * Conversions... Yay.. =/
	 */
	
	public double convertVolume(double Answer, double V1, double V2)
	{
		double Result = Answer;
		SelectV1 = MySingleton.getInstance().getSelectV1();
		SelectV2 = MySingleton.getInstance().getSelectV2();
		@SuppressWarnings("unused")
		double nothing = 0;

		if (V1 == -9876.5)
		{
			if (SelectV1.equals("L") && SelectV2.equals("mL"))
				Result = Convert2LfromML(Result);
			else if (SelectV1.equals("L") && SelectV2.equals("g"))
				Result = Convert2LfromG(Result);
			else if (SelectV1.equals("mL") && SelectV2.equals("L"))
				Result = Convert2MLfromL(Result);
			else if (SelectV1.equals("mL") && SelectV2.equals("g"))
				Result = Convert2MLfromG(Result);
			else if (SelectV1.equals("g") && SelectV2.equals("L"))
				Result = Convert2GfromL(Result);
			else if (SelectV1.equals("g") && SelectV2.equals("mL"))
				Result = Convert2GfromML(Result);
			else if (SelectV1.equals("L") && SelectV2.equals("L"))
				nothing = Result;
			else if (SelectV1.equals("mL") && SelectV2.equals("mL"))
				nothing = Result;
			else if (SelectV1.equals("g") && SelectV2.equals("g"))
				nothing = Result;
			else
				MySingleton.getInstance().addError("\"Gas Law: Error converting V1\"");
		}
		else if (V2 == -9876.5)
		{
			if (SelectV2.equals("L") && SelectV1.equals("mL"))
				Result = Convert2LfromML(Result);
			else if (SelectV2.equals("L") && SelectV1.equals("g"))
				Result = Convert2LfromG(Result);
			else if (SelectV2.equals("mL") && SelectV1.equals("L"))
				Result = Convert2MLfromL(Result);
			else if (SelectV2.equals("mL") && SelectV1.equals("g"))
				Result = Convert2MLfromG(Result);
			else if (SelectV2.equals("g") && SelectV1.equals("L"))
				Result = Convert2GfromL(Result);
			else if (SelectV2.equals("g") && SelectV1.equals("mL"))
				Result = Convert2GfromML(Result);
			else if (SelectV2.equals("L") && SelectV1.equals("L"))
				nothing = Result;
			else if (SelectV2.equals("mL") && SelectV1.equals("mL"))
				nothing = Result;
			else if (SelectV2.equals("g") && SelectV1.equals("g"))
				nothing = Result;
			else
				MySingleton.getInstance().addError(
						"\"Gas Law: Error converting V2\"");
		}
		return Result;
	}

	public double convertPressure(double Answer, double P1, double P2)
	{
		double Result = Answer;
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectP2 = MySingleton.getInstance().getSelectP2();
		@SuppressWarnings("unused")
		double nothing = 0;

		if (P1 == -9876.5)
		{
			if (SelectP1.equals("atm") && SelectP2.equals("mmHg"))
				Result = Convert2ATMfromMMHG(Result);
			else if (SelectP1.equals("atm") && SelectP2.equals("torr"))
				Result = Convert2ATMfromTORR(Result);
			else if (SelectP1.equals("atm") && SelectP2.equals("kPa"))
				Result = Convert2ATMfromKPA(Result);
			else if (SelectP1.equals("mmHg") && SelectP2.equals("atm"))
				Result = Convert2MMHGfromATM(Result);
			else if (SelectP1.equals("mmHg") && SelectP2.equals("torr"))
				Result = Convert2MMHGfromTORR(Result);
			else if (SelectP1.equals("mmHg") && SelectP2.equals("kPa"))
				Result = Convert2MMHGfromKPA(Result);
			else if (SelectP1.equals("torr") && SelectP2.equals("atm"))
				Result = Convert2TORRfromATM(Result);
			else if (SelectP1.equals("torr") && SelectP2.equals("mmHg"))
				Result = Convert2TORRfromMMHG(Result);
			else if (SelectP1.equals("torr") && SelectP2.equals("kPa"))
				Result = Convert2TORRfromKPA(Result);
			else if (SelectP1.equals("kPa") && SelectP2.equals("atm"))
				Result = Convert2KPAfromATM(Result);
			else if (SelectP1.equals("kPa") && SelectP2.equals("mmHg"))
				Result = Convert2KPAfromMMHG(Result);
			else if (SelectP1.equals("kPa") && SelectP2.equals("torr"))
				Result = Convert2KPAfromTORR(Result);
			else if (SelectP1.equals("atm") && SelectP2.equals("atm"))
				nothing = Result;
			else if (SelectP1.equals("mmHg") && SelectP2.equals("mmHg"))
				nothing = Result;
			else if (SelectP1.equals("torr") && SelectP2.equals("torr"))
				nothing = Result;
			else if (SelectP2.equals("kPa") && SelectP1.equals("kPa"))
				nothing = Result;
			else
				MySingleton.getInstance().addError("\"Gas Law: Error converting P1\"");
		}
		else if (P2 == -9876.5)
		{
			if (SelectP2.equals("atm") && SelectP1.equals("mmHg"))
				Result = Convert2ATMfromMMHG(Result);
			else if (SelectP2.equals("atm") && SelectP1.equals("torr"))
				Result = Convert2ATMfromTORR(Result);
			else if (SelectP2.equals("atm") && SelectP1.equals("kPa"))
				Result = Convert2ATMfromKPA(Result);
			else if (SelectP2.equals("mmHg") && SelectP1.equals("atm"))
				Result = Convert2MMHGfromATM(Result);
			else if (SelectP2.equals("mmHg") && SelectP1.equals("torr"))
				Result = Convert2MMHGfromTORR(Result);
			else if (SelectP2.equals("mmHg") && SelectP1.equals("kPa"))
				Result = Convert2MMHGfromKPA(Result);
			else if (SelectP2.equals("torr") && SelectP1.equals("atm"))
				Result = Convert2TORRfromATM(Result);
			else if (SelectP2.equals("torr") && SelectP1.equals("mmHg"))
				Result = Convert2TORRfromMMHG(Result);
			else if (SelectP2.equals("torr") && SelectP1.equals("kPa"))
				Result = Convert2TORRfromKPA(Result);
			else if (SelectP2.equals("kPa") && SelectP1.equals("atm"))
				Result = Convert2KPAfromATM(Result);
			else if (SelectP2.equals("kPa") && SelectP1.equals("mmHg"))
				Result = Convert2KPAfromMMHG(Result);
			else if (SelectP2.equals("kPa") && SelectP1.equals("torr"))
				Result = Convert2KPAfromTORR(Result);
			else if (SelectP2.equals("atm") && SelectP1.equals("atm"))
				nothing = Result;
			else if (SelectP2.equals("mmHg") && SelectP1.equals("mmHg"))
				nothing = Result;
			else if (SelectP2.equals("torr") && SelectP1.equals("torr"))
				nothing = Result;
			else if (SelectP2.equals("kPa") && SelectP1.equals("kPa"))
				nothing = Result;
			else
				MySingleton.getInstance().addError("\"Gas Law: Error converting P2\"");
		}
		return Result;
	}

	public double convertTemperature(double Answer, double T1, double T2)
	{
		double Result = Answer;
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectT2 = MySingleton.getInstance().getSelectT2();
		@SuppressWarnings("unused")
		double nothing = 0;

		if (T1 == -9876.5)
		{
			if (SelectT1.equals("K") && SelectT2.equals("C"))
				Result = Convert2KfromC(Result);
			else if (SelectT1.equals("K") && SelectT2.equals("F"))
				Result = Convert2KfromF(Result);
			else if (SelectT1.equals("C") && SelectT2.equals("K"))
				Result = Convert2CfromK(Result);
			else if (SelectT1.equals("C") && SelectT2.equals("F"))
				Result = Convert2CfromF(Result);
			else if (SelectT1.equals("F") && SelectT2.equals("K"))
				Result = Convert2FfromK(Result);
			else if (SelectT1.equals("F") && SelectT2.equals("C"))
				Result = Convert2FfromC(Result);
			else if (SelectT1.equals("K") && SelectT2.equals("K"))
				nothing = Result;
			else if (SelectT1.equals("C") && SelectT2.equals("C"))
				nothing = Result;
			else if (SelectT1.equals("F") && SelectT2.equals("F"))
				nothing = Result;
			else
				MySingleton.getInstance().addError("\"Gas Law: Error converting T1\"");
		}
		else if (T2 == -9876.5)
		{
			if (SelectT2.equals("K") && SelectT1.equals("C"))
				Result = Convert2KfromC(Result);
			else if (SelectT2.equals("K") && SelectT1.equals("F"))
				Result = Convert2KfromF(Result);
			else if (SelectT2.equals("C") && SelectT1.equals("K"))
				Result = Convert2CfromK(Result);
			else if (SelectT2.equals("C") && SelectT1.equals("F"))
				Result = Convert2CfromF(Result);
			else if (SelectT2.equals("F") && SelectT1.equals("K"))
				Result = Convert2FfromK(Result);
			else if (SelectT2.equals("F") && SelectT1.equals("C"))
				Result = Convert2FfromC(Result);
			else if (SelectT2.equals("K") && SelectT1.equals("K"))
				nothing = Result;
			else if (SelectT2.equals("C") && SelectT1.equals("C"))
				nothing = Result;
			else if (SelectT2.equals("F") && SelectT1.equals("F"))
				nothing = Result;
			else
				MySingleton.getInstance().addError("\"Gas Law: Error converting T2\"");
		}
		return Result;
	}

	/*
	 * Show your work!
	 */

	public String Boyles_Law_Work(double V1, double P1, double V2, double P2)
	{
		SelectV1 = MySingleton.getInstance().getSelectV1();
		SelectV2 = MySingleton.getInstance().getSelectV2();
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectP2 = MySingleton.getInstance().getSelectP2();
		
		String Work = "";
		double Result = 0;
		boolean Converted = false;
		int Step = 1;
		
		if (P1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1V1 = P2V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P1... \nP1 = (V2 * P2) / V1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP1 = (" + V2 + " " + SelectV2 + " * " + P2 + " " + SelectP2 +") / " + V1 + " " + SelectV1;
			Step++;
			
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nP1 = (" + V2 + " " + SelectV1 + " * " + P2 + " " + SelectP2 +") / " + V1 + " " + SelectV1;
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP1 = " + (V2 * P2) + " / " + V1 + " " + SelectV1;
			Step++;
			
			Result = (V2 * P2) / V1;

			Work += "\n\n[Step " + Step + "]\nDivide... \nP1 = " + ((V2 * P2) / V1) + " " + SelectP2;
			Step++;
			
			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			
			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP1 + "... \nP1 = " + Result + " " + SelectP1;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP1;
			
			return Work;
		}
		else if (P2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1V1 = P2V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P2... \nP2 = (V1 * P1) / V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP2 = (" + V1 + " " + SelectV1 + " * " + P1 + " " + SelectP1 +") / " + V2 + " " + SelectV2;
			Step++;
			
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nP2 = (" + V1 + " " + SelectV1 + " * " + P1 + " " + SelectP2 +") / " + V2 + " " + SelectV1;
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP2 = " + (V1 * P1) + " / " + V2 + " " + SelectV1;
			Step++;

			Result = (V1 * P1) / V2;

			Work += "\n\n[Step " + Step + "]\nDivide... \nP2 = " + ((V1 * P1) / V2) + " " + SelectP1;
			Step++;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);
			
			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP2 + "... \nP2 = " + Result + " " + SelectP2;
			//818 551 - 
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP2;
			
			return Work;
		}
		else if (V1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1V1 = P2V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V1... \nV1 = (V2 * P2) / P1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV1 = (" + V2 + " " + SelectV2 + " * " + P2 + " " + SelectP2 +") / " + P1 + " " + SelectP1;
			Step++;
			
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law SW: Gas Unit Logic Error (Pressure)");

				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nV1 = (" + V2 + " " + SelectV2 + " * " + P2 + " " + SelectP1 +") / " + P1 + " " + SelectP1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nV1 = " + (V2 * P2) + " / " + P1 + " " + SelectP1;
			Step++;
			
			Result = (V2 * P2) / P1;

			Work += "\n\n[Step " + Step + "]\nDivide... \nV1 = " + ((V2 * P2) / P1) + " " + SelectV2;
			Step++;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV1 + "... \nV1 = " + Result + " " + SelectV1;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV1;
			
			return Work;
		}
		else if (V2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1V1 = P2V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V2... \nV2 = (V1 * P1) / P2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV2 = (" + V1 + " " + SelectV1 + " * " + P1 + " " + SelectP1 +") / " + P2 + " " + SelectP2;
			Step++;
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Boyle's Law SW: Gas Unit Logic Error (Pressure)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nV2 = (" + V1 + " " + SelectV1 + " * " + P1 + " " + SelectP1 +") / " + P2 + " " + SelectP1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nV2 = " + (V1 * P1) + " / " + P2 + " " + SelectP1;
			Step++;

			Result = (V1 * P1) / P2;

			Work += "\n\n[Step " + Step + "]\nDivide... \nV2 = " + ((V1 * P1) / P2) + " " + SelectV1;
			Step++;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);
			
			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV2 + "... \nV2 = " + Result + " " + SelectV2;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV2;
			
			return Work;
		}
		else
			return Work;
	}
	
	public String Charles_Law_Work(double V1, double T1, double V2, double T2)
	{
		SelectV1 = MySingleton.getInstance().getSelectV1();
		SelectV2 = MySingleton.getInstance().getSelectV2();
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectT2 = MySingleton.getInstance().getSelectT2();
		
		double Result = 0;
		boolean Converted = false;
		boolean ConvertedTemp = false;
		String Work = "";
		int Step = 1;

		if (V1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nV1 / T1 = V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V1... \nV1 = (V2 * T1) / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV1 = (" + V2 + " " + SelectV2 + " * " + T1 + " " + SelectT1 +") / " + T2 + " " + SelectT2;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				Converted = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nV1 = (" + V2 + " " + SelectV2 + " * " + T1 + " K" + ") / " + T2 + " K";
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nV1 = " + (V2 * T1) + " / " + T2 + " K";
			Step++;

			Result = (V2 * T1) / T2;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nV1 = " + ((V2 * T1) / T2) + " " + SelectV2;
			Step++;

			if(Converted)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
			
			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV1 + "... \nV1 = " + Result + " " + SelectV1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV1;
			
			return Work;
		}

		else if (T1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nV1 / T1 = V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T1... \nT1 = (V1 * T2) / V2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT1 = (" + V1 + " " + SelectV1 + " * " + T2 + " " + SelectT2 +") / " + V2 + " " + SelectV2;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T2 = Misc.toKelvins(T2, 2);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT1 = (" + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / " + V2 + " " + SelectV2;
				Step++;
			}

			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Charle's Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nT1 = (" + V1 + " " + SelectV1 + " * " + T2 + " K" +") / " + V2 + " " + SelectV1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nT1 = " + (V1 * T2) + " / " + V2 + " " + SelectV1;
			Step++;

			Result = (V1 * T2) / V2;

			Work += "\n\n[Step " + Step + "]\nDivide... \nT1 = " + ((V1 * T2) / V2) + " " + SelectT2;
			Step++;
			
			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			if(ConvertedTemp)
				T2 = Misc.revertTemp(T2, 2);
			
			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT1 + "... \nT1 = " + Result + " " + SelectT1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT1;
			
			return Work;
		}

		else if (V2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nV1 / T1 = V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V2... \nV2 = (V1 * T2) / T1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV2 = (" + V1 + " " + SelectV1 + " * " + T2 + " " + SelectT2 +") / " + T1 + " " + SelectT1;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				Converted = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nV2 = (" + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / " + T1 + " K";
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nV2 = " + (V1 * T2) + " / " + T1 + " K";
			Step++;
			
			Result = (V1 * T2) / T1;

			Work += "\n\n[Step " + Step + "]\nDivide... \nV2 = " + ((V1 * T2) / T1) + " " + SelectV1;
			Step++;
			
			if(Converted)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
			
			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV2 + "... \nV2 = " + Result + " " + SelectV2;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV2;
			
			return Work;
		}

		else if (T2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nV1 / T1 = V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T2... \nT2 = (T1 * V2) / V1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT2 = (" + T1 + " " + SelectT1 + " * " + V2 + " " + SelectV2 +") / " + V1 + " " + SelectV1;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT2 = (" + T1 + " K" + " * " + V2 + " " + SelectV2 + ") / " + V1 + " " + SelectV1;
				Step++;
			}
			
			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectV1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Charle's Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nT2 = (" + T1 + " K" + " * " + V2 + " " + SelectV1 +") / " + V1 + " " + SelectV1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nT2 = " + (T1 * V2) + " / " + V1 + " " + SelectV1;
			Step++;
			
			Result = (T1 * V2) / V1;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nT2 = " + ((T1 * V2) / V1) + " " + SelectT1;
			Step++;
			
			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			if(ConvertedTemp)
				T1 = Misc.revertTemp(T1, 1);
			
			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT2 + "... \nT2 = " + Result + " " + SelectT2;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT2;

			return Work;
		}
		else
			return Work;
	}
	
	public String GayLussac_Law_Work(double P1, double T1, double P2, double T2)
	{
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectP2 = MySingleton.getInstance().getSelectP2();
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectT2 = MySingleton.getInstance().getSelectT2();
		
		double Result = 0;
		boolean Converted = false;
		boolean ConvertedTemp = false;
		String Work = "";
		int Step = 1;
		
		if (P1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 / T1 = P2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P1... \nP1 = (P2 * T1) / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP1 = (" + P2 + " " + SelectP2 + " * " + T1 + " " + SelectT1 +") / " + T2 + " " + SelectT2;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				Converted = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nP1 = (" + P2 + " " + SelectP2 + " * " + T1 + " K" + ") / " + T2 + " K";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP1 = " + (P2 * T1) + " / " + T2 + " K";
			Step++;
			
			Result = (P2 * T1) / T2;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nP1 = " + ((P2 * T1) / T2) + " " + SelectP2;
			Step++;
			
			if(Converted)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
			
			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP1 + "... \nP1 = " + Result + " " + SelectP1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP1;
			
			return Work;
		}
		else if (T1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 / T1 = P2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T1... \nT1 = (P1 * T2) / P2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT1 = (" + P1 + " " + SelectP1 + " * " + T2 + " " + SelectT2 +") / " + P2 + " " + SelectP2;
			Step++;
			
			if(!SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T2 = Misc.toKelvins(T2, 2);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT1 = (" + P1 + " " + SelectP1 + " * " + T2 + " K" + ") / " + P2 + " " + SelectP2;
				Step++;
			}
			
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nT1 = (" + P1 + " " + SelectP1 + " * " + T2 + " K" +") / " + P2 + " " + SelectP1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nT1 = " + (P1 * T2) + " / " + P2 + " " + SelectP1;
			Step++;
			
			Result = (P1 * T2) / P2;

			Work += "\n\n[Step " + Step + "]\nDivide... \nT1 = " + ((P1 * T2) / P2) + " " + SelectT2;
			Step++;
			
			if(Converted)
				P2 = Misc.revertPressure(P2, 2);
			
			if(ConvertedTemp)
				T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT1 + "... \nT1 = " + Result + " " + SelectT1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT1;
			
			return Work;
		}
		else if (P2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 / T1 = P2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P2... \nP2 = (P1 * T2) / T1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP2 = (" + P1 + " " + SelectP1 + " * " + T2 + " " + SelectT2 +") / " + T1 + " " + SelectT1;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				Converted = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nP2 = (" + P1 + " " + SelectP1 + " * " + T2 + " K" + ") / " + T1 + " K";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP2 = " + (P1 * T2) + " / " + T1 + " K";
			Step++;
			
			Result = (P1 * T2) / T1;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nP2 = " + ((P1 * T2) / T1) + " " + SelectP1;
			Step++;
			
			if(Converted)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
			
			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP2 + "... \nP2 = " + Result + " " + SelectP2;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP2;
			
			return Work;
		}
		else if (T2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 / T1 = P2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T2... \nT2 = (T1 * P2) / P1";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT2 = (" + T1 + " " + SelectT1 + " * " + P2 + " " + SelectP2 +") / " + P1 + " " + SelectP1;
			Step++;
			
			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT2 = (" + T1 + " K" + " * " + P2 + " " + SelectP2 + ") / " + P1 + " " + SelectP1;
				Step++;
			}
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nT2 = (" + T1 + " K" + " * " + P2 + " " + SelectP1 +") / " + P1 + " " + SelectP1;
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nT2 = " + (T1 * P2) + " / " + P1 + " " + SelectP1;
			Step++;
			
			Result = (P2 * T1) / P1;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nT2 = " + ((T1 * P2) / P1) + " " + SelectT1;
			Step++;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);
			
			if(ConvertedTemp)
				T1 = Misc.revertTemp(T1, 1);
			
			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT2 + "... \nT2 = " + Result + " " + SelectT2;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT2;
			
			return Work;
		}
		else
			return Work;
	}
	
	public String IdealGas_Law_Work(double n, double P, double V, double T)
	{
		SelectV = MySingleton.getInstance().getSelectV();
		SelectP = MySingleton.getInstance().getSelectP();
		SelectT = MySingleton.getInstance().getSelectT();
		
		double R = 0.08206;
		double Result = 0;
		boolean ConvertedVol = false;
		boolean ConvertedPres = false;
		boolean ConvertedTemp = false;
		String Work = "";
		int Step = 1;
		
		if (n == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nPV = nRT";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for n... \nn = (P * V) / (R * T)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nn = (" + P + " " + SelectP + " * " + V + " " + SelectV +") / (" + R + " * " + T + " " + SelectT + ")";
			Step++;
			
			if(!SelectT.equals("K"))
			{
				ConvertedTemp = true;
				T = Misc.toKelvins(T, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert temperature to Kelvins... \nn = (" + P + " " + SelectP + " * " + V + " " + SelectV +") / (" + R + " * " + T + " K" + ")";
				Step++;
			}
			if(!SelectP.equals("atm"))
			{
				ConvertedPres = true;
				P = Misc.toAtmospheres(P, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert pressure to Atmospheres... \nn = (" + P + " atm" + " * " + V + " " + SelectV +") / (" + R + " * " + T + " K" + ")";
				Step++;
			}
			if(!SelectV.equals("L"))
			{
				ConvertedVol = true;
				V = Misc.toLiters(V, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert volume to Liters... \nn = (" + P + " atm" + " * " + V + " L" +") / (" + R + " * " + T + " K" + ")";
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in paratheses... \nn = " + (P * V) + " / " + (R * T);
			Step++;
			
			Result = (P * V) / (R * T);
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nn = " + ((P * V) / (R * T));
			Step++;

			if(ConvertedTemp)
				T = Misc.revertTemp(T, 0);
			if(ConvertedPres)
				P = Misc.revertPressure(P, 0);
			if(ConvertedVol)
				V = Misc.revertVolume(V, 0);

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " mol";
			
			return Work;
		}
		else if (P == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nPV = nRT";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P... \nP = (n * R * T) / V";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP = (" + n + " mols" + " * " + R + " * " + T + " " + SelectT +") / " + V + " " + SelectV;
			Step++;
			
			if(!SelectT.equals("K"))
			{
				ConvertedTemp = true;
				T = Misc.toKelvins(T, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert temperature to Kelvins... \nP = (" + n + " mol" + " * " + R + " * " + T + " " + SelectT +") / " + V + " " + SelectV;
				Step++;
			}
			if(!SelectV.equals("L"))
			{
				ConvertedVol = true;
				V = Misc.toLiters(V, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert volume to Liters... \nP = (" + n + " mol" + " * " + R + " * " + T + " K" +") / " + V + " L";
				Step++;
			}

			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in paratheses... \nP = " + (n * R * T) + " / " + V + " L";
			Step++;

			Result = (n * R * T) / V;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nP = " + ((n * R * T) / V) + " atm";
			Step++;
			
			if(ConvertedTemp)
				T = Misc.revertTemp(T, 0);
			if(ConvertedVol)
				V = Misc.revertVolume(V, 0);
			
			if(!SelectP.equals("atm"))
			{
				if (SelectP.equals("mmHg"))
					Result = Convert2MMHGfromATM(Result);
				else if (SelectP.equals("torr"))
					Result = Convert2TORRfromATM(Result);
				else if (SelectP.equals("kPa"))
					Result = Convert2KPAfromATM(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law SW: Error converting P");
				
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP + "... \nP = " + Result + " " + SelectP;
				Step++;
			}
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP;

			return Work;
		}
		else if (V == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nPV = nRT";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V... \nV = (n * R * T) / P";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV = (" + n + " mol" + " * " + R + " * " + T + SelectT +") / " + P + " " + SelectP;
			Step++;
			
			if(!SelectT.equals("K"))
			{
				ConvertedTemp = true;
				T = Misc.toKelvins(T, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert temperature to Kelvins... \nV = (" + n + " mol" + " * " + R + " * " + T + " K" +") / " + P + " " + SelectP;
				Step++;
			}
			if(!SelectP.equals("atm"))
			{
				ConvertedPres = true;
				P = Misc.toAtmospheres(P, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert pressure to Atmospheres... \nV = (" + n + " mol" + " * " + R + " * " + T + " K" +") / " + P + " atm";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in paratheses... \nV = " + (n * R * T) + " / " + P + " atm";
			Step++;
			
			Result = (n * R * T) / P;
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nV = " + ((n * R * T) / P) + " L";
			Step++;
			
			if(ConvertedTemp)
				T = Misc.revertTemp(T, 0);
			if(ConvertedPres)
				P = Misc.revertPressure(P, 0);
			
			if(!SelectV.equals("L"))
			{
				if (SelectP.equals("mL"))
					Result = Convert2MLfromL(Result);
				else if (SelectP.equals("g"))
					Result = Convert2GfromL(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law SW: Error converting V");
				
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV + "... \nV = " + Result + " " + SelectV;
				Step++;
			}
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV;
			
			return Work;
		}
		else if (T == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nPV = nRT";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T... \nT = (P * V) / (n * R)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT = (" + P + " " + SelectP + " * " + V + " " + SelectV + ") / (" + n + " mol" + " * " + R + ")";
			Step++;
			
			if(!SelectP.equals("atm"))
			{
				ConvertedPres = true;
				P = Misc.toAtmospheres(P, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert pressure to Atmospheres... \nT = (" + P + " atm" + " * " + V + " " + SelectV +") / (" + n + " mol" + " * " + R + ")";
				Step++;
			}
			if(!SelectV.equals("L"))
			{
				ConvertedVol = true;
				V = Misc.toLiters(V, 0);
				
				Work += "\n\n[Step " + Step + "]\nConvert volume to Liters... \nT = (" + P + " atm" + " * " + V + " L" +") / (" + n + " mol" + " * " + R + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in paratheses... \nT = " + (P * V) + " / " + (n * R);
			Step++;
			
			Result = (P * V) / (n * R);
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nn = " + ((P * V) / (n * R)) + " K";
			Step++;
			
			if(ConvertedPres)
				P = Misc.revertPressure(P, 0);
			if(ConvertedVol)
				V = Misc.revertVolume(V, 0);
			
			if(!SelectT.equals("K"))
			{
				if (SelectT.equals("C"))
					Result = Convert2CfromK(Result);
				else if (SelectT.equals("F"))
					Result = Convert2FfromK(Result);
				else
					MySingleton.getInstance().addError("Ideal Gas Law SW: Error converting T");
				
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT + "... \nT = " + Result + " " + SelectT;
				Step++;
			}
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT;
			
			return Work;
		}
		else
			return Work;
	}
	
	public String CombinedGas_Law_Work(double V1, double P1, double T1, double V2, double P2, double T2)
	{
		SelectV1 = MySingleton.getInstance().getSelectV1();
		SelectV2 = MySingleton.getInstance().getSelectV2();
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectP2 = MySingleton.getInstance().getSelectP2();
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectT2 = MySingleton.getInstance().getSelectT2();
		
		double Result = 0;
		boolean Converted = false;
		boolean ConvertedVol = false;
		boolean ConvertedPres = false;
		boolean ConvertedTemp = false;
		String Work = "";
		int Step = 1;
		
		if (P1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P1... \nP1 = (P2 * V2 * T1) / (V1 * T2)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP1 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV2 + " * " + T1 + SelectT1 + ") / (" + V1 + " " + SelectV1 + " * " + T2 + SelectT2 + ")";
			Step++;

			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nP1 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV2 + " * " + T1 + " K" + ") / (" + V1 + " " + SelectV1 + " * " + T2 + " K" + ")";
				Step++;
			}

			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nP1 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV1 + " * " + T1 + " K" + ") / (" + V1 + " " + SelectV1 + " * " + T2 + " K" + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP1 = " + (P2 * V2 * T1) + " / " + (V1 * T2);
			Step++;

			Result = (P2 * V2 * T1) / (V1 * T2);

			Work += "\n\n[Step " + Step + "]\nDivide... \nP1 = " + ((P2 * V2 * T1) / (V1 * T2)) + " " + SelectP2;
			Step++;

			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			if(ConvertedTemp)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}

			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP1 + "... \nP1 = " + Result + " " + SelectP1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP1;
			
			return Work;
		}
		else if (V1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V1... \nV1 = V2 * (P2 / P1) * (T1 * T2)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV1 = " + V2 + " " + SelectV2 + " (" + P2 + " " + SelectP2 + " / " + P1 + SelectP1 + ") * (" + T1 + " " + SelectT1 + " * " + T2 + SelectT2 + ")";
			Step++;

			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nV1 = " + V2 + " " + SelectV2 + " (" + P2 + " " + SelectP2 + " / " + P1 + " " + SelectP1 + ") * (" + T1 + " K" + " * " + T2 + " K" + ")";
				Step++;
			}

			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nV1 = " + V2 + " " + SelectV2 + " (" + P2 + " " + SelectP1 + " * " + P1 + " " + SelectP1 + ") * (" + T1 + " K" + " * " + T2 + " K" + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply and divide the numbers in parantheses... \nV1 = " + V2 + " " + SelectV2 + " * " + (P2 / P1) + " * " + (T1 * T2);
			Step++;

			Result = V2 * (P2 / P1) * (T1 * T2);
			
			Work += "\n\n[Step " + Step + "]\nMultiply them all... \nV1 = " + (V2 * (P2 / P1) * (T1 * T2)) + " " + SelectV2;
			Step++;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);

			if(ConvertedTemp)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
				
			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV1 + "... \nV1 = " + Result + " " + SelectV1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV1;
			
			return Work;
		}
		else if (T1 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T1... \nT1 = (P1 / P2) / (V1 / V2) * T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT1 = (" + P1 + " " + SelectP1 + " / " + P2 + " " + SelectP2 + ") / (" + V1 + " " + SelectV1 + " / " + V2 + SelectV2 + ") * " + T2 + SelectT2;
			Step++;
			
			if(!SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT1 = (" + P1 + " " + SelectP1 + " / " + P2 + " " + SelectP2 + ") / (" + V1 + " " + SelectV1 + " / " + V2 + " " + SelectV2 + ") * " + T2 + " K";
				Step++;
			}
				
			if(!SelectV1.equals(SelectV2))
			{
				ConvertedVol = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law SW: Gas Unit Logic Error (Volume)");

				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nT1 = (" + P1 + " " + SelectP1 + " / " + P2 + " " + SelectP2 + ") / (" + V1 + " " + SelectV1 + " / " + V2 + " " + SelectV1 + ") * " + T2 + " K";
				Step++;
			}
			if(!SelectP1.equals(SelectP2))
			{
				ConvertedPres = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");

				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nT1 = (" + P1 + " " + SelectP1 + " / " + P2 + " " + SelectP1 + ") / (" + V1 + " " + SelectV1 + " / " + V2 + " " + SelectV1 + ") * " + T2 + " K";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nDivide the numbers in parantheses... \nT1 = " + (P1 / P2) + " / " + (V1 / V2) + " * " + T2 + " K";
			Step++;

			Result = (P1 / P2) / (V1 / V2) * T2;
			
			Work += "\n\n[Step " + Step + "]\nDivide then multiply... \nT1 = " + ((P1 / P2) / (V1 / V2) * T2) + " K";
			Step++;

			if(ConvertedVol)
				V2 = Misc.revertVolume(V2, 2);
			if(ConvertedPres)
				P2 = Misc.revertPressure(P2, 2);

			if(ConvertedTemp)
				T2 = Misc.revertTemp(T2, 2);

			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT1 + "... \nT1 = " + Result + " " + SelectT1;

			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT1;
			
			return Work;
		}
		else if (P2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for P2... \nP2 = (P1 * V1 * T2) / (T1 * V2)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nP2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " " + SelectT2 + ") / (" + T1 + " " + SelectT1 + " * " + V2 + " " + SelectV2 + ")";
			Step++;

			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);
				
				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nP2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / (" + T1 + " K" + " * " + V2 + " " + SelectV2 + ")";
				Step++;
			}

			if(!SelectV1.equals(SelectV2))
			{
				Converted = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law SW: Gas Unit Logic Error (Volume)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nP2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / (" + T1 + " K" + " * " + V2 + " " + SelectV1 + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nP2 = " + (P1 * V1 * T2) + " / " + (T1 * V2);
			Step++;
			
			Result = (P1 * V1 * T2) / (T1 * V2);
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nP2 = " + ((P1 * V1 * T2) / (T1 * V2)) + " " + SelectP1;
			Step++;
			
			if(Converted)
				V2 = Misc.revertVolume(V2, 2);

			if(ConvertedTemp)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}
			
			Result = convertPressure(Result, P1, P2);
			if(!SelectP1.equals(SelectP2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectP2 + "... \nP2 = " + Result + " " + SelectP2;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectP2;
			
			return Work;
		}
		else if (V2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for V2... \nV2 = (P1 * V1 * T2) / (P2 * T1)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nV2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " " + SelectT2 + ") / (" + P2 + " " + SelectP2 + " * " + T1 + " " + SelectT1 + ")";
			Step++;

			if(!SelectT1.equals("K") || !SelectT2.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);
				T2 = Misc.toKelvins(T2, 2);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nV2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / (" + P2 + " " + SelectP2 + " * " + T1 + " K" + ")";
				Step++;
			}
			
			if(!SelectP1.equals(SelectP2))
			{
				Converted = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");
				
				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nV2 = (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + " * " + T2 + " K" + ") / (" + P2 + " " + SelectP1 + " * " + T1 + " K" + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nV2 = " + (P1 * V1 * T2) + " / " + (P2 * T1);
			Step++;

			Result = (P1 * V1 * T2) / (P2 * T1);
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nV2 = " + ((P1 * V1 * T2) / (P2 * T1)) + " " + SelectV1;
			Step++;

			if(Converted)
				P2 = Misc.revertPressure(P2, 2);
			
			if(ConvertedTemp)
			{
				T1 = Misc.revertTemp(T1, 1);
				T2 = Misc.revertTemp(T2, 2);
			}

			Result = convertVolume(Result, V1, V2);
			if(!SelectV1.equals(SelectV2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectV2 + "... \nV2 = " + Result + " " + SelectV2;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectV2;
			
			return Work;
		}
		else if (T2 == -9876.5)
		{
			Work = "[Step " + Step + "]\nWrite down the formula... \nP1 * V1 / T1 = P2 * V2 / T2";
			Step++;
			Work += "\n\n[Step " + Step + "]\nRearrange to solve for T2... \nT2 = (P2 * V2 * T1) / (P1 * V1)";
			Step++;
			Work += "\n\n[Step " + Step + "]\nPlug in your variables... \nT2 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV2 + " * " + T1 + " " + SelectT1 + ") / (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + ")";
			Step++;
			
			
			if(!SelectT1.equals("K"))
			{
				ConvertedTemp = true;
				T1 = Misc.toKelvins(T1, 1);

				Work += "\n\n[Step " + Step + "]\nConvert your temperature to Kelvins... \nT2 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV2 + " * " + T1 + " K" + ") / (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + ")";
				Step++;
			}

			if(!SelectV1.equals(SelectV2))
			{
				ConvertedVol = true;
				if(SelectV1.equals("L"))
					V2 = Misc.toLiters(V2, 2);
				else if(SelectV1.equals("mL"))
					V2 = Misc.toMilliliters(V2, 2);
				else if(SelectP1.equals("g"))
					V2 = Misc.toGrams(V2, 2);
				else
					MySingleton.getInstance().addError("Combined Gas Law SW: Gas Unit Logic Error (Volume)");

				Work += "\n\n[Step " + Step + "]\nMake sure both volume units are the same by converting one of them... \nT2 = (" + P2 + " " + SelectP2 + " * " + V2 + " " + SelectV1 + " * " + T1 + " K" + ") / (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + ")";
				Step++;
			}
			if(!SelectP1.equals(SelectP2))
			{
				ConvertedPres = true;
				if(SelectP1.equals("atm"))
					P2 = Misc.toAtmospheres(P2, 2);
				else if(SelectP1.equals("mmHg"))
					P2 = Misc.toMillimeterOfMercury(P2, 2);
				else if(SelectP1.equals("torr"))
					P2 = Misc.toTorr(P2, 2);
				else if(SelectP1.equals("kPa"))
					P2 = Misc.toKilopascals(P2, 2);
				else
					MySingleton.getInstance().addError("Gay Lussac's Law SW: Gas Unit Logic Error (Pressure)");

				Work += "\n\n[Step " + Step + "]\nMake sure both pressure units are the same by converting one of them... \nT2 = (" + P2 + " " + SelectP1 + " * " + V2 + " " + SelectV1 + " * " + T1 + " K" + ") / (" + P1 + " " + SelectP1 + " * " + V1 + " " + SelectV1 + ")";
				Step++;
			}
			
			Work += "\n\n[Step " + Step + "]\nMultiply the numbers in parantheses... \nT2 = " + (P2 * V2 * T1) + " / " + (P1 * V1);
			Step++;
			
			Result = (P2 * V2 * T1) / (P1 * V1);
			
			Work += "\n\n[Step " + Step + "]\nDivide... \nT2 = " + ((P2 * V2 * T1) / (P1 * V1)) + " K";
			Step++;
			
			if(ConvertedVol)
				V2 = Misc.revertVolume(V2, 2);
			if(ConvertedPres)
				P2 = Misc.revertPressure(P2, 2);
			
			if(ConvertedTemp)
				T1 = Misc.revertTemp(T1, 1);

			Result = convertTemperature(Result, T1, T2);
			if(!SelectT1.equals(SelectT2))
				Work += "\n\n[Step " + Step + "]\nConvert to " + SelectT2 + "... \nT2 = " + Result + " " + SelectT2;
			
			Result = Misc.decimalPrecisionAssign(Result);
			Work += "\n\nYour answer is: " + Result + " " + SelectT2;
			
			return Work;
		}
		else
			return Work;
	}
}
