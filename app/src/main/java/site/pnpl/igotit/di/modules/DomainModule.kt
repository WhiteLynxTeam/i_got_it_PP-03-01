package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.usecases.CreateLessonsBySheduleUseCase
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
import site.pnpl.igotit.domain.usecases.GetLessonsByListSchedulesBDUseCase
import site.pnpl.igotit.domain.usecases.GetLessonsByUuidCourseBDUseCase
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
        getCourseScheduleByUuidFromDbUseCase: GetCourseScheduleByUuidFromDbUseCase,
        createLessonsBySheduleUseCase: CreateLessonsBySheduleUseCase,
    ): SetCourseAsMyUseCase {
        return SetCourseAsMyUseCase(
            repository = repository,
            getCourseScheduleByUuidFromDbUseCase = getCourseScheduleByUuidFromDbUseCase,
            createLessonsBySheduleUseCase = createLessonsBySheduleUseCase,)
    }

    @Singleton
    @Provides
    fun provideGetMyCoursesFromDbUseCase(
        repository: IClubRepository,
        getMyCoursesFromDbByListUuidUseCase: GetMyCoursesFromDbByListUuidUseCase,
        getMyCoursesFromDbByFlagUseCase: GetMyCoursesFromDbByFlagUseCase,

    ): GetMyCoursesFromDbUseCase {
        return GetMyCoursesFromDbUseCase(
            repository = repository,
            getMyCoursesFromDbByListUuidUseCase = getMyCoursesFromDbByListUuidUseCase,
            getMyCoursesFromDbByFlagUseCase = getMyCoursesFromDbByFlagUseCase,
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
    fun provideGetCoursesSchedulerByUuidCoursesFromDbUseCase(
        repository: IClubRepository,
    ): GetCoursesSchedulerByUuidCoursesFromDbUseCase {
        return GetCoursesSchedulerByUuidCoursesFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCourseScheduleByUuidFromDbUseCase(
        repository: IClubRepository,
    ): GetCourseScheduleByUuidFromDbUseCase {
        return GetCourseScheduleByUuidFromDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCourseClubByUuidDbUseCase(
        repository: IClubRepository,
    ): GetCourseClubByUuidDbUseCase {
        return GetCourseClubByUuidDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideCreateLessonsBySheduleUseCase(
        repository: ILessonRepository,
    ): CreateLessonsBySheduleUseCase {
        return CreateLessonsBySheduleUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetFirstNextLessonUseCase(
        repository: ILessonRepository,
        getCourseScheduleByUuidFromDbUseCase: GetCourseScheduleByUuidFromDbUseCase,
        getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
    ): GetFirstNextLessonUseCase {
        return GetFirstNextLessonUseCase(
            repository = repository,
            getCourseScheduleByUuidFromDbUseCase = getCourseScheduleByUuidFromDbUseCase,
            getCourseClubByUuidDbUseCase = getCourseClubByUuidDbUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideGetLessonsByListSchedulesBDUseCase(
        repository: ILessonRepository,
    ): GetLessonsByListSchedulesBDUseCase {
        return GetLessonsByListSchedulesBDUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetLessonsByUuidCourseBDUseCase(
        repository: ILessonRepository,
        getCoursesSchedulerByUuidCoursesFromDbUseCase: GetCoursesSchedulerByUuidCoursesFromDbUseCase,
        getLessonsByListSchedulesBDUseCase: GetLessonsByListSchedulesBDUseCase,
    ): GetLessonsByUuidCourseBDUseCase {
        return GetLessonsByUuidCourseBDUseCase(
            repository = repository,
            getCoursesSchedulerByUuidCoursesFromDbUseCase = getCoursesSchedulerByUuidCoursesFromDbUseCase,
            getLessonsByListSchedulesBDUseCase = getLessonsByListSchedulesBDUseCase,
        )
    }

}