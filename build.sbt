organization := "me.maciejb"
name := "http-hello-world"
version := "1.0"
scalaVersion := "2.11.8"

libraryDependencies ++= Dependencies.all

DockerSettings.dockerBuild

enablePlugins(JavaAppPackaging, DockerPlugin)
