package site.pnpl.igotit.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CursesDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun save(imageEntity: ImageEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(imageEntitys: List<ImageEntity>): List<Long>
//
//    @Query("DELETE FROM IMAGES WHERE id = :id")
//    fun del(id: Int)
//
//    @Query("SELECT * FROM IMAGES")
//    fun getImages(): List<ImageEntity>
//
//    @Query("SELECT * FROM IMAGES  WHERE id = :id")
//    fun getImageById(id: Int): ImageEntity
//
//    @Query("DELETE FROM IMAGES")
//    fun trunc(): Int

}