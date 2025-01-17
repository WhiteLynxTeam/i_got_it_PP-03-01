package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.models.Lesson

interface ILessonRepository {
    suspend fun createLessons(lessons: List<Lesson>): Boolean
    suspend fun delLessons(lessons: List<CoursesSchedule>): Boolean
    suspend fun getFirstNextLesson(nowMilis: Long): Lesson?
    suspend fun getLessonsBySchedules(schedules: List<CoursesSchedule>): List<Lesson>
    suspend fun getAllLessons(): List<Lesson>
}