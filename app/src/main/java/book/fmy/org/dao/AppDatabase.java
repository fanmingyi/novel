package book.fmy.org.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract BookDao wordDao();


}
