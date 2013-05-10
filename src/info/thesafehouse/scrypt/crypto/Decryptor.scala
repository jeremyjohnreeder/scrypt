package crypto

import java.io.{OutputStreamWriter, ByteArrayOutputStream}
import java.nio.charset.Charset
import java.nio.charset.Charset.defaultCharset

trait Decryptor {
	def decryptToBytes(cypher: Iterable[Byte]): Stream[Byte]

	def decrypt(cypher: Iterable[Byte], encoding: Charset = defaultCharset): String =
		new String(decryptToBytes(cypher).toArray, encoding)
}
