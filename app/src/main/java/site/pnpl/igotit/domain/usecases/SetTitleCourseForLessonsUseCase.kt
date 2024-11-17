package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Lesson

class SetTitleCourseForLessonsUseCase(
    private val repository: IClubRepository,
    private val getCourseScheduleByUuidFromDbUseCase: GetCourseScheduleByUuidFromDbUseCase,
    private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,

    ) {
    suspend operator fun invoke(lessons: List<Lesson>): Boolean {
        lessons.forEach {
            val schedule = getCourseScheduleByUuidFromDbUseCase(it.uuidSchedule)
            val course = getCourseClubByUuidDbUseCase(schedule.uuidCourses)
            println("SetTitleCourseForLessonsUseCase - lesson = $it")
            println("SetTitleCourseForLessonsUseCase - course.title = ${course.title}")
            it.title = course.title
        }
        return true
    }
}