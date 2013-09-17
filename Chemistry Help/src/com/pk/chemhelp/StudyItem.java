package com.pk.chemhelp;

public class StudyItem
{
	// Variables
	private String item;
	private String description;
	private String value;
	private boolean isHeader;
	private boolean isLast;
	
	// Constructor
	public StudyItem(String item, String description)
	{
		super();
		this.item = item;
		this.description = description;
		this.value = "0";
		this.isHeader = false;
		this.isLast = false;
	}
	
	public StudyItem(boolean isHeader, boolean isLast, String item, String description)
	{
		super();
		this.item = item;
		this.description = description;
		this.value = "0";
		this.isHeader = isHeader;
		this.isLast = isLast;
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
}
