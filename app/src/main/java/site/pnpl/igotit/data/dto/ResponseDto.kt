package site.pnpl.igotit.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<T>(
    @SerialName("root") val root: ArrayList<T>,
 )

