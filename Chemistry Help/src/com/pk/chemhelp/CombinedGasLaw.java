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

public class CombinedGasLaw extends SherlockActivity
{
	/* Important Objects for Referencing and Problem Solving */
	protected DataStorage appState;
	final String PageID = "Combined Gas Law";
	ErrorDetection ErrorDetect = new ErrorDetection();
	Laws Laws = new Laws();
	
	/* Exit */
	private boolean Exit;
	
	/* Declare Basic UI Objects */
	Spinner spinnerV1;
	Spinner spinnerP1;
    Spinner spinnerT1;
    Spinner spinnerV2;
    Spinner spinnerP2;
    Spinner spinnerT2;
    ArrayAdapter<CharSequence> adapterV1;
	ArrayAdapter<CharSequence> adapterP1;
    ArrayAdapter<CharSequence> adapterT1;
    ArrayAdapter<CharSequence> adapterV2;
    ArrayAdapter<CharSequence> adapterP2;
    ArrayAdapter<CharSequence> adapterT2;
    private EditText editTextV1;
    private EditText editTextP1;
    private EditText editTextT1;
    private EditText editTextV2;
    private EditText editTextP2;
    private EditText editTextT2;
    
    /* Declare and Instantiate Important Variable for Problem Solving */
    double V1 = MySingleton.getInstance().getV1();
    double V2 = MySingleton.getInstance().getV2();
    double P1 = MySingleton.getInstance().getP1();
    double P2 = MySingleton.getInstance().getP2();
    double T1 = MySingleton.getInstance().getT1();
    double T2 = MySingleton.getInstance().getT2();
    int lengthV1;
    int lengthV2;
    int lengthP1;
    int lengthP2;
    int lengthT1;
    int lengthT2;
	double Result;
    
    /* Store Text Field Values Here */
	String inputV1;
	String inputV2;
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
	String SelectV1;
	String SelectV2;
	String SelectP1;
	String SelectP2;
	String SelectT1;
	String SelectT2;
	
	/* Extra Functions */
	boolean Gas_SameUnits;
	boolean Gas_AutoSolve;
	boolean Gas_FormatHelp;
	boolean FILLED_V1;
	boolean FILLED_V2;
	boolean FILLED_P1;
	boolean FILLED_P2;
	boolean FILLED_T1;
	boolean FILLED_T2;
	
	/* Bookmarks */

	String[] pageValues;
	String pageValue1;
	String pageValue2;
	String pageValue3;
	String pageValue4;
	String pageValue5;
	String pageValue6;
	String pageValue7;
	String pageValue8;
	String pageValue9;
	String pageValue10;
	String pageValue11;
	String pageValue12;
	
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
		setContentView(R.layout.combinedgaslaw);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Gas Laws");
		actionBar.setSubtitle("Combined Gas Law");
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		
		Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
		Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
		Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
		
		pageValue1 = MySingleton.getInstance().getDefaultVolS();
		pageValue2 = MySingleton.getInstance().getDefaultPresS();
		pageValue3 = MySingleton.getInstance().getDefaultTempS();
		pageValue4 = MySingleton.getInstance().getDefaultVolS();
		pageValue5 = MySingleton.getInstance().getDefaultPresS();
		pageValue6 = MySingleton.getInstance().getDefaultTempS();
		pageValue7 = "";
		pageValue8 = "";
		pageValue9 = "";
		pageValue10 = "";
		pageValue11 = "";
		pageValue12 = "";
		
		appState = (DataStorage)getApplication();
		addItemsOnSpinners();
		addListenerOnSpinnersItemSelection();
		
		editTextV1 = (EditText)findViewById(R.id.editTextV1);
		editTextP1 = (EditText)findViewById(R.id.editTextP1);
		editTextT1 = (EditText)findViewById(R.id.editTextT1);
		editTextV2 = (EditText)findViewById(R.id.editTextV2);
		editTextP2 = (EditText)findViewById(R.id.editTextP2);
		editTextT2 = (EditText)findViewById(R.id.editTextT2);

		errorTextView = (TextView)findViewById(R.id.errorMessage);

