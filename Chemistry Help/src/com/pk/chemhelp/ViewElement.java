package com.pk.chemhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class ViewElement extends SherlockFragmentActivity
{
	final String PageID = "View Element";
	boolean Exit = false;
	
	public MyAdapter mAdapter;
	private static ViewPager mPager;
	static Chemical[] Chem;
	
	String[] pageValues;
	String pageValue1;
	int ElementPosition;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewelement);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		Chem = Chemical.getElements("Atomic Number");
		pageValue1 = "";
		
		mAdapter = new MyAdapter(getSupportFragmentManager());
		
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		
		if(intentValues.hasExtra("Element Position"))
		{
			extraBundle = getIntent().getExtras();
			ElementPosition = extraBundle.getInt("Element Position") - 1;

			mPager.setCurrentItem(ElementPosition);
		}
		else if(intentValues.hasExtra("Page Values"))
		{
			extraBundle = getIntent().getExtras();
			pageValues = extraBundle.getStringArray("Page Values");

			setPageValues();
		}
		else
			MySingleton.getInstance().addError("Incorrect Element View Position");
		
		
		//mPager.setPageMargin(-50);
		//mPager.setHorizontalFadingEdgeEnabled(true);
		//mPager.setFadingEdgeLength(50);
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
			pageValue1 = "" + mPager.getCurrentItem();
			MySingleton.getInstance().setPageValues(getPageValues());
			MySingleton.getInstance().setPreviousPageID(PageID);
			Dialogs.getDialog("Add Bookmark", ViewElement.this).show();
			return true;
		case R.id.Bookmarks_Label:
			Dialogs.getDialog("Bookmarks", ViewElement.this).show();
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
			Intent i = new Intent(ViewElement.this, Debug.class);
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
	
	public static ViewPager getPager()
	{
		return mPager;
	}

	public static void setPager(ViewPager mPager)
	{
		mPager = mPager;
	}
	
	public String[] getPageValues()
	{
		String[] values = new String[50];
		for(int l = 0; l < 50; l++)
			values[l] = "pcx_value";

		values[0] = PageID;
		values[1] = pageValue1;

		return values;
	}
	
	public void setPageValues()
	{
		if(pageValues[0].equals(PageID))
		{
			mPager.setCurrentItem(Integer.parseInt(pageValues[1]));
		}
	}
	
	public void showError()
	{
		Toast.makeText(ViewElement.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}

	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}

	public static class MyAdapter extends FragmentPagerAdapter
	{
		public MyAdapter(FragmentManager fm)
		{
			super(fm);
		}
		
		@Override
		public int getCount()
		{
			return 118;
		}
		
		@Override
		public Fragment getItem(int position)
		{
			switch (position)
			{
				case 0:
					return new ElementFragment(Chem[0]);
				case 1:
					return new ElementFragment(Chem[1]);
				case 2:
					return new ElementFragment(Chem[2]);
				case 3:
					return new ElementFragment(Chem[3]);
				case 4:
					return new ElementFragment(Chem[4]);
				case 5:
					return new ElementFragment(Chem[5]);
				case 6:
					return new ElementFragment(Chem[6]);
				case 7:
					return new ElementFragment(Chem[7]);
				case 8:
					return new ElementFragment(Chem[8]);
				case 9:
					return new ElementFragment(Chem[9]);
				case 10:
					return new ElementFragment(Chem[10]);
				case 11:
					return new ElementFragment(Chem[11]);
				case 12:
					return new ElementFragment(Chem[12]);
				case 13:
					return new ElementFragment(Chem[13]);
				case 14:
					return new ElementFragment(Chem[14]);
				case 15:
					return new ElementFragment(Chem[15]);
				case 16:
					return new ElementFragment(Chem[16]);
				case 17:
					return new ElementFragment(Chem[17]);
				case 18:
					return new ElementFragment(Chem[18]);
				case 19:
					return new ElementFragment(Chem[19]);
				case 20:
					return new ElementFragment(Chem[20]);
				case 21:
					return new ElementFragment(Chem[21]);
				case 22:
					return new ElementFragment(Chem[22]);
				case 23:
					return new ElementFragment(Chem[23]);
				case 24:
					return new ElementFragment(Chem[24]);
				case 25:
					return new ElementFragment(Chem[25]);
				case 26:
					return new ElementFragment(Chem[26]);
				case 27:
					return new ElementFragment(Chem[27]);
				case 28:
					return new ElementFragment(Chem[28]);
				case 29:
					return new ElementFragment(Chem[29]);
				case 30:
					return new ElementFragment(Chem[30]);
				case 31:
					return new ElementFragment(Chem[31]);
				case 32:
					return new ElementFragment(Chem[32]);
				case 33:
					return new ElementFragment(Chem[33]);
				case 34:
					return new ElementFragment(Chem[34]);
				case 35:
					return new ElementFragment(Chem[35]);
				case 36:
					return new ElementFragment(Chem[36]);
				case 37:
					return new ElementFragment(Chem[37]);
				case 38:
					return new ElementFragment(Chem[38]);
				case 39:
					return new ElementFragment(Chem[39]);
				case 40:
					return new ElementFragment(Chem[40]);
				case 41:
					return new ElementFragment(Chem[41]);
				case 42:
					return new ElementFragment(Chem[42]);
				case 43:
					return new ElementFragment(Chem[43]);
				case 44:
					return new ElementFragment(Chem[44]);
				case 45:
					return new ElementFragment(Chem[45]);
				case 46:
					return new ElementFragment(Chem[46]);
				case 47:
					return new ElementFragment(Chem[47]);
				case 48:
					return new ElementFragment(Chem[48]);
				case 49:
					return new ElementFragment(Chem[49]);
				case 50:
					return new ElementFragment(Chem[50]);
				case 51:
					return new ElementFragment(Chem[51]);
				case 52:
					return new ElementFragment(Chem[52]);
				case 53:
					return new ElementFragment(Chem[53]);
				case 54:
					return new ElementFragment(Chem[54]);
				case 55:
					return new ElementFragment(Chem[55]);
				case 56:
					return new ElementFragment(Chem[56]);
				case 57:
					return new ElementFragment(Chem[57]);
				case 58:
					return new ElementFragment(Chem[58]);
				case 59:
					return new ElementFragment(Chem[59]);
				case 60:
					return new ElementFragment(Chem[60]);
				case 61:
					return new ElementFragment(Chem[61]);
				case 62:
					return new ElementFragment(Chem[62]);
				case 63:
					return new ElementFragment(Chem[63]);
				case 64:
					return new ElementFragment(Chem[64]);
				case 65:
					return new ElementFragment(Chem[65]);
				case 66:
					return new ElementFragment(Chem[66]);
				case 67:
					return new ElementFragment(Chem[67]);
				case 68:
					return new ElementFragment(Chem[68]);
				case 69:
					return new ElementFragment(Chem[69]);
				case 70:
					return new ElementFragment(Chem[70]);
				case 71:
					return new ElementFragment(Chem[71]);
				case 72:
					return new ElementFragment(Chem[72]);
				case 73:
					return new ElementFragment(Chem[73]);
				case 74:
					return new ElementFragment(Chem[74]);
				case 75:
					return new ElementFragment(Chem[75]);
				case 76:
					return new ElementFragment(Chem[76]);
				case 77:
					return new ElementFragment(Chem[77]);
				case 78:
					return new ElementFragment(Chem[78]);
				case 79:
					return new ElementFragment(Chem[79]);
				case 80:
					return new ElementFragment(Chem[80]);
				case 81:
					return new ElementFragment(Chem[81]);
				case 82:
					return new ElementFragment(Chem[82]);
				case 83:
					return new ElementFragment(Chem[83]);
				case 84:
					return new ElementFragment(Chem[84]);
				case 85:
					return new ElementFragment(Chem[85]);
				case 86:
					return new ElementFragment(Chem[86]);
				case 87:
					return new ElementFragment(Chem[87]);
				case 88:
					return new ElementFragment(Chem[88]);
				case 89:
					return new ElementFragment(Chem[89]);
				case 90:
					return new ElementFragment(Chem[90]);
				case 91:
					return new ElementFragment(Chem[91]);
				case 92:
					return new ElementFragment(Chem[92]);
				case 93:
					return new ElementFragment(Chem[93]);
				case 94:
					return new ElementFragment(Chem[94]);
				case 95:
					return new ElementFragment(Chem[95]);
				case 96:
					return new ElementFragment(Chem[96]);
				case 97:
					return new ElementFragment(Chem[97]);
				case 98:
					return new ElementFragment(Chem[98]);
				case 99:
					return new ElementFragment(Chem[99]);
				case 100:
					return new ElementFragment(Chem[100]);
				case 101:
					return new ElementFragment(Chem[101]);
				case 102:
					return new ElementFragment(Chem[102]);
				case 103:
					return new ElementFragment(Chem[103]);
				case 104:
					return new ElementFragment(Chem[104]);
				case 105:
					return new ElementFragment(Chem[105]);
				case 106:
					return new ElementFragment(Chem[106]);
				case 107:
					return new ElementFragment(Chem[107]);
				case 108:
					return new ElementFragment(Chem[108]);
				case 109:
					return new ElementFragment(Chem[109]);
				case 110:
					return new ElementFragment(Chem[110]);
				case 111:
					return new ElementFragment(Chem[111]);
				case 112:
					return new ElementFragment(Chem[112]);
				case 113:
					return new ElementFragment(Chem[113]);
				case 114:
					return new ElementFragment(Chem[114]);
				case 115:
					return new ElementFragment(Chem[115]);
				case 116:
					return new ElementFragment(Chem[116]);
				case 117:
					return new ElementFragment(Chem[117]);
					
				default:
					MySingleton.getInstance().addError("Default View Element Array");
					return new ElementFragment(Chem[53]);
			}
		}
	}
}
