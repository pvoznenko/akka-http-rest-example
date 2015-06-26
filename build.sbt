name := "Instagram-pics-filter"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions in GlobalScope ++= Seq(
  "-Dsbt.override.build.repos=true",
  "-Dsbt.repository.config=./proxy-sbt-repositories"
)

libraryDependencies ++= {
  val akkaStreamV = "1.0-RC4"
  Seq(
    "com.typesafe.akka" %% "akka-actor"                           % "2.3.11",
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"               % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"               % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaStreamV,
    "org.scalatest"     %% "scalatest"                            % "2.2.5" % "test",
    "org.reactivemongo" %% "reactivemongo"                        % "0.10.5.0.akka23",
    "com.typesafe.akka" %% "akka-testkit"                         % "2.3.11" % "test"
  )
}

Revolver.settings
