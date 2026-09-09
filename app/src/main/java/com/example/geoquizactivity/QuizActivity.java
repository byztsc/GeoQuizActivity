package com.example.geoquizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";

    private static final String TAG = "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;

    private int[] mQuestionBank = {
            R.string.question_text,
            R.string.question_ocean,
            R.string.question_africa,
            R.string.question_mideast
    };

    private boolean[] mAnswers = {true, true, false, true};

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex];
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate(Bundle) called");

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        

        mQuestionTextView = findViewById(R.id.question_text_view);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.previous_button);

        updateQuestion();

        mTrueButton.setOnClickListener(v -> checkAnswer(true));

        mFalseButton.setOnClickListener(v -> checkAnswer(false));

        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        mPrevButton.setOnClickListener(v -> {

            if(mCurrentIndex == 0){
                mCurrentIndex = mQuestionBank.length - 1;
            } else{
                mCurrentIndex--;
            }

            updateQuestion();
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void checkAnswer(boolean userAnswer){

        boolean correctAnswer = mAnswers[mCurrentIndex];

        int messageResId;

        if(userAnswer == correctAnswer){
            messageResId = R.string.correct;
        } else{
            messageResId = R.string.incorrect;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
