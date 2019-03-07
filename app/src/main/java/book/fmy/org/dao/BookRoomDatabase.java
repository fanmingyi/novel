package book.fmy.org.dao;

import android.content.Context;
import androidx.room.Room;

public class BookRoomDatabase {


    private static volatile AppDatabase INSTANCE;

    static AppDatabase getBookRommDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "book_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
