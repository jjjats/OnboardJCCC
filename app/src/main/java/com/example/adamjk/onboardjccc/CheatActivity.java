package com.example.adamjk.onboardjccc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

   public static final String EXTRA_ANSWER_IS_TRUE = "package com.example.adamjk.practicejccc.answer_is_true";
   public static final String EXTRA_ANSWER_SHOWN = "package com.example.adamjk.practicejccc.answer_shown";
   public static final String KEY_CHEATER = "cheater";

   private boolean mAnswerIsTrue;
   private TextView mAnswerTextView;
   private Button mShowAnswer;
   private Boolean mIsCheater;

   private void setAnswerShownResult(boolean isAnswerShown) {
      Intent data = new Intent();
      data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
      setResult(RESULT_OK, data);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_cheat);

      if (savedInstanceState != null) {// if there was a savedInstanceState issuance
         setAnswerShownResult(savedInstanceState.getBoolean(KEY_CHEATER, false));// was the answer displayed before savedInstanceState if so forward that on
         mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER);// did the user cheat before savedInstanceState
         mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE, false);// was the answer to the ? cheated on true
         mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
         if ((mAnswerIsTrue == true) && (mIsCheater == true)) {// if the answer was true, and it was viewed(cheated)
            mAnswerTextView.setText(R.string.true_button);// then display it again
         } else if ((mAnswerIsTrue == false) && (mIsCheater == true)) {//if the answer was false, and it was viewed(cheated)
            mAnswerTextView.setText(R.string.false_button);// display it again
         }

        } else {// no savedInstanceState
           mIsCheater = false;// they haven't cheated yet
           setAnswerShownResult(false);// they haven't seen the answer yet
        }


      mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
      mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
      mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
      mShowAnswer.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {

            if (mAnswerIsTrue) {
               mAnswerTextView.setText(R.string.true_button);
            } else {
               mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(true);// the answer was seen
            mIsCheater = true;// the user cheated
         }
      });

   }

    @Override
       public void onSaveInstanceState(Bundle savedInstanceState) {
          super.onSaveInstanceState(savedInstanceState);
          savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);// save the cheat status of the user onSaveInstanceState issuance
          savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue);// get whether ? was True/false onSaveInstanceState issuance
       }

}