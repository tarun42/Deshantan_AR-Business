package com.manet.deshatan.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.manet.deshatan.R;
import com.manet.deshatan.constants;
import com.manet.deshatan.dataModels.Game;
import com.manet.deshatan.dataModels.Player;
import com.manet.deshatan.gameAcitivity.GameActivity;

import java.util.ArrayList;


public class CreateRoomFrag extends Fragment {
    ViewFlipper imgFlipper;
    TextView roomNumber;
    Button create,next;
    FirebaseDatabase database;
    DatabaseReference gameRef;
    DatabaseReference roomRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_create_room_new, container, false);
        roomNumber = view.findViewById(R.id.createRoomNumber);
        create = view.findViewById(R.id.joinbtn);
        next = view.findViewById(R.id.next);

        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference("game");
        roomRef = database.getReference("rooms");

        int sliders[]={

                R.drawable.hawa,
                R.drawable.lotus,
                R.drawable.india_gate,
                R.drawable.jama,
                R.drawable.golden


        };
        imgFlipper= view.findViewById(R.id.imgFlipper);
        int i=0;
        for(int slide:sliders){
            i++;
            sliderFlipper(slide,i);
        }


        constants.UniversalRoomNumber = String.valueOf((int) (Math.random()*100000));
        Log.d("UniversalRoomNumber" ," :  "+constants.UniversalRoomNumber);
        roomNumber.setText(constants.UniversalRoomNumber);
        roomRef.child(constants.UniversalRoomNumber).setValue("available");
        createNewGame();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GameActivity.class));
            }
        });

        return view;
    }

    void createNewGame()
    {
        ArrayList<String> monumnets =  new ArrayList<>();
        monumnets.add("");
        Player player =  new Player("10000","0",constants.userName,"1",monumnets);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        Game game = new Game(players , constants.userName,"0");
        gameRef.child(constants.UniversalRoomNumber).setValue(game);
        constants.id = "0";
//        Log.d("============= ; ",game.getMonuments().get("xyz"));

    }

    public void sliderFlipper(int image,int x){

        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(image);

        imgFlipper.addView(imageView);
        imgFlipper.setFlipInterval(4000);
        imgFlipper.setAutoStart(true);
        imgFlipper.setInAnimation(getContext(),android.R.anim.fade_in);
        imgFlipper.setOutAnimation(getContext(),android.R.anim.fade_out);


    }
}