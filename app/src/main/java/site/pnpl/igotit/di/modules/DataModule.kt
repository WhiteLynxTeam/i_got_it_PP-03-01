package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.api.TagApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.LessonsDao
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.data.repository.ClubRepository
import site.pnpl.igotit.data.repository.LessonRepository
import site.pnpl.igotit.data.repository.TagRepository
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.irepository.ITagRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideClubRepository(
        clubApi: ClubApi,
        clubsDao: ClubsDao,
       lessonsDao: LessonsDao
    ) : IClubRepository {
        return ClubRepository(
            clubApi = clubApi,
            clubsDao = clubsDao,
            lessonsDao = lessonsDao

        )
    }

    @Provides
    @Singleton
    fun provideTagRepository(
        tagApi: TagApi,
        tagsDao: TagsDao
    ) : ITagRepository {
        return TagRepository(
            tagApi = tagApi,
            tagsDao = tagsDao
        )
    }

    @Provides
    @Singleton
    fun provideLessonRepository(
        lessonsDao: LessonsDao
    ) : ILessonRepository {
        return LessonRepository(
            lessonsDao = lessonsDao
        )
    }
}