package stenograffia.app.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)