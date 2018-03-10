package eu.timepit.refined.types

import eu.timepit.refined.types.all._
import org.scalacheck.Prop._
import org.scalacheck.Properties

class StringTypesSpec extends Properties("StringTypes") {
  // Hashes for ""
  object EmptyString {
    val md5 = "d41d8cd98f00b204e9800998ecf8427e"
    val sha1 = "da39a3ee5e6b4b0d3255bfef95601890afd80709"
    val sha224 = "d14a028c2a3a2bc9476102bb288234c415a2b01f828ea62ac5b3e42f"
    val sha256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
    val sha384 =
      "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b"
    val sha512 =
      "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e"
  }

  property(s"MD5.from(${EmptyString.md5})") = secure {
    MD5.from(EmptyString.md5).isRight
  }

  property(s"SHA1.from(${EmptyString.sha1})") = secure {
    SHA1.from(EmptyString.sha1).isRight
  }

  property(s"SHA224.from(${EmptyString.sha224})") = secure {
    SHA224.from(EmptyString.sha224).isRight
  }

  property(s"SHA256.from(${EmptyString.sha256})") = secure {
    SHA256.from(EmptyString.sha256).isRight
  }

  property(s"SHA384.from(${EmptyString.sha384})") = secure {
    SHA384.from(EmptyString.sha384).isRight
  }

  property(s"SHA512.from(${EmptyString.sha512})") = secure {
    SHA512.from(EmptyString.sha512).isRight
  }
}
