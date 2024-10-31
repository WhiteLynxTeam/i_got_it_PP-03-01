package site.pnpl.igotit.domain

import site.pnpl.igotit.data.dbo.entity.ClubEntity

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
const val DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH = "dd MMMM yyyy"
const val DATE_PATTERN_WEEK_DAY = "EE"
const val DATE_PATTERN_DAY_ONLY = "dd"
const val DATE_PATTERN_WITHOUT_YEAR = "dd MMMM, EEEE"

/**
 * SAMPLE DATE FOR DATABASE
 */
val sampleListOfClubs = listOf(
    ClubEntity(
        type = "club",
        clubName = "Разговорный клуб Extra",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "club",
        clubName = "Клуб по книге Бритни",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "club",
        clubName = "Разговорный клуб по новелле «История Кицунэ»",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "club",
        clubName = "Разговорный клуб по новелле «Сквозь время»",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "course",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "course",
        clubName = "Хочу в айти!",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
    ClubEntity(
        type = "course",
        clubName = "Качаю софт скиллы",
        level = "A1 - A2",
        numberClasses = "2 занятие",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней",
        about = "Курс для тех, кто хочет заговорить на английском. На занятиях мы затрагиваем все грамматические и лексические темы уровней A1 и A2 (Beginner и Pre-Intermeiate), при этом акцент делаем на умении говорить. Учимся высказывать своё мнение, обсуждать бытовые ситуации, свои предпочтения. Оттачиваем грамматику в домашних заданиях.",
    ),
)