package com.pk.chemhelp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.text.DecimalFormat;

public class Misc extends Conversions
{
	protected DataStorage appState;
	static Chemical[] Chem;

	private static String SelectV = MySingleton.getInstance().getSelectV();
	private static String SelectV1 = MySingleton.getInstance().getSelectV1();
	private static String SelectV2 = MySingleton.getInstance().getSelectV2();
	private static String SelectP = MySingleton.getInstance().getSelectP();
	private static String SelectP1 = MySingleton.getInstance().getSelectP1();
	private static String SelectP2 = MySingleton.getInstance().getSelectP2();
	private static String SelectT;
	private static String SelectT1;
	private static String SelectT2;

	private static int Previous_VUnit;
	private static int Current_VUnit;
	private static int Previous_PUnit;
	private static int Current_PUnit;
	private static int Previous_TUnit;
	private static int Current_TUnit;

	private static int Previous_V1Unit = MySingleton.getInstance().getPrevious_V1Unit();
	private static int Current_V1Unit = MySingleton.getInstance().getCurrent_V1Unit();
	private static int Previous_P1Unit = MySingleton.getInstance().getPrevious_P1Unit();
	private static int Current_P1Unit = MySingleton.getInstance().getCurrent_P1Unit();
	private static int Previous_T1Unit;
	private static int Current_T1Unit;
	private static int Previous_V2Unit = MySingleton.getInstance().getPrevious_V2Unit();
	private static int Current_V2Unit = MySingleton.getInstance().getCurrent_V2Unit();
	private static int Previous_P2Unit = MySingleton.getInstance().getPrevious_P2Unit();
	private static int Current_P2Unit = MySingleton.getInstance().getCurrent_P2Unit();
	private static int Previous_T2Unit;
	private static int Current_T2Unit;
	
	public Misc()
	{
		
	}
	
	public static double Convert(double input)
	{
		String category = MySingleton.getInstance().getCategory();
		int sub1 = MySingleton.getInstance().getSubcategory();
		int sub2 = MySingleton.getInstance().getSubcategory2();
		double in = input;
		if(category.equals("Temperature"))
		{
			if(sub1 == 0 && sub2 == 1)
				in = Conversions.Convert2CfromK(in);
			else if(sub1 == 0 && sub2 == 2)
				in = Conversions.Convert2FfromK(in);
			else if(sub1 == 1 && sub2 == 0)
				in = Conversions.Convert2KfromC(in);
			else if(sub1 == 1 && sub2 == 2)
				in = Conversions.Convert2FfromC(in);
			else if(sub1 == 2 && sub2 == 0)
				in = Conversions.Convert2KfromF(in);
			else if(sub1 == 2 && sub2 == 1)
				in = Conversions.Convert2CfromF(in);
			return in;
		}
		else if(category.equals("Pressure"))
		{
			if(sub1 == 0 && sub2 == 1)
				in = Conversions.Convert2MMHGfromATM(in);
			else if(sub1 == 0 && sub2 == 2)
				in = Conversions.Convert2TORRfromATM(in);
			else if(sub1 == 0 && sub2 == 3)
				in = Conversions.Convert2KPAfromATM(in);
			else if(sub1 == 1 && sub2 == 0)
				in = Conversions.Convert2ATMfromMMHG(in);
			else if(sub1 == 1 && sub2 == 2)
				in = Conversions.Convert2TORRfromMMHG(in);
			else if(sub1 == 1 && sub2 == 3)
				in = Conversions.Convert2KPAfromMMHG(in);
			else if(sub1 == 2 && sub2 == 0)
				in = Conversions.Convert2ATMfromTORR(in);
			else if(sub1 == 2 && sub2 == 1)
				in = Conversions.Convert2MMHGfromTORR(in);
			else if(sub1 == 2 && sub2 == 3)
				in = Conversions.Convert2KPAfromTORR(in);
			else if(sub1 == 3 && sub2 == 0)
				in = Conversions.Convert2ATMfromKPA(in);
			else if(sub1 == 3 && sub2 == 1)
				in = Conversions.Convert2MMHGfromKPA(in);
			else if(sub1 == 3 && sub2 == 2)
				in = Conversions.Convert2TORRfromKPA(in);
			return in;
		}
		
		else if(category.equals("Volume"))
		{
			if(sub1 == 0 && sub2 == 1)
				in = Conversions.Convert2MLfromL(in);
			else if(sub1 == 0 && sub2 == 2)
				in = Conversions.Convert2GfromL(in);
			else if(sub1 == 1 && sub2 == 0)
				in = Conversions.Convert2LfromML(in);
			else if(sub1 == 1 && sub2 == 2)
				in = Conversions.Convert2GfromML(in);
			else if(sub1 == 2 && sub2 == 0)
				in = Conversions.Convert2LfromG(in);
			else if(sub1 == 2 && sub2 == 1)
				in = Conversions.Convert2MLfromG(in);
			return in;
		}
		else
			return 0;
	}
	
	public static int getUnitValue(String U)
    {
		int value = 0;
		String category = MySingleton.getInstance().getCategory();
    	if(category.equals("Temperature"))
    	{
    		if(U.equals("F"))
    			value = 2;
    		else if(U.equals("C"))
    			value = 1;
    		else if(U.equals("K"))
    			value = 0;
    		return value;
    	}
    	else if(category.equals("Pressure"))
    	{
    		if(U.equals("atm"))
    			value = 0;
    		else if(U.equals("mmHg"))
    			value = 1;
    		else if(U.equals("torr"))
    			value = 2;
    		else if(U.equals("kPa"))
    			value = 3;
    		return value;
    	}
    	else
    	{
    		if(U.equals("L"))
    			value = 0;
    		else if(U.equals("mL"))
    			value = 1;
    		else if(U.equals("g"))
    			value = 2;
    		return value;
    	}
	}
	
	public static double decimalPrecisionAssign(double d)
	{
		String Precision = MySingleton.getInstance().getMaxDecimalAccuracy();
		double FormatedDecimal;
		DecimalFormat oneDForm   = new DecimalFormat("#.#");
        DecimalFormat twoDForm   = new DecimalFormat("#.##");
        DecimalFormat threeDForm = new DecimalFormat("#.###");
        DecimalFormat fourDForm  = new DecimalFormat("#.####");
        DecimalFormat fiveDForm  = new DecimalFormat("#.#####");
        DecimalFormat sixDForm   = new DecimalFormat("#.######");
        DecimalFormat sevenDForm = new DecimalFormat("#.#######");
        if(Precision.equals("1"))
        	FormatedDecimal   = Double.valueOf(oneDForm.format(d));
        else if(Precision.equals("2"))
        	FormatedDecimal   = Double.valueOf(twoDForm.format(d));
        else if(Precision.equals("3"))
        	FormatedDecimal = Double.valueOf(threeDForm.format(d));
        else if(Precision.equals("4"))
        	FormatedDecimal  = Double.valueOf(fourDForm.format(d));
        else if(Precision.equals("5"))
        	FormatedDecimal  = Double.valueOf(fiveDForm.format(d));
        else if(Precision.equals("6"))
        	FormatedDecimal   = Double.valueOf(sixDForm.format(d));
        else if(Precision.equals("7"))
        	FormatedDecimal = Double.valueOf(sevenDForm.format(d));
        else
        	FormatedDecimal = d;
        return FormatedDecimal;
	}
	
	public static double ConvertDecimalPrecisionAssign(double d)
	{
		String Precision = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
		double FormatedDecimal;
		DecimalFormat oneDForm   = new DecimalFormat("#.#");
        DecimalFormat twoDForm   = new DecimalFormat("#.##");
        DecimalFormat threeDForm = new DecimalFormat("#.###");
        DecimalFormat fourDForm  = new DecimalFormat("#.####");
        DecimalFormat fiveDForm  = new DecimalFormat("#.#####");
        DecimalFormat sixDForm   = new DecimalFormat("#.######");
        DecimalFormat sevenDForm = new DecimalFormat("#.#######");
        if(Precision.equals("1"))
        	FormatedDecimal   = Double.valueOf(oneDForm.format(d));
        else if(Precision.equals("2"))
        	FormatedDecimal   = Double.valueOf(twoDForm.format(d));
        else if(Precision.equals("3"))
        	FormatedDecimal = Double.valueOf(threeDForm.format(d));
        else if(Precision.equals("4"))
        	FormatedDecimal  = Double.valueOf(fourDForm.format(d));
        else if(Precision.equals("5"))
        	FormatedDecimal  = Double.valueOf(fiveDForm.format(d));
        else if(Precision.equals("6"))
        	FormatedDecimal   = Double.valueOf(sixDForm.format(d));
        else if(Precision.equals("7"))
        	FormatedDecimal = Double.valueOf(sevenDForm.format(d));
        else
        	FormatedDecimal = d;
        return FormatedDecimal;
	}
	
