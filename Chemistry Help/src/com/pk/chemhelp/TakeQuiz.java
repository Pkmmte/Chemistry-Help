package com.pk.chemhelp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TakeQuiz extends SherlockActivity
{
	protected DataStorage appState;
	final String PageID = "Take Quiz";
	boolean PRO;
	private boolean Exit;
	
	/* Globals */
	boolean Quiz_NotifyCorrect;
	boolean Quiz_ShowTime;
	boolean Quiz_ShowPercent;
	boolean Quiz_ShowProgress;
	
	/* UI Elements */
	TextView Time;
	TextView Correct;
	TextView Progress;
	TextView Question;
	EditText Answer;
	Button Previous;
	Button Next;
	RadioGroup MultipleChoiceGroup;
	RadioButton Choice1;
	RadioButton Choice2;
	RadioButton Choice3;
	RadioButton Choice4;
	
	Button Start;
	TextView textReady;
	TextView textCategory;
	TextView textCategoryValue;
	TextView textTotalQuestions;
	TextView textTotalQuestionsValue;
	TextView textQuizType;
	TextView textQuizTypeValue;
	TextView textTimeLimit;
	TextView textTimeLimitValue;
	
	TextView Results;
	TextView ResultCorrect;
	TextView ResultTime;
	TextView CorrectValue;
	TextView TimeValue;
	Button GoBack;
	Button TryAgain;
	
	/* Timer */
	boolean timerResume;
	int savedSeconds;
	int savedMinutes;
	int savedHours;
	
	/* Random Number Generator */
	Random generator;
	int randomMultipleChoice;
	Integer[] usedNumbers;
	
	/* Grab Configuration From Previous Activity */
	Bundle quizConfig;
	Intent quizIntent;
	int numCategories;
	int numQuestions;
	int timeLimit;
	String[] quizCategories;
	String[] quizValues;
	String[] quizTypes;
	String[] Choices;
	
	/* Progress & Percent Correct */
	boolean runningQuiz;
	int totalQuestions;
	double perCorrect;
	int numCorrect;
	int numProgress;
	int maxProgress;
	
	/* Get Out Of Quiz Prematurely */
	Dialog confirmDialog;
	TextView confirmDialogTitle;
	TextView confirmDialogValue;
	Button confirmDialogYes;
	Button confirmDialogNo;
	
	/* Get Questions & Answer */
	Question[] Questions;
	Question[] UserAnswers;
	String multAnswer;
	
	/* Debugging */
	MenuItem debugMenu;
	boolean DEBUG_MODE = MySingleton.getInstance().getDebugMode();
	boolean FORCE_STOP;
	
	/* TIMER */
	long starttime = 0;
	final Handler h = new Handler(new Callback()
	{
		@Override
		public boolean handleMessage(Message msg)
		{
			long millis = System.currentTimeMillis() - starttime;
			int seconds = (int) (millis / 1000);
			int minutes = seconds / 60;
			int hours = minutes / 60;
			seconds = seconds % 60;
			minutes = minutes % 60;
			
			if(timerResume)
			{
				timerResume = false;
				seconds = savedSeconds;
				minutes = savedMinutes;
				hours = savedHours;
			}
			
			Time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
			
			if(timeLimit <= minutes)
				setResults();
			
			return false;
		}
	});
	
	// Tells handler to send a message
	class firstTask extends TimerTask
	{
		@Override
		public void run()
		{
			h.sendEmptyMessage(0);
		}
	};
	
	Timer timer = new Timer();
	
	/* Activity Starts Here */
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.takequiz);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Quiz");
		
		PRO = MySingleton.getInstance().isPRO();
		
		quizIntent = getIntent();
		quizValues = new String[500];
		quizTypes = new String[2];
		numCategories = 0;
		numQuestions = 0;
		
		Quiz_NotifyCorrect = MySingleton.getInstance().getQuizNotifyCorrect();
		Quiz_ShowTime = MySingleton.getInstance().getQuizShowTime();
		Quiz_ShowPercent = MySingleton.getInstance().getQuizShowPercent();
		Quiz_ShowProgress = MySingleton.getInstance().getQuizShowProgress();
		
		confirmDialog = new Dialog(TakeQuiz.this);
		confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		confirmDialog.setContentView(R.layout.dialog_confirm);
		confirmDialog.setCancelable(true);
		confirmDialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
		confirmDialogTitle = (TextView) confirmDialog.findViewById(R.id.Title);
		confirmDialogValue = (TextView) confirmDialog.findViewById(R.id.Value);
		confirmDialogYes = (Button) confirmDialog.findViewById(R.id.Yes);
		confirmDialogNo = (Button) confirmDialog.findViewById(R.id.No);
		confirmDialogTitle.setText("Confirm");
		confirmDialogValue.setText("Are you sure you wish to go back?\nYou will have to start your quiz over again from the beginning.");
		
		Time = (TextView) findViewById(R.id.textTime);
		Correct = (TextView) findViewById(R.id.textCorrect);
		Progress = (TextView) findViewById(R.id.textProgress);
		Question = (TextView) findViewById(R.id.textQuestion);
		Answer = (EditText) findViewById(R.id.editAnswer);
		Previous = (Button) findViewById(R.id.Previous);
		Next = (Button) findViewById(R.id.Next);
		MultipleChoiceGroup = (RadioGroup) findViewById(R.id.MultipleChoiceGroup);
		Choice1 = (RadioButton) findViewById(R.id.Choice1);
		Choice2 = (RadioButton) findViewById(R.id.Choice2);
		Choice3 = (RadioButton) findViewById(R.id.Choice3);
		Choice4 = (RadioButton) findViewById(R.id.Choice4);
		
		Start = (Button) findViewById(R.id.Start);
		textReady = (TextView) findViewById(R.id.textReady);
		textCategory = (TextView) findViewById(R.id.textCategory);
		textCategoryValue = (TextView) findViewById(R.id.CategoryValue);
		textTotalQuestions = (TextView) findViewById(R.id.textTotalQuestions);
		textTotalQuestionsValue = (TextView) findViewById(R.id.TotalQuestionsValue);
		textQuizType = (TextView) findViewById(R.id.textQuizType);
		textQuizTypeValue = (TextView) findViewById(R.id.QuizTypeValue);
		textTimeLimit = (TextView) findViewById(R.id.textTimeLimit);
		textTimeLimitValue = (TextView) findViewById(R.id.TimeLimitValue);
		
		Results = (TextView) findViewById(R.id.textResults);
		ResultCorrect = (TextView) findViewById(R.id.resultCorrect);
		ResultTime = (TextView) findViewById(R.id.resultTime);
		CorrectValue = (TextView) findViewById(R.id.CorrectValue);
		TimeValue = (TextView) findViewById(R.id.TimeValue);
		GoBack = (Button) findViewById(R.id.GoBack);
		TryAgain = (Button) findViewById(R.id.TryAgain);
		
		multAnswer = "Meh";
		generator = new Random();
		usedNumbers = new Integer[4];
		runningQuiz = false;
		
		if(Quiz_ShowTime)
			Time.setVisibility(View.VISIBLE);
		else
			Time.setVisibility(View.GONE);
		
		if(Quiz_ShowPercent)
			Correct.setVisibility(View.VISIBLE);
		else
			Correct.setVisibility(View.GONE);
		
		if(Quiz_ShowProgress)
			Progress.setVisibility(View.VISIBLE);
		else
			Progress.setVisibility(View.GONE);
		
		if(quizIntent.hasExtra("Quiz Content"))
		{
			quizConfig = getIntent().getExtras();
			numCategories = quizConfig.getInt("Quiz Content Number");
			quizCategories = new String[numCategories];
			quizCategories = quizConfig.getStringArray("Quiz Content");
			String Categories = "";

			for(int h = 0; h < numCategories; h++)
			{
				if(h == 0)
					Categories = quizCategories[h];
				else
					Categories += ", " + quizCategories[h];
			}
			
			textCategoryValue.setText(Categories);
			if(numCategories > 1)
				textCategory.setText("Categories: ");
			
			quizTypes = new String[2]; // Change later
			quizTypes[0] = quizConfig.getString("Quiz Type");
		}
		else
			MySingleton.getInstance().addError("Taking Quiz Without Content...");
		
		Questions = getQuestions();
		timeLimit = totalQuestions;
		perCorrect = 0.0;
		numCorrect = 0;
		numProgress = 0;
		maxProgress = 0;
		UserAnswers = new Question[totalQuestions];
		textTotalQuestionsValue.setText("" + totalQuestions);
		textTimeLimitValue.setText("" + timeLimit + " Minutes");
		textQuizTypeValue.setText(quizTypes[0]);
		
		for (int a = 0; a < totalQuestions; a++)
		{
			UserAnswers[a] = new Question("PCX_Answer");
			UserAnswers[a].setAnswered(false);
		}
		
		Start.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg1)
			{
				runningQuiz = true;
				
				starttime = System.currentTimeMillis();
				timer = new Timer();
				timer.schedule(new firstTask(), 0, 500);
				
				Progress.setText(numProgress + "/" + totalQuestions);
				Correct.setText("0.0%");
				Question.setText(Questions[numProgress].getDescription());
				
				textReady.setVisibility(View.GONE);
				textCategory.setVisibility(View.GONE);
				textCategoryValue.setVisibility(View.GONE);
				textTotalQuestions.setVisibility(View.GONE);
				textTotalQuestionsValue.setVisibility(View.GONE);
				textTimeLimit.setVisibility(View.GONE);
				textTimeLimitValue.setVisibility(View.GONE);
				textQuizType.setVisibility(View.GONE);
				textQuizTypeValue.setVisibility(View.GONE);
				Start.setVisibility(View.GONE);
				
				Question.setVisibility(View.VISIBLE);
				if(quizTypes[0].equals("Free Response"))
				{
					Answer.setVisibility(View.VISIBLE);
				}
				else if(quizTypes[0].equals("Multiple Choice"))
				{
					MultipleChoiceGroup.setVisibility(View.VISIBLE);
				}
				//Previous.setVisibility(View.VISIBLE);
				Next.setVisibility(View.VISIBLE);
				
				setChoices();
				onInitialAnswerSelected();
			}
		});
		
		Previous.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				if (numProgress > 0)
				{
					numProgress--;
					String userAnswer = UserAnswers[numProgress].getAnswer();
					Answer.setText(userAnswer);
					
					Question.setText(Questions[numProgress].getDescription());
					Progress.setText((numProgress + 1) + "/" + totalQuestions);
				}
			}
		});
		Next.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				if (numProgress < totalQuestions)
				{
					String userAnswer = Answer.getText().toString();
					if(quizTypes[0].equals("Free Response"))
						{
						if (UserAnswers[numProgress].isAnswered()) // Answered Already; Get Old Values
						{
							Answer.setText("");
							if (userAnswer.equalsIgnoreCase(Questions[numProgress].getAnswer()))
							{
								if(!(UserAnswers[numProgress].isCorrect()))
									numCorrect++;
								UserAnswers[numProgress].setCorrect(true);
								if(Quiz_NotifyCorrect)
									Toast.makeText(TakeQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
							}
							else
							{
								if(UserAnswers[numProgress].isCorrect())
									numCorrect--;
								UserAnswers[numProgress].setCorrect(false);
								if(Quiz_NotifyCorrect)
									Toast.makeText(TakeQuiz.this, "Wrong!", Toast.LENGTH_SHORT).show();
							}
							
							UserAnswers[numProgress].setAnswer(userAnswer);
							numProgress++;
							if(numProgress > maxProgress)
								maxProgress = numProgress;
							
							double dNumCorrect = 0.0 + numCorrect;
							double dNumProgress = 0.0 + maxProgress;
							perCorrect = (dNumCorrect / dNumProgress) * 100.0;
							Progress.setText((numProgress + 1) + "/" + totalQuestions);
							DecimalFormat oneDForm = new DecimalFormat("#.#");
							perCorrect = Double.valueOf(oneDForm.format(perCorrect));
							Correct.setText(perCorrect + "%");
							if (numProgress < totalQuestions)
							{
								Question.setText(Questions[numProgress].getDescription());
								if(UserAnswers[numProgress].isAnswered())
									Answer.setText(UserAnswers[numProgress].getAnswer());
							}
						}
						else // Not yet answered; compare values
						{
							Answer.setText("");
							if (userAnswer.equalsIgnoreCase(Questions[numProgress].getAnswer()))
							{
								numCorrect++;
								UserAnswers[numProgress].setCorrect(true);
								if(Quiz_NotifyCorrect)
									Toast.makeText(TakeQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
							}
							else
							{
								UserAnswers[numProgress].setCorrect(false);
								if(Quiz_NotifyCorrect)
									Toast.makeText(TakeQuiz.this, "Wrong!", Toast.LENGTH_SHORT).show();
							}
							
							UserAnswers[numProgress].setAnswer(userAnswer);
							UserAnswers[numProgress].setAnswered(true);
							numProgress++;
							if(numProgress > maxProgress)
								maxProgress = numProgress;
							
							double dNumCorrect = 0.0 + numCorrect;
							double dNumProgress = 0.0 + maxProgress;
							perCorrect = (dNumCorrect / dNumProgress) * 100.0;
							Progress.setText((numProgress + 1) + "/" + totalQuestions);
							DecimalFormat oneDForm = new DecimalFormat("#.#");
							perCorrect = Double.valueOf(oneDForm.format(perCorrect));
							Correct.setText(perCorrect + "%");
							if (numProgress < totalQuestions)
							{
								Question.setText(Questions[numProgress].getDescription());
								if(UserAnswers[numProgress].isAnswered())
									Answer.setText(UserAnswers[numProgress].getAnswer());
							}
							else
								setResults();
						}
					}
					else if(quizTypes[0].equals("Multiple Choice")) // IN PROGRESS
					{
						if (multAnswer.equalsIgnoreCase(decodeAnswer(Questions[numProgress].getAnswer())))
						{
							numCorrect++;
							UserAnswers[numProgress].setCorrect(true);
							if(Quiz_NotifyCorrect)
								Toast.makeText(TakeQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
						}
						else
						{
							UserAnswers[numProgress].setCorrect(false);
							if(Quiz_NotifyCorrect)
								Toast.makeText(TakeQuiz.this, "Wrong!", Toast.LENGTH_SHORT).show();
						}

						UserAnswers[numProgress].setAnswer(multAnswer);
						UserAnswers[numProgress].setAnswered(true);
						numProgress++;
						if(numProgress > maxProgress)
							maxProgress = numProgress;

						double dNumCorrect = 0.0 + numCorrect;
						double dNumProgress = 0.0 + maxProgress;
						perCorrect = (dNumCorrect / dNumProgress) * 100.0;
						Progress.setText((numProgress + 1) + "/" + totalQuestions);
						DecimalFormat oneDForm = new DecimalFormat("#.#");
						perCorrect = Double.valueOf(oneDForm.format(perCorrect));
						Correct.setText(perCorrect + "%");
						if (numProgress < totalQuestions)
						{
							Question.setText(Questions[numProgress].getDescription());
							if(UserAnswers[numProgress].isAnswered())
								Answer.setText(UserAnswers[numProgress].getAnswer());
							
							setChoices();
							onInitialAnswerSelected();
						}
						else
							setResults();
					}
				}
				else
					setResults();
			}
		});
		
		GoBack.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Intent intent = new Intent(TakeQuiz.this, Quiz.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		TryAgain.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				Intent intent = new Intent(TakeQuiz.this, Quiz.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		confirmDialogYes.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				confirmDialog.dismiss();
				timer.cancel();
				timer.purge();
				Intent intent = new Intent(TakeQuiz.this, Quiz.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		confirmDialogNo.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg1)
			{
				confirmDialog.dismiss();
			}
		});
	}
	
	public void onRestart()
	{
		super.onRestart();
		
		Exit = MySingleton.getInstance().getExit();
		if (Exit)
			Exit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		
		if(PRO)
			inflater.inflate(R.menu.quiz_menu, menu);
		else
			inflater.inflate(R.menu.empty_menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				if(runningQuiz)
					confirmDialog.show();
				else
				{
					timer.cancel();
					timer.purge();
					Intent intent = new Intent(TakeQuiz.this, Quiz.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
				return true;
			case R.id.Debug_Label:
				Intent i = new Intent(TakeQuiz.this, Debug.class);
				startActivity(i);
				return true;
			default:
				
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(runningQuiz)
			confirmDialog.show();
		
        return super.onKeyDown(keyCode, event);
    }
	
	public Question[] getQuestions()
	{
		Question[] allQuestions = Misc.getQuestions();
		Question[] quizQuestions;
		
		// Count which ones apply first
		for(int u = 0; u < allQuestions.length; u++)
		{
			for(int r = 0; r < numCategories; r++)
			{
				if(quizCategories[r].equals(allQuestions[u].getType()))
					totalQuestions++;
			}
		}
		
		// Initialize depending on the ones that apply
		quizQuestions = new Question[totalQuestions];
		
		// Add values to all! Rawr!
		int x = 0;
		while(x < totalQuestions)
		{
			for(int a = 0; a < allQuestions.length; a++)
			{
				for(int r = 0; r < numCategories; r++)
				{
					if(quizCategories[r].equals(allQuestions[a].getType()))
					{
						quizQuestions[x] = new Question("pcx_category", "pcx_name", "pcx_description", "pcx_image", "pcx_answer");
						quizQuestions[x].setAnswer(allQuestions[a].getAnswer());
						quizQuestions[x].setName(allQuestions[a].getName());
						quizQuestions[x].setDescription(allQuestions[a].getDescription());
						quizQuestions[x].setImage(allQuestions[a].getImage());
						quizQuestions[x].setType(allQuestions[a].getType());
						x++;
					}
				}
			}
		}
		
		return quizQuestions;
	}
	
	public String decodeAnswer(String encoded)
	{
		String decoded = "";
		Choices = encoded.split("PCX");
		decoded = Choices[0];
		
		return decoded;
	}
	
	/*public String realAnswer(String encoded)
	{
		String
	}*/
	
	public void setResults()
	{
		runningQuiz = false;
		timer.cancel();
		timer.purge();
		
		String timeSTR = Time.getText().toString();
		TimeValue.setText(timeSTR);
		
		double dNumCorrect = 0.0 + numCorrect;
		double dTotalQuestions = 0.0 + totalQuestions;
		perCorrect = (dNumCorrect / dTotalQuestions) * 100;
		DecimalFormat oneDForm = new DecimalFormat("#.#");
		perCorrect = Double.valueOf(oneDForm.format(perCorrect));
		CorrectValue.setText(perCorrect + "%");
		
		Progress.setVisibility(View.INVISIBLE);
		Correct.setVisibility(View.INVISIBLE);
		Time.setVisibility(View.INVISIBLE);
		Question.setVisibility(View.GONE);
		Answer.setVisibility(View.GONE);
		MultipleChoiceGroup.setVisibility(View.GONE);
		Previous.setVisibility(View.GONE);
		Next.setVisibility(View.GONE);
		
		Results.setVisibility(View.VISIBLE);
		ResultCorrect.setVisibility(View.VISIBLE);
		ResultTime.setVisibility(View.VISIBLE);
		CorrectValue.setVisibility(View.VISIBLE);
		TimeValue.setVisibility(View.VISIBLE);
		GoBack.setVisibility(View.VISIBLE);
		TryAgain.setVisibility(View.VISIBLE);
	}
	
	public void setChoices()
	{
		String Encoded = Questions[numProgress].getAnswer();		
		Choices = Encoded.split("PCX");
		
		randomMultipleChoice = generator.nextInt(4);
		usedNumbers[0] = 0;
		usedNumbers[1] = 0;
		usedNumbers[2] = 0;
		usedNumbers[3] = 0;
		
		if(quizTypes[0].equals("Free Response"))
		{
			
		}
		else if(quizTypes[0].equals("Multiple Choice"))
		{
			Choice1.setText(Choices[0]);
			Choice2.setText(Choices[1]);
			Choice3.setText(Choices[2]);
			Choice4.setText(Choices[3]);
		}
		
		randomizeChoices();
	}
	
	public void randomizeChoices()
	{
		int u = 0;
		Integer[] randomArray = new Integer[4];
		Integer[] usedChoices = new Integer[4];
		
		usedChoices[0] = -1;
		usedChoices[1] = -1;
		usedChoices[2] = -1;
		usedChoices[3] = -1;
		
		randomArray[0] = -1;
		randomArray[1] = -1;
		randomArray[2] = -1;
		randomArray[3] = -1;
		
		while(u < 4)
		{
			randomMultipleChoice = generator.nextInt(4);
			if(hasInteger(randomArray, randomMultipleChoice))
			{
				// Do Nothing
			}
			else
			{
				randomArray[u] = randomMultipleChoice;
				usedChoices[u] = randomMultipleChoice;
				u++;
			}
		}
		
		Choice1.setText(Choices[randomArray[0]]);
		Choice2.setText(Choices[randomArray[1]]);
		Choice3.setText(Choices[randomArray[2]]);
		Choice4.setText(Choices[randomArray[3]]);
	}
	
	public boolean hasInteger(Integer[] array, int number)
	{
		for(int x = 0; x < 4; x++)
		{
			if(array[x] == number)
				return true;
		}
		return false;
	}
	
	public void randomizeChoices2()
	{
		randomMultipleChoice = generator.nextInt(4);
		
	}
	
	public void onAnswerSelected(View v)
	{
		if (Choice1.isChecked())
		{
			multAnswer = Choice1.getText().toString();
		}
		else if (Choice2.isChecked())
		{
			multAnswer = Choice2.getText().toString();
		}
		else if(Choice3.isChecked())
		{
			multAnswer = Choice3.getText().toString();
		}
		else if(Choice4.isChecked())
		{
			multAnswer = Choice4.getText().toString();
		}
		else
		{
			MySingleton.getInstance().addError("TakeQuiz: Unable to get value from proper selection");
		}
	}
	
	public void onInitialAnswerSelected()
	{
		if (Choice1.isChecked())
		{
			multAnswer = Choice1.getText().toString();
		}
		else if (Choice2.isChecked())
		{
			multAnswer = Choice2.getText().toString();
		}
		else if(Choice3.isChecked())
		{
			multAnswer = Choice3.getText().toString();
		}
		else if(Choice4.isChecked())
		{
			multAnswer = Choice4.getText().toString();
		}
		else
		{
			MySingleton.getInstance().addError("TakeQuiz: Unable to get initial value from proper selection");
		}
	}
	
	public void Exit()
	{
		Exit = true;
		MySingleton.getInstance().setExit(Exit);
		finish();
	}
}
