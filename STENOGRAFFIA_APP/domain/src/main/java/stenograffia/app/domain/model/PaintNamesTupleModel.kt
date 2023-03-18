package stenograffia.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaintNamesTupleModel(
    val nameCreator: String,
    val nameLine: String
): Parcelable {
    override fun toString(): String {
        return "$nameCreator - $nameLine"
    }
}