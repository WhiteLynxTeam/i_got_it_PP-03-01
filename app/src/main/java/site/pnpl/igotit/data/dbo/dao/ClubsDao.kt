package site.pnpl.igotit.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.domain.models.Clubs

@Dao
interface ClubsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun save(imageEntity: ImageEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(imageEntitys: List<ClubEntity>): List<Long>

    @Query("SELECT * FROM CLUBS")
    fun getClubs(): List<ClubEntity>

//    @Query("SELECT * FROM IMAGES  WHERE id = :id")
//    fun getImageById(id: Int): ImageEntity
//
    @Query("DELETE FROM CLUBS")
    fun trunc(): Int

}