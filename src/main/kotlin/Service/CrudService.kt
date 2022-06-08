package Service

interface CrudService<E> {
    fun add ( entity: E) :Int
    fun delete( id:Int) : Boolean
    fun edit ( entity: E) :Boolean
    fun getById( id : Int) : E?
    fun restore ( id: Int) :Boolean
    fun read(id: Int) :MutableList<E>?


}