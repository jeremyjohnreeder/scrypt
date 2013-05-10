name := "Scrypt"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "1.6.1" % "test",
	"org.scalaz" %% "scalaz-core" % "6.0.4"
)

resolvers += "Scala Tools Snapshots" at "https://oss.sonatype.org/content/groups/scala-tools/"
