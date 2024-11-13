package site.pnpl.igotit.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class GetMyCoursesFromDbUseCase(
    private val repository: IClubRepository,
    private val getMyCoursesFromDbByListUuidUseCase: GetMyCoursesFromDbByListUuidUseCase,
    private val getMyCoursesFromDbByFlagUseCase: GetMyCoursesFromDbByFlagUseCase,

) {
    suspend operator fun invoke(): Pair<List<Clubs>,List<Clubs>> {
        var listClubs: List<Clubs>
        var listFavorites: List<Clubs>
        val lessons = withContext(Dispatchers.IO) {
            val listMyCourses = repository.getMyCourses()

            println("GetMyCoursesFromDbUseCase - listMyCourses = $listMyCourses")

            listClubs = getMyCoursesFromDbByListUuidUseCase(listMyCourses)
            listFavorites = getMyCoursesFromDbByFlagUseCase()

            println("GetMyCoursesFromDbUseCase - listClubs = $listClubs")
        }

        return Pair(listClubs, listFavorites)
    }
}