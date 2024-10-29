package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class GetCoursesFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(): List<Clubs> {
        val result = repository.getEntities("course")
        return result
    }
}