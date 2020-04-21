package models
import collection.mutable
object Student{
  private val users = mutable.Map[String, String]("Arkan"->"ak","Anas"->"anas")
  def validateStudent(name: String,age: String) : Boolean = {
    println("------------>"+users.get(name).map(_ == age))
    users.get(name).map(_ == age).getOrElse(false)
  }
}