package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.Lesson

interface ILessonRepository {
    suspend fun createLessons(lessons: List<Lesson>): Boolean
    suspend fun getFirstNextLesson(nowMilis: Long): Lesson?
}