package ioasys.books.network.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

class NetworkMonitor(
    private val context: Context
) {
    private var isConnected = false

    fun isOnline(): Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                if (isConnected.not()) {
                    isConnected = connectivityManager.isCurrentlyConnected()
                    channel.trySend(isConnected)
                }
            }

            override fun onLost(network: Network) {
               if (isConnected) {
                   isConnected = connectivityManager.isCurrentlyConnected()
                   channel.trySend(isConnected)
               }
            }
        }

        connectivityManager?.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(),
            callback
        )

        awaitClose {
            connectivityManager?.unregisterNetworkCallback(callback)
        }
    }.conflate()

    private fun ConnectivityManager?.isCurrentlyConnected() = when (this) {
        null -> false
        else -> activeNetwork
            ?.let(::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
}