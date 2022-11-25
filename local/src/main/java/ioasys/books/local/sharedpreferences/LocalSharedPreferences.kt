package ioasys.books.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LocalSharedPreferences(
    context: Context
) {

    private val prefs: SharedPreferences = context
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        prefs.registerOnSharedPreferenceChangeListener { _, key ->
            getData(key)
        }
    }

    fun getData(key: String): Flow<Pair<String, String?>> = flow {
        emit(
            Pair(key, prefs.getString(key, String()))
        )
    }

    fun saveData(key: String, value: Any?) {
        with(prefs.edit()) {
            putString(key, value?.toString())
        }
    }

    private companion object {
        const val PREFS_NAME = "ioasys.books.LocalSharedPreferences"
    }
}
