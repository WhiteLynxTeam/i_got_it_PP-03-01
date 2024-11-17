package site.pnpl.igotit.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.data.dbo.dao.LessonsDao
import site.pnpl.igotit.data.dbo.entity.LessonEntity
import site.pnpl.igotit.domain.irepository.ILessonRepository
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.utils.randomUuid
import site.pnpl.igotit.utils.toDayOnly
import java.time.Instant
import java.time.ZoneId
import java.util.UUID

class LessonRepository(
    private val lessonsDao: LessonsDao,
) : ILessonRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createLessons(lessons: List<Lesson>): Boolean {
        val lessonsDb = mapperLessonsToLessonsEntity(lessons)
        lessonsDb.randomUuid()
        withContext(Dispatchers.IO) {
            lessonsDao.insertAll(lessonsDb)
        }
        return true
    }

    override suspend fun delLessons(schedules: List<CoursesSchedule>): Boolean {
        withContext(Dispatchers.IO) {
            lessonsDao.delLessons(mapperListCoursesScheduleToListUUID(schedules))
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getFirstNextLesson(nowMilis: Long): Lesson? {
        val lessons = withContext(Dispatchers.IO) {
            lessonsDao.getFirstNextLesson(nowMilis)
        }
        return if (lessons.isNotEmpty()) {
            mapperLessonsEntityToLessons(lessons)[0]
        } else {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getLessonsBySchedules(schedules: List<CoursesSchedule>): List<Lesson> {
        val lessons = withContext(Dispatchers.IO) {
            lessonsDao.getLessonsBySchedules(mapperSchedulesToListUUID(schedules))
        }
        return mapperLessonsEntityToLessons(lessons)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllLessons(): List<Lesson> {
        val lessons = withContext(Dispatchers.IO) {
            lessonsDao.getAllLessons()
        }
        return mapperLessonsEntityToLessons(lessons)
    }

    private fun mapperSchedulesToListUUID(
        schedules: List<CoursesSchedule>
    ): List<UUID> {
        return schedules.map {
            it.uuid
        }
    }

    private fun mapperListCoursesScheduleToListUUID(
        schedules: List<CoursesSchedule>
    ): List<UUID> {
        return schedules.map {
            it.uuid
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapperLessonsEntityToLessons(
        lessons: List<LessonEntity>
    ): List<Lesson> {
        return lessons.map {
            Lesson(
                uuid = it.uuid,
                uuidSchedule = it.uuidSchedule
                    ?: UUID.fromString("00000000-0000-0000-0000-000000000000"),
                dateMilis = it.dateMilis,
                isActive = it.dateMilis > System.currentTimeMillis()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapperLessonsToLessonsEntity(
        lessons: List<Lesson>
    ): List<LessonEntity> {
        return lessons.map {
            LessonEntity(
                uuidSchedule = it.uuidSchedule,
                dateMilis = it.dateMilis,
                day = Instant.ofEpochMilli(it.dateMilis).atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .toDayOnly(),
                month = Instant.ofEpochMilli(it.dateMilis).atZone(ZoneId.systemDefault())
                    .toLocalDate().monthValue.toString(),
                year = Instant.ofEpochMilli(it.dateMilis).atZone(ZoneId.systemDefault())
                    .toLocalDate().year.toString(),
            )
        }
    }

}