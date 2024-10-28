package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.Clubs

interface IClubRepository {
    suspend fun getFromApi(): Result<List<Clubs>>
    suspend fun saveSampleClubsToDb(): Boolean
}