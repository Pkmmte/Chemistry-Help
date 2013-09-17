package com.pk.chemhelp;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

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
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class ChemistryHelp extends SherlockFragmentActivity
{
	private String SettingsTAG = "ChemHelpSettings";
	private SharedPreferences settings;
	final String PageID = "Chemistry Help";

	protected DataStorage appState;
	Handler uHandler;

	ActionBar actionBar;
	ViewPager mPager;

	Tab toolsTab;
	Tab studyTab;

	ToolsFragment toolsFragment;
	StudyFragment studyFragment;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;

	/* Version Comparison */
	//final float VersionNumber = MySingleton.getInstance().getVersionNumber();
	float RunningVersion;
	long lastUpdateTime;
	int remindLater;

	/* Pro */
	boolean PRO = MySingleton.getInstance().isPRO();

	/* Get us out of here! */
	private boolean Exit;

	/* Dialogs For Updates/Welcome */
	private boolean firstTime = true;
	private boolean justUpdated = false;

	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	boolean FORCE_STOP;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		settings = getSharedPreferences(SettingsTAG, 0);
		appState = (DataStorage) getApplication();
		
		
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		mPager = (ViewPager) findViewById(R.id.pager);
		uHandler = new Handler();

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

		/** Creating an instance of FragmentPagerAdapter **/
		mPager.setOnPageChangeListener(pageChangeListener);

		FragmentManager fm = getSupportFragmentManager();
		MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);

		toolsFragment = new ToolsFragment();
		studyFragment = new StudyFragment();

		/** Setting the FragmentPagerAdapter object to the viewPager object **/
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

		toolsTab = actionBar.newTab().setText("Tools").setTabListener(tabListener);
		studyTab = actionBar.newTab().setText("Study").setTabListener(tabListener);

		actionBar.addTab(toolsTab);
		actionBar.addTab(studyTab);

		actionBar.setSubtitle("BETA");

		//checkPro();
		if(PRO)
			actionBar.setSubtitle("PRO");

		appState = (DataStorage) getApplication();
		Bookmark.loadBookmarks(ChemistryHelp.this);

		checkFirstTime();

		Misc.restoreValues(settings, SettingsTAG);
        lastUpdateTime =  settings.getLong("LastUpdateTime", 0);
		remindLater = settings.getInt("RemindLater", 0);

		if (firstTime)
		{
			Editor editor = settings.edit();
			firstTime = false;
			editor.putBoolean("FirstTime", firstTime);
			editor.commit();
			Dialogs.getDialog("First Time", ChemistryHelp.this).show();
		}
		checkVersion();

		/*if (justUpdated)
		{
			Dialogs.getDialog("Change Log", ChemistryHelp.this).show();
			Editor editor = settings.edit();
			editor.putFloat("RunningVersion", VersionNumber);
			editor.commit();
		}
		else
		{
			Editor editor = settings.edit();
			editor.putFloat("RunningVersion", VersionNumber);
			editor.commit();
		}*/

		// Should Activity Check for Updates Now?
        if ((lastUpdateTime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis())
        {
        	if(isNetworkConnected() && !(checkUpdate.isAlive()))
            {
				lastUpdateTime = System.currentTimeMillis();            
            	Editor editor = settings.edit();
            	editor.putLong("LastUpdateTime", lastUpdateTime);
            	editor.commit();

            	MySingleton.getInstance().addLog("Yes connected!");
            	checkUpdate.start();
			}
			else
				MySingleton.getInstance().addLog("No network...");
        }
	}

	@Override
	protected void onRestoreInstanceState(Bundle outState)
	{
		super.onRestoreInstanceState(outState);
	}

	public void onRestart()
	{
		super.onRestart();
		Bookmark.loadBookmarks(getApplicationContext());

		FORCE_STOP = MySingleton.getInstance().getFS();
		Exit = MySingleton.getInstance().getExit();
		if (FORCE_STOP)
			System.exit(0);
		if (Exit)
			Exit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		/*
		DEBUG_MODE = settings.getBoolean("DebugMode", false);
		MySingleton.getInstance().setDebugMode(DEBUG_MODE);
		
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
			debugMenu.setVisible(false);*/

		return true;
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
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				Toast.makeText(ChemistryHelp.this, "You cannot bookmark this page.", Toast.LENGTH_LONG).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", ChemistryHelp.this).show();
				return true;
			case R.id.Settings_Label:
				MySingleton.getInstance().setPreviousPageID(PageID);
				Intent settingsIntent = new Intent(this, Settings.class);
				settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(settingsIntent);
				return true;
				/*case R.id.Help_Label:
				 Bookmarks = QuickBookmark.loadBookmarks(getApplicationContext());
				 numBookmarks = MySingleton.getInstance().getNumBookmarks();
				 Toast.makeText(ChemistryHelp.this, "Reading..." , Toast.LENGTH_SHORT).show();
				 //helpDialog.show();
				 return true;
				 case R.id.About_Label:
				 Dialogs.getDialog("About", ChemistryHelp.this);
				 return true;*/
			case R.id.Exit_Label:
				Exit();
				return true;
			case R.id.Debug_Label:
				Intent i = new Intent(ChemistryHelp.this, Debug.class);
				startActivity(i);
				return true;
			default:

				return super.onOptionsItemSelected(item);
		}
	}
	
	/*@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB && keyCode == KeyEvent.KEYCODE_MENU)
		{
			mainMenu.performIdentifierAction(subMenu.getItem().getItemId(), 0);
            return true;
		}
		return super.onKeyUp(keyCode, event);
	}*/

	public void checkFirstTime()
	{
		firstTime = settings.getBoolean("FirstTime", true);
	}



	public void checkVersion()
	{
		RunningVersion = settings.getFloat("RunningVersion", 0.7F);
		//if (RunningVersion != VersionNumber) ////////
			justUpdated = true;
	}

	/*public void checkPro()
	{
		PRO = settings.getBoolean("PRO", false);
		MySingleton.getInstance().setPRO(PRO);
	}*/

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

                // Convert the Bytes read to a String and decrypt it.
                final String s = new String(baf.toByteArray());
                String[] updateInfo = s.split("PCX");

                MySingleton.getInstance().setUpdateDetails(updateInfo[2]);

                int curVersion = getPackageManager().getPackageInfo("com.pk.chemhelp", 0).versionCode;
                int newVersion = Integer.valueOf(updateInfo[0]);

                if (newVersion > curVersion)
                {
                	uHandler.post(showUpdate);
                }
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

            Dialogs.getDialog("Update Available", ChemistryHelp.this).show();
    	}
    };

	public void toolsSelection(View v)
	{
		String Type = v.getTag().toString();
		Intent select;

		if(Type.equals("Gas Laws"))
			select = new Intent(ChemistryHelp.this, GasLaws.class);
		else if(Type.equals("Molar Mass"))
			select = new Intent(ChemistryHelp.this, MolarMass.class);
		else if(Type.equals("Periodic Table"))
			select = new Intent(ChemistryHelp.this, PeriodicTable.class);
		else if(Type.equals("Chemical Search"))
			select = new Intent(ChemistryHelp.this, ChemicalSearch.class);
		else
			select = new Intent(ChemistryHelp.this, Convert.class);

		startActivity(select);
	}
	
	public void studySelection(View v)
	{
		String Type = v.getTag().toString();
		Intent select;
		
		//if(Type.equals("Quiz"))
			select = new Intent(ChemistryHelp.this, Quiz.class);
		
		startActivity(select);
	}

	public void showError()
	{
		Toast.makeText(ChemistryHelp.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}

	public void Exit()
	{
		Exit = false;
		MySingleton.getInstance().setExit(Exit);
		MySingleton.getInstance().clearErrors();

		finish();
	}

	public static class MyFragmentPagerAdapter extends FragmentPagerAdapter
	{
		final int PAGE_COUNT = 2;

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
					ToolsFragment toolsFragment = new ToolsFragment();
					data.putInt("current_page", arg0 + 1);
					toolsFragment.setArguments(data);
					return toolsFragment;
				case 1:
					StudyFragment studyFragment = new StudyFragment();
					data.putInt("current_page", arg0 + 1);
					studyFragment.setArguments(data);
					return studyFragment;
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






	/*
	public void Update(String apkurl)
	{
		try
		{
			URL url = new URL(apkurl);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET"); c.setDoOutput(true); c.connect();

			String PATH = Environment.getExternalStorageDirectory() + "/download/";
			File file = new File(PATH);
			file.mkdirs();
			File outputFile = new File(file, "app.apk");
			FileOutputStream fos = new FileOutputStream(outputFile);

			InputStream is = c.getInputStream();

			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) != -1)
			{
				fos.write(buffer, 0, len1);
			}
			fos.close();
			is.close();//till here, it works fine - .apk is download to my sdcard in download file

			Intent promptInstall = new Intent(Intent.ACTION_VIEW)
			.setData(Uri.parse(PATH+"app.apk"))
			.setType("application/android.com.app");
			startActivity(promptInstall);//installation is not working
		}
		catch (IOException e)
		{
			Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_LONG).show();
		}
	}*/
