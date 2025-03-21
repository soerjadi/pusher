lazy val googleApiVersion = "1.23.0"
lazy val guavaVersion = "20.0"
lazy val firebaseVersion = "5.9.0"

lazy val commonSettings = Seq(
    organization := "com.github.soerjadi.pusher",
    version := "0.0.1-SNAPSHOT-2.10",
    scalaVersion := "2.10.7"
)

// Add this to create a fat JAR
assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
}

lazy val root = (project in file("."))
    .settings(
        commonSettings,
        name := "pusher",
        libraryDependencies ++= Seq(
            "org.slf4j" % "slf4j-api" % "1.7.25",
            "ch.qos.logback" % "logback-classic" % "1.2.3",
            "com.google.api-client" % "google-api-client" % googleApiVersion exclude ("com.google.guava", "guava-jdk5"),
            "com.google.guava" % "guava" % guavaVersion,
            "com.google.firebase" % "firebase-admin" % firebaseVersion exclude ("com.google.guava", "guava")
        )
    )
