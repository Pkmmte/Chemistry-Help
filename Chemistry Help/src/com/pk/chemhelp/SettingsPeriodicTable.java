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

public class SettingsPeriodicTable extends SherlockActivity
{
	protected DataStorage appState;
	private String SettingsTAG = "ChemHelpSettings";
	private SharedPreferences settings;
	
	// Globals
	boolean Periodic_ShowProgress;
	boolean Periodic_ClickableElements;
	String Periodic_Sort;
	boolean Periodic_ShowSelector;
	boolean Periodic_ShowSearch;
	
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
	
	// Variables
	String Name;
	String Description;
	String Value;
	boolean Check;
	
	// Local Variables
	String ShowProgressName = "Show Loading";
	String ClickElementName = "Clickable Elements";
	String SortName = "Sort By";
	String ShowSelectorName = "Show Selector";
	String ShowSearchName = "Show Search";
	
	String ShowProgressDescription = "Show progress bar when loading...";
	String ClickElementDescription = "Click element for more details...";
	String SortDescription = "Prefered list order...";
	String ShowSelectorDescription = "Toggle selection bar on the right...";
	String ShowSearchDescription = "Show search bar on top...";
	
	String ShowProgressValue = "true";
	String ClickElementValue = "true";
	String SortValue = "Symbol";
	String ShowSelectorValue = "true";
	String ShowSearchValue = "true";
	
	final List<SettingsItem> listOfItems = new ArrayList<SettingsItem>();
	SettingsPeriodicTableAdapter adapter = new SettingsPeriodicTableAdapter(this, listOfItems);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Periodic Table Settings");
		
		initializeGlobals();
		
		selectionDialog = new Dialog(SettingsPeriodicTable.this);
		selectionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		selectionDialog.setContentView(R.layout.dialog_selectionmenu);
		selectionDialog.setCancelable(true);
		
		addListenerOnButtons();
		
