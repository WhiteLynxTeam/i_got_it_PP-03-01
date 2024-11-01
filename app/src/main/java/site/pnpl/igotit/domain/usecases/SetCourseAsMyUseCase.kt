package site.pnpl.igotit.domain.usecases

import site.pnpl.igotit.domain.irepository.IClubRepository
import java.util.UUID

class SetCourseAsMyUseCase(
    private val repository: IClubRepository,
) {
    suspend operator fun invoke(uuid : Int):Boolean {
        return repository.setMyCourse(uuid)
    }
}