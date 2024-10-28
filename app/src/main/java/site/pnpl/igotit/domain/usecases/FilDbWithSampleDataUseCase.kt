package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository

class FilDbWithSampleDataUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(): Boolean  {
        val result = repository.saveSampleClubsToDb()
        return result
    }
}