package site.pnpl.igotit.domain

import site.pnpl.igotit.data.dbo.entity.ClubsSample
import site.pnpl.igotit.data.dbo.entity.CoursesScheduleEntity

/**
 * APP CONFIG
 */
const val PHONE_MASK = "+7 ([000]) [000]-[00]-[00]"
const val RUS_PHONE_NUMBER_LENGTH = 11

/**
 * COMMON
 */
const val EMPTY_STRING = ""

/**
 * REMOTE SETTINGS
 */
const val BASE_URL = "http://vm4.mogilevich.net:8080/"

/**
 * BUNDLE KEYS
 */
const val TITLE = "title"
const val ID = "id"

/**
 * DATE PATTERNS
 */
const val DATE_PATTERN_DEFAULT = "dd.MM.yyyy"
const val DATE_PATTERN_WITH_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_WITHOUT_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH = "dd MMMM yyyy"
const val DATE_PATTERN_WEEK_DAY = "EE"
const val DATE_PATTERN_DAY_ONLY = "dd"
const val DATE_PATTERN_WITHOUT_YEAR = "dd MMMM, EEEE"

/**
 * SAMPLE DATE FOR DATABASE
 */
val sampleListOfClubs = listOf(
    ClubsSample(
        type = "club",
        clubName = "Разговорный клуб Extra",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "ср",
                startHour = "13",
                startMinute = "00",
                endHour = "14",
                endMinute = "00",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "ср",
                startHour = "19",
                startMinute = "00",
                endHour = "20",
                endMinute = "00",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "сб",
                startHour = "13",
                startMinute = "00",
                endHour = "14",
                endMinute = "00",
                type = "выходного дня",
                shortType = "вых.дн",
            ),
        )
    ),
    ClubsSample(
        type = "club",
        clubName = "Клуб по книге Бритни",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "ср",
                startHour = "15",
                startMinute = "00",
                endHour = "16",
                endMinute = "00",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "ср",
                startHour = "17",
                startMinute = "30",
                endHour = "18",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "сб",
                startHour = "15",
                startMinute = "00",
                endHour = "16",
                endMinute = "00",
                type = "выходного дня",
                shortType = "вых.дн",
            ),
        )
    ),
    ClubsSample(
        type = "club",
        clubName = "Разговорный клуб по новелле «История Кицунэ»",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "вт",
                startHour = "13",
                startMinute = "30",
                endHour = "14",
                endMinute = "30",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "вт",
                startHour = "17",
                startMinute = "30",
                endHour = "18",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "сб",
                startHour = "13",
                startMinute = "00",
                endHour = "14",
                endMinute = "00",
                type = "выходного дня",
                shortType = "вых.дн",
            ),
        )
    ),
    ClubsSample(
        type = "club",
        clubName = "Разговорный клуб по новелле «Сквозь время»",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пт",
                startHour = "13",
                startMinute = "00",
                endHour = "14",
                endMinute = "00",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пт",
                startHour = "17",
                startMinute = "30",
                endHour = "18",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "сб",
                startHour = "17",
                startMinute = "00",
                endHour = "18",
                endMinute = "00",
                type = "выходного дня",
                shortType = "вых.дн",
            ),
        )
    ),
    ClubsSample(
        type = "course",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пн",
                startHour = "12",
                startMinute = "00",
                endHour = "13",
                endMinute = "30",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "чт",
                startHour = "12",
                startMinute = "00",
                endHour = "13",
                endMinute = "30",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пн",
                startHour = "18",
                startMinute = "00",
                endHour = "19",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "чт",
                startHour = "18",
                startMinute = "00",
                endHour = "19",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
        )
    ),
    ClubsSample(
        type = "course",
        clubName = "Хочу в айти!",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пн",
                startHour = "14",
                startMinute = "00",
                endHour = "15",
                endMinute = "30",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "чт",
                startHour = "14",
                startMinute = "00",
                endHour = "15",
                endMinute = "30",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пн",
                startHour = "20",
                startMinute = "00",
                endHour = "21",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "чт",
                startHour = "20",
                startMinute = "00",
                endHour = "21",
                endMinute = "30",
                type = "вечерняя",
                shortType = "вечер",
            ),
        )
    ),
    ClubsSample(
        type = "course",
        clubName = "Качаю софт скиллы",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
        listSchedule = listOf(
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "вт",
                startHour = "12",
                startMinute = "00",
                endHour = "13",
                endMinute = "00",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пт",
                startHour = "12",
                startMinute = "00",
                endHour = "13",
                endMinute = "00",
                type = "дневная",
                shortType = "день",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "вт",
                startHour = "19",
                startMinute = "00",
                endHour = "20",
                endMinute = "00",
                type = "вечерняя",
                shortType = "вечер",
            ),
            CoursesScheduleEntity(
                year = "2024",
                month = "11",
                dayOfWeek = "пт",
                startHour = "19",
                startMinute = "00",
                endHour = "20",
                endMinute = "00",
                type = "вечерняя",
                shortType = "вечер",
            ),
        )
    ),
)