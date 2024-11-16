package site.pnpl.igotit.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.models.Lesson

class GetLessonsByListSchedulesBDUseCase(
    private val repository: ILessonRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(schedules: List<CoursesSchedule>): List<Lesson> {
        return withContext(Dispatchers.IO) {
            repository.getLessonsBySchedules(schedules)
        }
    }
}