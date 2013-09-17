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

public class GayLussacsLaw extends SherlockActivity
{
	/* Important Objects for Referencing and Problem Solving */
	protected DataStorage appState;
	final String PageID = "Gay Lussac's Law";
	ErrorDetection ErrorDetect = new ErrorDetection();
	Laws Laws = new Laws();
	
	/* Exit */
	private boolean Exit;
	
	/* Declare Basic UI Objects */
	Spinner spinnerP1;
    Spinner spinnerT1;
    Spinner spinnerP2;
    Spinner spinnerT2;
	ArrayAdapter<CharSequence> adapterP1;
    ArrayAdapter<CharSequence> adapterT1;
    ArrayAdapter<CharSequence> adapterP2;
    ArrayAdapter<CharSequence> adapterT2;
    private EditText editTextP1;
    private EditText editTextT1;
    private EditText editTextP2;
    private EditText editTextT2;
    
    /* Declare and Instantiate Important Variable for Problem Solving */
    double P1 = MySingleton.getInstance().getP1();
    double P2 = MySingleton.getInstance().getP2();
    double T1 = MySingleton.getInstance().getT1();
    double T2 = MySingleton.getInstance().getT2();
    int lengthP1;
    int lengthP2;
    int lengthT1;
    int lengthT2;
	double Result;
    
    /* Store Text Field Values Here */
    String inputP1;
    String inputP2;
    String inputT1;
    String inputT2;
    
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
	String SelectP1;
	String SelectP2;
	String SelectT1;
	String SelectT2;
	
	/* Extra Functions */
	boolean Gas_SameUnits;
	boolean Gas_AutoSolve;
	boolean Gas_FormatHelp;
	boolean FILLED_P1;
	boolean FILLED_P2;
	boolean FILLED_T1;
	boolean FILLED_T2;
	
	/* Bookmark */
	String[] pageValues;
	String pageValue1;
	String pageValue2;
	String pageValue3;
	String pageValue4;
	String pageValue5;
	String pageValue6;
	String pageValue7;
	String pageValue8;
	
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
		setContentView(R.layout.gaylussacslaw);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Gas Laws");
		actionBar.setSubtitle("Gay Lussac's Law");
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		
		Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
		
		pageValue1 = MySingleton.getInstance().getDefaultPresS();
		pageValue2 = MySingleton.getInstance().getDefaultTempS();
		pageValue3 = MySingleton.getInstance().getDefaultPresS();
		pageValue4 = MySingleton.getInstance().getDefaultTempS();
		pageValue5 = "";
		pageValue6 = "";
		pageValue7 = "";
		pageValue8 = "";
		
		appState = (DataStorage)getApplication();
		addItemsOnSpinners();
		addListenerOnSpinnersItemSelection();

		editTextP1 = (EditText)findViewById(R.id.editTextP1);
		editTextT1 = (EditText)findViewById(R.id.editTextT1);
		editTextP2 = (EditText)findViewById(R.id.editTextP2);
		editTextT2 = (EditText)findViewById(R.id.editTextT2);

		errorTextView = (TextView)findViewById(R.id.errorMessage);

