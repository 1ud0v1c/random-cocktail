package com.ludovic.vimont.randomcocktail.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHelper {
    companion object {
        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connectivityManager?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                        return when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            else -> false
                        }
                    }
                } else {
                    connectivityManager.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            return true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            return true
                        }
                    }
                }
            }
            return false
        }
    }
}