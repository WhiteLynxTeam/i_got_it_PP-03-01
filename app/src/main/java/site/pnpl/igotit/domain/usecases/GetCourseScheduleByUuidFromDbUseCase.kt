package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import java.util.UUID

class GetCourseScheduleByUuidFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(uuid: UUID): CoursesSchedule {
        val result = repository.getCourseScheduleByUuidFromDb(uuid)
        return result
    }
}