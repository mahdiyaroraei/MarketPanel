package mahdiyar.ir.marketpanel.core;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Mahdiyar on 11/8/2016.
 */

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 100);
        return noOfColumns;
    }
}
