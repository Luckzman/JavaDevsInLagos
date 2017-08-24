package com.example.lucky.javadevsinlagos;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;
import android.support.v4.app.TaskStackBuilder;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private String url;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //ImageView and TextView object is been created to load text and image in profile activity
        TextView textView = (TextView) findViewById(R.id.profile_username);
        CircleImageView img = (CircleImageView) findViewById(R.id.profile_image);
        TextView textView2 = (TextView) findViewById(R.id.profile_user_url);

        //Intent is used to fetch data from the MainActivity class
        Intent intent = getIntent();
        userName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1).toUpperCase();
        url = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String image = intent.getStringExtra(MainActivity.EXTRA_MESSAGE3);

        //textView objects are set to display their respective data
        textView.setText(userName);
        textView2.setText(url);
        Glide.with(this).load(image).into(img); //Glide is used here to load to image in profile activity

        //fab button to send share intent
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProfile(); //method call to shareprofile
            }
        });

    }

    /* This method sends a share intent to be displayed in a browser*/
    private void shareProfile(){
        Intent fabIntent = new Intent(Intent.ACTION_SEND);
        fabIntent.setType("text/plain");
        String content =  "Check out this awesome developer @" +userName.toLowerCase()+" , "+ url;
        fabIntent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(fabIntent,"Share via"));
    }

    //this method prevent the back-button on the menu from creating a new activity instead it will load its previous state
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
