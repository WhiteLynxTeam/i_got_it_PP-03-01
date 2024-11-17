package site.pnpl.igotit.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.utils.toGetDaysOfWeekInMonth
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

class DelMyCourseClubDbUseCase(
    private val repository: IClubRepository,
    private val getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    private val deleteLessonsByShedulesUseCase: DeleteLessonsByShedulesUseCase,

) {
    suspend  operator fun invoke(uuid : UUID):Boolean {
        val schedules = getCoursesSchedulerByUuidCoursesFromDbUseCase(uuid)
        if (repository.delMyCourse(schedules)) {
            deleteLessonsByShedulesUseCase(schedules)
            return true
        } else {
            return false
        }
    }
}