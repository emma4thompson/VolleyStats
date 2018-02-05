package com.example.niceg.mysqlproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo extends AppCompatActivity {

    List playerList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClearClick(View view) {

    }

    public void onDoneClick(View view) {

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onAddClick(View view) {
        //newPlayer(playerList);
    }

    public void newPlayer(List list) {
        for (int i = 0; i < list.size(); i++) {
            Player temp = (Player)list.get(i);
        }
    }
}
