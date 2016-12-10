package mahdiyar.ir.loadershow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Mahdiyar on 12/2/2016.
 */

public class LoaderShow {


    public static void show(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_gear, null);
        ViewGroup parent = ((ViewGroup) viewGroup.getParent());

        View gear = view.findViewById(R.id.loader_show_gear_iv);
        if (viewGroup instanceof LinearLayout) {
            FrameLayout frameLayout = new FrameLayout(context);
            parent.removeView(viewGroup);
            frameLayout.addView(view, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            frameLayout.addView(viewGroup, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            parent.addView(frameLayout, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            viewGroup.addView(view, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        startAnimation(gear, context);
    }

    private static void startAnimation(View gear, Context context) {
        Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        gear.startAnimation(rotate);
    }

    public static void hide(ViewGroup viewGroup) {
        if (viewGroup instanceof LinearLayout) {
            viewGroup = (ViewGroup) viewGroup.getParent();
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view.findViewById(R.id.loader_show_gear_iv) != null) {
                viewGroup.removeView(view);
                break;
            }
        }
    }
}
