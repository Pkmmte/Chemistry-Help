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

public class SettingsConvert extends SherlockActivity
{
	protected DataStorage appState;
	private String SettingsTAG = "ChemHelpSettings";
	private SharedPreferences settings;
	
	// Globals
	String Convert_DefaultCategory;
	String Convert_MaxDecimalAccuracy;
	boolean Convert_AutoSolve;
	boolean Convert_FormatHelp;
	
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
	ImageView CheckBox;
	
	// Variables
	String Name;
	String Description;
	String Value;
	boolean Check;
	
	// Local Variables
	String CategoryName = "Category";
	String AutoSolveName = "Auto Solve";
	String FormatHelpName = "Format Help";
	String DecimalName = "Decimal Accuracy";
	
	String CategoryDescription = "Most used category...";
	String AutoSolveDescription = "No need to press \"Convert\" button...";
	String FormatHelpDescription = "Helps prevent possible confusion...";
	String DecimalDescription = "Maximum decimal point accuracy...";
	
	String CategoryValue = "X";
	String AutoSolveValue = "false";
	String FormatHelpValue = "false";
	String DecimalValue = "5";
	
	final List<SettingsItem> listOfItems = new ArrayList<SettingsItem>();
	SettingsConvertAdapter adapter = new SettingsConvertAdapter(this, listOfItems);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Convert Settings");
		
		initializeGlobals();
		
		selectionDialog = new Dialog(SettingsConvert.this);
		selectionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		selectionDialog.setContentView(R.layout.dialog_selectionmenu);
		selectionDialog.setTitle("Help");
		selectionDialog.setCancelable(true);
		
		addListenerOnButtons();
		
