package site.pnpl.igotit.view.catalogue.clubs.record_club

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.usecases.GetCoursesSchedulerByUuidFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetCourseAsMyUseCase
import site.pnpl.igotit.utils.toGetFirstDayOfWeek
import java.time.LocalDate
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
class RecordClubViewModel(
    private val setCourseAsMyUseCase: SetCourseAsMyUseCase,
    private val getCoursesSchedulerByUuidFromDbUseCase: GetCoursesSchedulerByUuidFromDbUseCase,
) : ViewModel() {
    private var _isMyCourse = MutableSharedFlow<Boolean>()
    val isMyCourse: SharedFlow<Boolean>
        get() = _isMyCourse.asSharedFlow()

    private val _firstDayOfWeek = MutableStateFlow<LocalDate>(LocalDate.now())
    val firstDayOfWeek: StateFlow<LocalDate> = _firstDayOfWeek

    private val _schedule = MutableStateFlow<Pair<LocalDate,List<CoursesSchedule>>>(Pair(LocalDate.now(),emptyList()))
    val schedule: StateFlow<Pair<LocalDate,List<CoursesSchedule>>> = _schedule

    init {
        println("RecordClubViewModel - init - _firstDayOfWeek = ${_firstDayOfWeek.value}")
        println("RecordClubViewModel - init - _schedule = ${_schedule.value}")
        getFirstDayOfWeek(_firstDayOfWeek.value)
    }

    fun getFirstDayOfWeek(date: LocalDate) {
        viewModelScope.launch {
            _firstDayOfWeek.emit(date.toGetFirstDayOfWeek())
        }
    }

    fun setMyCourse(id: Int) {
        viewModelScope.launch {
            _isMyCourse.emit(setCourseAsMyUseCase(id))
        }
    }

    fun getCoursesScheduler(uuid: UUID) {
        viewModelScope.launch {
            val list = getCoursesSchedulerByUuidFromDbUseCase(uuid)
            if (list.isNotEmpty()) _schedule.emit(Pair(_firstDayOfWeek.value,list))
        }
    }

    fun selectGroupe(dayOfWeekRuShort: String?) {
        if (dayOfWeekRuShort != null) {
            val timeScheduler = _schedule.value.second.filter { it.dayOfWeek == dayOfWeekRuShort }
        }
    }

    class Factory(
        private val setCourseAsMyUseCase: SetCourseAsMyUseCase,
        private val getCoursesSchedulerByUuidFromDbUseCase: GetCoursesSchedulerByUuidFromDbUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecordClubViewModel::class.java)) {
                return RecordClubViewModel(
                    setCourseAsMyUseCase = setCourseAsMyUseCase,
                    getCoursesSchedulerByUuidFromDbUseCase = getCoursesSchedulerByUuidFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}