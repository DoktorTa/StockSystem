package stenograffia.app.domain.model

data class MaterialModel(
    val id: Int,
    val type: String,
    val timeLabel: Int,
    val description: String,
    val unique: Boolean,
    val location: String,
    val quantityInStorage: Int
) {}
