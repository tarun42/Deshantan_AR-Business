package com.manet.deshatan.mainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.manet.deshatan.R;
import com.manet.deshatan.constants;

public class JoinCreateFrag extends Fragment {

    Button join,create;
    EditText username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join_create, container, false);
        join = view.findViewById(R.id.join);
        create = view.findViewById(R.id.create);
        username = view.findViewById(R.id.username);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUsername())
                  Navigation.findNavController(view).navigate(R.id.action_joinCreateFrag_to_joinRoomFrag);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUsername())
                    Navigation.findNavController(view).navigate(R.id.action_joinCreateFrag_to_createRoomFrag);
            }
        });
        return view;

    }
    Boolean checkUsername(){

        if(!username.getText().toString().isEmpty() && !username.getText().toString().contains(" "))
        {
            constants.userName = username.getText().toString();
            return true;
        }
        return false;
    }
}