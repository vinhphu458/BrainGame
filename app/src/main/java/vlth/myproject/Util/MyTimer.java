package vlth.myproject.Util;

/**
 * Created by Administrator on 10/16/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import static android.graphics.Color.BLUE;

public class MyTimer extends Activity{

    public int miliSecond;
    private Handler handler;
    private ProgressBar progressBar;

    public CountDownTimer timer;

    public void setOnTickHtmlListener(Handler handler) {
        this.handler = handler;
    }
    public void setProgressBar(ProgressBar progressBar){
        this.progressBar=progressBar;
    }

    public MyTimer(int ms){
        this.miliSecond=ms;
    }

    public void tick() {

        progressBar.setMax(miliSecond);
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(miliSecond, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                progressBar.setProgress((int) millisUntilFinished);

            }
            @Override
            public void onFinish() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.start();
    }

    public void stop(){
        timer.cancel();
        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
    }

}
