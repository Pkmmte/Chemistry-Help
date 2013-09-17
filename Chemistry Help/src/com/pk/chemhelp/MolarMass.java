package com.pk.chemhelp;

import android.content.Intent;
import android.os.Bundle;
import android.text.*;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import java.text.DecimalFormat;

public class MolarMass extends SherlockActivity
{
	protected DataStorage appState;
	final String PageID = "Molar Mass";
	
	/* Exit */
	private boolean Exit;
	
	int numChemicals;
	Chemical[] Chem;
	int numSaved;
	Chemical[] savedChem;
	boolean removedNum;
	
	/* Results */
	TextView textMolecule;
	EditText inputMolecule;
	String valueMolecule;
	
	TextView textMolesMass;
	EditText inputMolesMass;
	String valueMolesMass;
	
	Button Calculate;
	
	TextView textResult;
	TextView textGramsPerMole;
	TextView GramsPerMole;
	TextView textMassMoles;
	TextView MassMoles;
	TextView textMassFraction;
	TextView MassFraction;
	TextView textMoleFraction;
	TextView MoleFraction;
	TextView textEmpiricalFormula;
	TextView EmpiricalFormula;
	
	TextView textError;
	String checkError;
	boolean foundError;
	
	/* Set Options */
	private RadioButton selectGramsPerMole = null;
	private RadioButton selectMolesToMass = null;
	private RadioButton selectMassToMoles = null;
	private boolean isSelectedGramsPerMole;
	private boolean isSelectedMolesToMass;
	private boolean isSelectedMassToMoles;
	private CheckBox checkBox;
	private boolean extraInfo;
	
