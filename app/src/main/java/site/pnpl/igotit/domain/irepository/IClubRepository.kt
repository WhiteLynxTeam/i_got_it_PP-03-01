package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.Clubs
import java.util.UUID

interface IClubRepository {
    suspend fun getEntities(type: String?): List<Clubs>
    suspend fun getEntityById(type: String, id: Int): Clubs

    suspend fun getFromApi(): Result<List<Clubs>>

    suspend fun saveSampleClubsToDb(): Boolean
    suspend fun setFavorites(id : Int): Result<Boolean>
    suspend fun setMyCourse(uuid: Int): Boolean
}