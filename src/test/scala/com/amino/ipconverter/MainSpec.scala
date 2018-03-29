package com.amino.ipconverter

import com.amino.ipcoverter.Main
import org.specs2.mutable._

class MainSpec extends Specification {
  "convert" should {
    "return correct 32-bit integer when given a valid IP" in {
      val validIP = "192.168.1.8"
      Main.convert(validIP) must beRight(3232235784L)
    }

    "return an error when given an invalid IP" in {
      val invalidIP = "zhihui.1 68.1 .8%%"
      Main.convert(invalidIP) must beLeft
    }
  }

  "withValidation" should {
    "return an error" should {
      "when there's number greater than 255" in {
        val invalidIP = "912.456.123.123"
        Main.withValidation(invalidIP) must beLeft
      }

      "when there's spaces between two digits" in {
        val invalidIP = "19 2.168.1"
        Main.withValidation(invalidIP) must beLeft
      }

      "when there's a continuous series of zero " in {
        val invalidIP = "000.0000.00.00"
        Main.withValidation(invalidIP) must beLeft
      }

      "when there's non-number inside" in {
        val invalidIP = "192&.168.1"
        Main.withValidation(invalidIP) must beLeft
      }
    }

    "return a correct list of IP" should {
      "when there's space between a digit and a dot" in {
        val validIP = "192.168  .1.1"
        Main.withValidation(validIP) must beRight(List("192", "168", "1", "1"))
      }

      "when it's definitely valid" in {
        val validIP = "100.168.1.1"
        Main.withValidation(validIP) must beRight(List("100", "168", "1", "1"))
      }
    }
  }

  "decimalToBinary" should {
    "return a correct binary" should {
      "when the number is 0" in {
        Main.decimalToBinary(0, "") must beRight("00000000")
      }

      "when the number is 255" in {
        Main.decimalToBinary(255, "") must beRight("11111111")
      }

      "when the number is between 0 and 255" in {
        Main.decimalToBinary(172, "") must beRight("10101100")
      }
    }

    "return an error when the number is greater than 255" in  {
      Main.decimalToBinary(300) must beLeft
    }
  }

  "binaryToDecimal" should {
    "return a correct decimal" should {
      "when the binary is 00000000" in {
        Main.binaryToDecimal("00000000") must equalTo(0L)
      }

      "when the binary is 11111111" in {
        Main.binaryToDecimal("11111111") must equalTo(255L)
      }

      "when the binary is 10101100" in {
        Main.binaryToDecimal("10101100") must equalTo(172L)
      }

      "when the binary is 1010110000001001101101" in {
        Main.binaryToDecimal("1010110000001001101101") must equalTo(2818669L)
      }
    }
  }
}

