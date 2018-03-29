package com.amino.ipcoverter

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length == 0 ) {
      println("Please enter the IP address you want to convert: ")
      return
    }
    convert(args.toList.head) match {
      case Left(err) => println(err.message)
      case Right(result) => println(result)
    }
  }

  def convert(input: String): Either[AppError, Long] = {
    withValidation(input).flatMap(ips => {
      ips
        .map(ip => decimalToBinary(ip.toInt))
        .foldRight[Either[AppError, List[String]]](Right(Nil)) {
          case (Left(e), _) => Left(e)
          case (Right(_), Left(e)) => Left(e)
          case (Right(a), Right(list)) => Right(a :: list)}
        .map(_.foldRight ("") ((next, acc) => next + acc))
        .map(binaryToDecimal(_))
    })
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

  def decimalToBinary(input: Int, res: String = "", size: Int = 8): Either[AppError, String] = {
    val maxValue = 1 << size
    val binary: Either[AppError, String] = input / 2 match {
      case _ if input > maxValue => Left(AppError(s"$input is greater than $maxValue"))
      case _ if size == 0 => Right(res)
      case _ =>
        decimalToBinary(input / 2, res + (input % 2).toString, size - 1)
    }
    binary.map(_.reverse)
  }

  def binaryToDecimal(input: String, res: Long = 0L): Long = {
    val inputLength = input.length
    inputLength match {
      case 0 => res
      case _ =>
        binaryToDecimal(
          input.substring(1, inputLength),
          res + input.substring(0, 1).toInt * (1L << inputLength - 1 )
        )
    }
  }
}
