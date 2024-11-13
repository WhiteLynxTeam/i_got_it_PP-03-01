package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs
import java.util.UUID

class GetCourseClubByUuidDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(uuid: UUID): Clubs {
        val result = repository.getEntityByUuid(uuid)
        return result
    }
}