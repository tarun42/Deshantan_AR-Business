package com.manet.deshatan.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.manet.deshatan.R;
import com.manet.deshatan.constants;
import com.manet.deshatan.gameAcitivity.GameActivity;

public class JoinRoomFrag extends Fragment {
    ViewFlipper imgFlipper;
    EditText roomNumber;
    Button join;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join_room_new, container, false);
        roomNumber = view.findViewById(R.id.joinRoomNumber);
        join = view.findViewById(R.id.joinbtn);

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

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room = roomNumber.getText().toString();
                if(!room.isEmpty() && room.length()==5 )
                {
                    // CHECK ROOM EXIXTS OR NOT
                    constants.UniversalRoomNumber = room;
                    startActivity(new Intent(getContext(), GameActivity.class));
                }
                else
                {
                    Toast.makeText(getContext(),"Enter valid Code",Toast.LENGTH_SHORT).show();
                    roomNumber.setText("");
                }
            }
        });
        return view;
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