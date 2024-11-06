package site.pnpl.igotit.data.dbo.entity

data class ClubsSample(
    override val clubName: String,
    override val type: String,
    override val level: String,
    override val description: String,
    override val length: String,
    override val frequency: String,
    override val numberClasses: String,
    override val totalQuantity: String,
    override val about: String = "",
    val listSchedule: List<CoursesScheduleEntity>,
) : BaseClub(
    clubName = clubName,
    type = type,
    level = level,
    description = description,
    length = length,
    frequency = frequency,
    numberClasses = numberClasses,
    totalQuantity = totalQuantity,
    about = about,
)