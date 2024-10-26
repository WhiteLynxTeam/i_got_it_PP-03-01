package site.pnpl.igotit.data.dto.club.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ClubDtoOut(
    @SerialName("clubName") val clubName: String,
    @SerialName("type") val type: String,
    @SerialName("level") val level: String,
    @SerialName("labelIds") val labelIds: List<UUID>,
    @SerialName("description") val description: String,
    @SerialName("length") val length: String,
    @SerialName("frequency") val frequency: String,
 )