package server

data class Person(val name: String, val age: Int)
data class Error(val code: Int, val message: String)
data class Product(val id: Int,
                   val name: String,
                   val description: String,
                   val descLong: String,
                   val price: Double,
                   val imgUrl: String)
data class ResponseJson(val statusCode: Int, val payload:Any)