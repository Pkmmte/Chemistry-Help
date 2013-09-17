package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Settings extends SherlockActivity
{
	final String PageID = "Settings";
	String PreviousPageID = "";
	Intent intent;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.rotation, R.anim.rotation);
		setContentView(R.layout.settings);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		findPreviousPage();
		
		ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		
		// Menu Items
		final List<SettingsItem> listOfItems = new ArrayList<SettingsItem>();
		listOfItems.add(new SettingsItem("Display (Comming Soon)", "Select themes, modify looks, customize buttons and layout, etc.", "display"));
		listOfItems.add(new SettingsItem("Gas Law", "Set your preference of unit types, max decimal precision, and a few others.", "gas"));
		listOfItems.add(new SettingsItem("Convert", "Change the way you want your conversions to appear.", "convert"));
		listOfItems.add(new SettingsItem("Periodic Table", "Element ordering, experience, and other periodic defaults.", "periodic"));
		listOfItems.add(new SettingsItem("Study (Comming Soon)", "Select the way you prefer to study for better experience.", "study"));
		listOfItems.add(new SettingsItem("Quiz", "Customize your quiz experience to ensure best results.", "practice"));
		listOfItems.add(new SettingsItem("Advanced (Comming Soon)", "Advanced topics. Not available at this moment.", "advanced"));
		
		SettingsAdapter adapter = new SettingsAdapter(this, listOfItems);
		
		// Cyan Divider
		int[] colors = { 0, 0xFF00FFFF, 0 };
		list.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		list.setDividerHeight(1);
		;
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				System.out.println("sadsfsf");
				String ID = listOfItems.get(position).getIcon();
				Intent subSettings;
				subSettings = new Intent(Settings.this, ChemistryHelp.class);
				if (ID.equals("display"))
					showToast("Comming Soon");
				else if (ID.equals("gas"))
					subSettings = new Intent(Settings.this, SettingsGas.class);
				else if (ID.equals("convert"))
					subSettings = new Intent(Settings.this, SettingsConvert.class);
				else if (ID.equals("periodic"))
					subSettings = new Intent(Settings.this, SettingsPeriodicTable.class);
				else if (ID.equals("study"))
					showToast("Comming Soon");
				else if (ID.equals("practice"))
					subSettings = new Intent(Settings.this, SettingsQuiz.class);
				else if (ID.equals("advanced"))
					showToast("Comming Soon");
				else
					subSettings = new Intent(Settings.this, ChemistryHelp.class);
				
				startActivity(subSettings);
				//showToast(listOfItems.get(position).getItemName());
			}
		});
		
		list.setAdapter(adapter);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home: // Previous page... NOT HOME
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void findPreviousPage()
	{
		PreviousPageID = MySingleton.getInstance().getPreviousPageID();
		if(PreviousPageID.equals("Chemistry Help"))
			intent = new Intent(this, ChemistryHelp.class);
		else if(PreviousPageID.equals("Gas Laws"))
			intent = new Intent(this, GasLaws.class);
		else if(PreviousPageID.equals("Convert"))
			intent = new Intent(this, Convert.class);
		else if(PreviousPageID.equals("Periodic Table"))
			intent = new Intent(this, PeriodicTable.class);
		else if(PreviousPageID.equals("Chemical Search"))
			intent = new Intent(this, ChemicalSearch.class);
		else if(PreviousPageID.equals("Molar Mass"))
			intent = new Intent(this, MolarMass.class);
		else if(PreviousPageID.equals("Boyle's Law"))
			intent = new Intent(this, BoylesLaw.class);
		else if(PreviousPageID.equals("Charles Law"))
			intent = new Intent(this, CharlesLaw.class);
		else if(PreviousPageID.equals("Ideal Gas Law"))
			intent = new Intent(this, IdealGasLaw.class);
		else if(PreviousPageID.equals("Gay Lussac's Law"))
			intent = new Intent(this, GayLussacsLaw.class);
		else if(PreviousPageID.equals("Combined Gas Law"))
			intent = new Intent(this, CombinedGasLaw.class);
		else
			intent = new Intent(this, ChemistryHelp.class);
	}
	
	private void showToast(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	public class SettingsAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<SettingsItem> listItem;

		public SettingsAdapter(Context context, List<SettingsItem> listItem)
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
				convertView = inflater.inflate(R.layout.settings_row, null);
			}

			ImageView iconImage = (ImageView) convertView
				.findViewById(R.id.imageView1);
			String icon = entry.getIcon();
			if (icon.equals("display"))
				iconImage.setImageResource(R.drawable.display_icon);
			else if (icon.equals("gas"))
				iconImage.setImageResource(R.drawable.gas_icon);
			else if (icon.equals("convert"))
				iconImage.setImageResource(R.drawable.convert_icon);
			else if (icon.equals("periodic"))
				iconImage.setImageResource(R.drawable.periodic_icon);
			else if (icon.equals("study"))
				iconImage.setImageResource(R.drawable.study_icon);
			else if (icon.equals("practice"))
				iconImage.setImageResource(R.drawable.practice_icon);
			else if (icon.equals("advanced"))
				iconImage.setImageResource(R.drawable.extra_icon);

			TextView Item = (TextView) convertView.findViewById(R.id.Item);
			Item.setText(entry.getItemName());

			TextView Description = (TextView) convertView
				.findViewById(R.id.Description);
			Description.setText(entry.getDescription());

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
