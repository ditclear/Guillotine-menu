package com.vienan.guillotine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.vienan.guillotine.interpolator.GuillotineInterpolator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.content_hamburger)
    ImageView contentHamburger;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.root)
    FrameLayout root;

    View view;
    boolean isOpening, isClosing;

    @OnClick(R.id.content_hamburger)
    public void menuClicked() {
        if (!isOpening) startOpenAnim();
    }

    private void startCloseAnim() {

        view.setPivotX(calculatePivotX(contentHamburger));
        view.setPivotY(calculatePivotY(contentHamburger));
        toolbar.setPivotX(calculatePivotX(contentHamburger));
        toolbar.setPivotY(calculatePivotY(contentHamburger));

        ObjectAnimator closeAnim = ObjectAnimator.ofFloat(view, "rotation", 0.0f, -90.0f)
                .setDuration((long) (625 * GuillotineInterpolator.ROTATION_TIME));
        ObjectAnimator closeAnim1 = ObjectAnimator.ofFloat(toolbar, "rotation", 70f, 0f)
                .setDuration(625);
        closeAnim1.setInterpolator(new GuillotineInterpolator());
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(closeAnim, closeAnim1);
        closeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isClosing = true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosing = false;
                view.setVisibility(View.GONE);
            }

        });
        animSet.start();
    }

    private void startOpenAnim() {

        ObjectAnimator openAnim = ObjectAnimator.ofFloat(view, "rotation", -90f, 0.0f)
                .setDuration(625);
        openAnim.setInterpolator(new GuillotineInterpolator());

        openAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isClosing=isOpening= true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosing =isOpening= false;
            }
        });
        openAnim.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        view = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        ImageView guillotineMenu = (ImageView) view.findViewById(R.id.guillotine_hamburger);
        guillotineMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClosing) startCloseAnim();
            }
        });

        root.addView(view);


    }

    private float calculatePivotY(View burger) {
        return burger.getTop() + burger.getHeight() / 2;
    }

    private float calculatePivotX(View burger) {
        return burger.getLeft() + burger.getWidth() / 2;
    }

}
