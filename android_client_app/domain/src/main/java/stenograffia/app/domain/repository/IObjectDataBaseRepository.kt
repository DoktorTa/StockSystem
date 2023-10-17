package stenograffia.app.domain.repository

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.ObjectModel

interface IObjectDataBaseRepository {

    fun getAllObjects(): Flow<List<ObjectModel>>

    fun addAllObjects(objectModels: List<ObjectModel>)

    fun getLocations() : MutableSet<String>

}