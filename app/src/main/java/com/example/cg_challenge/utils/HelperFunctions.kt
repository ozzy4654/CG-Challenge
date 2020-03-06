package com.example.cg_challenge.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cg_challenge.R

val networkRequest = NetworkRequest.Builder().build()
var dialog: AlertDialog? = null

object PreferenceHelper {
    fun customPrefs(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}

fun createNetworkCallback(activity: AppCompatActivity): ConnectivityManager.NetworkCallback {
    //network checker from Android Developers site
    return object : ConnectivityManager.NetworkCallback() {
        val builder = AlertDialog.Builder(activity, R.style.Theme_AppCompat_Light_Dialog)
            .setTitle(activity.getString(R.string.network_alert_title))
            .setMessage(activity.getString(R.string.network_alert_message))
            .setPositiveButton(activity.getString(R.string.dialog_pos_button_text)) { dialog, _ -> dialog.dismiss()
            }


        override fun onAvailable(network: Network) {
            dialog?.dismiss()
        }

        override fun onUnavailable() {
            super.onUnavailable()
            dialog = builder.show()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            dialog = builder.show()
        }
    }
}