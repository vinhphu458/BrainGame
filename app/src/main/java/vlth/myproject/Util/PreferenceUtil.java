package vlth.myproject.Util;

import android.content.Context;
import android.content.SharedPreferences;

import vlth.myproject.HigherOrLower;
import vlth.myproject.MixWord;

public class PreferenceUtil {

	private static SharedPreferences pref;
	private static SharedPreferences.Editor edit;

	public static void setValue(Context ctx, String key, int val){

		if(ctx instanceof MixWord){
			pref = ctx.getSharedPreferences("MW", 0);
		}
		if(ctx instanceof HigherOrLower){
			pref = ctx.getSharedPreferences("HL", 0);
		}
		edit = pref.edit();
		edit.putInt(key, val);
		edit.commit();
	}
	
	public static int getValue(Context ctx, String key, int delVal){
		if(ctx instanceof MixWord){
			pref = ctx.getSharedPreferences("MW", 0);
		}
		if(ctx instanceof HigherOrLower){
			pref = ctx.getSharedPreferences("HL", 1);
		}
		return pref.getInt(key, delVal);
	}


}
