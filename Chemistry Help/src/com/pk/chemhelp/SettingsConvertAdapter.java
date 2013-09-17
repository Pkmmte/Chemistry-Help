package com.pk.chemhelp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
	
	@SuppressWarnings("unused")
	private void showDialog(SettingsItem entry)
	{
		// Create and show your dialog
		// Depending on the Dialogs button clicks delete it or do nothing
	}
	
}
