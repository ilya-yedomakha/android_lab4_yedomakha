package stu.cn.ua.lab4.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MeteoriteDbEntity.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MeteoriteDao getMeteoriteDao();
}
