package com.manet.deshatan.gameAcitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridView = findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        gameRef = database.getReference("game").child(constants.UniversalRoomNumber);

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Log.d("DataSnapshot "," : "+snapshot.toString());
                gameObj = snapshot.getValue(Game.class);
                Toast.makeText(getApplicationContext(),""+gameObj.getOwner()+" = = "+gameObj.getPlayers().get(0).getUserName() + " == "+ gameObj.getPlayers().get(0).getBalance(),Toast.LENGTH_LONG).show();

                if(!checkAmInGame())
                {
                    Log.d(TAG,"Im not in game");
                    addMeToGame();
                }

                PlayerList playersAdapter = new PlayerList(GameActivity.this, gameObj.getPlayers());
                //attaching adapter to the listview
                gridView.setAdapter(playersAdapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



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
        gameObj.getPlayers().add(player);
        gameRef.setValue(gameObj);
        Log.d(TAG,"added to game");
    }
}