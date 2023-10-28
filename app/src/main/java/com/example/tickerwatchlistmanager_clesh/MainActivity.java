package com.example.tickerwatchlistmanager_clesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CustomViewModel viewModel;
    FragmentManager fg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fg = getSupportFragmentManager();
        Intent intent = getIntent();
        String message = intent.getStringExtra("sms");
        viewModel = new ViewModelProvider(this).get(CustomViewModel.class);

        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.list_ContainerView, new TickerListFragment()).commit();
            fg.beginTransaction().replace(R.id.web_ContainerView2, new InfoWebFragment()).commit();
        }


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){

            String[] permission = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permission,101);
        }
    }

    protected void onNewIntent (Intent intent) {

        super.onNewIntent(intent);
        String text = intent.getStringExtra("sms");

        //validate the sms format
        if(text.startsWith("Ticker:<<") && text.endsWith(">>")){
            String input = text.substring(9, text.length() -2); // access the string not including the parts above
            if(input.matches("[a-zA-Z]+")){
                input.toUpperCase();
                String link = "https://seekingalpha.com/symbol/" + input;
                Ticker newticker = new Ticker(input, link);
                viewModel.addTicker(newticker);
                viewModel.setSelectedTicker(newticker.getLink());
            }
            else{
                Toast.makeText(this,"Invalid ticker", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"No valid watchlist entry was found", Toast.LENGTH_SHORT).show();
        }
    }
}