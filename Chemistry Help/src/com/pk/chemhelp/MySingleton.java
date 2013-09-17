package com.pk.chemhelp;

public class MySingleton
{
	private static MySingleton instance;
	
	/* VERSION NUMBER */
	public static final float VersionNumber = 0.7F;
	public static final double BuildNumber = 0.7;
	public static Boolean PRO;
	
	/* Debug */
	public static Boolean DEBUG_MODE;
	public static Boolean FORCE_STOP;
    public static String ERROR;
    public static String oldERROR1;
    public static String oldERROR2;
    public static String oldERROR3;
    public static String oldERROR4;
    public static String oldERROR5;
    public static Integer ERROR_COUNTER;
	public static String[] DebugLogs;
	public static String[] ErrorLogs;
	public static Integer DebugCounter;
	public static Integer ErrorCounter;
	public static String Debug_DialogPosition;
	
	/* App Navigation */
	public static Boolean Exit;						// Needed to exit the app via action bar button
	public static String PreviousPageID;			// When needed to go back in dynamic navigation
	
	/* Bookmarks */
	public static Integer numBookmarks;
	public static Bookmark[] Bookmarks;
	public static String BookmarkName;
	public static String BookmarkDescription;
	public static String BookmarkIcon;
	public static String[] PageValues;
	
	/* Saved Quizes */
	public static Integer numSavedQuizes;
	
	/* Quiz Constants */
	public static Integer TYPE_AMOUNT_PeriodicTable;
	public static Integer TYPE_AMOUNT_PeriodicElements;
	
	/* Declare DEFAULT Variables */
	public static Boolean showWork;					// True is default.
	public static Integer defaultTempUnit;			// 0 = Kelvin, 1 = Celcius, 2 = Fahrenheit.
	public static String defaultTemp;				// Kelvins is default.
	public static String defaultTempS;				// Short way of temperature unit. "K" is default.
	public static Integer defaultPressureUnit;		// 0 = atm, 1 = mmHg, 2 = Torr, 3 = kPa
    public static String defaultPres;				// atm is default.
	public static String defaultPresS;				// Short way of pressure units "atm" is default.
    public static Integer defaultVolumeUnit;		// 0 = liters (L), 1 = milli liters (mL), 2 = grams (g)
    public static String defaultVol;				// Liters is default.
    public static String defaultVolS;				// Short way of volume unit. "L" default.
    
    /* Settings */
    
    /* Networking */
    public static String updateDetails;
    
    /* Declare These For Problem Solving */
	public static Double N;
	public static Double V;
    public static Double V1;
    public static Double V2;
	public static Double P;
    public static Double P1;
    public static Double P2;
	public static Double T;
    public static Double T1;
    public static Double T2;
    
	/* Declare These To Let Methods Know What Units Are Selected */
	public static String SelectN;
	public static String SelectV;
	public static String SelectV1;
	public static String SelectV2;
	public static String SelectP;
	public static String SelectP1;
	public static String SelectP2;
	public static String SelectT;
	public static String SelectT1;
	public static String SelectT2;
	
	/* Previous and Current Units */
	public static Integer Previous_VUnit;
	public static Integer Previous_PUnit;
	public static Integer Previous_TUnit;
	public static Integer Previous_V1Unit;
	public static Integer Previous_P1Unit;
	public static Integer Previous_T1Unit;
	public static Integer Previous_V2Unit;
	public static Integer Previous_P2Unit;
	public static Integer Previous_T2Unit;
	public static Integer Current_VUnit;
	public static Integer Current_PUnit;
	public static Integer Current_TUnit;
	public static Integer Current_V1Unit;
	public static Integer Current_P1Unit;
	public static Integer Current_T1Unit;
	public static Integer Current_V2Unit;
	public static Integer Current_P2Unit;
	public static Integer Current_T2Unit;
    
    /* Declare Category and Subcategory Variables for Spinner Purposes */
    public static String category;
    public static Integer subcategory;
    public static Integer subcategory2;
    public static String outputUnit;
    
