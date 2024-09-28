package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import site.pnpl.igotit.view.MainActivity
import site.pnpl.igotit.view.auth.AuthFragment
import site.pnpl.igotit.view.intro.IntroFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    fun bindIntroFragment(): IntroFragment
    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment


}