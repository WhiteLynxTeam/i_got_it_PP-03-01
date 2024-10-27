package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.repository.ClubRepository
import site.pnpl.igotit.domain.irepository.IClubRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideClubRepository(
        clubApi: ClubApi,
        clubsDao: ClubsDao
    ) : IClubRepository {
        return ClubRepository(
            clubApi = clubApi,
            clubsDao = clubsDao
        )
    }
}