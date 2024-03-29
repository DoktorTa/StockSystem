package stenograffia.app.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stenograffia.app.data.network.Urls
import stenograffia.app.data.network.data.*

interface StockApi {

    @POST(Urls.GET_PAINTS_BY_TIME)
    suspend fun getAllPaintByTime(@Body request: TimeRequest): Response<ElementPaintResponse>

    @POST(Urls.CHANGE_QUANTITY_PAINT)
    suspend fun changeQuantityPaint(@Body request: ChangeQuantityPaint): Response<ElementPaintResponse>

    @POST(Urls.GET_MATERIALS_BY_TIME)
    suspend fun getMaterial(@Body request: TimeRequest): Response<ElementsMaterialResponse>

    @POST(Urls.CHANGE_MATERIAL)
    suspend fun changeMaterial(@Body request: ChangeMaterialRequest): Response<ElementsMaterialResponse>

}