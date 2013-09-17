package com.pk.chemhelp;

public class Question
{
	// Variables
	private String Type;
	private String Name;
	private String Description;
	private String Image;
	private String Answer;
	private boolean Answered;
	private boolean Correct;

	// Constructor
	public Question(String t, String n, String d, String i, String a)
	{
		super();
		Type = t;
		Name = n;
		Description = d;
		Image = i;
		Answer = a;
	}
	
	// Constructor For Storing Answers
	public Question(String a)
	{
		super();
		Type = "PCX_Answer";
		Name = "PCX_Answer";
		Description = "PCX_Answer";
		Image = "PCX_Answer";
		Answer = a;
		Answered = true;
	}

	// Getter and Setter Methods
	public String getType()
	{
		return Type;
	}
	
	public void setType(String t)
	{
		Type = t;
	}
	public String getName()
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

	public String getImage()
	{
		return Image;
	}

	public void setImage(String i)
	{
		Image = i;
	}
	
	public String getAnswer()
	{
		return Answer;
	}
	
	public void setAnswer(String a)
	{
		Answer = a;
	}
	
	public boolean isAnswered()
	{
		return Answered;
	}
	
	public void setAnswered(boolean ia)
	{
		Answered = ia;
	}
	
	public boolean isCorrect()
	{
		return Correct;
	}
	
	public void setCorrect(boolean c)
	{
		Correct = c;
	}
}