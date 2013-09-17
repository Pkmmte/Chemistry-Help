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

public class SavedQuizAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;
	
	private List<SavedQuiz> listItem;
	
	public SavedQuizAdapter(Context context, List<SavedQuiz> listItem)
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
		SavedQuiz entry = listItem.get(position);
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.gaslaws_row, null);
		}
		
		ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
		String icon = entry.getIcon();
		if (icon.equals("icon1"))
			iconImage.setImageResource(R.drawable.boyles_icon);
		else if (icon.equals("icon2"))
			iconImage.setImageResource(R.drawable.charles_icon);
		else if (icon.equals("icon3"))
			iconImage.setImageResource(R.drawable.ideal_icon);
		
		TextView Item = (TextView) convertView.findViewById(R.id.Item);
		Item.setText(entry.getItemName());
		
		TextView Description = (TextView) convertView.findViewById(R.id.Description);
		Description.setText(entry.getDescription());
		
		return convertView;
	}
	
	@Override
	public void onClick(View view)
	{
		SavedQuiz entry = (SavedQuiz) view.getTag();
		listItem.remove(entry);
		notifyDataSetChanged();
		
	}
	
	@SuppressWarnings("unused")
	private void showDialog(GasLawsItem entry)
	{
		// Create and show your dialog
		// Depending on the Dialogs button clicks delete it or do nothing
	}
	
}
