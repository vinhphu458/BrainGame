package vlth.myproject.Util;

/**
 * Created by 12130 on 10/21/2015.
 */

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CgTimer {
    private int MAX_TIME=2000;

    private ProgressBar progressBar;

    public CountDownTimer timer;

    public CgTimer() {

    }

    public void setProgressBar(ProgressBar progressBar){
        this.progressBar=progressBar;
    }

    public void tick(final TextView noti, final TextView point, final ImageView btnplay, final ImageView im1, final ImageView im2) {

        progressBar.setMax(MAX_TIME);
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(MAX_TIME, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                progressBar.setProgress((int) millisUntilFinished);
            }
            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                noti.setText("You Lose");
                point.setText("");
                btnplay.setClickable(true);
                im1.setClickable(false);
                im2.setClickable(false);

            }
        };
        timer.start();
    }

    public void stop(){
        timer.cancel();
        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
    }
}
