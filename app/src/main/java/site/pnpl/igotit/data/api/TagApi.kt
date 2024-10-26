package site.pnpl.igotit.data.api

import retrofit2.http.GET
import site.pnpl.igotit.data.dto.ResponseDto
import site.pnpl.igotit.data.dto.tag.response.TagDtoOut

interface TagApi {
    @GET("tags")
    suspend fun getTags(
    ): Result<ResponseDto<TagDtoOut>>
}
