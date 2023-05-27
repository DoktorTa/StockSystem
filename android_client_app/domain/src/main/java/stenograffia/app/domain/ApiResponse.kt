package stenograffia.app.domain

sealed class ApiResponse<T>(
    data: T? = null,
    exception: Exception? = null
) {
    class Success<T>(val data: T) : ApiResponse<T>(data, null)
    class Error<T>(exception: Exception) : ApiResponse<T>(null, exception)
    class Unauthorized<T>() : ApiResponse<T>(null, null)
    class ServerDisconnect<T>() : ApiResponse<T>(null, null)
}