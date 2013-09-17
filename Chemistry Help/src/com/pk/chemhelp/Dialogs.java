package com.pk.chemhelp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

public class Dialogs
{
	/* TESTING BOOKMARKS ON CONVERT AND PERIODIC TABLE CLASSES */
	public static boolean ABDialogChangingIcon;
	public static boolean ABDialogExtra;
	public static String ABDialogNameValue;
	public static String ABDialogDescriptionValue;
	public static String ABDialogIconValue;
	
	public static boolean BDialogDeleteMode;
	public static boolean BDialogDeleteSelected;
	public static String BDialogDeleteSet;
	
	public static Dialog getDialog(String Type, Context context)
	{
		Dialog dialog;
		
		if(Type.equals("First Time"))
			dialog = getFirstTimeDialog(context);
		else if(Type.equals("Change Log"))
			dialog = getChangelogDialog(context);
		else if(Type.equals("Help"))
			dialog = getHelpDialog(context);
		else if (Type.equals("About"))
			dialog = getAboutDialog(context);
		else if (Type.equals("Bookmarks"))
			dialog = getBookmarksDialog(context);
		else if(Type.equals("Add Bookmark"))
			dialog = getAddBookmarkDialog(context);
		else if(Type.equals("Update Available"))
			dialog = getUpdateAvailableDialog(context);
		else if (Type.equals("Report"))
			dialog = getReportDialog(context);
		else
			dialog = getHelpDialog(context);
		
		return dialog;
	}
	
