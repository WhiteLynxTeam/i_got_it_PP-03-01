package site.pnpl.igotit.view.catalogue.courses.record_course

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
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.usecases.GetCoursesSchedulerByUuidCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetCourseAsMyUseCase
import site.pnpl.igotit.utils.toGetFirstDayOfWeek
import java.time.LocalDate
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
class RecordCourseViewModel(
    private val setCourseAsMyUseCase: SetCourseAsMyUseCase,
    private val getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    ) :ViewModel() {
    var uuidTimeSchedule: UUID? = null

    private var _isMyCourse = MutableSharedFlow<Boolean>()
    val isMyCourse: SharedFlow<Boolean>
        get() = _isMyCourse.asSharedFlow()

    private val _firstDayOfWeek = MutableStateFlow<LocalDate>(LocalDate.now())
    val firstDayOfWeek: StateFlow<LocalDate> = _firstDayOfWeek

    private val _schedule =
        MutableStateFlow<Pair<LocalDate, List<CoursesSchedule>>>(Pair(LocalDate.now(), emptyList()))
    val schedule: StateFlow<Pair<LocalDate, List<CoursesSchedule>>> = _schedule

    private val _timeSchedule = MutableStateFlow<List<CoursesSchedule>>(emptyList())
    val timeSchedule: StateFlow<List<CoursesSchedule>> = _timeSchedule

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

    fun setMyCourse() {
        viewModelScope.launch {
            if (uuidTimeSchedule != null)
                _isMyCourse.emit(setCourseAsMyUseCase(uuidTimeSchedule!!))

//            _isMyCourse.emit(setCourseAsMyUseCase())
//            _isMyCourse.emit(setCourseAsMyUseCase())
        }
    }

    fun getCoursesScheduler(uuid: UUID) {
        viewModelScope.launch {
            val list = getCoursesSchedulerByUuidCoursesFromDbUseCase(uuid)
            if (list.isNotEmpty()) _schedule.emit(Pair(_firstDayOfWeek.value, list))
        }
    }

    fun selectGroupe(dayOfWeekRuShort: String?) {
        if (dayOfWeekRuShort != null) {
            viewModelScope.launch {
                val list = _schedule.value.second.filter { it.dayOfWeek == dayOfWeekRuShort }
                if (list.isNotEmpty()) _timeSchedule.emit(list)
            }
        }
    }

    class Factory(
        private val setCourseAsMyUseCase: SetCourseAsMyUseCase,
        private val getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecordCourseViewModel::class.java)) {
                return RecordCourseViewModel(
                    setCourseAsMyUseCase = setCourseAsMyUseCase,
                    getCoursesSchedulerByUuidCoursesFromDbUseCase = getCoursesSchedulerByUuidCoursesFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}