	/* Bookmarks */
	String[] pageValues;
	String pageValue1;
	String pageValue2;
	String pageValue3;
	String pageValue4;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.molarmass);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		
		checkBox = (CheckBox) findViewById(R.id.CheckBox);
		
		pageValue1 = "Grams Per Mole";
		pageValue2 = "False";
		pageValue3 = "";
		pageValue4 = "";
		
		appState = (DataStorage) getApplication();
		
		textMolecule = (TextView) findViewById(R.id.EnterMolecule);
		inputMolecule = (EditText) findViewById(R.id.MoleculeInput);
		textMolesMass = (TextView) findViewById(R.id.EnterMolesMass);
		inputMolesMass = (EditText) findViewById(R.id.MolesMassInput);
		Calculate = (Button) findViewById(R.id.Calculate);
		
		textResult = (TextView) findViewById(R.id.textResult);
		textGramsPerMole = (TextView) findViewById(R.id.textGramsPerMole);
		GramsPerMole = (TextView) findViewById(R.id.GramsPerMole);
		textMassMoles = (TextView) findViewById(R.id.textMassMoles);
		MassMoles = (TextView) findViewById(R.id.MassMoles);
		textMassFraction = (TextView) findViewById(R.id.textMassFraction);
		MassFraction = (TextView) findViewById(R.id.MassFraction);
		textMoleFraction = (TextView) findViewById(R.id.textMoleFraction);
		MoleFraction = (TextView) findViewById(R.id.MoleFraction);
		textEmpiricalFormula = (TextView) findViewById(R.id.textEmpiricalFormula);
		EmpiricalFormula = (TextView) findViewById(R.id.EmpiricalFormula);
		
		textError = (TextView) findViewById(R.id.textError);
		textError.setTextColor(0xFFFF0000);
		
		Chem = Misc.getChemicals();
		numChemicals = Chem.length;
		savedChem = new Chemical[200];
		numSaved = 0;
		
		for (int i = 0; i < 200; i++)
		{
			savedChem[i] = new Chemical("Value", "Value");
		}
		
		savedChem[0].setName("Name0");
		savedChem[1].setName("Name1");
		
		extraInfo = false;
		isSelectedGramsPerMole = true;
		isSelectedMolesToMass = false;
		isSelectedMassToMoles = false;
		initializeRadioButtons();
		hideResults();
		hideExtraInfo();
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");
			
			setPageValues();
		}
		
		Calculate.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				//findNumbers(inputMolecule.getText().toString());
				
				if (inputMolecule.getText().toString().length() > 0)
				{
					checkError = "";
					foundError = findError();
					if (foundError)
					{
						textError.setText(checkError);
						textError.setVisibility(View.VISIBLE);
					}
					else
					{
						//fixTypos(inputMolecule.getText().toString());
						
						for (int i = 0; i < 200; i++)
						{
							savedChem[i] = new Chemical("Value", "Value");
							savedChem[i].setMassFraction(4.5);
							savedChem[i].setMoleFraction(4.5);
							savedChem[i].setMoles("1");
						}
						numSaved = 0;
						
						textError.setVisibility(View.GONE);
						showResults();
						
						double GPM = 0;
						double MOLES = 0;
						double GRAMS = 0;
						Spanned EF = Html.fromHtml("");
						
						valueMolecule = inputMolecule.getText().toString();
						separateChemicals(valueMolecule);
						findMassFraction(findGPM(valueMolecule));
						findMoleFraction();
						
						if (extraInfo)
						{
							showExtraInfo();
							
							Spanned[] E = new Spanned[numSaved];
							for (int g = 0; g < numSaved; g++)
							{
								E[g] = Html.fromHtml(EF + savedChem[g].getFormula());
								if (!(savedChem[g].getMoles().equals("1")) && !(savedChem[g].getMoles().equals("0")))
									E[g] = Html.fromHtml(E[g] + "<sub><small>" + savedChem[g].getMoles() + "</small></sub>");
							}
							for (int a = 0; a < numSaved; a++)
							{
								EF = (Spanned) TextUtils.concat(EF, E[a]);
							}
							
							EmpiricalFormula.setText(findEmpirical());
							
							String MaFraction = "";
							for (int j = 0; j < numSaved; j++)
							{
								MaFraction += savedChem[j].getFormula() + ": ";
								MaFraction += savedChem[j].getMassFraction() + "%";
								if (j != numSaved - 1)
									MaFraction += "\n";
							}
							
							String MoFraction = "";
							for (int j = 0; j < numSaved; j++)
							{
								MoFraction += savedChem[j].getFormula() + ": ";
								MoFraction += savedChem[j].getMoleFraction() + "%";
								if (j != numSaved - 1)
									MoFraction += "\n";
							}
							
							MassFraction.setText(MaFraction);
							MoleFraction.setText(MoFraction);
						}
						else
							hideExtraInfo();
						
						if (isSelectedGramsPerMole)
						{
							textMassMoles.setVisibility(View.GONE);
							MassMoles.setVisibility(View.GONE);
							
							GPM = findGPM(valueMolecule);
							GramsPerMole.setText("" + GPM + " g/mol");
						}
						else if (isSelectedMolesToMass)
						{
							textMassMoles.setText("Grams: ");
							if (inputMolesMass.getText().toString().length() > 0)
							{
								MassMoles.setVisibility(View.VISIBLE);
								textMassMoles.setVisibility(View.VISIBLE);
								
								GPM = findGPM(valueMolecule);
								GramsPerMole.setText("" + GPM + " g/mol");
								
								MOLES = Double.parseDouble(inputMolesMass.getText().toString());
								GRAMS = GPM * MOLES;
								MassMoles.setText("" + GRAMS);
							}
							else
							{
								textError.setText("Enter the number of moles");
								textError.setVisibility(View.VISIBLE);
								MassMoles.setVisibility(View.GONE);
								textMassMoles.setVisibility(View.GONE);
								
								hideResults();
								hideExtraInfo();
							}
						}
						else if (isSelectedMassToMoles)
						{
							textMassMoles.setText("Moles: ");
							if (inputMolesMass.getText().toString().length() > 0)
							{
								MassMoles.setVisibility(View.VISIBLE);
								textMassMoles.setVisibility(View.VISIBLE);
								
								GPM = findGPM(valueMolecule);
								GramsPerMole.setText("" + GPM + " g/mol");
								
								GRAMS = Double.parseDouble(inputMolesMass.getText().toString());
								MOLES = GRAMS / GPM;
								MassMoles.setText("" + MOLES);
							}
							else
							{
								textError.setText("Enter the number of grams");
								textError.setVisibility(View.VISIBLE);
								MassMoles.setVisibility(View.GONE);
								textMassMoles.setVisibility(View.GONE);
								
								hideResults();
								hideExtraInfo();
							}
						}
						else
							MySingleton.getInstance().addError("Wrong Molar Mass topic selection..");
					}
				}
				else
				{
					textError.setVisibility(View.VISIBLE);
					textError.setText("Please enter a molecule");
					hideResults();
					hideExtraInfo();
				}
				
			}
		});
		
		inputMolecule.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				valueMolecule = inputMolecule.getText().toString();
				
				if (isSelectedMolesToMass)
					textMolesMass.setText("Moles of " + valueMolecule);
				else
					textMolesMass.setText("Grams of " + valueMolecule);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				valueMolecule = inputMolecule.getText().toString();
				
				if (isSelectedMolesToMass)
					textMolesMass.setText("Moles of " + valueMolecule);
				else
					textMolesMass.setText("Grams of " + valueMolecule);
			}
		});
	}
	
	public void onRestart()
	{
		super.onRestart();
		
		Exit = MySingleton.getInstance().getExit();
		if (Exit)
			Exit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			mainMenu = menu;
			subMenu = menu.addSubMenu("");
			subMenu.add(Menu.NONE, R.id.AddBookmark_Label, Menu.NONE, "Add Bookmark");
			subMenu.add(Menu.NONE, R.id.Settings_Label, Menu.NONE, "Settings");
			if(DEBUG_MODE)
				subMenu.add(Menu.NONE, R.id.Debug_Label, Menu.NONE, "Debug");
			subMenu.add(Menu.NONE, R.id.Exit_Label, Menu.NONE, "Exit");
			
			MenuItem subMenuItem = subMenu.getItem();
			subMenuItem.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark);
			subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		
		this.warningIcon = menu.findItem(R.id.Warning_Label);
		this.debugMenu = menu.findItem(R.id.Debug_Label);
		if (!MySingleton.getInstance().getErrors()[0].equals("pcx_value"))
			warningIcon.setVisible(true);
		else
			warningIcon.setVisible(false);
		if (DEBUG_MODE)
			debugMenu.setVisible(true);
		else
			debugMenu.setVisible(false);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent intent = new Intent(this, ChemistryHelp.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				pageValue3 = inputMolecule.getText().toString();
				pageValue4 = inputMolesMass.getText().toString();
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", MolarMass.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", MolarMass.this).show();
				return true;
			case R.id.Settings_Label:
				MySingleton.getInstance().setPreviousPageID(PageID);
				Intent settingsIntent = new Intent(this, Settings.class);
				settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(settingsIntent);
				return true;
				/*
				 * case R.id.Help_Label: helpDialog.show(); return true; case
				 * R.id.Credits_Label: creditsDialog.show(); return true;
				 */
			case R.id.Exit_Label:
				Exit();
				return true;
			case R.id.Debug_Label:
				Intent i = new Intent(MolarMass.this, Debug.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB && keyCode == KeyEvent.KEYCODE_MENU)
		{
			mainMenu.performIdentifierAction(subMenu.getItem().getItemId(), 0);
            return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	
	public double findGPM(String Molecule)
	{
		double finalValue = 0;
		
		for (int y = 0; y < numSaved; y++)
		{
			finalValue += savedChem[y].getAtomicMass() * Double.parseDouble(savedChem[y].getMoles());
			//MySingleton.getInstance().addERROR("" + savedChem[y].getAtomicMass());
		}
		
		return finalValue;
	}
	
	public void findMassFraction(double GPM)
	{
		double Fraction = 0;
		for (int k = 0; k <= numSaved; k++)
		{
			Fraction = 100 * ((savedChem[k].getAtomicMass() * Double.parseDouble(savedChem[k].getMoles())) / GPM);
			Fraction = maxPrecision(Fraction);
			savedChem[k].setMassFraction(Fraction);
		}
		
	}
	
	public void findMoleFraction()
	{
		double Fraction = 0;
		int totalMoles = 0;
		for (int k = 0; k < numSaved; k++)
			totalMoles += Double.parseDouble(savedChem[k].getMoles());
		for (int k = 0; k <= numSaved; k++)
		{
			Fraction = 100 * (Double.parseDouble(savedChem[k].getMoles()) / totalMoles);
			Fraction = maxPrecision(Fraction);
			savedChem[k].setMoleFraction(Fraction);
		}
	}
	
	// Not ready for prime time yet...
	public void fixTypos(String Molecule)
	{
		boolean found = false;
		int start = 0;
		int end = 2;
		int length = Molecule.length();
		String fixedMolecule = "";
		
		while (length >= end)
		{
			for (int x = 0; x < numChemicals; x++)
			{
				if ((end + 0) > length)
				{
					end += 5;
					break;
				}
				if (Molecule.substring(start, end).equalsIgnoreCase(Chem[x].getFormula()))
				{
					fixedMolecule += Chem[x].getFormula();
					start += 2;
					end += 2;
					found = true;
				}
				else if (Molecule.substring(start, end - 1).equalsIgnoreCase(Chem[x].getFormula()))
				{
					fixedMolecule += Chem[x].getFormula();
					start += 1;
					end += 1;
					found = true;
				}
			}
			if (!found)
			{
				start += 2;
				end += 2;
			}
		}
		
		inputMolecule.setText(fixedMolecule);
	}
	
	public void separateChemicals(String Input)
	{
		String[] elements = Input.split("(?=[A-Z]{1,1}[a-z]{0,2})");
		String elementNN = "";
		int mols = 1;
		
		for (int x = 1; x <= elements.length - 1; x++)
		{
			for (int i = 0; i <= numChemicals - 1; i++)
			{
				removedNum = false;
				elementNN = removeNumbers(elements[x]);
				if (elementNN.equals(Chem[i].getFormula()))
				{
					savedChem[numSaved].setName(Chem[i].getName());
					savedChem[numSaved].setFormula(Chem[i].getFormula());
					savedChem[numSaved].setAtomicMass(Chem[i].getAtomicMass());
					savedChem[numSaved].setAtomicNumber(Chem[i].getAtomicNumber());
					if (removedNum)
					{
						mols = findNum(elements[x]);
						savedChem[numSaved].setMoles("" + mols);
					}
					numSaved++;
				}
			}
		}
	}
	
	public int findNum(String Input)
	{
		String num;
		int number = 0;
		String Test = "";
		int s = 0;
		int e = 1;
		boolean foundNumber = false;
		boolean skip = false;
		
		for (int p = 0; p < Input.length(); p++)
		{
			Test = Input.substring(s, e);
			s++;
			e++;
			skip = false;
			if (foundNumber)
			{
				if (isInteger(Test))
				{
					skip = true;
					num = number + Test;
					number = Integer.parseInt(num);
					
					if (p == Input.length() - 1)
						return number;
				}
			}
			if (isInteger(Test) && skip == false)
			{
				foundNumber = true;
				number = Integer.parseInt(Test);
				if (p == Input.length() - 1)
					return number;
			}
		}
		
		return 1;
	}
	
	public void findNumbers(String Input)
	{
		String[] elements = Input.split("(?=[A-Z]{1,1}[a-z]{0,2})");
		String num;
		int number = 0;
		String Test = "";
		int s = 0;
		int e = 1;
		boolean foundNumber = false;
		//int numFound = 0;
		boolean skip = false;
		
		/*
		 * for(int x = 0; x < Input.length(); x++) { foundNumber = false; Test =
		 * Input.substring(s, e);
		 * 
		 * s++; e++; if(isInteger(Test))
		 * MySingleton.getInstance().addERROR(Test); }
		 */
		for (int x = 1; x <= elements.length - 1; x++)
		{
			foundNumber = false;
			//numFound = 0;
			for (int p = 0; p < elements[x].length(); p++)
			{
				Test = Input.substring(s, e);
				s++;
				e++;
				skip = false;
				if (foundNumber)
				{
					if (isInteger(Test))
					{
						skip = true;
						num = number + Test;
						number = Integer.parseInt(num);
						//numFound++;
					}
				}
				if (isInteger(Test) && skip == false)
				{
					foundNumber = true;
					//numFound++;
					number = Integer.parseInt(Test);
					//MySingleton.getInstance().addERROR("" + number);
					if (p == elements[x].length() - 1)
					{
						//if(elements[x].substring(0, (elements[x].length() - numFound)).equals(elements[x].getFormula))
						//MySingleton.getInstance().addERROR(elements[x].substring(0, (elements[x].length() - numFound)));
						//MySingleton.getInstance().addERROR("LAST NUMBER: " + number);
					}
				}
			}
			//	for (int i = 0; i <= numSaved; i++)
			//	{
			//		if(isInteger(elements[x]))
			//			savedChem[i - 1].setMoles(Integer.parseInt(elements[x]));
			//	}
		}
	}
	
	public boolean findError()
	{
		boolean isCapital = Character.isUpperCase(valueMolecule.charAt(0));
		if (!isCapital)
		{
			checkError = "Please make sure you capitalize the first letter of each element.";
			return true;
		}
		String[] elementsN = valueMolecule.split("(?=[A-Z]{1,1}[a-z]{0,2})");
		String element = removeNumbers(valueMolecule);
		String[] elements = element.split("(?=[A-Z]{1,1}[a-z]{0,2})");
		String[] invalidElements = new String[200];
		boolean foundValid = false;
		boolean foundInvalid = false;
		int numInvalid = 0;
		for (int i = 0; i < 200; i++)
		{
			invalidElements[i] = "Value";
		}
		for (int x = 1; x <= elements.length - 1; x++)
		{
			foundValid = false;
			for (int i = 0; i <= numChemicals - 1; i++)
			{
				if (elements[x].equals(Chem[i].getFormula()))
				{
					foundValid = true;
				}
				else
				{
					if (i == 119 && foundValid == false)
					{
						foundInvalid = true;
						invalidElements[numInvalid] = elements[x];
						numInvalid++;
					}
				}
			}
		}
		if (isInteger(elementsN[0]))
		{
			checkError = "Cannot begin with number. Try placing it after the element not before.";
			return true;
		}
		else if (foundInvalid)
		{
			if (numInvalid == 1)
				checkError = "Invalid Element: " + invalidElements[0];
			else
			{
				checkError = "Invalid Elements: ";
				for (int h = 0; h < numInvalid; h++)
				{
					checkError += invalidElements[h];
					if (h < numInvalid - 1)
						checkError += ", ";
				}
			}
			return true;
		}
		else
			return false;
	}
	
	public double maxPrecision(double d)
	{
		double FormatedDecimal;
		DecimalFormat fiveDForm = new DecimalFormat("#.#####");
		FormatedDecimal = Double.valueOf(fiveDForm.format(d));
		return FormatedDecimal;
	}
	
	public boolean isInteger(String input)
	{
		try
		{
			Integer.parseInt(input);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public String removeNumbers(String input)
	{
		String endResult = "";
		String Test = "";
		int s = 0;
		int e = 1;
		for (int x = 0; x < input.length(); x++)
		{
			Test = input.substring(s, e);
			if (!(Test.equals("0")) && !(Test.equals("1")) && !(Test.equals("2")) && !(Test.equals("3")) && !(Test.equals("4")) && !(Test.equals("5")) && !(Test.equals("6")) && !(Test.equals("7")) && !(Test.equals("8")) && !(Test.equals("9")))
				endResult += input.substring(s, e);
			else
				removedNum = true;
			s++;
			e++;
		}
		return endResult;
	}
	
	public Spanned findEmpirical()
	{
		Chemical[] empChem = savedChem.clone();
		int lowest = 999999999;
		boolean divisible = true;
		for (int t = 0; t < numSaved; t++)
		{
			if(Integer.parseInt(empChem[t].getMoles()) < lowest)
				lowest = Integer.parseInt(empChem[t].getMoles());
		}
		
		
		for (int p = 0; p < numSaved; p++)
		{
			if(Integer.parseInt(empChem[p].getMoles()) % lowest == 0)
				divisible = true;
			else
			{
				divisible = false;
				break;
			}
		}
		
		if (divisible)
		{
			for(int v = 0; v < numSaved; v++)
				empChem[v].setMoles("" + (Integer.parseInt(empChem[v].getMoles()) / lowest));
		}
		
		Spanned EMP = Html.fromHtml("");
		Spanned[] E = new Spanned[numSaved];
		for (int g = 0; g < numSaved; g++)
		{
			E[g] = Html.fromHtml(EMP + empChem[g].getFormula());
			if (!(empChem[g].getMoles().equals("1")) && !(empChem[g].getMoles().equals("0")))
				E[g] = Html.fromHtml(E[g] + "<sub><small>" + empChem[g].getMoles() + "</small></sub>");
		}
		for (int a = 0; a < numSaved; a++)
			EMP = (Spanned) TextUtils.concat(EMP, E[a]);
	
		return EMP;
	}
	
	public void onCheckboxClicked(View view)
	{
		// Is the view now checked?
		boolean checked = checkBox.isChecked();
		if (checked)
		{
			// I heard you like extra information.. =P
			extraInfo = true;
			
			pageValue2 = "True";
		}
		else
		{
			// Dumbass!! >=O
			extraInfo = false;
			
			pageValue2 = "False";
		}
	}
	
	public void initializeRadioButtons()
	{
		selectGramsPerMole = (RadioButton) findViewById(R.id.Radio1);
		selectMolesToMass = (RadioButton) findViewById(R.id.Radio2);
		selectMassToMoles = (RadioButton) findViewById(R.id.Radio3);
	}
	
	public void onRadioButtonClicked(View v)
	{
		valueMolecule = inputMolecule.getText().toString();
		if (selectGramsPerMole.isChecked())
		{
			textMolesMass.setText("Value");
			textMolesMass.setVisibility(View.GONE);
			inputMolesMass.setVisibility(View.GONE);
			
			isSelectedGramsPerMole = true;
			isSelectedMolesToMass = false;
			isSelectedMassToMoles = false;
			
			pageValue1 = "Grams Per Mole";
		}
		else if (selectMolesToMass.isChecked())
		{
			textMolesMass.setText("Moles of " + valueMolecule);
			textMolesMass.setVisibility(View.VISIBLE);
			inputMolesMass.setVisibility(View.VISIBLE);
			
			isSelectedGramsPerMole = false;
			isSelectedMolesToMass = true;
			isSelectedMassToMoles = false;
			
			pageValue1 = "MolesToMass";
		}
		else if (selectMassToMoles.isChecked())
		{
			textMolesMass.setText("Grams of " + valueMolecule);
			textMolesMass.setVisibility(View.VISIBLE);
			inputMolesMass.setVisibility(View.VISIBLE);
			
			isSelectedGramsPerMole = false;
			isSelectedMolesToMass = false;
			isSelectedMassToMoles = true;
			
			pageValue1 = "MassToMoles";
		}
	}
	
	public void showResults()
	{
		textResult.setVisibility(View.VISIBLE);
		textGramsPerMole.setVisibility(View.VISIBLE);
		GramsPerMole.setVisibility(View.VISIBLE);
	}
	
	public void hideResults()
	{
		textResult.setVisibility(View.GONE);
		textGramsPerMole.setVisibility(View.GONE);
		GramsPerMole.setVisibility(View.GONE);
	}
	
	public void showExtraInfo()
	{
		textMassFraction.setVisibility(View.VISIBLE);
		MassFraction.setVisibility(View.VISIBLE);
		textMoleFraction.setVisibility(View.VISIBLE);
		MoleFraction.setVisibility(View.VISIBLE);
		textEmpiricalFormula.setVisibility(View.VISIBLE);
		EmpiricalFormula.setVisibility(View.VISIBLE);
	}
	
	public void hideExtraInfo()
	{
		textMassFraction.setVisibility(View.GONE);
		MassFraction.setVisibility(View.GONE);
		textMoleFraction.setVisibility(View.GONE);
		MoleFraction.setVisibility(View.GONE);
		textEmpiricalFormula.setVisibility(View.GONE);
		EmpiricalFormula.setVisibility(View.GONE);
	}
	
	public String[] getPageValues()
	{
		String[] values = new String[50];
		for(int l = 0; l < 50; l++)
			values[l] = "pcx_value";
		
		values[0] = "MolarMass";
		values[1] = pageValue1;
		values[2] = pageValue2;
		values[3] = pageValue3;
		values[4] = pageValue4;
		
		return values;
	}
	
	public void setPageValues()
	{
		valueMolecule = pageValues[3];
		
		if(pageValues[0].equals("MolarMass"))
		{
			if(pageValues[1].equals("MolesToMass"))
			{
				selectGramsPerMole.setChecked(false);
				selectMassToMoles.setChecked(false);
				selectMolesToMass.setChecked(true);
				
				textMolesMass.setText("Moles of " + valueMolecule);
				textMolesMass.setVisibility(View.VISIBLE);
				inputMolesMass.setVisibility(View.VISIBLE);
				
				isSelectedGramsPerMole = false;
				isSelectedMolesToMass = true;
				isSelectedMassToMoles = false;
				
				pageValue1 = "MolesToMass";
			}
			else if(pageValues[1].equals("MassToMoles"))
			{
				selectGramsPerMole.setChecked(false);
				selectMassToMoles.setChecked(true);
				selectMolesToMass.setChecked(false);
				textMolesMass.setText("Grams of " + valueMolecule);
				textMolesMass.setVisibility(View.VISIBLE);
				inputMolesMass.setVisibility(View.VISIBLE);
				
				isSelectedGramsPerMole = false;
				isSelectedMolesToMass = false;
				isSelectedMassToMoles = true;
				
				pageValue1 = "MassToMoles";
			}
			else
			{
				selectGramsPerMole.setChecked(true);
				selectMassToMoles.setChecked(false);
				selectMolesToMass.setChecked(false);
				textMolesMass.setText("Value");
				textMolesMass.setVisibility(View.GONE);
				inputMolesMass.setVisibility(View.GONE);
				
				isSelectedGramsPerMole = true;
				isSelectedMolesToMass = false;
				isSelectedMassToMoles = false;
				
				pageValue1 = "Grams Per Mole";
			}
			
			if(pageValues[2].equals("True"))
			{
				checkBox.setChecked(true);
				extraInfo = true;
				pageValue2 = "True";
			}
			else
			{
				checkBox.setChecked(false);
				extraInfo = false;
				pageValue2 = "False";
			}
			
			inputMolecule.setText(pageValues[3]);
			valueMolecule = inputMolecule.getText().toString();

			if (isSelectedMolesToMass)
				textMolesMass.setText("Moles of " + valueMolecule);
			else
				textMolesMass.setText("Grams of " + valueMolecule);
			
			if(pageValues[1].equals("MolesToMass") || pageValues[1].equals("MassToMoles"))
				inputMolesMass.setText(pageValues[4]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(MolarMass.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_LONG).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}

/*
 * boolean found = false; //boolean kill = false; //String howMany = ""; int
 * start = 0; int end = 2; int length = Input.length();
 * 
 * while(length >= end) { //countloop: for(int x = 0; x < numChemicals; x++) {
 * if((end + 0) > length) { end += 5; //kill = true; //howMany = "zero"; break;
 * } if(Input.substring(start, end).equals(Chem[x].getFormula())) {
 * //savedChem[numSaved].setAtomicMass(Chem[x].getAtomicMass());
 * //savedChem[numSaved].setAtomicNumber(Chem[x].getAtomicNumber());
 * savedChem[numSaved].setName(Chem[x].getName());
 * savedChem[numSaved].setFormula(Chem[x].getFormula());
 * //savedChem[numSaved].setNumProtons(Chem[x].getNumProtons());
 * //savedChem[numSaved].setNumElectrons(Chem[x].getNumElectrons());
 * //savedChem[numSaved].setNumNeutrons(Chem[x].getNumNeutrons()); numSaved++;
 * start += 2; end += 2; found = true; //howMany = "two"; //break countloop; }/*
 * else if(Input.substring(start, end - 1).equals(Chem[x].getFormula())) {
 * //savedChem[numSaved].setAtomicMass(Chem[x].getAtomicMass());
 * //savedChem[numSaved].setAtomicNumber(Chem[x].getAtomicNumber());
 * //savedChem[numSaved].setName(Chem[x].getName());
 * savedChem[numSaved].setFormula(Chem[x].getFormula());
 * //savedChem[numSaved].setNumProtons(Chem[x].getNumProtons());
 * //savedChem[numSaved].setNumElectrons(Chem[x].getNumElectrons());
 * //savedChem[numSaved].setNumNeutrons(Chem[x].getNumNeutrons()); numSaved++;
 * start += 1; end += 1; found = true; //howMany = "one"; //break countloop; }
 */
/*
 * else { found = false; //howMany = "zero"; } } if(!found) { start += 2; end +=
 * 2; }
 */
/*
 * if(found) { if(howMany.equals("two")) { start += 2; end += 2; } else
 * if(howMany.equals("one")) { start += 1; end += 1; } else {
 * 
 * } }
 */
//if(kill)
//	break;
/*
 * else { found = false; start = 0; end = 2; }
 */
/*
 * }
 */
