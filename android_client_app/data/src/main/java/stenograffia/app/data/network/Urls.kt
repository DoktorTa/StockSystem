package stenograffia.app.data.network

object Urls {

    const val PREFIX: String = "http://192.168.1.112:8000/"

    const val GET_ACCESS_TOKEN: String = PREFIX + "login"
    const val REFRESH_TOKEN: String = PREFIX + "refresh_token"
}