	public static String convertInt2String(int i)
	{
		return "" + i;
	}
	
	public static double toKelvins(double T, int Type)
	{
		double Temp = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_TUnit(0);
			SelectT = MySingleton.getInstance().getSelectT();
			if(SelectT.equals("K"))
			{
				Temp = T;
				MySingleton.getInstance().setPrevious_TUnit(0);
			}
			else if(SelectT.equals("C"))
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setPrevious_TUnit(1);
			}
			else if(SelectT.equals("F"))
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setPrevious_TUnit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToKelvins: T convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_T1Unit(0);
			SelectT1 = MySingleton.getInstance().getSelectT1();
			if(SelectT1.equals("K"))
			{
				Temp = T;
				MySingleton.getInstance().setPrevious_T1Unit(0);
			}
			else if(SelectT1.equals("C"))
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setPrevious_T1Unit(1);
			}
			else if(SelectT1.equals("F"))
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setPrevious_T1Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToKelvins: T1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_T2Unit(0);
			SelectT2 = MySingleton.getInstance().getSelectT2();
			if(SelectT2.equals("K"))
			{
				Temp = T;
				MySingleton.getInstance().setPrevious_T2Unit(0);
			}
			else if(SelectT2.equals("C"))
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setPrevious_T2Unit(1);
			}
			else if(SelectT2.equals("F"))
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setPrevious_T2Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToKelvins: T2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Temperature");
		}
		
		return Temp;
	}
	
	public static double toAtmospheres(double P, int Type)
	{
		double Pressure = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_PUnit(0);
			SelectP = MySingleton.getInstance().getSelectP();
			if(SelectP.equals("atm"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_TUnit(0);
			}
			else if(SelectP.equals("mmHg"))
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(SelectP.equals("torr"))
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setPrevious_PUnit(2);
			}
			else if(SelectP.equals("kPa"))
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToAtmospheres: P convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_P1Unit(0);
			SelectP1 = MySingleton.getInstance().getSelectP1();
			if(SelectP1.equals("atm"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(SelectP1.equals("mmHg"))
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(SelectP1.equals("torr"))
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(SelectP1.equals("kPa"))
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToAtmospheres: P1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_P2Unit(0);
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(SelectP2.equals("atm"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(SelectP2.equals("mmHg"))
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(SelectP2.equals("torr"))
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(SelectP2.equals("kPa"))
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToAtmospheres: P2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Pressure (atm)");
		}
		
		return Pressure;
	}
	
	public static double toMillimeterOfMercury(double P, int Type)
	{
		double Pressure = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_PUnit(1);
			SelectP = MySingleton.getInstance().getSelectP();
			if(SelectP.equals("atm"))
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(SelectP.equals("mmHg"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_TUnit(1);
			}
			else if(SelectP.equals("torr"))
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setPrevious_PUnit(2);
			}
			else if(SelectP.equals("kPa"))
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToMillimeterOfMercury: P convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_P1Unit(1);
			SelectP1 = MySingleton.getInstance().getSelectP1();
			if(SelectP1.equals("atm"))
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(SelectP1.equals("mmHg"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(SelectP1.equals("torr"))
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(SelectP1.equals("kPa"))
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToMillimeterOfMercury: P1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_P2Unit(1);
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(SelectP2.equals("atm"))
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(SelectP2.equals("mmHg"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(SelectP2.equals("torr"))
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(SelectP2.equals("kPa"))
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToMillimeterOfMercury: P2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Pressure (mmHg)");
		}
		
		return Pressure;
	}
	
	public static double toTorr(double P, int Type)
	{
		double Pressure = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_PUnit(2);
			SelectP = MySingleton.getInstance().getSelectP();
			if(SelectP.equals("atm"))
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(SelectP.equals("mmHg"))
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(SelectP.equals("torr"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_TUnit(2);
			}
			else if(SelectP.equals("kPa"))
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToTorr: P convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_P1Unit(2);
			SelectP1 = MySingleton.getInstance().getSelectP1();
			if(SelectP1.equals("atm"))
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(SelectP1.equals("mmHg"))
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(SelectP1.equals("torr"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(SelectP1.equals("kPa"))
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToTorr: P1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_P2Unit(2);
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(SelectP2.equals("atm"))
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(SelectP2.equals("mmHg"))
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(SelectP2.equals("torr"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(SelectP2.equals("kPa"))
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToTorr: P2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Pressure (Torr)");
		}
		
		return Pressure;
	}
	
	public static double toKilopascals(double P, int Type)
	{
		double Pressure = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_PUnit(3);
			SelectP = MySingleton.getInstance().getSelectP();
			if(SelectP.equals("atm"))
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(SelectP.equals("mmHg"))
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(SelectP.equals("torr"))
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setPrevious_PUnit(2);
			}
			else if(SelectP.equals("kPa"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_TUnit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToKilopascals: P convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_P1Unit(3);
			SelectP1 = MySingleton.getInstance().getSelectP1();
			if(SelectP1.equals("atm"))
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(SelectP1.equals("mmHg"))
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(SelectP1.equals("torr"))
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(SelectP1.equals("kPa"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToKilopascals: P1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_P2Unit(3);
			SelectP2 = MySingleton.getInstance().getSelectP2();
			if(SelectP2.equals("atm"))
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(SelectP2.equals("mmHg"))
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(SelectP2.equals("torr"))
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(SelectP2.equals("kPa"))
			{
				Pressure = P;
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else
			{
				MySingleton.getInstance().addError("ToKilopascals: P2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Pressure (kPa)");
		}
		
		return Pressure;
	}
	
	public static double toLiters(double V, int Type)
	{
		double Volume = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_VUnit(0);
			SelectV = MySingleton.getInstance().getSelectV();
			if(SelectV.equals("L"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_VUnit(0);
			}
			else if(SelectV.equals("mL"))
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setPrevious_VUnit(1);
			}
			else if(SelectV.equals("g"))
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setPrevious_VUnit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToLiters: V convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_V1Unit(0);
			SelectV1 = MySingleton.getInstance().getSelectV1();
			if(SelectV1.equals("L"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V1Unit(0);
			}
			else if(SelectV1.equals("mL"))
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setPrevious_V1Unit(1);
			}
			else if(SelectV1.equals("g"))
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setPrevious_V1Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToLiters: V1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_V2Unit(0);
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(SelectV2.equals("L"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V2Unit(0);
			}
			else if(SelectV2.equals("mL"))
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setPrevious_V2Unit(1);
			}
			else if(SelectV2.equals("g"))
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setPrevious_V2Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToLiters: V2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Volume (L)");
		}
		
		return Volume;
	}
	
	public static double toMilliliters(double V, int Type)
	{
		double Volume = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_VUnit(1);
			SelectV = MySingleton.getInstance().getSelectV();
			if(SelectV.equals("L"))
			{
				Volume = Convert2MLfromL(V);
				MySingleton.getInstance().setPrevious_VUnit(0);
			}
			else if(SelectV.equals("mL"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_VUnit(1);
			}
			else if(SelectV.equals("g"))
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setPrevious_VUnit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToMilliliters: V convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_V1Unit(1);
			SelectV1 = MySingleton.getInstance().getSelectV1();
			if(SelectV1.equals("L"))
			{
				Volume = Convert2MLfromL(V);
				MySingleton.getInstance().setPrevious_V1Unit(0);
			}
			else if(SelectV1.equals("mL"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V1Unit(1);
			}
			else if(SelectV1.equals("g"))
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setPrevious_V1Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToMilliliters: V1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_V2Unit(1);
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(SelectV2.equals("L"))
			{
				Volume = Convert2MLfromL(V);
				MySingleton.getInstance().setPrevious_V2Unit(0);
			}
			else if(SelectV2.equals("mL"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V2Unit(1);
			}
			else if(SelectV2.equals("g"))
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setPrevious_V2Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToMilliliters: V2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Volume (mL)");
		}
		
		return Volume;
	}
	
	public static double toGrams(double V, int Type)
	{
		double Volume = -1;
		
		if(Type == 0)
		{
			MySingleton.getInstance().setCurrent_VUnit(2);
			SelectV = MySingleton.getInstance().getSelectV();
			if(SelectV.equals("L"))
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setPrevious_VUnit(0);
			}
			else if(SelectV.equals("mL"))
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setPrevious_VUnit(1);
			}
			else if(SelectV.equals("g"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_VUnit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToGrams: V convert error");
			}
				
		}
		else if(Type == 1)
		{
			MySingleton.getInstance().setCurrent_V1Unit(2);
			SelectV1 = MySingleton.getInstance().getSelectV1();
			if(SelectV1.equals("L"))
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setPrevious_V1Unit(0);
			}
			else if(SelectV1.equals("mL"))
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setPrevious_V1Unit(1);
			}
			else if(SelectV1.equals("g"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V1Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToGrams: V1 convert error");
			}
		}
		else if(Type == 2)
		{
			MySingleton.getInstance().setCurrent_V2Unit(2);
			SelectV2 = MySingleton.getInstance().getSelectV2();
			if(SelectV2.equals("L"))
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setPrevious_V2Unit(0);
			}
			else if(SelectV2.equals("mL"))
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setPrevious_V2Unit(1);
			}
			else if(SelectV2.equals("g"))
			{
				Volume = V;
				MySingleton.getInstance().setPrevious_V2Unit(2);
			}
			else
			{
				MySingleton.getInstance().addError("ToGrams: V2 convert error");
			}
		}
		else
		{
			// How the f**k did this happen?!
			MySingleton.getInstance().addError("Conversions error: Volume (g)");
		}
		
		return Volume;
	}
	
	public static double revertTemp(double T, int Type)
	{
		double Temp = -1;
		@SuppressWarnings("unused")
		double nothing = 0;
		if(Type == 0)
		{
			Previous_TUnit = MySingleton.getInstance().getPrevious_TUnit();
			Current_TUnit = MySingleton.getInstance().getCurrent_TUnit();
			if(Current_TUnit == 0 && Previous_TUnit == 2)
			{
				Temp = Convert2FfromK(T);
				MySingleton.getInstance().setCurrent_TUnit(2);
				MySingleton.getInstance().setPrevious_TUnit(0);
			}
			else if(Current_TUnit == 0 && Previous_TUnit == 1)
			{
				Temp = Convert2CfromK(T);
				MySingleton.getInstance().setCurrent_TUnit(1);
				MySingleton.getInstance().setPrevious_TUnit(0);
			}
			else if(Current_TUnit == 1 && Previous_TUnit == 0)
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setCurrent_TUnit(0);
				MySingleton.getInstance().setPrevious_TUnit(1);
			}
			else if(Current_TUnit == 1 && Previous_TUnit == 2)
			{
				Temp = Convert2FfromC(T);
				MySingleton.getInstance().setCurrent_TUnit(2);
				MySingleton.getInstance().setPrevious_TUnit(1);
			}
			else if(Current_TUnit == 2 && Previous_TUnit == 0)
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setCurrent_TUnit(0);
				MySingleton.getInstance().setPrevious_TUnit(2);
			}
			else if(Current_TUnit == 2 && Previous_TUnit == 1)
			{
				Temp = Convert2CfromF(T);
				MySingleton.getInstance().setCurrent_TUnit(1);
				MySingleton.getInstance().setPrevious_TUnit(2);
			}
			else if(Current_TUnit == 0 && Previous_TUnit == 0)
			{
				Temp = T;
			}
			else if(Current_TUnit == 1 && Previous_TUnit == 1)
			{
				Temp = T;
			}
			else if(Current_TUnit == 2 && Previous_TUnit == 2)
			{
				Temp = T;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Temp - T");
		}
		else if(Type == 1)
		{
			Previous_T1Unit = MySingleton.getInstance().getPrevious_T1Unit();
			Current_T1Unit = MySingleton.getInstance().getCurrent_T1Unit();
			if(Current_T1Unit == 0 && Previous_T1Unit == 2)
			{
				Temp = Convert2FfromK(T);
				MySingleton.getInstance().setCurrent_T1Unit(2);
				MySingleton.getInstance().setPrevious_T1Unit(0);
			}
			else if(Current_T1Unit == 0 && Previous_T1Unit == 1)
			{
				Temp = Convert2CfromK(T);
				MySingleton.getInstance().setCurrent_T1Unit(1);
				MySingleton.getInstance().setPrevious_T1Unit(0);
			}
			else if(Current_T1Unit == 1 && Previous_T1Unit == 0)
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setCurrent_T1Unit(0);
				MySingleton.getInstance().setPrevious_T1Unit(1);
			}
			else if(Current_T1Unit == 1 && Previous_T1Unit == 2)
			{
				Temp = Convert2FfromC(T);
				MySingleton.getInstance().setCurrent_T1Unit(2);
				MySingleton.getInstance().setPrevious_T1Unit(1);
			}
			else if(Current_T1Unit == 2 && Previous_T1Unit == 0)
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setCurrent_T1Unit(0);
				MySingleton.getInstance().setPrevious_T1Unit(2);
			}
			else if(Current_T1Unit == 2 && Previous_T1Unit == 1)
			{
				Temp = Convert2CfromF(T);
				MySingleton.getInstance().setCurrent_T1Unit(1);
				MySingleton.getInstance().setPrevious_T1Unit(2);
			}
			else if(Current_T1Unit == 0 && Previous_T1Unit == 0)
			{
				Temp = T;
			}
			else if(Current_T1Unit == 1 && Previous_T1Unit == 1)
			{
				Temp = T;
			}
			else if(Current_T1Unit == 2 && Previous_T1Unit == 2)
			{
				Temp = T;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Temp - T1");
		}
		else if(Type == 2)
		{
			Previous_T2Unit = MySingleton.getInstance().getPrevious_T2Unit();
			Current_T2Unit = MySingleton.getInstance().getCurrent_T2Unit();
			if(Current_T2Unit == 0 && Previous_T2Unit == 2)
			{
				Temp = Convert2FfromK(T);
				MySingleton.getInstance().setCurrent_T2Unit(2);
				MySingleton.getInstance().setPrevious_T2Unit(0);
			}
			else if(Current_T2Unit == 0 && Previous_T2Unit == 1)
			{
				Temp = Convert2CfromK(T);
				MySingleton.getInstance().setCurrent_T2Unit(1);
				MySingleton.getInstance().setPrevious_T2Unit(0);
			}
			else if(Current_T2Unit == 1 && Previous_T2Unit == 0)
			{
				Temp = Convert2KfromC(T);
				MySingleton.getInstance().setCurrent_T2Unit(0);
				MySingleton.getInstance().setPrevious_T2Unit(1);
			}
			else if(Current_T2Unit == 1 && Previous_T2Unit == 2)
			{
				Temp = Convert2FfromC(T);
				MySingleton.getInstance().setCurrent_T2Unit(2);
				MySingleton.getInstance().setPrevious_T2Unit(1);
			}
			else if(Current_T2Unit == 2 && Previous_T2Unit == 0)
			{
				Temp = Convert2KfromF(T);
				MySingleton.getInstance().setCurrent_T2Unit(0);
				MySingleton.getInstance().setPrevious_T2Unit(2);
			}
			else if(Current_T2Unit == 2 && Previous_T2Unit == 1)
			{
				Temp = Convert2CfromF(T);
				MySingleton.getInstance().setCurrent_T2Unit(1);
				MySingleton.getInstance().setPrevious_T2Unit(2);
			}
			else if(Current_T2Unit == 0 && Previous_T2Unit == 0)
			{
				Temp = T;
			}
			else if(Current_T2Unit == 1 && Previous_T2Unit == 1)
			{
				Temp = T;
			}
			else if(Current_T2Unit == 2 && Previous_T2Unit == 2)
			{
				Temp = T;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Temp - T2");
		}
		else
		{
			MySingleton.getInstance().addError("Conversions Error: Revert Temp");
		}
		
		return Temp;
	}
	
	public static double revertPressure(double P, int Type)
	{
		double Pressure = -1;
		@SuppressWarnings("unused")
		double nothing = 0;
		if(Type == 0)
		{
			Previous_PUnit = MySingleton.getInstance().getPrevious_PUnit();
			Current_PUnit = MySingleton.getInstance().getCurrent_PUnit();
			if(Current_PUnit == 0 && Previous_PUnit == 3)
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setCurrent_PUnit(3);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(Current_PUnit == 0 && Previous_PUnit == 2)
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setCurrent_PUnit(2);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(Current_PUnit == 0 && Previous_PUnit == 1)
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setCurrent_PUnit(1);
				MySingleton.getInstance().setPrevious_PUnit(0);
			}
			else if(Current_PUnit == 1 && Previous_PUnit == 0)
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setCurrent_PUnit(0);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(Current_PUnit == 1 && Previous_PUnit == 2)
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setCurrent_PUnit(2);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(Current_PUnit == 1 && Previous_PUnit == 3)
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setCurrent_PUnit(3);
				MySingleton.getInstance().setPrevious_PUnit(1);
			}
			else if(Current_PUnit == 2 && Previous_PUnit == 0)
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setCurrent_PUnit(0);
				MySingleton.getInstance().setPrevious_PUnit(2);
			}
			else if(Current_PUnit == 2 && Previous_PUnit == 1)
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setCurrent_PUnit(1);
				MySingleton.getInstance().setPrevious_PUnit(2);
			}
			else if(Current_PUnit == 2 && Previous_PUnit == 3)
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setCurrent_PUnit(1);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else if(Current_PUnit == 3 && Previous_PUnit == 0)
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setCurrent_PUnit(0);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else if(Current_PUnit == 3 && Previous_PUnit == 1)
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setCurrent_PUnit(1);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else if(Current_PUnit == 3 && Previous_PUnit == 2)
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setCurrent_PUnit(2);
				MySingleton.getInstance().setPrevious_PUnit(3);
			}
			else if(Current_PUnit == 0 && Previous_PUnit == 0)
			{
				Pressure = P;
			}
			else if(Current_PUnit == 1 && Previous_PUnit == 1)
			{
				Pressure = P;
			}
			else if(Current_PUnit == 2 && Previous_PUnit == 2)
			{
				Pressure = P;
			}
			else if(Current_PUnit == 3 && Previous_PUnit == 3)
			{
				Pressure = P;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Pressure - P");
		}
		else if(Type == 1)
		{
			Previous_P1Unit = MySingleton.getInstance().getPrevious_P1Unit();
			Current_P1Unit = MySingleton.getInstance().getCurrent_P1Unit();
			if(Current_P1Unit == 0 && Previous_P1Unit == 3)
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setCurrent_P1Unit(3);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(Current_P1Unit == 0 && Previous_P1Unit == 2)
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setCurrent_P1Unit(2);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(Current_P1Unit == 0 && Previous_P1Unit == 1)
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setCurrent_P1Unit(1);
				MySingleton.getInstance().setPrevious_P1Unit(0);
			}
			else if(Current_P1Unit == 1 && Previous_P1Unit == 0)
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setCurrent_P1Unit(0);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(Current_P1Unit == 1 && Previous_P1Unit == 2)
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setCurrent_P1Unit(2);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(Current_P1Unit == 1 && Previous_P1Unit == 3)
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setCurrent_P1Unit(3);
				MySingleton.getInstance().setPrevious_P1Unit(1);
			}
			else if(Current_P1Unit == 2 && Previous_P1Unit == 0)
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setCurrent_P1Unit(0);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(Current_P1Unit == 2 && Previous_P1Unit == 1)
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setCurrent_P1Unit(1);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(Current_P1Unit == 2 && Previous_P1Unit == 3)
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setCurrent_P1Unit(1);
				MySingleton.getInstance().setPrevious_P1Unit(2);
			}
			else if(Current_P1Unit == 3 && Previous_P1Unit == 0)
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setCurrent_P1Unit(0);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else if(Current_P1Unit == 3 && Previous_P1Unit == 1)
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setCurrent_P1Unit(1);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else if(Current_P1Unit == 3 && Previous_P1Unit == 2)
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setCurrent_P1Unit(2);
				MySingleton.getInstance().setPrevious_P1Unit(3);
			}
			else if(Current_P1Unit == 0 && Previous_P1Unit == 0)
			{
				Pressure = P;
			}
			else if(Current_P1Unit == 1 && Previous_P1Unit == 1)
			{
				Pressure = P;
			}
			else if(Current_P1Unit == 2 && Previous_P1Unit == 2)
			{
				Pressure = P;
			}
			else if(Current_P1Unit == 3 && Previous_P1Unit == 3)
			{
				Pressure = P;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Pressure - P1");
		}
		else if(Type == 2)
		{
			Previous_P2Unit = MySingleton.getInstance().getPrevious_P2Unit();
			Current_P2Unit = MySingleton.getInstance().getCurrent_P2Unit();
			if(Current_P2Unit == 0 && Previous_P2Unit == 3)
			{
				Pressure = Convert2KPAfromATM(P);
				MySingleton.getInstance().setCurrent_P2Unit(3);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(Current_P2Unit == 0 && Previous_P2Unit == 2)
			{
				Pressure = Convert2TORRfromATM(P);
				MySingleton.getInstance().setCurrent_P2Unit(2);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(Current_P2Unit == 0 && Previous_P2Unit == 1)
			{
				Pressure = Convert2MMHGfromATM(P);
				MySingleton.getInstance().setCurrent_P2Unit(1);
				MySingleton.getInstance().setPrevious_P2Unit(0);
			}
			else if(Current_P2Unit == 1 && Previous_P2Unit == 0)
			{
				Pressure = Convert2ATMfromMMHG(P);
				MySingleton.getInstance().setCurrent_P2Unit(0);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(Current_P2Unit == 1 && Previous_P2Unit == 2)
			{
				Pressure = Convert2TORRfromMMHG(P);
				MySingleton.getInstance().setCurrent_P2Unit(2);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(Current_P2Unit == 1 && Previous_P2Unit == 3)
			{
				Pressure = Convert2KPAfromMMHG(P);
				MySingleton.getInstance().setCurrent_P2Unit(3);
				MySingleton.getInstance().setPrevious_P2Unit(1);
			}
			else if(Current_P2Unit == 2 && Previous_P2Unit == 0)
			{
				Pressure = Convert2ATMfromTORR(P);
				MySingleton.getInstance().setCurrent_P2Unit(0);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(Current_P2Unit == 2 && Previous_P2Unit == 1)
			{
				Pressure = Convert2MMHGfromTORR(P);
				MySingleton.getInstance().setCurrent_P2Unit(1);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(Current_P2Unit == 2 && Previous_P2Unit == 3)
			{
				Pressure = Convert2KPAfromTORR(P);
				MySingleton.getInstance().setCurrent_P2Unit(3);
				MySingleton.getInstance().setPrevious_P2Unit(2);
			}
			else if(Current_P2Unit == 3 && Previous_P2Unit == 0)
			{
				Pressure = Convert2ATMfromKPA(P);
				MySingleton.getInstance().setCurrent_P2Unit(0);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else if(Current_P2Unit == 3 && Previous_P2Unit == 1)
			{
				Pressure = Convert2MMHGfromKPA(P);
				MySingleton.getInstance().setCurrent_P2Unit(1);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else if(Current_P2Unit == 3 && Previous_P2Unit == 2)
			{
				Pressure = Convert2TORRfromKPA(P);
				MySingleton.getInstance().setCurrent_P2Unit(2);
				MySingleton.getInstance().setPrevious_P2Unit(3);
			}
			else if(Current_P2Unit == 0 && Previous_P2Unit == 0)
			{
				Pressure = P;
			}
			else if(Current_P2Unit == 1 && Previous_P2Unit == 1)
			{
				Pressure = P;
			}
			else if(Current_P2Unit == 2 && Previous_P2Unit == 2)
			{
				Pressure = P;
			}
			else if(Current_P2Unit == 3 && Previous_P2Unit == 3)
			{
				Pressure = P;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Pressure - P2");
		}
		else
		{
			MySingleton.getInstance().addError("Conversions Error: Revert Pressure");
		}
		
		return Pressure;
	}
	
	public static double revertVolume(double V, int Type)
	{
		double Volume = -1;
		@SuppressWarnings("unused")
		double nothing = 0;
		if(Type == 0)
		{
			Previous_VUnit = MySingleton.getInstance().getPrevious_VUnit();
			Current_VUnit = MySingleton.getInstance().getCurrent_VUnit();
			if(Current_VUnit == 0 && Previous_VUnit == 2)
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setCurrent_VUnit(2);
				MySingleton.getInstance().setPrevious_VUnit(0);
			}
			else if(Current_VUnit == 0 && Previous_VUnit == 1)
			{
				Volume = Convert2MLfromL(V);
				MySingleton.getInstance().setCurrent_VUnit(1);
				MySingleton.getInstance().setPrevious_VUnit(0);
			}
			else if(Current_VUnit == 1 && Previous_VUnit == 0)
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setCurrent_VUnit(0);
				MySingleton.getInstance().setPrevious_VUnit(1);
			}
			else if(Current_VUnit == 1 && Previous_VUnit == 2)
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setCurrent_VUnit(2);
				MySingleton.getInstance().setPrevious_VUnit(1);
			}
			else if(Current_VUnit == 2 && Previous_VUnit == 0)
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setCurrent_VUnit(0);
				MySingleton.getInstance().setPrevious_VUnit(2);
			}
			else if(Current_VUnit == 2 && Previous_VUnit == 1)
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setCurrent_VUnit(1);
				MySingleton.getInstance().setPrevious_VUnit(2);
			}
			else if(Current_VUnit == 0 && Previous_VUnit == 0)
			{
				Volume = V;
			}
			else if(Current_VUnit == 1 && Previous_VUnit == 1)
			{
				Volume = V;
			}
			else if(Current_VUnit == 2 && Previous_VUnit == 2)
			{
				Volume = V;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Volume - V");
		}
		else if(Type == 1)
		{
			Previous_V1Unit = MySingleton.getInstance().getPrevious_V1Unit();
			Current_V1Unit = MySingleton.getInstance().getCurrent_V1Unit();
			if(Current_V1Unit == 0 && Previous_V1Unit == 2)
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setCurrent_V1Unit(2);
				MySingleton.getInstance().setPrevious_V1Unit(0);
			}
			else if(Current_V1Unit == 0 && Previous_V1Unit == 1)
			{
				Volume= Convert2MLfromL(V);
				MySingleton.getInstance().setCurrent_V1Unit(1);
				MySingleton.getInstance().setPrevious_V1Unit(0);
			}
			else if(Current_V1Unit == 1 && Previous_V1Unit == 0)
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setCurrent_V1Unit(0);
				MySingleton.getInstance().setPrevious_V1Unit(1);
			}
			else if(Current_V1Unit == 1 && Previous_V1Unit == 2)
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setCurrent_V1Unit(2);
				MySingleton.getInstance().setPrevious_V1Unit(1);
			}
			else if(Current_V1Unit == 2 && Previous_V1Unit == 0)
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setCurrent_V1Unit(0);
				MySingleton.getInstance().setPrevious_V1Unit(2);
			}
			else if(Current_V1Unit == 2 && Previous_V1Unit == 1)
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setCurrent_V1Unit(1);
				MySingleton.getInstance().setPrevious_V1Unit(2);
			}
			else if(Current_V1Unit == 0 && Previous_V1Unit == 0)
			{
				Volume = V;
			}
			else if(Current_V1Unit == 1 && Previous_V1Unit == 1)
			{
				Volume = V;
			}
			else if(Current_V1Unit == 2 && Previous_V1Unit == 2)
			{
				Volume = V;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Volume - V1");
		}
		else if(Type == 2)
		{
			Previous_V2Unit = MySingleton.getInstance().getPrevious_V2Unit();
			Current_V2Unit = MySingleton.getInstance().getCurrent_V2Unit();
			if(Current_V2Unit == 0 && Previous_V2Unit == 2)
			{
				Volume = Convert2GfromL(V);
				MySingleton.getInstance().setCurrent_V2Unit(2);
				MySingleton.getInstance().setPrevious_V2Unit(0);
			}
			else if(Current_V2Unit == 0 && Previous_V2Unit == 1)
			{
				Volume = Convert2MLfromL(V);
				MySingleton.getInstance().setCurrent_V2Unit(1);
				MySingleton.getInstance().setPrevious_V2Unit(0);
			}
			else if(Current_V2Unit == 1 && Previous_V2Unit == 0)
			{
				Volume = Convert2LfromML(V);
				MySingleton.getInstance().setCurrent_V2Unit(0);
				MySingleton.getInstance().setPrevious_V2Unit(1);
			}
			else if(Current_V2Unit == 1 && Previous_V2Unit == 2)
			{
				Volume = Convert2GfromML(V);
				MySingleton.getInstance().setCurrent_V2Unit(2);
				MySingleton.getInstance().setPrevious_V2Unit(1);
			}
			else if(Current_V2Unit == 2 && Previous_V2Unit == 0)
			{
				Volume = Convert2LfromG(V);
				MySingleton.getInstance().setCurrent_V2Unit(0);
				MySingleton.getInstance().setPrevious_V2Unit(2);
			}
			else if(Current_V2Unit == 2 && Previous_V2Unit == 1)
			{
				Volume = Convert2MLfromG(V);
				MySingleton.getInstance().setCurrent_V2Unit(1);
				MySingleton.getInstance().setPrevious_V2Unit(2);
			}
			else if(Current_V2Unit == 0 && Previous_V2Unit == 0)
			{
				Volume = V;
			}
			else if(Current_V2Unit == 1 && Previous_V2Unit == 1)
			{
				Volume = V;
			}
			else if(Current_V2Unit == 2 && Previous_V2Unit == 2)
			{
				Volume = V;
			}
			else
				MySingleton.getInstance().addError("Conversions Error: Revert Volume - V2");
		}
		else
		{
			MySingleton.getInstance().addError("Conversions Error: Revert Volume");
		}
		
		return Volume;
	}
	
	public static void restoreValues(SharedPreferences settings, String SettingsTAG)
	{
		// Are We In Debug Mode?
		boolean DebugMode = settings.getBoolean("DebugMode", false);
		MySingleton.getInstance().setDebugMode(DebugMode);
		
		// Get Saved Volume
		int SelectedVolume = settings.getInt("selectVolume", 0);

		// Set Selected Volume Values
		if (SelectedVolume == 0)
		{
			MySingleton.getInstance().setDefaultVolumeUnit(0);
			MySingleton.getInstance().setDefaultVol("liters");
			MySingleton.getInstance().setDefaultVolS("L");
			MySingleton.getInstance().setSelectedVolume("Liters");
		}
		else if (SelectedVolume == 1)
		{
			MySingleton.getInstance().setDefaultVolumeUnit(1);
			MySingleton.getInstance().setDefaultVol("milliliters");
			MySingleton.getInstance().setDefaultVolS("mL");
			MySingleton.getInstance().setSelectedVolume("Milliliters");
		}
		else if (SelectedVolume == 2)
		{
			MySingleton.getInstance().setDefaultVolumeUnit(2);
			MySingleton.getInstance().setDefaultVol("grams");
			MySingleton.getInstance().setDefaultVolS("g");
			MySingleton.getInstance().setSelectedVolume("Grams");
		}

		// Get Saved Pressure
		int SelectedPressure = settings.getInt("selectPressure", 0);

		// Set Selected Pressure Values
		if (SelectedPressure == 0)
		{
			MySingleton.getInstance().setDefaultPressureUnit(0);
			MySingleton.getInstance().setDefaultPres("atm");
			MySingleton.getInstance().setDefaultPresS("atm");
			MySingleton.getInstance().setSelectedPressure("Atm");
		}
		else if (SelectedPressure == 1)
		{
			MySingleton.getInstance().setDefaultPressureUnit(1);
			MySingleton.getInstance().setDefaultPres("mmHg");
			MySingleton.getInstance().setDefaultPresS("mmHg");
			MySingleton.getInstance().setSelectedPressure("mmHg");
		}
		else if (SelectedPressure == 2)
		{
			MySingleton.getInstance().setDefaultPressureUnit(2);
			MySingleton.getInstance().setDefaultPres("torr");
			MySingleton.getInstance().setDefaultPresS("torr");
			MySingleton.getInstance().setSelectedPressure("Torr");
		}
		else if (SelectedPressure == 3)
		{
			MySingleton.getInstance().setDefaultPressureUnit(3);
			MySingleton.getInstance().setDefaultPres("kPa");
			MySingleton.getInstance().setDefaultPresS("kPa");
			MySingleton.getInstance().setSelectedPressure("kPa");
		}

		// Get Saved Temperature
		int SelectedTemperature = settings.getInt("selectTemperature", 0);

		// Set Selected Temperature Values
		if (SelectedTemperature == 0)
		{
			MySingleton.getInstance().setDefaultTempUnit(0);
			MySingleton.getInstance().setDefaultTemp("kelvins");
			MySingleton.getInstance().setDefaultTempS("K");
			MySingleton.getInstance().setSelectedTemperature("Kel");
		}
		else if (SelectedTemperature == 1)
		{
			MySingleton.getInstance().setDefaultTempUnit(1);
			MySingleton.getInstance().setDefaultTemp("celcius");
			MySingleton.getInstance().setDefaultTempS("C");
			MySingleton.getInstance().setSelectedTemperature("Cel");
		}
		else if (SelectedTemperature == 2)
		{
			MySingleton.getInstance().setDefaultTempUnit(2);
			MySingleton.getInstance().setDefaultTemp("fahrenheit");
			MySingleton.getInstance().setDefaultTempS("F");
			MySingleton.getInstance().setSelectedTemperature("Fah");
		}

		// Get Saved Decimal Precisions
		String GasMaxDecimalAccuracy = settings.getString("selectedGasMaxDecimalAccuracy", "5");

		// Set decimal precision
		MySingleton.getInstance().setMaxDecimalAccuracy(GasMaxDecimalAccuracy);

		// Get Saved Gas Unit Configurations
		boolean Gas_SameUnits = settings.getBoolean("GasSameUnits", true);
		boolean Gas_AutoSolve = settings.getBoolean("GasAutoSolve", false);
		boolean Gas_FormatHelp = settings.getBoolean("GasFormatHelp", false);

		// Set Gas Unit Configurations
		MySingleton.getInstance().setGasSameUnits(Gas_SameUnits);
		MySingleton.getInstance().setGasAutoSolve(Gas_AutoSolve);
		MySingleton.getInstance().setGasFormatHelp(Gas_FormatHelp);

		// Get Saved Convert Configurations
		String Convert_DefaultCategory = settings.getString("ConvertDefaultCategory", "Blank");
		boolean Convert_AutoSolve = settings.getBoolean("ConvertAutoSolve", true);
		boolean Convert_FormatHelp = settings.getBoolean("ConvertFormatHelp", true);
		String Convert_MaxDecimalAccuracy = settings.getString("selectedConvertMaxDecimalAccuracy", "5");

		// Set Convert Configurations
		MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
		MySingleton.getInstance().setConvertDefaultCategory(Convert_DefaultCategory);
		MySingleton.getInstance().setConvertAutoSolve(Convert_AutoSolve);
		MySingleton.getInstance().setConvertFormatHelp(Convert_FormatHelp);
		
		// Get Saved Periodic Configurations
		boolean Periodic_ShowProgress = settings.getBoolean("PeriodicShowProgress", true);
		boolean Periodic_ClickableElements = settings.getBoolean("PeriodicClickableElements", true);
		String Periodic_Sort = settings.getString("PeriodicSort", "Symbol");
		boolean Periodic_ShowSelector = settings.getBoolean("PeriodicShowSelector", true);
		boolean Periodic_ShowSearch = settings.getBoolean("PeriodicShowSearch", true);
		
		// Set Periodic Configurations
		MySingleton.getInstance().setPeriodicShowProgress(Periodic_ShowProgress);
		MySingleton.getInstance().setPeriodicClickableElements(Periodic_ClickableElements);
		MySingleton.getInstance().setPeriodicSort(Periodic_Sort);
		MySingleton.getInstance().setPeriodicShowSelector(Periodic_ShowSelector);
		MySingleton.getInstance().setPeriodicShowSearch(Periodic_ShowSearch);
		
		// Get Saved Quiz Configurations
		boolean Quiz_NotifyCorrect = settings.getBoolean("QuizNotifyCorrect", false);
		boolean Quiz_ShowTime = settings.getBoolean("QuizShowTime", true);
		boolean Quiz_ShowPercent = settings.getBoolean("QuizShowPercent", true);
		boolean Quiz_ShowProgress = settings.getBoolean("QuizShowProgress", true);
		
		// Set Quiz Configurations
		MySingleton.getInstance().setQuizNotifyCorrect(Quiz_NotifyCorrect);
		MySingleton.getInstance().setQuizShowTime(Quiz_ShowTime);
		MySingleton.getInstance().setQuizShowPercent(Quiz_ShowPercent);
		MySingleton.getInstance().setQuizShowProgress(Quiz_ShowProgress);
	}
	
	public static Chemical[] getChemicals()
	{
		Chem = new Chemical[120];
		
		//AtomicNumber, AtomicMass, Name, Formula, Protons, Neutrons, isElement, (O)Classification, (O)Melting Point, (O)Boiling Point
		Chem[0] = new Chemical(89, 227.0, "Actinium", "Ac", 89, 138, true, "Rare Earth", 1050.0, 3200.0);
		Chem[1] = new Chemical(47, 107.8682, "Silver", "Ag", 47, 61, true, "Transition Metal", 961.93, 2212.0);
		Chem[2] = new Chemical(13, 26.9815386, "Aluminum", "Al", 13, 14, true, "Other Metals", 660.37, 2467.0);
		Chem[3] = new Chemical(95, 243.0, "Americium", "Am", 95, 148, true, "Rare Earth", 994.0, 2607.0);
		Chem[4] = new Chemical(18, 39.948, "Argon", "Ar", 18, 22, true, "Noble Gas", -189.3, -186.0);
		Chem[5] = new Chemical(33, 74.9216, "Arsenic", "As", 33, 42, true, "Metalloid", 817.0, 613.0);
		Chem[6] = new Chemical(85, 210.0, "Astatine", "At", 85, 125, true, "Halogen", 302.0, 337.0);
		Chem[7] = new Chemical(79, 196.966569, "Gold", "Au", 79, 118, true, "Transition Metal", 1064.43, 2807.0);
		Chem[8] = new Chemical(5, 10.811, "Boron", "B", 5, 6, true, "Metalloid", 2300.0, 2550.0);
		Chem[9] = new Chemical(56, 137.327, "Barium", "Ba", 56, 81, true, "Alkaline Earth", 725.0, 1140.0);
		Chem[10] = new Chemical(4, 9.012182, "Beryllium", "Be", 4, 5, true, "Alkaline Earth", 1278.0, 2970.0);
		Chem[11] = new Chemical(107, 262.0, "Bohrium", "Bh", 107, 155, true, "Transition Metal");
		Chem[12] = new Chemical(83, 208.9804, "Bismuth", "Bi", 83, 126, true, "Other Metals", 271.3, 1560.0);
		Chem[13] = new Chemical(97, 247.0, "Berkelium", "Bk", 97, 150, true, "Rare Earth");
		Chem[14] = new Chemical(35, 79.904, "Bromine", "Br", 35, 45, true, "Halogen", -7.2, 58.78);
		Chem[15] = new Chemical(6, 12.0107, "Carbon", "C", 6, 6, true, "Non-metal", 3500.0, 4827.0);
		Chem[16] = new Chemical(20, 40.078, "Calcium", "Ca", 20, 20, true, "Alkaline Earth", 839.0, 1484.0);
		Chem[17] = new Chemical(48, 112.411, "Cadmium", "Cd", 48, 64, true, "Transition Metal", 320.9, 765.0);
		Chem[18] = new Chemical(58, 140.116, "Cerium", "Ce", 58, 82, true, "Rare Earth", 795.0, 3257.0);
		Chem[19] = new Chemical(98, 251.0, "Californium", "Cf", 98, 153, true, "Rare Earth");
		Chem[20] = new Chemical(17, 35.453, "Chlorine", "Cl", 17, 18, true, "Halogen", -100.98, -34.6);
		Chem[21] = new Chemical(96, 247.0, "Curium", "Cm", 96, 151, true, "Rare Earth", 1340.0);
		Chem[22] = new Chemical(27, 58.933195, "Cobalt", "Co", 27, 32, true, "Transition Metal", 1495.0, 2870.0);
		Chem[23] = new Chemical(112, 285.0, "Copernicium", "Cp", 112, 277, true, "Transition Metal");
		Chem[24] = new Chemical(24, 51.9961, "Chromium", "Cr", 24, 28, true, "Transition Metal", 1857.0, 2672.0);
		Chem[25] = new Chemical(55, 132.9054519, "Cesium", "Cs", 55, 78, true, "Alkali Metal", 28.5, 678.4);
		Chem[26] = new Chemical(29, 63.546, "Copper", "Cu", 29, 35, true, "Transition Metal", 1083.0, 2567.0);
		Chem[27] = new Chemical(105, 262.0, "Dubnium", "Db", 105, 157, true, "Transition Metal");
		Chem[28] = new Chemical(110, 281.0, "Darmstadtium", "Ds", 110, 159, true, "Transition Metal");
		Chem[29] = new Chemical(66, 162.5, "Dysprosium", "Dy", 66, 97, true, "Rare Earth");
		Chem[30] = new Chemical(68, 167.259, "Erbium", "Er", 68, 99, true, "Rare Earth", 1522.0, 2510.0);
		Chem[31] = new Chemical(99, 252.0, "Einsteinium", "Es", 99, 153, true, "Rare Earth");
		Chem[32] = new Chemical(63, 151.964, "Europium", "Eu", 63, 89, true, "Rare Earth", 822.0, 1597.0);
		Chem[33] = new Chemical(9, 18.9984032, "Fluorine", "F", 9, 10, true, "Halogen", -219.62, -188.14);
		Chem[34] = new Chemical(26, 55.845, "Iron", "Fe", 26, 30, true, "Transition Metal", 1535.0, 2750.0);
		Chem[35] = new Chemical(100, 257.0, "Fermium", "Fm", 100, 157, true, "Rare Earth");
		Chem[36] = new Chemical(87, 223.0, "Francium", "Fr", 87, 136, true, "Alkali Metal", 27.0, 677.0);
		Chem[37] = new Chemical(31, 69.723, "Gallium", "Ga", 31, 39, true, "Other Metals", 29.78, 2403.0);
		Chem[38] = new Chemical(64, 157.25, "Gadolinium", "Gd", 64, 93, true, "RareEarth", 1311.0, 3233.0);
		Chem[39] = new Chemical(32, 72.63, "Germanium", "Ge", 32, 41, true, "Metalloid", 937.4, 2830.0);
		Chem[40] = new Chemical(1, 1.00794, "Hydrogen", "H", 1, 0, true, "Non-metal", -259.14, -252.87);
		Chem[41] = new Chemical(2, 4.002602, "Helium", "He", 2, 2, true, "Noble Gas", -272.0, -268.6);
		Chem[42] = new Chemical(72, 178.49, "Hafnium", "Hf", 72, 106, true, "Transition Metal", 2150.0, 5400.0);
		Chem[43] = new Chemical(80, 200.59, "Mercury", "Hg", 80, 121, true, "Transition Metal", -38.87, 356.58);
		Chem[44] = new Chemical(67, 164.93032, "Holmium", "Ho", 67, 98, true, "Rare Earth", 1470.0, 2720.0);
		Chem[45] = new Chemical(108, 270.0, "Hassium", "Hs", 108, 157, true, "Transition Metal");
		Chem[46] = new Chemical(53, 126.90447, "Iodine", "I", 53, 74, true, "Halogen", 113.5, 184.0);
		Chem[47] = new Chemical(49, 114.818, "Indium", "In", 49, 66, true, "Other Metals", 156.61, 2000.0);
		Chem[48] = new Chemical(77, 192.217, "Iridium", "Ir", 77, 115, true, "Transition Metal", 2410.0, 4527.0);
		Chem[49] = new Chemical(19, 39.0983, "Potassium", "K", 19, 20, true, "Alkali Metal", 63.65, 774.0);
		Chem[50] = new Chemical(36, 83.798, "Krypton", "Kr", 36, 48, true, "Noble Gas", -157.2, -153.4);
		Chem[51] = new Chemical(57, 138.90547, "Lanthanum", "La", 57, 82, true, "Rare Earth", 920.0, 3469.0);
		Chem[52] = new Chemical(3, 6.941, "Lithium", "Li", 3, 4, true, "Alkali Metal", 180.54, 1347.0);
		Chem[53] = new Chemical(103, 262.0, "Lawrencium", "Lr", 103, 159, true, "Rare Earth");
		Chem[54] = new Chemical(71, 174.9668, "Lutetium", "Lu", 71, 104, true, "Rare Earth", 1656.0, 3315.0);
		Chem[55] = new Chemical(101, 258.0, "Mendelevium", "Md", 101, 157, true, "Rare Earth");
		Chem[56] = new Chemical(12, 24.305, "Magnesium", "Mg", 12, 12, true, "Alkaline Earth", 650.0, 1107.0);
		Chem[57] = new Chemical(25, 54.938045, "Manganese", "Mn", 25, 30, true, "Transition Metal", 1245.0, 1962.0);
		Chem[58] = new Chemical(42, 95.96, "Molybdenum", "Mo", 42, 54, true, "Transition Metal", 2617.0, 4612.0);
		Chem[59] = new Chemical(109, 266.0, "Meitnerium", "Mt", 109, 157, true, "Transition Metal");
		Chem[60] = new Chemical(7, 14.0067, "Nitrogen", "N", 7, 7, true, "Non-metal", -209.9, -195.8);
		Chem[61] = new Chemical(11, 22.98676928, "Sodium", "Na", 11, 12, true, "Alkali Metal", 97.72, 883);
		Chem[62] = new Chemical(41, 92.90638, "Niobium", "Nb", 41, 52, true, "Transition Metal", 2468.0, 4927.0);
		Chem[63] = new Chemical(60, 144.242, "Neodymium", "Nd", 60, 84, true, "Rare Earth", 1010.0, 3127.0);
		Chem[64] = new Chemical(10, 20.1797, "Neon", "Ne", 10, 10, true, "Noble Gas", -248.6, 246.1);
		Chem[65] = new Chemical(28, 58.6934, "Nickel", "Ni", 28, 31, true, "Transition Metal", 1453.0, 2732.0);
		Chem[66] = new Chemical(102, 259.0, "Nobelium", "No", 102, 157, true, "Rare Earth");
		Chem[67] = new Chemical(93, 237.0, "Neptunium", "Np", 93, 144, true, "Rare Earth", 640.0, 3902.0);
		Chem[68] = new Chemical(8, 15.9994, "Oxygen", "O", 8, 8, true, "Non-metal", -218.4, -183.0);
		Chem[69] = new Chemical(76, 190.23, "Osmium", "Os", 76, 114, true, "Transition Metal", 3045.0, 5027.0);
		Chem[70] = new Chemical(15, 30.973762, "Phosphorus", "P", 15, 16, true, "Non-metal", 44.1, 280.0);
		Chem[71] = new Chemical(91, 231.03588, "Protactinium", "Pa", 91, 140, true, "Rare Earth", 231.04, 1600.0);
		Chem[72] = new Chemical(82, 207.2, "Lead", "Pb", 82, 125, true, "Other Metals", 207.2, 327.5);
		Chem[73] = new Chemical(46, 106.42, "Palladium", "Pd", 46, 60, true, "Transition Metal", 1552.0, 2927.0);
		Chem[74] = new Chemical(61, 145.0, "Promethium", "Pm", 61, 84, true, "Rare Earth");
		Chem[75] = new Chemical(84, 209.0, "Polonium", "Po", 84, 125, true, "Metalloid", 254.0, 962.0);
		Chem[76] = new Chemical(59, 140.91, "Praseodymium", "Pr", 59, 82, true, "Rare Earth", 935.0, 3127.0);
		Chem[77] = new Chemical(78, 195.084, "Platinum", "Pt", 78, 117, true, "Transition Metal", 1772.0, 3827.0);
		Chem[78] = new Chemical(94, 244.0, "Plutonium", "Pu", 94, 150, true, "Rare Earth", 639.5, 3235.0);
		Chem[79] = new Chemical(88, 226.0, "Radium", "Ra", 88, 138, true, "Alkaline Earth", 700.0, 1737.0);
		Chem[80] = new Chemical(37, 85.4678, "Rubidium", "Rb", 37, 48, true, "Alkali Metal", 38.89, 688.0);
		Chem[81] = new Chemical(75, 186.207, "Rhenium", "Re", 75, 111, true, "Transition Metal", 3180.0, 5627.0);
		Chem[82] = new Chemical(104, 267.0, "Rutherfordium", "Rf", 104, 157, true, "Transition Metal");
		Chem[83] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
		Chem[84] = new Chemical(45, 102.9055, "Rhodium", "Rh", 45, 58, true, "Transition Metal", 1966.0, 3727.0);
		Chem[85] = new Chemical(86, 222.0, "Radon", "Rn", 86, 136, true, "Noble Gas", -71.0, -61.8);
		Chem[86] = new Chemical(44, 101.07, "Ruthenium", "Ru", 44, 57, true, "Transition Metal", 2250.0, 3900.0);
		Chem[87] = new Chemical(16, 32.065, "Sulfur", "S", 16, 16, true, "Non-metal", 112.8, 444.6);
		Chem[88] = new Chemical(51, 121.76, "Antimony", "Sb", 51, 71, true, "Metalloid", 630.0, 1750.0);
		Chem[89] = new Chemical(21, 44.955912, "Scandium", "Sc", 21, 24, true, "Transition Metal", 1539.0, 2832.0);
		Chem[90] = new Chemical(34, 78.96, "Selenium", "Se", 34, 45, true, "Non-metal", 217.0, 684.9);
		Chem[91] = new Chemical(106, 271.0, "Seaborgium", "Sg", 106, 157, true, "Transition Metal");
		Chem[92] = new Chemical(14, 28.0855, "Silicon", "Si", 14, 14, true, "Metalloid", 1410.0, 2355.0);
		Chem[93] = new Chemical(62, 150.36, "Samarium", "Sm", 62, 88, true, "Rare Earth", 1072.0, 1900.0);
		Chem[94] = new Chemical(50, 118.71, "Tin", "Sn", 50, 69, true, "Other Metals", 231.9, 2270.0);
		Chem[95] = new Chemical(38, 87.62, "Strontium", "Sr", 38, 50, true, "Alkaline Earth", 769.0, 1384.0);
		Chem[96] = new Chemical(73, 180.94788, "Tantalum", "Ta", 73, 108, true, "Transition Metal", 2996.0, 5425.0);
		Chem[97] = new Chemical(65, 158.92535, "Terbium", "Tb", 65, 94, true, "Rare Earth", 1360.0, 3041.0);
		Chem[98] = new Chemical(43, 98.0, "Technetium", "Tc", 43, 55, true, "Transition Metal", 2200.0, 4877.0);
		Chem[99] = new Chemical(52, 127.6, "Tellurium", "Te", 52, 76, true, "Metalloid", 449.5, 989.8);
		Chem[100] = new Chemical(90, 232.03806, "Thorium", "Th", 90, 142, true, "Rare Earth", 1750.0, 4790.0);
		Chem[101] = new Chemical(22, 47.867, "Titanium", "Ti", 22, 26, true, "Transition Metal", 1660.0, 3287.0);
		Chem[102] = new Chemical(81, 204.3833, "Thallium", "Tl", 81, 123, true, "Other Metals", 303.5, 1457.0);
		Chem[103] = new Chemical(69, 168.93421, "Thulium", "Tm", 69, 100, true, "Rare Earth", 1545.0, 1727.0);
		Chem[104] = new Chemical(92, 238.02891, "Uranium", "U", 92, 146, true, "Rare Earth", 1132.0, 3818.0);
		Chem[105] = new Chemical(116, 293.0, "Livermorium", "Lv", 116, 173, true, "Other Metal");
		Chem[106] = new Chemical(110, 281.0, "Darmstadtium", "Ds", 110, 159, true, "Transition Metal");
		Chem[107] = new Chemical(118, 294.0, "Ununoctium", "Uuo", 118, 175, true, "Non-metal");
		Chem[108] = new Chemical(115, 288.0, "Ununpentium", "Uup", 115, 176, true, "Other Metal");
		Chem[109] = new Chemical(114, 289.0, "Flerovium", "Fl", 114, 175, true, "Other Metal");
		Chem[110] = new Chemical(117, 294.0, "Ununseptium", "Uus", 117, 177, true, "Unknown");
		Chem[111] = new Chemical(113, 284.0, "Ununtrium", "Uut", 113, 170, true, "Other Metal");
		Chem[112] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
		Chem[113] = new Chemical(23, 50.9415, "Vanadium", "V", 23, 28, true, "Transition Metal", 1890.0, 3380.0);
		Chem[114] = new Chemical(74, 183.84, "Tungsten", "W", 74, 110, true, "Transition Metal", 3410.0, 5660.0);
		Chem[115] = new Chemical(54, 131.293, "Xenon", "Xe", 54, 77, true, "Noble Gas", -111.9, -108.1);
		Chem[116] = new Chemical(39, 88.90585, "Yttrium", "Y", 39, 50, true, "Transition Metal", 1523.0, 3337.0);
		Chem[117] = new Chemical(70, 173.054, "Ytterbium", "Yb", 70, 103, true, "Rare Earth", 824.0, 1466.0);
		Chem[118] = new Chemical(30, 65.38, "Zinc", "Zn", 30, 35, true, "Transition Metal", 419.58, 907.0);
		Chem[119] = new Chemical(40, 91.224, "Zirconium", "Zr", 40, 51, true, "Transition Metal", 1852.0, 4377.0);
		
		return Chem;
	}
	
	public static Question[] getQuestions()
	{
		int numQuestions = 20;
		int TYPE_AMOUNT_PeriodicTable = 0;
		int TYPE_AMOUNT_PeriodicElements = 0;
		Question[] question = new Question[numQuestions];
		
		question[0] = new Question("Periodic Table", "Column", "What is a vertical column of elements in the periodic table called?", "image", "GroupPCXColumnPCXRowPCXPeriod");
		question[1] = new Question("Periodic Table", "Column 2", "What is a horizontal row of elements in the periodic table called?", "image 2", "PeriodPCXGroupPCXRowPCXColumn");
		question[2] = new Question("Periodic Table", "Column 3", "What is a vertical column of elements that share the same chemical properties called?", "image 3", "FamilyPCXRowPCXGasesPCXColumn");
		question[3] = new Question("Periodic Table", "Column 4", "What is the total mass of protons + neutrons in an element called?", "image 4", "Atomic MassPCXAtomic NumberPCXWeightPCXAtom");
		question[4] = new Question("Periodic Table", "Column 5", "What is the tendency of an atom to attract electrons in the formation of an ionic bond called?", "image 5", "ElectronegativityPCXElectron CloudPCXIonization EnergyPCXCation");
		question[5] = new Question("Periodic Element", "Element 1", "What is the name of the element whose Symbol is \"Md\"?", "image", "MendeleviumPCXMagnesiumPCXManganesePCXHydrogen");
		question[6] = new Question("Periodic Element", "Element 2", "What is the symbol of \"Cerium\"?", "image 2", "CePCXCPCXScPCXCs");
		question[7] = new Question("Periodic Element", "Element 3", "What is the symbol of \"Gold\"?", "image", "AuPCXAgPCXGdPCXGa");
		question[8] = new Question("Periodic Element", "Element 4", "What is the atomic number of \"Krypton\"?", "image 2", "36PCX65PCX17PCX107");
		question[9] = new Question("Periodic Element", "Element 5", "What is the name of the element whose atomic mass is \"72.63\"?", "image", "GermaniumPCXPlutoniumPCXManganesePCXIron");
		question[10] = new Question("Periodic Element", "Element 6", "What is the atomic mass of \"Dysprosium\"?", "image 2", "162.5PCX185.18PCX130.6461PCX78.2");
		question[11] = new Question("Periodic Element", "Element 7", "What is the name of the element whose Symbol is \"Ag\"?", "image", "SilverPCXMagnesiumPCXManganesePCXIron");
		question[12] = new Question("Periodic Element", "Element 8", "What is the name of the element whose Symbol is \"K\"?", "image 2", "PotassiumPCXKryptonPCXNickelPCXBerkelium");
		question[13] = new Question("Periodic Element", "Element 9", "What is the atomic number of \"Xe\"?", "image", "54PCX13PCX78PCX66");
		question[14] = new Question("Periodic Element", "Element 10", "What is the symbol of \"Lead\"?", "image 2", "PbPCXLiPCXScPCXFe");
		question[15] = new Question("Periodic Element", "Element 11", "What is the atomic number of \"Nytrogen\"?", "image 2", "7PCX9PCX65PCX59");
		question[16] = new Question("Periodic Element", "Element 12", "What is the name of the element whose Symbol is \"Fe\"?", "image", "IronPCXFermiumPCXFluorinePCXFlerovium");
		question[17] = new Question("Periodic Element", "Element 13", "What is the name of the element whose Symbol is \"Gd\"?", "image 2", "GadoliniumPCXGoldPCXSilverPCXArgon");
		question[18] = new Question("Periodic Element", "Element 14", "What is the atomic number of \"Co\"?", "image", "27PCX29PCX34PCX16");
		question[19] = new Question("Periodic Element", "Element 15", "What is the symbol of \"Copper\"?", "image 2", "CuPCXCoPCXCdPCXHg");
		
		for(int b = 0; b < numQuestions; b++)
		{
			if(question[b].getType().equals("Periodic Table"))
				TYPE_AMOUNT_PeriodicTable++;
			else if(question[b].getType().equals("Periodic Element"))
				TYPE_AMOUNT_PeriodicElements++;
		}
		
		MySingleton.getInstance().setTYPE_AMOUNT_PeriodicTable(TYPE_AMOUNT_PeriodicTable);
		MySingleton.getInstance().setTYPE_AMOUNT_PeriodicElements(TYPE_AMOUNT_PeriodicElements);
		
		return question;
	}
	
	public static boolean isTablet(Context context)
	{
	    boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
	    boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
	    return (xlarge || large);
	}
}
