package com.manet.deshatan.mapActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.DefaultCompany.Dice.UnityPlayerActivity;
import com.unity3d.player.UnityPlayer;

public class MapActivity extends UnityPlayerActivity {

    private static final String TAG_UNITY = "Unity";
    //Unity callback object
    private String _strCallbackGameObjectName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button myButton = new Button(this);
        myButton.setText("Touch Me");
        myButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        mUnityPlayer.addView(myButton);
    }

    //Initialize the callback object
    public void Init(String callbackGoName) {
        this._strCallbackGameObjectName = callbackGoName;
        //Log.i(TAG_UNITY, "Init " + callbackGoName);
        Toast.makeText(this, "Init" + callbackGoName, Toast.LENGTH_LONG).show();
    }

    //unity calls Android
    public void UnityCallAndroid() {
        //Log.i(TAG_UNITY, "TestFunc1");
        Toast.makeText(this, "Unity calls android successfully", Toast.LENGTH_LONG).show();
        AndoridCallUnity();
    }

    //Android calls Unity
    public void AndoridCallUnity() {
        //The first parameter is the name of the object used to receive android messages in the Unity scene
        //The second parameter is a member method name of the script on the object (the script name is not limited)
        //The third parameter is the parameter of the unity method
        UnityPlayer.UnitySendMessage("receiveObj", "OnMessage", "Android calls Unity successfully");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}