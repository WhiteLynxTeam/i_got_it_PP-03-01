package site.pnpl.igotit.data.api

import retrofit2.http.GET
import site.pnpl.igotit.data.dto.ResponseDto
import site.pnpl.igotit.data.dto.club.response.ClubDtoOut
import site.pnpl.igotit.data.dto.tag.response.TagDtoOut

interface ClubApi {
    @GET("tags")
    suspend fun getClubs(
    ): Result<ResponseDto<ClubDtoOut>>
}
