package stenograffia.app.domain.model

enum class UserRole(val level: Int) {
    ADMIN(0),
    ORGANIZER(1),
    STOCK(2),
    ARTIST(3),
    VOLUNTEER(4),
    PRESS(5),
    GUIDE(6);

    companion object {
        fun getUserRoleByLevel(level: Int) : UserRole {
            return when (level) {
                0 -> ADMIN
                1 -> ORGANIZER
                2 -> STOCK
                3 -> ARTIST
                4 -> VOLUNTEER
                5 -> PRESS
                6 -> GUIDE
                else -> GUIDE
            }
        }
    }
}
