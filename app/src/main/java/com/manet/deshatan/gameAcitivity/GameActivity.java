package com.manet.deshatan.gameAcitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manet.deshatan.R;
import com.manet.deshatan.constants;
import com.manet.deshatan.dataModels.Game;
import com.manet.deshatan.dataModels.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    String TAG = "GameActivity";
    FirebaseDatabase database;
    DatabaseReference gameRef;
    Game gameObj;
//    ListView listView;
    GridView gridView;
    Button start,dice,map;
    TextView status,action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        start = findViewById(R.id.start);
        map = findViewById(R.id.map);
        dice = findViewById(R.id.dice);
        status = findViewById(R.id.status);
        action = findViewById(R.id.action);
        gridView = findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference("game").child(constants.UniversalRoomNumber);

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Log.d("DataSnapshot "," : "+snapshot.toString());
                gameObj = snapshot.getValue(Game.class);
                Toast.makeText(getApplicationContext(),""+gameObj.getOwner()+" = = "+gameObj.getPlayers().get(0).getUserName() + " == "+ gameObj.getPlayers().get(0).getBalance(),Toast.LENGTH_LONG).show();
                if(constants.id.equals("-1"))
                {
                    setID();
                }
                if(!checkAmInGame())
                {
                    Log.d(TAG,"Im not in game");
                    addMeToGame();
                }
                if(checkAmIOwner() && !gameObj.getStartGame())
                {
                    Log.d(TAG,"NOW START SHOUULD BE VISIBLE");
                    dice.setVisibility(View.GONE);
                    start.setVisibility(View.VISIBLE);
                }
                if(gameObj.getStartGame())
                {
                    setStatus();
//                    setAction();
                }
                PlayerList playersAdapter = new PlayerList(GameActivity.this, gameObj.getPlayers());
                //attaching adapter to the listview
                gridView.setAdapter(playersAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameObj.setStartGame(true);
                gameRef.setValue(gameObj);
                Log.d(TAG,"NOW START SHOUULD BE INVISIBLE");
                start.setVisibility(View.GONE);

                dice.setVisibility(View.VISIBLE);
            }
        });

        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameObj.getTurn().equals(constants.id))
                {
                    Toast.makeText(getApplicationContext(),"its your turn ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wait for your turn",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(getApplicationContext(), "MY ID IS : "+constants.id, Toast.LENGTH_SHORT).show();
    }

    void setStatus()
    {
        if(gameObj.getTurn().equals(constants.id))
        {
            status.setText("Your turn to play....");
        }
        else
        {
            status.setText(gameObj.getPlayers().get(Integer.valueOf(gameObj.getTurn())).getUserName() + " is having turn....");
        }
    }
    boolean checkAmInGame(){
        ArrayList<Player> players = gameObj.getPlayers();
        Log.d(TAG,"size : "+players.size());
        for(int i = 0; i<players.size() ;i++)
        {
            Log.d(TAG, players.get(i).getUserName());
            Log.d(TAG,constants.userName);
            if( players.get(i).getUserName().equals(constants.userName) )
            {
                return true;
            }
        }
        return false;
    }
    void addMeToGame(){

        ArrayList<String> monuments  = new ArrayList<>();
        monuments.add("");
        Player player = new Player("10000","0",constants.userName, String.valueOf(gameObj.getPlayers().size())  ,monuments);
        constants.id = String.valueOf(gameObj.getPlayers().size());
        gameObj.getPlayers().add(player);
        gameRef.setValue(gameObj);
        Log.d(TAG,"added to game");
    }
    boolean checkAmIOwner(){
        if(gameObj.getOwner().equals(constants.userName))
            return true;
        return false;
    }
    void setID(){
        ArrayList<Player> players = gameObj.getPlayers();
        for(int i = 0; i<players.size() ;i++)
        {
            if( players.get(i).getUserName().equals(constants.userName) )
            {
                constants.id= String.valueOf(i);
            }
        }
    }
}