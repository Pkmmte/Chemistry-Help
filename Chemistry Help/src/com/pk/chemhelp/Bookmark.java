package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Bookmark
{
	// Bookmark Properties
	String Name;
	String Description;
	String Icon;
	String PageID;
	boolean DeleteSelected;
	
	// Bookmarks Values
	String[] PageValues;
	
	// ListView Purposes
	boolean DeleteMode;
	int BookmarkNum;
	
	// Empty Constructor
	public Bookmark()
	{
		
	}
	
	// ListView Purposes
	public Bookmark(String N, String D, String I, boolean S, boolean M, int X)
	{
		Name = N;
		Description = D;
		Icon = I;
		DeleteSelected = S;
		DeleteMode = M;
		BookmarkNum = X;
	}
	
	// Actual Bookmark
	public Bookmark(String N, String D, String I, String PID)
	{
		Name = N;
		Description = D;
		Icon = I;
		PageID = PID;
		DeleteSelected = false;
		PageValues = new String[50];
		for (int v = 0; v < 50; v++)
			PageValues[v] = "pcx_value";
	}
	
	public void setName(String N)
	{
		Name = N;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public void setDescription(String D)
	{
		Description = D;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	public void setIcon(String I)
	{
		Icon = I;
	}
	
	public String getIcon()
	{
		return Icon;
	}
	
	public void setPageID(String PID)
	{
		PageID = PID;
	}
	
	public String getPageID()
	{
		return PageID;
	}
	
	public void setDeleteSelect(boolean DS)
	{
		DeleteSelected = DS;
	}
	
	public boolean getDeleteSelected()
	{
		return DeleteSelected;
	}
	
	public void setPageValues(String[] val)
	{
		PageValues = val;
	}
	
	public String[] getPageValues()
	{
		return PageValues;
	}
	
	public void setDeleteMode(boolean M)
	{
		DeleteMode = M;
	}
	
	public boolean getDeleteMode()
	{
		return DeleteMode;
	}
	
	public void setBookmarkNum(int X)
	{
		BookmarkNum = X;
	}
	
	public int getBookmarkNum()
	{
		return BookmarkNum;
	}
	
	
	//////////////////////
	private static String SettingsTAG = "ChemHelpSettings";
	private static SharedPreferences settings;
	
	public static int getIconValue(String ic)
	{
		int val = 0;
		if (ic.equals("icon1"))
			val = R.drawable.addbookmark_icon;
		else if (ic.equals("icon2"))
			val = R.drawable.bookmarks_icon;
		else if (ic.equals("icon3"))
			val = R.drawable.lussac_icon;
		else if (ic.equals("icon4"))
			val = R.drawable.periodic_icon;
		else if (ic.equals("icon5"))
			val = R.drawable.boyles_icon;
		else if (ic.equals("icon6"))
			val = R.drawable.charles_icon;
		else if (ic.equals("icon7"))
			val = R.drawable.combined_icon;
		else if (ic.equals("icon8"))
			val = R.drawable.ideal_icon;
		else if (ic.equals("icon9"))
			val = R.drawable.display_icon;
		else if (ic.equals("icon10"))
			val = R.drawable.gas_icon;
		else if (ic.equals("icon11"))
			val = R.drawable.convert_icon;
		else if (ic.equals("icon12"))
			val = R.drawable.study_icon;
		else if (ic.equals("icon13"))
			val = R.drawable.study_icon;
		else if (ic.equals("icon14"))
			val = R.drawable.delete_icon;
		else if (ic.equals("icon15"))
			val = R.drawable.extra_icon;
		else if (ic.equals("icon16"))
			val = R.drawable.warning;
		else
			val = 0x7f020056;
		return val;
	}
	
	public static Intent getIntentValue(String STR, Context context, String[] vals)
	{
		Intent intent;
		if (STR.equals("Gas Laws"))
			intent = new Intent(context, GasLaws.class);
		else if (STR.equals("Boyle's Law"))
			intent = new Intent(context, BoylesLaw.class);
		else if (STR.equals("Charles Law"))
			intent = new Intent(context, CharlesLaw.class);
		else if (STR.equals("Gay Lussac's Law"))
			intent = new Intent(context, GayLussacsLaw.class);
		else if (STR.equals("Ideal Gas Law"))
			intent = new Intent(context, IdealGasLaw.class);
		else if (STR.equals("Combined Gas Law"))
			intent = new Intent(context, CombinedGasLaw.class);
		else if (STR.equals("Molar Mass"))
			intent = new Intent(context, MolarMass.class);
		else if (STR.equals("Periodic Table"))
			intent = new Intent(context, PeriodicTable.class);
		else if (STR.equals("Convert"))
			intent = new Intent(context, Convert.class);
		else if (STR.equals("Chemical Search"))
			intent = new Intent(context, ChemicalSearch.class);
		else if (STR.equals("Quiz"))
			intent = new Intent(context, Quiz.class);
		else
			intent = new Intent(context, ChemistryHelp.class);
		
		intent.putExtra("Page Values", vals);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}
	
	public static boolean findSelectedBookmarks(Bookmark[] Bookmarks)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		for (int bo = 0; bo < numBookmarks; bo++)
		{
			if (Bookmarks[bo].getDeleteSelected())
				return true;
		}
		return false;
	}
	
	public static Bookmark[] deselectAll(Bookmark[] bMarks)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		for (int bm = 0; bm < numBookmarks; bm++)
			bMarks[bm].setDeleteSelect(false);
		MySingleton.getInstance().setBookmarks(bMarks);
		return bMarks;
	}
	
	public static Bookmark[] deleteSelectedBookmarks(Bookmark[] bMarks)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		int numDeleted = 0;
		// Loop through all bookmarks
		for (int bo = 0; bo < numBookmarks; bo++)
		{
			// Set default values if selected and lower the number of bookmarks
			if (bMarks[bo].getDeleteSelected())
			{
				bMarks[bo].setName("pcx_name");
				bMarks[bo].setDescription("pcx_description");
				bMarks[bo].setIcon("pcx_icon");
				bMarks[bo].setPageID("pcx_pageid");
				bMarks[bo].setDeleteSelect(false);
				numDeleted++;
			}
		}
		numBookmarks -= numDeleted;
		MySingleton.getInstance().setBookmarks(bMarks);
		MySingleton.getInstance().setNumBookmarks(numBookmarks);
		bMarks = Bookmark.restructureBookmarks(bMarks);
		
		return bMarks;
	}
	
	public static Bookmark[] restructureBookmarks(Bookmark[] bMarks)
	{
		int moveOn = 0;
		for (int w = 0; w < 150; w++)
		{
			moveOn = w + 1;
			while (moveOn < 150)
			{
				if (bMarks[w].getName().equals("pcx_name"))
				{
					if (!(bMarks[moveOn].getName().equals("pcx_name")))
					{
						bMarks[w].setName(bMarks[moveOn].getName());
						bMarks[w].setDescription(bMarks[moveOn].getDescription());
						bMarks[w].setIcon(bMarks[moveOn].getIcon());
						bMarks[w].setPageID(bMarks[moveOn].getPageID());
						bMarks[w].setDeleteSelect(bMarks[moveOn].getDeleteSelected());
						bMarks[w].setPageValues(bMarks[moveOn].getPageValues());
						
						bMarks[moveOn].setName("pcx_name");
						bMarks[moveOn].setDescription("pcx_description");
						bMarks[moveOn].setIcon("pcx_icon");
						bMarks[moveOn].setPageID("pcx_pageid");
						bMarks[moveOn].setDeleteSelect(false);
						moveOn = 170;
					}
					else
						moveOn++;
				}
				else
					moveOn = 170;
			}
		}
		
		MySingleton.getInstance().setBookmarks(bMarks);
		return bMarks;
	}
	
	public static Bookmark[] addNewBookmark(Bookmark[] bMarks, String nameValue, String descriptionValue, String iconValue, String PageID, boolean extraValues, String[] pageValues)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		
		if (nameValue.length() > 0 && numBookmarks < 150)
		{
			if (extraValues)
			{
				bMarks[numBookmarks].setName(nameValue);
				bMarks[numBookmarks].setDescription(descriptionValue);
				bMarks[numBookmarks].setIcon(iconValue);
				bMarks[numBookmarks].setPageID(PageID);
				bMarks[numBookmarks].setPageValues(pageValues);
			}
			else
			{
				bMarks[numBookmarks].setName(nameValue);
				bMarks[numBookmarks].setDescription("");
				bMarks[numBookmarks].setIcon("icon1");
				bMarks[numBookmarks].setPageID(PageID);
				bMarks[numBookmarks].setPageValues(pageValues);
			}
			
			numBookmarks++;
			
			MySingleton.getInstance().setBookmarks(bMarks);
			MySingleton.getInstance().setNumBookmarks(numBookmarks);
			return bMarks;
		}
		return bMarks;
	}
	
	public static String checkSelectedBookmarks(Bookmark[] bMarks)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		boolean selected = false;
		// Loop through all bookmarks
		for (int bo = 0; bo < numBookmarks; bo++)
		{
			if (bMarks[bo].getDeleteSelected())
			{
				selected = true;
				break;
			}
		}
		
		if (selected)
			return "Delete Selected";
		else
			return "Exit Delete Mode";
	}
	
	public static boolean storeBookmarks(Bookmark[] bMarks, Context context)
	{
		int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		
		settings = context.getSharedPreferences(SettingsTAG, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("Bookmarks Number", numBookmarks);
		for (int i = 0; i < numBookmarks; i++)
		{
			editor.putString("Bookmark_" + i + "_Name", bMarks[i].getName());
			editor.putString("Bookmark_" + i + "_Description", bMarks[i].getDescription());
			editor.putString("Bookmark_" + i + "_Icon", bMarks[i].getIcon());
			editor.putString("Bookmark_" + i + "_PageID", bMarks[i].getPageID());
			
			String[] vals = new String[50];
			vals = bMarks[i].getPageValues();
			for (int fu = 0; fu < 50; fu++)
				editor.putString("Bookmark_" + i + "_PageValues_" + fu, vals[fu]);
		}
		return editor.commit();
	}
	
	public static Bookmark[] loadBookmarks(Context context)
	{
		//int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		Bookmark[] bMarks = new Bookmark[150];
		for (int x = 0; x < 150; x++)
			bMarks[x] = new Bookmark("pcx_name", "pcx_description", "pcx_icon", "pcx_pageid");
		
		settings = context.getSharedPreferences(SettingsTAG, 0);
		int numBookmarks = settings.getInt("Bookmarks Number", 0);
		for (int i = 0; i < numBookmarks; i++)
		{
			bMarks[i].setName(settings.getString("Bookmark_" + i + "_Name", "pcx_name"));
			bMarks[i].setDescription(settings.getString("Bookmark_" + i + "_Description", "pcx_description"));
			bMarks[i].setIcon(settings.getString("Bookmark_" + i + "_Icon", "pcx_icon"));
			bMarks[i].setPageID(settings.getString("Bookmark_" + i + "_PageID", "pcx_pageid"));
			
			String[] vals = new String[50];
			for (int fu = 0; fu < 50; fu++)
				vals[fu] = settings.getString("Bookmark_" + i + "_PageValues_" + fu, "pcx_value");
			bMarks[i].setPageValues(vals);
		}
		
		MySingleton.getInstance().setNumBookmarks(numBookmarks);
		MySingleton.getInstance().setBookmarks(bMarks);
		
		return bMarks;
	}
	
	public void restructurePersistentBookmarks(Context context, Bookmark[] bMarks)
	{
		settings = context.getSharedPreferences(SettingsTAG, 0);
		SharedPreferences.Editor editor = settings.edit();
		// blah
		int moveOn = 0;
		
		for (int w = 0; w < 150; w++)
		{
			moveOn = w + 1;
			while (moveOn < 150)
			{
				if (bMarks[w].getName().equals("pcx_name"))
				{
					if (!(bMarks[moveOn].getName().equals("pcx_name")))
					{
						bMarks[w].setName(bMarks[moveOn].getName());
						bMarks[w].setDescription(bMarks[moveOn].getDescription());
						bMarks[w].setIcon(bMarks[moveOn].getIcon());
						bMarks[w].setPageID(bMarks[moveOn].getPageID());
						bMarks[w].setDeleteSelect(bMarks[moveOn].getDeleteSelected());
						bMarks[w].setPageValues(bMarks[moveOn].getPageValues());
						
						bMarks[moveOn].setName("pcx_name");
						bMarks[moveOn].setDescription("pcx_description");
						bMarks[moveOn].setIcon("pcx_icon");
						bMarks[moveOn].setPageID("pcx_pageid");
						bMarks[moveOn].setDeleteSelect(false);
						moveOn = 170;
					}
					else
						moveOn++;
				}
				else
					moveOn = 170;
			}
		}
		editor.commit();
	}
}
