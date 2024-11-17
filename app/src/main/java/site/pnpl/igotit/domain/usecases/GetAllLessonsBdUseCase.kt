package site.pnpl.igotit.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.Lesson

class GetAllLessonsBdUseCase(
    private val repository: ILessonRepository,
    private val setTitleCourseForLessonsUseCase: SetTitleCourseForLessonsUseCase,
) {
    suspend operator fun invoke(): List<Lesson> {
        return withContext(Dispatchers.IO) {
            val lessons = repository.getAllLessons()
            setTitleCourseForLessonsUseCase(lessons)

            println("GetAllLessonsBdUseCase - lessons = $lessons")

            lessons
        }
    }
}