package com.vickykdv.mymovie.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.vickykdv.mymovie.data.database.model.TvShowEntity



@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : TvShowEntity) : Long

    @Query("SELECT * from tvshow where id = :id")
    fun getDataById(id : Int) : List<TvShowEntity>

    @Query("SELECT * from tvshow")
    fun getData() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * from tvshow")
    fun getDataList() : List<TvShowEntity>

    @Query("SELECT * From tvshow where name like :query")
    fun searchData(query : String) : DataSource.Factory<Int, TvShowEntity>

    @Delete
    fun delete(data : TvShowEntity)
}