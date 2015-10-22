package vlth.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;

import java.util.Random;

import vlth.myproject.Util.CgTimer;

public class ColorShape extends AppCompatActivity {

    private ProgressBar prog;
    private TextView noti;
    private TextView tw1, tw2;
    private TextView point;
    private ImageView im1, im2;
    private ImageView btnplay;

    public static int[] sqr = {R.drawable.square_green, R.drawable.square_yellow, R.drawable.square_red};
    public static int[] tri = {R.drawable.triangle_green, R.drawable.triangle_yellow, R.drawable.triangle_red};
    public static int[] cir = {R.drawable.circle_green, R.drawable.circle_yellow, R.drawable.circle_red};

    String choose1, choose2, result;
    int rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shape);

        prog = (ProgressBar) findViewById(R.id.prog);
        noti = (TextView) findViewById(R.id.noti);
        tw1 = (TextView) findViewById(R.id.tw1);
        tw2 = (TextView) findViewById(R.id.tw2);

        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);

        point = (TextView) findViewById(R.id.point);

        btnplay = (ImageView) findViewById(R.id.btnplay);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_shape, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void play(final CgTimer timer, final int pnt) {
        int num1, num2, num3;
        num1 = randomtype();
        num2 = random();
        num3 = random();

        timer.setProgressBar(prog);
        timer.tick(noti, point, btnplay, im1, im2);

        noti.setText("");
        btnplay.setClickable(false);

        rd = random2();
        switch (rd) {
            case 0:
                rdim(im1, im2, num1, num2, num3);
                break;
            case 1:
                rdim(im2, im1, num1, num2, num3);
                break;
        }

        if (num1 % 2 == 0) {
            tw1.setText("Color: ");
            switch (num3) {
                case 0:
                    result = "green";
                    break;
                case 1:
                    result = "yellow";
                    break;
                case 2:
                    result = "red";
                    break;
            }
        } else {
            tw1.setText("Shape: ");
            switch (num2) {
                case 0:
                    result = "square";
                    break;
                case 1:
                    result = "triangle";
                    break;
                case 2:
                    result = "circle";
                    break;
            }
        }

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rd) {
                    case 0:
                        if (choose1.contains(result)) {
                            final int i = pnt + 1;
                            point.setText(Integer.toString(i));
                            play(timer, i);
                        } else {
                            timer.stop();
                            im1.setClickable(false);
                            im2.setClickable(false);
                            btnplay.setClickable(true);
                            noti.setText("You lose");
                        }
                        break;
                    case 1:
                        if (choose2.contains(result)) {
                            final int i = pnt + 1;
                            point.setText(Integer.toString(i));
                            play(timer, i);
                        } else {
                            timer.stop();
                            im1.setClickable(false);
                            im2.setClickable(false);
                            btnplay.setClickable(true);
                            noti.setText("You lose");
                        }
                        break;
                }
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rd) {
                    case 0:
                        if (choose2.contains(result)) {
                            int i = pnt + 1;
                            point.setText(Integer.toString(i));
                            play(timer, i);
                        } else {
                            timer.stop();
                            im1.setClickable(false);
                            im2.setClickable(false);
                            btnplay.setClickable(true);
                            noti.setText("You lose");
                        }
                        break;
                    case 1:
                        if (choose1.contains(result)) {
                            int i = pnt + 1;
                            point.setText(Integer.toString(i));
                            play(timer, i);
                        } else {
                            timer.stop();
                            im1.setClickable(false);
                            im2.setClickable(false);
                            btnplay.setClickable(true);
                            noti.setText("You lose");
                        }
                        break;
                }
            }
        });

    }

    public void btnstart(View view) {
        final int pnt = 0;

        im1.setClickable(true);
        im2.setClickable(true);

        final CgTimer timer = new CgTimer();

        point.setText("");
        play(timer, pnt);
    }

    public int randomtype() {
        Random rd = new Random();
        int num = rd.nextInt(100);
        return num;
    }

    public int random() {
        Random rd = new Random();
        int num = rd.nextInt(3);
        return num;
    }

    public int random2() {
        Random rd = new Random();
        int num = rd.nextInt(2);
        return num;
    }

    public void btnback(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        finish();;
        startActivity(intent);
    }

    public void rdim (ImageView im1, ImageView im2, int num1, int num2, int num3) {
        int type = sqr[random()];
        int ck;

        switch (num2) {
            case 0:
                tw2.setText("Square");
                type = sqr[random()];

                switch (num3) {
                    case 0:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                    case 1:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                    case 2:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (sqr[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "square_green";
                                break;
                            case 1:
                                choose1 = "square_yellow";
                                break;
                            case 2:
                                choose1 = "square_red";
                                break;
                        }
                    }
                }
                break;
            case 1:
                tw2.setText("Triangle");
                type = tri[random()];

                switch (num3) {
                    case 0:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                    case 1:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                    case 2:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (tri[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "triangle_green";
                                break;
                            case 1:
                                choose1 = "triangle_yellow";
                                break;
                            case 2:
                                choose1 = "triangle_red";
                                break;
                        }
                    }
                }
                break;
            case 2:
                tw2.setText("Circle");
                type = cir[random()];

                switch (num3) {
                    case 0:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                    case 1:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                    case 2:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (cir[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "circle_green";
                                break;
                            case 1:
                                choose1 = "circle_yellow";
                                break;
                            case 2:
                                choose1 = "circle_red";
                                break;
                        }
                    }
                }
                break;
        }

        int rd = random2();
        switch (num3) {
            case 0:
                tw2.setTextColor(Color.parseColor("#0cff00"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_green";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_green";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_green";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_green";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_green";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_green";
                                break;
                        }
                        break;
                }
                break;
            case 1:
                tw2.setTextColor(Color.parseColor("#FFF000"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle__yellow";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_yellow";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_yellow";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_yellow";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_yellow";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_yellow";
                                break;
                        }
                        break;
                }
                break;
            case 2:
                tw2.setTextColor(Color.parseColor("#FF0000"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_red";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_red";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_red";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_red";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_red";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_red";
                                break;
                        }
                        break;
                }
                break;
        }
    }
}
