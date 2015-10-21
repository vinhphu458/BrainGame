package vlth.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import vlth.myproject.Util.MyTimer;
import vlth.myproject.Util.PreferenceUtil;
import vlth.myproject.Util.SoundUtil;
import vlth.myproject.Util.Var;

public class HigherOrLower extends AppCompatActivity {

    private Button btH, btL;
    private TextView num, count, score;
    private int firstNum, lastNum, myScore = 0;
    private ProgressBar progressBar;
    private MyTimer myTimer;
    private boolean finish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        score = (TextView) findViewById(R.id.point);
        count = (TextView) findViewById(R.id.count);
        btH = (Button) findViewById(R.id.btHigher);
        btL = (Button) findViewById(R.id.btLower);
        num = (TextView) findViewById(R.id.number);
        progressBar = (ProgressBar) findViewById(R.id.proTimer);

        myTimer = new MyTimer(2000);
        myTimer.setProgressBar(progressBar);

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setRandomNumber();
                firstNum = Integer.parseInt(num.getText().toString());
            }

            @Override
            public void onFinish() {
                setRandomNumber();
                lastNum = Integer.parseInt(num.getText().toString());

            }
        }.start();

        myTimer.setOnTickHtmlListener(gameLose);
    }

    private void setRandomNumber() {
        Random r = new Random();
        int i = r.nextInt(100);
        num.setText("" + i);
    }

    public void choose(View view) {

        switch (view.getId()) {
            case R.id.btHigher:

                if (lastNum > firstNum) {
                    setRandomNumber();
                    firstNum = lastNum;
                    lastNum = Integer.parseInt(num.getText().toString());
                    myScore++;
                    score.setText("Your score: " + myScore);
                    SoundUtil.play(this, SoundUtil.WIN);

                    myTimer.tick();
                } else {
                    score.setText("Your score: " + myScore);
                    gameLose.sendEmptyMessage(0);
                }
                break;
            case R.id.btLower:

                if (lastNum < firstNum) {
                    setRandomNumber();
                    firstNum = lastNum;
                    lastNum = Integer.parseInt(num.getText().toString());
                    myScore++;
                    score.setText("Your score: " + myScore);
                    SoundUtil.play(this, SoundUtil.WIN);
                    myTimer.tick();
                } else {
                    score.setText("Your score: " + myScore);
                    gameLose.sendEmptyMessage(0);
                }
                break;
        }
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (finish) {
                return;
            }
            PreferenceUtil.setValue(HigherOrLower.this, Var.KEY_SCORE_HIGHER_OR_LOWER, myScore);
            myScore = 0;
            SoundUtil.play(HigherOrLower.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(HigherOrLower.this, closeDialog);
            endDialog.show();
            finish = true;
        }

    };
    private Handler closeDialog = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    };

    protected void onDestroy() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        super.onDestroy();
    }
}