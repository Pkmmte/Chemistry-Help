package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Debug extends SherlockFragmentActivity
{
	protected DataStorage appState;
	String SettingsTAG = "ChemHelpSettings";
	SharedPreferences settings;
	boolean PRO = MySingleton.getInstance().isPRO();
	
	Handler uHandler;
	
	ActionBar actionBar;
	ViewPager mPager;
	
	Tab controlTab;
	Tab globalTab;
	Tab savedTab;
	Tab logTab;
	
	ControlFragment controlFragment;
	GlobalFragment globalFragment;
	SavedFragment savedFragment;
	LogFragment logFragment;
	
	MenuItem warningIcon;
	MenuItem debugMenu;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug);
		appState = (DataStorage) getApplication();
		
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		mPager = (ViewPager) findViewById(R.id.pager);
		uHandler = new Handler();
		settings = getSharedPreferences(SettingsTAG, 0);
		
		/** Defining a listener for pageChange */
		ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				super.onPageSelected(position);
				actionBar.setSelectedNavigationItem(position);
			}
		};
		
		/** Creating an instance of FragmentPagerAdapter */
		mPager.setOnPageChangeListener(pageChangeListener);
		
		FragmentManager fm = getSupportFragmentManager();
		MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);
		
		controlFragment = new ControlFragment();
		globalFragment = new GlobalFragment();
		savedFragment = new SavedFragment();
		logFragment = new LogFragment();
		
		/** Setting the FragmentPagerAdapter object to the viewPager object */
		mPager.setAdapter(fragmentPagerAdapter);
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener()
		{
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft)
			{
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft)
			{
				mPager.setCurrentItem(tab.getPosition());
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft)
			{
				
			}
		};
		
		controlTab = actionBar.newTab().setText("Control").setTabListener(tabListener);
		globalTab = actionBar.newTab().setText("Global").setTabListener(tabListener);
		savedTab = actionBar.newTab().setText("Saved").setTabListener(tabListener);
		logTab = actionBar.newTab().setText("Log").setTabListener(tabListener);
		
		actionBar.addTab(controlTab);
		actionBar.addTab(globalTab);
		actionBar.addTab(savedTab);
		actionBar.addTab(logTab);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onRestart()
	{
		super.onRestart();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle outState)
	{
		super.onRestoreInstanceState(outState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.debug_menu, menu);
		this.warningIcon = menu.findItem(R.id.Warning_Label);
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			mainMenu = menu;
			subMenu = menu.addSubMenu("");
			subMenu.add(Menu.NONE, R.id.Settings_Label, Menu.NONE, "Settings");
			subMenu.add(Menu.NONE, R.id.Exit_Label, Menu.NONE, "Exit");
			subMenu.add(Menu.NONE, R.id.Item1_Label, Menu.NONE, "Item 1");
			subMenu.add(Menu.NONE, R.id.Item2_Label, Menu.NONE, "Item 2");
			subMenu.add(Menu.NONE, R.id.Item3_Label, Menu.NONE, "Item 3");
			subMenu.add(Menu.NONE, R.id.Item4_Label, Menu.NONE, "Item 4");
			
			MenuItem subMenuItem = subMenu.getItem();
			subMenuItem.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark);
			subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}

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
			case R.id.Settings_Label:
				Intent settingsIntent = new Intent(Debug.this, Settings.class);
				settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(settingsIntent);
				return true;
			case R.id.Exit_Label:
				Exit();
				return true;
			case R.id.Item1_Label:
				showToast("Item 1");
				Intent tent = new Intent(Debug.this, ViewElement.class);
				startActivity(tent);
				return true;
			case R.id.Item2_Label:
				showToast("Item 2");
				Intent ten = new Intent(Debug.this, NewGasLaws.class);
				startActivity(ten);
				return true;
			case R.id.Item3_Label:
				showToast("Item 3");
				return true;
			case R.id.Item4_Label:
				showToast("Item 4");
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
	
	public void clearApplicationData()
	{
		File cache = getCacheDir();
		File appDir = new File(cache.getParent());
		if (appDir.exists())
		{
			String[] children = appDir.list();
			for (String s : children)
			{
				if (!s.equals("lib"))
				{
					deleteDir(new File(appDir, s));
					Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
				}
			}
		}
	}

	public static boolean deleteDir(File dir)
	{
		if (dir != null && dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}

		return dir.delete();
	}
	
	public void ClearData(View v)
	{
		clearApplicationData();
	}
	
	public void ForceStop(View v)
	{
		MySingleton.getInstance().setFS(true);
		finish();
	}
	
	public void callDialog(View v)
	{
		String Type;
		Type = MySingleton.getInstance().getDebugDialogPosition();
		
		if(Type.equals("Update Available"))
		{
			if(isNetworkConnected())
	        {
	        	MySingleton.getInstance().addLog("Yes connected!");
	        	checkUpdate.start();
			}
			else // Get details from old value if available
			{
				String uDetails = settings.getString("UpdateDetails", "Value");
				MySingleton.getInstance().setUpdateDetails(uDetails);
				MySingleton.getInstance().addLog("No network...");
				Dialogs.getDialog("Update Available", Debug.this).show();
			}
		}
		else
			Dialogs.getDialog(Type, Debug.this).show();
	}
	
	private boolean isNetworkConnected()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null)
		{
			return false;
		}
		else
			return true;
	}
	
	private Thread checkUpdate = new Thread()
    {
        public void run()
        {
        	MySingleton.getInstance().addLog("Called Thread");
            try
            {
                URL updateURL = new URL("http://pkmmte.com/update/chemhelp.txt");
                URLConnection conn = updateURL.openConnection();
                InputStream is = conn.getInputStream();
                is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                
                int current = 0;
                while((current = bis.read()) != -1) 
                {
                     baf.append((byte)current);
                }
                
                final String s = new String(baf.toByteArray());
                String[] updateInfo = s.split("PCX");

                MySingleton.getInstance().setUpdateDetails(updateInfo[2]);
                uHandler.post(showUpdate);
            }
            catch (Exception e)
            {
            	MySingleton.getInstance().addError("Could not read update details from server..." + e.getMessage());
    			MySingleton.getInstance().addError("Could not read update details from server..." + e.toString());
            }
        }
    };
    
    private Runnable showUpdate = new Runnable()
    {
    	public void run()
    	{
    		String uDetails = MySingleton.getInstance().getUpdateDetails();
    		Editor editor = settings.edit();
			editor.putString("UpdateDetails", uDetails);
			editor.commit();
			
            Dialogs.getDialog("Update Available", Debug.this).show();
    	}
    };
	
	public void showToast(String text)
	{
		Toast.makeText(Debug.this, text, Toast.LENGTH_LONG).show();
	}
	
	public void showError()
	{
		Dialogs.getDialog("Report", Debug.this).show();
		//Toast.makeText(CheckValues.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getERROR(), Toast.LENGTH_SHORT).show();
	}

	public void Exit()
	{
		MySingleton.getInstance().setExit(true);
		finish();
	}
	
	public static class ControlFragment extends SherlockFragment
	{
		boolean PRO;
		protected DataStorage appState;
		String SettingsTAG = "ChemHelpSettings";
		SharedPreferences settings;
		
		Button activatePro;
		Spinner dialogSpinner;
		ArrayAdapter<String> dialogAdapter;
		String[] dialogArray;		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.debug_control, container, false);
			activatePro = (Button) view.findViewById(R.id.Pro);
			PRO = MySingleton.getInstance().isPRO();
			
			if(PRO)
				activatePro.setText("Deactivate Pro");
			
			dialogArray = new String[] { "First Time", "Change Log", "Help", "About", "Bookmarks", "Add Bookmark", "Update Available", "Report"};
			
			dialogSpinner = (Spinner) view.findViewById(R.id.DialogSpinner);
			dialogAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, dialogArray);
			dialogAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			dialogSpinner.setAdapter(dialogAdapter);
			
			dialogSpinner.setOnItemSelectedListener(new OnDialogSelectedListener());
			
			activatePro.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					Pro();
				}
			});
			
			return view;
		}
		
		public void Pro()
		{
			PRO = MySingleton.getInstance().isPRO();
			if(PRO)
			{
				MySingleton.getInstance().setPRO(false);
				PRO = false;
				activatePro.setText("Activate Pro");
			}
			else
			{
				MySingleton.getInstance().setPRO(true);
				PRO = true;
				activatePro.setText("Deactivate Pro");
			}
			
			saveValues();
		}
		
		public void saveValues()
		{
			PRO = MySingleton.getInstance().isPRO();

			settings = getActivity().getBaseContext().getSharedPreferences(SettingsTAG, 0);
			Editor editor = settings.edit();

			editor.putBoolean("PRO", PRO);

			editor.commit();
		}
		
		public class OnDialogSelectedListener implements Spinner.OnItemSelectedListener
		{
			String position;
	
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				if (parent.getItemAtPosition(pos).toString().length() != 0)
				{
					position = parent.getItemAtPosition(pos).toString();
					MySingleton.getInstance().setDebugDialogPosition(position);
				}
			}
			
			@SuppressWarnings("rawtypes")
			public void onNothingSelected(AdapterView parent)
			{
				// Do nothing.
			}
		}
	}
	
	public static class GlobalFragment extends SherlockFragment
	{
		TextView appConfig;
		TextView Category1;
		TextView Category2;
		TextView Category3;
		TextView Category4;
		TextView Content1;
		TextView Content2;
		TextView Content3;
		TextView Content4;
		
		boolean Exit;
		float VersionNumber;
		double BuildNumber;
		boolean DEBUG_MODE;
		boolean PRO;
		int numBookmarks;
		int DebugCounter;
		int ErrorCounter;
		
		double N;
		double V;
		double V1;
		double V2;
		double P;
		double P1;
		double P2;
		double T;
		double T1;
		double T2;
		String SelectV;
		String SelectV1;
		String SelectV2;
		String SelectP;
		String SelectP1;
		String SelectP2;
		String SelectT;
		String SelectT1;
		String SelectT2;
		
		String category;
		int subcategory;
		int subcategory2;
		String selectedTemperature;
		String selectedPressure;
		String selectedVolume;
		
		boolean showWork;
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
		String Convert_MaxDecimalAccuracy;
		String Convert_DefaultCategory;
		boolean Gas_SameUnits;
		boolean Gas_AutoSolve;
		boolean Gas_FormatHelp;
		boolean Convert_AutoSolve;
		boolean Convert_FormatHelp;
		boolean Periodic_ShowProgress;
		boolean Periodic_ClickableElements;
		String Periodic_Sort;
		boolean Periodic_ShowSelector;
		boolean Periodic_ShowSearch;
		boolean Quiz_NotifyCorrect;
		boolean Quiz_ShowTime;
		boolean Quiz_ShowPercent;
		boolean Quiz_ShowProgress;
		
		String testString;
		int testInteger;
		double testDouble;
		
		String updateDetails;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.debug_global, container, false);
			
			appConfig = (TextView) view.findViewById(R.id.AppConfig);
			Category1 = (TextView) view.findViewById(R.id.Category1);
			Category2 = (TextView) view.findViewById(R.id.Category2);
			Category3 = (TextView) view.findViewById(R.id.Category3);
			Category4 = (TextView) view.findViewById(R.id.Category4);
			Content1 = (TextView) view.findViewById(R.id.Content1);
			Content2 = (TextView) view.findViewById(R.id.Content2);
			Content3 = (TextView) view.findViewById(R.id.Content3);
			Content4 = (TextView) view.findViewById(R.id.Content4);
			
			refreshValues();
			
			appConfig.setText("Version Number: " + VersionNumber + "\n" + 
				  			  "Build Number: " + BuildNumber + "\n" + 
							  "Debug Mode: " + DEBUG_MODE + "\n" +
							  "PRO: " + PRO + "\n" + 
							  "Bookmarks #: " + numBookmarks + "\n" + 
							  "Error Count: " + ErrorCounter + "\n" + 
							  "Debug Count: " + DebugCounter);
			
			Category1.setText("Gas Laws");
			Category2.setText("Convert");
			
			Category3.setText("Settings");
			Category4.setText("Misc");
			
			Content1.setText("N: " + N + "\n" + 
							 "V: " + V + "\n" + 
							 "V1: " + V1 + "\n" + 
							 "V2: " + V2 + "\n" + 
							 "P: " + P + "\n" + 
							 "P1: " + P1 + "\n" + 
							 "P2: " + P2 + "\n" + 
							 "T: " + T + "\n" + 
							 "T1: " + T1 + "\n" + 
							 "T2: " + T2 + "\n" +  
							 "SelectV: " + SelectV + "\n" + 
							 "SelectV1: " + SelectV1 + "\n" + 
							 "SelectV2: " + SelectV2 + "\n" + 
							 "SelectP: " + SelectP + "\n" + 
							 "SelectP1: " + SelectP1 + "\n" + 
							 "SelectP2: " + SelectP2 + "\n" + 
							 "SelectT: " + SelectT + "\n" + 
							 "SelectT1: " + SelectT1 + "\n" + 
							 "SelectT2: " + SelectT2);
			
			Content2.setText( "Category: " + category + "\n" + 
							  "Subcategory: " + subcategory + "\n" + 
							  "Subcategory2: " + subcategory2 + "\n" + 
							  "selectedTemperature: " + selectedTemperature + "\n" + 
							  "selectedPressure: " + selectedPressure + "\n" + 
							  "selectedVolume: " + selectedVolume);
			
			Content3.setText("showWork: " + showWork + "\n" + 
							 "defaultTempUnit: " + defaultTempUnit + "\n" + 
							 "defaultTemp: " + defaultTemp + "\n" + 
						   	 "defaultTempS: " + defaultTempS + "\n" + 
							 "defaultPressureUnit: " + defaultPressureUnit + "\n" + 
							 "defaultPres: " + defaultPres + "\n" + 
							 "defaultPresS: " + defaultPresS + "\n" + 
							 "defaultVolumeUnit: " + defaultVolumeUnit + "\n" + 
							 "defaultVol: " + defaultVol + "\n" + 
							 "defaultVolS: " + defaultVolS + "\n" + 
							 "maxDecimalAccuracy: " + maxDecimalAccuracy + "\n" + 
							 "Gas_SameUnits: " + Gas_SameUnits + "\n" + 
							 "Gas_AutoSolve: " + Gas_AutoSolve + "\n" + 
							 "Gas_FormatHelp: " + Gas_FormatHelp + "\n" + 
							 "Convert_DefaultCategory: " + Convert_DefaultCategory + "\n" +
							 "Convert_MaxDecimalAccuracy: " + Convert_MaxDecimalAccuracy + "\n" +
							 "Convert_AutoSolve: " + Convert_AutoSolve + "\n" + 
							 "Convert_FormatHelp: " + Convert_FormatHelp + "\n" + 
							 "Periodic_ShowProgress: " + Periodic_ShowProgress + "\n" + 
							 "Periodic_ClickableElements: " + Periodic_ClickableElements + "\n" + 
							 "Periodic_Sort: " + Periodic_Sort + "\n" + 
							 "Periodic_ShowSelector: " + Periodic_ShowSelector + "\n" + 
							 "Periodic_ShowSearch: " + Periodic_ShowSearch + "\n" + 
							 "Quiz_NotifyCorrect: " + Quiz_NotifyCorrect + "\n" + 
							 "Quiz_ShowTime: " + Quiz_ShowTime + "\n" + 
							 "Quiz_ShowPercent: " + Quiz_ShowPercent + "\n" + 
							 "Quiz_ShowProgress: " + Quiz_ShowProgress);
			
			Content4.setText("Test String: " + testString + "\n" + 
				 			 "Test Integer: " + testInteger + "\n" + 
							 "Test Double: " + testDouble + "\n" + 
				 			 "updateDetails: " + updateDetails);

			return view;
		}
		
		public void refreshValues()
		{
			Exit = MySingleton.getInstance().getExit();
			VersionNumber = MySingleton.getInstance().getVersionNumber();
			BuildNumber = MySingleton.getInstance().getBuildNumber();
			DEBUG_MODE = MySingleton.getInstance().getDebugMode();
			PRO = MySingleton.getInstance().isPRO();
			numBookmarks = MySingleton.getInstance().getNumBookmarks();
			DebugCounter = MySingleton.getInstance().getLogCount();
			ErrorCounter = MySingleton.getInstance().getErrorCount();
			
			N = MySingleton.getInstance().getN();
			V = MySingleton.getInstance().getV();
			V1 = MySingleton.getInstance().getV1();
			V2 = MySingleton.getInstance().getV2();
			P = MySingleton.getInstance().getP();
			P1 = MySingleton.getInstance().getP1();
			P2 = MySingleton.getInstance().getP2();
			T = MySingleton.getInstance().getT();
			T1 = MySingleton.getInstance().getT1();
			T2 = MySingleton.getInstance().getT2();
			SelectV = MySingleton.getInstance().getSelectV();
			SelectV1 = MySingleton.getInstance().getSelectV1();
			SelectV2 = MySingleton.getInstance().getSelectV2();
			SelectP = MySingleton.getInstance().getSelectP();
			SelectP1 = MySingleton.getInstance().getSelectP1();
			SelectP2 = MySingleton.getInstance().getSelectP2();
			SelectT = MySingleton.getInstance().getSelectT();
			SelectT1 = MySingleton.getInstance().getSelectT1();
			SelectT2 = MySingleton.getInstance().getSelectT2();
			
			category = MySingleton.getInstance().getCategory();
			subcategory = MySingleton.getInstance().getSubcategory();
			subcategory2 = MySingleton.getInstance().getSubcategory2();
			selectedTemperature = MySingleton.getInstance().getSelectedTemperature();
			selectedPressure = MySingleton.getInstance().getSelectedPressure();
			selectedVolume = MySingleton.getInstance().getSelectedVolume();
			
			showWork = MySingleton.getInstance().getShowWork();
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
			Convert_MaxDecimalAccuracy = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
			Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
			Gas_SameUnits = MySingleton.getInstance().getGasSameUnits();
			Gas_AutoSolve = MySingleton.getInstance().getGasAutoSolve();
			Gas_FormatHelp = MySingleton.getInstance().getGasFormatHelp();
			Convert_AutoSolve = MySingleton.getInstance().getConvertAutoSolve();
			Convert_FormatHelp = MySingleton.getInstance().getConvertFormatHelp();
			
			Periodic_ShowProgress = MySingleton.getInstance().getPeriodicShowProgress();
			Periodic_ClickableElements = MySingleton.getInstance().getPeriodicClickableElements();
			Periodic_Sort = MySingleton.getInstance().getPeriodicSort();
			Periodic_ShowSelector = MySingleton.getInstance().getPeriodicShowSelector();
			Periodic_ShowSearch = MySingleton.getInstance().getPeriodicShowSearch();
			Quiz_NotifyCorrect = MySingleton.getInstance().getQuizNotifyCorrect();
			Quiz_ShowTime = MySingleton.getInstance().getQuizShowTime();
			Quiz_ShowPercent = MySingleton.getInstance().getQuizShowPercent();
			Quiz_ShowProgress = MySingleton.getInstance().getQuizShowProgress();
			
			testString = MySingleton.getInstance().getTestString();
			testInteger = MySingleton.getInstance().getTestInteger();
			testDouble = MySingleton.getInstance().getTestDouble();
			
			updateDetails = MySingleton.getInstance().getUpdateDetails();
		}
	}
	
	public static class SavedFragment extends SherlockFragment
	{
		private static String SettingsTAG = "ChemHelpSettings";
		private SharedPreferences settings;
		
		TextView appConfig;
		TextView Category1;
		TextView Category2;
		TextView Category3;
		TextView Category4;
		TextView Content1;
		TextView Content2;
		TextView Content3;
		TextView Content4;
		
		// App Config
		float VersionNumber;
		long lastUpdateTime;
		int remindLater;
		boolean firstTime;
		String UpdateDetails;
		
		// Gas Settings
		int defaultVolumeUnit;
		int defaultPressureUnit;
		int defaultTempUnit;
		String maxDecimalAccuracy;
		boolean Gas_SameUnits;
		boolean Gas_AutoSolve;
		boolean Gas_FormatHelp;
		
		// Convert Settings
		String Convert_DefaultCategory;
		String Convert_MaxDecimalAccuracy;
		boolean Convert_AutoSolve;
		boolean Convert_FormatHelp;
		
		// Periodic Settings
		boolean Periodic_ShowProgress;
		boolean Periodic_ClickableElements;
		String Periodic_Sort;
		boolean Periodic_ShowSelector;
		boolean Periodic_ShowSearch;
		
		// Quiz Settings
		boolean Quiz_NotifyCorrect;
		boolean Quiz_ShowTime;
		boolean Quiz_ShowPercent;
		boolean Quiz_ShowProgress;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.debug_saved, container, false);
			settings = getSharedPreferences(getActivity().getBaseContext());
			
			appConfig = (TextView) view.findViewById(R.id.AppConfig);
			Category1 = (TextView) view.findViewById(R.id.Category1);
			Category2 = (TextView) view.findViewById(R.id.Category2);
			Category3 = (TextView) view.findViewById(R.id.Category3);
			Category4 = (TextView) view.findViewById(R.id.Category4);
			Content1 = (TextView) view.findViewById(R.id.Content1);
			Content2 = (TextView) view.findViewById(R.id.Content2);
			Content3 = (TextView) view.findViewById(R.id.Content3);
			Content4 = (TextView) view.findViewById(R.id.Content4);
			
			getSavedValues();
			
			appConfig.setText("Version Number: " + VersionNumber + "\n" + 
							  "Last Update Time: " + lastUpdateTime + "\n" + 
					          "Remind Later: " + remindLater + "\n" + 
							  "First Time: " + firstTime + "\n" + 
					          "Update Details: " + UpdateDetails);
			
			Category1.setText("Gas Settings");
			
			Content1.setText("selectVolume: " + defaultVolumeUnit + "\n" + 
							 "selectPressure: " + defaultPressureUnit + "\n" + 
							 "selectTemperature: " + defaultTempUnit + "\n" + 
							 "GasSameUnits: " + Gas_SameUnits + "\n" + 
							 "GasAutoSolve: " + Gas_AutoSolve + "\n" + 
							 "GasFormatHelp: " + Gas_FormatHelp + "\n" + 
							 "selectedGasMaxDecimalAccuracy: " + maxDecimalAccuracy);
			
			Category2.setText("Convert Settings");
			
			Content2.setText("ConvertDefaultCategory: " + Convert_DefaultCategory + "\n" + 
							 "selectedConvertMaxDecimalAccuracy: " + Convert_MaxDecimalAccuracy + "\n" + 
							 "ConvertAutoSolve: " + Convert_AutoSolve + "\n" + 
							 "ConvertFormatHelp: " + Convert_FormatHelp);
			
			Category3.setText("Periodic Settings");
			
			Content3.setText("PeriodicShowProgress: " + Periodic_ShowProgress + "\n" + 
							 "PeriodicClickableElements: " + Periodic_ClickableElements + "\n" + 
						     "PeriodicSort: " + Periodic_Sort + "\n" + 
							 "PeriodicShowSelector: " + Periodic_ShowSelector + "\n" + 
							 "PeriodicShowSearch: " + Periodic_ShowSearch);
			
			Category4.setText("Quiz Settings");
			
			Content4.setText("QuizNotifyCorrect: " + Quiz_NotifyCorrect + "\n" + 
							 "QuizShowTime: " + Quiz_ShowTime + "\n" + 
							 "QuizShowPercent: " + Quiz_ShowPercent + "\n" + 
							 "QuizShowProgress: " + Quiz_ShowProgress);
			
			return view;
		}
		
		public void getSavedValues()
		{
			VersionNumber = settings.getFloat("RunningVersion", 0);
			lastUpdateTime = settings.getLong("LastUpdateTime", 0);
			remindLater = settings.getInt("RemindLater", 0);
			firstTime = settings.getBoolean("FirstTime", true);
			UpdateDetails = settings.getString("UpdateDetails", "Value");
			
			defaultVolumeUnit = settings.getInt("selectVolume", 0);
			defaultPressureUnit = settings.getInt("selectPressure", 0);
			defaultTempUnit = settings.getInt("selectTemperature", 0);
			Gas_SameUnits = settings.getBoolean("GasSameUnits", true);
			Gas_AutoSolve = settings.getBoolean("GasAutoSolve", false);
			Gas_FormatHelp = settings.getBoolean("GasFormatHelp", false);
			maxDecimalAccuracy = settings.getString("selectedGasMaxDecimalAccuracy", "5");
			
			Convert_DefaultCategory = settings.getString("ConvertDefaultCategory", "Blank");
			Convert_MaxDecimalAccuracy = settings.getString("selectedConvertMaxDecimalAccuracy", "5");
			Convert_AutoSolve = settings.getBoolean("ConvertAutoSolve", true);
			Convert_FormatHelp = settings.getBoolean("ConvertFormatHelp", true);
			
			Periodic_ShowProgress = settings.getBoolean("PeriodicShowProgress", true);
			Periodic_ClickableElements = settings.getBoolean("PeriodicClickableElements", true);
			Periodic_Sort = settings.getString("PeriodicSort", "Symbol");
			Periodic_ShowSelector = settings.getBoolean("PeriodicShowSelector", true);
			Periodic_ShowSearch = settings.getBoolean("PeriodicShowSearch", true);
			
			Quiz_NotifyCorrect = settings.getBoolean("QuizNotifyCorrect", false);
			Quiz_ShowTime = settings.getBoolean("QuizShowTime", true);
			Quiz_ShowPercent = settings.getBoolean("QuizShowPercent", true);
			Quiz_ShowProgress = settings.getBoolean("QuizShowProgress", true);
		}
		
		// Workaround...
		public static SharedPreferences getSharedPreferences (Context context)
		{
			   return context.getSharedPreferences(SettingsTAG, 0);
		}
	}
	
	public static class LogFragment extends SherlockFragment
	{
		TextView errorLog;
		TextView debugLog;
		Button clearError;
		Button testError;
		Button clearDebug;
		Button testDebug;
		
		String[] errorArray;
		String[] debugArray;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.debug_log, container, false);
			
			debugArray = MySingleton.getInstance().getLog();
			errorArray = MySingleton.getInstance().getErrors();
			
			errorLog = (TextView) view.findViewById(R.id.textErrorLog);
			debugLog = (TextView) view.findViewById(R.id.textDebugLog);
			clearError = (Button) view.findViewById(R.id.errorClear);
			testError = (Button) view.findViewById(R.id.errorTest);
			clearDebug = (Button) view.findViewById(R.id.debugClear);
			testDebug = (Button) view.findViewById(R.id.debugTest);
			
			refreshDebugLog();
			refreshErrorLog();
			
			clearError.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					MySingleton.getInstance().clearErrors();
					errorLog.setText("No Values...");
				}
			});
			
			clearDebug.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					MySingleton.getInstance().clearLog();
					debugLog.setText("No Values...");
				}
			});
			
			testError.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					MySingleton.getInstance().addError("Test");
					refreshErrorLog();
				}
			});
			
			testDebug.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					MySingleton.getInstance().addLog("Test");
					refreshDebugLog();
				}
			});
			
			return view;
		}
		
		public void refreshDebugLog()
		{
			String log = "";
			
			for(int x = 0; x < 250; x++)
			{
				if(!(debugArray[x].equals("pcx_value")))
					log += "[" + (x + 1) + "] " + debugArray[x] + "\n"; 
			}
			
			if(log.equals(""))
				log = "No values...";
			
			debugLog.setText(log);
		}
		
		public void refreshErrorLog()
		{
			String log = "";
			
			for(int x = 0; x < 100; x++)
			{
				if(!(errorArray[x].equals("pcx_value")))
					log += "[" + (x + 1) + "] " + errorArray[x] + "\n"; 
			}
			
			if(log.equals(""))
				log = "No values...";
			
			errorLog.setText(log);
		}
	}
	
	public class AndroidFragment extends SherlockListFragment
	{
		
		/** An array of items to display in ArrayList */
		String android_versions[] = new String[] { "Jelly Bean", "IceCream Sandwich", "HoneyComb", "Ginger Bread", "Froyo" };
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			/** Creating array adapter to set data in listview */
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, android_versions);
			
			/** Setting the array adapter to the listview */
			setListAdapter(adapter);
			
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		@Override
		public void onStart()
		{
			super.onStart();
			/** Setting the multiselect choice mode for the listview */
			getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		}
	}
	
	public class AppleFragment extends SherlockFragment
	{
		
		/** An array of items to display in ArrayList */
		//String apple_versions[] = new String[] { "Mountain Lion", "Lion", "Snow Leopard", "Leopard", "Tiger", "Panther", "Jaguar", "Puma" };
		Button AFragment;
		TextView text;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			/** Creating array adapter to set data in listview */
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, apple_versions);
			
			/** Setting the array adapter to the listview */
			//setListAdapter(adapter);
			
			//View view = inflater.inflate(R.layout.afragment, container, false);
			//AFragment = (Button) view.findViewById(R.id.button1);
			//text = (TextView) view.findViewById(R.id.textView1);
			
			//return inflater.inflate(R.layout.afragment, container, false);
			
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		@Override
		public void onStart()
		{
			super.onStart();
			
			/** Setting the multiselect choice mode for the listview */
			//getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			
		}
		
		public void myClickMethod(View v)
		{
			Toast.makeText(Debug.this, "I LIEK FRUGMENTZZ!!", Toast.LENGTH_LONG).show();
			
			text.setVisibility(View.GONE);
		}
	}
	
	public static class MyFragmentPagerAdapter extends FragmentPagerAdapter
	{
		final int PAGE_COUNT = 4;
		
		//Button btn;
		
		/** Constructor of the class */
		public MyFragmentPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}
		
		/** This method will be invoked when a page is requested to create */
		@Override
		public Fragment getItem(int arg0)
		{
			Bundle data = new Bundle();
			
			switch (arg0)
			{
				case 0:
					ControlFragment controlFragment = new ControlFragment();
					data.putInt("current_page", arg0 + 1);
					controlFragment.setArguments(data);
					return controlFragment;
				case 1:
					GlobalFragment globalFragment = new GlobalFragment();
					data.putInt("current_page", arg0 + 1);
					globalFragment.setArguments(data);
					return globalFragment;
				case 2:
					SavedFragment savedFragment = new SavedFragment();
					data.putInt("current_page", arg0 + 1);
					savedFragment.setArguments(data);
					return savedFragment;
				case 3:
					LogFragment logFragment = new LogFragment();
					data.putInt("current_page", arg0 + 1);
					logFragment.setArguments(data);
					return logFragment;
			}
			return null;
		}
		
		@Override
		public int getCount()
		{
			return PAGE_COUNT;
		}
	}
}
