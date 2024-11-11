package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs
import java.util.UUID

class GetMyCoursesFromDbByListUuidUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(listMyCourses: List<UUID>): List<Clubs> {
        val result = repository.getEntities(listMyCourses)
        return result
    }
}