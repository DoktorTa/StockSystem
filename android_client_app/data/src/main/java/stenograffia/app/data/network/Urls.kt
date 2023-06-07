package stenograffia.app.data.network

object Urls {

    const val PREFIX: String = "http://192.168.1.102:8000/"

    const val GET_ACCESS_TOKEN: String = PREFIX + "login"
    const val REFRESH_TOKEN: String = PREFIX + "refresh_token"

    const val UPDATE_PAINT_QUANTITY: String = PREFIX + "update_paint"
}