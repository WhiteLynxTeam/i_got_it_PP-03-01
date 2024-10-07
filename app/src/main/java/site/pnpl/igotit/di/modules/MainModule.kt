package site.pnpl.igotit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import site.pnpl.igotit.view.MainActivity
import site.pnpl.igotit.view.auth.AuthFragment
import site.pnpl.igotit.view.catalogue.CatalogueFragment
import site.pnpl.igotit.view.catalogue.clubs.ClubsFragment
import site.pnpl.igotit.view.catalogue.courses.CoursesCatalogueFragment
import site.pnpl.igotit.view.catalogue.individually.IndividuallyFragment
import site.pnpl.igotit.view.courses.CoursesFragment
import site.pnpl.igotit.view.courses.calendar.CalendarFragment
import site.pnpl.igotit.view.courses.lessons.LessonsFragment
import site.pnpl.igotit.view.courses.remove.RemoveCourseFragment
import site.pnpl.igotit.view.home.HomeFragment
import site.pnpl.igotit.view.intro.IntroFragment
import site.pnpl.igotit.view.profile.ProfileFragment
import site.pnpl.igotit.view.profile.about.AboutFragment
import site.pnpl.igotit.view.profile.data.UserDataFragment
import site.pnpl.igotit.view.profile.setting.SettingsFragment
import site.pnpl.igotit.view.reg.RegFragment
import site.pnpl.igotit.view.restore.RestoreFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindIntroFragment(): IntroFragment

    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    fun bindRegFragment(): RegFragment

    @ContributesAndroidInjector
    fun bindRestoreFragment(): RestoreFragment

    @ContributesAndroidInjector
    fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun bindCatalogueFragment(): CatalogueFragment

    @ContributesAndroidInjector
    fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun bindCoursesFragment(): CoursesFragment

    @ContributesAndroidInjector
    fun bindLessonsFragment(): LessonsFragment

    @ContributesAndroidInjector
    fun bindCalendarFragment(): CalendarFragment

    @ContributesAndroidInjector
    fun bindRemoveCourseFragment(): RemoveCourseFragment

    @ContributesAndroidInjector
    fun bindUserDataFragment(): UserDataFragment

    @ContributesAndroidInjector
    fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    fun bindAboutFragment(): AboutFragment

    @ContributesAndroidInjector
    fun bindClubsFragment(): ClubsFragment

    @ContributesAndroidInjector
    fun bindIndividuallyFragment(): IndividuallyFragment

    @ContributesAndroidInjector
    fun bindCoursesCatalogueFragment(): CoursesCatalogueFragment

}