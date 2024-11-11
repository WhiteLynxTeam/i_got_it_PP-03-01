package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.domain.usecases.FilDbWithSampleDataUseCase
import site.pnpl.igotit.domain.usecases.GetClubByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetClubsFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesSchedulerByUuidFromDbUseCase
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbByFlagUseCase
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbByListUuidUseCase
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetClubsFavoriteUseCase
import site.pnpl.igotit.domain.usecases.SetCourseAsMyUseCase
import site.pnpl.igotit.domain.usecases.SetCoursesFavoriteUseCase
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideDownloadCoursesUseCase(
        repository: IClubRepository,
    ): DownloadCoursesUseCase {
        return DownloadCoursesUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideFilDbWithSampleDataUseCase(
        repository: IClubRepository,
    ): FilDbWithSampleDataUseCase {
        return FilDbWithSampleDataUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetClubsFromDbUseCase(
        repository: IClubRepository,
    ): GetClubsFromDbUseCase {
        return GetClubsFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCoursesFromDbUseCase(
        repository: IClubRepository,
    ): GetCoursesFromDbUseCase {
        return GetCoursesFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideSetCoursesFavoriteUseCase(
        repository: IClubRepository,
    ): SetCoursesFavoriteUseCase {
        return SetCoursesFavoriteUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCourseByIdFromDbUseCase(
        repository: IClubRepository,
    ): GetCourseByIdFromDbUseCase {
        return GetCourseByIdFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetClubByIdFromDbUseCase(
        repository: IClubRepository,
    ): GetClubByIdFromDbUseCase {
        return GetClubByIdFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideSetClubsFavoriteUseCase(
        repository: IClubRepository,
    ): SetClubsFavoriteUseCase {
        return SetClubsFavoriteUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideSetCourseAsMyUseCase(
        repository: IClubRepository,
    ): SetCourseAsMyUseCase {
        return SetCourseAsMyUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetMyCoursesFromDbUseCase(
        repository: IClubRepository,
        getMyCoursesFromDbByListUuidUseCase: GetMyCoursesFromDbByListUuidUseCase,
    ): GetMyCoursesFromDbUseCase {
        return GetMyCoursesFromDbUseCase(
            repository = repository,
            getMyCoursesFromDbByListUuidUseCase = getMyCoursesFromDbByListUuidUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideGetMyCoursesFromDbByListUuidUseCase(
        repository: IClubRepository,

    ): GetMyCoursesFromDbByListUuidUseCase {
        return GetMyCoursesFromDbByListUuidUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetGetMyCoursesFromDbByFlagUseCase(
        repository: IClubRepository,

    ): GetMyCoursesFromDbByFlagUseCase {
        return GetMyCoursesFromDbByFlagUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCoursesSchedulerByUuidFromDbUseCase(
        repository: IClubRepository,
    ): GetCoursesSchedulerByUuidFromDbUseCase {
        return GetCoursesSchedulerByUuidFromDbUseCase(
            repository = repository,
        )
    }

}