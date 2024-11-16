package site.pnpl.igotit.di.modules

import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.domain.usecases.FilDbWithSampleDataUseCase
import site.pnpl.igotit.domain.usecases.GetClubByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetClubsFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseClubByUuidDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseScheduleByUuidFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesSchedulerByUuidCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetFirstNextLessonUseCase
import site.pnpl.igotit.domain.usecases.GetLessonsByUuidCourseBDUseCase
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetClubsFavoriteUseCase
import site.pnpl.igotit.domain.usecases.SetCourseAsMyUseCase
import site.pnpl.igotit.domain.usecases.SetCoursesFavoriteUseCase
import site.pnpl.igotit.view.auth.AuthViewModel
import site.pnpl.igotit.view.catalogue.CatalogueViewModel
import site.pnpl.igotit.view.catalogue.clubs.ClubsViewModel
import site.pnpl.igotit.view.catalogue.clubs.about_club.AboutClubViewModel
import site.pnpl.igotit.view.catalogue.clubs.record_club.RecordClubViewModel
import site.pnpl.igotit.view.catalogue.courses.CoursesCatalogueViewModel
import site.pnpl.igotit.view.catalogue.courses.about_course.AboutCourseViewModel
import site.pnpl.igotit.view.catalogue.courses.record_course.RecordCourseViewModel
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel
import site.pnpl.igotit.view.home.HomeViewModel

@Module
class AppModule() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideHomeViewModelFactory(
        getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase,
        getFirstNextLessonUseCase: GetFirstNextLessonUseCase,
    ) = HomeViewModel.Factory(
        getMyCoursesFromDbUseCase = getMyCoursesFromDbUseCase,
        getFirstNextLessonUseCase = getFirstNextLessonUseCase,

    )

    @Provides
    fun provideLessonsViewModelFactory(
        getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
        getLessonsByUuidCourseBDUseCase: GetLessonsByUuidCourseBDUseCase,

        ) = LessonsViewModel.Factory(
        getCourseClubByUuidDbUseCase = getCourseClubByUuidDbUseCase,
        getLessonsByUuidCourseBDUseCase = getLessonsByUuidCourseBDUseCase,
    )

    @Provides
    fun provideCoursesCatalogueViewModelFactory(
        getCoursesFromDbUseCase: GetCoursesFromDbUseCase
    ) = CoursesCatalogueViewModel.Factory(
        getCoursesFromDbUseCase = getCoursesFromDbUseCase
    )

    @Provides
    fun provideClubsViewModelFactory(
        getClubsFromDbUseCase: GetClubsFromDbUseCase
    ) = ClubsViewModel.Factory(
        getClubsFromDbUseCase = getClubsFromDbUseCase
    )

    @Provides
    fun provideCatalogueViewModelFactory(
        downloadCoursesUseCase: DownloadCoursesUseCase
    ) = CatalogueViewModel.Factory(
        downloadCoursesUseCase = downloadCoursesUseCase
    )

    @Provides
    fun provideAuthViewModelFactory(
        filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase
    ) = AuthViewModel.Factory(
        filDbWithSampleDataUseCase = filDbWithSampleDataUseCase
    )
    @Provides
    fun provideAboutCourseViewModel(
        setCoursesFavoriteUseCase: SetCoursesFavoriteUseCase,
        getCourseByIdFromDbUseCase: GetCourseByIdFromDbUseCase
    ) = AboutCourseViewModel.Factory(
        setCoursesFavoriteUseCase = setCoursesFavoriteUseCase,
        getCourseByIdFromDbUseCase = getCourseByIdFromDbUseCase
    )

    @Provides
    fun provideAboutClubViewModel(
        setClubsFavoriteUseCase: SetClubsFavoriteUseCase,
        getClubByIdFromDbUseCase: GetClubByIdFromDbUseCase
    ) = AboutClubViewModel.Factory(
        setClubsFavoriteUseCase = setClubsFavoriteUseCase,
        getClubByIdFromDbUseCase = getClubByIdFromDbUseCase
    )

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideRecordCourseViewModel(
        setCourseAsMyUseCase: SetCourseAsMyUseCase,
        getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    ) = RecordCourseViewModel.Factory(
        setCourseAsMyUseCase = setCourseAsMyUseCase,
        getCoursesSchedulerByUuidCoursesFromDbUseCase = getCoursesSchedulerByUuidCoursesFromDbUseCase,
    )

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideRecordClubViewModel(
        setCourseAsMyUseCase: SetCourseAsMyUseCase,
        getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
    ) = RecordClubViewModel.Factory(
        setCourseAsMyUseCase = setCourseAsMyUseCase,
        getCoursesSchedulerByUuidCoursesFromDbUseCase = getCoursesSchedulerByUuidCoursesFromDbUseCase,
    )

}
