package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.domain.model.UserModel
import stenograffia.app.domain.model.UserRole

class GetUserResponse(

    @SerializedName("user_name")
    val username: String,

    @SerializedName("user_role")
    val userRole: Int

) {}

fun GetUserResponse.toUserModel() : UserModel {
    return UserModel(
        username = username,
        userRole = UserRole.getUserRoleByLevel(userRole)
    )
}