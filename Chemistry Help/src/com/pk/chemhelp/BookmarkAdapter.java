package com.pk.chemhelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookmarkAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;
	
	private List<Bookmark> listItem;
	
	public BookmarkAdapter(Context context, List<Bookmark> listItem)
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
	
	public View getView(int position, View bookmarkView, ViewGroup viewGroup)
	{
		Bookmark entry = listItem.get(position);
		if (bookmarkView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			bookmarkView = inflater.inflate(R.layout.bookmark_row, null);
		}
		
		ImageView iconImage = (ImageView) bookmarkView.findViewById(R.id.Icon);
		String icon = entry.getIcon();  // FindFind valuevalue later
		if (icon.equals("icon1"))
			iconImage.setImageResource(R.drawable.addbookmark_icon);
		else if (icon.equals("icon2"))
			iconImage.setImageResource(R.drawable.bookmarks_icon);
		else if (icon.equals("icon3"))
			iconImage.setImageResource(R.drawable.lussac_icon);
		else if (icon.equals("icon4"))
			iconImage.setImageResource(R.drawable.periodic_icon);
		else if (icon.equals("icon5"))
			iconImage.setImageResource(R.drawable.boyles_icon);
		else if (icon.equals("icon6"))
			iconImage.setImageResource(R.drawable.charles_icon);
		else if (icon.equals("icon7"))
			iconImage.setImageResource(R.drawable.combined_icon);
		else if (icon.equals("icon8"))
			iconImage.setImageResource(R.drawable.ideal_icon);
		else if (icon.equals("icon9"))
			iconImage.setImageResource(R.drawable.display_icon);
		else if (icon.equals("icon10"))
			iconImage.setImageResource(R.drawable.gas_icon);
		else if (icon.equals("icon11"))
			iconImage.setImageResource(R.drawable.convert_icon);
		else if (icon.equals("icon12"))
			iconImage.setImageResource(R.drawable.study_icon);
		else if (icon.equals("icon13"))
			iconImage.setImageResource(R.drawable.study_icon);
		else if (icon.equals("icon14"))
			iconImage.setImageResource(R.drawable.delete_icon);
		else if (icon.equals("icon15"))
			iconImage.setImageResource(R.drawable.extra_icon);
		else if (icon.equals("icon16"))
			iconImage.setImageResource(R.drawable.warning);
		else
			iconImage.setImageResource(R.drawable.addbookmark_icon);
		
		ImageView deleteImage = (ImageView) bookmarkView.findViewById(R.id.Delete);
		if(entry.getDeleteSelected())
			deleteImage.setImageResource(R.drawable.delete_red_icon);
		else
			deleteImage.setImageResource(R.drawable.delete_icon);
		
		if(entry.getDeleteMode())
			deleteImage.setVisibility(View.VISIBLE);
		else
			deleteImage.setVisibility(View.GONE);
		
		TextView Item = (TextView) bookmarkView.findViewById(R.id.Name);
		Item.setText(entry.getName());
		
		TextView Description = (TextView) bookmarkView.findViewById(R.id.Description);
		Description.setText(entry.getDescription());
		
		return bookmarkView;
	}
	
	@Override
	public void onClick(View view)
	{
		Bookmark entry = (Bookmark) view.getTag();
		listItem.remove(entry);
		notifyDataSetChanged();
		
	}
	
	@SuppressWarnings("unused")
	private void showDialog(Bookmark entry)
	{
		// Create and show your dialog
		// Depending on the Dialogs button clicks delete it or do nothing
	}
	
}
