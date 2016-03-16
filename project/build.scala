import sbt.Keys._
import sbt._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._

object Versions {
  val akka = "2.4.2"
}

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"

  val akka = Seq(
    "com.typesafe.akka" %% "akka-actor" % Versions.akka,
    "com.typesafe.akka" %% "akka-testkit" % Versions.akka % "test",
    "com.typesafe.akka" %% "akka-slf4j" % Versions.akka,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka,
    "com.typesafe.akka" %% "akka-http-core" % Versions.akka,
    "com.typesafe.akka" %% "akka-http-experimental" % Versions.akka
  )

  val typesafeConfig = "com.typesafe" % "config" % "1.3.0"

  val logging = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "ch.qos.logback" % "logback-classic" % "1.1.5"
  )

  val all = akka ++ logging ++ Seq(scalaTest, typesafeConfig)

}

/*
 * On publishing to Docker Hub:
 * https://docs.docker.com/engine/userguide/dockerrepos/
 * https://docs.docker.com/engine/articles/dockerfile_best-practices/
 * https://docs.docker.com/mac/step_six/
 * http://www.scala-sbt.org/sbt-native-packager/formats/docker.html
 */

object DockerSettings {

  private val javaOpts = Seq("-Djava.net.preferIPv4Stack=true")

  lazy val dockerBuild = Seq(
    javaOptions in Universal := javaOpts,
    dockerBaseImage := "java:8",
    dockerExposedPorts := Seq(8080),
    version in Docker <<= version,
    packageName in Docker <<= name,
    maintainer in Docker := "Maciej Bilas <maciej.bilas@gmail.com>",
    dockerRepository := Some("maciejb"),
    dockerUpdateLatest := true,
    defaultLinuxInstallLocation in Docker := s"/opt/${name.value}"
  )
}
