package com.pk.chemhelp;

import java.text.DecimalFormat;

public class Conversions
{
	/**
	 * Bleh
	 */
	public Conversions()
	{
		
	}
	
	public static double roundTwoDecimals(double d)
	{
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
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
	
    /**
     * Temperature convertions
     */
    public static double Convert2KfromC(double T)
    {
        double kelvins = 273;
        kelvins += T;
        return kelvins;
    }
    
    public static double Convert2KfromF(double T)
    {
        double temp = 0;
        temp = ((T - 32) * 5) / 9 + 273;
        return temp;
    }
    
    public static double Convert2CfromF(double T)
    {
        double temp = 0;
        temp = ((T - 32) * 5) / 9;
        return temp;
    }
    
    public static double Convert2CfromK(double T)
    {
        double temp = 0;
        temp = T - 273;
        return temp;
    }
    
    public static double Convert2FfromK(double T)
    {
        double temp = 0;
        temp = 1.8 * (T - 273) + 32;
        return temp;
    }
    
    public static double Convert2FfromC(double T)
    {
        double temp = 0;
        temp = 1.8 * T + 32;
        return temp;
    }
    
    /**
     * Volume conversions
     */
    public static double Convert2LfromML(double V)
    {
        double volume = 0;
        volume = V * 0.001;
        return volume;
    }
    
    public static double Convert2LfromG(double V)
    {
        double volume = 0;
        volume = V * 0.001;
        return volume;
    }
    
    public static double Convert2MLfromL(double V)
    {
        double volume = 0;
        volume = V * 1000;
        return volume;
    }
    
    public static double Convert2MLfromG(double V)
    {
        double volume = 0;
        volume = V;
        return volume;
    }
    
    public static double Convert2GfromL(double V)
    {
        double volume = 0;
        volume = V * 1000;
        return volume;
    }
    
    public static double Convert2GfromML(double V)
    {
        double volume = 0;
        volume = V;
        return volume;
    }
    
    /**
     * Pressure conversions.
     */
    public static double Convert2ATMfromMMHG(double P)
    {
        double pressure = 0;
        pressure = P / 760;
        return pressure;
    }
    
    public static double Convert2ATMfromTORR(double P)
    {
        double pressure = 0;
        pressure = P / 760;
        return pressure;
    }
    
    public static double Convert2ATMfromKPA(double P)
    {
        double pressure = 0;
        pressure = P / 101.325;
        return pressure;
    }
    
    public static double Convert2MMHGfromATM(double P)
    {
        double pressure = 0;
        pressure = P * 760;
        return pressure;
    }
    
    
    public static double Convert2MMHGfromKPA(double P)
    {
        double pressure = 0;
        pressure = P * 7.50061561303;
        return pressure;
    }
    
    public static double Convert2MMHGfromTORR(double P)
    {
        double pressure = 0;
        pressure = P * 0.999999849988;
        return pressure;
    }
    
    public static double Convert2TORRfromATM(double P)
    {
        double pressure = 0;
        pressure = P * 760;
        return pressure;
    }
    
    public static double Convert2TORRfromMMHG(double P)
    {
        double pressure = 0;
        pressure = P * 1.00000015001;
        return pressure;
    }
    
    public static double Convert2TORRfromKPA(double P)
    {
        double pressure = 0;
        pressure = P * 7.5006375541921;
        return pressure;
    }
    
    public static double Convert2KPAfromATM(double P)
    {
        double pressure = 0;
        pressure = P * 101.325;
        return pressure;
    }
    
    public static double Convert2KPAfromMMHG(double P)
    {
        double pressure = 0;
        pressure = P * 0.1333223684211;
        return pressure;
    }
    
    public static double Convert2KPAfromTORR(double P)
    {
        double pressure = 0;
        pressure = P * 0.133322;
        return pressure;
    }
    
    
    
    
    
    
    
    
    
    /**
     * Show your work, bro!!!!!!
     */
    
    
    
    
    
    
    
    
    
    public static String Convert2KfromC_SW(double T)
    {
        double kelvins = 273;
        String Step1, Step2, Step3, Answer, Kelvins;
        Step1 = "(Step 1)\nWrite down the formula...\n\nK = C + 273";
        Step2 = "(Step 2)\nPlug in your variables...\n\nK = " + T + " + 273";
        Step3 = "(Step 3)\nAdd...\n\nK = " + (T + kelvins);
        Answer = "Your answer is: " + (T + kelvins);
        Kelvins = Step1 + Step2 + Step3 + Answer;
        return Kelvins;
    }
    
    public static String Convert2KfromF_SW(double T)
    {
        String Step1, Step2, Step3, Step4, Step5, Step6, Answer, Fahrenheit;
        Step1 = "(Step 1)\nWrite down the formula...\n\nK = 5 / 9 * (F - 32) + 273";
        Step2 = "(Step 2)\nPlug in your variables...\n\nK = 5 / 9 * (" + T + " - 32) + 273";
        Step3 = "(Step 3)\nSubtract the numbers in parantheses...\n\nK = 5 / 9 * " + (T - 32) + " + 273";
        Step4 = "(Step 4)\nDivide 5 by 9...\n\nK = "  + (5 / 9) + " * " + (T - 32) + " + 273";
        Step5 = "(Step 5)\nMultiply...\n\nK = " + ((5 / 9) * (T - 32)) + " + 273";
        Step6 = "(Step 6)\nAdd...\n\nK = " + (5 / 9 * (T - 32) + 273);
        Answer = "Your answer is: " + (5 / 9 * (T - 32) + 273);
        Fahrenheit = Step1 + Step2 + Step3 + Step4 + Step5 + Step6 + Answer;
        return Fahrenheit;
    }
        
    public static String Convert2CfromF_SW(double T)
    {
        String Step1, Step2, Step3, Step4, Step5, Answer, Celcius;
        Step1 = "(Step 1)\nWrite down the formula...\n\nC = 5 / 9 * (F - 32)";
        Step2 = "(Step 2)\nPlug in your variables...\n\nC = 5 / 9 * (" + T + " - 32)";
        Step3 = "(Step 3)\nSubtract the numbers in parantheses...\n\nC = 5 / 9 * " + (T - 32);
        Step4 = "(Step 4)\nDivide 5 by 9...\n\nC = "  + (5 / 9) + " * " + (T - 32);
        Step5 = "(Step 5)\nMultiply...\n\nC = " + ((5 / 9) * (T - 32));
        Answer = "Your answer is: " + (5 / 9 * (T - 32));
        Celcius = Step1 + Step2 + Step3 + Step4 + Step5 + Answer;
        return Celcius;
    }
    
    public static String Convert2CfromK_SW(double T)
    {
        String Step1, Step2, Step3, Answer, Celcius;
        Step1 = "(Step 1)\nWrite down the formula...\n\nC = K - 273";
        Step2 = "(Step 2)\nPlug in your variables...\n\nC = " + T + " - 273";
        Step3 = "(Step 3)\nSubtract...\n\nC = " + (T - 273);
        Answer = "Your answer is: " + (T - 273);
        Celcius = Step1 + Step2 + Step3 + Answer;
        return Celcius;
    }
    
    public static String Convert2FfromK_SW(double T)
    {
        String Step1, Step2, Step3, Step4, Step5, Step6, Answer, Fahrenheit;
        Step1 = "(Step 1)\nWrite down the formula...\n\nF = 9 / 5 * (F - 273) + 32";
        Step2 = "(Step 2)\nPlug in your variables...\n\nF = 9 / 5 * (" + T + " - 273) + 32";
        Step3 = "(Step 3)\nSubtract the numbers in parantheses...\n\nF = 9 / 5 * " + (T - 273) + " + 32";
        Step4 = "(Step 4)\nDivide 9 by 5...\n\nF = "  + (9 / 5) + " * " + (T - 273) + " + 32";
        Step5 = "(Step 5)\nMultiply...\n\nF = " + ((9 / 5) * (T - 273)) + " + 32";
        Step6 = "(Step 6)\nAdd...\n\nF = " + (9 / 5 * (T - 273) + 32);
        Answer = "Your answer is: " + (9 / 5 * (T - 273) + 32);
        Fahrenheit = Step1 + Step2 + Step3 + Step4 + Step5 + Step6 + Answer;
        return Fahrenheit;
    }
    
    public static String Convert2FfromC_SW(double T)
    {
        String Step1, Step2, Step3, Step4, Step5, Answer, Fahrenheit;
        Step1 = "(Step 1)\nWrite down the formula...\n\nF = 9 / 5 * F + 32";
        Step2 = "(Step 2)\nPlug in your variables...\n\nF = 9 / 5 * " + T + " + 32";
        Step3 = "(Step 3)\nDivide 9 by 5...\n\nF = "  + (9 / 5) + " * " + T + " + 32";
        Step4 = "(Step 4)\nMultiply...\n\nF = " + ((9 / 5) * T) + " + 32";
        Step5 = "(Step 5)\nAdd...\n\nF = " + (9 / 5 * T + 32);
        Answer = "Your answer is: " + (9 / 5 * T + 32);
        Fahrenheit = Step1 + Step2 + Step3 + Step4 + Step5 + Answer;
        return Fahrenheit;
    }
}
