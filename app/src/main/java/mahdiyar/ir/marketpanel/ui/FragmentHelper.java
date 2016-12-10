package mahdiyar.ir.marketpanel.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mahdiyar on 11/3/2016.
 */

public class FragmentHelper {

    /**
     * <p> Attach new Fragment to Activity.</p>
     *
     * @param context
     * @param fragment, fragment to add
     * @param viewID    , layout id to replace the view.
     */
    public static void attachFragment(AppCompatActivity context, Fragment fragment, int viewID) {
        FragmentTransaction ft = context.getSupportFragmentManager()
                .beginTransaction();
        ft.add(viewID, fragment).commit();
    }

    /**
     * <p>Open new fragment to existing fragment. Provides developer to add this fragment to backstack.</p>
     *
     * @param context
     * @param fragment,         fragment to add
     * @param viewID            , layout id to replace the view.
     * @param canAddBackStrace, is added to backstrace
     */

    public static void openNewFragment(AppCompatActivity context, Fragment fragment,
                                       boolean canAddBackStrace, int viewID) {
        FragmentTransaction ft = context.getSupportFragmentManager()
                .beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, 0);
        ft.replace(viewID, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (canAddBackStrace) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    /**
     * <p>
     * Open new fragment to existing fragment. Provides developer to add this
     * fragment to backstack.
     * </p>
     *
     * @param context
     * @param fragment,         fragment to add
     * @param args,             Bundle arguments to pass into next fragment
     * @param viewID            , layout id to replace the view.
     * @param canAddBackStrace, is added to backstrace
     */
    public static void openNewFragment(AppCompatActivity context,
                                       Fragment fragment, Bundle args, boolean canAddBackStrace, int viewID) {
        fragment.setArguments(args);
        openNewFragment(context, fragment, canAddBackStrace, viewID);
    }

    /**
     * <p>Close the number of fragments.</p>
     *
     * @param context
     * @param numBackStack, number of fragments to pop up.
     */
    public static void popBackStack(AppCompatActivity context, int numBackStack) {
        FragmentManager manager = context.getSupportFragmentManager();
        int fragCount = manager.getBackStackEntryCount();
        for (int i = 0; i < fragCount - numBackStack; i++) {
            manager.popBackStack();
        }
    }
}
