package stenograffia.app.localdata.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ObjectTypeConverter {

    @TypeConverter
    fun toList(value: String): List<Int> {
        val type: Type = object : TypeToken<List<List<Int>>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return Gson().toJson(list)
    }
}
