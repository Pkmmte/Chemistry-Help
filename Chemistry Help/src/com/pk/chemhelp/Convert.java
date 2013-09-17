package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class Convert extends SherlockActivity
{
	/* Important Objects for Referencing and Problem Solving */
	protected DataStorage appState;
	final String PageID = "Convert";
	Conversions Convert = new Conversions();
	
	/* Exit */
	private boolean Exit = MySingleton.getInstance().getExit();
	
	/* Problem Solving */
	String convertionInput = "";
	String convertionOutput = "";
	double temperature = 0;
	double volume = 0;
	double pressure = 0;
	String volumeInput = "";
	
	/* Detecting Different Categories */
	String category = MySingleton.getInstance().getCategory();
	int subcategory = MySingleton.getInstance().getSubcategory();
	int subcategory2 = 0;
	
	// Declare Spinners and adapters
	Spinner convertTopic;
	Spinner inputUnit;
	Spinner outputUnit;
	ArrayAdapter<CharSequence> topicAdapter;
	ArrayAdapter<CharSequence> inputAdapter;
	ArrayAdapter<CharSequence> outputAdapter;
	ArrayAdapter<CharSequence> tempAdapter;
	ArrayAdapter<CharSequence> presAdapter;
	ArrayAdapter<CharSequence> volAdapter;
	private EditText convertInput;
	private EditText convertOutput;
	
	/* Extra Functions */
	String Convert_DefaultCategory;
	String Convert_MaxDecimalAccuracy;
	boolean Convert_AutoSolve;
	boolean Convert_FormatHelp;
	
	/* Bookmarks */

	String[] pageValues;
	String pageValue1;
	String pageValue2;
	String pageValue3;
	String pageValue4;
	String pageValue5;
	
	// Sad workaround for this activity...
	boolean loadingValues;
	
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
		setContentView(R.layout.convert);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		loadingValues = false;
		
		Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
		Convert_MaxDecimalAccuracy = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
		Convert_AutoSolve = MySingleton.getInstance().getConvertAutoSolve();
		Convert_FormatHelp = MySingleton.getInstance().getConvertFormatHelp();
		
		pageValue1 = "";
		pageValue2 = "";
		pageValue3 = "";
		pageValue4 = "";
		pageValue5 = "";
		
		appState = (DataStorage) getApplication();
		convertInput = (EditText) findViewById(R.id.convertInput);
		convertOutput = (EditText) findViewById(R.id.convertOutput);
		
		if (Convert_FormatHelp)
		{
			convertOutput.setEnabled(false);
			convertOutput.setFocusable(false);
		}
		else
		{
			convertOutput.setEnabled(true);
			convertOutput.setFocusable(true);
		}
		
		addItemsOnSpinner1();
		addListenerOnSpinnerItemSelection();
		addItemsOnSpinner2();
		addListenerOnSpinner1ItemSelection();
		addItemsOnSpinner3();
		addListenerOnSpinner2ItemSelection();
		createSubAdapters();
		setSelections();
		
		Button convert = (Button) findViewById(R.id.Convert);
		Button swap = (Button) findViewById(R.id.Swap);
		Button clear = (Button) findViewById(R.id.Clear);
		
		if (Convert_AutoSolve)
			convert.setVisibility(View.GONE);
		else
			convert.setVisibility(View.VISIBLE);
		
		if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");
			
			setPageValues();
		}
		
		convert.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0)
			{
				convertionInput = convertInput.getText().toString().replaceAll("\\s","").replaceAll(",$", "");
				String outUnit = MySingleton.getInstance().getOutputUnit();
				
				if (convertionInput.length() > 0)
				{
					temperature = Double.parseDouble(convertionInput);
					temperature = Misc.Convert(temperature);
					temperature = Misc.ConvertDecimalPrecisionAssign(temperature);
					convertOutput.setText(Double.toString(temperature) + " " + outUnit);
				}
			}
		});
		
		swap.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0)
			{
				subcategory2 = MySingleton.getInstance().getSubcategory2();
				subcategory = MySingleton.getInstance().getSubcategory();
				outputUnit.setSelection(subcategory);
				inputUnit.setSelection(subcategory2);
				MySingleton.getInstance().setSubcategory2(subcategory);
				MySingleton.getInstance().setSubcategory(subcategory2);
				
				String outUnit = MySingleton.getInstance().getOutputUnit();
				
				convertionInput = convertInput.getText().toString();
				convertionOutput = convertOutput.getText().toString();
				
				convertOutput.setText(convertionInput);
				convertionOutput = convertionOutput.substring(0, convertionOutput.length() - outUnit.length());
				convertInput.setText(convertionOutput);
				
			}
		});
		
		clear.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0)
			{
				convertInput.setText("");
				convertOutput.setText("");
			}
		});
		
		convertInput.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				// Do nothing...
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				onInput();
			}
		});
		
		convertOutput.setOnClickListener(new OnClickListener()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View view)
			{
				String text = convertOutput.getText().toString();
				
				if (text.length() > 0)
				{
					int sdk = android.os.Build.VERSION.SDK_INT;
					if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB)
					{
						android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						clipboard.setText(text);
					}
					else
					{
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("text label", text);
						clipboard.setPrimaryClip(clip);
					}
					
					Toast.makeText(Convert.this, "Copied to clipboard: \n\"" + text + "\"", Toast.LENGTH_LONG).show();
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
				pageValue4 = convertInput.getText().toString();
				pageValue5 = convertOutput.getText().toString();
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", Convert.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", Convert.this).show();
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
				Intent i = new Intent(Convert.this, Debug.class);
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
	
	public void onInput()
	{
		convertionInput = convertInput.getText().toString().replaceAll("\\s","").replaceAll(",$", "");
		String outUnit = MySingleton.getInstance().getOutputUnit();
		
		if (Convert_AutoSolve)
		{
			if (convertionInput.length() > 0)
			{
				temperature = Double.parseDouble(convertionInput);
				temperature = Misc.Convert(temperature);
				temperature = Misc.ConvertDecimalPrecisionAssign(temperature);
				convertOutput.setText(Double.toString(temperature) + " " + outUnit);
			}
		}
		
		if (Convert_FormatHelp)
		{
			convertOutput.setEnabled(false);
			convertOutput.setFocusable(false);
		}
		else
		{
			convertOutput.setEnabled(true);
			convertOutput.setFocusable(true);
		}
	}
	
	public void createSubAdapters()
	{
		// Temperature
		tempAdapter = ArrayAdapter.createFromResource(this, R.array.UnitTemperature_array, android.R.layout.simple_spinner_item);
		tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Pressure
		presAdapter = ArrayAdapter.createFromResource(this, R.array.UnitPressure_array, android.R.layout.simple_spinner_item);
		presAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Volume
		volAdapter = ArrayAdapter.createFromResource(this, R.array.UnitVolume_array, android.R.layout.simple_spinner_item);
		volAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	
	public void addItemsOnSpinner1()
	{
		// Category Info
		convertTopic = (Spinner) findViewById(R.id.convertTopic);
		topicAdapter = ArrayAdapter.createFromResource(this, R.array.Topic_array, android.R.layout.simple_spinner_item);
		topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		convertTopic.setAdapter(topicAdapter);
		
		category = MySingleton.getInstance().getCategory();
	}
	
	public void addListenerOnSpinnerItemSelection()
	{
		convertTopic.setOnItemSelectedListener(new OnTopicSelectedListener());
	}
	
	public void addListenerOnSpinner1ItemSelection()
	{
		inputUnit.setOnItemSelectedListener(new OnSubSelectedListener());
	}
	
	public void addListenerOnSpinner2ItemSelection()
	{
		outputUnit.setOnItemSelectedListener(new OnSub2SelectedListener());
	}
	
	public void addItemsOnSpinner2()
	{
		// First Subcategory Info
		inputUnit = (Spinner) findViewById(R.id.inputUnit);
		inputAdapter = ArrayAdapter.createFromResource(this, R.array.blank_array, android.R.layout.simple_spinner_item);
		inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		inputUnit.setAdapter(inputAdapter);
	}
	
	public void addItemsOnSpinner3()
	{
		// Second Subcategory Info
		outputUnit = (Spinner) findViewById(R.id.outputUnit);
		outputAdapter = ArrayAdapter.createFromResource(this, R.array.blank_array, android.R.layout.simple_spinner_item);
		outputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		outputUnit.setAdapter(outputAdapter);
	}
	
	public void setSelections()
	{
		// Get Position Defaults
		int spinnerPosition;
		Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
		//int DefaultC;
		//
		//if(Convert_DefaultCategory.equals("Blank"))
		//	Default = 0;
		// Set Defaults
		spinnerPosition = topicAdapter.getPosition(Convert_DefaultCategory);
		convertTopic.setSelection(spinnerPosition);
	}
	
	public class OnTopicSelectedListener implements
			Spinner.OnItemSelectedListener
	{
		String category = MySingleton.getInstance().getCategory();
		
		// protected DataStorage appState;
		
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			category = parent.getItemAtPosition(pos).toString();
			pageValue1 = category;
			MySingleton.getInstance().setCategory(category);
			if (category.equals("Temperature") && !loadingValues)
			{
				inputUnit.setAdapter(tempAdapter);
				outputUnit.setAdapter(tempAdapter);
			}
			else if (category.equals("Pressure") && !loadingValues)
			{
				inputUnit.setAdapter(presAdapter);
				outputUnit.setAdapter(presAdapter);
			}
			else if (category.equals("Volume") && !loadingValues)
			{
				inputUnit.setAdapter(volAdapter);
				outputUnit.setAdapter(volAdapter);
			}
			else if (category.toString().length() == 0)
			{
				inputUnit.setAdapter(inputAdapter);
				outputUnit.setAdapter(outputAdapter);
			}
			
			loadingValues = false;
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
		}
	}
	
	public class OnSubSelectedListener implements
			Spinner.OnItemSelectedListener
	{
		String category = MySingleton.getInstance().getCategory();
		int subcategory = MySingleton.getInstance().getSubcategory();
		
		String sub = "";
		
		// protected DataStorage appState;
		
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			if (parent.getItemAtPosition(pos).toString().length() != 0)
			{
				sub = parent.getItemAtPosition(pos).toString();
				pageValue2 = sub;
				subcategory = Misc.getUnitValue(sub);
				MySingleton.getInstance().setSubcategory(subcategory);
				onInput();
			}
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
		}
	}
	
	public class OnSub2SelectedListener implements
			Spinner.OnItemSelectedListener
	{
		String category = MySingleton.getInstance().getCategory();
		int subcategory2 = MySingleton.getInstance().getSubcategory2();
		
		String sub = "";
		
		// protected DataStorage appState;
		
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			if (parent.getItemAtPosition(pos).toString().length() != 0)
			{
				sub = parent.getItemAtPosition(pos).toString();
				pageValue3 = sub;
				subcategory2 = Misc.getUnitValue(sub);
				MySingleton.getInstance().setOutputUnit(sub);
				MySingleton.getInstance().setSubcategory2(subcategory2);
				onInput();
			}
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
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
		values[3] = pageValue3;
		values[4] = pageValue4;
		values[5] = pageValue5;
		
		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			loadingValues = true;
			
			int spinnerPosition;
			String topicPosition = pageValues[1];
			String sub1Position = pageValues[2];
			String sub2Position= pageValues[3];
			
			spinnerPosition = topicAdapter.getPosition(topicPosition);
			convertTopic.setSelection(spinnerPosition);
			
			if(topicPosition.equals("Temperature"))
			{
				inputUnit.setAdapter(tempAdapter);
				outputUnit.setAdapter(tempAdapter);
				
				spinnerPosition = tempAdapter.getPosition(sub1Position);
				inputUnit.setSelection(spinnerPosition);
				
				spinnerPosition = tempAdapter.getPosition(sub2Position);
				outputUnit.setSelection(spinnerPosition);
			}
			else if (topicPosition.equals("Pressure"))
			{
				inputUnit.setAdapter(presAdapter);
				outputUnit.setAdapter(presAdapter);

				spinnerPosition = presAdapter.getPosition(sub1Position);
				inputUnit.setSelection(spinnerPosition);

				spinnerPosition = presAdapter.getPosition(sub2Position);
				outputUnit.setSelection(spinnerPosition);
			}
			else if (topicPosition.equals("Volume"))
			{
				inputUnit.setAdapter(volAdapter);
				outputUnit.setAdapter(volAdapter);

				spinnerPosition = volAdapter.getPosition(sub1Position);
				inputUnit.setSelection(spinnerPosition);

				spinnerPosition = volAdapter.getPosition(sub2Position);
				outputUnit.setSelection(spinnerPosition);
			}
			else if (topicPosition.toString().length() == 0)
			{
				inputUnit.setAdapter(inputAdapter);
				outputUnit.setAdapter(outputAdapter);
			}

			convertInput.setText(pageValues[4]);
			convertOutput.setText(pageValues[5]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(Convert.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
