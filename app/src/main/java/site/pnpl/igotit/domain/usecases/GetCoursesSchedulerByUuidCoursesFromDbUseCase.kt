package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import java.util.UUID

class GetCoursesSchedulerByUuidCoursesFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(uuidCourses: UUID): List<CoursesSchedule> {
        val result = repository.getCoursesSchedulerByUuidCourseFromDb(uuidCourses)
        return result
    }
}