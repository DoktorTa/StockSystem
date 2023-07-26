package stenograffia.app.localdata.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stenograffia.app.domain.model.ObjectModel
import stenograffia.app.domain.repository.IObjectDataBaseRepository
import stenograffia.app.localdata.database.dao.ObjectsDao
import stenograffia.app.localdata.database.model.fromObjectEntity
import stenograffia.app.localdata.database.model.toObjectModel
import javax.inject.Inject

class ObjectsDataBaseRepositoryImpl @Inject constructor(
    private val objectsApi: ObjectsDao
) : IObjectDataBaseRepository {

    override fun getAllObjects(): Flow<List<ObjectModel>> {
        return objectsApi.getAllObjects().map { it.map { it.toObjectModel() } }

    }

    override fun addAllObjects(objectModels: List<ObjectModel>) {
        objectsApi.addAllObjects(objectModels.map { it.fromObjectEntity() })
    }

    override fun getLocations(): MutableSet<String> {
        return objectsApi.getLocations().toMutableSet()
    }

}