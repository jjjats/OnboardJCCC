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
    This Activity provides a way for the prospective student to refresh and reaffirm the knowledge
    gained from the JCCC OnBoard App by interacting with a simple quiz.

     */

   private static final String TAG = "JcccQuizActivity";
   private static final String KEY_INDEX = "index";
   private static final String KEY_CHEATER = "cheater";
   private static final String KEY_CHEAT_ARRAY = "cheatarray";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
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

    // int array to hold the cheating status of each question 1 = didn't cheat, 2 = cheated
    private int[] mCheatBank = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    private void updateQuestion() {
       int question = mQuestionBank[mCurrentIndex].getQuestion();
       mQuestionTextView.setText(question);
    }
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       Log.i(TAG, "onSaveInstanceState");
       savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
       savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
       savedInstanceState.putIntArray(KEY_CHEAT_ARRAY, mCheatBank);
    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}