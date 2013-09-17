package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class SettingsQuiz extends SherlockActivity
{
	protected DataStorage appState;
	private String SettingsTAG = "ChemHelpSettings";
	private SharedPreferences settings;
	
	// Globals
	boolean Quiz_NotifyCorrect;
	boolean Quiz_ShowTime;
	boolean Quiz_ShowPercent;
	boolean Quiz_ShowProgress;
	
	// Function Selection
	String Item;
	String Select;
	int Selection = 0;
	int Position = 0;
	
	// Variables
	String Name;
	String Description;
	String Value;
	boolean Check;
	
	// Local Variables
	String NotifyCorrectName = "Notify Correct";
	String ShowTimeName = "Show Time";
	String ShowPercentName = "Show Percentage";
	String ShowProgressName = "Show Progress";
	
	String NotifyCorrectDescription = "Notify if correct upon answer...";
	String ShowTimeDescription = "Show time label during quiz...";
	String ShowPercentDescription = "Show percentage label during quiz...";
	String ShowProgressDescription = "Show progress label during quiz...";
	
	String NotifyCorrectValue = "false";
	String ShowTimeValue = "true";
	String ShowPercentValue = "true";
	String ShowProgressValue = "true";
	
	final List<SettingsItem> listOfItems = new ArrayList<SettingsItem>();
	SettingsQuizAdapter adapter = new SettingsQuizAdapter(this, listOfItems);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Quiz Settings");
		
		initializeGlobals();
		
		final ListView list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);
		list.setDividerHeight(0);
		
		setSelections();
		
		// Menu Items
		listOfItems.add(new SettingsItem(true, true, true, NotifyCorrectName, NotifyCorrectDescription, NotifyCorrectValue));
		listOfItems.add(new SettingsItem(true, false, true, ShowTimeName, ShowTimeDescription, ShowTimeValue));
		listOfItems.add(new SettingsItem(false, false, true, ShowPercentName, ShowPercentDescription, ShowPercentValue));
		listOfItems.add(new SettingsItem(false, true, true, ShowProgressName, ShowProgressDescription, ShowProgressValue));
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				Item = listOfItems.get(position).getItemName();
				Position = position;
				Check = listOfItems.get(position).isCheck();
				
				if (Check)
				{
					if (Item.equals(NotifyCorrectName))
					{
						if (NotifyCorrectValue.equals("false"))
						{
							MySingleton.getInstance().setQuizNotifyCorrect(true);
							Value = "true";
						}
						else if (NotifyCorrectValue.equals("true"))
						{
							MySingleton.getInstance().setQuizNotifyCorrect(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(true, true, true, NotifyCorrectName, NotifyCorrectDescription, Value));
						adapter.notifyDataSetChanged();
						NotifyCorrectValue = Value;
						savePreferences();
					}
					else if (Item.equals(ShowTimeName))
					{
						if (ShowTimeValue.equals("false"))
						{
							MySingleton.getInstance().setQuizShowTime(true);
							Value = "true";
						}
						else if (ShowTimeValue.equals("true"))
						{
							MySingleton.getInstance().setQuizShowTime(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(true, false, true, ShowTimeName, ShowTimeDescription, Value));
						adapter.notifyDataSetChanged();
						ShowTimeValue = Value;
						savePreferences();
					}
					else if (Item.equals(ShowPercentName))
					{
						if (ShowPercentValue.equals("false"))
						{
							MySingleton.getInstance().setQuizShowPercent(true);
							Value = "true";
						}
						else if (ShowTimeValue.equals("true"))
						{
							MySingleton.getInstance().setQuizShowPercent(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, false, true, ShowPercentName, ShowPercentDescription, Value));
						adapter.notifyDataSetChanged();
						ShowPercentValue = Value;
						savePreferences();
					}
					else if (Item.equals(ShowProgressName))
					{
						if (ShowProgressValue.equals("false"))
						{
							MySingleton.getInstance().setQuizShowProgress(true);
							Value = "true";
						}
						else if (ShowProgressValue.equals("true"))
						{
							MySingleton.getInstance().setQuizShowProgress(false);
							Value = "false";
						}
						listOfItems.remove(listOfItems.get(Position));
						listOfItems.add(Position, new SettingsItem(false, true, true, ShowProgressName, ShowProgressDescription, Value));
						adapter.notifyDataSetChanged();
						ShowProgressValue = Value;
						savePreferences();
					}
				}
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
		Quiz_NotifyCorrect = MySingleton.getInstance().getQuizNotifyCorrect();
		Quiz_ShowTime = MySingleton.getInstance().getQuizShowTime();
		Quiz_ShowPercent = MySingleton.getInstance().getQuizShowPercent();
		Quiz_ShowProgress = MySingleton.getInstance().getQuizShowProgress();
		
		settings = getSharedPreferences(SettingsTAG, 0);
		Editor editor = settings.edit();
		
		editor.putBoolean("QuizNotifyCorrect", Quiz_NotifyCorrect);
		editor.putBoolean("QuizShowTime", Quiz_ShowTime);
		editor.putBoolean("QuizShowPercent", Quiz_ShowPercent);
		editor.putBoolean("QuizShowProgress", Quiz_ShowProgress);
		
		editor.commit();
	}
	
	public void setSelections()
	{
		Quiz_NotifyCorrect = MySingleton.getInstance().getQuizNotifyCorrect();
		Quiz_ShowTime = MySingleton.getInstance().getQuizShowTime();
		Quiz_ShowPercent = MySingleton.getInstance().getQuizShowPercent();
		Quiz_ShowProgress = MySingleton.getInstance().getQuizShowProgress();

		if(Quiz_NotifyCorrect)
			NotifyCorrectValue = "true";
		else
			NotifyCorrectValue = "false";
		
		if(Quiz_ShowTime)
			ShowTimeValue = "true";
		else
			ShowTimeValue = "false";

		if(Quiz_ShowPercent)
			ShowPercentValue = "true";
		else
			ShowPercentValue = "false";
		
		if(Quiz_ShowProgress)
			ShowProgressValue = "true";
		else
			ShowProgressValue = "false";
	}
	
	public void initializeGlobals()
	{
		Quiz_NotifyCorrect = MySingleton.getInstance().getQuizNotifyCorrect();
		Quiz_ShowTime = MySingleton.getInstance().getQuizShowTime();
		Quiz_ShowPercent = MySingleton.getInstance().getQuizShowPercent();
		Quiz_ShowProgress = MySingleton.getInstance().getQuizShowProgress();
	}
	
	public class SettingsQuizAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<SettingsItem> listItem;

		public SettingsQuizAdapter(Context context, List<SettingsItem> listItem)
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
				convertView = inflater.inflate(R.layout.settings_quizrow, null);
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
				if(entry.getItemName().equals("Notify Correct"))
					Header.setText("Quiz Experience");
				else if(entry.getItemName().equals("Show Time"))
					Header.setText("User Interface");
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
