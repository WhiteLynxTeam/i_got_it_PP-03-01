package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.view.catalogue.CatalogueViewModel
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel

@Module
class AppModule() {
    @Provides
    fun provideLessonsViewModelFactory(
    ) = LessonsViewModel.Factory(
    )

    @Provides
    fun provideCatalogueViewModelFactory(
        downloadCoursesUseCase: DownloadCoursesUseCase
    ) = CatalogueViewModel.Factory(
        downloadCoursesUseCase = downloadCoursesUseCase
    )

}
