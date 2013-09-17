package com.pk.chemhelp;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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

public class Quiz extends SherlockActivity
{
	protected DataStorage appState;
	final String PageID = "Quiz";
	boolean PRO;

	/* Get us out of here! */
	private boolean Exit;

	/* UI Elements */
	Button TakeQuiz;
	private RadioButton selectQuiz = null;
	private RadioButton selectSavedQuiz = null;
	private RadioButton selectMultipleChoice = null;
	private RadioButton selectFreeResponse = null;
	boolean selectedQuiz;
	boolean selectedSavedQuiz;
	boolean selectedMultipleChoice;
	boolean selectedFreeResponse;
	TextView textError;
	TextView textContent;
	TextView textSavedQuizes;
	ListView list;
	LinearLayout contentQuiz;
	LinearLayout contentSavedQuiz;
	LinearLayout typeQuiz;
	LinearLayout timeLimitQuiz;
	
	/* Check What Content The User Wants */
	CheckBox checkPeriodicTable;
	CheckBox checkPeriodicElements;
	boolean checkedPeriodicTable;
	boolean checkedPeriodicElements;
	int numChecked;
	
	/* Values To Pass To The Actual Quiz */
	String[] QuizContent;
	String QuizType;

	/* Bookmarks */
	String[] pageValues;
	
	/* Backport Overflow Menu Workaround */
	Menu mainMenu;
	SubMenu subMenu;
	
	/* Debugging */
	MenuItem warningIcon;
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	boolean FORCE_STOP;

	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.quiz);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		PRO = MySingleton.getInstance().isPRO();

		Bundle extraBundle;
		Intent intentValues = getIntent();
		pageValues = new String[50];

		TakeQuiz = (Button) findViewById(R.id.TakeQuiz);
		selectQuiz = (RadioButton) findViewById(R.id.Quiz);
		selectSavedQuiz = (RadioButton) findViewById(R.id.SavedQuiz);
		selectedQuiz = true;
		selectedSavedQuiz = false;
		selectMultipleChoice = (RadioButton) findViewById(R.id.MultipleChoice);
		selectFreeResponse = (RadioButton) findViewById(R.id.FreeResponse);
		selectedMultipleChoice = true;
		selectedFreeResponse = false;
		textContent = (TextView) findViewById(R.id.textContent);
		textError = (TextView) findViewById(R.id.textError);
		checkPeriodicTable = (CheckBox) findViewById(R.id.checkPeriodicTable);
		checkPeriodicElements = (CheckBox) findViewById(R.id.checkPeriodicElements);
		contentQuiz = (LinearLayout) findViewById(R.id.contentQuiz);
		contentSavedQuiz = (LinearLayout)findViewById(R.id.contentSavedQuiz);
		timeLimitQuiz = (LinearLayout) findViewById(R.id.timelimitQuiz);
		numChecked = 0;
		QuizType = "Multiple Choice";
		
		if(PRO)
		{
			timeLimitQuiz.setVisibility(View.VISIBLE);
		}
		else
		{
			timeLimitQuiz.setVisibility(View.GONE);
		}

		list = (ListView) findViewById(R.id.ListView);
		list.setClickable(true);

		// Menu Items
		final List<SavedQuiz> listOfItems = new ArrayList<SavedQuiz>();
		listOfItems.add(new SavedQuiz("name1", "description1", "icon1"));
		listOfItems.add(new SavedQuiz("name2", "description2", "icon2"));
		listOfItems.add(new SavedQuiz("name3", "description3", "icon3"));

		SavedQuizAdapter adapter = new SavedQuizAdapter(this, listOfItems);

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
					if (ID.equals("icon1"))
						selectedLaw = new Intent(Quiz.this, BoylesLaw.class);
					else if (ID.equals("icon2"))
						selectedLaw = new Intent(Quiz.this, CharlesLaw.class);
					else
						selectedLaw = new Intent(Quiz.this, ChemistryHelp.class);
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
		
		ProValues();

		TakeQuiz.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				if(selectedQuiz)
				{
					boolean contentSelected = selectContent();
					if(contentSelected)
					{
						Intent intent = new Intent(Quiz.this, TakeQuiz.class);
						intent.putExtra("Quiz Content", QuizContent);
						intent.putExtra("Quiz Content Number", numChecked);
						intent.putExtra("Quiz Type", QuizType);
						startActivity(intent);
					}
					else // Set Error
					{
						textError.setText("No quiz category selected");
						textError.setTextColor(0xFFFF0000);
						textError.setVisibility(View.VISIBLE);
					}
				}
			}
		});
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
				Dialogs.getDialog("Add Bookmark", Quiz.this).show();
				return true;
			case R.id.Bookmarks_Label:
				Dialogs.getDialog("Bookmarks", Quiz.this).show();
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
				 QuickBookmark.storeBookmarks(Bookmarks, getApplicationContext());
				 Toast.makeText(ChemistryHelp.this, "Writing..." , Toast.LENGTH_SHORT).show();
				 return true;*/
			case R.id.Exit_Label:
				Exit();
				return true;
			case R.id.Debug_Label:
				Intent i = new Intent(Quiz.this, Debug.class);
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

	public void onSelectQuizType(View v)
	{
		if (selectQuiz.isChecked())
		{
			selectedQuiz = true;
			selectedSavedQuiz = false;
			contentQuiz.setVisibility(View.VISIBLE);
			contentSavedQuiz.setVisibility(View.GONE);
		}
		else if (selectSavedQuiz.isChecked())
		{
			selectedQuiz = false;
			selectedSavedQuiz = true;
			contentQuiz.setVisibility(View.GONE);
			contentSavedQuiz.setVisibility(View.VISIBLE);
		}
	}
	
	public void onSelectResponseType(View v)
	{
		if (selectMultipleChoice.isChecked())
		{
			selectedMultipleChoice = true;
			selectedFreeResponse = false;
			QuizType = "Multiple Choice";
		}
		else if (selectFreeResponse.isChecked())
		{
			selectedMultipleChoice = false;
			selectedFreeResponse = true;
			QuizType = "Free Response";
		}
	}
	
	public void onCheckContentClicked(View view)
	{
		checkedPeriodicTable = checkPeriodicTable.isChecked();
		checkedPeriodicElements = checkPeriodicElements.isChecked();
	}
	
	public boolean selectContent()
	{
		boolean selected = false;
		int numSelected = 0;
		
		// Check If & How Much Selected First
		if(checkedPeriodicTable)
		{
			selected = true;
			numSelected++;
		}
		if(checkedPeriodicElements)
		{
			selected = true;
			numSelected++;
		}
		
		// Done Checking, Initialize & Assign What's Checked
		if(selected)
		{
			QuizContent = new String[numSelected];
			numChecked = 0;
			
			if(checkedPeriodicElements)
			{
				QuizContent[numChecked] = "Periodic Element";
				numChecked++;
			}
			if(checkedPeriodicTable)
			{
				QuizContent[numChecked] = "Periodic Table";
				numChecked++;
			}
		}
		
		return selected;
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
	
	public void ProValues()
	{
		if(PRO)
		{
			selectQuiz.setVisibility(View.VISIBLE);
			selectSavedQuiz.setVisibility(View.VISIBLE);
		}
		else
		{
			selectQuiz.setVisibility(View.GONE);
			selectSavedQuiz.setVisibility(View.GONE);
		}
	}

	public void showError()
	{
		Toast.makeText(Quiz.this, "A small error happened. Please report the following to the developer: \n" + MySingleton.getInstance().getErrors()[0], Toast.LENGTH_SHORT).show();
	}

	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
