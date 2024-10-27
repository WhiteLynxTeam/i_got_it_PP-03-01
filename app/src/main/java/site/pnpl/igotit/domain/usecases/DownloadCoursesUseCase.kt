package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository

class DownloadCoursesUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(): Boolean {
        val result = repository.getFromApi()
        return result
    }
}