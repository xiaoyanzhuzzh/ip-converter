name := "ip-converter"
organization := "com.amino"
version := "0.0.1"

scalaVersion := "2.12.1"
sbtVersion := "0.13.16"

val specs2Version = "3.8.6"

libraryDependencies ++= Seq(
  "org.specs2"                      %% "specs2-core"                 % specs2Version    % "test",
  "org.specs2"                      %% "specs2-matcher-extra"        % specs2Version    % "test"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ywarn-unused-import",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ypartial-unification",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-language:higherKinds")

scalacOptions in Test ++= Seq("-Yrangepos")

// Read here for optional dependencies:
// http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)
