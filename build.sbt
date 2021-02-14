val scala3Version = "3.0.0-M3"

lazy val root = project
   .enablePlugins(NativeImagePlugin)
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.13.0",
    scalaVersion := scala3Version,
    Compile / mainClass := Some("Main"),
    libraryDependencies ++= Seq(
        "org.typelevel" % "cats-effect_2.13"  % "2.3.1",
        "com.lihaoyi"   %% "fansi"        % "0.2.10",
        "com.lihaoyi"   %% "os-lib"       % "0.7.2",
        "com.monovore"   % "decline_2.13" % "1.0.0",
        "org.scalatest" %% "scalatest"    % "3.2.3" % Test
      )
  )
