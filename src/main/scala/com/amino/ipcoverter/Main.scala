package com.amino.ipcoverter

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length == 0 ) {
      println("Please enter the IP address you want to convert: ")
      return
    }
    withValidation(args.toList.head) match {
      case Left(e) => println(e.message)
      case Right(validIP) => println(validIP)
    }
  }

  def withValidation(inputIP: String): Either[AppError, List[String]] = {
    val IPPattern = ("^\\s*([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\s*\\.\\s*([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\s*\\." +
      "\\s*([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\s*\\.\\s*([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\s*$").r
    inputIP match {
      case IPPattern(first, second, third, last) =>
        Right(List[String](first, second, third, last))
      case err =>
        Left(AppError(s"$err is an invalid IP Address."))
    }
  }
}
