package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class GetMyCoursesFromDbUseCase(
    private val repository: IClubRepository,
    private val getMyCoursesFromDbByListUuidUseCase: GetMyCoursesFromDbByListUuidUseCase,

) {
    suspend operator fun invoke(): List<Clubs> {
        val listMyCourses = repository.getMyCourses()

        println("GetMyCoursesFromDbUseCase - listMyCourses = $listMyCourses")

        val listClubs = getMyCoursesFromDbByListUuidUseCase(listMyCourses)

        println("GetMyCoursesFromDbUseCase - listClubs = $listClubs")

        return listClubs
    }
}