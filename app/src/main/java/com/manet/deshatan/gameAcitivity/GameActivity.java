                                                                                                                                                                                        package com.manet.deshatan.gameAcitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.manet.deshatan.BackgroundMusicService;
import com.manet.deshatan.R;
import com.manet.deshatan.constants;
import com.manet.deshatan.dataModels.Game;
import com.manet.deshatan.dataModels.Player;
import com.manet.deshatan.mainActivity.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    String TAG = "GameActivity";
    FirebaseDatabase database;
    DatabaseReference gameRef;
    Game gameObj;
    GridView gridView;
    Button start,dice,map;
    TextView status,action;
    Intent intent;


    @Override
    protected void onPause() {
        super.onPause();
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(intent);
    }

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

        intent = new Intent(GameActivity.this, BackgroundMusicService.class);
        startService(intent);

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Log.d("DataSnapshot "," : "+snapshot.toString());
                gameObj = snapshot.getValue(Game.class);
//                Toast.makeText(getApplicationContext(),""+gameObj.getOwner()+" = = "+gameObj.getPlayers().get(0).getUserName() + " == "+ gameObj.getPlayers().get(0).getBalance(),Toast.LENGTH_LONG).show();
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
                }
                PlayerList playersAdapter = new PlayerList(GameActivity.this, gameObj.getPlayers());
                //attaching adapter to the listview
                gridView.setAdapter(playersAdapter);
                action.setText(gameObj.getAction());

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameObj.setStartGame(true);
                gameObj.setAction("STARTED");
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
                    int rand = ((int)(Math.random()*100))%6 + 1;
                    int nextPos = (Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getCurPos()) + rand)%16 ;

                    if(isOwned(nextPos))
                    {
                        showOwnedDialogBox(String.valueOf(nextPos));
                    }
                    else
                    {
                        showNotOwnedDialogBox(String.valueOf(nextPos));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wait for your turn",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(getApplicationContext(), "MY ID IS : "+constants.id, Toast.LENGTH_SHORT).show();
    }

    void setStatus() {
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
    boolean isOwned(int pos) {
        String cityName = constants.cityMap.get(String.valueOf(pos));
        if(gameObj.getMonuments().get(cityName).equals("available") )
        {
            return false;
        }
        return true;
    }
    void showNotOwnedDialogBox(String pos){
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.not_owned_dialog_box, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView textView = dialogView.findViewById(R.id.place);
        textView.setText(constants.cityMap.get(pos));
        dialogView.findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clicked buy",Toast.LENGTH_LONG).show();
                BUY(pos);
                Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getBalance());
                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.leave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clicked leave",Toast.LENGTH_LONG).show();
                LEAVE(pos);
                alertDialog.dismiss();
            }
        });
    }
    void showOwnedDialogBox(String pos){
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.owned_dialog_box, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
        TextView textView = dialogView.findViewById(R.id.place);
        textView.setText(constants.cityMap.get(pos));
        dialogView.findViewById(R.id.payrent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clicked payrent",Toast.LENGTH_LONG).show();
                PAYRENT(pos);
                alertDialog.dismiss();
            }
        });

    }
    void BUY(String pos){
        if(Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getBalance()) >= Integer.valueOf(constants.priceMap.get(pos)))
        {
            gameObj.getPlayers().get(Integer.valueOf(constants.id)).setBalance(String.valueOf( Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getBalance()) - Integer.valueOf(constants.priceMap.get(pos)) ));
            gameObj.getPlayers().get(Integer.valueOf(constants.id)).setCurPos(pos);
            gameObj.getPlayers().get(Integer.valueOf(constants.id)).getMonuments().add(constants.cityMap.get(pos));
            gameObj.setTurn(String.valueOf(((Integer.valueOf( gameObj.getTurn() ) )+1) % gameObj.getPlayers().size()) );
            gameObj.getMonuments().put(constants.cityMap.get(pos),constants.id);
            gameObj.setAction(constants.userName+" has bought "+constants.cityMap.get(pos));

            gameRef.setValue(gameObj);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"You dont have enough balance", Toast.LENGTH_SHORT).show();
            LEAVE(pos);
        }
    }
    void LEAVE(String pos){
        gameObj.setTurn(String.valueOf(((Integer.valueOf( gameObj.getTurn() ) )+1) % gameObj.getPlayers().size()) );
        gameObj.setAction(constants.userName + " has decided to leave");

        gameRef.setValue(gameObj);
    }
    void PAYRENT(String pos){
        if( Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getBalance()) >=  Integer.valueOf(constants.priceMap.get(pos)) )
        {
            String cityName = constants.cityMap.get(String.valueOf(pos));
            String ownerID = gameObj.getMonuments().get(cityName);
            String ownerBal = gameObj.getPlayers().get(Integer.valueOf(ownerID)).getBalance();
            gameObj.getPlayers().get(Integer.valueOf(ownerID)).setBalance(String.valueOf( Integer.valueOf(constants.priceMap.get(pos)) + Integer.valueOf(ownerBal) ));
            gameObj.getPlayers().get(Integer.valueOf(constants.id)).setBalance(String.valueOf( Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getBalance()) - Integer.valueOf(constants.priceMap.get(pos)) ));
            gameObj.setTurn(String.valueOf(((Integer.valueOf( gameObj.getTurn() ) )+1) % gameObj.getPlayers().size()) );
            gameObj.setAction(constants.userName + " has paid rent for "+ constants.cityMap.get(pos));

            gameRef.setValue(gameObj);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"you are out of game! ",Toast.LENGTH_SHORT).show();
            gameObj.setAction(constants.userName + "out of money to pay rent!.");
            gameRef.setValue(gameObj);
            //I dont know
        }
    }
}