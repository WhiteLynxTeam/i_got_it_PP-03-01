package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import java.util.UUID

class GetCoursesSchedulerByUuidFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(uuid: UUID): List<CoursesSchedule> {
        val result = repository.getCoursesSchedulerByUuidFromDb(uuid)
        return result
    }
}