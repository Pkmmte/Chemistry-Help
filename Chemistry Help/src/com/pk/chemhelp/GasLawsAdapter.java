package com.pk.chemhelp;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GasLawsAdapter extends BaseAdapter implements OnClickListener
{
	private Context context;
	
	private List<GasLawsItem> listItem;
	
	public GasLawsAdapter(Context context, List<GasLawsItem> listItem)
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
		GasLawsItem entry = listItem.get(position);
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.gaslaws_row, null);
		}
		
		ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
		String icon = entry.getIcon();
		if (icon.equals("boyles"))
			iconImage.setImageResource(R.drawable.boyles_icon);
		else if (icon.equals("charles"))
			iconImage.setImageResource(R.drawable.charles_icon);
		else if (icon.equals("ideal"))
			iconImage.setImageResource(R.drawable.ideal_icon);
		else if (icon.equals("gay"))
			iconImage.setImageResource(R.drawable.lussac_icon);
		else if (icon.equals("combined"))
			iconImage.setImageResource(R.drawable.combined_icon);
		
		TextView Item = (TextView) convertView.findViewById(R.id.Item);
		Item.setText(entry.getItemName());
		
		TextView Description = (TextView) convertView.findViewById(R.id.Description);
		if(entry.getDescription().equals("P1V1 = P2V2"))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>"));
		else if(entry.getDescription().equals("V1/T1 = V2/T2"))
			Description.setText(Html.fromHtml("V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
		else if(entry.getDescription().equals("P1T2 = P2T1"))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>T<sub><small>2</small></sub> = P<sub><small>2</small></sub>T<sub><small>1</small></sub>"));
		else if(entry.getDescription().equals("PV = nRT"))
			Description.setText(Html.fromHtml("PV = nRT"));
		else if(entry.getDescription().equals("P1V1/T1 = P2V2/T2"))
			Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
		else
			Description.setText(entry.getDescription());
		
		return convertView;
	}
	
	@Override
	public void onClick(View view)
	{
		GasLawsItem entry = (GasLawsItem) view.getTag();
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
