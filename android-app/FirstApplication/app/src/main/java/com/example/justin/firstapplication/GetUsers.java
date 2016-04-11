package com.example.justin.firstapplication;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Justin on 4/7/2016.
 */



public class GetUsers extends ListActivity {

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {

        if (json != null) {

            try {
                //ArrayList<HashMap<String, String>> userList = new ArrayList<>();
                returnList = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                JSONArray users = jsonObj.getJSONArray(USERNAME);

                for (int i = 0; i < users.length(); i++) {
                    JSONObject c = users.getJSONObject(i);

                    String username = c.getString(USERNAME);
                    String password = c.getString(PASSWORD);
                    String device = c.getString(DEVICE_ID);

                    // tmp hashmap for single student
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding every child node to HashMap key => value
                    map.put(USERNAME, username);
                    map.put(PASSWORD, password);
                    map.put(DEVICE_ID, device);

                    returnList.add(map);


                }

                return returnList;


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            Log.e("ServiceHandler", "No data received from HTTP request");
            return null;

        }
        return null;

    }


    /*************************
       ** DEFINE VARIABLES **
    *************************/


    private static String ScriptURL = "http://ded01.stormat.ca/~justin/android/get_users.php";

    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> userList;
    ArrayList<HashMap<String, String>> returnList;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DEVICE_ID = "device_id";


    // New onCreate..... Kind of act liks "onload" for jquery

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_users);

        // Hashmap for ListView
        userList = new ArrayList<HashMap<String, String>>();

       //Loads the products to display (see background threat below)
        new LoadUsers().execute();



        // This is what makes our list items now clickable
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String pid = ((TextView) view.findViewById(R.id.username)).getText().toString();


                //For now just redirect back to MainActivity (default page)
                Intent in = new Intent(getApplicationContext(), MainActivity.class);

                in.putExtra(USERNAME, pid);

                startActivityForResult(in, 100);

            }
        });

    }

    class LoadUsers extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GetUsers.this);
            pDialog.setMessage("Loading users. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            try {
                try

                {
                    URL urls = new URL(ScriptURL);

                    try {

                        InputStream in = urls.openStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        //This is for console output
                        System.out.println(result.toString());


                        //Close the buffer
                        in.close();


                        String jsonStr = result.toString();


                        //Parse using Parse function defined above
                        userList = ParseJSON(jsonStr);

                        //userList = result.toString();
                        Log.d("HTTP Request OUTPUT", in.toString());
                        Log.d("JSON OUTPUT", userList.toString());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e)

                {
                    e.printStackTrace();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    Log.d("JSON OUTPUT", userList.toString());
                    /**
                     * Updating parsed JSON data into ListView
                     * */


                    ListAdapter adapter = new SimpleAdapter(
                            GetUsers.this, userList,
                            R.layout.list, new String[]{USERNAME,
                            PASSWORD, DEVICE_ID},
                            new int[]{R.id.username, R.id.password, R.id.device_id});
                    //ListView list = (ListView) findViewById(android.R.id.list);
                    //list.setAdapter(adapter);
                    setListAdapter(adapter);
                }
            });

        }

    }
}



