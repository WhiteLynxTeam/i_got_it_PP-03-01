package site.pnpl.igotit.di

import android.content.Context
import site.pnpl.igotit.di.modules.DomainModule
import site.pnpl.igotit.di.modules.MainModule
import site.pnpl.igotit.di.modules.MappersModule
import site.pnpl.igotit.di.modules.RemoteModule
import site.pnpl.igotit.di.modules.DataModule
import site.pnpl.igotit.di.modules.DatabaseModule
import site.pnpl.igotit.di.modules.AppModule
import site.pnpl.igotit.di.modules.SharedPreferencesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import site.pnpl.igotit.App
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        MappersModule::class,
        RemoteModule::class,
        SharedPreferencesModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}

