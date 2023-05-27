package stenograffia.app.domain.model

data class UserModel (
    val userName: String,
    val userGroup: String
)

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)