package com.manet.deshatan.gameAcitivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.manet.deshatan.R;
import com.manet.deshatan.dataModels.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends ArrayAdapter<Player> {
    private Activity context;
    ArrayList<Player> gamePlayers;
    public PlayerList(@NonNull Activity context, ArrayList<Player> gamePlayers) {
        super(context, R.layout.player_list_item,gamePlayers);
        this.context = (Activity) context;
        this.gamePlayers = gamePlayers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.player_list_item, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewPos = (TextView) listViewItem.findViewById(R.id.curposition);
        TextView textViewBal = (TextView) listViewItem.findViewById(R.id.balance);

        Player player = gamePlayers.get(position);
        textViewName.setText(player.getUserName());
        textViewPos.setText(player.getCurPos());
        textViewBal.setText(player.getBalance());

        return listViewItem;
    }
}
