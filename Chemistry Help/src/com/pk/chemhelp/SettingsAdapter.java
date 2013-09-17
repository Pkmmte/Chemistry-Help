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
	
	@SuppressWarnings("unused")
	private void showDialog(SettingsItem entry)
	{
		// Create and show your dialog
		// Depending on the Dialogs button clicks delete it or do nothing
	}
	
}
