package mahdiyar.ir.marketpanel.ui.component;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Mahdiyar on 12/5/2016.
 */

public class ChromaKey {
    int[] pix;
    Bitmap bm;
    int picw, pich;
    int index, cur_pix, red2, green2, blue2;

    public Bitmap replaceIntervalColor(Bitmap bitmap, int red, int green, int blue, int dist) {
        if (bitmap != null) {
            picw = bitmap.getWidth();
            pich = bitmap.getHeight();
            if (pix == null) {
                pix = new int[picw * pich];
            }
            bitmap.getPixels(pix, 0, picw, 0, 0, picw, pich);

            double distance;

            for (int y = 0; y < pich; y++) {
                for (int x = 0; x < picw; x++) {
                    index = y * picw + x;
                    cur_pix = pix[index];
                    red2 = (int) ((cur_pix & 0x00FF0000) >>> 16); // Color.red(cur_pix);
                    green2 = (int) ((cur_pix & 0x0000FF00) >>> 8); //Color.green(cur_pix);
                    blue2 = (int) (cur_pix & 0x000000FF); //Color.blue(cur_pix);
                    // faster Math.sqrt
                    // Source: http://stackoverflow.com/a/13264441/956397
                    /* distance = Math.sqrt(
                            (red2 - red) * (red2 - red)
                                    + (green2 - green) * (green2 - green)
                                    + (blue2 - blue) * (blue2 - blue)
                    ); */
                    distance = Double.longBitsToDouble(((Double.doubleToRawLongBits((red2 - red) * (red2 - red)
                            + (green2 - green) * (green2 - green)
                            + (blue2 - blue) * (blue2 - blue)) >> 32) + 1072632448) << 31);

                    if (distance < dist) {
                        pix[index] = Color.WHITE;
                    }
                }
            }

            if (bm == null) {
                bm = Bitmap.createBitmap(picw, pich, Bitmap.Config.ARGB_4444);
            }
            bm.setPixels(pix, 0, picw, 0, 0, picw, pich);
            return bm;
        }
        return null;
    }
}