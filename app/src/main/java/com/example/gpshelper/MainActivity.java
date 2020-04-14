package com.example.gpshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void RequestPermission() {
        ActivityCompat.requestPermissions(this,
                new String [] {Manifest.permission.ACCESS_FINE_LOCATION},100 );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Permission Denied To Access Location",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        }

    }


    public void OnClick(View view) {
        checkPermissions();
        GetLocation GetLocation =  new GetLocation(this);
        if(GetLocation.canGetLocation()){
            double lat = GetLocation.getLatitude();
            double lon = GetLocation.getLongitude();
            Toast.makeText(this,
                    "Location is \n lat : " + lat + "\n lon : " + lon,
                    Toast.LENGTH_SHORT).show();
        } else {
            GetLocation.showGpsAlertDialog();
        }
    }
    
}
