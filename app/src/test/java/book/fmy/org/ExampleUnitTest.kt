package book.fmy.org

import book.fmy.org.utils.LoginCheck
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://ic_book.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        LoginCheck.login("asd","23")
        assertEquals(4, 2 + 2)
    }
}
