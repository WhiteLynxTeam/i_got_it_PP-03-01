package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import site.pnpl.igotit.view.MainActivity

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity


}