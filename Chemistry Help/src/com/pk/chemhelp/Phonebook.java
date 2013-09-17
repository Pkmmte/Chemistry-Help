package com.pk.chemhelp;

public class Phonebook
{
	private String name;
	private String phone;
	private String mail;
	private String icon;
	
	// Constructor for the Phonebook class
	public Phonebook(String name, String phone, String mail, String icon)
	{
		super();
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.icon = icon;
	}
	
	// Getter and setter methods for all the fields.
	// Though you would not be using the setters for this example,
	// it might be useful later.
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getMail()
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
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
