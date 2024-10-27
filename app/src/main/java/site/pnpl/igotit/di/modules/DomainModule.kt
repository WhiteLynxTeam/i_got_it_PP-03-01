package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import javax.inject.Singleton

@Module
class DomainModule {

//
//    @Singleton
//    @Provides
//    fun provideDownloadCoursesUseCase(repository: IClubRepository,
//    ): DownloadCoursesUseCase {
//        return DownloadCoursesUseCase(repository = repository
//        )
//    }


    @Singleton
    @Provides
    fun provideDownloadCoursesUseCase(
        repository: IClubRepository,
    ): DownloadCoursesUseCase {
        return DownloadCoursesUseCase(
            repository = repository,
        )
    }

}