		Relative1 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative1);
		Relative2 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative2);
		Relative3 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative3);
		Relative4 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative4);
		Relative5 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative5);
		Relative6 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative6);
		Relative7 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative7);
		
		final ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		list.setDividerHeight(0);
		
		setSelections();
		
		// Menu Items
		listOfItems.add(new SettingsItem(true, false, true, ShowProgressName, ShowProgressDescription, ShowProgressValue));
		listOfItems.add(new SettingsItem(false, true, true, ClickElementName, ClickElementDescription, ClickElementValue));
		listOfItems.add(new SettingsItem(true, false, false, SortName, SortDescription, SortValue));
		listOfItems.add(new SettingsItem(false, false, true, ShowSelectorName, ShowSelectorDescription, ShowSelectorValue));
		listOfItems.add(new SettingsItem(false, true, true, ShowSearchName, ShowSearchDescription, ShowSearchValue));
		
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
					if (Item.equals(ShowProgressName))
					{
						if (ShowProgressValue.equals("false"))
						{
							MySingleton.getInstance().setPeriodicShowProgress(true);
							Value = "true";
						}
						else if (ShowProgressValue.equals("true"))
						{
							MySingleton.getInstance().setPeriodicShowProgress(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(true, false, true, ShowProgressName, ShowProgressDescription, Value));
						adapter.notifyDataSetChanged();
						ShowProgressValue = Value;
						savePreferences();
					}
					else if (Item.equals(ClickElementName))
					{
						if (ClickElementValue.equals("false"))
						{
							MySingleton.getInstance().setPeriodicClickableElements(true);
							Value = "true";
						}
						else if (ClickElementValue.equals("true"))
						{
							MySingleton.getInstance().setPeriodicClickableElements(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, ClickElementName, ClickElementDescription, Value));
						adapter.notifyDataSetChanged();
						ClickElementValue = Value;
						savePreferences();
					}
					else if (Item.equals(ShowSelectorName))
					{
						if (ShowSelectorValue.equals("false"))
						{
							MySingleton.getInstance().setPeriodicShowSelector(true);
							Value = "true";
						}
						else if (ShowSelectorValue.equals("true"))
						{
							MySingleton.getInstance().setPeriodicShowSelector(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, false, true, ShowSelectorName, ShowSelectorDescription, Value));
						adapter.notifyDataSetChanged();
						ShowSelectorValue = Value;
						savePreferences();
					}
					else if (Item.equals(ShowSearchName))
					{
						if (ShowSearchValue.equals("false"))
						{
							MySingleton.getInstance().setPeriodicShowSearch(true);
							Value = "true";
						}
						else if (ShowSearchValue.equals("true"))
						{
							MySingleton.getInstance().setPeriodicShowSearch(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, ShowSearchName, ShowSearchDescription, Value));
						adapter.notifyDataSetChanged();
						ShowSearchValue = Value;
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
		Periodic_ShowProgress = MySingleton.getInstance().getPeriodicShowProgress();
		Periodic_ClickableElements = MySingleton.getInstance().getPeriodicClickableElements();
		Periodic_Sort = MySingleton.getInstance().getPeriodicSort();
		Periodic_ShowSelector = MySingleton.getInstance().getPeriodicShowSelector();
		Periodic_ShowSearch = MySingleton.getInstance().getPeriodicShowSearch();
		
		settings = getSharedPreferences(SettingsTAG, 0);
		Editor editor = settings.edit();
		
		editor.putBoolean("PeriodicShowProgress", Periodic_ShowProgress);
		editor.putBoolean("PeriodicClickableElements", Periodic_ClickableElements);
		editor.putString("PeriodicSort", Periodic_Sort);
		editor.putBoolean("PeriodicShowSelector", Periodic_ShowSelector);
		editor.putBoolean("PeriodicShowSearch", Periodic_ShowSearch);
		
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
				if (Item.equals(SortName))
				{
					Value = "Symbol";
					
					Periodic_Sort = "Symbol";
					MySingleton.getInstance().setPeriodicSort(Periodic_Sort);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, SortName, SortDescription, Value));
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
				if (Item.equals(SortName))
				{
					Value = "Name";
					
					Periodic_Sort = "Name";
					MySingleton.getInstance().setPeriodicSort(Periodic_Sort);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, SortName, SortDescription, Value));
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
				if (Item.equals(SortName))
				{
					Value = "Atomic Number";
					
					Periodic_Sort = "Atomic Number";
					MySingleton.getInstance().setPeriodicSort(Periodic_Sort);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, SortName, SortDescription, Value));
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
				if (Item.equals(SortName))
				{
					Value = "Atomic Mass";
					
					Periodic_Sort = "Atomic Mass";
					MySingleton.getInstance().setPeriodicSort(Periodic_Sort);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, false, false, SortName, SortDescription, Value));
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
				// No item here...
			}
		});
		selectionSix.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// No item here...
			}
		});
		selectionSeven.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// No item here...
			}
		});
	}
	
	public void setVisibles()
	{
		if (Item.equals("Sort By"))
		{
			selectionOne.setText("Symbol");
			selectionTwo.setText("Name");
			selectionThree.setText("Atomic Number");
			selectionFour.setText("Atomic Mass");
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
	}
	
	public void setSelections()
	{
		Periodic_ShowProgress = MySingleton.getInstance().getPeriodicShowProgress();
		Periodic_ClickableElements = MySingleton.getInstance().getPeriodicClickableElements();
		Periodic_Sort = MySingleton.getInstance().getPeriodicSort();
		Periodic_ShowSelector = MySingleton.getInstance().getPeriodicShowSelector();
		Periodic_ShowSearch = MySingleton.getInstance().getPeriodicShowSearch();
		
		if (Periodic_Sort.equals("Symbol"))
			SortValue = "Symbol";
		else if (Periodic_Sort.equals("Name"))
			SortValue = "Name";
		else if (Periodic_Sort.equals("Atomic Number"))
			SortValue = "Atomic Number";
		else if (Periodic_Sort.equals("Atomic Mass"))
			SortValue = "Atomic Mass";
		
		if(Periodic_ShowProgress)
			ShowProgressValue = "true";
		else
			ShowProgressValue = "false";
		
		if(Periodic_ClickableElements)
			ClickElementValue = "true";
		else
			ClickElementValue = "false";
		
		if(Periodic_ShowSelector)
			ShowSelectorValue = "true";
		else
			ShowSelectorValue = "false";
		
		if(Periodic_ShowSearch)
			ShowSearchValue = "true";
		else
			ShowSearchValue = "false";
	}
	
	public void initializeGlobals()
	{
		Periodic_ShowProgress = MySingleton.getInstance().getPeriodicShowProgress();
		Periodic_ClickableElements = MySingleton.getInstance().getPeriodicClickableElements();
		Periodic_Sort = MySingleton.getInstance().getPeriodicSort();
		Periodic_ShowSelector = MySingleton.getInstance().getPeriodicShowSelector();
		Periodic_ShowSearch = MySingleton.getInstance().getPeriodicShowSearch();
	}
	
	public class SettingsPeriodicTableAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<SettingsItem> listItem;

		public SettingsPeriodicTableAdapter(Context context, List<SettingsItem> listItem)
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
				convertView = inflater.inflate(R.layout.settings_convertrow, null);
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
				if(entry.getItemName().equals("Show Loading"))
					Header.setText("Periodic Table");
				else if(entry.getItemName().equals("Sort By"))
					Header.setText("Element List");
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