		formulaDialog = new Dialog(CombinedGasLaw.this);
		formulaDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		formulaDialog.setContentView(R.layout.dialog_formula);
		formulaDialog.setCancelable(true);
		formulaImage = (ImageView) formulaDialog.findViewById(R.id.imageFormula);
		formulaImage.setImageResource(R.drawable.formula_combined);
		Button formulaDialogClose = (Button) formulaDialog.findViewById(R.id.Close);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		answerDialog = new Dialog(CombinedGasLaw.this);
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
				editTextV1.setText("");
				editTextV2.setText("");
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
				inputV1 = editTextV1.getText().toString();
				inputV2 = editTextV2.getText().toString();
				inputP1 = editTextP1.getText().toString();
				inputP2 = editTextP2.getText().toString();
				inputT1 = editTextT1.getText().toString();
				inputT2 = editTextT2.getText().toString();
				lengthV1 = inputV1.length();
				lengthV2 = inputV2.length();
				lengthP1 = inputP1.length();
				lengthP2 = inputP2.length();
				lengthT1 = inputT1.length();
				lengthT2 = inputT2.length();
				V1 = -9876.5;
				V2 = -9876.5;
				P1 = -9876.5;
				P2 = -9876.5;
				T1 = -9876.5;
				T2 = -9876.5;
				if(lengthV1 != 0)
					V1 = Double.parseDouble(inputV1);
				if(lengthV2 != 0)
					V2 = Double.parseDouble(inputV2);
				if(lengthP1 != 0)
					P1 = Double.parseDouble(inputP1);
				if(lengthP2 != 0)
					P2 = Double.parseDouble(inputP2);
				if(lengthT1 != 0)
					T1 = Double.parseDouble(inputT1);
				if(lengthT2 != 0)
					T2 = Double.parseDouble(inputT2);
				
