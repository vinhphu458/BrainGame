package vlth.myproject.Util;

/**
 * Created by 12130 on 10/21/2015.
 */

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.Handler;

public class FmTimer {
    private int MAX_TIME=1500;

    private ProgressBar progressBar;
    private Handler handler;

    public CountDownTimer timer;

    public FmTimer() {

    }

    public void setProgressBar(ProgressBar progressBar){
        this.progressBar=progressBar;
    }

    public void tick(final Handler handler) {

        progressBar.setMax(MAX_TIME);
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(MAX_TIME, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                progressBar.setProgress((int) millisUntilFinished);
            }
            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
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
