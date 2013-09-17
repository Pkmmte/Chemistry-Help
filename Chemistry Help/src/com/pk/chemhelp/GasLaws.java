package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import java.util.ArrayList;
import java.util.List;

public class GasLaws extends SherlockActivity
{
	private boolean Exit;
	final String PageID = "Gas Laws";
	Bookmark BookmarkMethod;
	Bookmark[] Bookmarks;
	int numBookmarks;
	
	/* Bookmarks */
	String[] pageValues;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_out);
		setContentView(R.layout.gaslaws);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];

		numBookmarks = MySingleton.getInstance().getNumBookmarks();
		Bookmarks = MySingleton.getInstance().getBookmarks();
		
		ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		
		// Menu Items
		final List<GasLawsItem> listOfItems = new ArrayList<GasLawsItem>();
		listOfItems.add(new GasLawsItem("Boyle's Law", "P1V1 = P2V2", "boyles"));
		listOfItems.add(new GasLawsItem("Charle's Law", "V1/T1 = V2/T2", "charles"));
		listOfItems.add(new GasLawsItem("Gay Lussac's Law", "P1T2 = P2T1", "gay"));
		listOfItems.add(new GasLawsItem("Ideal Gas Law", "PV = nRT", "ideal"));
		listOfItems.add(new GasLawsItem("Combined Gas Law", "P1V1/T1 = P2V2/T2", "combined"));
		
		GasLawsAdapter adapter = new GasLawsAdapter(this, listOfItems);
		
		// Red Divider
		int[] colors = { 0, 0xFF00FFFF, 0 };
		list.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		list.setDividerHeight(1);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				System.out.println("sadsfsf");
				String ID = listOfItems.get(position).getIcon();
				Intent selectedLaw;
				if (ID.equals("boyles"))
					selectedLaw = new Intent(GasLaws.this, BoylesLaw.class);
				else if (ID.equals("charles"))
					selectedLaw = new Intent(GasLaws.this, CharlesLaw.class);
				else if (ID.equals("ideal"))
					selectedLaw = new Intent(GasLaws.this, IdealGasLaw.class);
				else if (ID.equals("gay"))
					selectedLaw = new Intent(GasLaws.this, GayLussacsLaw.class);
				else if (ID.equals("combined"))
					selectedLaw = new Intent(GasLaws.this, CombinedGasLaw.class);
				else
					selectedLaw = new Intent(GasLaws.this, ChemistryHelp.class);
				startActivity(selectedLaw);
				//showToast(listOfItems.get(position).getItemName());
			}
		});
		
		list.setAdapter(adapter);
		
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
				Intent intent = new Intent(GasLaws.this, ChemistryHelp.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.Warning_Label:
				showError();
				return true;
			case R.id.AddBookmark_Label:
				MySingleton.getInstance().setPageValues(getPageValues());
				MySingleton.getInstance().setPreviousPageID(PageID);
				Dialogs.getDialog("Add Bookmark", GasLaws.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", GasLaws.this).show();
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
				Intent i = new Intent(GasLaws.this, Debug.class);
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
		Toast.makeText(GasLaws.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
	
	public class GasLawsAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<GasLawsItem> listItem;

		public GasLawsAdapter(Context context, List<GasLawsItem> listItem)
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
			GasLawsItem entry = listItem.get(position);
			if (convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.gaslaws_row, null);
			}

			ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
			String icon = entry.getIcon();
			if (icon.equals("boyles"))
				iconImage.setImageResource(R.drawable.boyles_icon);
			else if (icon.equals("charles"))
				iconImage.setImageResource(R.drawable.charles_icon);
			else if (icon.equals("ideal"))
				iconImage.setImageResource(R.drawable.ideal_icon);
			else if (icon.equals("gay"))
				iconImage.setImageResource(R.drawable.lussac_icon);
			else if (icon.equals("combined"))
				iconImage.setImageResource(R.drawable.combined_icon);

			TextView Item = (TextView) convertView.findViewById(R.id.Item);
			Item.setText(entry.getItemName());

			TextView Description = (TextView) convertView.findViewById(R.id.Description);
			if(entry.getDescription().equals("P1V1 = P2V2"))
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>"));
			else if(entry.getDescription().equals("V1/T1 = V2/T2"))
				Description.setText(Html.fromHtml("V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
			else if(entry.getDescription().equals("P1T2 = P2T1"))
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>T<sub><small>2</small></sub> = P<sub><small>2</small></sub>T<sub><small>1</small></sub>"));
			else if(entry.getDescription().equals("PV = nRT"))
				Description.setText(Html.fromHtml("PV = nRT"));
			else if(entry.getDescription().equals("P1V1/T1 = P2V2/T2"))
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
			else
				Description.setText(entry.getDescription());

			return convertView;
		}

		@Override
		public void onClick(View view)
		{
			GasLawsItem entry = (GasLawsItem) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();

		}
	}
}
