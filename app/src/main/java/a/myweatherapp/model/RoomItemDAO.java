package a.myweatherapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RoomItemDAO {

    @Query("SELECT * FROM weatherdb")
    List<RoomItem> getAll();

    @Query("SELECT * FROM weatherdb WHERE id LIKE :id")
    RoomItem findById(int id);

    @Insert
    void insertAll(RoomItem... items);

    @Insert
    void insertAll(List<RoomItem> list);

    @Insert
    void insert(RoomItem item);

    @Delete
    void delete(RoomItem item);

}
