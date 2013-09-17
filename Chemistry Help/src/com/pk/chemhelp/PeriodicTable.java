package com.pk.chemhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class PeriodicTable extends SherlockFragmentActivity
{
	protected DataStorage appState;
	final String PageID = "Periodic Table";
	
	/* Exit */
	private boolean Exit;

	/* Bookmarks */
	String[] pageValues;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;

	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();

	public void TestMethod()
	{
		Intent webIntent = new Intent(PeriodicTable.this, Settings.class);
		webIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(webIntent);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.periodictable);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab TableTab = actionBar.newTab().setText("Table");
		Tab ElementTab = actionBar.newTab().setText("List");

		PeriodicTableFragment periodicTableFragment = new PeriodicTableFragment();
		PeriodicElementsFragment periodicElementsFragment = new PeriodicElementsFragment();

		TableTab.setTabListener(new MyTabsListener(periodicTableFragment));
		ElementTab.setTabListener(new MyTabsListener(periodicElementsFragment));

		actionBar.addTab(TableTab);
		actionBar.addTab(ElementTab);
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];
		
		appState = (DataStorage) getApplication();
		
		if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");

			setPageValues();
		}
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
			MySingleton.getInstance().setPageValues(getPageValues());
			MySingleton.getInstance().setPreviousPageID(PageID);
			Dialogs.getDialog("Add Bookmark", PeriodicTable.this).show();
			return true;
		case R.id.Bookmarks_Label:
			Dialogs.getDialog("Bookmarks", PeriodicTable.this).show();
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
			Intent i = new Intent(PeriodicTable.this, Debug.class);
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
	
	public String[] getPageValues()
	{
		String[] values = new String[50];
		for(int l = 0; l < 50; l++)
			values[l] = "pcx_value";

		values[0] = PageID;

		return values;
	}

	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			// Nothing to do here...
		}
	}
	
	public void showError()
	{
		Toast.makeText(PeriodicTable.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_LONG).show();
	}

	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
	
	public static class MyTabsListener implements ActionBar.TabListener
	{
		public Fragment fragment;

		public MyTabsListener(Fragment fragment)
		{
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft)
		{
			// Do Nothing
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft)
		{
			ft.replace(R.id.fragment_container, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft)
		{
			ft.remove(fragment);
		}
	}
}
