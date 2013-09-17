package com.pk.chemhelp;

public class GasLawsItem
{
	// Variables
	private String item;
	private String description;
	private String icon;
	
	// Constructor
	public GasLawsItem(String item, String description, String icon)
	{
		super();
		this.item = item;
		this.description = description;
		this.icon = icon;
	}
	
	// Getter and Setter Methods
	public String getItemName()
	{
		return item;
	}
	
	public void setItemName(String item)
	{
		this.item = item;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getIcon()
	{
		return icon;
	}
	
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
}
