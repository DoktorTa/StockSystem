package stenograffia.app.localdata.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

class PaintTypeConverter {

    @TypeConverter
    fun toSimilarColors(value: String): List<List<Int>> {
        val type: Type = object : TypeToken<List<List<Int>>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSimilarColors(list: List<List<Int>>): String {
        return Gson().toJson(list)
    }
}