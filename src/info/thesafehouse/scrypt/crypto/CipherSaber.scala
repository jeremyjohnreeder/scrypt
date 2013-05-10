package crypto

import java.security.SecureRandom
import util.Random

class CipherSaber(val key: String, val setupRounds: Int = 1) extends Encryptor with Decryptor {
	val sevenBits = 127.toByte

	def arc4Key(iv: Iterable[Byte]) = {
		val saberKeyAsAsciiBytes = for (char ← key.toCharArray) yield (char & sevenBits).toByte
		saberKeyAsAsciiBytes.toBuffer union iv.toBuffer
	}

	override def encryptBytes(message: Iterable[Byte]) = {
		val initializationVector = {
			val tenRandomBytes = new Array[Byte](10)
			new Random(new SecureRandom).nextBytes(tenRandomBytes)
			tenRandomBytes
		}
		val arc4 = new ARC4(key=arc4Key(initializationVector), setupRounds)

		{initializationVector union (arc4 encryptBytes message)}.toStream
	}

	override def decryptToBytes(cypher: Iterable[Byte]) = {
		val iv_C = cypher splitAt 10
		val iv = iv_C._1; val C = iv_C._2
		val arc4 = new ARC4(key=arc4Key(iv), setupRounds)
		arc4 decryptToBytes C
	}
}

