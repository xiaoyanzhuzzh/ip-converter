package com.amino.ipconverter

import com.amino.ipcoverter.Main
import org.specs2.mutable._

class MainSpec extends Specification {
  "withValidation" should {
    "should return an error when given an invalid IP" in {
      val invalidIP = "000.0000.00.00"
      Main.withValidation(invalidIP) must beLeft
    }

    "should return an error when given an invalid IP" in {
      val invalidIP = "912.456.123.123"
      Main.withValidation(invalidIP) must beLeft
    }

    "should return an error when given an invalid IP" in {
      val invalidIP = "19 2.168.1.1"
      Main.withValidation(invalidIP) must beLeft
    }

    "should return a correct list of IP parts when given a valid IP" in {
      val invalidIP = "192.168.1.1"
      Main.withValidation(invalidIP) must beRight(List("192", "168", "1", "1"))
    }

    "should return a correct list of IP parts when given a valid IP" in {
      val invalidIP = "  192.168  .1.1"
      Main.withValidation(invalidIP) must beRight(List("192", "168", "1", "1"))
    }
  }

  "decimalToBinary" should {
    "should return a correct binary" should {
      "when given 0" in {
        Main.decimalToBinary(0, "") must beRight("00000000")
      }

      "when given 255" in {
        Main.decimalToBinary(255, "") must beRight("11111111")
      }

      "when given 172" in {
        Main.decimalToBinary(172, "") must beRight("10101100")
      }
    }

    "return an error when given a decimal greater than 255" in  {
      Main.decimalToBinary(300, "") must beLeft
    }
  }

  "binaryToDecimal" should {
    "should return a correct decimal" should {
      "when given 00000000" in {
        Main.binaryToDecimal("00000000", 0L) must equalTo(0L)
      }

      "when given 11111111" in {
        Main.binaryToDecimal("11111111", 0L) must equalTo(255L)
      }

      "when given 10101100" in {
        Main.binaryToDecimal("10101100", 0L) must equalTo(172L)
      }

      "when given 1010110000001001101101" in {
        Main.binaryToDecimal("1010110000001001101101", 0L) must equalTo(2818669L)
      }
    }
  }
}

