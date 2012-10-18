package com.example.gpsexample;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity  {
	
	  private TextView latituteField;
	  private TextView longitudeField;

	    /**
	     * Provides the location updates.
	     */
	  private LocationManager mLocationManager;
	  

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        latituteField = (TextView) findViewById(R.id.TextLatitude);
        longitudeField = (TextView) findViewById(R.id.TextLongitude);
        



    }
    
    @Override
    public void onResume() {
    super.onResume();
    
    String context = Context.LOCATION_SERVICE;    
    mLocationManager = (LocationManager) getSystemService(context);
    
    getLocation();
    
	}
    
    @Override
    public void onPause(){
    	super.onPause();
    	
    	mLocationManager.removeUpdates(mLocationListener);
    	
    }

    private final LocationListener mLocationListener = new LocationListener() {
        
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
        
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            
        }
        
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            
        }
        
        /**
         * Called when the listened gets a location update. Broadcasts the
         * update.
         */
        @Override
        public void onLocationChanged(final Location location) {
            
            int lat = (int) (location.getLatitude());
            int lng = (int) (location.getLongitude());
            latituteField.setText(String.valueOf(lat));
            longitudeField.setText(String.valueOf(lng));
        	
        }
    };
    

    
    public void update() {
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(mLocationListener);
    }
    
    private void getLocation() {
        
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = mLocationManager.getBestProvider(criteria, false);
        
        mLocationManager.requestLocationUpdates(provider, 0, 0,
                mLocationListener);
        
    }

}
