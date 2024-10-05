package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.Provides
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel

@Module
class AppModule() {
    @Provides
    fun provideLessonsViewModelFactory(
    ) = LessonsViewModel.Factory(
    )

}
