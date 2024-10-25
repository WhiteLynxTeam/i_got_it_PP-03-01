package site.pnpl.igotit.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import site.pnpl.igotit.data.dbo.db.IgotitDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDb(context: Context) = Room.databaseBuilder(
        context, IgotitDatabase::class.java, "test_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideClubsDao(context: Context) = provideDb(context).clubsDao()

    @Singleton
    @Provides
    fun provideCursesDao(context: Context) = provideDb(context).cursesDao()

    @Singleton
    @Provides
    fun provideTagsDao(context: Context) = provideDb(context).tagsDao()
}

