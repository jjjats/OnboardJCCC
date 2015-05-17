package com.example.adamjk.onboardjccc;

public class TrueFalse {

    /*
    This class supports JcccQuizActivity

    An object of TrueFalse type takes an int variable and a boolean variable as its parameters
     */

	private int mQuestion;

	private boolean mTrueQuestion;

	public TrueFalse(int question, boolean trueQuestion) {
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}

    //Retrieves index of question
	public int getQuestion() {
		return mQuestion;
	}


    /*Sets question's value (never used in current application because JcccQuizActivity decides to set a TextView
    with the value directly
    */
	public void setQuestion(int question) {
		mQuestion = question;
	}

    //Checks whether a question is True or not
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

    /*
    This method sets the mTrueQuestion value (is not used in current application as JcccQuizActivity
    implements the value directly from the mCheatBank array
     */
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

}