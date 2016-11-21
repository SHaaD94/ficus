import sbt._, Keys._
import bintray.BintrayKeys._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._


object Publish {

  val bintraySettings = Seq(
    bintrayOrganization := Some("iheartradio"),
    bintrayPackageLabels := Seq("typesafe-config", "parser", "config")
  )

  import ReleaseTransformations._


  pomExtra in Global := {

      <developers>
        <developer>
          <id>ceedubs</id>
          <name>Cody Allen</name>
          <email>ceedubs@gmail.com</email>
        </developer>
        <developer>
          <id>kailuowang</id>
          <name>Kailuo Wang</name>
          <email>kailuo.wang@gmail.com</email>
        </developer>
      </developers>
  }


  val publishingSettings = Seq(

    organization in ThisBuild := "com.iheart",
    publishMavenStyle := true,
    licenses := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.html")),
    homepage := Some(url("http://iheartradio.github.io/ficus")),
    scmInfo := Some(ScmInfo(
      url("https://github.com/iheartradio/ficus"),
      "git@github.com:iheartradio/ficus.git",
      Some("git@github.com:iheartradio/ficus.git"))),
    pomIncludeRepository := { _ => false },
    publishArtifact in Test := false,
    pomExtra := (
      <url>https://github.com/iheartradio/ficus/</url>
      <licenses>
        <license>
          <name>MIT</name>
          <url>http://www.opensource.org/licenses/mit-license.html</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/iheart/ficus</connection>
        <developerConnection>scm:git:git@github.com:iheart/ficus</developerConnection>
        <url>github.com/iheart/ficus</url>
      </scm>
      <developers>
        <developer>
          <id>ceedubs</id>
          <name>Cody Allen</name>
          <email>ceedubs@gmail.com</email>
        </developer>
        <developer>
          <id>kailuowang</id>
          <name>Kailuo Wang</name>
          <email>kailuo.wang@gmail.com</email>
        </developer>
      </developers>
      ),

    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      ReleaseStep(action = Command.process("publishSigned", _)),
      setNextVersion,
      commitNextVersion,
      ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
      pushChanges
    )

  )

  val settings = publishingSettings
}
