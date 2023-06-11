package stenograffia.app.data.network

object Urls {

    private const val PREFIX: String = "http://192.168.1.102:8000/"

    const val GET_ACCESS_TOKEN: String = PREFIX + "login"
    const val REFRESH_TOKEN: String = PREFIX + "refresh_token"

    const val GET_PAINTS_BY_TIME: String = PREFIX + "get_paint"
    const val UPDATE_PAINT_QUANTITY: String = PREFIX + "update_paint"
}