package com.bangkit.scrapncraft.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.scrapncraft.data.remote.response.DataItem

@Dao
interface CraftsDao {
    @Query("SELECT * FROM crafts")
    fun getCrafts(): LiveData<List<DataItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCrafts(crafts: List<DataItem>)

    @Update
    fun updateCrafts(crafts: DataItem)

    @Query("DELETE FROM crafts WHERE isViewed = 0")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM crafts WHERE title = :title AND isViewed = 1)")
    fun isCraftsViewed(title: String): Boolean

    @Query("SELECT * FROM crafts WHERE isViewed = 1 ORDER BY viewedTimestamp DESC LIMIT 10")
    fun getRecentlyViewedCrafts(): LiveData<List<DataItem>>

    @Query("DELETE FROM crafts WHERE isViewed = 1 AND viewedTimestamp < :timestamp")
    fun deleteOldRecentlyViewedCrafts(timestamp: Long)
}
