package com.example.imagegallery;


import android.animation.Animator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RelativeLayout layoutMain;
    private RelativeLayout layoutLogin;
    private RelativeLayout layoutSplash;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        layoutMain = findViewById(R.id.layoutMain);
        layoutLogin = findViewById(R.id.layoutLogin);
        layoutSplash = findViewById(R.id.layoutSplash);

        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        layoutSplash.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viewMenu();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation1.setDuration(2000);

    }

    private void viewMenu() {

        if (!isOpen) {

            int x = layoutLogin.getRight();
            int y = layoutLogin.getBottom();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutLogin, x, y, startRadius, endRadius);
            anim.setDuration(3000);
            layoutLogin.setVisibility(View.VISIBLE);
            anim.start();

            isOpen = true;

        } else {

            int x = layoutLogin.getRight();
            int y = layoutLogin.getBottom();

            int startRadius = Math.max(layoutSplash.getWidth(), layoutSplash.getHeight());
            int endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutLogin, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutLogin.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();

            isOpen = false;
        }
    }
}