package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class GetClubByIdFromDbUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(id: Int): Clubs {
        val result = repository.getEntityById("club", id)
        return result
    }
}