package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.domain.usecases.FilDbWithSampleDataUseCase
import site.pnpl.igotit.domain.usecases.GetClubByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetClubsFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesFromDbUseCase
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
import site.pnpl.igotit.view.catalogue.courses.record.RecordViewModel
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel
import site.pnpl.igotit.view.home.HomeViewModel

@Module
class AppModule() {
    @Provides
    fun provideHomeViewModelFactory(
        getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase
    ) = HomeViewModel.Factory(
        getMyCoursesFromDbUseCase = getMyCoursesFromDbUseCase
    )

    @Provides
    fun provideLessonsViewModelFactory(
    ) = LessonsViewModel.Factory(
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

    @Provides
    fun provideRecordViewModel(
        setCourseAsMyUseCase: SetCourseAsMyUseCase,
    ) = RecordViewModel.Factory(
        setCourseAsMyUseCase = setCourseAsMyUseCase,
    )

    @Provides
    fun provideRecordClubViewModel(
        setCourseAsMyUseCase: SetCourseAsMyUseCase,
    ) = RecordClubViewModel.Factory(
        setCourseAsMyUseCase = setCourseAsMyUseCase,
    )

}
