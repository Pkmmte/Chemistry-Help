package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.SherlockFragment;

import java.util.ArrayList;
import java.util.List;

public class StudyFragment extends SherlockFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.studyfragment, container, false);
		
		ListView list = (ListView) view.findViewById(R.id.ListView);
		list.setClickable(true);
		
		int[] colors = { 0, 0xFF00FFFF, 0 };
		list.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		list.setDividerHeight(1);
		
		final List<StudyItem> listOfItems = new ArrayList<StudyItem>();
		listOfItems.add(new StudyItem("Periodic Table", "All of the periodic table basics!"));
		listOfItems.add(new StudyItem("Periodic Elements", "For those who don't like the dynamic periodic table."));
		
		StudyAdapter adapter = new StudyAdapter(getActivity().getBaseContext(), listOfItems);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				String ID = listOfItems.get(position).getItemName();
				Intent studyIntent;
				
				if(ID.equals("Periodic Elements"))
				{
					studyIntent = new Intent(getActivity().getBaseContext(), ViewElement.class);
					startActivity(studyIntent);
				}
				 
				//showToast(listOfItems.get(position).getItemName());
			}
		});
		
		list.setAdapter(adapter);
		
		return view;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	public class StudyAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;
		
		private List<StudyItem> listItem;
		
		public StudyAdapter(Context context, List<StudyItem> listItem)
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
			StudyItem entry = listItem.get(position);
			if (convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.study_row, null);
			}
			
			ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView1);
			//String icon = entry.getIcon();
			iconImage.setVisibility(View.GONE); // Later
			
			TextView Item = (TextView) convertView.findViewById(R.id.Item);
			Item.setText(entry.getItemName());
			
			TextView Description = (TextView) convertView.findViewById(R.id.Description);
			Description.setText(entry.getDescription());
			
			return convertView;
		}
		
		@Override
		public void onClick(View view)
		{
			StudyItem entry = (StudyItem) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();
		}
	}
}
