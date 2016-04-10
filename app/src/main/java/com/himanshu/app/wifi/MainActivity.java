package com.himanshu.app.wifi;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;



public class MainActivity extends ListActivity {
    int flag=0;
   ImageView fab;





   //ListView listView = (ListView) findViewById(R.id.list);
    Random random = new Random();
    String sold="";

    String st;


    NotificationManager manager;
    Notification myNotication;

    TextView[] txv = new TextView[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] BSSID=new String[]{""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, BSSID);


        // Assign adapter to List
        setListAdapter(adapter);





    //    txv[0] = (TextView) findViewById(R.id.tv1);
       fab = (ImageView) findViewById(R.id.bt);
        ImageView about = (ImageView) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This App is developed by Himanshu Attri         Under Graduate at NSIT COE branch.                    Contact : attri.him@gmail.com",
                        Toast.LENGTH_LONG).show();

               // about.startAnimation(myAnimation);




                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.himanshuattri.com"));
                startActivity(browserIntent);


            }
        });




        RunScan();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setRotation(fab.getRotation() + 90);
                RunScan();


            }
        });


    }


    private int RunScan() {

        WifiManager wManager;
        List<ScanResult> wifiList;
        String[] BSSID=new String[]{"killall"};
        // initiate the listadapter
        ArrayList<String> list = new ArrayList<String>();

        ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(this,

               android.R.layout.simple_list_item_1, list);







        wManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        wifiList = wManager.getScanResults();

        new IntentFilter(wManager.SCAN_RESULTS_AVAILABLE_ACTION);
        wManager.startScan();
        for (int i = 0; i < wifiList.size(); i++) {
            ScanResult scanresult = wManager.getScanResults().get(i);
            double exp = (27.55 - (20 * Math.log10(scanresult.frequency)) + Math.abs(scanresult.level)) / 20.0;

            Math.pow(10.0, exp);
//            txv[0].setMovementMethod(new ScrollingMovementMethod());
            double dis = (Math.pow(10.0, exp) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat("###.##");
            String id = scanresult.SSID.toString();
            String id2 = scanresult.BSSID.toString();

            list.add(id + "\n" + id2 + "\n" + "Estimated Distance: " + df.format(dis) + "m.");

            // assign the list adapter




        }

        setListAdapter(myAdapter);




        return  0;
    }

}