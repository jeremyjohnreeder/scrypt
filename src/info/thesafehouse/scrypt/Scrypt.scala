package info.thesafehouse.scrypt

import _root_.crypto.{CipherSaber, ARC4}
import java.io.File

object Scrypt extends App {

	def help {
		val usage = """
				Usage:
					scrypt encrypt=<algorithm> key=__ [input=__] output=__
					scrypt decrypt=<algorithm> key=__ input=__ [output=__]
		"""
		println(usage)
	}

//	val options = {
//		for (arg ← args; (k: String, v) = (arg split '=').toMap)
//			yield (k,v) match {
//				case (k, _) if (k(0)=='-') => {help; sys.exit}
//				case (k, algorithm) if (k(0)=='e') => ('encrypt, algorithm)
//				case (k, algorithm) if (k(0)=='d') => ('decrypt, algorithm)
//				case (k, key) if (k(0)=='k') => ('key, key)
//				case (k, input) if (k(0)=='i') => ('input, input)
//				case (k, output) if (k(0)=='o') => ('output, output)
//				case _ => {printf("Invalid option: %s%n", arg); sys.exit(1)}
//			}
//	}.toMap
//
//	val operation =
//		(options('encrypt), options('decrypt)) match {
//			case (_: String, null) => 'encrypt
//			case (null, _: String) => 'decrypt
//			case _ => {println("Encrypt or decrypt?"); sys.exit(1)}
//		}
//
//	val key: String = {
//		val k = options('key)
//		if (k == null) {printf("Invalid key: %s%n", k); sys.exit(1);}
//		k
//	}
//
//	val algorithm = {
//		val name =
//			(options('encrypt), options('decrypt)) match {
//				case (e: String, null) => e
//				case (null, d: String) => d
//				case s => {printf("Invalid algorithm: %s%n", s); sys.exit(1)}
//			}
//		name match {
//			case "arc4" => new ARC4(key.getBytes)
//			case "cs1" => new CipherSaber(key)
//		}
//	}
//
//	val input =
//		(options('input)) match {
//			case null =>
//				operation match {
//					case 'encrypt => io.Source.stdin
//					case _ => {println("Which input file?"); sys.exit(1)}
//				}
//			case f => /*try*/ new File(f) //catch {case _ => printf("Invalid input file: %s%n", input); sys.exit(1)}
//		}
//
//	val output =
//		(options('output)) match {
//			case null =>
//				operation match {
//					case 'decrypt => null //stdout
//					case _ => {println("Which output file?")}
//				}
//			case f => /*try*/ new File(f) //catch {case _ => printf("Invaid output file: %s%n", output); sys.exit(1)}
//		}
//
//	val function = null //if (operation == 'encrypt) algorithm.encrypt else algorithm.decrypt
}