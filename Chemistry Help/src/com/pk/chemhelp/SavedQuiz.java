package com.pk.chemhelp;

public class SavedQuiz
{
	// Variables
	private String Name;
	private String Description;
	private String Icon;
	
	// Constructor
	public SavedQuiz(String n, String d, String i)
	{
		super();
		Name = n;
		Description = d;
		Icon = i;
	}
	
	// Getter and Setter Methods
	public String getItemName()
	{
		return Name;
	}
	
	public void setName(String n)
	{
		Name = n;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	public void setDescription(String d)
	{
		Description = d;
	}
	
	public String getIcon()
	{
		return Icon;
	}
	
	public void setIcon(String i)
	{
		Icon = i;
	}
}
