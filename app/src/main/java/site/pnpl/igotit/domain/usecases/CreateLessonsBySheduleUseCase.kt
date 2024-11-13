package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.Lesson

class CreateLessonsBySheduleUseCase(
    private val repository: ILessonRepository,
) {
    suspend operator fun invoke(lessons: List<Lesson>): Boolean {
        val result = repository.createLessons(lessons)
        return result
    }
}