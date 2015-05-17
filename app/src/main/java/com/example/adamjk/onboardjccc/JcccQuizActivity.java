package com.example.adamjk.onboardjccc;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JcccQuizActivity extends ActionBarActivity {

    /*Documentation: User
    This Activity provides a way for the prospective student to refresh and affirm the knowledge
    gained from the JCCC OnBoard App by interacting with a simple quiz.

    Corresponding layouts and menus are as follows:
    activity_quiz.xml,
    activity_cheat.xml,
    menu_quiz.xml,
    quiz.xml

     */

   private static final String TAG = "JcccQuizActivity"; //Used to track the Activity lifecycle
   private static final String KEY_INDEX = "index"; //Used to track the particular index of the savedInstanceState
   private static final String KEY_CHEATER = "cheater"; //Used to check whether or not a question has been cheated on
   private static final String KEY_CHEAT_ARRAY = "cheatarray"; //Denotes the array that holds the boolean values of each question's cheat state

    /*
    Buttons of the JcccQuizActivity interface. Each button corresponds with the buttons in the activity_quiz.xml layout.
     */
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private Button mPrevButton;

    private TextView mQuestionTextView; // TextView for displaying the question text. Corresponds with the question_text_view in activity_quiz.xml layout

    /*
    TrueFalse mQuestionBank is an array object of type TrueFalse. TrueFalse object is defined in TrueFalse.java

    mQuestionBank stores a reference to the string resource id of each question as well as the boolean value of trueQuestion (defined in TrueFalse.java)
     */
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
          new TrueFalse(R.string.question_1, false),
          new TrueFalse(R.string.question_2, false),
          new TrueFalse(R.string.question_3, false),
          new TrueFalse(R.string.question_4, false),
          new TrueFalse(R.string.question_5, true),
          new TrueFalse(R.string.question_6, true),
          new TrueFalse(R.string.question_7, true),
          new TrueFalse(R.string.question_8, true),
          new TrueFalse(R.string.question_9, true),
          new TrueFalse(R.string.question_10, true),
    };

    // mCheatBank is an int array to hold the cheating status of each question. 1 = didn't cheat, 2 = cheated
    private int[] mCheatBank = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    /*
    The updateQuestion() method retrieves the question from the current index within the mQuestionBank array
    and sets the text of the mQuestionTextView to the value of the string resource of the retrieved object
     */
    private void updateQuestion() {
       int question = mQuestionBank[mCurrentIndex].getQuestion();
       mQuestionTextView.setText(question);
    }

    /*
    The checkAnswer() method does the following:
    Initiates when user presses either mTrueButton or mFalseButton
    Checks to see if user has cheated on particular question
    If they have, user is presented with a message acknowledging that they have cheated ("YOU CHEAT!")
    If they haven't, user is presented with a message based on whether or not they answered correctly ("Correct!", "Incorrect!")
     */
    private void checkAnswer(boolean userPressedTrue) {
       boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
       int messageResId = 0;

      if (userPressedTrue == answerIsTrue) {
         if (mCheatBank[mCurrentIndex] == 2) {
            messageResId = R.string.cheater_toast;
         } else {
            messageResId = R.string.correct_toast;
         }
      } else {
         if (mCheatBank[mCurrentIndex] == 2) {
            messageResId = R.string.cheater_toast;
         } else {
            messageResId = R.string.incorrect_toast;
         }
      }
       Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    /*
    This is the onCreate method - the start of the JcccQuizActivity lifecycle

    It inherits the savedInstanceState of its parent Activity (MainActivity.java)
    Then sets the user interface View to the value of activity_quiz.xml

    Then each component of the View is wired to the activity via 'findViewById'
    Each component is given an onClickListener and then its behavior is defined in its own onClick() method



     */
   @TargetApi(11)
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called for JcccQuizActivity");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);
            }
        });
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
         }
      });
        updateQuestion();

        mPrevButton = (Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
            updateQuestion();
         }
      });
        updateQuestion();

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
         }
      });
        if (savedInstanceState != null) {
           mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
           mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
           mCheatBank = savedInstanceState.getIntArray(KEY_CHEAT_ARRAY);
        }
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            // start cheat activity
            Intent i = new Intent(JcccQuizActivity.this, CheatActivity.class);
            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
            i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
            startActivityForResult(i, 0);

         }
      });
        updateQuestion();

    }

    /*
    The onActivityResult() method checks whether or not CheatActivity has been initialized (by the onClick() method
    of mCheatButton's OnClickListener).
    If it hasn't been initialized it does nothing.
    If it HAS been initialized it gets the boolean value of the cheat status for the question from CheatActivity and
    sets the corresponding question's (in JcccQuizActivity) cheat status to that value

     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (data == null) {
          return;
       }

       mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
       if (mIsCheater) {
          mCheatBank[mCurrentIndex] = 2;
       }
    }

    /*
    Part of JcccQuizActivity's lifecycle.

    Saves current instance information of the running activity.
    Saves the index of the current question, the cheat status of the current question, and the cheat status of each question in the array
     */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       Log.i(TAG, "onSaveInstanceState");
       savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
       savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
       savedInstanceState.putIntArray(KEY_CHEAT_ARRAY, mCheatBank);
    }

    /*The next 5 methods are part of the Android Activity lifecycle
    for basic information about the Android Activity lifecycle visit: http://developer.android.com/reference/android/app/Activity.html#ActivityLifecycle
    */

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called for JcccQuizActivity");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called for JcccQuizActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called for JcccQuizActivity");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called for JcccQuizActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called for JcccQuizActivity");
    }


    //Sets App's Quiz menu item with the settings defined in menu_quiz.xml

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    //Sets App's Quiz menu item settings for when it is selected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}