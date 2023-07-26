package stenograffia.app.domain.model

enum class PaintType(name: String) {
    CANS_DEFAULT("default"),
    CANS_TRANSPARENT("transparent"),
    CANS_FLUORESCENT("fluorescent"),
    CANS_CHROME("chrome"),
    CANS_METALLIC("metallic"),
    CANS_SPECIAL("special"),
    EXTERIOR_PAINT("exterior");

    companion object {
        fun getPaintTypeByName(name: String) : PaintType {
            return when (name) {
                "default" -> CANS_DEFAULT
                "transparent" -> CANS_TRANSPARENT
                "fluorescent" -> CANS_FLUORESCENT
                "chrome" -> CANS_CHROME
                "metallic" -> CANS_METALLIC
                "special" -> CANS_SPECIAL
                "exterior" -> EXTERIOR_PAINT
                else -> CANS_DEFAULT
            }
        }
    }
}
