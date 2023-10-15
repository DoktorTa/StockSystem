package stenograffia.app.data.network

object Urls {

//    const val BASE_URL: String = "http://steno-stock.ru:8080/"
//    const val BASE_URL: String = "http://192.168.1.102:8000/"
    const val BASE_URL: String = "http://192.168.1.102:8080/"

//    const val BASE_URL: String = "http://192.168.88.67:8000/"


    const val GET_ACCESS_TOKEN: String = BASE_URL + "login"
    const val REFRESH_TOKEN: String = BASE_URL + "refresh_token"
    const val GET_USER_BY_TOKEN: String = BASE_URL + "get_user"

    const val GET_PAINTS_BY_TIME: String = BASE_URL + "get_paints"
    const val CHANGE_QUANTITY_PAINT: String = BASE_URL + "change_quantity_paint"

    const val GET_MATERIALS_BY_TIME: String = BASE_URL + "get_materials"
    const val CHANGE_MATERIAL: String = BASE_URL + "change_material"

    const val GET_OBJECT_BY_TIME: String = BASE_URL + "get_object"


}