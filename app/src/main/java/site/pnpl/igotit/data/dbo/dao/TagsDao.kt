package site.pnpl.igotit.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TagsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun save(commentEntity: CommentEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(commentEntity: List<CommentEntity>): List<Long>
//
//    @Query("DELETE FROM COMMENTS WHERE commentId = :id")
//    fun del(id: Int)
//
//    @Query("SELECT * FROM COMMENTS WHERE imageId = :id")
//    fun getComments(id: Int): List<CommentEntity>

//    @Query("SELECT * FROM IMAGES  WHERE id = :id")
//    fun getImageById(id: Int): ImageEntity
//
//    @Query("DELETE FROM COMMENTS WHERE imageId = :id")
//    fun trunc(id: Int): Int

}