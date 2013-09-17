package com.pk.chemhelp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class IdealGasLaw extends SherlockActivity
{
	/* Important Objects for Referencing and Problem Solving */
	protected DataStorage appState;
	final String PageID = "Ideal Gas Law";
	ErrorDetection ErrorDetect = new ErrorDetection();
	Laws Laws = new Laws();
	
	private boolean Exit;
	
	/* Declare Basic UI Objects */
	Spinner spinnerN;
	Spinner spinnerP;
	Spinner spinnerV;
	Spinner spinnerT;
	ArrayAdapter<CharSequence> adapterN;
	ArrayAdapter<CharSequence> adapterP;
	ArrayAdapter<CharSequence> adapterV;
	ArrayAdapter<CharSequence> adapterT;
	private EditText editTextN;
	private EditText editTextP;
	private EditText editTextV;
	private EditText editTextT;
	
	/* Declare and Instantiate Important Variable for Problem Solving */
	double N = MySingleton.getInstance().getN();
	double P = MySingleton.getInstance().getP();
	double V = MySingleton.getInstance().getV();
	double T = MySingleton.getInstance().getT();
	int lengthN;
	int lengthP;
	int lengthV;
	int lengthT;
	double Result;
	
	/* Store Text Field Values Here */
	String inputN;
	String inputP;
	String inputV;
	String inputT;
	
	/* Error Message */
	TextView errorTextView;
	String errorMessage = "";
	int Empty = -1;
	
	/* Set Up Dialog Box and Results */
	Dialog answerDialog;
	Dialog formulaDialog;
	TextView answerDialogTitle;
	TextView answerDialogResult;
	TextView answerWork;
	ImageView formulaImage;
	ScrollView answerDialogWork;
	Button formulaDialogClose;
	Button answerDialogClose;
	Button answerDialogShowWork;
	boolean showWork;
	String Work;
	
	/* Know What Units Are Selected */
	String SelectN;
	String SelectP;
	String SelectV;
	String SelectT;
	
	/* Extra Functions */
	boolean Gas_AutoSolve;
	boolean Gas_FormatHelp;
	boolean FILLED_N;
	boolean FILLED_P;
	boolean FILLED_V;
	boolean FILLED_T;
	
	/* Bookmark */
	String[] pageValues;
	String pageValue1;
	String pageValue2;
	String pageValue3;
	String pageValue4;
	String pageValue5;
	String pageValue6;
	String pageValue7;
	
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
		setContentView(R.layout.idealgaslaw);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Gas Laws");
		actionBar.setSubtitle("Ideal Gas Law");
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();

		pageValue1 = MySingleton.getInstance().getDefaultVolS();
		pageValue2 = MySingleton.getInstance().getDefaultPresS();
		pageValue3 = MySingleton.getInstance().getDefaultTempS();
		pageValue4 = "";
		pageValue5 = "";
		pageValue6 = "";
		pageValue7 = "";
		
		appState = (DataStorage) getApplication();
		addItemsOnSpinners();
		addListenerOnSpinnersItemSelection();
		
		editTextN = (EditText) findViewById(R.id.editTextN);
		editTextP = (EditText) findViewById(R.id.editTextP);
		editTextV = (EditText) findViewById(R.id.editTextV);
		editTextT = (EditText) findViewById(R.id.editTextT);
		
		errorTextView = (TextView) findViewById(R.id.errorMessage);
		
		formulaDialog = new Dialog(IdealGasLaw.this);
		formulaDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		formulaDialog.setContentView(R.layout.dialog_formula);
		formulaDialog.setCancelable(true);
		formulaImage = (ImageView) formulaDialog.findViewById(R.id.imageFormula);
		formulaImage.setImageResource(R.drawable.formula_ideal);
		Button formulaDialogClose = (Button) formulaDialog.findViewById(R.id.Close);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		answerDialog = new Dialog(IdealGasLaw.this);
		answerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		answerDialog.setContentView(R.layout.dialog_answer);
		answerDialog.setCancelable(true);
		answerDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		answerDialogTitle = (TextView) answerDialog.findViewById(R.id.Title);
		answerDialogResult = (TextView) answerDialog.findViewById(R.id.Answer);
		answerDialogClose = (Button) answerDialog.findViewById(R.id.Close);
		answerDialogShowWork = (Button) answerDialog.findViewById(R.id.ShowWork);
		answerDialogWork = (ScrollView) answerDialog.findViewById(R.id.WorkBox);
		answerWork = (TextView) answerDialog.findViewById(R.id.Work);
		showWork = false;
		Work = "";
		
		if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");

			setPageValues();
		}
		answerDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				answerDialog.dismiss();
			}
		});
		answerDialogShowWork.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				if(showWork)
				{
					showWork = false;
					answerDialogWork.setVisibility(View.GONE);
					answerDialogShowWork.setText("Show Work");
				}
				else
				{
					showWork = true;
					answerDialogWork.setVisibility(View.VISIBLE);
					answerDialogShowWork.setText("Hide Work");
				}
			}
		});
		formulaDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				formulaDialog.dismiss();
			}
		});
		
		Button formula = (Button) findViewById(R.id.Formula);
		Button submit = (Button) findViewById(R.id.Solve);
		Button clear = (Button) findViewById(R.id.Clear);
		formula.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				formulaDialog.show();
			}
			
		});
		clear.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				editTextN.setText("");
				editTextV.setText("");
				editTextP.setText("");
				editTextT.setText("");
			}
		});
		submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0)
			{
				inputN = editTextN.getText().toString();
				inputP = editTextP.getText().toString();
				inputV = editTextV.getText().toString();
				inputT = editTextT.getText().toString();
				lengthN = inputN.length();
				lengthP = inputP.length();
				lengthV = inputV.length();
				lengthT = inputT.length();
				N = -9876.5;
				P = -9876.5;
				V = -9876.5;
				T = -9876.5;
				if (lengthN != 0)
					N = Double.parseDouble(inputN);
				if (lengthP != 0)
					P = Double.parseDouble(inputP);
				if (lengthV != 0)
					V = Double.parseDouble(inputV);
				if (lengthT != 0)
					T = Double.parseDouble(inputT);
				
				errorTextView.setTextColor(0xFFFF0000);
				Empty = ErrorDetect.getEmptyFieldIdealGas(lengthN, lengthP, lengthV, lengthT);
				if (Empty == -1)
				{
					errorTextView.setText("Please leave the field you're solving for empty");
				}
				else if (Empty == 1) // Solving for N
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForN));
					Result = Laws.IdealGas_Law(N, P, V, T);
					Work = Laws.IdealGas_Law_Work(N, P, V, T);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectN);
					answerDialog.show();
				}
				else if (Empty == 2) // Solving for P
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForP));
					Result = Laws.IdealGas_Law(N, P, V, T);
					Work = Laws.IdealGas_Law_Work(N, P, V, T);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectP);
					answerDialog.show();
				}
				else if (Empty == 3) // Solving for V
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForV));
					Result = Laws.IdealGas_Law(N, P, V, T);
					Work = Laws.IdealGas_Law_Work(N, P, V, T);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectV);
					answerDialog.show();
				}
				else if (Empty == 4) // Solving for T
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForT));
					Result = Laws.IdealGas_Law(N, P, V, T);
					Work = Laws.IdealGas_Law_Work(N, P, V, T);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectT);
					answerDialog.show();
				}
				else
				// Error Messages
				{
					errorMessage = ErrorDetect.detectErrorIdealGas(Empty);
					errorTextView.setText(errorMessage);
				}
			}
		});
		
		editTextN.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextN.getText().toString();
				if (text.length() > 0)
					FILLED_N = true;
				else
					FILLED_N = false;
				checkInputs();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String text = editTextN.getText().toString();
				if (text.length() > 0)
					FILLED_N = true;
				else
					FILLED_N = false;
				checkInputs();
			}
			
		});
		editTextV.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextV.getText().toString();
				if (text.length() > 0)
					FILLED_V = true;
				else
					FILLED_V = false;
				checkInputs();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String text = editTextV.getText().toString();
				if (text.length() > 0)
					FILLED_V = true;
				else
					FILLED_V = false;
				checkInputs();
			}
			
		});
		editTextP.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextP.getText().toString();
				if (text.length() > 0)
					FILLED_P = true;
				else
					FILLED_P = false;
				checkInputs();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String text = editTextP.getText().toString();
				if (text.length() > 0)
					FILLED_P = true;
				else
					FILLED_P = false;
				checkInputs();
			}
			
		});
		editTextT.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextT.getText().toString();
				if (text.length() > 0)
					FILLED_T = true;
				else
					FILLED_T = false;
				checkInputs();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String text = editTextT.getText().toString();
				if (text.length() > 0)
					FILLED_T = true;
				else
					FILLED_T = false;
				checkInputs();
			}
		});
	}
	
	public void checkInputs()
	{
		String EmptyField;
		EmptyField = checkEmpty(FILLED_N, FILLED_V, FILLED_P, FILLED_T);

		if(Gas_FormatHelp)
		{
			if(EmptyField.equals("N"))
			{
				editTextN.setEnabled(false);
				editTextN.setFocusable(false);
				editTextV.setEnabled(true);
				editTextV.setFocusable(true);
				editTextP.setEnabled(true);
				editTextP.setFocusable(true);
				editTextT.setEnabled(true);
				editTextT.setFocusable(true);

				editTextN.setHint(getString(R.string.GASLAW_SolvingForN));
				editTextV.setHint(getString(R.string.blankForV));
				editTextP.setHint(getString(R.string.blankForP));
				editTextT.setHint(getString(R.string.blankForT));
			}
			else if(EmptyField.equals("V"))
			{
				editTextN.setEnabled(true);
				editTextN.setFocusable(true);
				editTextV.setEnabled(false);
				editTextV.setFocusable(false);
				editTextP.setEnabled(true);
				editTextP.setFocusable(true);
				editTextT.setEnabled(true);
				editTextT.setFocusable(true);

				editTextN.setHint(getString(R.string.blankForN));
				editTextV.setHint(getString(R.string.GASLAW_SolvingForV));
				editTextP.setHint(getString(R.string.blankForP));
				editTextT.setHint(getString(R.string.blankForT));
			}
			else if(EmptyField.equals("P"))
			{
				editTextN.setEnabled(true);
				editTextN.setFocusable(true);
				editTextV.setEnabled(true);
				editTextV.setFocusable(true);
				editTextP.setEnabled(false);
				editTextP.setFocusable(false);
				editTextT.setEnabled(true);
				editTextT.setFocusable(true);

				editTextN.setHint(getString(R.string.blankForN));
				editTextV.setHint(getString(R.string.blankForV));
				editTextP.setHint(getString(R.string.GASLAW_SolvingForP));
				editTextT.setHint(getString(R.string.blankForT));
			}
			else if(EmptyField.equals("T"))
			{
				editTextN.setEnabled(true);
				editTextN.setFocusable(true);
				editTextV.setEnabled(true);
				editTextV.setFocusable(true);
				editTextP.setEnabled(true);
				editTextP.setFocusable(true);
				editTextT.setEnabled(false);
				editTextT.setFocusable(false);

				editTextN.setHint(getString(R.string.blankForN));
				editTextV.setHint(getString(R.string.blankForV));
				editTextP.setHint(getString(R.string.blankForP));
				editTextT.setHint(getString(R.string.GASLAW_SolvingForT));
			}
			else
			{
				editTextN.setEnabled(true);
				editTextN.setFocusable(true);
				editTextV.setEnabled(true);
				editTextV.setFocusable(true);
				editTextP.setEnabled(true);
				editTextP.setFocusable(true);
				editTextT.setEnabled(true);
				editTextT.setFocusable(true);

				editTextN.setHint(getString(R.string.blankForN));
				editTextV.setHint(getString(R.string.blankForV));
				editTextP.setHint(getString(R.string.blankForP));
				editTextT.setHint(getString(R.string.blankForT));
			}
		}
		if(Gas_AutoSolve)
		{
			Result = -1;
			inputN = editTextN.getText().toString();
			inputV = editTextV.getText().toString();
			inputP = editTextP.getText().toString();
			inputT = editTextT.getText().toString();
			lengthN = inputN.length();
			lengthV = inputV.length();
			lengthP = inputP.length();
			lengthT = inputT.length();
			N = -9876.5;
			V = -9876.5;
			P = -9876.5;
			T = -9876.5;
			if (lengthN != 0)
				N = Double.parseDouble(inputN);
			if (lengthV != 0)
				V = Double.parseDouble(inputV);
			if (lengthP != 0)
				P = Double.parseDouble(inputP);
			if (lengthT != 0)
				T = Double.parseDouble(inputT);
			
			if(EmptyField.equals("N"))
			{
				errorTextView.setText("");
				editTextN.setHintTextColor(Color.BLUE);
				Result = Laws.IdealGas_Law(N, P, V, T);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextN.setHint("" + Result);
			}
			else if(EmptyField.equals("V"))
			{
				errorTextView.setText("");
				editTextV.setHintTextColor(Color.BLUE);
				Result = Laws.IdealGas_Law(N, P, V, T);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextV.setHint("" + Result);
			}
			else if(EmptyField.equals("P"))
			{
				errorTextView.setText("");
				editTextP.setHintTextColor(Color.BLUE);
				Result = Laws.IdealGas_Law(N, P, V, T);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextP.setHint("" + Result);
			}
			else if(EmptyField.equals("T"))
			{
				errorTextView.setText("");
				editTextT.setHintTextColor(Color.BLUE);
				Result = Laws.IdealGas_Law(N, P, V, T);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextT.setHint("" + Result);
			}
			else
			{
				editTextN.setHintTextColor(Color.GRAY);
				editTextV.setHintTextColor(Color.GRAY);
				editTextP.setHintTextColor(Color.GRAY);
				editTextT.setHintTextColor(Color.GRAY);
				
				editTextN.setHint(getString(R.string.blankForN));
				editTextV.setHint(getString(R.string.blankForV));
				editTextP.setHint(getString(R.string.blankForP));
				editTextT.setHint(getString(R.string.blankForT));
			}
		}
	}
	
	public String checkEmpty(boolean FILLED_N, boolean FILLED_V, boolean FILLED_P, boolean FILLED_T)
	{
		if(FILLED_V && FILLED_P && FILLED_T)
			return "N";
		else if(FILLED_N && FILLED_P && FILLED_T)
			return "V";
		else if(FILLED_N && FILLED_V && FILLED_T)
			return "P";
		else if(FILLED_N && FILLED_V && FILLED_P)
			return "T";
		else
			return "";
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
				Intent intent = new Intent(this, GasLaws.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				pageValue4 = editTextN.getText().toString();
				pageValue5 = editTextV.getText().toString();
				pageValue6 = editTextP.getText().toString();
				pageValue7 = editTextT.getText().toString();
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", IdealGasLaw.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", IdealGasLaw.this).show();
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
				Intent i = new Intent(IdealGasLaw.this, Debug.class);
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
	
	public void addItemsOnSpinners()
	{
		// Get Position Defaults
		int spinnerPosition;
		String defaultVolUnit = MySingleton.getInstance().getDefaultVolS();
		String defaultPresUnit = MySingleton.getInstance().getDefaultPresS();
		String defaultTempUnit = MySingleton.getInstance().getDefaultTempS();
		
		// Initialize Spinners and Adapters. Set Default If Necessary.
		spinnerN = (Spinner) findViewById(R.id.spinnerN);
		adapterN = ArrayAdapter.createFromResource(this, R.array.UnitMole_array, android.R.layout.simple_spinner_item);
		adapterN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerN.setAdapter(adapterN);
		
		spinnerP = (Spinner) findViewById(R.id.spinnerP);
		adapterP = ArrayAdapter.createFromResource(this, R.array.UnitPressure_array, android.R.layout.simple_spinner_item);
		adapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerP.setAdapter(adapterP);
		spinnerPosition = adapterP.getPosition(defaultPresUnit);
		spinnerP.setSelection(spinnerPosition);
		
		spinnerV = (Spinner) findViewById(R.id.spinnerV);
		adapterV = ArrayAdapter.createFromResource(this, R.array.UnitVolume_array, android.R.layout.simple_spinner_item);
		adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerV.setAdapter(adapterV);
		spinnerPosition = adapterV.getPosition(defaultVolUnit);
		spinnerV.setSelection(spinnerPosition);
		
		spinnerT = (Spinner) findViewById(R.id.spinnerT);
		adapterT = ArrayAdapter.createFromResource(this, R.array.UnitTemperature_array, android.R.layout.simple_spinner_item);
		adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerT.setAdapter(adapterT);
		spinnerPosition = adapterT.getPosition(defaultTempUnit);
		spinnerT.setSelection(spinnerPosition);
	}
	
	public void addListenerOnSpinnersItemSelection()
	{
		spinnerN.setOnItemSelectedListener(new OnNSelectedListener());
		spinnerP.setOnItemSelectedListener(new OnPSelectedListener());
		spinnerV.setOnItemSelectedListener(new OnVSelectedListener());
		spinnerT.setOnItemSelectedListener(new OnTSelectedListener());
	}
	
	public class OnNSelectedListener implements Spinner.OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			SelectN = parent.getItemAtPosition(pos).toString();
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
		}
	}
	
	public class OnPSelectedListener implements Spinner.OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			SelectP = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectP(SelectP);
			pageValue1 = SelectP;
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
		}
	}
	
	public class OnVSelectedListener implements Spinner.OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			SelectV = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectV(SelectV);
			pageValue2 = SelectV;
		}
		
		@SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
		{
			// Do nothing.
		}
	}
	
	public class OnTSelectedListener implements Spinner.OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{
			SelectT = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectT(SelectT);
			pageValue3 = SelectT;
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
		values[6] = pageValue6;
		values[7] = pageValue7;

		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			int spinnerPosition;

			spinnerPosition = adapterV.getPosition(pageValues[1]);
			spinnerV.setSelection(spinnerPosition);
			SelectV = pageValues[1];
			MySingleton.getInstance().setSelectV1(SelectV);
			pageValue1 = SelectV;

			spinnerPosition = adapterP.getPosition(pageValues[2]);
			spinnerP.setSelection(spinnerPosition);
			SelectP = pageValues[2];
			MySingleton.getInstance().setSelectP1(SelectP);
			pageValue2 = SelectP;

			spinnerPosition = adapterT.getPosition(pageValues[3]);
			spinnerT.setSelection(spinnerPosition);
			SelectT = pageValues[3];
			MySingleton.getInstance().setSelectV2(SelectT);
			pageValue3 = SelectT;

			editTextN.setText(pageValues[4]);
			editTextV.setText(pageValues[5]);
			editTextP.setText(pageValues[6]);
			editTextT.setText(pageValues[7]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(IdealGasLaw.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
