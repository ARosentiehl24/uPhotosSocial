package com.unimagdalena.android.app.uphotossocial;

/*
 * Created by albert on 2/12/2015.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;

public class Constants {

    public static final int DURATIONS_OF_ANIMATIONS = 350;

    public static void showAnimationProgress(final View progressView, final View layoutView, final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            layoutView.setVisibility(show ? View.GONE : View.VISIBLE);
            layoutView.animate().setDuration(DURATIONS_OF_ANIMATIONS).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(DURATIONS_OF_ANIMATIONS).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            layoutView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
