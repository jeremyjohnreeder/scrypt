package test.scala

import crypto.{CipherSaber, ARC4, Decryptor, Encryptor}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FreeSpec

class CryptoSpec extends FreeSpec with ShouldMatchers {

	class Algorithm(val name: String, val encryptor: Encryptor, val decryptor: Decryptor) {
		def this(name: String, encryptor: Encryptor with Decryptor) {
			this(name, encryptor, encryptor)
		}
	}

	val algorithms = {
		val key = "ThomasJefferson"
		def arc4 = new ARC4(key.getBytes)
		def cs1 = new CipherSaber(key)
		def cs2 = new CipherSaber(key, setupRounds=3)
		Seq(
			new Algorithm("ARC4", arc4, arc4),
			new Algorithm("CS1", cs1, cs1),
			new Algorithm("CS2", cs2, cs2))
		}

	val message = "The donkey flies at midnight."

	for (algorithm ← algorithms) {
		"The output of " + algorithm.name + "'s encrypt function should differ from its input." in {
			val originalBytes = message.getBytes
			val encryptedBytes = algorithm.encryptor encryptBytes originalBytes
			encryptedBytes.toArray should not equal (originalBytes)
		}

		algorithm.name + "'s decrypt function should be the inverse of its encrypt function" - {
			"where the message is a byte stream." in {
				val originalBytes = message.getBytes
				val encryptedBytes = algorithm.encryptor encryptBytes originalBytes
				val decryptedBytes = (algorithm.decryptor decryptToBytes encryptedBytes).toArray
				decryptedBytes should equal (originalBytes)
			}
			"where the message is a string." in {
				val encryptedBytes = algorithm.encryptor encrypt message
				val decryptedText = algorithm.decryptor decrypt encryptedBytes
				decryptedText should equal (message)
			}
		}
	}
}