	/* Customized Results */
	public static String maxDecimalAccuracy;
	public static String selectedTemperature;
	public static String selectedPressure;
	public static String selectedVolume;
	public static Boolean Gas_SameUnits;
	public static Boolean Gas_AutoSolve;
	public static Boolean Gas_FormatHelp;
	public static String Convert_DefaultCategory;
	public static String Convert_MaxDecimalAccuracy;
	public static Boolean Convert_AutoSolve;
	public static Boolean Convert_FormatHelp;
	public static Boolean Periodic_ShowProgress;
	public static Boolean Periodic_ClickableElements;
	public static String Periodic_Sort;
	public static Boolean Periodic_ShowSelector;
	public static Boolean Periodic_ShowSearch;
	public static Boolean Quiz_NotifyCorrect;
	public static Boolean Quiz_ShowTime;
	public static Boolean Quiz_ShowPercent;
	public static Boolean Quiz_ShowProgress;
	
    /* Just for Testing Purposes */
    public static String testString;
    public static Integer testInteger;
    public static Double testDouble;
	
	public static void initInstance()
	{
		if (instance == null)
		{
			// Create the instance
			instance = new MySingleton();
		}
	}
	
	public static void initDefaultValues()
	{
		if(PRO == null)
			PRO = false;
		if(DEBUG_MODE == null)
			DEBUG_MODE = false;
		if(showWork == null)
			showWork = true;
		if(numBookmarks == null)
			numBookmarks = 0;
		if(Bookmarks == null)
		{
			Bookmarks = new Bookmark[150];
			initializeBookmarks();
		}
		if(PageValues == null)
		{
			PageValues = new String[50];
			initializePageValues();
		}
		if(DebugLogs == null)
		{
			DebugLogs = new String[250];
			initializeDebugLog();
		}
		if(ErrorLogs == null)
		{
			ErrorLogs = new String[100];
			initializeErrorLog();
		}
		if(DebugCounter == null)
			DebugCounter = 0;
		if(ErrorCounter == null)
			ErrorCounter = 0;
		if(Debug_DialogPosition == null)
			Debug_DialogPosition = "";
		if(numSavedQuizes == null)
			numSavedQuizes = 0;
		if(TYPE_AMOUNT_PeriodicElements == null)
			TYPE_AMOUNT_PeriodicElements = 2;
		if(TYPE_AMOUNT_PeriodicTable == null)
			TYPE_AMOUNT_PeriodicTable = 5;
		if(defaultTempUnit == null)
			defaultTempUnit = 0;
		if(defaultTemp == null)
			defaultTemp = "Kelvins";
		if(defaultTempS == null)
			defaultTempS = "K";
		if(defaultPressureUnit == null)
			defaultPressureUnit = 0;
		if(defaultPres == null)
			defaultPres = "atm";
		if(defaultPresS == null)
			defaultPresS = "atm";
		if(defaultVolumeUnit == null)
			defaultVolumeUnit = 0;
		if(defaultVol == null)
			defaultVol = "liters";
		if(defaultVolS == null)
			defaultVolS = "L";
		if(category == null)
			category = "";
		if(subcategory == null)
			subcategory = 0;
		if(subcategory2 == null)
			subcategory2 = 0;
		if(outputUnit == null)
			outputUnit = "";
		if(testString == null)
			testString = "";
		if(testInteger == null)
			testInteger = 0;
		if(testDouble == null)
			testDouble = 0.0;
		if(ERROR == null)
			ERROR = "";
		if(oldERROR1 == null)
			oldERROR1 = "";
		if(oldERROR2 == null)
			oldERROR2 = "";
		if(oldERROR3 == null)
			oldERROR3 = "";
		if(oldERROR4 == null)
			oldERROR4 = "";
		if(oldERROR5 == null)
			oldERROR5 = "";
		if(ERROR_COUNTER == null)
			ERROR_COUNTER = 0;
		if(N == null)
			N = -1.0;
		if(V == null)
			V = -1.0;
		if(V1 == null)
			V1 = -1.0;
		if(V2 == null)
			V2 = -1.0;
		if(P == null)
			P = -1.0;
		if(P1 == null)
			P1 = -1.0;
		if(P2 == null)
			P2 = -1.0;
		if(T == null)
			T = -1.0;
		if(T1 == null)
			T1 = -1.0;
		if(T2 == null)
			T2 = -1.0;
		if(SelectN == null)
			SelectN = "";
		if(SelectV == null)
			SelectV = "";
		if(SelectV1 == null)
			SelectV1 = "";
		if(SelectV2 == null)
			SelectV2 = "";
		if(SelectP == null)
			SelectP = "";
		if(SelectP1 == null)
			SelectP1 = "";
		if(SelectP2 == null)
			SelectP2 = "";
		if(SelectT == null)
			SelectT = "";
		if(SelectT1 == null)
			SelectT1 = "";
		if(SelectT2 == null)
			SelectT2 = "";
		if(Previous_VUnit == null)
			Previous_VUnit = -1;
		if(Previous_PUnit == null)
			Previous_PUnit = -1;
		if(Previous_TUnit == null)
			Previous_TUnit = -1;
		if(Previous_V1Unit == null)
			Previous_V1Unit = -1;
		if(Previous_P1Unit == null)
			Previous_P1Unit = -1;
		if(Previous_T1Unit == null)
			Previous_T1Unit = -1;
		if(Previous_V2Unit == null)
			Previous_V2Unit = -1;
		if(Previous_P2Unit == null)
			Previous_P2Unit = -1;
		if(Previous_T2Unit == null)
			Previous_T2Unit = -1;
		if(Current_VUnit == null)
			Current_VUnit = -1;
		if(Current_PUnit == null)
			Current_PUnit = -1;
		if(Current_TUnit == null)
			Current_TUnit = -1;
		if(Current_V1Unit == null)
			Current_V1Unit = -1;
		if(Current_P1Unit == null)
			Current_P1Unit = -1;
		if(Current_T1Unit == null)
			Current_T1Unit = -1;
		if(Current_V2Unit == null)
			Current_V2Unit = -1;
		if(Current_P2Unit == null)
			Current_P2Unit = -1;
		if(Current_T2Unit == null)
			Current_T2Unit = -1;
		if(maxDecimalAccuracy == null)
			maxDecimalAccuracy = "5";
		if(selectedTemperature == null)
			selectedTemperature = "";
		if(selectedPressure == null)
			selectedPressure = "";
		if(selectedVolume == null)
			selectedVolume = "";
		if(Gas_SameUnits == null)
			Gas_SameUnits = true;
		if(Gas_AutoSolve == null)
			Gas_AutoSolve = false;
		if(Gas_FormatHelp == null)
			Gas_FormatHelp = false;
		if(Convert_MaxDecimalAccuracy == null)
			Convert_MaxDecimalAccuracy = "5";
		if(Convert_DefaultCategory == null)
			Convert_DefaultCategory = "Blank";
		if(Convert_AutoSolve == null)
			Convert_AutoSolve = true;
		if(Convert_FormatHelp == null)
			Convert_FormatHelp = true;
		if(Periodic_ShowProgress == null)
			Periodic_ShowProgress = true;
		if(Periodic_ClickableElements == null)
			Periodic_ClickableElements = true;
		if(Periodic_Sort == null)
			Periodic_Sort = "Symbol";
		if(Periodic_ShowSelector == null)
			Periodic_ShowSelector = true;
		if(Periodic_ShowSearch == null)
			Periodic_ShowSearch = true;
		if(Quiz_NotifyCorrect == null)
			Quiz_NotifyCorrect = false;
		if(Quiz_ShowTime == null)
			Quiz_ShowTime = true;
		if(Quiz_ShowPercent == null)
			Quiz_ShowPercent = true;
		if(Quiz_ShowProgress == null)
			Quiz_ShowProgress = true;
		if(PreviousPageID == null)
			PreviousPageID = "Main";
		if(Exit == null)
			Exit = false;
		if(FORCE_STOP == null)
			FORCE_STOP = false;
		if(updateDetails == null)
			updateDetails = "";
	}
	
