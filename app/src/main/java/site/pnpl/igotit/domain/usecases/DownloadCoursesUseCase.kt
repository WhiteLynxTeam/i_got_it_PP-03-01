package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

class DownloadCoursesUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(): Result<List<Clubs>>  {
        val result = repository.getFromApi()
        return result
    }
}