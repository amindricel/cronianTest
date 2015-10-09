package test.cronian.ro.croniantest.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Alex on 9/28/2015.
 */
public class Animations {

    public static void customTranslateAnimationOnY(final View v, final float fromY, final float toY, final int duration, final int visibility) {
        ObjectAnimator custom_translate = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, fromY, toY);
        custom_translate.setDuration(duration);
        custom_translate.start();
        v.setVisibility(View.VISIBLE);
        custom_translate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setEnabled(false);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setEnabled(true);
                v.setVisibility(visibility);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
