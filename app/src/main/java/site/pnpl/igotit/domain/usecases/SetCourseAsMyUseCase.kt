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

class SetCourseAsMyUseCase(
    private val repository: IClubRepository,
    private val getCourseScheduleByUuidFromDbUseCase: GetCourseScheduleByUuidFromDbUseCase,
    private val createLessonsBySheduleUseCase: CreateLessonsBySheduleUseCase,

) {
    suspend operator fun invoke(id : Int):Boolean {
        return repository.setMyCourse(id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend  operator fun invoke(uuid : UUID):Boolean {
        if (repository.setMyCourse(uuid)) {
            val moscowOffset = ZoneOffset.ofHours(3)
            val courseSchedule = getCourseScheduleByUuidFromDbUseCase(uuid)
            val listDaysOfWeekInMonth = LocalDate.now().toGetDaysOfWeekInMonth(courseSchedule.dayOfWeek)

            val listDateTimes: List<LocalDateTime> = listDaysOfWeekInMonth.map { localDate ->
                localDate.atTime(courseSchedule.startHour.toInt(), courseSchedule.startMinute.toInt(), 0)
            }

            val lessons = listDateTimes.map {
                Lesson(
                    id = -1L,
                    dateMilis = it.toInstant(moscowOffset).toEpochMilli(),
                    uuidSchedule = uuid
                )
            }

            createLessonsBySheduleUseCase(lessons)
            return true
        } else {
            return false
        }
    }
}