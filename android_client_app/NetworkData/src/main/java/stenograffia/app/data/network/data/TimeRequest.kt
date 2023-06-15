package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

class TimeRequest(
    @SerializedName("timeLabel")
    val timeLabel: Int
) {}