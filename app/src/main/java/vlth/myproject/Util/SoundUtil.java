package vlth.myproject.Util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import vlth.myproject.R;


public class SoundUtil {
	
 
	public static int WIN = R.raw.win;
	public static int DIE = R.raw.lose;

	public static void play(Context context, int sound) {
		final MediaPlayer mediaPlayer = MediaPlayer.create(context, sound);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mediaPlayer.release();
			}
		});
	}

}
