package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class SetCoursesFavoriteUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(id : Int): Boolean {
        val result = repository.setFavorites(id)
        return result
    }
}