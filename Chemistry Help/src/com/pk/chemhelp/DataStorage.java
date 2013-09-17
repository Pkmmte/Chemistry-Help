package com.pk.chemhelp;

import android.app.Application;

public class DataStorage extends Application
{	
	@Override
	public void onCreate()
	{
		super.onCreate();

		// Initialize the singletons so their instances
		// are bound to the application process.
		initSingletons();
		initDefaultValues();
    }
	
	protected void initSingletons()
	{
		// Initialize the instance of MySingleton
		MySingleton.initInstance();
	}
	
	protected void initDefaultValues()
	{
		MySingleton.initDefaultValues();
	}
}
