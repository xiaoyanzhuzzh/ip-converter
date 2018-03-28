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

  "binaryToDecimal" should {
    "return a correct decimal when given a valid binary" in {
      Main.binaryToDecimal("10101100", 0L) must equalTo(172)
    }
  }
}