	public static MySingleton getInstance()
	{
		// Return the instance
		return instance;
	}
	
	private MySingleton()
	{
		// Constructor hidden because this is a singleton
	}
	
	public static void initializeBookmarks()
	{
		for (int x = 0; x < 150; x++)
			Bookmarks[x] = new Bookmark("pcx_name", "pcx_description", "pcx_icon", "pcx_pageid");
	}
	
	public static void initializePageValues()
	{
		for(int x = 0; x < 50; x++)
			PageValues[x] = "pcx_value";
	}
	
	public static String[] getPageValues()
	{
		return PageValues;
	}
	
	public static void setPageValues(String[] pv)
	{
		PageValues = pv;
	}
	
	public static void initializeDebugLog()
	{
		for(int x = 0; x < 250; x++)
			DebugLogs[x] = "pcx_value";
	}
	
	public void addLog(String l)
	{
		for(int x = 0; x < 250; x++)
		{
			if(DebugLogs[x].equals("pcx_value"))
			{
				DebugLogs[x] = l;
				DebugCounter++;
				break;
			}
		}
	}
	
	public void clearLog()
	{
		for(int x = 0; x < 250; x++)
			DebugLogs[x] = "pcx_value";
		
		DebugCounter = 0;
	}
	
	public String[] getLog()
	{
		return DebugLogs;
	}
	
