package com.pk.chemhelp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;

public class ElementFragment extends SherlockFragment
{
	public final Chemical ElementID;
	
	public ElementFragment()
	{
		this.ElementID = new Chemical();
	}
	
	public ElementFragment(Chemical ElementID)
	{
		this.ElementID = ElementID;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.e("Test", "hello");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.elementfragment, container, false);
		
		TextView textMeltingPoint = (TextView) view.findViewById(R.id.textMeltingPoint);
		TextView textBoilingPoint = (TextView) view.findViewById(R.id.textBoilingPoint);
		
		TextView Element = (TextView) view.findViewById(R.id.Element);
		TextView NameValue = (TextView) view.findViewById(R.id.NameValue);
		TextView SymbolValue = (TextView) view.findViewById(R.id.SymbolValue);
		TextView AtomicNumberValue = (TextView) view.findViewById(R.id.AtomicNumberValue);
		TextView AtomicMassValue = (TextView) view.findViewById(R.id.AtomicMassValue);
		TextView ProtonNumberValue = (TextView) view.findViewById(R.id.ProtonNumberValue);
		TextView NeutronNumberValue = (TextView) view.findViewById(R.id.NeutronNumberValue);
		TextView ElectronNumberValue = (TextView) view.findViewById(R.id.ElectronNumberValue);
		TextView ClassificationValue = (TextView) view.findViewById(R.id.ClassificationValue);
		TextView MeltingPointValue = (TextView) view.findViewById(R.id.MeltingPointValue);
		TextView BoilingPointValue = (TextView) view.findViewById(R.id.BoilingPointValue);
		
		Element.setText(ElementID.getName());
		NameValue.setText(ElementID.getName());
		SymbolValue.setText(ElementID.getFormula());
		AtomicNumberValue.setText("" + ElementID.getAtomicNumber());
		AtomicMassValue.setText("" + ElementID.getAtomicMass());
		ProtonNumberValue.setText("" + ElementID.getNumProtons());
		NeutronNumberValue.setText("" + ElementID.getNumNeutrons());
		ElectronNumberValue.setText("" + ElementID.getNumElectrons());
		ClassificationValue.setText(ElementID.getClassification());
		if(ElementID.nullMeltingPoint())
		{
			MeltingPointValue.setVisibility(View.GONE);
			textMeltingPoint.setVisibility(View.GONE);
		}
		else
			MeltingPointValue.setText("" + ElementID.getMeltingPoint());
		if(ElementID.nullBoilingPoint())
		{
			BoilingPointValue.setVisibility(View.GONE);
			textBoilingPoint.setVisibility(View.GONE);
		}
		else
			BoilingPointValue.setText("" + ElementID.getBoilingPoint());
		
		
		ViewPager mPager = ViewElement.getPager();
		mPager.setPageMargin(-100);
		ViewElement.setPager(mPager);
		
		return view;
	}
}
