package com.manet.deshatan.mainActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.manet.deshatan.R;
import com.manet.deshatan.constants;

import java.util.ArrayList;
import java.util.List;

public class OnboardingScreen extends AppCompatActivity {
    private ViewPager screenPager;
    OnboardingScreenAdapter onboardingScreenAdapter;
    TabLayout tabIndicator;
    Button btnNext, btnGetStarted,btnPlayNow;
    LinearLayout linearLayoutNext, linearLayoutGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        final WindowInsetsController insetsController = getWindow().getInsetsController();
        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars());
        }

        if (restorePreData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_onboarding_screen);

//        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        btnPlayNow=findViewById(R.id.playNow);
        linearLayoutNext = findViewById(R.id.linear_layout_next);
        linearLayoutGetStarted = findViewById(R.id.linear_layout_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);

        //Data
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Invite", "Create a room, get your friends onboarded", R.drawable.onboarding1));
        mList.add(new ScreenItem("Roll", "Play the game of monopoly to buy,visit\nand rent places across India", R.drawable.onboarding2));
        mList.add(new ScreenItem("Explore", "Once bought, take the virtual train and explore\nthe place in 360 degree AR experience", R.drawable.onboarding3));

        //Setup viewPager
        screenPager = findViewById(R.id.screen_viewpager);
        onboardingScreenAdapter = new OnboardingScreenAdapter(this,mList);
        screenPager.setAdapter(onboardingScreenAdapter);

        //Setup tab indicator
        tabIndicator.setupWithViewPager(screenPager);

        //Button Next
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                screenPager.setCurrentItem(screenPager.getCurrentItem() + 1, true);
//            }
//        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.game_instrcutions_dialog_box, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(OnboardingScreen.this,R.style.myDialog);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                dialogView.findViewById(R.id.playNow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);
                        savePrefsData();
                        finish();

                    }
                });


            }
        });





    }

        private boolean restorePreData(){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
            Boolean isIntroActivityOpenedBefore = preferences.getBoolean("isIntroOpened", false);
            return isIntroActivityOpenedBefore;
        }

        private void savePrefsData(){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isIntroOpened", true);
            editor.apply();
        }

        private void loadLastScreen(){
            linearLayoutNext.setVisibility(View.INVISIBLE);
            linearLayoutGetStarted.setVisibility(View.VISIBLE);
        }

}