	public int getLogCount()
	{
		return DebugCounter;
	}
	
	public static void initializeErrorLog()
	{
		for(int x = 0; x < 100; x++)
			ErrorLogs[x] = "pcx_value";
	}
	
	public void addError(String e)
	{
		for(int x = 0; x < 100; x++)
		{
			if(ErrorLogs[x].equals("pcx_value"))
			{
				ErrorLogs[x] = e;
				ErrorCounter++;
				break;
			}
		}
	}
	
	public void clearErrors()
	{
		for(int x = 0; x < 100; x++)
			ErrorLogs[x] = "pcx_value";
		
		ErrorCounter = 0;
	}
	
	public String[] getErrors()
	{
		return ErrorLogs;
	}
	
	public int getErrorCount()
	{
		return ErrorCounter;
	}
	
	public float getVersionNumber()
	{
		return VersionNumber;
	}
	
	public double getBuildNumber()
	{
		return BuildNumber;
	}
	
	public String getPreviousPageID()
	{
		return PreviousPageID;
	}
	
	public void setPreviousPageID(String prev)
	{
		PreviousPageID = prev;
	}
	
	public int getNumBookmarks()
	{
		return numBookmarks;
	}
	
	public void setNumBookmarks(int nb)
	{
		numBookmarks = nb;
	}
	
	public Bookmark[] getBookmarks()
	{
		return Bookmarks;
	}
	
	public void setBookmarks(Bookmark[] BM)
	{
		Bookmarks = BM;
	}
	
	public boolean isPRO()
	{
		return PRO;
	}
	
	public void setPRO(boolean p)
	{
		PRO = p;
	}
	
	public int getNumSavedQuizes()
	{
		return numSavedQuizes;
	}
	
	public void setNumSavedQuizes(int nq)
	{
		numSavedQuizes = nq;
	}
	
	public boolean getExit()
	{
		return Exit;
	}
	
	public void setExit(boolean e)
	{
		Exit = e;
	}
	
	public boolean getShowWork()
	{
		return showWork;
	}
	
	public void setShowWork(boolean b)
	{
		showWork = b;
	}
	
	public int getDefaultTempUnit()
	{
		return defaultTempUnit;
	}
	
	public void setDefaultTempUnit(int u)
	{
		defaultTempUnit = u;
	}
	
	public String getDefaultTemp()
	{
		return defaultTemp;
	}
	
	public void setDefaultTemp(String t)
	{
		defaultTemp = t;
	}
	
	public String getDefaultTempS()
	{
		return defaultTempS;
	}
	
	public void setDefaultTempS(String t)
	{
		defaultTempS = t;
	}
	
	public int getDefaultPressureUnit()
	{
		return defaultPressureUnit;
	}
	
	public void setDefaultPressureUnit(int u)
	{
		defaultPressureUnit = u;
	}
	
	public String getDefaultPres()
	{
		return defaultPres;
	}
	
	public void setDefaultPres(String p)
	{
		defaultPres = p;
	}
	
	public String getDefaultPresS()
	{
		return defaultPresS;
	}
	
	public void setDefaultPresS(String p)
	{
		defaultPresS = p;
	}
	
	public int getDefaultVolumeUnit()
	{
		return defaultVolumeUnit;
	}
	
	public void setDefaultVolumeUnit(int u)
	{
		defaultVolumeUnit = u;
	}
	
	public String getDefaultVol()
	{
		return defaultVol;
	}
	
	public void setDefaultVol(String v)
	{
		defaultVol = v;
	}
	
