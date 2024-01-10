package com.harsh.swipeandroidassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Handler;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private TextView networkStatusTextView;
    private Context context;

    public NetworkChangeReceiver(Context context, TextView networkStatusTextView) {
        this.context = context;
        this.networkStatusTextView = networkStatusTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateNetworkStatus();
    }

    private void updateNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                if (networkCapabilities != null &&
                        (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                    showConnected();
                } else {
                    showDisconnected();
                }
            } else {
                showDisconnected();
            }
        }
    }

    private void showDisconnected() {
        networkStatusTextView.setText("No Internet Connection");
        networkStatusTextView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
        networkStatusTextView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        networkStatusTextView.setVisibility(TextView.VISIBLE);

        // Hide the TextView after 3 seconds
        Handler handler = new Handler();
        handler.postDelayed(() -> networkStatusTextView.setVisibility(TextView.GONE), 3000);
    }

    private void showConnected() {
        networkStatusTextView.setText("Internet Connected");
        networkStatusTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.connected_green));
        networkStatusTextView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        networkStatusTextView.setVisibility(TextView.VISIBLE);

        // Hide the TextView after 3 seconds
        Handler handler = new Handler();
        handler.postDelayed(() -> networkStatusTextView.setVisibility(TextView.GONE), 3000);
    }
}

