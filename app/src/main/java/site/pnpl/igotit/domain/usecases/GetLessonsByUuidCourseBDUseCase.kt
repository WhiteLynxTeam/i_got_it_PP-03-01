package site.pnpl.igotit.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.Lesson
import java.util.UUID


class GetLessonsByUuidCourseBDUseCase(
    private val repository: ILessonRepository,
    private val getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    private val getLessonsByListSchedulesBDUseCase: GetLessonsByListSchedulesBDUseCase,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(uuidCourse: UUID): List<Lesson> {
        return withContext(Dispatchers.IO) {
            val shedules = getCoursesSchedulerByUuidCoursesFromDbUseCase(uuidCourse)
            getLessonsByListSchedulesBDUseCase(shedules)
        }
    }
}