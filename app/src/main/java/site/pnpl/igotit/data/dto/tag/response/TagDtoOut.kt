package site.pnpl.igotit.data.dto.tag.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDtoOut(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
 )