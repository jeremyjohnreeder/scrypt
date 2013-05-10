package crypto

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, OutputStream, OutputStreamWriter}
import java.nio.charset.Charset
import java.nio.charset.Charset.defaultCharset

trait Encryptor {
	def encryptBytes(message: Iterable[Byte]): Stream[Byte]

	def encrypt(message: String, encoding: Charset = defaultCharset): Stream[Byte] =
		encryptBytes(message.getBytes(encoding))
}
