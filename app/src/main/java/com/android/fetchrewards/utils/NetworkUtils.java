package com.android.fetchrewards.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class NetworkUtils {

    private NetworkUtils() {
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities( connectivityManager.getActiveNetwork() );
                if (capabilities != null) {
                    if (capabilities.hasTransport( NetworkCapabilities.TRANSPORT_WIFI ) || capabilities.hasTransport( NetworkCapabilities.TRANSPORT_CELLULAR )) {
                        return true;
                    }
                }
            } else {
                    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                    if (activeNetwork != null) {
                        // connected to the internet
                        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }
}
