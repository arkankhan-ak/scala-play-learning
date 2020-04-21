package controllers

import javax.inject._
import models._
import play.api._
import play.api.mvc._


@Singleton
class TestPlay @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
    def testPlay = Action { request =>
        val nameOption = request.session.get("name")
        Ok{views.html.testplay(List("Amanda","David","Alesha","Simon",nameOption.toString))}
    }

    def login = Action { implicit request =>
        Ok{views.html.login()}
    }

    def studentRegistration() = Action { request => 
        val postVals = request.body.asFormUrlEncoded
        postVals.map {
            args => 
            val name = args("name").head
            val age = args("age").head
            val gender = args("gender").head
            if(Student.validateStudent(name,gender)) {
                print("hi"+name+" "+gender)
                Redirect(routes.TestPlay.testPlay()).withSession("name" -> name)
            }
            else {
                print("by "+name+" "+gender)
                Redirect(routes.TestPlay.login())
            }
        }.getOrElse(Ok("OK"))
    }

    def validateLogin(uname:String,pword:String) = Action{ implicit request =>
        Ok{s"$uname logged in with $pword"}
    }

    def showPerson(name:String,age:Int) = Action{ request =>
        Ok{s"person name: $name and age : $age"}
    }
    def logout = Action{ request =>
        Redirect(routes.TestPlay.login()).withNewSession
    }
}