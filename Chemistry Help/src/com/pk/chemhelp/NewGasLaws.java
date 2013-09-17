package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;

import java.util.ArrayList;
import java.util.List;

public class NewGasLaws extends SherlockFragmentActivity
{
	public MyAdapter mAdapter;
	private static ViewPager mPager;
	
	public static String[] Content;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablet);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		mAdapter = new MyAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.LawsPager);
		mPager.setAdapter(mAdapter);
		
		Content = new String[25];
	}
	
	public static class GasLawFragment extends SherlockFragment
	{
		String[] Arguments;

		TextView text1;
		TextView text2;
		TextView text3;
		TextView text4;
		TextView text5;
		TextView text6;
		EditText edit1;
		EditText edit2;
		EditText edit3;
		EditText edit4;
		EditText edit5;
		EditText edit6;
		Spinner spinner1;
		Spinner spinner2;
		Spinner spinner3;
		Spinner spinner4;
		Spinner spinner5;
		Spinner spinner6;
		
		public GasLawFragment()
		{
			Arguments = new String[]{"Turtles", "Pie", "Empty Constructor"};
		}
		
		public GasLawFragment(String[] args)
		{
			Arguments = args;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.gaslawfragment, container, false);

			declareObjects(view);
			setVisibles();
			setObjectText();
			
			return view;
		}
		
		public void declareObjects(View view)
		{
			text1 = (TextView) view.findViewById(R.id.textView1);
			text2 = (TextView) view.findViewById(R.id.textView2);
			text3 = (TextView) view.findViewById(R.id.textView3);
			text4 = (TextView) view.findViewById(R.id.textView4);
			text5 = (TextView) view.findViewById(R.id.textView5);
			text6 = (TextView) view.findViewById(R.id.textView6);
			
			edit1 = (EditText) view.findViewById(R.id.editText1);
			edit2 = (EditText) view.findViewById(R.id.editText2);
			edit3 = (EditText) view.findViewById(R.id.editText3);
			edit4 = (EditText) view.findViewById(R.id.editText4);
			edit5 = (EditText) view.findViewById(R.id.editText5);
			edit6 = (EditText) view.findViewById(R.id.editText6);
			
			spinner1 = (Spinner) view.findViewById(R.id.spinner1);
			spinner2 = (Spinner) view.findViewById(R.id.spinner2);
			spinner3 = (Spinner) view.findViewById(R.id.spinner3);
			spinner4 = (Spinner) view.findViewById(R.id.spinner4);
			spinner5 = (Spinner) view.findViewById(R.id.spinner5);
			spinner6 = (Spinner) view.findViewById(R.id.spinner6);
		}
		
		public void setVisibles()
		{
			if(Arguments[0].equals("Boyle's Law"))
			{
				text1.setVisibility(View.VISIBLE);
				text2.setVisibility(View.VISIBLE);
				text3.setVisibility(View.VISIBLE);
				text4.setVisibility(View.VISIBLE);
				
				edit1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.VISIBLE);
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				
				spinner1.setVisibility(View.VISIBLE);
				spinner2.setVisibility(View.VISIBLE);
				spinner3.setVisibility(View.VISIBLE);
				spinner4.setVisibility(View.VISIBLE);
			}
			else if(Arguments[0].equals("Charle's Law"))
			{
				text1.setVisibility(View.VISIBLE);
				text2.setVisibility(View.VISIBLE);
				text3.setVisibility(View.VISIBLE);
				text4.setVisibility(View.VISIBLE);
				
				edit1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.VISIBLE);
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				
				spinner1.setVisibility(View.VISIBLE);
				spinner2.setVisibility(View.VISIBLE);
				spinner3.setVisibility(View.VISIBLE);
				spinner4.setVisibility(View.VISIBLE);
			}
			else if(Arguments[0].equals("Gay Lussac's Law"))
			{
				text1.setVisibility(View.VISIBLE);
				text2.setVisibility(View.VISIBLE);
				text3.setVisibility(View.VISIBLE);
				text4.setVisibility(View.VISIBLE);
				
				edit1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.VISIBLE);
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				
				spinner1.setVisibility(View.VISIBLE);
				spinner2.setVisibility(View.VISIBLE);
				spinner3.setVisibility(View.VISIBLE);
				spinner4.setVisibility(View.VISIBLE);
			}
			else if(Arguments[0].equals("Ideal Gas Law"))
			{
				text1.setVisibility(View.VISIBLE);
				text2.setVisibility(View.VISIBLE);
				text3.setVisibility(View.VISIBLE);
				text4.setVisibility(View.VISIBLE);
				
				edit1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.VISIBLE);
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				
				spinner1.setVisibility(View.VISIBLE);
				spinner2.setVisibility(View.VISIBLE);
				spinner3.setVisibility(View.VISIBLE);
				spinner4.setVisibility(View.VISIBLE);
			}
			else if(Arguments[0].equals("Combined Gas Law"))
			{
				text1.setVisibility(View.VISIBLE);
				text2.setVisibility(View.VISIBLE);
				text3.setVisibility(View.VISIBLE);
				text4.setVisibility(View.VISIBLE);
				text5.setVisibility(View.VISIBLE);
				text6.setVisibility(View.VISIBLE);
				
				edit1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.VISIBLE);
				edit3.setVisibility(View.VISIBLE);
				edit4.setVisibility(View.VISIBLE);
				edit5.setVisibility(View.VISIBLE);
				edit6.setVisibility(View.VISIBLE);
				
				spinner1.setVisibility(View.VISIBLE);
				spinner2.setVisibility(View.VISIBLE);
				spinner3.setVisibility(View.VISIBLE);
				spinner4.setVisibility(View.VISIBLE);
				spinner5.setVisibility(View.VISIBLE);
				spinner6.setVisibility(View.VISIBLE);
			}
			else
				MySingleton.getInstance().addError("Gas Law Fragment: Could not set visibles... " + Arguments[0]);
		}
		
		public void setObjectText()
		{
			if(Arguments[0].equals("Boyle's Law"))
			{
				text1.setText("(V1) Initial Volume");
				text2.setText("(P1) Initial Pressure");
				text3.setText("(V2) Final Volume");
				text4.setText("(P2) Final Pressure");
			}
			else if(Arguments[0].equals("Charle's Law"))
			{
				text1.setText("(V1) Initial Volume");
				text2.setText("(T1) Initial Temperature");
				text3.setText("(V2) Final Volume");
				text4.setText("(T2) Final Temperature");
			}
			else if(Arguments[0].equals("Gay Lussac's Law"))
			{
				text1.setText("(P1) Initial Pressure");
				text2.setText("(T1) Initial Temperature");
				text3.setText("(P2) Final Pressure");
				text4.setText("(T2) Final Temperature");
			}
			else if(Arguments[0].equals("Ideal Gas Law"))
			{
				text1.setText("(N) Moles");
				text2.setText("(P) Pressure");
				text3.setText("(V) Volume");
				text4.setText("(T) Temperature");
			}
			else if(Arguments[0].equals("Combined Gas Law"))
			{
				text1.setText("(V1) Initial Volume");
				text2.setText("(P1) Initial Pressure");
				text3.setText("(T1) Final Temperature");
				text4.setText("(V2) Final Volume");
				text3.setText("(P2) Final Pressure");
				text4.setText("(T2) Final Temperature");
			}
			else
				MySingleton.getInstance().addError("Could not set object text values... " + Arguments[0]);
		}
	}
	
	public static class GasListFragment extends SherlockListFragment
	{
		final List<String> listOfItems = new ArrayList<String>();
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			listOfItems.add("Boyle's Law");
			listOfItems.add("Charle's Law");
			listOfItems.add("Gay Lussac's Law");
			listOfItems.add("Ideal Gas Law");
			listOfItems.add("Combined Gas Law");
			
			GasListAdapter adapter = new GasListAdapter(getActivity().getBaseContext(), listOfItems);
			setListAdapter(adapter);
			
			//getListView().setSelector(R.drawable.layout_selector);
			
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id)
		{
			String ID = listOfItems.get(position);
			Intent selectedLaw;
			if (ID.equals("Boyle's Law"))
				selectedLaw = new Intent(getActivity().getBaseContext(), BoylesLaw.class);
			else if (ID.equals("Charle's Law"))
				selectedLaw = new Intent(getActivity().getBaseContext(), CharlesLaw.class);
			else if (ID.equals("Ideal Gas Law"))
				selectedLaw = new Intent(getActivity().getBaseContext(), IdealGasLaw.class);
			else if (ID.equals("Gay Lussac's Law"))
				selectedLaw = new Intent(getActivity().getBaseContext(), GayLussacsLaw.class);
			else if (ID.equals("Combined Gas Law"))
				selectedLaw = new Intent(getActivity().getBaseContext(), CombinedGasLaw.class);
			else
			{
				selectedLaw = new Intent(getActivity().getBaseContext(), ChemistryHelp.class);
				MySingleton.getInstance().addError("Gas Laws List: Unrecognizable Item ID..." + ID);
			}
			startActivity(selectedLaw);
		}
	}
	
	public static class GasListAdapter extends BaseAdapter implements OnClickListener
	{
		private Context context;

		private List<String> listItem;

		public GasListAdapter(Context context, List<String> listItem)
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

		public View getView(int position, View gasView, ViewGroup viewGroup)
		{
			String entry = listItem.get(position);
			if (gasView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				gasView = inflater.inflate(R.layout.gaslaws_row, null);
			}

			ImageView Image = (ImageView) gasView.findViewById(R.id.imageView1);
			TextView Item = (TextView) gasView.findViewById(R.id.Item);
			TextView Description = (TextView) gasView.findViewById(R.id.Description);

			Item.setText(entry);
			
			if (entry.equals("Boyle's Law"))
			{
				Image.setImageResource(R.drawable.boyles_icon);
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>"));
			}
			else if (entry.equals("Charle's Law"))
			{
				Image.setImageResource(R.drawable.charles_icon);
				Description.setText(Html.fromHtml("V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
			}
			else if (entry.equals("Gay Lussac's Law"))
			{
				Image.setImageResource(R.drawable.lussac_icon);
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>T<sub><small>2</small></sub> = P<sub><small>2</small></sub>T<sub><small>1</small></sub>"));
			}
			else if (entry.equals("Ideal Gas Law"))
			{
				Image.setImageResource(R.drawable.ideal_icon);
				Description.setText(Html.fromHtml("PV = nRT"));
			}
			else if (entry.equals("Combined Gas Law"))
			{
				Image.setImageResource(R.drawable.combined_icon);
				Description.setText(Html.fromHtml("P<sub><small>1</small></sub>V<sub><small>1</small></sub>/T<sub><small>1</small></sub> = P<sub><small>2</small></sub>V<sub><small>2</small></sub>/T<sub><small>2</small></sub>"));
			}

			return gasView;
		}

		@Override
		public void onClick(View view)
		{
			String entry = (String) view.getTag();
			listItem.remove(entry);
			notifyDataSetChanged();
		}
	}
	
	public static class MyAdapter extends FragmentPagerAdapter
	{
		public MyAdapter(FragmentManager fm)
		{
			super(fm);
		}
		
		@Override
		public int getCount()
		{
			return 5;
		}
		
		@Override
		public Fragment getItem(int position)
		{
			switch (position)
			{
				case 0:
					Content[0] = "Boyle's Law";
					MySingleton.getInstance().addLog(Content[0]);
					return new GasLawFragment(Content);
				case 1:
					Content[0] = "Charle's Law";
					MySingleton.getInstance().addLog(Content[0]);
					return new GasLawFragment(Content);
				case 2:
					Content[0] = "Gay Lussac's Law";
					MySingleton.getInstance().addLog(Content[0]);
					return new GasLawFragment(Content);
				case 3:
					Content[0] = "Ideal Gas Law";
					MySingleton.getInstance().addLog(Content[0]);
					return new GasLawFragment(Content);
				case 4:
					Content[0] = "Combined Gas Law";
					MySingleton.getInstance().addLog(Content[0]);
					return new GasLawFragment(Content);
					
				default:
					MySingleton.getInstance().addError("Default Gas Law Fragment Array");
					return new GasLawFragment();
			}
		}
	}
}
