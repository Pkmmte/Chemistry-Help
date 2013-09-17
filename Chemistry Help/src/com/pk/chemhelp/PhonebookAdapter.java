package com.pk.chemhelp;
/*
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PhonebookAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;
	
	private List<Phonebook> listPhonebook;
	
	public PhonebookAdapter(Context context, List<Phonebook> listPhonebook)
	{
		this.context = context;
		this.listPhonebook = listPhonebook;
	}
	
	public int getCount()
	{
		return listPhonebook.size();
	}
	
	public Object getItem(int position)
	{
		return listPhonebook.get(position);
	}
	
	public long getItemId(int position)
	{
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup viewGroup)
	{
		Phonebook entry = listPhonebook.get(position);
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.settings_row, null);
		}
		
		ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
		String icon = entry.getIcon();
		if(icon.equals("display"))
			iconImage.setImageResource(R.drawable.display_icon);
		else if(icon.equals("gas"))
			iconImage.setImageResource(R.drawable.gas_icon);
		else if(icon.equals("convert"))
			iconImage.setImageResource(R.drawable.convert_icon);
		else if(icon.equals("periodic"))
			iconImage.setImageResource(R.drawable.periodic_icon);
		else if(icon.equals("extra"))
			iconImage.setImageResource(R.drawable.extra_icon);
		
		TextView tvContact = (TextView) convertView
				.findViewById(R.id.tvContact);
		tvContact.setText(entry.getName());
		
		TextView tvPhone = (TextView) convertView.findViewById(R.id.tvMobile);
		tvPhone.setText(entry.getPhone());
		
		TextView tvMail = (TextView) convertView.findViewById(R.id.tvMail);
		tvMail.setText(entry.getMail());
		
		// Set the onClick Listener on this button
		Button btnRemove = (Button) convertView.findViewById(R.id.btnRemove);
		btnRemove.setFocusableInTouchMode(false);
		btnRemove.setFocusable(false);
		btnRemove.setOnClickListener(this);
		// Set the entry, so that you can capture which item was clicked and
		// then remove it
		// As an alternative, you can use the id/position of the item to capture
		// the item
		// that was clicked.
		btnRemove.setTag(entry);
		
		// btnRemove.setId(position);
		
		return convertView;
	}
	
	@Override
	public void onClick(View view)
	{
		Phonebook entry = (Phonebook) view.getTag();
		listPhonebook.remove(entry);
		// listPhonebook.remove(view.getId());
		notifyDataSetChanged();
		
	}
	
	private void showDialog(Phonebook entry)
	{
		// Create and show your dialog
		// Depending on the Dialogs button clicks delete it or do nothing
	}
	
}
*/