	public static Dialog getFirstTimeDialog(Context context)
	{
		final Dialog firstTimeDialog = new Dialog(context);
		firstTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		firstTimeDialog.setContentView(R.layout.dialog_startup);
		firstTimeDialog.setCancelable(false);
		firstTimeDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		
		Button FTDialogClose = (Button) firstTimeDialog.findViewById(R.id.Close);
		FTDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				firstTimeDialog.dismiss();
			}
		});
		
		return firstTimeDialog;
	}
	
	public static Dialog getChangelogDialog(Context context)
	{
		final Dialog CDialog = new Dialog(context);
		CDialog.setTitle("Change Log");
		CDialog.setContentView(R.layout.dialog_updated);
		CDialog.setCancelable(true);
		CDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		
		Button CDialogClose = (Button) CDialog.findViewById(R.id.Close);
		CDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				CDialog.dismiss();
			}
		});
		
		return CDialog;
	}
	
	public static Dialog getHelpDialog(Context context)
	{
		Dialog helpDialog = new Dialog(context);
		helpDialog.setContentView(R.layout.dialog_help);
		helpDialog.setTitle("Help");
		helpDialog.setCancelable(true);
		
		return helpDialog;
	}
	
	public static Dialog getAboutDialog(Context context)
	{
		final Dialog ADialog = new Dialog(context);
		ADialog.setTitle("About");
		ADialog.setContentView(R.layout.dialog_about);
		ADialog.setCancelable(true);
		ADialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;

		Button ADialogClose = (Button) ADialog.findViewById(R.id.Close);

		ADialogClose.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View arg1)
				{
					ADialog.dismiss();
				}
			});
		return ADialog;
	}
	
	public static Dialog getBookmarksDialog(Context context)
	{
		Bookmark[] Bookmarks = MySingleton.getInstance().getBookmarks();
		final int numBookmarks = MySingleton.getInstance().getNumBookmarks();
		final Context cntxt = context;
		
		final Dialog BDialog = new Dialog(context);
		BDialog.setTitle("Bookmarks");
		BDialog.setContentView(R.layout.dialog_bookmarks);
		BDialog.setCancelable(true);
		BDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		
		final TextView BDialogTextError = (TextView) BDialog.findViewById(R.id.textError);
		Button BDialogClose = (Button) BDialog.findViewById(R.id.Close);
		final Button BDialogDelete = (Button) BDialog.findViewById(R.id.Delete);
		setBDialogDeleteMode(false);
		setBDialogDeleteSelected(Bookmark.findSelectedBookmarks(Bookmarks));
		
		final ListView list = (ListView) BDialog.findViewById(R.id.ListView);
		list.setClickable(true);

		int[] colors = { 0, 0xFFFFFFFF, 0 };
		list.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		list.setDividerHeight(1);
	
		final List<Bookmark> listOfItems = new ArrayList<Bookmark>();
		if(numBookmarks > 0)
		{
			BDialogTextError.setVisibility(View.GONE);
			list.setVisibility(View.VISIBLE);
			
			for(int x = 0; x < numBookmarks; x++)
				listOfItems.add(new Bookmark(Bookmarks[x].getName(), Bookmarks[x].getDescription(), Bookmarks[x].getIcon(), false, false, x));
		}
		else
		{
			BDialogTextError.setVisibility(View.VISIBLE);
			BDialogTextError.setText("You have no bookmarks. \n\nYou can add new bookmarks from the overflow menu located in the action bar from every page.");
			list.setVisibility(View.GONE);
		}
		
		final BookmarkAdapter adapter = new BookmarkAdapter(context, listOfItems);

		list.setOnItemClickListener(new OnItemClickListener()
		{
				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
				{
					System.out.println("sadsfsf");
					int bNum = listOfItems.get(position).getBookmarkNum();
					Bookmark[] Bookmarks = MySingleton.getInstance().getBookmarks();
					
					if(getBDialogDeleteMode())
					{
						if (Bookmarks[bNum].getDeleteSelected())
						{
							Bookmarks[bNum].setDeleteSelect(false);
							
							listOfItems.remove(listOfItems.get(position));
							listOfItems.add(position, new Bookmark(Bookmarks[bNum].getName(), Bookmarks[bNum].getDescription(), Bookmarks[bNum].getIcon(), false, true, bNum));
							adapter.notifyDataSetChanged();
							
							setBDialogDeleteSet(Bookmark.checkSelectedBookmarks(Bookmarks));
							BDialogDelete.setText(getBDialogDeleteSet());
						}
						else
						{
							Bookmarks[bNum].setDeleteSelect(true);
							
							listOfItems.remove(listOfItems.get(position));
							listOfItems.add(position, new Bookmark(Bookmarks[bNum].getName(), Bookmarks[bNum].getDescription(), Bookmarks[bNum].getIcon(), true, true, bNum));
							adapter.notifyDataSetChanged();
							
							setBDialogDeleteSet(Bookmark.checkSelectedBookmarks(Bookmarks));
							BDialogDelete.setText(getBDialogDeleteSet());
						}
					}
					else
					{
						// Delete This After Beta!
						if(Bookmarks[bNum].getName().equalsIgnoreCase("Debug") && Bookmarks[bNum].getDescription().equalsIgnoreCase("True"))
						{
							MySingleton.getInstance().setDebugMode(true);
							SharedPreferences settings = cntxt.getSharedPreferences("ChemHelpSettings", 0);
							Editor editor = settings.edit();
							editor.putBoolean("DebugMode", true);
							editor.commit();
						}
						else if(Bookmarks[bNum].getName().equalsIgnoreCase("Debug") && Bookmarks[bNum].getDescription().equalsIgnoreCase("False"))
						{
							MySingleton.getInstance().setDebugMode(false);
							SharedPreferences settings = cntxt.getSharedPreferences("ChemHelpSettings", 0);
							Editor editor = settings.edit();
							editor.putBoolean("DebugMode", false);
							editor.commit();
						}
						
						cntxt.startActivity(Bookmark.getIntentValue(Bookmarks[bNum].getPageID(), cntxt, Bookmarks[bNum].getPageValues()));
					}
				}
		});
		
		BDialogDelete.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Bookmark[] Bookmarks = MySingleton.getInstance().getBookmarks();
				int numBookmarks = MySingleton.getInstance().getNumBookmarks();
				
				// Set to delete mode...
				if (getBDialogDeleteMode())
				{
					if (Bookmark.findSelectedBookmarks(Bookmarks))
					{
						Bookmarks = Bookmark.deleteSelectedBookmarks(Bookmarks);
						Bookmark.storeBookmarks(Bookmarks, cntxt);
					}
					
					numBookmarks = MySingleton.getInstance().getNumBookmarks();
					if(numBookmarks > 0)
					{
						BDialogTextError.setVisibility(View.GONE);
						list.setVisibility(View.VISIBLE);

						listOfItems.clear();
						for(int x = 0; x < numBookmarks; x++)
							listOfItems.add(new Bookmark(Bookmarks[x].getName(), Bookmarks[x].getDescription(), Bookmarks[x].getIcon(), false, false, x));

						adapter.notifyDataSetChanged();
					}
					else
					{
						BDialogTextError.setVisibility(View.VISIBLE);
						BDialogTextError.setText("You have no bookmarks. \n(Can't delete what you don't have...) \n\nYou can add new bookmarks from the overflow menu located in the action bar from every page.");
						list.setVisibility(View.GONE);
					}
					
					BDialogDelete.setText("Delete Mode");
					setBDialogDeleteMode(false);
				}
				else
				{
					Bookmarks = Bookmark.deselectAll(Bookmarks);
					if(numBookmarks > 0)
					{
						BDialogTextError.setVisibility(View.GONE);
						list.setVisibility(View.VISIBLE);
						
						listOfItems.clear();
						for(int x = 0; x < numBookmarks; x++)
							listOfItems.add(new Bookmark(Bookmarks[x].getName(), Bookmarks[x].getDescription(), Bookmarks[x].getIcon(), false, true, x));
						
						adapter.notifyDataSetChanged();
					}
					else
					{
						BDialogTextError.setVisibility(View.VISIBLE);
						BDialogTextError.setText("You have no bookmarks. \n(Can't delete what you don't have...) \n\nYou can add new bookmarks from the overflow menu located in the action bar from every page.");
						list.setVisibility(View.GONE);
					}
					
					BDialogDelete.setText("Exit Delete Mode");
					setBDialogDeleteMode(true);
				}
			}
		});
		BDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Bookmark[] Bookmarks = MySingleton.getInstance().getBookmarks();
				Bookmarks = Bookmark.deselectAll(Bookmarks);
				MySingleton.getInstance().setBookmarks(Bookmarks);
				BDialog.dismiss();
			}
		});

		list.setAdapter(adapter);
		
		return BDialog;
	}
	
	public static Dialog getAddBookmarkDialog(Context context)
	{
		final Context cntxt = context;
		
		final Dialog ABDialog = new Dialog(context);
		ABDialog.setTitle("Add Bookmark");
		ABDialog.setContentView(R.layout.dialog_addbookmark);
		ABDialog.setCancelable(false);
		ABDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		
		Button ABDialogClose = (Button) ABDialog.findViewById(R.id.BtnColorPickerCancel);
		Button ABDialogAdd = (Button) ABDialog.findViewById(R.id.BtnColorPickerOk);
		final Button ABDialogChangeIcon = (Button) ABDialog.findViewById(R.id.changeIcon);
		CheckBox ABDialogCheckbox = (CheckBox) ABDialog.findViewById(R.id.checkBox);
		final TextView ABDialogTextDescription = (TextView) ABDialog.findViewById(R.id.textDescription);
		final EditText ABDialogEditTextName = (EditText) ABDialog.findViewById(R.id.Name);
		final EditText ABDialogEditTextDescription = (EditText) ABDialog.findViewById(R.id.Description);
		final ScrollView ABDialogIconMenu = (ScrollView) ABDialog.findViewById(R.id.Icon);
		final LinearLayout ABDialogIconSet = (LinearLayout) ABDialog.findViewById(R.id.Iconset);
		final ImageView ABDialogIcon = (ImageView) ABDialog.findViewById(R.id.imageIcon);
		
		// Load image icons...
		ImageButton[] iconButtonArray = new ImageButton[16];

		// Initialize and set icon button values + listeners
		int xB = R.id.imageButton1;
		for (int b = 0; b < 16; b++)
		{
			iconButtonArray[b] = (ImageButton) ABDialog.findViewById(xB);
			final int numbah = b;
			iconButtonArray[b].setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View arg1)
				{
					setABDialogIconValue("icon" + (numbah + 1));
					ABDialogIcon.setImageResource(Bookmark.getIconValue(getABDialogIconValue()));
				}
			});
			xB++;
		}
		
		ABDialogCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if (isChecked)
				{
					ABDialogTextDescription.setVisibility(View.VISIBLE);
					ABDialogEditTextDescription.setVisibility(View.VISIBLE);
					ABDialogIconMenu.setVisibility(View.VISIBLE);
					setABDialogExtra(true);
					setABDialogIconValue("icon1");
				}
				else
				{
					ABDialogTextDescription.setVisibility(View.GONE);
					ABDialogEditTextDescription.setVisibility(View.GONE);
					ABDialogIconMenu.setVisibility(View.GONE);
					ABDialogIconSet.setVisibility(View.GONE);
					setABDialogExtra(false);

					setABDialogChangingIcon(false);
					ABDialogChangeIcon.setText("Change Icon");
				}
			}
		});
		
		ABDialogChangeIcon.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				if (getABDialogChangingIcon())
				{
					setABDialogChangingIcon(false);
					ABDialogChangeIcon.setText("Change Icon");
					ABDialogIconSet.setVisibility(View.GONE);
				}
				else
				{
					setABDialogChangingIcon(true);
					ABDialogChangeIcon.setText("Keep Current Icon");
					ABDialogIconSet.setVisibility(View.VISIBLE);
				}
			}
		});
		
		ABDialogAdd.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Bookmark[] Bookmarks = MySingleton.getInstance().getBookmarks();
				
				setABDialogChangingIcon(false);
				ABDialogChangeIcon.setText("Change Icon");
				ABDialogIconSet.setVisibility(View.GONE);
				
				setABDialogNameValue(ABDialogEditTextName.getText().toString());
				setABDialogDescriptionValue(ABDialogEditTextDescription.getText().toString());
				
				
				
				Bookmarks = Bookmark.addNewBookmark(Bookmarks, getABDialogNameValue(), getABDialogDescriptionValue(), getABDialogIconValue(), MySingleton.getInstance().getPreviousPageID(), getABDialogExtra(), MySingleton.getInstance().getPageValues());
				Bookmark.storeBookmarks(Bookmarks, cntxt);
				
				int numBookmarks = MySingleton.getInstance().getNumBookmarks();
				if(numBookmarks > 149)
					Toast.makeText(cntxt, "You have reached the 150 bookmark limit. \nPlease delete some of your current bookmarks before adding more.", Toast.LENGTH_LONG).show();
				
				ABDialog.dismiss();
			}
		});
		
		ABDialogClose.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				ABDialog.dismiss();
			}
		});
		
		return ABDialog;
	}
	
	public static Dialog getUpdateAvailableDialog(Context context)
	{
		final Context cntxt = context;
		final Dialog updateAvailableDialog = new Dialog(context);
		updateAvailableDialog.setTitle("Update Available");
		updateAvailableDialog.setContentView(R.layout.dialog_updateavailable);
		updateAvailableDialog.setCancelable(false);
		updateAvailableDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		
		Button UADialogYes = (Button) updateAvailableDialog.findViewById(R.id.Yes);
		Button UADialogLater = (Button) updateAvailableDialog.findViewById(R.id.Later);
		TextView UADialogDetails = (TextView) updateAvailableDialog.findViewById(R.id.updateValue);
		TextView UADialogRequired = (TextView) updateAvailableDialog.findViewById(R.id.Required);
		
		String SettingsTAG = "ChemHelpSettings";
		final SharedPreferences settings = context.getSharedPreferences(SettingsTAG, 0);
		final int remindLater = settings.getInt("RemindLater", 0);
		
		if(remindLater >= 3)
		{
			UADialogLater.setEnabled(false);
			UADialogRequired.setVisibility(View.VISIBLE);
			UADialogRequired.setTextColor(0xFFFF0000);
		}
		
		String details = MySingleton.getInstance().getUpdateDetails();
		UADialogDetails.setText(details);
		
		UADialogYes.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.pk.chemhelp"));
				cntxt.startActivity(intent);
				
				updateAvailableDialog.dismiss();
			}
		});
		
		UADialogLater.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				int rLater = remindLater + 1;
				Editor editor = settings.edit();
				editor.putInt("RemindLater", rLater);
				editor.commit();
				
				updateAvailableDialog.dismiss();
			}
		});
		
		return updateAvailableDialog;
	}
	
	public static Dialog getReportDialog(Context context)
	{
		final Dialog ReportDialog = new Dialog(context);
		ReportDialog.setTitle("Report Error");
		ReportDialog.setContentView(R.layout.dialog_report);
		ReportDialog.setCancelable(false);
		ReportDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		CheckBox RCheckBox = (CheckBox) ReportDialog.findViewById(R.id.checkBox);
		final EditText RDetails = (EditText) ReportDialog.findViewById(R.id.editText);
		Button RSend = (Button) ReportDialog.findViewById(R.id.Send);
		Button RNo = (Button) ReportDialog.findViewById(R.id.No);

		RSend.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				// Contact Server...
				
				ReportDialog.dismiss();
			}
		});

		RNo.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				ReportDialog.dismiss();
			}
		});

		RCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if (isChecked)
					RDetails.setVisibility(View.VISIBLE);
				else
					RDetails.setVisibility(View.GONE);
			}
		});

		return ReportDialog;
	}
	
	public static boolean isNetworkConnected(Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null)
		{
			return false;
		}
		else
			return true;
	}
	
	public static boolean getABDialogChangingIcon()
	{
		return ABDialogChangingIcon;
	}
	
	public static void setABDialogChangingIcon(boolean b)
	{
		ABDialogChangingIcon = b;
	}
	
	public static boolean getABDialogExtra()
	{
		return ABDialogExtra;
	}
	
	public static void setABDialogExtra(boolean b)
	{
		ABDialogExtra = b;
	}
	
	public static String getABDialogNameValue()
	{
		return ABDialogNameValue;
	}
	
	public static void setABDialogNameValue(String s)
	{
		ABDialogNameValue = s;
	}
	
	public static String getABDialogDescriptionValue()
	{
		return ABDialogDescriptionValue;
	}
	
	public static void setABDialogDescriptionValue(String s)
	{
		ABDialogDescriptionValue = s;
	}
	
	public static String getABDialogIconValue()
	{
		return ABDialogIconValue;
	}
	
	public static void setABDialogIconValue(String s)
	{
		ABDialogIconValue = s;
	}
	
	
	public static boolean getBDialogDeleteMode()
	{
		return BDialogDeleteMode;
	}
	
	public static void setBDialogDeleteMode(boolean b)
	{
		BDialogDeleteMode = b;
	}
	
	public static boolean getBDialogDeleteSelected()
	{
		return BDialogDeleteSelected;
	}
	
	public static void setBDialogDeleteSelected(boolean b)
	{
		BDialogDeleteSelected = b;
	}
	
	public static String getBDialogDeleteSet()
	{
		return BDialogDeleteSet;
	}
	
	public static void setBDialogDeleteSet(String s)
	{
		BDialogDeleteSet = s;
	}
	
	/*public ArrayList<String> getServerData() throws JSONException, ClientProtocolException, IOException
	{
		String URL = "http://www.pkmmte.com/log.php";
		ArrayList<String> stringData = new ArrayList<String>();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		ResponseHandler <String> responseHandler = new BasicResponseHandler();
		HttpPost postMethod = new HttpPost(URL);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("someKey", "Stuff");
		
		nameValuePairs.add(new BasicNameValuePair("jsonString", jsonObject.toString()));
		postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		String response = httpClient.execute(postMethod, responseHandler);
		
		JSONObject jsonReponse = new JSONObject(response);

		JSONArray serverData1 = jsonResponse.getJSONArray("data1");
		JSONArray serverData2 = jsonResponse.getJSONArray("data2");
		
		return;
	}*/
	
}













