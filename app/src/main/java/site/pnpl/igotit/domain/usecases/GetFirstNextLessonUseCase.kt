package site.pnpl.igotit.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.Lesson
import java.time.LocalDate
import java.time.ZoneId


class GetFirstNextLessonUseCase(
    private val repository: ILessonRepository,
    private val getCourseScheduleByUuidFromDbUseCase: GetCourseScheduleByUuidFromDbUseCase,
    private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,

) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Lesson? {
        return withContext(Dispatchers.IO) {
            val nowMillis = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val lesson = repository.getFirstNextLesson(nowMillis)
            if (lesson != null) {
                val schedule = getCourseScheduleByUuidFromDbUseCase(lesson.uuidSchedule)
                val course = getCourseClubByUuidDbUseCase(schedule.uuidCourses)
                lesson.title = course.title
                lesson
            } else {
                null
            }
        }
    }
}