package stenograffia.app.ui.screens.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import stenograffia.app.domain.model.AuthTokens

//class DataStoreAuthTokens(private val context: Context) {
//
//    companion object {
//        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
//
//        val LOGIN_CORRECT_KEY = booleanPreferencesKey("login_correct")
//
//        val ACCESS_TOKEN = stringPreferencesKey("access_token")
//        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
//    }
//
//    suspend fun saveTokens(accessToken: String, refreshToken: String){
//        context.dataStore.edit { preferences ->
//            preferences[ACCESS_TOKEN] = accessToken
//            preferences[REFRESH_TOKEN] = refreshToken
//        }
//    }
//
//    fun getTokens(): Flow<AuthTokens> {
//        val authToken: Flow<AuthTokens> = context.dataStore.data.map { preferences ->
//            val accessToken = preferences[ACCESS_TOKEN] ?: ""
//            val refreshToken = preferences[REFRESH_TOKEN] ?: ""
//            AuthTokens(accessToken, refreshToken)
//        }
//
//        return authToken
//    }
//
//    fun getLoginCorrect(): Flow<Boolean> = context.dataStore.data
//        .map { preferences ->
//            preferences[LOGIN_CORRECT_KEY] ?: false
//        }
//
//
//    suspend fun saveLoginCorrect(loginCorrect: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[LOGIN_CORRECT_KEY] = loginCorrect
//        }
//    }
//
//
//}