	public String getDefaultVolS()
	{
		return defaultVolS;
	}
	
	public void setDefaultVolS(String v)
	{
		defaultVolS = v;
	}
	
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String c)
	{
		category = c;
	}
	
	public int getSubcategory()
	{
		return subcategory;
	}
	
	public void setSubcategory(int c)
	{
		subcategory = c;
	}
	
	public int getSubcategory2()
	{
		return subcategory2;
	}
	
	public void setSubcategory2(int c)
	{
		subcategory2 = c;
	}
	
	public String getOutputUnit()
	{
		return outputUnit;
	}
	
	public void setOutputUnit(String u)
	{
		outputUnit = u;
	}
	
	public double getN()
	{
		return N;
	}
	
	public void setN(double n)
	{
		N = n;
	}
	
	public double getV()
	{
		return V;
	}
	
	public void setV(double v)
	{
		V = v;
	}
	
	public double getV1()
	{
		return V1;
	}
	
	public void setV1(double v)
	{
		V1 = v;
	}
	
	public double getV2()
	{
		return V2;
	}
	
	public void setV2(double v)
	{
		V2 = v;
	}
	
	public double getP()
	{
		return P;
	}
	
	public void setP(double p)
	{
		P = p;
	}
	
	public double getP1()
	{
		return P1;
	}
	
	public void setP1(double p)
	{
		P1 = p;
	}
	
	public double getP2()
	{
		return P2;
	}
	
	public void setP2(double p)
	{
		P2 = p;
	}
	
	public double getT()
	{
		return T;
	}
	
	public void setT(double t)
	{
		T = t;
	}
	
	public double getT1()
	{
		return T1;
	}
	
	public void setT1(double t)
	{
		T1 = t;
	}
	
	public double getT2()
	{
		return T2;
	}
	
	public void setT2(double t)
	{
		T2 = t;
	}
	
	public String getSelectV()
	{
		return SelectV;
	}
	
	public void setSelectV(String sv)
	{
		SelectV = sv;
	}
	
	public String getSelectV1()
	{
		return SelectV1;
	}
	
	public void setSelectV1(String sv)
	{
		SelectV1 = sv;
	}
	
	public String getSelectV2()
	{
		return SelectV2;
	}
	
	public void setSelectV2(String sv)
	{
		SelectV2 = sv;
	}
	
	public String getSelectP()
	{
		return SelectP;
	}
	
	public void setSelectP(String sp)
	{
		SelectP = sp;
	}
	
	public String getSelectP1()
	{
		return SelectP1;
	}
	
	public void setSelectP1(String sp)
	{
		SelectP1 = sp;
	}
	
	public String getSelectP2()
	{
		return SelectP2;
	}
	
	public void setSelectP2(String sp)
	{
		SelectP2 = sp;
	}
	
	public String getSelectT()
	{
		return SelectT;
	}
	
	public void setSelectT(String st)
	{
		SelectT = st;
	}
	
	public String getSelectT1()
	{
		return SelectT1;
	}
	
	public void setSelectT1(String st)
	{
		SelectT1 = st;
	}
	
	public String getSelectT2()
	{
		return SelectT2;
	}
	
	public void setSelectT2(String st)
	{
		SelectT2 = st;
	}
	
	public int getPrevious_VUnit()
	{
		return Previous_VUnit;
	}
	
	public void setPrevious_VUnit(int v)
	{
		Previous_VUnit = v;
	}
	
	public int getPrevious_PUnit()
	{
		return Previous_PUnit;
	}
	
	public void setPrevious_PUnit(int p)
	{
		Previous_PUnit = p;
	}
	
	public int getPrevious_TUnit()
	{
		return Previous_TUnit;
	}
	
	public void setPrevious_TUnit(int t)
	{
		Previous_TUnit = t;
	}
	
	public int getPrevious_V1Unit()
	{
		return Previous_V1Unit;
	}
	
	public void setPrevious_V1Unit(int v)
	{
		Previous_V1Unit = v;
	}
	
	public int getPrevious_P1Unit()
	{
		return Previous_P1Unit;
	}
	
	public void setPrevious_P1Unit(int p)
	{
		Previous_P1Unit = p;
	}
	
	public int getPrevious_T1Unit()
	{
		return Previous_T1Unit;
	}
	
	public void setPrevious_T1Unit(int t)
	{
		Previous_T1Unit = t;
	}
	
	public int getPrevious_V2Unit()
	{
		return Previous_V2Unit;
	}
	
	public void setPrevious_V2Unit(int v)
	{
		Previous_V2Unit = v;
	}
	
	public int getPrevious_P2Unit()
	{
		return Previous_P2Unit;
	}
	
	public void setPrevious_P2Unit(int p)
	{
		Previous_P2Unit = p;
	}
	
	public int getPrevious_T2Unit()
	{
		return Previous_T2Unit;
	}
	
	public void setPrevious_T2Unit(int t)
	{
		Previous_T2Unit = t;
	}
	
	public int getCurrent_VUnit()
	{
		return Current_VUnit;
	}
	
	public void setCurrent_VUnit(int v)
	{
		Current_VUnit = v;
	}
	
	public int getCurrent_PUnit()
	{
		return Current_PUnit;
	}
	
	public void setCurrent_PUnit(int p)
	{
		Current_PUnit = p;
	}
	
	public int getCurrent_TUnit()
	{
		return Current_TUnit;
	}
	
	public void setCurrent_TUnit(int t)
	{
		Current_TUnit = t;
	}
	
	public int getCurrent_V1Unit()
	{
		return Current_V1Unit;
	}
	
	public void setCurrent_V1Unit(int v)
	{
		Current_V1Unit = v;
	}
	
	public int getCurrent_P1Unit()
	{
		return Current_P1Unit;
	}
	
	public void setCurrent_P1Unit(int p)
	{
		Current_P1Unit = p;
	}
	
	public int getCurrent_T1Unit()
	{
		return Current_T1Unit;
	}
	
	public void setCurrent_T1Unit(int t)
	{
		Current_T1Unit = t;
	}
	
	public int getCurrent_V2Unit()
	{
		return Current_V2Unit;
	}
	
	public void setCurrent_V2Unit(int v)
	{
		Current_V2Unit = v;
	}
	
	public int getCurrent_P2Unit()
	{
		return Current_P2Unit;
	}
	
	public void setCurrent_P2Unit(int p)
	{
		Current_P2Unit = p;
	}
	
	public int getCurrent_T2Unit()
	{
		return Current_T2Unit;
	}
	
	public void setCurrent_T2Unit(int t)
	{
		Current_T2Unit = t;
	}
	
	public String getMaxDecimalAccuracy()
	{
		return maxDecimalAccuracy;
	}
	
	public void setMaxDecimalAccuracy(String d)
	{
		maxDecimalAccuracy = d;
	}
	
	public String getSelectedTemperature()
	{
		return selectedTemperature;
	}
	
	public void setSelectedTemperature(String s)
	{
		selectedTemperature = s;
	}
	
	public String getSelectedPressure()
	{
		return selectedPressure;
	}
	
	public void setSelectedPressure(String s)
	{
		selectedPressure = s;
	}
	
	public String getSelectedVolume()
	{
		return selectedVolume;
	}
	
	public void setSelectedVolume(String s)
	{
		selectedVolume = s;
	}
	
	public boolean getGasSameUnits()
	{
		return Gas_SameUnits;
	}
	
	public void setGasSameUnits(boolean SU)
	{
		Gas_SameUnits = SU;
	}
	
	public boolean getGasAutoSolve()
	{
		return Gas_AutoSolve;
	}
	
	public void setGasAutoSolve(boolean AS)
	{
		Gas_AutoSolve = AS;
	}
	
	public boolean getGasFormatHelp()
	{
		return Gas_FormatHelp;
	}
	
	public void setGasFormatHelp(boolean FH)
	{
		Gas_FormatHelp = FH;
	}
	
	public String getConvertDefaultCategory()
	{
		return Convert_DefaultCategory;
	}
	
	public void setConvertDefaultCategory(String DC)
	{
		Convert_DefaultCategory = DC;
	}
	
	public String getConvertMaxDecimalAccuracy()
	{
		return Convert_MaxDecimalAccuracy;
	}
	
	public void setConvertMaxDecimalAccuracy(String d)
	{
		Convert_MaxDecimalAccuracy = d;
	}
	
	public boolean getConvertAutoSolve()
	{
		return Convert_AutoSolve;
	}
	
	public void setConvertAutoSolve(boolean AS)
	{
		Convert_AutoSolve = AS;
	}
	
	public boolean getConvertFormatHelp()
	{
		return Convert_FormatHelp;
	}
	
	public void setConvertFormatHelp(boolean FH)
	{
		Convert_FormatHelp = FH;
	}
	
	public boolean getPeriodicShowProgress()
	{
		return Periodic_ShowProgress;
	}
	
	public void setPeriodicShowProgress(boolean SP)
	{
		Periodic_ShowProgress = SP;
	}
	
	public boolean getPeriodicClickableElements()
	{
		return Periodic_ClickableElements;
	}
	
	public void setPeriodicClickableElements(boolean CE)
	{
		Periodic_ClickableElements = CE;
	}
	
	public String getPeriodicSort()
	{
		return Periodic_Sort;
	}
	
	public void setPeriodicSort(String S)
	{
		Periodic_Sort = S;
	}
	
	public boolean getPeriodicShowSelector()
	{
		return Periodic_ShowSelector;
	}
	
	public void setPeriodicShowSelector(boolean SS)
	{
		Periodic_ShowSelector = SS;
	}
	
	public boolean getPeriodicShowSearch()
	{
		return Periodic_ShowSearch;
	}
	
	public void setPeriodicShowSearch(boolean SS)
	{
		Periodic_ShowSearch = SS;
	}
	
	public boolean getQuizNotifyCorrect()
	{
		return Quiz_NotifyCorrect;
	}
	
	public void setQuizNotifyCorrect(boolean NC)
	{
		Quiz_NotifyCorrect = NC;
	}
	
	public boolean getQuizShowTime()
	{
		return Quiz_ShowTime;
	}
	
	public void setQuizShowTime(boolean ST)
	{
		Quiz_ShowTime = ST;
	}
	
	public boolean getQuizShowPercent()
	{
		return Quiz_ShowPercent;
	}
	
	public void setQuizShowPercent(boolean SP)
	{
		Quiz_ShowPercent = SP;
	}
	
	public boolean getQuizShowProgress()
	{
		return Quiz_ShowProgress;
	}
	
	public void setQuizShowProgress(boolean SP)
	{
		Quiz_ShowProgress = SP;
	}
	
	public int getTYPE_AMOUNT_PeriodicTable()
	{
		return TYPE_AMOUNT_PeriodicTable;
	}
	
	public void setTYPE_AMOUNT_PeriodicTable(int tapt)
	{
		TYPE_AMOUNT_PeriodicTable = tapt;
	}
	
	public int getTYPE_AMOUNT_PeriodicElements()
	{
		return TYPE_AMOUNT_PeriodicElements;
	}
	
	public void setTYPE_AMOUNT_PeriodicElements(int tape)
	{
		TYPE_AMOUNT_PeriodicElements = tape;
	}
	
	public String getUpdateDetails()
	{
		return updateDetails;
	}
	
	public void setUpdateDetails(String d)
	{
		updateDetails = d;
	}
	
	/* Test Strings */
	
	public String getTestString()
	{
		return testString;
	}
	
	public void setTestString(String t)
	{
		testString = t;
	}
	
	public int getTestInteger()
	{
		return testInteger;
	}
	
	public void setTestInteger(int t)
	{
		testInteger = t;
	}
	
	public double getTestDouble()
	{
		return testDouble;
	}
	
	public void setTestDouble(double t)
	{
		testDouble = t;
	}
	
	public String getDebugDialogPosition()
	{
		return Debug_DialogPosition;
	}
	
	public void setDebugDialogPosition(String p)
	{
		Debug_DialogPosition = p;
	}
	
	public int getErrorCounter()
	{
		return ERROR_COUNTER;
	}
	
	public void setErrorCounter(int EC)
	{
		ERROR_COUNTER = EC;
	}
	
	public boolean getDebugMode()
	{
		return DEBUG_MODE;
	}
	
	public void setDebugMode(boolean DM)
	{
		DEBUG_MODE = DM;
	}
	
	public boolean getFS()
	{
		return FORCE_STOP;
	}
	
	public void setFS(boolean FS)
	{
		FORCE_STOP = FS;
	}
}
