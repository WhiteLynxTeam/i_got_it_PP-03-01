package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.models.Lesson

class DeleteLessonsByShedulesUseCase(
    private val repository: ILessonRepository,
) {
    suspend operator fun invoke(schedule: List<CoursesSchedule>): Boolean {
        val result = repository.delLessons(schedule)
        return result
    }
}