package com.manet.deshatan.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manet.deshatan.R;
import com.manet.deshatan.constants;
import com.manet.deshatan.gameAcitivity.GameActivity;

public class JoinRoomFrag extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_join_room, container, false);
        roomNumber = view.findViewById(R.id.joinRoomNumber);
        join = view.findViewById(R.id.joinbtn);

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
}