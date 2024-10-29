package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.domain.usecases.FilDbWithSampleDataUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesFromDbUseCase
import site.pnpl.igotit.view.auth.AuthViewModel
import site.pnpl.igotit.view.catalogue.CatalogueViewModel
import site.pnpl.igotit.view.catalogue.courses.CoursesCatalogueViewModel
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel

@Module
class AppModule() {
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

}
