package frost.com.htvchallengetruck;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import frost.com.htvchallengetruck.retrofit.RetrofitAPIClient;
import frost.com.htvchallengetruck.sharePreferences.AppPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendTruckDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private String item;
    private AppPreferenceUtils prefs;
    protected Location mLocation;
//    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_truck_details);
        Spinner spinner = (Spinner) findViewById(R.id.spn_vehicleId);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        Log.i("FCMK", "onCreate: "+new AppPreferenceUtils(this).getRegId());

        List<String> vehicleIds = new ArrayList<>();
        vehicleIds.add("Submit Vehicle ID");
        for (int i = 0; i <= 13; i++) {
            vehicleIds.add(i + "");
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleIds);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        Button btnSubmit = (Button) findViewById(R.id.btn_vehicleId);
        prefs = new AppPreferenceUtils(getApplicationContext());
        /*if (ActivityCompat.checkSelfPermission(SendTruckDetailsActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(SendTruckDetailsActivity.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SendTruckDetailsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                mLocation = location;
                            }
                        }
                    });*/
            btnSubmit.setOnClickListener(this);
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(!item.equals("Submit Vehicle ID")){
            RetrofitAPIClient retrofitAPIClient = RetrofitAPIClient.retrofit.create(RetrofitAPIClient.class);
            Call<ResponseModel> callForTruckData = retrofitAPIClient.callTruckService(item,prefs.getRegId());
            callForTruckData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    ResponseModel responseModel = response.body();
                    prefs.setVehicleId(item);
                    /*String uri = "http://maps.google.com/maps?saddr=" + mLocation.getLatitude() + "," + mLocation.getLongitude() + "&daddr=" + *//*responseModel.getLat()*//*42.652580 + "," + -73.756233;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);*/
                    Log.i("frost", "onResponse: "+responseModel.getResponse());
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {

                }
            });


        }else{
            Toast.makeText(this, "Not a Valid Selection", Toast.LENGTH_SHORT).show();
        }
    }
}
