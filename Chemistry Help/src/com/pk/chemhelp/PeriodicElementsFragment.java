package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.SherlockFragment;

import java.util.ArrayList;
import java.util.List;

public class PeriodicElementsFragment extends SherlockFragment
{
	ListView list;
	LinearLayout Selector;
	EditText SearchField;
	
	List<Chemical> listOfItems;
	ChemicalAdapter adapter;
	
	Chemical[] Chem;
	int numChemicals;
	boolean searchActive;
	String searchString;
	
	String Sort;
	boolean ShowSelector;
	boolean ShowSearch;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.periodicelementfragment, container, false);
		
		Selector = (LinearLayout) view.findViewById(R.id.Selector);
		list = (ListView) view.findViewById(R.id.ListView);
		SearchField = (EditText) view.findViewById(R.id.Search);
		
		Sort = MySingleton.getInstance().getPeriodicSort();
		ShowSelector = MySingleton.getInstance().getPeriodicShowSelector();
		ShowSearch = MySingleton.getInstance().getPeriodicShowSearch();
		
		if(ShowSelector)
			Selector.setVisibility(View.VISIBLE);
		else
			Selector.setVisibility(View.GONE);
		
		if(ShowSearch)
			SearchField.setVisibility(View.VISIBLE);
		else
			SearchField.setVisibility(View.GONE);
		
		list.setClickable(true);
		searchActive = false;
		searchString = "";
		
		Chem = Chemical.getElements(Sort);
		numChemicals = Chem.length;
		
		list.setDividerHeight(1);
		
		populateList();
		setSelector(view);
		
		SearchField.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				// Do nothing...
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				// Do nothing...
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				if(SearchField.getText().toString().length() > 0)
					Selector.setVisibility(View.GONE);
				else
					Selector.setVisibility(View.VISIBLE);
				
				onInput();
			}
		});
		
		return view;
	}
	
	public void populateList()
	{
		listOfItems = new ArrayList<Chemical>();
		
		if(searchActive)
		{
			MySingleton.getInstance().addLog("Active Name: " + searchString);
			for(int x = 0; x < numChemicals; x++)
			{
				if(Chem[x].getName().toLowerCase().contains(searchString) || Chem[x].getFormula().toLowerCase().contains(searchString) || ("" + Chem[x].getAtomicNumber()).contains(searchString) || ("" + Chem[x].getAtomicMass()).contains(searchString))
					listOfItems.add(new Chemical(Chem[x].getAtomicNumber(), Chem[x].getAtomicMass(), Chem[x].getName(), Chem[x].getFormula(), Chem[x].getNumProtons(), Chem[x].getNumNeutrons(), true));
			}
		}
		else
		{
			for(int x = 0; x < numChemicals; x++)
				listOfItems.add(new Chemical(Chem[x].getAtomicNumber(), Chem[x].getAtomicMass(), Chem[x].getName(), Chem[x].getFormula(), Chem[x].getNumProtons(), Chem[x].getNumNeutrons(), true));
		}
		
		adapter = new ChemicalAdapter(getActivity().getBaseContext(), listOfItems);

		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long index)
			{
				String Element = listOfItems.get(position).getName();
				int ElementPosition = listOfItems.get(position).getAtomicNumber();
				Intent elementIntent = new Intent(getActivity().getBaseContext(), ViewElement.class);
				elementIntent.putExtra("Element", Element);
				elementIntent.putExtra("Element Position", ElementPosition);
				startActivity(elementIntent);
			}
		});

		list.setAdapter(adapter);
	}
	
	public void onInput()
	{
		searchString = SearchField.getText().toString().toLowerCase();
		if(searchString.length() > 0)
			searchActive = true;
		else
			searchActive = false;
		
		MySingleton.getInstance().addLog("onInput() called: " + searchString);
		listOfItems.clear();
		populateList();
		adapter.notifyDataSetChanged();
	}
	
	public void setSelector(View view)
	{
		if(Sort.equals("Atomic Number") || Sort.equals("Atomic Mass"))
			Selector.setVisibility(View.GONE);
		else
		{
			int xB = R.id.A;
			TextView[] SelectorArray = new TextView[26];
			for (int b = 0; b < 26; b++)
			{
				SelectorArray[b] = (TextView) view.findViewById(xB);
				final int numbah = b;
				
				SelectorArray[b].setOnTouchListener(new View.OnTouchListener()
				{
					@Override
					public boolean onTouch(View v, MotionEvent event)
					{
						if (event.getAction() == MotionEvent.ACTION_DOWN )
						{
							int index = 54;
							if(numbah == 0)
								index = 0;
							else if(numbah == 1)
							{
								if(Sort.equals("Symbol"))
									index = 8;
								else if(Sort.equals("Name"))
									index = 7;
							}
							else if(numbah == 2)
							{
								if(Sort.equals("Symbol"))
									index = 15;
								else if(Sort.equals("Name"))
									index = 14;
							}
							else if(numbah == 3)
							{
								if(Sort.equals("Symbol"))
									index = 27;
								else if(Sort.equals("Name"))
									index = 26;
							}
							else if(numbah == 4)
							{
								if(Sort.equals("Symbol"))
									index = 30;
								else if(Sort.equals("Name"))
									index = 29;
							}
							else if(numbah == 5)
							{
								if(Sort.equals("Symbol"))
									index = 33;
								else if(Sort.equals("Name"))
									index = 32;
							}
							else if(numbah == 6)
							{
								if(Sort.equals("Symbol"))
									index = 38;
								else if(Sort.equals("Name"))
									index = 36;
							}
							else if(numbah == 7)
							{
								if(Sort.equals("Symbol"))
									index = 41;
								else if(Sort.equals("Name"))
									index = 40;
							}
							else if(numbah == 8)
							{
								if(Sort.equals("Symbol"))
									index = 47;
								else if(Sort.equals("Name"))
									index = 45;
							}
							else if(numbah == 9) // J
							{
								if(Sort.equals("Symbol"))
									index = 47;
								else if(Sort.equals("Name"))
									index = 45;
							}
							else if(numbah == 10)
							{
								if(Sort.equals("Symbol"))
									index = 50;
								else if(Sort.equals("Name"))
									index = 49;
							}
							else if(numbah == 11)
							{
								if(Sort.equals("Symbol"))
									index = 52;
								else if(Sort.equals("Name"))
									index = 50;
							}
							else if(numbah == 12) // M
							{
								if(Sort.equals("Symbol"))
									index = 57;
								else if(Sort.equals("Name"))
									index = 56;
							}
							else if(numbah == 13)
							{
								if(Sort.equals("Symbol"))
									index = 62;
								else if(Sort.equals("Name"))
									index = 62;
							}
							else if(numbah == 14)
							{
								if(Sort.equals("Symbol"))
									index = 70;
								else if(Sort.equals("Name"))
									index = 69;
							}
							else if(numbah == 15)
							{
								if(Sort.equals("Symbol"))
									index = 72;
								else if(Sort.equals("Name"))
									index = 71;
							}
							else if(numbah == 16) // Q
							{
								if(Sort.equals("Symbol"))
									index = 72;
								else if(Sort.equals("Name"))
									index = 71;
							}
							else if(numbah == 17)
							{
								if(Sort.equals("Symbol"))
									index = 81;
								else if(Sort.equals("Name"))
									index = 80;
							}
							else if(numbah == 18) // S
							{
								if(Sort.equals("Symbol"))
									index = 89;
								else if(Sort.equals("Name"))
									index = 88;
							}
							else if(numbah == 19)
							{
								if(Sort.equals("Symbol"))
									index = 98;
								else if(Sort.equals("Name"))
									index = 97;
							}
							else if(numbah == 20)
							{
								if(Sort.equals("Symbol"))
									index = 106;
								else if(Sort.equals("Name"))
									index = 107;
							}
							else if(numbah == 21)
							{
								if(Sort.equals("Symbol"))
									index = 111;
								else if(Sort.equals("Name"))
									index = 112;
							}
							else if(numbah == 22)
							{
								if(Sort.equals("Symbol"))
									index = 112;
								else if(Sort.equals("Name"))
									index = 113;
							}
							else if(numbah == 23)
							{
								if(Sort.equals("Symbol"))
									index = 113;
								else if(Sort.equals("Name"))
									index = 113;
							}
							else if(numbah == 24)
							{
								if(Sort.equals("Symbol"))
									index = 114;
								else if(Sort.equals("Name"))
									index = 114;
							}
							else if(numbah == 25)
							{
								if(Sort.equals("Symbol"))
									index = 115;
								else if(Sort.equals("Name"))
									index = 116;
							}
							View vi = list.getChildAt(0);
							int top = (v == null) ? 0 : vi.getTop();

							list.setSelectionFromTop(index, top);
                		}
						return false;
					}
				});
				xB++;
			}
		}
	}
	
	public class ChemicalAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;
		
		private List<Chemical> listItem;
		
		public ChemicalAdapter(Context context, List<Chemical> listItem)
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
			Chemical entry = listItem.get(position);
			if (convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.element_row, null);
			}
			
			ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageView);
			
			TextView Name = (TextView) convertView.findViewById(R.id.Name);
			Name.setText(entry.getName());
			
			TextView Formula = (TextView) convertView.findViewById(R.id.Formula);
			Formula.setText(entry.getFormula());
			
			TextView Number = (TextView) convertView.findViewById(R.id.Number);
			Number.setText("" + entry.getAtomicNumber());
			
			TextView Mass = (TextView) convertView.findViewById(R.id.Mass);
			Mass.setText("" + entry.getAtomicMass());
			
			return convertView;
		}
		
		@Override
		public void onClick(View view)
		{
			Chemical entry = (Chemical) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();
			
		}
		
	}

}