		formulaDialog = new Dialog(GayLussacsLaw.this);
		formulaDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		formulaDialog.setContentView(R.layout.dialog_formula);
		formulaDialog.setCancelable(true);
		formulaImage = (ImageView) formulaDialog.findViewById(R.id.imageFormula);
		formulaImage.setImageResource(R.drawable.formula_gaylussac);
		Button formulaDialogClose = (Button) formulaDialog.findViewById(R.id.Close);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		answerDialog = new Dialog(GayLussacsLaw.this);
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
				editTextP1.setText("");
				editTextP2.setText("");
				editTextT1.setText("");
				editTextT2.setText("");
			}
		});
		submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0)
			{
				inputP1 = editTextP1.getText().toString();
				inputP2 = editTextP2.getText().toString();
				inputT1 = editTextT1.getText().toString();
				inputT2 = editTextT2.getText().toString();
				lengthP1 = inputP1.length();
				lengthP2 = inputP2.length();
				lengthT1 = inputT1.length();
				lengthT2 = inputT2.length();
				P1 = -9876.5;
				P2 = -9876.5;
				T1 = -9876.5;
				T2 = -9876.5;
				if(lengthP1 != 0)
					P1 = Double.parseDouble(inputP1);
				if(lengthP2 != 0)
					P2 = Double.parseDouble(inputP2);
				if(lengthT1 != 0)
					T1 = Double.parseDouble(inputT1);
				if(lengthT2 != 0)
					T2 = Double.parseDouble(inputT2);
				
				errorTextView.setTextColor(0xFFFF0000);
				Empty = ErrorDetect.getEmptyFieldGayLussacs(lengthP1, lengthT1, lengthP2, lengthT2);
				if(Empty == -1) // If user filled out everything for some reason
				{
					errorTextView.setText("Please leave the field you're solving for empty");
				}
				else if(Empty == 1) // Solving for P1
				{
					// Do calculations later
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForP1));
					Result = Laws.GayLussac_Law(P1, T1, P2, T2);
					Work = Laws.GayLussac_Law_Work(P1, T1, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectP1);
					answerDialog.show();
				}
				else if(Empty == 2) // Solving for T1
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForT1));
					Result = Laws.GayLussac_Law(P1, T1, P2, T2);
					Work = Laws.GayLussac_Law_Work(P1, T1, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectT1);
					answerDialog.show();
				}
				else if(Empty == 3) // Solving for P2
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForP2));
					Result = Laws.GayLussac_Law(P1, T1, P2, T2);
					Work = Laws.GayLussac_Law_Work(P1, T1, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectP2);
					answerDialog.show();
				}
				else if(Empty == 4) // Solving for T2
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForT2));
					Result = Laws.GayLussac_Law(P1, T1, P2, T2);
					Work = Laws.GayLussac_Law_Work(P1, T1, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectT2);
					answerDialog.show();
				}
				else // Error Messages
				{
					errorMessage = ErrorDetect.detectErrorGayLussacs(Empty);
					errorTextView.setText(errorMessage);
				}
			}
		});
		
		editTextP1.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextP1.getText().toString();
				if (text.length() > 0)
					FILLED_P1 = true;
				else
					FILLED_P1 = false;
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
				String text = editTextP1.getText().toString();
				if (text.length() > 0)
					FILLED_P1 = true;
				else
					FILLED_P1 = false;
				checkInputs();
			}
			
		});
		editTextP2.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextP2.getText().toString();
				if (text.length() > 0)
					FILLED_P2 = true;
				else
					FILLED_P2 = false;
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
				String text = editTextP2.getText().toString();
				if (text.length() > 0)
					FILLED_P2 = true;
				else
					FILLED_P2 = false;
				checkInputs();
			}
			
		});
		editTextT1.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextT1.getText().toString();
				if (text.length() > 0)
					FILLED_T1 = true;
				else
					FILLED_T1 = false;
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
				String text = editTextT1.getText().toString();
				if (text.length() > 0)
					FILLED_T1 = true;
				else
					FILLED_T1 = false;
				checkInputs();
			}
			
		});
		editTextT2.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextT2.getText().toString();
				if (text.length() > 0)
					FILLED_T2 = true;
				else
					FILLED_T2 = false;
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
				String text = editTextT2.getText().toString();
				if (text.length() > 0)
					FILLED_T2 = true;
				else
					FILLED_T2 = false;
				checkInputs();
			}
		});
	}
	
	public void onRestart()
	{
		super.onRestart();
		
		Exit = MySingleton.getInstance().getExit();
		if(Exit)
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
				Intent intent = new Intent(this, GasLaws.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				pageValue5 = editTextP1.getText().toString();
				pageValue6 = editTextT1.getText().toString();
				pageValue7 = editTextP2.getText().toString();
				pageValue8 = editTextT2.getText().toString();
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", GayLussacsLaw.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", GayLussacsLaw.this).show();
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
				Intent i = new Intent(GayLussacsLaw.this, Debug.class);
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
	
	public void checkInputs()
	{
		String EmptyField;
		EmptyField = checkEmpty(FILLED_P1, FILLED_P2, FILLED_T1, FILLED_T2);

		if(Gas_FormatHelp)
		{
			if(EmptyField.equals("P1"))
			{
				editTextP1.setEnabled(false);
				editTextP1.setFocusable(false);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextP1.setHint(getString(R.string.GASLAW_SolvingForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("P2"))
			{
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(false);
				editTextP2.setFocusable(false);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.GASLAW_SolvingForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("T1"))
			{
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(false);
				editTextT1.setFocusable(false);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.GASLAW_SolvingForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("T2"))
			{
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(false);
				editTextT2.setFocusable(false);

				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.GASLAW_SolvingForT2));
			}
			else
			{
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
		}
		if(Gas_AutoSolve)
		{
			Result = -1;
			inputP1 = editTextP1.getText().toString();
			inputP2 = editTextP2.getText().toString();
			inputT1 = editTextT1.getText().toString();
			inputT2 = editTextT2.getText().toString();
			lengthP1 = inputP1.length();
			lengthP2 = inputP2.length();
			lengthT1 = inputT1.length();
			lengthT2 = inputT2.length();
			P1 = -9876.5;
			P2 = -9876.5;
			T1 = -9876.5;
			T2 = -9876.5;
			if (lengthP1 != 0)
				P1 = Double.parseDouble(inputP1);
			if (lengthP2 != 0)
				P2 = Double.parseDouble(inputP2);
			if (lengthT1 != 0)
				T1 = Double.parseDouble(inputT1);
			if (lengthT2 != 0)
				T2 = Double.parseDouble(inputT2);
			
			if(EmptyField.equals("P1"))
			{
				errorTextView.setText("");
				editTextP1.setHintTextColor(Color.BLUE);
				Result = Laws.GayLussac_Law(P1, T1, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextP1.setHint("" + Result);
			}
			else if(EmptyField.equals("P2"))
			{
				errorTextView.setText("");
				editTextP2.setHintTextColor(Color.BLUE);
				Result = Laws.GayLussac_Law(P1, T1, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextP2.setHint("" + Result);
			}
			else if(EmptyField.equals("T1"))
			{
				errorTextView.setText("");
				editTextT1.setHintTextColor(Color.BLUE);
				Result = Laws.GayLussac_Law(P1, T1, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextT1.setHint("" + Result);
			}
			else if(EmptyField.equals("T2"))
			{
				errorTextView.setText("");
				editTextT2.setHintTextColor(Color.BLUE);
				Result = Laws.GayLussac_Law(P1, T1, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextT2.setHint("" + Result);
			}
			else
			{
				editTextP1.setHintTextColor(Color.GRAY);
				editTextP2.setHintTextColor(Color.GRAY);
				editTextT1.setHintTextColor(Color.GRAY);
				editTextT2.setHintTextColor(Color.GRAY);
				
				editTextP1.setHint(getString(R.string.blankForV1));
				editTextP2.setHint(getString(R.string.blankForV2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
		}
	}
	
	public String checkEmpty(boolean FILLED_P1, boolean FILLED_P2, boolean FILLED_T1, boolean FILLED_T2)
	{
		if(FILLED_P2 && FILLED_T1 && FILLED_T2)
			return "P1";
		else if(FILLED_P1 && FILLED_T1 && FILLED_T2)
			return "P2";
		else if(FILLED_P1 && FILLED_P2 && FILLED_T2)
			return "T1";
		else if(FILLED_P1 && FILLED_P2 && FILLED_T1)
			return "T2";
		else
			return "";
	}
	
	public void addItemsOnSpinners()
	{
		// Get Position Defaults
		int spinnerPosition;
		String defaultPresUnit = MySingleton.getInstance().getDefaultPresS();
		String defaultTempUnit = MySingleton.getInstance().getDefaultTempS();

		// Initialize Spinners and Adapters. Set Default If Necessary.
		spinnerP1 = (Spinner) findViewById(R.id.spinnerP1);
		adapterP1 = ArrayAdapter.createFromResource(this, R.array.UnitPressure_array, android.R.layout.simple_spinner_item);
		adapterP1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerP1.setAdapter(adapterP1);
		spinnerPosition = adapterP1.getPosition(defaultPresUnit);
		spinnerP1.setSelection(spinnerPosition);
		
		spinnerT1 = (Spinner) findViewById(R.id.spinnerT1);
		adapterT1 = ArrayAdapter.createFromResource(this, R.array.UnitTemperature_array, android.R.layout.simple_spinner_item);
		adapterT1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerT1.setAdapter(adapterT1);
		spinnerPosition = adapterT1.getPosition(defaultTempUnit);
		spinnerT1.setSelection(spinnerPosition);
		
		spinnerP2 = (Spinner) findViewById(R.id.spinnerP2);
		adapterP2 = ArrayAdapter.createFromResource(this, R.array.UnitPressure_array, android.R.layout.simple_spinner_item);
		adapterP2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerP2.setAdapter(adapterP2);
		spinnerPosition = adapterP2.getPosition(defaultPresUnit);
		spinnerP2.setSelection(spinnerPosition);
		
		spinnerT2 = (Spinner) findViewById(R.id.spinnerT2);
		adapterT2 = ArrayAdapter.createFromResource(this, R.array.UnitTemperature_array, android.R.layout.simple_spinner_item);
		adapterT2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerT2.setAdapter(adapterT2);
		spinnerPosition = adapterT2.getPosition(defaultTempUnit);
		spinnerT2.setSelection(spinnerPosition);
	}
	
	public void addListenerOnSpinnersItemSelection()
	{
		spinnerP1.setOnItemSelectedListener(new OnP1SelectedListener());
		spinnerT1.setOnItemSelectedListener(new OnT1SelectedListener());
		spinnerP2.setOnItemSelectedListener(new OnP2SelectedListener());
		spinnerT2.setOnItemSelectedListener(new OnT2SelectedListener());
	}
	
	public class OnP1SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectP1 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectP1(SelectP1);
			pageValue1 = SelectP1;
			if (Gas_SameUnits)
				sameSelection("P1");
	    }
		
	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	public class OnT1SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectT1 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectT1(SelectT1);
			pageValue2 = SelectT1;
			if (Gas_SameUnits)
				sameSelection("T1");
	    }

	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	public class OnP2SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectP2 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectP2(SelectP2);
			pageValue3 = SelectP2;
			if (Gas_SameUnits)
				sameSelection("P2");
	    }

	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	public class OnT2SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectT2 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectT2(SelectT2);
			pageValue4 = SelectT2;
			if (Gas_SameUnits)
				sameSelection("T2");
	    }

	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	
	public void sameSelection(String Type)
	{
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectT2 = MySingleton.getInstance().getSelectT2();
		SelectP2 = MySingleton.getInstance().getSelectP2();

		if (Type.equals("T1"))
		{
			if (SelectT1.equals("K"))
			{
				spinnerT2.setSelection(0);
				MySingleton.getInstance().setSelectT2("K");
			}
			else if (SelectT1.equals("C"))
			{
				spinnerT2.setSelection(1);
				MySingleton.getInstance().setSelectT2("C");
			}
			else if (SelectT1.equals("F"))
			{
				spinnerT2.setSelection(2);
				MySingleton.getInstance().setSelectT2("F");
			}
			else
				MySingleton.getInstance().addError("Boyles Law: Unable to set spinnerT2 selection.");
		}

		else if (Type.equals("P1"))
		{
			if (SelectP1.equals("atm"))
			{
				spinnerP2.setSelection(0);
				MySingleton.getInstance().setSelectP2("atm");
			}
			else if (SelectP1.equals("mmHg"))
			{
				spinnerP2.setSelection(1);
				MySingleton.getInstance().setSelectP2("mmHg");
			}
			else if (SelectP1.equals("torr"))
			{
				spinnerP2.setSelection(2);
				MySingleton.getInstance().setSelectP2("torr");
			}
			else if (SelectP1.equals("kPa"))
			{
				spinnerP2.setSelection(3);
				MySingleton.getInstance().setSelectP2("kPa");
			}
			else
				MySingleton.getInstance().addError("Boyles Law: Unable to set spinnerP2 selection.");
		}

		else if (Type.equals("T2"))
		{
			if (SelectT2.equals("K"))
			{
				spinnerT1.setSelection(0);
				MySingleton.getInstance().setSelectT1("K");
			}
			else if (SelectT2.equals("C"))
			{
				spinnerT1.setSelection(1);
				MySingleton.getInstance().setSelectT1("C");
			}
			else if (SelectT2.equals("F"))
			{
				spinnerT1.setSelection(2);
				MySingleton.getInstance().setSelectT1("F");
			}
			else
				MySingleton.getInstance().addError("Boyles Law: Unable to set spinnerT1 selection.");
		}

		else if (Type.equals("P2"))
		{
			if (SelectP2.equals("atm"))
			{
				spinnerP1.setSelection(0);
				MySingleton.getInstance().setSelectP1("atm");
			}
			else if (SelectP2.equals("mmHg"))
			{
				spinnerP1.setSelection(1);
				MySingleton.getInstance().setSelectP1("mmHg");
			}
			else if (SelectP2.equals("torr"))
			{
				spinnerP1.setSelection(2);
				MySingleton.getInstance().setSelectP1("torr");
			}
			else if (SelectP2.equals("kPa"))
			{
				spinnerP1.setSelection(3);
				MySingleton.getInstance().setSelectP1("kPa");
			}
			else
				MySingleton.getInstance().addError("Boyles Law: Unable to set spinnerP1 selection.");
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
		values[8] = pageValue8;

		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			int spinnerPosition;

			spinnerPosition = adapterP1.getPosition(pageValues[1]);
			spinnerP1.setSelection(spinnerPosition);
			SelectP1 = pageValues[1];
			MySingleton.getInstance().setSelectP1(SelectP1);
			pageValue1 = SelectP1;

			spinnerPosition = adapterT1.getPosition(pageValues[2]);
			spinnerT1.setSelection(spinnerPosition);
			SelectT1 = pageValues[2];
			MySingleton.getInstance().setSelectP1(SelectT1);
			pageValue2 = SelectT1;

			spinnerPosition = adapterP2.getPosition(pageValues[3]);
			spinnerP2.setSelection(spinnerPosition);
			SelectP2 = pageValues[3];
			MySingleton.getInstance().setSelectP2(SelectP2);
			pageValue3 = SelectP2;

			spinnerPosition = adapterT2.getPosition(pageValues[4]);
			spinnerT2.setSelection(spinnerPosition);
			SelectT2 = pageValues[4];
			MySingleton.getInstance().setSelectT2(SelectT2);
			pageValue4 = SelectT2;

			editTextP1.setText(pageValues[5]);
			editTextT1.setText(pageValues[6]);
			editTextP2.setText(pageValues[7]);
			editTextT2.setText(pageValues[8]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(GayLussacsLaw.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
