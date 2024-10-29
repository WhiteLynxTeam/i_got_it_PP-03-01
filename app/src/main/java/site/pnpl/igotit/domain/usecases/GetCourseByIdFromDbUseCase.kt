package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class GetCourseByIdFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(id: Int): Clubs {
        val result = repository.getEntityById("course", id)
        return result
    }
}