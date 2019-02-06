package book.fmy.org.observer

import java.util.*

class ObserverList<T>() : ArrayList<T>() {

    override fun add(element: T): Boolean {

        return super.add(element)
    }
}
