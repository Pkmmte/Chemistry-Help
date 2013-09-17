package com.pk.chemhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class ChemicalSearch extends SherlockActivity
{
	final String PageID = "Chemical Search";
	
	/* Exit Out Of Here! */
	private boolean Exit;

	/* For AutoCompletion and Input */
	AutoCompleteTextView ChemicalSearch;
	String[] nameArray;
	String[] formulaArray;

	/* Set Options */
	ArrayAdapter<String> adapterName;
	ArrayAdapter<String> adapterFormula;
	private RadioButton selectName = null;
	private RadioButton selectFormula = null;
	
	Chemical[] Chem;

	/* Results */
	TextView textError;
	TextView textBasicInformation;
	TextView textName;
	TextView Name;
	TextView textFormula;
	TextView Formula;
	TextView textAtomicNumber;
	TextView AtomicNumber;
	TextView textAtomicMass;
	TextView AtomicMass;
	TextView textNumberOfProtons;
	TextView NumberOfProtons;
	TextView textNumberOfNeutrons;
	TextView NumberOfNeutrons;
	TextView textNumberOfElectrons;
	TextView NumberOfElectrons;
	TextView textClassification;
	TextView Classification;
	TextView textMeltingPoint;
	TextView MeltingPoint;
	TextView textBoilingPoint;
	TextView BoilingPoint;
	TextView textIsElement;
	
	/* Result Info */
	int numChemicals;
	String Selection;
	String searchName;
	String searchFormula;
	String foundName;
	String foundFormula;
	boolean selectedName;
	boolean selectedFormula;
	
	boolean infoIsElement;
	String infoName;
	String infoFormula;
	int infoAtomicNumber;
	double infoAtomicMass;
	int infoNumProtons;
	int infoNumNeutrons;
	int infoNumElectrons;
	
	/* Submit */
	Button Submit;
	
	/* Bookmarks */
	String[] pageValues;
	String pageValue1;
	String pageValue2;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;

	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();

	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.chemicalsearch);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];

		nameArray = getResources().getStringArray(R.array.ChemicalName_array);
		formulaArray = getResources().getStringArray(R.array.ChemicalFormula_array);
		
		pageValue1 = "Name";
		pageValue2 = "";

		ChemicalSearch = (AutoCompleteTextView) findViewById(R.id.searchChemical);
		adapterName = new ArrayAdapter<String>(this, R.layout.list_item, nameArray);
		adapterFormula = new ArrayAdapter<String>(this, R.layout.list_item, formulaArray);
		selectName = (RadioButton) findViewById(R.id.nameRadio);
		selectFormula = (RadioButton) findViewById(R.id.formulaRadio);
		ChemicalSearch.setThreshold(1);
		ChemicalSearch.setAdapter(adapterName);

		selectedName = true;
		selectedFormula = false;
		
		// IMPORTANT... get Chemicals.
		Chem = Misc.getChemicals();
		numChemicals = Chem.length;
		
		textError = (TextView)findViewById(R.id.textError);
		textBasicInformation = (TextView)findViewById(R.id.textBasicInformation);
		textName = (TextView)findViewById(R.id.textName);
		Name = (TextView)findViewById(R.id.Name);
		textFormula = (TextView)findViewById(R.id.textFormula);
		Formula = (TextView)findViewById(R.id.Formula);
		textAtomicNumber = (TextView)findViewById(R.id.textAtomicNumber);
		AtomicNumber = (TextView)findViewById(R.id.AtomicNumber);
		textAtomicMass = (TextView)findViewById(R.id.textAtomicMass);
		AtomicMass = (TextView)findViewById(R.id.AtomicMass);
		textNumberOfProtons = (TextView)findViewById(R.id.textNumberOfProtons);
		NumberOfProtons = (TextView)findViewById(R.id.NumberOfProtons);
		textNumberOfNeutrons = (TextView)findViewById(R.id.textNumberOfNeutrons);
		NumberOfNeutrons = (TextView)findViewById(R.id.NumberOfNeutrons);
		textNumberOfElectrons = (TextView)findViewById(R.id.textNumberOfElectrons);
		NumberOfElectrons = (TextView)findViewById(R.id.NumberOfElectrons);
		textClassification = (TextView)findViewById(R.id.textClassification);
		Classification = (TextView)findViewById(R.id.Classification);
		textMeltingPoint = (TextView)findViewById(R.id.textMeltingPoint);
		MeltingPoint = (TextView)findViewById(R.id.MeltingPoint);
		textBoilingPoint = (TextView)findViewById(R.id.textBoilingPoint);
		BoilingPoint = (TextView)findViewById(R.id.BoilingPoint);
		textIsElement = (TextView)findViewById(R.id.textIsElement);

		textError.setText("");
		hideInfo();
		
		Submit = (Button) findViewById(R.id.submit);
		
		if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");

			setPageValues();
		}
		
		Submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Selection = ChemicalSearch.getText().toString();
				boolean Found = false;
				@SuppressWarnings("static-access")
				InputMethodManager inputManager = (InputMethodManager) getSystemService(ChemicalSearch.this.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					
				for(int x = 0; x < numChemicals; x++)
				{
					if(selectedName)
					{
						if(Selection.equals(Chem[x].getName()))
						{
							foundName = Chem[x].getName();
							setChemicalInformation(x);
							Found = true;
							break;
						}
					}
					else if(selectedFormula)
					{
						if(Selection.equals(Chem[x].getFormula()))
						{
							foundFormula = Chem[x].getFormula();
							setChemicalInformation(x);
							Found = true;
							break;
						}
					}
				}
				if(Selection.length() > 0 && Found)
				{
					textError.setText("");
					showInfo();
				}
				else if(Selection.length() == 0)
				{
					textError.setText("Please enter the element then try again.");
					hideInfo();
				}
				else if(!Found)
				{
					textError.setText("No such thing found.");
					hideInfo();
				}
				else
				{
					textError.setText("" + numChemicals);
				}
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
		if(!MySingleton.getInstance().getErrors()[0].equals("pcx_value"))
			warningIcon.setVisible(true);
		else
			warningIcon.setVisible(false);
		if(DEBUG_MODE)
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
			pageValue2 = ChemicalSearch.getText().toString();
			MySingleton.getInstance().setPageValues(getPageValues());
			MySingleton.getInstance().setPreviousPageID(PageID);
			Dialogs.getDialog("Add Bookmark", ChemicalSearch.this).show();
			return true;
		case R.id.Bookmarks_Label:
			Dialogs.getDialog("Bookmarks", ChemicalSearch.this).show();
			return true;
		case R.id.Settings_Label:
			MySingleton.getInstance().setPreviousPageID(PageID);
			Intent settingsIntent = new Intent(this, Settings.class);
			settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(settingsIntent);
			return true;
		/*case R.id.Help_Label:
			helpDialog.show();
			return true;
		case R.id.Credits_Label:
			creditsDialog.show();
			return true;*/
		case R.id.Exit_Label:
			Exit();
			return true;
		case R.id.Debug_Label:
			Intent i = new Intent(ChemicalSearch.this, Debug.class);
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
	
	public void hideInfo()
	{
		textBasicInformation.setVisibility(View.GONE);
		textName.setVisibility(View.GONE);
		Name.setVisibility(View.GONE);
		textFormula.setVisibility(View.GONE);
		Formula.setVisibility(View.GONE);
		textAtomicNumber.setVisibility(View.GONE);
		AtomicNumber.setVisibility(View.GONE);
		textAtomicMass.setVisibility(View.GONE);
		AtomicMass.setVisibility(View.GONE);
		textNumberOfProtons.setVisibility(View.GONE);
		NumberOfProtons.setVisibility(View.GONE);
		textNumberOfNeutrons.setVisibility(View.GONE);
		NumberOfNeutrons.setVisibility(View.GONE);
		textNumberOfElectrons.setVisibility(View.GONE);
		NumberOfElectrons.setVisibility(View.GONE);
		textClassification.setVisibility(View.GONE);
		Classification.setVisibility(View.GONE);
		textMeltingPoint.setVisibility(View.GONE);
		MeltingPoint.setVisibility(View.GONE);
		textBoilingPoint.setVisibility(View.GONE);
		BoilingPoint.setVisibility(View.GONE);
		textIsElement.setVisibility(View.GONE);
	}
	
	public void showInfo()
	{
		textBasicInformation.setVisibility(View.VISIBLE);
		textName.setVisibility(View.VISIBLE);
		Name.setVisibility(View.VISIBLE);
		textFormula.setVisibility(View.VISIBLE);
		Formula.setVisibility(View.VISIBLE);
		textAtomicNumber.setVisibility(View.VISIBLE);
		AtomicNumber.setVisibility(View.VISIBLE);
		textAtomicMass.setVisibility(View.VISIBLE);
		AtomicMass.setVisibility(View.VISIBLE);
		textNumberOfProtons.setVisibility(View.VISIBLE);
		NumberOfProtons.setVisibility(View.VISIBLE);
		textNumberOfNeutrons.setVisibility(View.VISIBLE);
		NumberOfNeutrons.setVisibility(View.VISIBLE);
		textNumberOfElectrons.setVisibility(View.VISIBLE);
		NumberOfElectrons.setVisibility(View.VISIBLE);
	}

	public void onSelectRadioButtonClicked(View v)
	{
		if (selectName.isChecked())
		{
			ChemicalSearch.setAdapter(adapterName);
			selectedName = true;
			selectedFormula = false;
			pageValue1 = "Name";
		}
		else if (selectFormula.isChecked())
		{
			ChemicalSearch.setAdapter(adapterFormula);
			selectedName = false;
			selectedFormula = true;
			pageValue1 = "Formula";
		}
	}
	
	public void setChemicalInformation(int chemNum)
	{
		String text;
		text = "" + Chem[chemNum].getName();
		Name.setText(text);
		text = "" + Chem[chemNum].getFormula();
		Formula.setText(text);
		text = "" + Chem[chemNum].getAtomicNumber();
		AtomicNumber.setText(text);
		text = "" + Chem[chemNum].getAtomicMass() + " amu";
		AtomicMass.setText(text);
		text = "" + Chem[chemNum].getNumProtons();
		NumberOfProtons.setText(text);
		text = "" + Chem[chemNum].getNumNeutrons();
		NumberOfNeutrons.setText(text);
		text = "" + Chem[chemNum].getNumElectrons();
		NumberOfElectrons.setText(text);
		if(Chem[chemNum].isElement())
		{
			textFormula.setText("Symbol: ");
			textIsElement.setVisibility(View.VISIBLE);
		}
		else
		{
			textFormula.setText("Formula: ");
			textIsElement.setVisibility(View.GONE);
		}
		if(Chem[chemNum].getClassification().length() > 0)
		{
			textClassification.setVisibility(View.VISIBLE);
			Classification.setVisibility(View.VISIBLE);
			text = "" + Chem[chemNum].getClassification();
			Classification.setText(text);
		}
		else
		{
			textClassification.setVisibility(View.GONE);
			Classification.setVisibility(View.GONE);
		}
		if(Chem[chemNum].nullMeltingPoint())
		{
			textMeltingPoint.setVisibility(View.GONE);
			MeltingPoint.setVisibility(View.GONE);
		}
		else
		{
			textMeltingPoint.setVisibility(View.VISIBLE);
			MeltingPoint.setVisibility(View.VISIBLE);
			text = "" + Chem[chemNum].getMeltingPoint() + " �C";
			MeltingPoint.setText(text);
		}
		if(Chem[chemNum].nullBoilingPoint())
		{
			textBoilingPoint.setVisibility(View.GONE);
			BoilingPoint.setVisibility(View.GONE);
		}
		else
		{
			textBoilingPoint.setVisibility(View.VISIBLE);
			BoilingPoint.setVisibility(View.VISIBLE);
			text = "" + Chem[chemNum].getBoilingPoint() + " �C";
			BoilingPoint.setText(text);
		}
	}

	public String[] getPageValues()
	{
		String[] values = new String[50];
		for(int l = 0; l < 50; l++)
			values[l] = "pcx_value";

		values[0] = PageID;
		values[1] = pageValue1;
		values[2] = pageValue2;
		
		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			if(pageValues[1].equals("Name"))
			{
				selectFormula.setChecked(false);
				selectName.setChecked(true);
				ChemicalSearch.setAdapter(adapterName);
				selectedName = true;
				selectedFormula = false;
				pageValue1 = "Name";
			}
			else
			{
				selectName.setChecked(false);
				selectFormula.setChecked(true);
				ChemicalSearch.setAdapter(adapterFormula);
				selectedName = false;
				selectedFormula = true;
				pageValue1 = "Formula";
			}

			ChemicalSearch.setText(pageValues[2]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(ChemicalSearch.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}

	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