		Relative1 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative1);
		Relative2 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative2);
		Relative3 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative3);
		Relative4 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative4);
		Relative5 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative5);
		Relative6 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative6);
		Relative7 = (RelativeLayout) selectionDialog.findViewById(R.id.Relative7);
		
		CheckBox = (ImageView) findViewById(R.id.checkBox);
		
		final ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		list.setDividerHeight(0);
		
		setSelections();
		
		// Menu Items
		listOfItems.add(new SettingsItem(true, true, false, CategoryName, CategoryDescription, CategoryValue));
		listOfItems.add(new SettingsItem(true, false, true, AutoSolveName, AutoSolveDescription, AutoSolveValue));
		listOfItems.add(new SettingsItem(false, true, true, FormatHelpName, FormatHelpDescription, FormatHelpValue));
		listOfItems.add(new SettingsItem(true, true, false, DecimalName, DecimalDescription, DecimalValue));
		
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
					if (Item.equals(AutoSolveName))
					{
						if (AutoSolveValue.equals("false"))
						{
							MySingleton.getInstance().setConvertAutoSolve(true);
							Value = "true";
						}
						else if (AutoSolveValue.equals("true"))
						{
							MySingleton.getInstance().setConvertAutoSolve(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(true, false, true, AutoSolveName, AutoSolveDescription, Value));
						adapter.notifyDataSetChanged();
						AutoSolveValue = Value;
						savePreferences();
					}
					else if (Item.equals(FormatHelpName))
					{
						if (FormatHelpValue.equals("false"))
						{
							MySingleton.getInstance().setConvertFormatHelp(true);
							Value = "true";
						}
						else if (FormatHelpValue.equals("true"))
						{
							MySingleton.getInstance().setConvertFormatHelp(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, FormatHelpName, FormatHelpDescription, Value));
						adapter.notifyDataSetChanged();
						FormatHelpValue = Value;
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
		Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
		Convert_MaxDecimalAccuracy = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
		Convert_AutoSolve = MySingleton.getInstance().getConvertAutoSolve();
		Convert_FormatHelp = MySingleton.getInstance().getConvertFormatHelp();
		
		settings = getSharedPreferences(SettingsTAG, 0);
		Editor editor = settings.edit();
		
		editor.putString("ConvertDefaultCategory", Convert_DefaultCategory);
		editor.putBoolean("ConvertAutoSolve", Convert_AutoSolve);
		editor.putBoolean("ConvertFormatHelp", Convert_FormatHelp);
		editor.putString("selectedConvertMaxDecimalAccuracy", Convert_MaxDecimalAccuracy);
		
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
				if (Item.equals(CategoryName)) // Blank
				{
					Value = "Blank";
					
					Convert_DefaultCategory = "Blank";
					MySingleton.getInstance().setConvertDefaultCategory(Convert_DefaultCategory);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, CategoryName, CategoryDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (1) #.#
				{
					Value = "1";
					
					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
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
				if (Item.equals(CategoryName)) // Volume
				{
					Value = "Volume";
					
					Convert_DefaultCategory = "Volume";
					MySingleton.getInstance().setConvertDefaultCategory(Convert_DefaultCategory);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, CategoryName, CategoryDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (2) #.##
				{
					Value = "2";
					
					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
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
				if (Item.equals(CategoryName)) // Pressure
				{
					Value = "Pressure";
					
					Convert_DefaultCategory = "Pressure";
					MySingleton.getInstance().setConvertDefaultCategory(Convert_DefaultCategory);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, CategoryName, CategoryDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (3) #.###
				{
					Value = "3";
					
					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
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
				if (Item.equals(CategoryName)) // Temperature
				{
					Value = "Temperature";
					
					Convert_DefaultCategory = "Temperature";
					MySingleton.getInstance().setConvertDefaultCategory(Convert_DefaultCategory);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, CategoryName, CategoryDescription, Value));
					adapter.notifyDataSetChanged();
				}
				else if (Item.equals(DecimalName)) // (4) #.####
				{
					Value = "4";

					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
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
				
				if (Item.equals(DecimalName)) // (5) #.#####
				{
					Value = "5";

					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionSix.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(DecimalName)) // (6) #.######
				{
					Value = "6";
					
					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
		selectionSeven.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (Item.equals(DecimalName)) // (7) #.#######
				{
					Value = "7";
					
					Convert_MaxDecimalAccuracy = Value;
					MySingleton.getInstance().setConvertMaxDecimalAccuracy(Convert_MaxDecimalAccuracy);
					
					savePreferences();
					
					listOfItems.remove(listOfItems.get(Position));
					listOfItems.add(Position, new SettingsItem(true, true, false, DecimalName, DecimalDescription, Value));
					adapter.notifyDataSetChanged();
				}
				selectionDialog.dismiss();
			}
		});
	}
	
	public void setVisibles()
	{
		if (Item.equals("Category"))
		{
			selectionOne.setText("Blank");
			selectionTwo.setText("Volume");
			selectionThree.setText("Pressure");
			selectionFour.setText("Temperature");
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
		else if (Item.equals("Decimal Accuracy"))
		{
			selectionOne.setText("(1)   #.#");
			selectionTwo.setText("(2)   #.##");
			selectionThree.setText("(3)   #.###");
			selectionFour.setText("(4)   #.####");
			selectionFive.setText("(5)   #.#####");
			selectionSix.setText("(6)   #.######");
			selectionSeven.setText("(7)   #.#######");
			
			Relative1.setVisibility(View.VISIBLE);
			Relative2.setVisibility(View.VISIBLE);
			Relative3.setVisibility(View.VISIBLE);
			Relative4.setVisibility(View.VISIBLE);
			Relative5.setVisibility(View.VISIBLE);
			Relative6.setVisibility(View.VISIBLE);
			Relative7.setVisibility(View.VISIBLE);
		}
	}
	
	public void setSelections()
	{
		Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
		Convert_MaxDecimalAccuracy = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
		Convert_AutoSolve = MySingleton.getInstance().getConvertAutoSolve();
		Convert_FormatHelp = MySingleton.getInstance().getConvertFormatHelp();
		
		if (Convert_DefaultCategory.equals("Blank"))
			CategoryValue = "Blank";
		else if (Convert_DefaultCategory.equals("Volume"))
			CategoryValue = "Volume";
		else if (Convert_DefaultCategory.equals("Pressure"))
			CategoryValue = "Pressure";
		else if (Convert_DefaultCategory.equals("Temperature"))
			CategoryValue = "Temperature";
		
		if(Convert_MaxDecimalAccuracy.equals("1"))
			DecimalValue = "1";
		else if(Convert_MaxDecimalAccuracy.equals("2"))
			DecimalValue = "2";
		else if(Convert_MaxDecimalAccuracy.equals("3"))
			DecimalValue = "3";
		else if(Convert_MaxDecimalAccuracy.equals("4"))
			DecimalValue = "4";
		else if(Convert_MaxDecimalAccuracy.equals("5"))
			DecimalValue = "5";
		else if(Convert_MaxDecimalAccuracy.equals("6"))
			DecimalValue = "6";
		else if(Convert_MaxDecimalAccuracy.equals("7"))
			DecimalValue = "7";
		
		if(Convert_AutoSolve)
			AutoSolveValue = "true";
		else
			AutoSolveValue = "false";
		
		if(Convert_FormatHelp)
			FormatHelpValue = "true";
		else
			FormatHelpValue = "false";
	}
	
	public void initializeGlobals()
	{
		Convert_DefaultCategory = MySingleton.getInstance().getConvertDefaultCategory();
		Convert_MaxDecimalAccuracy = MySingleton.getInstance().getConvertMaxDecimalAccuracy();
		Convert_AutoSolve = MySingleton.getInstance().getConvertAutoSolve();
		Convert_FormatHelp = MySingleton.getInstance().getConvertFormatHelp();
	}
	
	public class SettingsConvertAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<SettingsItem> listItem;

		public SettingsConvertAdapter(Context context, List<SettingsItem> listItem)
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
				if(entry.getItemName().equals("Category"))
					Header.setText("Prefered Category");
				else if(entry.getItemName().equals("Auto Solve"))
					Header.setText("Smart Actions");
				else if(entry.getItemName().equals("Decimal Accuracy"))
					Header.setText("Accuracy");
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
