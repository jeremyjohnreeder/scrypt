package crypto

import collection.mutable

class ARC4(val key: Iterable[Byte], val setupRounds: Int = 1) extends Encryptor with Decryptor {
	private val modulus = 256

	def pseudorandomBytes: Stream[Byte] = {
		var s: mutable.Buffer[Int] = null
		var i = 0
		var j = 0

		def swap(i: Int, j: Int) {
			val temp = s(i)
			s(i) = s(j)
			s(j) = temp
		}

		def setup() {
			s = (0 until modulus).toBuffer
			i = 0
			j = 0
			val keyBuffer = key.toBuffer
			for (round <- 1 to setupRounds; i <- 0 until modulus) {
				j = (j + s(i) + keyBuffer(i % keyBuffer.length) + modulus) % modulus
				swap(i,j)
			}
		}

		def bytes: Stream[Byte] = {
			i = (i + 1) % modulus
			j = (j + s(i)) % modulus
			swap(i,j)
			val x = (s(i) + s(j)) % modulus
			s(x).toByte #:: bytes
		}

		setup
		bytes
	}

	override def encryptBytes(message: Iterable[Byte]) = {
		for (mkPair ← message zip pseudorandomBytes)
			yield (mkPair._1 ^ mkPair._2).toByte
		}.toStream

	/** The decryption process is identical to the encryption process. */
	override def decryptToBytes(ciphertext: Iterable[Byte]) = encryptBytes(ciphertext)
}
