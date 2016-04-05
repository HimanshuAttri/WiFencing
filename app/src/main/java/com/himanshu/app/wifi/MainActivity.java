package com.himanshu.app.wifi;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String st;
    int flag=0;
    NotificationManager manager;
    Notification myNotication;
    private static final int NOTIFY_ME_ID=1337;
    TextView[] txv = new TextView[10];
    //TextView thead =(TextView)findViewById(R.id.tv_heading);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //


        txv[0] = (TextView) findViewById(R.id.tv1);
       /* txv[1]=(TextView) findViewById(R.id.tv2);
        txv[2]=(TextView) findViewById(R.id.tv3);
        txv[3]=(TextView) findViewById(R.id.tv4);*/
        // txv5=(TextView) findViewById(R.id.tv5);
        //thead.setText("List Of WiFi Available !");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



                    RunScan();






            }




    public void RunScan() {
        WifiManager wManager;
        List<ScanResult> wifiList;


        wManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        wifiList = wManager.getScanResults();
        txv[0].setText("");
        new IntentFilter(wManager.SCAN_RESULTS_AVAILABLE_ACTION);
        wManager.startScan();
        for (int i = 0; i < wifiList.size(); i++) {
            ScanResult scanresult = wManager.getScanResults().get(i);
            double exp = (27.55 - (20 * Math.log10(scanresult.frequency)) + Math.abs(scanresult.level)) / 20.0;

            Math.pow(10.0, exp);
            txv[0].setMovementMethod(new ScrollingMovementMethod());
            double dis = (Math.pow(10.0, exp) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat("###.##");
            String id=scanresult.SSID.toString();

                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setAutoCancel(false);
                builder.setTicker("this is ticker text");

            if(flag==0)
            {

            if(new String("NirmankartaWiFi").equals(id)) {
                builder.setContentTitle("Welcome Home");
                flag=1;
            }

                if (new String("proxy01").equals(id)) {
                    builder.setContentTitle("Welcome To NSIT");
                    flag=1;
                }
                if (new String("kunjbihari").equals(id)) {
                    builder.setContentTitle("Welcome To Sangam Vihar");
                    flag=1;
                }
                if (new String("kunjbihari").equals(id)) {
                    builder.setContentTitle("Welcome To Sangam Vihar");
                    flag=1;
                }
                if (new String("NETGEAR84").equals(id)) {
                    builder.setContentTitle("Welcome To COE Block");
                    flag=1;
                }
                if (new String("GCLAB").equals(id)) {
                    builder.setContentTitle("ARE You in Class?");
                    flag=1;
                }

                builder.setContentText("New Delhi");
                builder.setSmallIcon(R.drawable.ifo);
                // builder.setContentIntent(pendingIntent);
                builder.setOngoing(true);
                builder.setSubText("Build No 1.3");
                builder.setNumber(100);
                builder.build();
                myNotication = builder.getNotification();
                manager.notify(11, myNotication);
            }



            txv[0].append("BSSID: " + scanresult.BSSID + "\n");
            txv[0].append("RSSI: " + scanresult.level + "\n" + "Frequency: " + scanresult.frequency + "\n" + "Capability: " + scanresult.capabilities + "\n" + scanresult.SSID + "\n" + scanresult.timestamp / 60000000 + "\n" + "Approx Distance Of Access Point: " + df.format(dis) + " m." + st + "\n\n");


        }
    }

}