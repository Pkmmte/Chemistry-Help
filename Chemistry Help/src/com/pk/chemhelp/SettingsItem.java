package com.pk.chemhelp;

public class SettingsItem
{
	// Variables
	private String item;
	private String description;
	private String icon;
	private String value;
	private boolean isHeader;
	private boolean isLast;
	private boolean checkBox;
	
	// Constructor
	public SettingsItem(String item, String description, String icon)
	{
		super();
		this.item = item;
		this.description = description;
		this.icon = icon;
		this.value = "0";
		this.isHeader = false;
		this.isLast = false;
		this.checkBox = false;
	}
	
	public SettingsItem(boolean isHeader, boolean isLast, String item,
			String description)
	{
		super();
		this.item = item;
		this.description = description;
		this.icon = "none";
		this.value = "0";
		this.isHeader = isHeader;
		this.isLast = isLast;
		this.checkBox = false;
	}
	
	public SettingsItem(boolean isHeader, boolean isLast, boolean checkBox, String item,
			String description, String value)
	{
		super();
		this.item = item;
		this.description = description;
		this.icon = "none";
		this.value = value;
		this.isHeader = isHeader;
		this.isLast = isLast;
		this.checkBox = checkBox;
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
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public boolean isHeader()
	{
		return isHeader;
	}
	
	public void setHeader(boolean head)
	{
		this.isHeader = head;
	}
	
	public boolean isLast()
	{
		return isLast;
	}
	
	public void setLast(boolean last)
	{
		this.isLast = last;
	}
	
	public boolean isCheck()
	{
		return checkBox;
	}
	
	public void setCheck(boolean box)
	{
		this.checkBox = box;
	}
}
