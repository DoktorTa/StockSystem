package stenograffia.app.ui.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stenograffia.app.domain.model.AuthTokens

class DataStoreSettings(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

        val THEME_KEY = booleanPreferencesKey("theme")
        val LOCALE_KEY = stringPreferencesKey("locale")

        val LOGIN_CORRECT_KEY = booleanPreferencesKey("login_correct")

        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
//        val LOGIN_CORRECT_KEY = booleanPreferencesKey("login_correct")
    }

    suspend fun saveTokens(authTokens: AuthTokens){
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = authTokens.accessToken
            preferences[REFRESH_TOKEN] = authTokens.refreshToken
        }
    }

    fun getTokens(): Flow<AuthTokens> {
        val authToken: Flow<AuthTokens> = context.dataStore.data.map { preferences ->
            val accessToken = preferences[ACCESS_TOKEN] ?: ""
            val refreshToken = preferences[REFRESH_TOKEN] ?: ""
            AuthTokens(accessToken, refreshToken)
        }

        return authToken
    }

    fun getLoginCorrect(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[LOGIN_CORRECT_KEY] ?: false
        }


    suspend fun saveLoginCorrect(loginCorrect: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_CORRECT_KEY] = loginCorrect
        }
    }

    fun getTheme(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: false
        }

    fun getLocale(): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LOCALE_KEY] ?: "en"
        }

//    suspend fun saveLoginCorrect(loginCorrect: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[LOGIN_CORRECT_KEY] = loginCorrect
//        }
//    }

    suspend fun saveTheme(isDarkThemeEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkThemeEnabled
        }
    }

    suspend fun saveLocale(localeCode: String) {
        context.dataStore.edit { preferences ->
            preferences[LOCALE_KEY] = localeCode
        }
    }
}