				errorTextView.setTextColor(0xFFFF0000);
				Empty = ErrorDetect.getEmptyFieldCombined(lengthV1, lengthP1, lengthT1, lengthV2, lengthP2, lengthT2);
				if(Empty == -1) // If user filled out everything for some reason
				{
					errorTextView.setText("Please leave the field you're solving for empty");
				}
				else if(Empty == 1) // Solving for V1
				{
					// Do calculations later
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForV1));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1,P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectV1);
					answerDialog.show();
				}
				else if(Empty == 2) // Solving for P1
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForP1));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1, P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectP1);
					answerDialog.show();
				}
				else if(Empty == 3) // Solving for T1
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForT1));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1, P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectT1);
					answerDialog.show();
				}
				else if(Empty == 4) // Solving for V2
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForV2));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1, P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectV2);
					answerDialog.show();
				}
				else if(Empty == 5) // Solving for P2
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForP2));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1, P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectP2);
					answerDialog.show();
				}
				else if(Empty == 6) // Solving for T2
				{
					errorTextView.setText("");
					answerDialogTitle.setText(getString(R.string.GASLAW_SolvingForT2));
					Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
					Work = Laws.CombinedGas_Law_Work(V1, P1, T1, V2, P2, T2);
					answerWork.setText(Work);
					Result = Misc.decimalPrecisionAssign(Result);
					answerDialogResult.setText(Result + " " + SelectT2);
					answerDialog.show();
				}
				else // Error Messages
				{
					errorMessage = ErrorDetect.detectErrorCombined(Empty);
					errorTextView.setText(errorMessage);
				}
			}
		});
		
		editTextV1.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextV1.getText().toString();
				if (text.length() > 0)
					FILLED_V1 = true;
				else
					FILLED_V1 = false;
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
				String text = editTextV1.getText().toString();
				if (text.length() > 0)
					FILLED_V1 = true;
				else
					FILLED_V1 = false;
				checkInputs();
			}
			
		});
		editTextV2.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				String text = editTextV2.getText().toString();
				if (text.length() > 0)
					FILLED_V2 = true;
				else
					FILLED_V2 = false;
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
				String text = editTextV2.getText().toString();
				if (text.length() > 0)
					FILLED_V2 = true;
				else
					FILLED_V2 = false;
				checkInputs();
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
				pageValue7 = editTextV1.getText().toString();
				pageValue8 = editTextP1.getText().toString();
				pageValue9 = editTextT1.getText().toString();
				pageValue10 = editTextV2.getText().toString();
				pageValue11 = editTextP2.getText().toString();
				pageValue12 = editTextT2.getText().toString();
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", CombinedGasLaw.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", CombinedGasLaw.this).show();
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
				Intent i = new Intent(CombinedGasLaw.this, Debug.class);
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
		EmptyField = checkEmpty(FILLED_V1, FILLED_V2, FILLED_P1, FILLED_P2, FILLED_T1, FILLED_T2);

		if(Gas_FormatHelp)
		{
			if(EmptyField.equals("V1"))
			{
				editTextV1.setEnabled(false);
				editTextV1.setFocusable(false);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.GASLAW_SolvingForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("V2"))
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(false);
				editTextV2.setFocusable(false);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.GASLAW_SolvingForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("P1"))
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(false);
				editTextP1.setFocusable(false);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.GASLAW_SolvingForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("P2"))
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(false);
				editTextP2.setFocusable(false);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.GASLAW_SolvingForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("T1"))
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(false);
				editTextT1.setFocusable(false);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.GASLAW_SolvingForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
			else if(EmptyField.equals("T2"))
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(false);
				editTextT2.setFocusable(false);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.GASLAW_SolvingForT2));
			}
			else
			{
				editTextV1.setEnabled(true);
				editTextV1.setFocusable(true);
				editTextV2.setEnabled(true);
				editTextV2.setFocusable(true);
				editTextP1.setEnabled(true);
				editTextP1.setFocusable(true);
				editTextP2.setEnabled(true);
				editTextP2.setFocusable(true);
				editTextT1.setEnabled(true);
				editTextT1.setFocusable(true);
				editTextT2.setEnabled(true);
				editTextT2.setFocusable(true);

				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
		}
		if(Gas_AutoSolve)
		{
			Result = -1;
			inputV1 = editTextV1.getText().toString();
			inputV2 = editTextV2.getText().toString();
			inputP1 = editTextP1.getText().toString();
			inputP2 = editTextP2.getText().toString();
			inputT1 = editTextT1.getText().toString();
			inputT2 = editTextT2.getText().toString();
			lengthV1 = inputV1.length();
			lengthV2 = inputV2.length();
			lengthP1 = inputP1.length();
			lengthP2 = inputP2.length();
			lengthT1 = inputT1.length();
			lengthT2 = inputT2.length();
			V1 = -9876.5;
			V2 = -9876.5;
			P1 = -9876.5;
			P2 = -9876.5;
			T1 = -9876.5;
			T2 = -9876.5;
			if (lengthV1 != 0)
				V1 = Double.parseDouble(inputV1);
			if (lengthV2 != 0)
				V2 = Double.parseDouble(inputV2);
			if (lengthP1 != 0)
				P1 = Double.parseDouble(inputP1);
			if (lengthP2 != 0)
				P2 = Double.parseDouble(inputP2);
			if (lengthT1 != 0)
				T1 = Double.parseDouble(inputT1);
			if (lengthT2 != 0)
				T2 = Double.parseDouble(inputT2);
			
			if(EmptyField.equals("V1"))
			{
				errorTextView.setText("");
				editTextV1.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextV1.setHint("" + Result);
			}
			else if(EmptyField.equals("V2"))
			{
				errorTextView.setText("");
				editTextV2.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextV2.setHint("" + Result);
			}
			else if(EmptyField.equals("P1"))
			{
				errorTextView.setText("");
				editTextP1.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextP1.setHint("" + Result);
			}
			else if(EmptyField.equals("P2"))
			{
				errorTextView.setText("");
				editTextP2.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextP2.setHint("" + Result);
			}
			else if(EmptyField.equals("T1"))
			{
				errorTextView.setText("");
				editTextT1.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextT1.setHint("" + Result);
			}
			else if(EmptyField.equals("T2"))
			{
				errorTextView.setText("");
				editTextT2.setHintTextColor(Color.BLUE);
				Result = Laws.CombinedGas_Law(V1, P1, T1, V2, P2, T2);
				Result = Misc.decimalPrecisionAssign(Result);
				editTextT2.setHint("" + Result);
			}
			else
			{
				editTextV1.setHintTextColor(Color.GRAY);
				editTextV2.setHintTextColor(Color.GRAY);
				editTextP1.setHintTextColor(Color.GRAY);
				editTextP2.setHintTextColor(Color.GRAY);
				editTextT1.setHintTextColor(Color.GRAY);
				editTextT2.setHintTextColor(Color.GRAY);
				


				editTextV1.setHint(getString(R.string.blankForV1));
				editTextV2.setHint(getString(R.string.blankForV2));
				editTextP1.setHint(getString(R.string.blankForP1));
				editTextP2.setHint(getString(R.string.blankForP2));
				editTextT1.setHint(getString(R.string.blankForT1));
				editTextT2.setHint(getString(R.string.blankForT2));
			}
		}
	}
	
	public String checkEmpty(boolean FILLED_V1, boolean FILLED_V2, boolean FILLED_P1, boolean FILLED_P2, boolean FILLED_T1, boolean FILLED_T2)
	{
		if(FILLED_V2 && FILLED_P1 && FILLED_P2 && FILLED_T1 && FILLED_T2)
			return "V1";
		else if(FILLED_V1 && FILLED_P1 && FILLED_P2 && FILLED_T1 && FILLED_T2)
			return "V2";
		else if(FILLED_V1 && FILLED_V2 && FILLED_P2 && FILLED_T1 && FILLED_T2)
			return "P1";
		else if(FILLED_V1 && FILLED_V2 && FILLED_P1 && FILLED_T1 && FILLED_T2)
			return "P2";
		else if(FILLED_V1 && FILLED_V2 && FILLED_P1 && FILLED_P2 && FILLED_T2)
			return "T1";
		else if(FILLED_V1 && FILLED_V2 && FILLED_P1 && FILLED_P2 && FILLED_T1)
			return "T2";
		else
			return "";
	}
	
	public void addItemsOnSpinners()
	{
		// Get Position Defaults
		int spinnerPosition;
		String defaultVolUnit = MySingleton.getInstance().getDefaultVolS();
		String defaultPresUnit = MySingleton.getInstance().getDefaultPresS();
		String defaultTempUnit = MySingleton.getInstance().getDefaultTempS();

		// Initialize Spinners and Adapters. Set Default If Necessary.
		spinnerV1 = (Spinner) findViewById(R.id.spinnerV1);
		adapterV1 = ArrayAdapter.createFromResource(this, R.array.UnitVolume_array, android.R.layout.simple_spinner_item);
		adapterV1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerV1.setAdapter(adapterV1);
		spinnerPosition = adapterV1.getPosition(defaultVolUnit);
		spinnerV1.setSelection(spinnerPosition);
		
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
		
		spinnerV2 = (Spinner) findViewById(R.id.spinnerV2);
		adapterV2 = ArrayAdapter.createFromResource(this, R.array.UnitVolume_array, android.R.layout.simple_spinner_item);
		adapterV2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerV2.setAdapter(adapterV2);
		spinnerPosition = adapterV2.getPosition(defaultVolUnit);
		spinnerV2.setSelection(spinnerPosition);
		
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
		spinnerPosition = adapterT2.getPosition(defaultPresUnit);
		spinnerT2.setSelection(spinnerPosition);
	}
	
	public void addListenerOnSpinnersItemSelection()
	{
		spinnerV1.setOnItemSelectedListener(new OnV1SelectedListener());
		spinnerP1.setOnItemSelectedListener(new OnP1SelectedListener());
		spinnerT1.setOnItemSelectedListener(new OnT1SelectedListener());
		spinnerV2.setOnItemSelectedListener(new OnV2SelectedListener());
		spinnerP2.setOnItemSelectedListener(new OnP2SelectedListener());
		spinnerT2.setOnItemSelectedListener(new OnT2SelectedListener());
	}
	
	public class OnV1SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectV1 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectV1(SelectV1);
			pageValue1 = SelectV1;
			if (Gas_SameUnits)
				sameSelection("V1");
	    }
		
	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	public class OnP1SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectP1 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectP1(SelectP1);
			pageValue2 = SelectP1;
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
			pageValue3 = SelectT1;
			if (Gas_SameUnits)
				sameSelection("T1");
	    }

	    @SuppressWarnings("rawtypes")
		public void onNothingSelected(AdapterView parent)
	    {
			// Do nothing.
	    }
	}
	public class OnV2SelectedListener implements Spinner.OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
			SelectV2 = parent.getItemAtPosition(pos).toString();
			MySingleton.getInstance().setSelectV2(SelectV2);
			pageValue4 = SelectV2;
			if (Gas_SameUnits)
				sameSelection("V2");
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
			pageValue5 = SelectP2;
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
			pageValue6 = SelectT2;
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
		SelectV1 = MySingleton.getInstance().getSelectV1();
		SelectP1 = MySingleton.getInstance().getSelectP1();
		SelectT1 = MySingleton.getInstance().getSelectT1();
		SelectV2 = MySingleton.getInstance().getSelectV2();
		SelectP2 = MySingleton.getInstance().getSelectP2();
		SelectT2 = MySingleton.getInstance().getSelectT2();

		if (Type.equals("V1"))
		{
			if (SelectV1.equals("L"))
			{
				spinnerV2.setSelection(0);
				MySingleton.getInstance().setSelectV2("L");
			}
			else if (SelectV1.equals("mL"))
			{
				spinnerV2.setSelection(1);
				MySingleton.getInstance().setSelectV2("mL");
			}
			else if (SelectV1.equals("g"))
			{
				spinnerV2.setSelection(2);
				MySingleton.getInstance().setSelectV2("g");
			}
			else
				MySingleton.getInstance().addError("Combined Law: Unable to set spinnerV2 selection.");
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
				MySingleton.getInstance().addError("Combined Law: Unable to set spinnerP2 selection.");
		}

		else if (Type.equals("T1"))
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
				MySingleton.getInstance().addError("Combined Law: Unable to set spinnerT2 selection.");
		}

		else if (Type.equals("V2"))
		{
			if (SelectV2.equals("L"))
			{
				spinnerV1.setSelection(0);
				MySingleton.getInstance().setSelectV1("L");
			}
			else if (SelectV2.equals("mL"))
			{
				spinnerV1.setSelection(1);
				MySingleton.getInstance().setSelectV1("mL");
			}
			else if (SelectV2.equals("g"))
			{
				spinnerV1.setSelection(2);
				MySingleton.getInstance().setSelectV1("g");
			}
			else
				MySingleton.getInstance().addError("Combined Gas Law: Unable to set spinnerV1 selection.");
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
				MySingleton.getInstance().addError("Combined Gas Law Law: Unable to set spinnerP1 selection.");
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
		values[9] = pageValue9;
		values[10] = pageValue10;
		values[11] = pageValue11;
		values[12] = pageValue12;

		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			int spinnerPosition;

			spinnerPosition = adapterV1.getPosition(pageValues[1]);
			spinnerV1.setSelection(spinnerPosition);
			SelectV1 = pageValues[1];
			MySingleton.getInstance().setSelectV1(SelectV1);
			pageValue1 = SelectV1;

			spinnerPosition = adapterP1.getPosition(pageValues[2]);
			spinnerP1.setSelection(spinnerPosition);
			SelectP1 = pageValues[2];
			MySingleton.getInstance().setSelectP1(SelectP1);
			pageValue2 = SelectP1;

			spinnerPosition = adapterT1.getPosition(pageValues[3]);
			spinnerT1.setSelection(spinnerPosition);
			SelectT1 = pageValues[3];
			MySingleton.getInstance().setSelectT1(SelectT1);
			pageValue3 = SelectT1;

			spinnerPosition = adapterV2.getPosition(pageValues[4]);
			spinnerV2.setSelection(spinnerPosition);
			SelectV2 = pageValues[4];
			MySingleton.getInstance().setSelectV2(SelectV2);
			pageValue4 = SelectV2;
			
			spinnerPosition = adapterP2.getPosition(pageValues[5]);
			spinnerP2.setSelection(spinnerPosition);
			SelectP2 = pageValues[5];
			MySingleton.getInstance().setSelectP2(SelectP2);
			pageValue5 = SelectP2;

			spinnerPosition = adapterT2.getPosition(pageValues[6]);
			spinnerT2.setSelection(spinnerPosition);
			SelectT2 = pageValues[6];
			MySingleton.getInstance().setSelectT2(SelectT2);
			pageValue6 = SelectT2;

			editTextV1.setText(pageValues[7]);
			editTextP1.setText(pageValues[8]);
			editTextT1.setText(pageValues[9]);
			editTextV2.setText(pageValues[10]);
			editTextP2.setText(pageValues[11]);
			editTextT2.setText(pageValues[12]);
		}
	}
	
	public void showError()
	{
		Toast.makeText(CombinedGasLaw.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
