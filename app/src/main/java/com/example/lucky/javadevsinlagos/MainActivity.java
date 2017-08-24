package com.example.lucky.javadevsinlagos;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<JavaDevs>> {
    private static final int JAVADEV_LOADER_ID = 1;
    private final static String GITHUB_URL = "https://api.github.com/search/users?q=language:Java+location:Lagos";
    JavaDevsAdapter javaDevsAdapter;
    public static final String EXTRA_MESSAGE1 = "user";
    public static final String EXTRA_MESSAGE2 = "url";
    public static final String EXTRA_MESSAGE3 = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        javaDevsAdapter = new JavaDevsAdapter(this, new ArrayList<JavaDevs>());
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(javaDevsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                JavaDevs javaDevs = javaDevsAdapter.getItem(position);
                String userName = String.valueOf(javaDevs.getUserJavaDevs());
                String url = javaDevs.getUrlJavaDevs().toString();
                String image = javaDevs.getImageUrlJavaDevs().toString();
                intent.putExtra(EXTRA_MESSAGE3,image);
                intent.putExtra(EXTRA_MESSAGE1,userName);
                intent.putExtra(EXTRA_MESSAGE2, url);
                startActivity(intent);
            }
        });

        checkConnection();

    }
    //this method check for internet connection
    //loads the listView if there is connection or load and emptyview if there is no connection
    private void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(JAVADEV_LOADER_ID, null, this);
        } else {
            TextView emptytextView =(TextView) findViewById(R.id.emptyView);
            emptytextView.setText("Check your Internet Connection");
        }
    }


    @Override
    public Loader<List<JavaDevs>> onCreateLoader(int id, Bundle args) {
        return new JavaDevsLoader(this, GITHUB_URL);
    }

    //return the list<JavaDevs> object from the loadInBackground method in JavaDevsLoader.java class
    @Override
    public void onLoadFinished(Loader<List<JavaDevs>> loader, List<JavaDevs> data) {
        javaDevsAdapter.clear();
        if (data != null && !data.isEmpty()) {
            javaDevsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<JavaDevs>> loader) {
        javaDevsAdapter.clear();
    }
}
