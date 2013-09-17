package com.pk.chemhelp;

public class Chemical
{
	boolean Element;
	String Name;
	String Formula;
	int AtomicNumber;
	double AtomicMass;
	int NumProtons;
	int NumNeutrons;
	int NumElectrons;
	String Classification;
	Double MeltingPoint;
	Double BoilingPoint;
	double MassFraction;
	double MoleFraction;
	String Moles;
	
	public Chemical()
	{
		
	}
	
	public Chemical(String N, String F)
	{
		Name = N;
		Formula = F;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E, double MP)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
		MeltingPoint = MP;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E, double MP, double BP)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
		MeltingPoint = MP;
		BoilingPoint = BP;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E, String C)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
		Classification = C;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E, String C, double MP)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
		Classification = C;
		MeltingPoint = MP;
	}
	
	public Chemical(int AN, double AM, String N, String F, int NP, int NN, boolean E, String C, double MP, double BP)
	{
		AtomicNumber = AN;
		AtomicMass = AM;
		Name = N;
		Formula = F;
		NumProtons = NP;
		NumElectrons = NP;
		NumNeutrons = NN;
		Element = E;
		Classification = C;
		MeltingPoint = MP;
		BoilingPoint = BP;
	}
	
	public void setAtomicMass(double AM)
	{
		AtomicMass = AM;
	}
	
	public double getAtomicMass()
	{
		return AtomicMass;
	}
	
	public void setAtomicNumber(int AN)
	{
		AtomicNumber = AN;
	}
	
	public int getAtomicNumber()
	{
		return AtomicNumber;
	}
	
	public void setName(String N)
	{
		Name = N;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public void setFormula(String F)
	{
		Formula = F;
	}
	
	public String getFormula()
	{
		return Formula;
	}
	
	public void setNumProtons(int NP)
	{
		NumProtons = NP;
	}
	
	public int getNumProtons()
	{
		return NumProtons;
	}
	
	public void setNumElectrons(int NE)
	{
		NumElectrons = NE;
	}
	
	public int getNumElectrons()
	{
		return NumElectrons;
	}
	
	public void setNumNeutrons(int NN)
	{
		NumNeutrons = NN;
	}
	
	public int getNumNeutrons()
	{
		return NumNeutrons;
	}
	
	public void setClassification(String C)
	{
		Classification = C;
	}
	
	public String getClassification()
	{
		return Classification;
	}
	
	public void setMeltingPoint(double MP)
	{
		MeltingPoint = MP;
	}
	
	public double getMeltingPoint()
	{
		return MeltingPoint;
	}
	
	public void setBoilingPoint(double BP)
	{
		BoilingPoint = BP;
	}
	
	public double getBoilingPoint()
	{
		return BoilingPoint;
	}
	
	public void setIsElement(boolean IE)
	{
		Element = IE;
	}
	
	public boolean isElement()
	{
		return Element;
	}
	
	public boolean nullMeltingPoint()
	{
		if(MeltingPoint == null)
			return true;
		else
			return false;
	}
	
	public boolean nullBoilingPoint()
	{
		if(BoilingPoint == null)
			return true;
		else
			return false;
	}
	
	public void setMassFraction(double MaF)
	{
		MassFraction = MaF;
	}
	
	public double getMassFraction()
	{
		return MassFraction;
	}
	
	public void setMoleFraction(double MoF)
	{
		MoleFraction = MoF;
	}
	
	public double getMoleFraction()
	{
		return MoleFraction;
	}
	
	public void setMoles(String M)
	{
		Moles = M;
	}
	
	public String getMoles()
	{
		return Moles;
	}
	
	
	
	
	public static Chemical[] getElements(String sort)
	{
		Chemical[] Chem = new Chemical[118];
		
		if(sort.equals("Atomic Number"))
		{
			//AtomicNumber, AtomicMass, Name, Formula, Protons, Neutrons, isElement, (O)Classification, (O)Melting Point, (O)Boiling Point
			Chem[0] = new Chemical(1, 1.00794, "Hydrogen", "H", 1, 0, true, "Non-metal", -259.14, -252.87);
			Chem[1] = new Chemical(2, 4.002602, "Helium", "He", 2, 2, true, "Noble Gas", -272.0, -268.6);
			Chem[2] = new Chemical(3, 6.941, "Lithium", "Li", 3, 4, true, "Alkali Metal", 180.54, 1347.0);
			Chem[3] = new Chemical(4, 9.012182, "Beryllium", "Be", 4, 5, true, "Alkaline Earth", 1278.0, 2970.0);
			Chem[4] = new Chemical(5, 10.811, "Boron", "B", 5, 6, true, "Metalloid", 2300.0, 2550.0);
			Chem[5] = new Chemical(6, 12.0107, "Carbon", "C", 6, 6, true, "Non-metal", 3500.0, 4827.0);
			Chem[6] = new Chemical(7, 14.0067, "Nitrogen", "N", 7, 7, true, "Non-metal", -209.9, -195.8);
			Chem[7] = new Chemical(8, 15.9994, "Oxygen", "O", 8, 8, true, "Non-metal", -218.4, -183.0);
			Chem[8] = new Chemical(9, 18.9984032, "Fluorine", "F", 9, 10, true, "Halogen", -219.62, -188.14);
			Chem[9] = new Chemical(10, 20.1797, "Neon", "Ne", 10, 10, true, "Noble Gas", -248.6, 246.1);
			Chem[10] = new Chemical(11, 22.98676928, "Sodium", "Na", 11, 12, true, "Alkali Metal", 97.72, 883);
			Chem[11] = new Chemical(12, 24.305, "Magnesium", "Mg", 12, 12, true, "Alkaline Earth", 650.0, 1107.0);
			Chem[12] = new Chemical(13, 26.9815386, "Aluminum", "Al", 13, 14, true, "Other Metals", 660.37, 2467.0);
			Chem[13] = new Chemical(14, 28.0855, "Silicon", "Si", 14, 14, true, "Metalloid", 1410.0, 2355.0);
			Chem[14] = new Chemical(15, 30.973762, "Phosphorus", "P", 15, 16, true, "Non-metal", 44.1, 280.0);
			Chem[15] = new Chemical(16, 32.065, "Sulfur", "S", 16, 16, true, "Non-metal", 112.8, 444.6);
			Chem[16] = new Chemical(17, 35.453, "Chlorine", "Cl", 17, 18, true, "Halogen", -100.98, -34.6);
			Chem[17] = new Chemical(18, 39.948, "Argon", "Ar", 18, 22, true, "Noble Gas", -189.3, -186.0);
			Chem[18] = new Chemical(19, 39.0983, "Potassium", "K", 19, 20, true, "Alkali Metal", 63.65, 774.0);
			Chem[19] = new Chemical(20, 40.078, "Calcium", "Ca", 20, 20, true, "Alkaline Earth", 839.0, 1484.0);
			Chem[20] = new Chemical(21, 44.955912, "Scandium", "Sc", 21, 24, true, "Transition Metal", 1539.0, 2832.0);
			Chem[21] = new Chemical(22, 47.867, "Titanium", "Ti", 22, 26, true, "Transition Metal", 1660.0, 3287.0);
			Chem[22] = new Chemical(23, 50.9415, "Vanadium", "V", 23, 28, true, "Transition Metal", 1890.0, 3380.0);
			Chem[23] = new Chemical(24, 51.9961, "Chromium", "Cr", 24, 28, true, "Transition Metal", 1857.0, 2672.0);
			Chem[24] = new Chemical(25, 54.938045, "Manganese", "Mn", 25, 30, true, "Transition Metal", 1245.0, 1962.0);
			Chem[25] = new Chemical(26, 55.845, "Iron", "Fe", 26, 30, true, "Transition Metal", 1535.0, 2750.0);
			Chem[26] = new Chemical(27, 58.933195, "Cobalt", "Co", 27, 32, true, "Transition Metal", 1495.0, 2870.0);
			Chem[27] = new Chemical(28, 58.6934, "Nickel", "Ni", 28, 31, true, "Transition Metal", 1453.0, 2732.0);
			Chem[28] = new Chemical(29, 63.546, "Copper", "Cu", 29, 35, true, "Transition Metal", 1083.0, 2567.0);
			Chem[29] = new Chemical(30, 65.38, "Zinc", "Zn", 30, 35, true, "Transition Metal", 419.58, 907.0);
			Chem[30] = new Chemical(31, 69.723, "Gallium", "Ga", 31, 39, true, "Other Metals", 29.78, 2403.0);
			Chem[31] = new Chemical(32, 72.63, "Germanium", "Ge", 32, 41, true, "Metalloid", 937.4, 2830.0);
			Chem[32] = new Chemical(33, 74.9216, "Arsenic", "As", 33, 42, true, "Metalloid", 817.0, 613.0);
			Chem[33] = new Chemical(34, 78.96, "Selenium", "Se", 34, 45, true, "Non-metal", 217.0, 684.9);
			Chem[34] = new Chemical(35, 79.904, "Bromine", "Br", 35, 45, true, "Halogen", -7.2, 58.78);
			Chem[35] = new Chemical(36, 83.798, "Krypton", "Kr", 36, 48, true, "Noble Gas", -157.2, -153.4);
			Chem[36] = new Chemical(37, 85.4678, "Rubidium", "Rb", 37, 48, true, "Alkali Metal", 38.89, 688.0);
			Chem[37] = new Chemical(38, 87.62, "Strontium", "Sr", 38, 50, true, "Alkaline Earth", 769.0, 1384.0);
			Chem[38] = new Chemical(39, 88.90585, "Yttrium", "Y", 39, 50, true, "Transition Metal", 1523.0, 3337.0);
			Chem[39] = new Chemical(40, 91.224, "Zirconium", "Zr", 40, 51, true, "Transition Metal", 1852.0, 4377.0);
			Chem[40] = new Chemical(41, 92.90638, "Niobium", "Nb", 41, 52, true, "Transition Metal", 2468.0, 4927.0);
			Chem[41] = new Chemical(42, 95.96, "Molybdenum", "Mo", 42, 54, true, "Transition Metal", 2617.0, 4612.0);
			Chem[42] = new Chemical(43, 98.0, "Technetium", "Tc", 43, 55, true, "Transition Metal", 2200.0, 4877.0);
			Chem[43] = new Chemical(44, 101.07, "Ruthenium", "Ru", 44, 57, true, "Transition Metal", 2250.0, 3900.0);
			Chem[44] = new Chemical(45, 102.9055, "Rhodium", "Rh", 45, 58, true, "Transition Metal", 1966.0, 3727.0);
			Chem[45] = new Chemical(46, 106.42, "Palladium", "Pd", 46, 60, true, "Transition Metal", 1552.0, 2927.0);
			Chem[46] = new Chemical(47, 107.8682, "Silver", "Ag", 47, 61, true, "Transition Metal", 961.93, 2212.0);
			Chem[47] = new Chemical(48, 112.411, "Cadmium", "Cd", 48, 64, true, "Transition Metal", 320.9, 765.0);
			Chem[48] = new Chemical(49, 114.818, "Indium", "In", 49, 66, true, "Other Metals", 156.61, 2000.0);
			Chem[49] = new Chemical(50, 118.71, "Tin", "Sn", 50, 69, true, "Other Metals", 231.9, 2270.0);
			Chem[50] = new Chemical(51, 121.76, "Antimony", "Sb", 51, 71, true, "Metalloid", 630.0, 1750.0);
			Chem[51] = new Chemical(52, 127.6, "Tellurium", "Te", 52, 76, true, "Metalloid", 449.5, 989.8);
			Chem[52] = new Chemical(53, 126.90447, "Iodine", "I", 53, 74, true, "Halogen", 113.5, 184.0);
			Chem[53] = new Chemical(54, 131.293, "Xenon", "Xe", 54, 77, true, "Noble Gas", -111.9, -108.1);
			Chem[54] = new Chemical(55, 132.9054519, "Cesium", "Cs", 55, 78, true, "Alkali Metal", 28.5, 678.4);
			Chem[55] = new Chemical(56, 137.327, "Barium", "Ba", 56, 81, true, "Alkaline Earth", 725.0, 1140.0);
			Chem[56] = new Chemical(57, 138.90547, "Lanthanum", "La", 57, 82, true, "Rare Earth", 920.0, 3469.0);
			Chem[57] = new Chemical(58, 140.116, "Cerium", "Ce", 58, 82, true, "Rare Earth", 795.0, 3257.0);
			Chem[58] = new Chemical(59, 140.91, "Praseodymium", "Pr", 59, 82, true, "Rare Earth", 935.0, 3127.0);
			Chem[59] = new Chemical(60, 144.242, "Neodymium", "Nd", 60, 84, true, "Rare Earth", 1010.0, 3127.0);
			Chem[60] = new Chemical(61, 145.0, "Promethium", "Pm", 61, 84, true, "Rare Earth");
			Chem[61] = new Chemical(62, 150.36, "Samarium", "Sm", 62, 88, true, "Rare Earth", 1072.0, 1900.0);
			Chem[62] = new Chemical(63, 151.964, "Europium", "Eu", 63, 89, true, "Rare Earth", 822.0, 1597.0);
			Chem[63] = new Chemical(64, 157.25, "Gadolinium", "Gd", 64, 93, true, "RareEarth", 1311.0, 3233.0);
			Chem[64] = new Chemical(65, 158.92535, "Terbium", "Tb", 65, 94, true, "Rare Earth", 1360.0, 3041.0);
			Chem[65] = new Chemical(66, 162.5, "Dysprosium", "Dy", 66, 97, true, "Rare Earth");
			Chem[66] = new Chemical(67, 164.93032, "Holmium", "Ho", 67, 98, true, "Rare Earth", 1470.0, 2720.0);
			Chem[67] = new Chemical(68, 167.259, "Erbium", "Er", 68, 99, true, "Rare Earth", 1522.0, 2510.0);
			Chem[68] = new Chemical(69, 168.93421, "Thulium", "Tm", 69, 100, true, "Rare Earth", 1545.0, 1727.0);
			Chem[69] = new Chemical(70, 173.054, "Ytterbium", "Yb", 70, 103, true, "Rare Earth", 824.0, 1466.0);
			Chem[70] = new Chemical(71, 174.9668, "Lutetium", "Lu", 71, 104, true, "Rare Earth", 1656.0, 3315.0);
			Chem[71] = new Chemical(72, 178.49, "Hafnium", "Hf", 72, 106, true, "Transition Metal", 2150.0, 5400.0);
			Chem[72] = new Chemical(73, 180.94788, "Tantalum", "Ta", 73, 108, true, "Transition Metal", 2996.0, 5425.0);
			Chem[73] = new Chemical(74, 183.84, "Tungsten", "W", 74, 110, true, "Transition Metal", 3410.0, 5660.0);
			Chem[74] = new Chemical(75, 186.207, "Rhenium", "Re", 75, 111, true, "Transition Metal", 3180.0, 5627.0);
			Chem[75] = new Chemical(76, 190.23, "Osmium", "Os", 76, 114, true, "Transition Metal", 3045.0, 5027.0);
			Chem[76] = new Chemical(77, 192.217, "Iridium", "Ir", 77, 115, true, "Transition Metal", 2410.0, 4527.0);
			Chem[77] = new Chemical(78, 195.084, "Platinum", "Pt", 78, 117, true, "Transition Metal", 1772.0, 3827.0);
			Chem[78] = new Chemical(79, 196.966569, "Gold", "Au", 79, 118, true, "Transition Metal", 1064.43, 2807.0);
			Chem[79] = new Chemical(80, 200.59, "Mercury", "Hg", 80, 121, true, "Transition Metal", -38.87, 356.58);
			Chem[80] = new Chemical(81, 204.3833, "Thallium", "Tl", 81, 123, true, "Other Metals", 303.5, 1457.0);
			Chem[81] = new Chemical(82, 207.2, "Lead", "Pb", 82, 125, true, "Other Metals", 207.2, 327.5);
			Chem[82] = new Chemical(83, 208.9804, "Bismuth", "Bi", 83, 126, true, "Other Metals", 271.3, 1560.0);
			Chem[83] = new Chemical(84, 209.0, "Polonium", "Po", 84, 125, true, "Metalloid", 254.0, 962.0);
			Chem[84] = new Chemical(85, 210.0, "Astatine", "At", 85, 125, true, "Halogen", 302.0, 337.0);
			Chem[85] = new Chemical(86, 222.0, "Radon", "Rn", 86, 136, true, "Noble Gas", -71.0, -61.8);
			Chem[86] = new Chemical(87, 223.0, "Francium", "Fr", 87, 136, true, "Alkali Metal", 27.0, 677.0);
			Chem[87] = new Chemical(88, 226.0, "Radium", "Ra", 88, 138, true, "Alkaline Earth", 700.0, 1737.0);
			Chem[88] = new Chemical(89, 227.0, "Actinium", "Ac", 89, 138, true, "Rare Earth", 1050.0, 3200.0);
			Chem[89] = new Chemical(90, 232.03806, "Thorium", "Th", 90, 142, true, "Rare Earth", 1750.0, 4790.0);
			Chem[90] = new Chemical(91, 231.03588, "Protactinium", "Pa", 91, 140, true, "Rare Earth", 231.04, 1600.0);
			Chem[91] = new Chemical(92, 238.02891, "Uranium", "U", 92, 146, true, "Rare Earth", 1132.0, 3818.0);
			Chem[92] = new Chemical(93, 237.0, "Neptunium", "Np", 93, 144, true, "Rare Earth", 640.0, 3902.0);
			Chem[93] = new Chemical(94, 244.0, "Plutonium", "Pu", 94, 150, true, "Rare Earth", 639.5, 3235.0);
			Chem[94] = new Chemical(95, 243.0, "Americium", "Am", 95, 148, true, "Rare Earth", 994.0, 2607.0);
			Chem[95] = new Chemical(96, 247.0, "Curium", "Cm", 96, 151, true, "Rare Earth", 1340.0);
			Chem[96] = new Chemical(97, 247.0, "Berkelium", "Bk", 97, 150, true, "Rare Earth");
			Chem[97] = new Chemical(98, 251.0, "Californium", "Cf", 98, 153, true, "Rare Earth");
			Chem[98] = new Chemical(99, 252.0, "Einsteinium", "Es", 99, 153, true, "Rare Earth");
			Chem[99] = new Chemical(100, 257.0, "Fermium", "Fm", 100, 157, true, "Rare Earth");
			Chem[100] = new Chemical(101, 258.0, "Mendelevium", "Md", 101, 157, true, "Rare Earth");
			Chem[101] = new Chemical(102, 259.0, "Nobelium", "No", 102, 157, true, "Rare Earth");
			Chem[102] = new Chemical(103, 262.0, "Lawrencium", "Lr", 103, 159, true, "Rare Earth");
			Chem[103] = new Chemical(104, 267.0, "Rutherfordium", "Rf", 104, 157, true, "Transition Metal");
			Chem[104] = new Chemical(105, 262.0, "Dubnium", "Db", 105, 157, true, "Transition Metal");
			Chem[105] = new Chemical(106, 271.0, "Seaborgium", "Sg", 106, 157, true, "Transition Metal");
			Chem[106] = new Chemical(107, 262.0, "Bohrium", "Bh", 107, 155, true, "Transition Metal");
			Chem[107] = new Chemical(108, 270.0, "Hassium", "Hs", 108, 157, true, "Transition Metal");
			Chem[108] = new Chemical(109, 266.0, "Meitnerium", "Mt", 109, 157, true, "Transition Metal");
			Chem[109] = new Chemical(110, 281.0, "Darmstadtium", "Ds", 110, 159, true, "Transition Metal");
			Chem[110] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
			Chem[111] = new Chemical(112, 285.0, "Copernicium", "Cp", 112, 277, true, "Transition Metal");
			Chem[112] = new Chemical(113, 284.0, "Ununtrium", "Uut", 113, 170, true, "Other Metal");
			Chem[113] = new Chemical(114, 289.0, "Flerovium", "Fl", 114, 175, true, "Other Metal");
			Chem[114] = new Chemical(115, 288.0, "Ununpentium", "Uup", 115, 176, true, "Other Metal");
			Chem[115] = new Chemical(116, 293.0, "Livermorium", "Lv", 116, 173, true, "Other Metal");
			Chem[116] = new Chemical(117, 294.0, "Ununseptium", "Uus", 117, 177, true, "Unknown");
			Chem[117] = new Chemical(118, 294.0, "Ununoctium", "Uuo", 118, 175, true, "Non-metal");
		}
		else if(sort.equals("Atomic Mass"))
		{
			//AtomicNumber, AtomicMass, Name, Formula, Protons, Neutrons, isElement, (O)Classification, (O)Melting Point, (O)Boiling Point
			Chem[0] = new Chemical(1, 1.00794, "Hydrogen", "H", 1, 0, true, "Non-metal", -259.14, -252.87);
			Chem[1] = new Chemical(2, 4.002602, "Helium", "He", 2, 2, true, "Noble Gas", -272.0, -268.6);
			Chem[2] = new Chemical(3, 6.941, "Lithium", "Li", 3, 4, true, "Alkali Metal", 180.54, 1347.0);
			Chem[3] = new Chemical(4, 9.012182, "Beryllium", "Be", 4, 5, true, "Alkaline Earth", 1278.0, 2970.0);
			Chem[4] = new Chemical(5, 10.811, "Boron", "B", 5, 6, true, "Metalloid", 2300.0, 2550.0);
			Chem[5] = new Chemical(6, 12.0107, "Carbon", "C", 6, 6, true, "Non-metal", 3500.0, 4827.0);
			Chem[6] = new Chemical(7, 14.0067, "Nitrogen", "N", 7, 7, true, "Non-metal", -209.9, -195.8);
			Chem[7] = new Chemical(8, 15.9994, "Oxygen", "O", 8, 8, true, "Non-metal", -218.4, -183.0);
			Chem[8] = new Chemical(9, 18.9984032, "Fluorine", "F", 9, 10, true, "Halogen", -219.62, -188.14);
			Chem[9] = new Chemical(10, 20.1797, "Neon", "Ne", 10, 10, true, "Noble Gas", -248.6, 246.1);
			Chem[10] = new Chemical(11, 22.98676928, "Sodium", "Na", 11, 12, true, "Alkali Metal", 97.72, 883);
			Chem[11] = new Chemical(12, 24.305, "Magnesium", "Mg", 12, 12, true, "Alkaline Earth", 650.0, 1107.0);
			Chem[12] = new Chemical(13, 26.9815386, "Aluminum", "Al", 13, 14, true, "Other Metals", 660.37, 2467.0);
			Chem[13] = new Chemical(14, 28.0855, "Silicon", "Si", 14, 14, true, "Metalloid", 1410.0, 2355.0);
			Chem[14] = new Chemical(15, 30.973762, "Phosphorus", "P", 15, 16, true, "Non-metal", 44.1, 280.0);
			Chem[15] = new Chemical(16, 32.065, "Sulfur", "S", 16, 16, true, "Non-metal", 112.8, 444.6);
			Chem[16] = new Chemical(17, 35.453, "Chlorine", "Cl", 17, 18, true, "Halogen", -100.98, -34.6);
			Chem[17] = new Chemical(19, 39.0983, "Potassium", "K", 19, 20, true, "Alkali Metal", 63.65, 774.0);
			Chem[18] = new Chemical(18, 39.948, "Argon", "Ar", 18, 22, true, "Noble Gas", -189.3, -186.0);
			Chem[19] = new Chemical(20, 40.078, "Calcium", "Ca", 20, 20, true, "Alkaline Earth", 839.0, 1484.0);
			Chem[20] = new Chemical(21, 44.955912, "Scandium", "Sc", 21, 24, true, "Transition Metal", 1539.0, 2832.0);
			Chem[21] = new Chemical(22, 47.867, "Titanium", "Ti", 22, 26, true, "Transition Metal", 1660.0, 3287.0);
			Chem[22] = new Chemical(23, 50.9415, "Vanadium", "V", 23, 28, true, "Transition Metal", 1890.0, 3380.0);
			Chem[23] = new Chemical(24, 51.9961, "Chromium", "Cr", 24, 28, true, "Transition Metal", 1857.0, 2672.0);
			Chem[24] = new Chemical(25, 54.938045, "Manganese", "Mn", 25, 30, true, "Transition Metal", 1245.0, 1962.0);
			Chem[25] = new Chemical(26, 55.845, "Iron", "Fe", 26, 30, true, "Transition Metal", 1535.0, 2750.0);
			Chem[26] = new Chemical(28, 58.6934, "Nickel", "Ni", 28, 31, true, "Transition Metal", 1453.0, 2732.0);
			Chem[27] = new Chemical(27, 58.933195, "Cobalt", "Co", 27, 32, true, "Transition Metal", 1495.0, 2870.0);
			Chem[28] = new Chemical(29, 63.546, "Copper", "Cu", 29, 35, true, "Transition Metal", 1083.0, 2567.0);
			Chem[29] = new Chemical(30, 65.38, "Zinc", "Zn", 30, 35, true, "Transition Metal", 419.58, 907.0);
			Chem[30] = new Chemical(31, 69.723, "Gallium", "Ga", 31, 39, true, "Other Metals", 29.78, 2403.0);
			Chem[31] = new Chemical(32, 72.63, "Germanium", "Ge", 32, 41, true, "Metalloid", 937.4, 2830.0);
			Chem[32] = new Chemical(33, 74.9216, "Arsenic", "As", 33, 42, true, "Metalloid", 817.0, 613.0);
			Chem[33] = new Chemical(34, 78.96, "Selenium", "Se", 34, 45, true, "Non-metal", 217.0, 684.9);
			Chem[34] = new Chemical(35, 79.904, "Bromine", "Br", 35, 45, true, "Halogen", -7.2, 58.78);
			Chem[35] = new Chemical(36, 83.798, "Krypton", "Kr", 36, 48, true, "Noble Gas", -157.2, -153.4);
			Chem[36] = new Chemical(37, 85.4678, "Rubidium", "Rb", 37, 48, true, "Alkali Metal", 38.89, 688.0);
			Chem[37] = new Chemical(38, 87.62, "Strontium", "Sr", 38, 50, true, "Alkaline Earth", 769.0, 1384.0);
			Chem[38] = new Chemical(39, 88.90585, "Yttrium", "Y", 39, 50, true, "Transition Metal", 1523.0, 3337.0);
			Chem[39] = new Chemical(40, 91.224, "Zirconium", "Zr", 40, 51, true, "Transition Metal", 1852.0, 4377.0);
			Chem[40] = new Chemical(41, 92.90638, "Niobium", "Nb", 41, 52, true, "Transition Metal", 2468.0, 4927.0);
			Chem[41] = new Chemical(42, 95.96, "Molybdenum", "Mo", 42, 54, true, "Transition Metal", 2617.0, 4612.0);
			Chem[42] = new Chemical(43, 98.0, "Technetium", "Tc", 43, 55, true, "Transition Metal", 2200.0, 4877.0);
			Chem[43] = new Chemical(44, 101.07, "Ruthenium", "Ru", 44, 57, true, "Transition Metal", 2250.0, 3900.0);
			Chem[44] = new Chemical(45, 102.9055, "Rhodium", "Rh", 45, 58, true, "Transition Metal", 1966.0, 3727.0);
			Chem[45] = new Chemical(46, 106.42, "Palladium", "Pd", 46, 60, true, "Transition Metal", 1552.0, 2927.0);
			Chem[46] = new Chemical(47, 107.8682, "Silver", "Ag", 47, 61, true, "Transition Metal", 961.93, 2212.0);
			Chem[47] = new Chemical(48, 112.411, "Cadmium", "Cd", 48, 64, true, "Transition Metal", 320.9, 765.0);
			Chem[48] = new Chemical(49, 114.818, "Indium", "In", 49, 66, true, "Other Metals", 156.61, 2000.0);
			Chem[49] = new Chemical(50, 118.71, "Tin", "Sn", 50, 69, true, "Other Metals", 231.9, 2270.0);
			Chem[50] = new Chemical(51, 121.76, "Antimony", "Sb", 51, 71, true, "Metalloid", 630.0, 1750.0);
			Chem[51] = new Chemical(52, 127.6, "Tellurium", "Te", 52, 76, true, "Metalloid", 449.5, 989.8);
			Chem[52] = new Chemical(53, 126.90447, "Iodine", "I", 53, 74, true, "Halogen", 113.5, 184.0);
			Chem[53] = new Chemical(54, 131.293, "Xenon", "Xe", 54, 77, true, "Noble Gas", -111.9, -108.1);
			Chem[54] = new Chemical(55, 132.9054519, "Cesium", "Cs", 55, 78, true, "Alkali Metal", 28.5, 678.4);
			Chem[55] = new Chemical(56, 137.327, "Barium", "Ba", 56, 81, true, "Alkaline Earth", 725.0, 1140.0);
			Chem[56] = new Chemical(57, 138.90547, "Lanthanum", "La", 57, 82, true, "Rare Earth", 920.0, 3469.0);
			Chem[57] = new Chemical(58, 140.116, "Cerium", "Ce", 58, 82, true, "Rare Earth", 795.0, 3257.0);
			Chem[58] = new Chemical(59, 140.91, "Praseodymium", "Pr", 59, 82, true, "Rare Earth", 935.0, 3127.0);
			Chem[59] = new Chemical(60, 144.242, "Neodymium", "Nd", 60, 84, true, "Rare Earth", 1010.0, 3127.0);
			Chem[60] = new Chemical(61, 145.0, "Promethium", "Pm", 61, 84, true, "Rare Earth");
			Chem[61] = new Chemical(62, 150.36, "Samarium", "Sm", 62, 88, true, "Rare Earth", 1072.0, 1900.0);
			Chem[62] = new Chemical(63, 151.964, "Europium", "Eu", 63, 89, true, "Rare Earth", 822.0, 1597.0);
			Chem[63] = new Chemical(64, 157.25, "Gadolinium", "Gd", 64, 93, true, "RareEarth", 1311.0, 3233.0);
			Chem[64] = new Chemical(65, 158.92535, "Terbium", "Tb", 65, 94, true, "Rare Earth", 1360.0, 3041.0);
			Chem[65] = new Chemical(66, 162.5, "Dysprosium", "Dy", 66, 97, true, "Rare Earth");
			Chem[66] = new Chemical(67, 164.93032, "Holmium", "Ho", 67, 98, true, "Rare Earth", 1470.0, 2720.0);
			Chem[67] = new Chemical(68, 167.259, "Erbium", "Er", 68, 99, true, "Rare Earth", 1522.0, 2510.0);
			Chem[68] = new Chemical(69, 168.93421, "Thulium", "Tm", 69, 100, true, "Rare Earth", 1545.0, 1727.0);
			Chem[69] = new Chemical(70, 173.054, "Ytterbium", "Yb", 70, 103, true, "Rare Earth", 824.0, 1466.0);
			Chem[70] = new Chemical(71, 174.9668, "Lutetium", "Lu", 71, 104, true, "Rare Earth", 1656.0, 3315.0);
			Chem[71] = new Chemical(72, 178.49, "Hafnium", "Hf", 72, 106, true, "Transition Metal", 2150.0, 5400.0);
			Chem[72] = new Chemical(73, 180.94788, "Tantalum", "Ta", 73, 108, true, "Transition Metal", 2996.0, 5425.0);
			Chem[73] = new Chemical(74, 183.84, "Tungsten", "W", 74, 110, true, "Transition Metal", 3410.0, 5660.0);
			Chem[74] = new Chemical(75, 186.207, "Rhenium", "Re", 75, 111, true, "Transition Metal", 3180.0, 5627.0);
			Chem[75] = new Chemical(76, 190.23, "Osmium", "Os", 76, 114, true, "Transition Metal", 3045.0, 5027.0);
			Chem[76] = new Chemical(77, 192.217, "Iridium", "Ir", 77, 115, true, "Transition Metal", 2410.0, 4527.0);
			Chem[77] = new Chemical(78, 195.084, "Platinum", "Pt", 78, 117, true, "Transition Metal", 1772.0, 3827.0);
			Chem[78] = new Chemical(79, 196.966569, "Gold", "Au", 79, 118, true, "Transition Metal", 1064.43, 2807.0);
			Chem[79] = new Chemical(80, 200.59, "Mercury", "Hg", 80, 121, true, "Transition Metal", -38.87, 356.58);
			Chem[80] = new Chemical(81, 204.3833, "Thallium", "Tl", 81, 123, true, "Other Metals", 303.5, 1457.0);
			Chem[81] = new Chemical(82, 207.2, "Lead", "Pb", 82, 125, true, "Other Metals", 207.2, 327.5);
			Chem[82] = new Chemical(83, 208.9804, "Bismuth", "Bi", 83, 126, true, "Other Metals", 271.3, 1560.0);
			Chem[83] = new Chemical(84, 209.0, "Polonium", "Po", 84, 125, true, "Metalloid", 254.0, 962.0);
			Chem[84] = new Chemical(85, 210.0, "Astatine", "At", 85, 125, true, "Halogen", 302.0, 337.0);
			Chem[85] = new Chemical(86, 222.0, "Radon", "Rn", 86, 136, true, "Noble Gas", -71.0, -61.8);
			Chem[86] = new Chemical(87, 223.0, "Francium", "Fr", 87, 136, true, "Alkali Metal", 27.0, 677.0);
			Chem[87] = new Chemical(88, 226.0, "Radium", "Ra", 88, 138, true, "Alkaline Earth", 700.0, 1737.0);
			Chem[88] = new Chemical(89, 227.0, "Actinium", "Ac", 89, 138, true, "Rare Earth", 1050.0, 3200.0);
			Chem[89] = new Chemical(91, 231.03588, "Protactinium", "Pa", 91, 140, true, "Rare Earth", 231.04, 1600.0);
			Chem[90] = new Chemical(90, 232.03806, "Thorium", "Th", 90, 142, true, "Rare Earth", 1750.0, 4790.0);
			Chem[91] = new Chemical(93, 237.0, "Neptunium", "Np", 93, 144, true, "Rare Earth", 640.0, 3902.0);
			Chem[92] = new Chemical(92, 238.02891, "Uranium", "U", 92, 146, true, "Rare Earth", 1132.0, 3818.0);
			Chem[93] = new Chemical(95, 243.0, "Americium", "Am", 95, 148, true, "Rare Earth", 994.0, 2607.0);
			Chem[94] = new Chemical(94, 244.0, "Plutonium", "Pu", 94, 150, true, "Rare Earth", 639.5, 3235.0);
			Chem[95] = new Chemical(96, 247.0, "Curium", "Cm", 96, 151, true, "Rare Earth", 1340.0);
			Chem[96] = new Chemical(97, 247.0, "Berkelium", "Bk", 97, 150, true, "Rare Earth");
			Chem[97] = new Chemical(98, 251.0, "Californium", "Cf", 98, 153, true, "Rare Earth");
			Chem[98] = new Chemical(99, 252.0, "Einsteinium", "Es", 99, 153, true, "Rare Earth");
			Chem[99] = new Chemical(100, 257.0, "Fermium", "Fm", 100, 157, true, "Rare Earth");
			Chem[100] = new Chemical(101, 258.0, "Mendelevium", "Md", 101, 157, true, "Rare Earth");
			Chem[101] = new Chemical(102, 259.0, "Nobelium", "No", 102, 157, true, "Rare Earth");
			Chem[102] = new Chemical(103, 262.0, "Lawrencium", "Lr", 103, 159, true, "Rare Earth");
			Chem[103] = new Chemical(105, 262.0, "Dubnium", "Db", 105, 157, true, "Transition Metal");
			Chem[104] = new Chemical(107, 262.0, "Bohrium", "Bh", 107, 155, true, "Transition Metal");
			Chem[105] = new Chemical(109, 266.0, "Meitnerium", "Mt", 109, 157, true, "Transition Metal");
			Chem[106] = new Chemical(104, 267.0, "Rutherfordium", "Rf", 104, 157, true, "Transition Metal");
			Chem[107] = new Chemical(108, 270.0, "Hassium", "Hs", 108, 157, true, "Transition Metal");
			Chem[108] = new Chemical(106, 271.0, "Seaborgium", "Sg", 106, 157, true, "Transition Metal");
			Chem[109] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
			Chem[110] = new Chemical(110, 281.0, "Darmstadtium", "Ds", 110, 159, true, "Transition Metal");
			Chem[111] = new Chemical(113, 284.0, "Ununtrium", "Uut", 113, 170, true, "Other Metal");
			Chem[112] = new Chemical(112, 285.0, "Copernicium", "Cp", 112, 277, true, "Transition Metal");
			Chem[113] = new Chemical(115, 288.0, "Ununpentium", "Uup", 115, 176, true, "Other Metal");
			Chem[114] = new Chemical(114, 289.0, "Flerovium", "Fl", 114, 175, true, "Other Metal");
			Chem[115] = new Chemical(116, 293.0, "Livermorium", "Lv", 116, 173, true, "Other Metal");
			Chem[116] = new Chemical(117, 294.0, "Ununseptium", "Uus", 117, 177, true, "Unknown");
			Chem[117] = new Chemical(118, 294.0, "Ununoctium", "Uuo", 118, 175, true, "Non-metal");
		}
		else if(sort.equals("Name"))
		{
			//AtomicNumber, AtomicMass, Name, Formula, Protons, Neutrons, isElement, (O)Classification, (O)Melting Point, (O)Boiling Point
			Chem[0] = new Chemical(89, 227.0, "Actinium", "Ac", 89, 138, true, "Rare Earth", 1050.0, 3200.0);
			Chem[1] = new Chemical(13, 26.9815386, "Aluminum", "Al", 13, 14, true, "Other Metals", 660.37, 2467.0);
			Chem[2] = new Chemical(95, 243.0, "Americium", "Am", 95, 148, true, "Rare Earth", 994.0, 2607.0);
			Chem[3] = new Chemical(51, 121.76, "Antimony", "Sb", 51, 71, true, "Metalloid", 630.0, 1750.0);
			Chem[4] = new Chemical(18, 39.948, "Argon", "Ar", 18, 22, true, "Noble Gas", -189.3, -186.0);
			Chem[5] = new Chemical(33, 74.9216, "Arsenic", "As", 33, 42, true, "Metalloid", 817.0, 613.0);
			Chem[6] = new Chemical(85, 210.0, "Astatine", "At", 85, 125, true, "Halogen", 302.0, 337.0);
			Chem[7] = new Chemical(56, 137.327, "Barium", "Ba", 56, 81, true, "Alkaline Earth", 725.0, 1140.0);
			Chem[8] = new Chemical(4, 9.012182, "Beryllium", "Be", 4, 5, true, "Alkaline Earth", 1278.0, 2970.0);
			Chem[9] = new Chemical(97, 247.0, "Berkelium", "Bk", 97, 150, true, "Rare Earth");
			Chem[10] = new Chemical(83, 208.9804, "Bismuth", "Bi", 83, 126, true, "Other Metals", 271.3, 1560.0);
			Chem[11] = new Chemical(107, 262.0, "Bohrium", "Bh", 107, 155, true, "Transition Metal");
			Chem[12] = new Chemical(5, 10.811, "Boron", "B", 5, 6, true, "Metalloid", 2300.0, 2550.0);
			Chem[13] = new Chemical(35, 79.904, "Bromine", "Br", 35, 45, true, "Halogen", -7.2, 58.78);
			Chem[14] = new Chemical(48, 112.411, "Cadmium", "Cd", 48, 64, true, "Transition Metal", 320.9, 765.0);
			Chem[15] = new Chemical(20, 40.078, "Calcium", "Ca", 20, 20, true, "Alkaline Earth", 839.0, 1484.0);
			Chem[16] = new Chemical(98, 251.0, "Californium", "Cf", 98, 153, true, "Rare Earth");
			Chem[17] = new Chemical(6, 12.0107, "Carbon", "C", 6, 6, true, "Non-metal", 3500.0, 4827.0);
			Chem[18] = new Chemical(58, 140.116, "Cerium", "Ce", 58, 82, true, "Rare Earth", 795.0, 3257.0);
			Chem[19] = new Chemical(55, 132.9054519, "Cesium", "Cs", 55, 78, true, "Alkali Metal", 28.5, 678.4);
			Chem[20] = new Chemical(17, 35.453, "Chlorine", "Cl", 17, 18, true, "Halogen", -100.98, -34.6);
			Chem[21] = new Chemical(24, 51.9961, "Chromium", "Cr", 24, 28, true, "Transition Metal", 1857.0, 2672.0);
			Chem[22] = new Chemical(27, 58.933195, "Cobalt", "Co", 27, 32, true, "Transition Metal", 1495.0, 2870.0);
			Chem[23] = new Chemical(112, 285.0, "Copernicium", "Cp", 112, 277, true, "Transition Metal");
			Chem[24] = new Chemical(29, 63.546, "Copper", "Cu", 29, 35, true, "Transition Metal", 1083.0, 2567.0);
			Chem[25] = new Chemical(96, 247.0, "Curium", "Cm", 96, 151, true, "Rare Earth", 1340.0);
			Chem[26] = new Chemical(110, 281.0, "Darmstadtium", "Ds", 110, 159, true, "Transition Metal");
			Chem[27] = new Chemical(105, 262.0, "Dubnium", "Db", 105, 157, true, "Transition Metal");
			Chem[28] = new Chemical(66, 162.5, "Dysprosium", "Dy", 66, 97, true, "Rare Earth");
			Chem[29] = new Chemical(99, 252.0, "Einsteinium", "Es", 99, 153, true, "Rare Earth");
			Chem[30] = new Chemical(63, 151.964, "Europium", "Eu", 63, 89, true, "Rare Earth", 822.0, 1597.0);
			Chem[31] = new Chemical(68, 167.259, "Erbium", "Er", 68, 99, true, "Rare Earth", 1522.0, 2510.0);
			Chem[32] = new Chemical(100, 257.0, "Fermium", "Fm", 100, 157, true, "Rare Earth");
			Chem[33] = new Chemical(114, 289.0, "Flerovium", "Fl", 114, 175, true, "Other Metal");
			Chem[34] = new Chemical(9, 18.9984032, "Fluorine", "F", 9, 10, true, "Halogen", -219.62, -188.14);
			Chem[35] = new Chemical(87, 223.0, "Francium", "Fr", 87, 136, true, "Alkali Metal", 27.0, 677.0);
			Chem[36] = new Chemical(64, 157.25, "Gadolinium", "Gd", 64, 93, true, "RareEarth", 1311.0, 3233.0);
			Chem[37] = new Chemical(31, 69.723, "Gallium", "Ga", 31, 39, true, "Other Metals", 29.78, 2403.0);
			Chem[38] = new Chemical(32, 72.63, "Germanium", "Ge", 32, 41, true, "Metalloid", 937.4, 2830.0);
			Chem[39] = new Chemical(79, 196.966569, "Gold", "Au", 79, 118, true, "Transition Metal", 1064.43, 2807.0);
			Chem[40] = new Chemical(72, 178.49, "Hafnium", "Hf", 72, 106, true, "Transition Metal", 2150.0, 5400.0);
			Chem[41] = new Chemical(108, 270.0, "Hassium", "Hs", 108, 157, true, "Transition Metal");
			Chem[42] = new Chemical(2, 4.002602, "Helium", "He", 2, 2, true, "Noble Gas", -272.0, -268.6);
			Chem[43] = new Chemical(67, 164.93032, "Holmium", "Ho", 67, 98, true, "Rare Earth", 1470.0, 2720.0);
			Chem[44] = new Chemical(1, 1.00794, "Hydrogen", "H", 1, 0, true, "Non-metal", -259.14, -252.87);
			Chem[45] = new Chemical(53, 126.90447, "Iodine", "I", 53, 74, true, "Halogen", 113.5, 184.0);
			Chem[46] = new Chemical(49, 114.818, "Indium", "In", 49, 66, true, "Other Metals", 156.61, 2000.0);
			Chem[47] = new Chemical(77, 192.217, "Iridium", "Ir", 77, 115, true, "Transition Metal", 2410.0, 4527.0);
			Chem[48] = new Chemical(26, 55.845, "Iron", "Fe", 26, 30, true, "Transition Metal", 1535.0, 2750.0);
			Chem[49] = new Chemical(36, 83.798, "Krypton", "Kr", 36, 48, true, "Noble Gas", -157.2, -153.4);
			Chem[50] = new Chemical(57, 138.90547, "Lanthanum", "La", 57, 82, true, "Rare Earth", 920.0, 3469.0);
			Chem[51] = new Chemical(103, 262.0, "Lawrencium", "Lr", 103, 159, true, "Rare Earth");
			Chem[52] = new Chemical(82, 207.2, "Lead", "Pb", 82, 125, true, "Other Metals", 207.2, 327.5);
			Chem[53] = new Chemical(3, 6.941, "Lithium", "Li", 3, 4, true, "Alkali Metal", 180.54, 1347.0);
			Chem[54] = new Chemical(116, 293.0, "Livermorium", "Lv", 116, 173, true, "Other Metal");
			Chem[55] = new Chemical(71, 174.9668, "Lutetium", "Lu", 71, 104, true, "Rare Earth", 1656.0, 3315.0);
			Chem[56] = new Chemical(12, 24.305, "Magnesium", "Mg", 12, 12, true, "Alkaline Earth", 650.0, 1107.0);
			Chem[57] = new Chemical(25, 54.938045, "Manganese", "Mn", 25, 30, true, "Transition Metal", 1245.0, 1962.0);
			Chem[58] = new Chemical(109, 266.0, "Meitnerium", "Mt", 109, 157, true, "Transition Metal");
			Chem[59] = new Chemical(101, 258.0, "Mendelevium", "Md", 101, 157, true, "Rare Earth");
			Chem[60] = new Chemical(80, 200.59, "Mercury", "Hg", 80, 121, true, "Transition Metal", -38.87, 356.58);
			Chem[61] = new Chemical(42, 95.96, "Molybdenum", "Mo", 42, 54, true, "Transition Metal", 2617.0, 4612.0);
			Chem[62] = new Chemical(60, 144.242, "Neodymium", "Nd", 60, 84, true, "Rare Earth", 1010.0, 3127.0);
			Chem[63] = new Chemical(10, 20.1797, "Neon", "Ne", 10, 10, true, "Noble Gas", -248.6, 246.1);
			Chem[64] = new Chemical(93, 237.0, "Neptunium", "Np", 93, 144, true, "Rare Earth", 640.0, 3902.0);
			Chem[65] = new Chemical(28, 58.6934, "Nickel", "Ni", 28, 31, true, "Transition Metal", 1453.0, 2732.0);
			Chem[66] = new Chemical(41, 92.90638, "Niobium", "Nb", 41, 52, true, "Transition Metal", 2468.0, 4927.0);
			Chem[67] = new Chemical(7, 14.0067, "Nitrogen", "N", 7, 7, true, "Non-metal", -209.9, -195.8);
			Chem[68] = new Chemical(102, 259.0, "Nobelium", "No", 102, 157, true, "Rare Earth");
			Chem[69] = new Chemical(76, 190.23, "Osmium", "Os", 76, 114, true, "Transition Metal", 3045.0, 5027.0);
			Chem[70] = new Chemical(8, 15.9994, "Oxygen", "O", 8, 8, true, "Non-metal", -218.4, -183.0);
			Chem[71] = new Chemical(46, 106.42, "Palladium", "Pd", 46, 60, true, "Transition Metal", 1552.0, 2927.0);
			Chem[72] = new Chemical(15, 30.973762, "Phosphorus", "P", 15, 16, true, "Non-metal", 44.1, 280.0);
			Chem[73] = new Chemical(78, 195.084, "Platinum", "Pt", 78, 117, true, "Transition Metal", 1772.0, 3827.0);
			Chem[74] = new Chemical(94, 244.0, "Plutonium", "Pu", 94, 150, true, "Rare Earth", 639.5, 3235.0);
			Chem[75] = new Chemical(84, 209.0, "Polonium", "Po", 84, 125, true, "Metalloid", 254.0, 962.0);
			Chem[76] = new Chemical(19, 39.0983, "Potassium", "K", 19, 20, true, "Alkali Metal", 63.65, 774.0);
			Chem[77] = new Chemical(59, 140.91, "Praseodymium", "Pr", 59, 82, true, "Rare Earth", 935.0, 3127.0);
			Chem[78] = new Chemical(61, 145.0, "Promethium", "Pm", 61, 84, true, "Rare Earth");
			Chem[79] = new Chemical(91, 231.03588, "Protactinium", "Pa", 91, 140, true, "Rare Earth", 231.04, 1600.0);
			Chem[80] = new Chemical(88, 226.0, "Radium", "Ra", 88, 138, true, "Alkaline Earth", 700.0, 1737.0);
			Chem[81] = new Chemical(86, 222.0, "Radon", "Rn", 86, 136, true, "Noble Gas", -71.0, -61.8);
			Chem[82] = new Chemical(75, 186.207, "Rhenium", "Re", 75, 111, true, "Transition Metal", 3180.0, 5627.0);
			Chem[83] = new Chemical(45, 102.9055, "Rhodium", "Rh", 45, 58, true, "Transition Metal", 1966.0, 3727.0);
			Chem[84] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
			Chem[85] = new Chemical(37, 85.4678, "Rubidium", "Rb", 37, 48, true, "Alkali Metal", 38.89, 688.0);
			Chem[86] = new Chemical(44, 101.07, "Ruthenium", "Ru", 44, 57, true, "Transition Metal", 2250.0, 3900.0);
			Chem[87] = new Chemical(104, 267.0, "Rutherfordium", "Rf", 104, 157, true, "Transition Metal");
			Chem[88] = new Chemical(62, 150.36, "Samarium", "Sm", 62, 88, true, "Rare Earth", 1072.0, 1900.0);
			Chem[89] = new Chemical(21, 44.955912, "Scandium", "Sc", 21, 24, true, "Transition Metal", 1539.0, 2832.0);
			Chem[90] = new Chemical(106, 271.0, "Seaborgium", "Sg", 106, 157, true, "Transition Metal");
			Chem[91] = new Chemical(34, 78.96, "Selenium", "Se", 34, 45, true, "Non-metal", 217.0, 684.9);
			Chem[92] = new Chemical(14, 28.0855, "Silicon", "Si", 14, 14, true, "Metalloid", 1410.0, 2355.0);
			Chem[93] = new Chemical(47, 107.8682, "Silver", "Ag", 47, 61, true, "Transition Metal", 961.93, 2212.0);
			Chem[94] = new Chemical(11, 22.98676928, "Sodium", "Na", 11, 12, true, "Alkali Metal", 97.72, 883);
			Chem[95] = new Chemical(38, 87.62, "Strontium", "Sr", 38, 50, true, "Alkaline Earth", 769.0, 1384.0);
			Chem[96] = new Chemical(16, 32.065, "Sulfur", "S", 16, 16, true, "Non-metal", 112.8, 444.6);
			Chem[97] = new Chemical(73, 180.94788, "Tantalum", "Ta", 73, 108, true, "Transition Metal", 2996.0, 5425.0);
			Chem[98] = new Chemical(43, 98.0, "Technetium", "Tc", 43, 55, true, "Transition Metal", 2200.0, 4877.0);
			Chem[99] = new Chemical(52, 127.6, "Tellurium", "Te", 52, 76, true, "Metalloid", 449.5, 989.8);
			Chem[100] = new Chemical(65, 158.92535, "Terbium", "Tb", 65, 94, true, "Rare Earth", 1360.0, 3041.0);
			Chem[101] = new Chemical(81, 204.3833, "Thallium", "Tl", 81, 123, true, "Other Metals", 303.5, 1457.0);
			Chem[102] = new Chemical(90, 232.03806, "Thorium", "Th", 90, 142, true, "Rare Earth", 1750.0, 4790.0);
			Chem[103] = new Chemical(69, 168.93421, "Thulium", "Tm", 69, 100, true, "Rare Earth", 1545.0, 1727.0);
			Chem[104] = new Chemical(50, 118.71, "Tin", "Sn", 50, 69, true, "Other Metals", 231.9, 2270.0);
			Chem[105] = new Chemical(22, 47.867, "Titanium", "Ti", 22, 26, true, "Transition Metal", 1660.0, 3287.0);
			Chem[106] = new Chemical(74, 183.84, "Tungsten", "W", 74, 110, true, "Transition Metal", 3410.0, 5660.0);
			Chem[107] = new Chemical(118, 294.0, "Ununoctium", "Uuo", 118, 175, true, "Non-metal");
			Chem[108] = new Chemical(115, 288.0, "Ununpentium", "Uup", 115, 176, true, "Other Metal");
			Chem[109] = new Chemical(117, 294.0, "Ununseptium", "Uus", 117, 177, true, "Unknown");
			Chem[110] = new Chemical(113, 284.0, "Ununtrium", "Uut", 113, 170, true, "Other Metal");
			Chem[111] = new Chemical(92, 238.02891, "Uranium", "U", 92, 146, true, "Rare Earth", 1132.0, 3818.0);
			Chem[112] = new Chemical(23, 50.9415, "Vanadium", "V", 23, 28, true, "Transition Metal", 1890.0, 3380.0);
			Chem[113] = new Chemical(54, 131.293, "Xenon", "Xe", 54, 77, true, "Noble Gas", -111.9, -108.1);
			Chem[114] = new Chemical(70, 173.054, "Ytterbium", "Yb", 70, 103, true, "Rare Earth", 824.0, 1466.0);
			Chem[115] = new Chemical(39, 88.90585, "Yttrium", "Y", 39, 50, true, "Transition Metal", 1523.0, 3337.0);
			Chem[116] = new Chemical(30, 65.38, "Zinc", "Zn", 30, 35, true, "Transition Metal", 419.58, 907.0);
			Chem[117] = new Chemical(40, 91.224, "Zirconium", "Zr", 40, 51, true, "Transition Metal", 1852.0, 4377.0);
		}
		else
		{
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
			Chem[35] = new Chemical(114, 289.0, "Flerovium", "Fl", 114, 175, true, "Other Metal");
			Chem[36] = new Chemical(100, 257.0, "Fermium", "Fm", 100, 157, true, "Rare Earth");
			Chem[37] = new Chemical(87, 223.0, "Francium", "Fr", 87, 136, true, "Alkali Metal", 27.0, 677.0);
			Chem[38] = new Chemical(31, 69.723, "Gallium", "Ga", 31, 39, true, "Other Metals", 29.78, 2403.0);
			Chem[39] = new Chemical(64, 157.25, "Gadolinium", "Gd", 64, 93, true, "RareEarth", 1311.0, 3233.0);
			Chem[40] = new Chemical(32, 72.63, "Germanium", "Ge", 32, 41, true, "Metalloid", 937.4, 2830.0);
			Chem[41] = new Chemical(1, 1.00794, "Hydrogen", "H", 1, 0, true, "Non-metal", -259.14, -252.87);
			Chem[42] = new Chemical(2, 4.002602, "Helium", "He", 2, 2, true, "Noble Gas", -272.0, -268.6);
			Chem[43] = new Chemical(72, 178.49, "Hafnium", "Hf", 72, 106, true, "Transition Metal", 2150.0, 5400.0);
			Chem[44] = new Chemical(80, 200.59, "Mercury", "Hg", 80, 121, true, "Transition Metal", -38.87, 356.58);
			Chem[45] = new Chemical(67, 164.93032, "Holmium", "Ho", 67, 98, true, "Rare Earth", 1470.0, 2720.0);
			Chem[46] = new Chemical(108, 270.0, "Hassium", "Hs", 108, 157, true, "Transition Metal");
			Chem[47] = new Chemical(53, 126.90447, "Iodine", "I", 53, 74, true, "Halogen", 113.5, 184.0);
			Chem[48] = new Chemical(49, 114.818, "Indium", "In", 49, 66, true, "Other Metals", 156.61, 2000.0);
			Chem[49] = new Chemical(77, 192.217, "Iridium", "Ir", 77, 115, true, "Transition Metal", 2410.0, 4527.0);
			Chem[50] = new Chemical(19, 39.0983, "Potassium", "K", 19, 20, true, "Alkali Metal", 63.65, 774.0);
			Chem[51] = new Chemical(36, 83.798, "Krypton", "Kr", 36, 48, true, "Noble Gas", -157.2, -153.4);
			Chem[52] = new Chemical(57, 138.90547, "Lanthanum", "La", 57, 82, true, "Rare Earth", 920.0, 3469.0);
			Chem[53] = new Chemical(3, 6.941, "Lithium", "Li", 3, 4, true, "Alkali Metal", 180.54, 1347.0);
			Chem[54] = new Chemical(103, 262.0, "Lawrencium", "Lr", 103, 159, true, "Rare Earth");
			Chem[55] = new Chemical(71, 174.9668, "Lutetium", "Lu", 71, 104, true, "Rare Earth", 1656.0, 3315.0);
			Chem[56] = new Chemical(116, 293.0, "Livermorium", "Lv", 116, 173, true, "Other Metal");
			Chem[57] = new Chemical(101, 258.0, "Mendelevium", "Md", 101, 157, true, "Rare Earth");
			Chem[58] = new Chemical(12, 24.305, "Magnesium", "Mg", 12, 12, true, "Alkaline Earth", 650.0, 1107.0);
			Chem[59] = new Chemical(25, 54.938045, "Manganese", "Mn", 25, 30, true, "Transition Metal", 1245.0, 1962.0);
			Chem[60] = new Chemical(42, 95.96, "Molybdenum", "Mo", 42, 54, true, "Transition Metal", 2617.0, 4612.0);
			Chem[61] = new Chemical(109, 266.0, "Meitnerium", "Mt", 109, 157, true, "Transition Metal");
			Chem[62] = new Chemical(7, 14.0067, "Nitrogen", "N", 7, 7, true, "Non-metal", -209.9, -195.8);
			Chem[63] = new Chemical(11, 22.98676928, "Sodium", "Na", 11, 12, true, "Alkali Metal", 97.72, 883);
			Chem[64] = new Chemical(41, 92.90638, "Niobium", "Nb", 41, 52, true, "Transition Metal", 2468.0, 4927.0);
			Chem[65] = new Chemical(60, 144.242, "Neodymium", "Nd", 60, 84, true, "Rare Earth", 1010.0, 3127.0);
			Chem[66] = new Chemical(10, 20.1797, "Neon", "Ne", 10, 10, true, "Noble Gas", -248.6, 246.1);
			Chem[67] = new Chemical(28, 58.6934, "Nickel", "Ni", 28, 31, true, "Transition Metal", 1453.0, 2732.0);
			Chem[68] = new Chemical(102, 259.0, "Nobelium", "No", 102, 157, true, "Rare Earth");
			Chem[69] = new Chemical(93, 237.0, "Neptunium", "Np", 93, 144, true, "Rare Earth", 640.0, 3902.0);
			Chem[70] = new Chemical(8, 15.9994, "Oxygen", "O", 8, 8, true, "Non-metal", -218.4, -183.0);
			Chem[71] = new Chemical(76, 190.23, "Osmium", "Os", 76, 114, true, "Transition Metal", 3045.0, 5027.0);
			Chem[72] = new Chemical(15, 30.973762, "Phosphorus", "P", 15, 16, true, "Non-metal", 44.1, 280.0);
			Chem[73] = new Chemical(91, 231.03588, "Protactinium", "Pa", 91, 140, true, "Rare Earth", 231.04, 1600.0);
			Chem[74] = new Chemical(82, 207.2, "Lead", "Pb", 82, 125, true, "Other Metals", 207.2, 327.5);
			Chem[75] = new Chemical(46, 106.42, "Palladium", "Pd", 46, 60, true, "Transition Metal", 1552.0, 2927.0);
			Chem[76] = new Chemical(61, 145.0, "Promethium", "Pm", 61, 84, true, "Rare Earth");
			Chem[77] = new Chemical(84, 209.0, "Polonium", "Po", 84, 125, true, "Metalloid", 254.0, 962.0);
			Chem[78] = new Chemical(59, 140.91, "Praseodymium", "Pr", 59, 82, true, "Rare Earth", 935.0, 3127.0);
			Chem[79] = new Chemical(78, 195.084, "Platinum", "Pt", 78, 117, true, "Transition Metal", 1772.0, 3827.0);
			Chem[80] = new Chemical(94, 244.0, "Plutonium", "Pu", 94, 150, true, "Rare Earth", 639.5, 3235.0);
			Chem[81] = new Chemical(88, 226.0, "Radium", "Ra", 88, 138, true, "Alkaline Earth", 700.0, 1737.0);
			Chem[82] = new Chemical(37, 85.4678, "Rubidium", "Rb", 37, 48, true, "Alkali Metal", 38.89, 688.0);
			Chem[83] = new Chemical(75, 186.207, "Rhenium", "Re", 75, 111, true, "Transition Metal", 3180.0, 5627.0);
			Chem[84] = new Chemical(104, 267.0, "Rutherfordium", "Rf", 104, 157, true, "Transition Metal");
			Chem[85] = new Chemical(111, 280.0, "Roentgenium", "Rg", 111, 161, true, "Transition Metal");
			Chem[86] = new Chemical(45, 102.9055, "Rhodium", "Rh", 45, 58, true, "Transition Metal", 1966.0, 3727.0);
			Chem[87] = new Chemical(86, 222.0, "Radon", "Rn", 86, 136, true, "Noble Gas", -71.0, -61.8);
			Chem[88] = new Chemical(44, 101.07, "Ruthenium", "Ru", 44, 57, true, "Transition Metal", 2250.0, 3900.0);
			Chem[89] = new Chemical(16, 32.065, "Sulfur", "S", 16, 16, true, "Non-metal", 112.8, 444.6);
			Chem[90] = new Chemical(51, 121.76, "Antimony", "Sb", 51, 71, true, "Metalloid", 630.0, 1750.0);
			Chem[91] = new Chemical(21, 44.955912, "Scandium", "Sc", 21, 24, true, "Transition Metal", 1539.0, 2832.0);
			Chem[92] = new Chemical(34, 78.96, "Selenium", "Se", 34, 45, true, "Non-metal", 217.0, 684.9);
			Chem[93] = new Chemical(106, 271.0, "Seaborgium", "Sg", 106, 157, true, "Transition Metal");
			Chem[94] = new Chemical(14, 28.0855, "Silicon", "Si", 14, 14, true, "Metalloid", 1410.0, 2355.0);
			Chem[95] = new Chemical(62, 150.36, "Samarium", "Sm", 62, 88, true, "Rare Earth", 1072.0, 1900.0);
			Chem[96] = new Chemical(50, 118.71, "Tin", "Sn", 50, 69, true, "Other Metals", 231.9, 2270.0);
			Chem[97] = new Chemical(38, 87.62, "Strontium", "Sr", 38, 50, true, "Alkaline Earth", 769.0, 1384.0);
			Chem[98] = new Chemical(73, 180.94788, "Tantalum", "Ta", 73, 108, true, "Transition Metal", 2996.0, 5425.0);
			Chem[99] = new Chemical(65, 158.92535, "Terbium", "Tb", 65, 94, true, "Rare Earth", 1360.0, 3041.0);
			Chem[100] = new Chemical(43, 98.0, "Technetium", "Tc", 43, 55, true, "Transition Metal", 2200.0, 4877.0);
			Chem[101] = new Chemical(52, 127.6, "Tellurium", "Te", 52, 76, true, "Metalloid", 449.5, 989.8);
			Chem[102] = new Chemical(90, 232.03806, "Thorium", "Th", 90, 142, true, "Rare Earth", 1750.0, 4790.0);
			Chem[103] = new Chemical(22, 47.867, "Titanium", "Ti", 22, 26, true, "Transition Metal", 1660.0, 3287.0);
			Chem[104] = new Chemical(81, 204.3833, "Thallium", "Tl", 81, 123, true, "Other Metals", 303.5, 1457.0);
			Chem[105] = new Chemical(69, 168.93421, "Thulium", "Tm", 69, 100, true, "Rare Earth", 1545.0, 1727.0);
			Chem[106] = new Chemical(92, 238.02891, "Uranium", "U", 92, 146, true, "Rare Earth", 1132.0, 3818.0);
			Chem[107] = new Chemical(118, 294.0, "Ununoctium", "Uuo", 118, 175, true, "Non-metal");
			Chem[108] = new Chemical(115, 288.0, "Ununpentium", "Uup", 115, 176, true, "Other Metal");
			Chem[109] = new Chemical(117, 294.0, "Ununseptium", "Uus", 117, 177, true, "Unknown");
			Chem[110] = new Chemical(113, 284.0, "Ununtrium", "Uut", 113, 170, true, "Other Metal");
			Chem[111] = new Chemical(23, 50.9415, "Vanadium", "V", 23, 28, true, "Transition Metal", 1890.0, 3380.0);
			Chem[112] = new Chemical(74, 183.84, "Tungsten", "W", 74, 110, true, "Transition Metal", 3410.0, 5660.0);
			Chem[113] = new Chemical(54, 131.293, "Xenon", "Xe", 54, 77, true, "Noble Gas", -111.9, -108.1);
			Chem[114] = new Chemical(39, 88.90585, "Yttrium", "Y", 39, 50, true, "Transition Metal", 1523.0, 3337.0);
			Chem[115] = new Chemical(70, 173.054, "Ytterbium", "Yb", 70, 103, true, "Rare Earth", 824.0, 1466.0);
			Chem[116] = new Chemical(30, 65.38, "Zinc", "Zn", 30, 35, true, "Transition Metal", 419.58, 907.0);
			Chem[117] = new Chemical(40, 91.224, "Zirconium", "Zr", 40, 51, true, "Transition Metal", 1852.0, 4377.0);
		}
		
		return Chem;
	}
}
