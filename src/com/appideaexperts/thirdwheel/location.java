package com.appideaexperts.thirdwheel;

/**made this to test adding a location.  
 * must try another way of implementing in the
 * Registration2 and Registration2b screens
 */

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import com.parse.ParseGeoPoint;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class location extends FragmentActivity implements LocationListener,
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {

	private Location currentLocation;
	private Location lastLocation;
	private LocationRequest locationRequest;
	private LocationClient locationClient;

	public static class ErrorDialogFragment extends DialogFragment {
		private Dialog mDialog;

		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

	private boolean servicesConnected() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);

		if (ConnectionResult.SUCCESS == resultCode) {
			return true;
		} else {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
					this, 0);
			if (dialog != null) {
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(dialog);
				errorFragment.show(getFragmentManager(), getLocalClassName());
			}
			return false;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Create a new global location parameters object
		locationRequest = LocationRequest.create();
		locationRequest.setInterval(150000);
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		locationRequest.setFastestInterval(5000);

		locationClient = new LocationClient(this, this, this);
	}

	private void startPeriodicUpdates() {
		locationClient.requestLocationUpdates(locationRequest, this);
	}

	private void stopPeriodicUpdates() {
		locationClient.removeLocationUpdates(this);
	}

	private Location getLocation() {
		if (servicesConnected()) {
			return locationClient.getLastLocation();
		} else {
			return null;
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
		    try {
		      connectionResult.startResolutionForResult(this, 9000);
		    } catch (IntentSender.SendIntentException e) {
		    }
		  } else {
		    Toast.makeText(this, connectionResult.getErrorCode(), Toast.LENGTH_LONG);
		  }
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		currentLocation = getLocation();
		startPeriodicUpdates();
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(Location location) {
		currentLocation = location;
		int lat = (int) (location.getLatitude() * 1E6);
	    int lng = (int) (location.getLongitude() * 1E6);
	    ParseGeoPoint point = new ParseGeoPoint(lat, lng);
		  if (lastLocation != null){
			  int lat2 = (int) (lastLocation.getLatitude() * 1E6);
			  int lng2 = (int) (lastLocation.getLongitude() * 1E6);
			  ParseGeoPoint point2 = new ParseGeoPoint(lat2, lng2);
			  if (point.distanceInKilometersTo(point2) < 0.01){
				  return;
			  }
		  }
		  lastLocation=location;
		
	}
	@Override
	public void onStop() {
	    if (locationClient.isConnected()) {
	      stopPeriodicUpdates();
	    }
	    locationClient.disconnect();
	 
	    super.onStop();
	  }
	 
	  @Override
	  public void onStart() {
	    super.onStart();
	 
	    locationClient.connect();
	  }
}
