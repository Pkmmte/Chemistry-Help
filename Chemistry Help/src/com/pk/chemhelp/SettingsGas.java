package com.pk.chemhelp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SettingsGas extends SherlockActivity
{
	protected DataStorage appState;
	private String SettingsTAG = "ChemHelpSettings";
	private SharedPreferences settings;
	
	// Globals
	int defaultTempUnit;
	String defaultTemp;
	String defaultTempS;
	int defaultPressureUnit;
	String defaultPres;
	String defaultPresS;
	int defaultVolumeUnit;
	String defaultVol;
	String defaultVolS;
	String maxDecimalAccuracy;
	String convertMaxDecimalAccuracy;
	String selectedTemperature;
	String selectedPressure;
	String selectedVolume;
	boolean Gas_SameUnits;
	boolean Gas_AutoSolve;
	boolean Gas_FormatHelp;
	
	// Function Selection
	String Item;
	String Select;
	int Selection = 0;
	int Position = 0;
	
	// Selection Dialog
	Dialog selectionDialog;
	Button selectionOne;
	Button selectionTwo;
	Button selectionThree;
	Button selectionFour;
	Button selectionFive;
	Button selectionSix;
	Button selectionSeven;
	
	// Layouts
	RelativeLayout Relative1;
	RelativeLayout Relative2;
	RelativeLayout Relative3;
	RelativeLayout Relative4;
	RelativeLayout Relative5;
	RelativeLayout Relative6;
	RelativeLayout Relative7;
	ImageView CheckBox;
	
	// Variables
	String Name;
	String Description;
	String Value;
	boolean Check;
	
	// Local Variables
	String VolumeName = "Volume Units";
	String PressureName = "Pressure Units";
	String TemperatureName = "Temperature Units";
	String SameUnitName = "Same Units";
	String AutoSolveName = "Auto Solve";
	String FormatHelpName = "Format Help";
	String DecimalName = "Decimal Accuracy";
	
	String VolumeDescription = "Prefered volume units...";
	String PressureDescription = "Prefered pressure units...";
	String TemperatureDescription = "Prefered temperature units...";
	String SameUnitDescription = "Matching units under each category...";
	String AutoSolveDescription = "No need to press \"Solve\" button...";
	String FormatHelpDescription = "Helps prevent possible confusion...";
	String DecimalDescription = "Maximum decimal point accuracy...";
	
	String VolumeValue = "L";
	String PressureValue = "atm";
	String TemperatureValue = "K";
	String SameUnitValue = "false";
	String AutoSolveValue = "false";
	String FormatHelpValue = "false";
	String DecimalValue = "5";
	
	final List<SettingsItem> listOfItems = new ArrayList<SettingsItem>();
	SettingsGasAdapter adapter = new SettingsGasAdapter(this, listOfItems);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Gas Settings");
		
		initializeGlobals();
		
		selectionDialog = new Dialog(SettingsGas.this);
		selectionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		selectionDialog.setContentView(R.layout.dialog_selectionmenu);
		selectionDialog.setTitle("Help");
		selectionDialog.setCancelable(true);
		
		addListenerOnButtons();
		
		Relative1 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative1);
		Relative2 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative2);
		Relative3 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative3);
		Relative4 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative4);
		Relative5 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative5);
		Relative6 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative6);
		Relative7 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative7);
		
		CheckBox = (ImageView) findViewById(R.id.checkBox);
		
		final ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		list.setDividerHeight(0);
		
		setSelections();
		
		// Menu Items
		listOfItems.add(new SettingsItem(true, false, false, VolumeName, VolumeDescription, VolumeValue));
		listOfItems.add(new SettingsItem(false, false, false, PressureName, PressureDescription, PressureValue));
		listOfItems.add(new SettingsItem(false, false, false, TemperatureName, TemperatureDescription, TemperatureValue));
		listOfItems.add(new SettingsItem(false, true, true, SameUnitName, SameUnitDescription, SameUnitValue));
		listOfItems.add(new SettingsItem(true, false, true, AutoSolveName, AutoSolveDescription, AutoSolveValue));
		listOfItems.add(new SettingsItem(false, true, true, FormatHelpName, FormatHelpDescription, FormatHelpValue));
		listOfItems.add(new SettingsItem(true, true, false, DecimalName, DecimalDescription, DecimalValue));
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				System.out.println("sadsfsf");
				Item = listOfItems.get(position).getItemName();
				Position = position;
				Check = listOfItems.get(position).isCheck();
				
				setVisibles();
				
				if (Check)
				{
					if (Item.equals(SameUnitName))
					{
						if (SameUnitValue.equals("false"))
						{
							MySingleton.getInstance().setGasSameUnits(true);
							Value = "true";
						}
						else if (SameUnitValue.equals("true"))
						{
							MySingleton.getInstance().setGasSameUnits(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, SameUnitName, SameUnitDescription, Value));
						adapter.notifyDataSetChanged();
						SameUnitValue = Value;
						savePreferences();
					}
					else if (Item.equals(AutoSolveName))
					{
						if (AutoSolveValue.equals("false"))
						{
							MySingleton.getInstance().setGasAutoSolve(true);
							Value = "true";
						}
						else if (AutoSolveValue.equals("true"))
						{
							MySingleton.getInstance().setGasAutoSolve(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(true, false, true, AutoSolveName, AutoSolveDescription, Value));
						adapter.notifyDataSetChanged();
						AutoSolveValue = Value;
						savePreferences();
					}
					else if (Item.equals(FormatHelpName))
					{
						if (FormatHelpValue.equals("false"))
						{
							MySingleton.getInstance().setGasFormatHelp(true);
							Value = "true";
						}
						else if (FormatHelpValue.equals("true"))
						{
							MySingleton.getInstance().setGasFormatHelp(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, FormatHelpName, FormatHelpDescription, Value));
						adapter.notifyDataSetChanged();
						FormatHelpValue = Value;
						savePreferences();
					}
				}
				else
					selectionDialog.show();
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent intent = new Intent(this, Settings.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void savePreferences()
	{
		defaultVolumeUnit = MySingleton.getInstance().getDefaultVolumeUnit();
		defaultPressureUnit = MySingleton.getInstance().getDefaultPressureUnit();
		defaultTempUnit = MySingleton.getInstance().getDefaultTempUnit();
		maxDecimalAccuracy = MySingleton.getInstance().getMaxDecimalAccuracy();
		Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
		
		settings = getSharedPreferences(SettingsTAG, 0);
		Editor editor = settings.edit();
		
		editor.putInt("selectVolume", defaultVolumeUnit);
		editor.putInt("selectPressure", defaultPressureUnit);
		editor.putInt("selectTemperature", defaultTempUnit);
		editor.putBoolean("GasSameUnits", Gas_SameUnits);
		editor.putBoolean("GasAutoSolve", Gas_AutoSolve);
		editor.putBoolean("GasFormatHelp", Gas_FormatHelp);
		editor.putString("selectedGasMaxDecimalAccuracy", maxDecimalAccuracy);
		
		editor.commit();
	}
	
	public void addListenerOnButtons()
	{
		selectionOne = (Button) selectionDialog.findViewById(R.id.Select1);
		selectionTwo = (Button) selectionDialog.findViewById(R.id.Select2);
		selectionThree = (Button) selectionDialog.findViewById(R.id.Select3);
		selectionFour = (Button) selectionDialog.findViewById(R.id.Select4);
		selectionFive = (Button) selectionDialog.findViewById(R.id.Select5);
		selectionSix = (Button) selectionDialog.findViewById(R.id.Select6);
		selectionSeven = (Button) selectionDialog.findViewById(R.id.Select7);
		
		selectionOne.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(VolumeName)) // (L) Liters
				{
					Value = "L";
					
					defaultVolumeUnit = 0;
					MySingleton.getInstance().setDefaultVolumeUnit(defaultVolumeUnit);
					defaultVol = "liters";
					MySingleton.getInstance().setDefaultVol(defaultVol);
					defaultVolS = "L";
					MySingleton.getInstance().setDefaultVolS(defaultVolS);
					selectedVolume = "Liters";
					MySingleton.getInstance().setSelectedVolume(selectedVolume);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, VolumeName, VolumeDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(PressureName)) // (atm) Atmospheres
				{
					Value = "atm";

					defaultPressureUnit = 0;
					MySingleton.getInstance().setDefaultPressureUnit(defaultPressureUnit);
					defaultPres = "atm";
					MySingleton.getInstance().setDefaultPres(defaultPres);
					defaultPresS = "atm";
					MySingleton.getInstance().setDefaultPresS(defaultPresS);
					selectedPressure = "Atm";
					MySingleton.getInstance().setSelectedPressure(selectedPressure);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, PressureName, PressureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(TemperatureName)) // (K) Kelvins
				{
					Value = "K";
					
					defaultTempUnit = 0;
					MySingleton.getInstance().setDefaultTempUnit(defaultTempUnit);
					defaultTemp = "kelvins";
					MySingleton.getInstance().setDefaultTemp(defaultTemp);
					defaultTempS = "K";
					MySingleton.getInstance().setDefaultTempS(defaultTempS);
					selectedTemperature = "Kel";
					MySingleton.getInstance().setSelectedTemperature(selectedTemperature);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, TemperatureName, TemperatureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (1) #.#
				{
					Value = "1";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionTwo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(VolumeName)) // (mL) Milliliters
				{
					Value = "mL";
					
					defaultVolumeUnit = 1;
					MySingleton.getInstance().setDefaultVolumeUnit(defaultVolumeUnit);
					defaultVol = "milliliters";
					MySingleton.getInstance().setDefaultVol(defaultVol);
					defaultVolS = "mL";
					MySingleton.getInstance().setDefaultVolS(defaultVolS);
					selectedVolume = "Milliliters";
					MySingleton.getInstance().setSelectedVolume(selectedVolume);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, VolumeName, VolumeDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(PressureName)) // (mmHg) Millimeter of
													// Mercury
				{
					Value = "mmHg";
					
					defaultPressureUnit = 1;
					MySingleton.getInstance().setDefaultPressureUnit(defaultPressureUnit);
					defaultPres = "mmHg";
					MySingleton.getInstance().setDefaultPres(defaultPres);
					defaultPresS = "mmHg";
					MySingleton.getInstance().setDefaultPresS(defaultPresS);
					selectedPressure = "mmHg";
					MySingleton.getInstance().setSelectedPressure(selectedPressure);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, PressureName, PressureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(TemperatureName)) // (C) Celcius
				{
					Value = "C";
					
					defaultTempUnit = 1;
					MySingleton.getInstance().setDefaultTempUnit(defaultTempUnit);
					defaultTemp = "celcius";
					MySingleton.getInstance().setDefaultTemp(defaultTemp);
					defaultTempS = "C";
					MySingleton.getInstance().setDefaultTempS(defaultTempS);
					selectedTemperature = "Cel";
					MySingleton.getInstance().setSelectedTemperature(selectedTemperature);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, TemperatureName, TemperatureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (2) #.##
				{
					Value = "2";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionThree.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(VolumeName)) // (g) Grams
				{
					Value = "g";
					
					defaultVolumeUnit = 2;
					MySingleton.getInstance().setDefaultVolumeUnit(defaultVolumeUnit);
					defaultVol = "grams";
					MySingleton.getInstance().setDefaultVol(defaultVol);
					defaultVolS = "g";
					MySingleton.getInstance().setDefaultVolS(defaultVolS);
					selectedVolume = "Grams";
					MySingleton.getInstance().setSelectedVolume(selectedVolume);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, VolumeName, VolumeDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(PressureName)) // (torr) Torr
				{
					Value = "torr";
					
					defaultPressureUnit = 2;
					MySingleton.getInstance().setDefaultPressureUnit(defaultPressureUnit);
					defaultPres = "torr";
					MySingleton.getInstance().setDefaultPres(defaultPres);
					defaultPresS = "torr";
					MySingleton.getInstance().setDefaultPresS(defaultPresS);
					selectedPressure = "Torr";
					MySingleton.getInstance().setSelectedPressure(selectedPressure);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, PressureName, PressureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(TemperatureName)) // (F) Fahrenheit
				{
					Value = "F";
					
					defaultTempUnit = 2;
					MySingleton.getInstance().setDefaultTempUnit(defaultTempUnit);
					defaultTemp = "fahrenheit";
					MySingleton.getInstance().setDefaultTemp(defaultTemp);
					defaultTempS = "F";
					MySingleton.getInstance().setDefaultTempS(defaultTempS);
					selectedTemperature = "Fah";
					MySingleton.getInstance().setSelectedTemperature(selectedTemperature);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, TemperatureName, TemperatureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (3) #.###
				{
					Value = "3";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionFour.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(PressureName)) // (kPa) Kilopascals
				{
					Value = "kPa";
					
					defaultPressureUnit = 3;
					MySingleton.getInstance().setDefaultPressureUnit(defaultPressureUnit);
					defaultPres = "kPa";
					MySingleton.getInstance().setDefaultPres(defaultPres);
					defaultPresS = "kPa";
					MySingleton.getInstance().setDefaultPresS(defaultPresS);
					selectedPressure = "kPa";
					MySingleton.getInstance().setSelectedPressure(selectedPressure);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(false, false, false, PressureName, PressureDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (4) #.####
				{
					Value = "4";
					
					MySingleton.getInstance().setMaxDecimalAccuracy(Value);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionFive.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				
				if (Item.equals(DecimalName)) // (5) #.#####
				{
					Value = "5";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionSix.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(DecimalName)) // (6) #.######
				{
					Value = "6";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionSeven.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(DecimalName)) // (7) #.#######
				{
					Value = "7";
					
					maxDecimalAccuracy = Value;
					MySingleton.getInstance().setMaxDecimalAccuracy(maxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
	}
	
	public void setVisibles()
	{
		if (Item.equals("Volume Units"))
		{
			selectionOne.setText("(L) Liters");
			selectionTwo.setText("(mL) Milliliters");
			selectionThree.setText("(g) Grams");
			selectionFour.setText("VALUE");
			selectionFive.setText("VALUE");
			selectionSix.setText("VALUE");
			selectionSeven.setText("VALUE");
			
			Relative1.setVisibility(View.VISIBLE);
			Relative2.setVisibility(View.VISIBLE);
			Relative3.setVisibility(View.VISIBLE);
			Relative4.setVisibility(View.GONE);
			Relative5.setVisibility(View.GONE);
			Relative6.setVisibility(View.GONE);
			Relative7.setVisibility(View.GONE);
		}
		else if (Item.equals("Pressure Units"))
		{
			selectionOne.setText("(atm) Atmospheres");
			selectionTwo.setText("(mmHg) Millimeter of Mercury");
			selectionThree.setText("(torr) Torr");
			selectionFour.setText("(kPa) Kilopascals");
			selectionFive.setText("VALUE");
			selectionSix.setText("VALUE");
			selectionSeven.setText("VALUE");
			
			Relative1.setVisibility(View.VISIBLE);
			Relative2.setVisibility(View.VISIBLE);
			Relative3.setVisibility(View.VISIBLE);
			Relative4.setVisibility(View.VISIBLE);
			Relative5.setVisibility(View.GONE);
			Relative6.setVisibility(View.GONE);
			Relative7.setVisibility(View.GONE);
		}
		else if (Item.equals("Temperature Units"))
		{
			selectionOne.setText("(K) Kelvins");
			selectionTwo.setText("(C) Celcius");
			selectionThree.setText("(F) Fahrenheit");
			selectionFour.setText("VALUE");
			selectionFive.setText("VALUE");
			selectionSix.setText("VALUE");
			selectionSeven.setText("VALUE");
			
			Relative1.setVisibility(View.VISIBLE);
			Relative2.setVisibility(View.VISIBLE);
			Relative3.setVisibility(View.VISIBLE);
			Relative4.setVisibility(View.GONE);
			Relative5.setVisibility(View.GONE);
			Relative6.setVisibility(View.GONE);
			Relative7.setVisibility(View.GONE);
		}
		else if (Item.equals("Decimal Accuracy"))
		{
			selectionOne.setText("(1)   #.#");
			selectionTwo.setText("(2)   #.##");
			selectionThree.setText("(3)   #.###");
			selectionFour.setText("(4)   #.####");
			selectionFive.setText("(5)   #.#####");
			selectionSix.setText("(6)   #.######");
			selectionSeven.setText("(7)   #.#######");
			
			Relative1.setVisibility(View.VISIBLE);
			Relative2.setVisibility(View.VISIBLE);
			Relative3.setVisibility(View.VISIBLE);
			Relative4.setVisibility(View.VISIBLE);
			Relative5.setVisibility(View.VISIBLE);
			Relative6.setVisibility(View.VISIBLE);
			Relative7.setVisibility(View.VISIBLE);
		}
	}
	
	public void setSelections()
	{
		selectedTemperature = MySingleton.getInstance().getSelectedTemperature();
		defaultTempUnit = MySingleton.getInstance().getDefaultTempUnit();
		selectedPressure = MySingleton.getInstance().getSelectedPressure();
		defaultPressureUnit = MySingleton.getInstance().getDefaultPressureUnit();
		selectedVolume = MySingleton.getInstance().getSelectedVolume();
		defaultVolumeUnit = MySingleton.getInstance().getDefaultVolumeUnit();
		maxDecimalAccuracy = MySingleton.getInstance().getMaxDecimalAccuracy();
		Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
		
		if (selectedVolume.equals("Liters") || defaultVolumeUnit == 0)
			VolumeValue = "L";
		else if (selectedVolume.equals("Milliliters") || defaultVolumeUnit == 1)
			VolumeValue = "mL";
		else if (selectedVolume.equals("Grams") || defaultVolumeUnit == 2)
			VolumeValue = "g";
		
		if (selectedPressure.equals("Atm") || defaultPressureUnit == 0)
			PressureValue = "atm";
		else if (selectedPressure.equals("mmHg") || defaultPressureUnit == 1)
			PressureValue = "mmHg";
		else if (selectedPressure.equals("Torr") || defaultPressureUnit == 2)
			PressureValue = "torr";
		else if (selectedPressure.equals("kPa") || defaultPressureUnit == 3)
			PressureValue = "kPa";
		
		if (selectedTemperature.equals("Kel") || defaultTempUnit == 0)
			TemperatureValue = "K";
		else if (selectedTemperature.equals("Cel") || defaultTempUnit == 1)
			TemperatureValue = "C";
		else if (selectedTemperature.equals("Fah") || defaultTempUnit == 2)
			TemperatureValue = "F";
		
		if(maxDecimalAccuracy.equals("1"))
			DecimalValue = "1";
		else if(maxDecimalAccuracy.equals("2"))
			DecimalValue = "2";
		else if(maxDecimalAccuracy.equals("3"))
			DecimalValue = "3";
		else if(maxDecimalAccuracy.equals("4"))
			DecimalValue = "4";
		else if(maxDecimalAccuracy.equals("5"))
			DecimalValue = "5";
		else if(maxDecimalAccuracy.equals("6"))
			DecimalValue = "6";
		else if(maxDecimalAccuracy.equals("7"))
			DecimalValue = "7";
		
		if(Gas_SameUnits)
			SameUnitValue = "true";
		else
			SameUnitValue = "false";
		
		if(Gas_AutoSolve)
			AutoSolveValue = "true";
		else
			AutoSolveValue = "false";
		
		if(Gas_FormatHelp)
			FormatHelpValue = "true";
		else
			FormatHelpValue = "false";
	}
	
	public void initializeGlobals()
	{
		defaultTempUnit = MySingleton.getInstance().getDefaultTempUnit();
		defaultTemp = MySingleton.getInstance().getDefaultTemp();
		defaultTempS = MySingleton.getInstance().getDefaultTempS();
		defaultPressureUnit = MySingleton.getInstance().getDefaultPressureUnit();
		defaultPres = MySingleton.getInstance().getDefaultPres();
		defaultPresS = MySingleton.getInstance().getDefaultPresS();
		defaultVolumeUnit = MySingleton.getInstance().getDefaultVolumeUnit();
		defaultVol = MySingleton.getInstance().getDefaultVol();
		defaultVolS = MySingleton.getInstance().getDefaultVolS();
		maxDecimalAccuracy = MySingleton.getInstance().getMaxDecimalAccuracy();
		selectedTemperature = MySingleton.getInstance().getSelectedTemperature();
		selectedPressure = MySingleton.getInstance().getSelectedPressure();
		selectedVolume = MySingleton.getInstance().getSelectedVolume();
		Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
	}
	
	public class SettingsGasAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<SettingsItem> listItem;

		public SettingsGasAdapter(Context context, List<SettingsItem> listItem)
		{
			this.context = context;
			this.listItem = listItem;
		}

		public int getCount()
		{
			return listItem.size();
		}

		public Object getItem(int position)
		{
			return listItem.get(position);
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup viewGroup)
		{
			SettingsItem entry = listItem.get(position);
			if (convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.settings_gasrow, null);
			}

			TextView Item = (TextView) convertView.findViewById(R.id.Item);
			Item.setText(entry.getItemName());

			TextView Description = (TextView) convertView
				.findViewById(R.id.Description);
			Description.setText(entry.getDescription());

			TextView Value = (TextView) convertView
				.findViewById(R.id.Value);
			Value.setText(entry.getValue());

			TextView Header = (TextView) convertView.findViewById(R.id.ListHeader);

			ImageView checkBox = (ImageView) convertView.findViewById(R.id.checkBox);

			View DividerRow = (View) convertView.findViewById(R.id.DividerRow);

			boolean isLast = entry.isLast();
			boolean isHeader = entry.isHeader();
			boolean isCheck = entry.isCheck();
			if(isCheck)
			{
				checkBox.setVisibility(View.VISIBLE);
				Value.setVisibility(View.GONE);

				if(entry.getValue().equals("false"))
					checkBox.setImageResource(R.drawable.checkbox_off);
				else if(entry.getValue().equals("true"))
					checkBox.setImageResource(R.drawable.checkbox_on);
			}
			else
			{
				checkBox.setVisibility(View.GONE);
				Value.setVisibility(View.VISIBLE);
			}
			if(isHeader)
			{
				Header.setVisibility(View.VISIBLE);
				if(entry.getItemName().equals("Volume Units"))
					Header.setText("Unit Preference");
				else if(entry.getItemName().equals("Auto Solve"))
					Header.setText("Smart Actions");
				else if(entry.getItemName().equals("Decimal Accuracy"))
					Header.setText("Accuracy");
			}
			else
				Header.setVisibility(View.GONE);

			if(isLast)
				DividerRow.setVisibility(View.GONE);
			else
				DividerRow.setVisibility(View.VISIBLE);

			return convertView;
		}

		@Override
		public void onClick(View view)
		{
			SettingsItem entry = (SettingsItem) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();

		}
	}
}
