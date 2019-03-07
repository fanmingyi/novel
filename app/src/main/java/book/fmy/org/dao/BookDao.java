package book.fmy.org.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
interface BookDao {

    @Insert
    void insetBook(Book book);

    @Query("SELECT * from book_table WHERE bookId=:id")
    LiveData<Book> queryBookById(String id);

    @Query("SELECT * from book_table")
    LiveData<List<Book>> getAllBookList();

}
