package com.example.lucky.javadevsinlagos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by LUCKY on 8/18/2017.
 */

public final class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    private Utils() {
    }

    public static List<JavaDevs> extractJavaDevInfo(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<JavaDevs> javaDevs = extractInfoFromJson(jsonResponse);
        return javaDevs;
    }

    /*this method convert Url String and returns a URL Object*/
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error closing Input Stream", e);
        }
        return url;
    }
    //HttpRequest Helper method
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON result");
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //this helper method read stream input from url and convert them to Strings
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    //this method parse JSON object from the String Url
    public static List<JavaDevs> extractInfoFromJson(String javaDevJSON){
        if(TextUtils.isEmpty(javaDevJSON)){
            return null;
        }
        List<JavaDevs> javaDevs = new ArrayList<JavaDevs>();
        try{
            JSONObject root = new JSONObject(javaDevJSON);
            JSONArray itemArray = root.getJSONArray("items");
            for (int i = 0; i < itemArray.length(); i++){
                JSONObject currentObj = itemArray.getJSONObject(i);
                String javaDevImageUrl = currentObj.getString("avatar_url");
                String javaDevUserName = currentObj.getString("login");
                String javaDevUrl = currentObj.getString("html_url");

                JavaDevs javaDevsObj = new JavaDevs(javaDevImageUrl,javaDevUserName,javaDevUrl);
                javaDevs.add(javaDevsObj);

            }
        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem parsing the JavaDev JSON result");
        }

        return javaDevs;
    }


}
