package vlth.myproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vlth.myproject.Util.PreferenceUtil;
import vlth.myproject.Util.Var;

public class EndDialog extends Dialog {

//    private Context mContext;

    private RelativeLayout root;
    private int current_score = 0;
    private int best_score = 0;
    private TextView mTvYourMove, mTvYourBest;

    private Button bt1, bt2;

    public EndDialog(Context context, final Handler handler) {
        super(context);
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.end_dialog);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);

        mTvYourMove = (TextView) this.findViewById(R.id.yourMove);
        mTvYourBest = (TextView) this.findViewById(R.id.yourBest);

        root = (RelativeLayout) findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EndDialog.this.dismiss();
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
                handler.sendEmptyMessage(0);
            }
        });

        if (context instanceof MixWord) {
            current_score = PreferenceUtil.getValue(context, Var.KEY_SCORE_MIX_WORD, 0);
            best_score = PreferenceUtil.getValue(context, Var.KEY_BEST_SCORE_MIX_WORD, 0);
            if (current_score > best_score) {
                PreferenceUtil.setValue(context, Var.KEY_BEST_SCORE_MIX_WORD, current_score);
            }
        }
        if (context instanceof HigherOrLower) {
            current_score = PreferenceUtil.getValue(context, Var.KEY_SCORE_MIX_WORD, 0);
            best_score = PreferenceUtil.getValue(context, Var.KEY_BEST_SCORE_MIX_WORD, 0);
            if (current_score> best_score) {
                PreferenceUtil.setValue(context, Var.KEY_BEST_SCORE_HIGHER_OR_LOWER, current_score);
            }
        }

        mTvYourMove.setText("YOUR MOVE: " + current_score);
        mTvYourBest.setText("BEST MOVE: " + best_score);
    }


}
