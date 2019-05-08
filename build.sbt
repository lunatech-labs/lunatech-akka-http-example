import com.typesafe.sbt.packager.docker.ExecCmd
import com.typesafe.sbt.packager.docker.Cmd

lazy val library = new Object {

  val version = new Object {
    val scala                      = "2.12.6"
    val akkaHttp                   = "10.1.5"
    val akka                       = "2.5.18"
    val circe                      = "0.10.1"
    val akkaHttpCirce              = "1.21.0"
    val akkaHttpSwagger            = "1.0.0"
    val scalaGuice                 = "4.2.1"
    val scalaTest                  = "3.0.5"
    val mockitoScala               = "1.0.5"
    val scalaJsDom                 = "0.9.5"
    val scalaJsReact               = "1.2.0"
    val scalaCss                   = "0.5.5"
    val scalaJsVideoJs             = "1.0.5"
    val scalaJsScripts             = "1.1.2"
    val elastic4s                  = "6.3.7"
    val elasticClient              = "6.3.2"
    val commonsIO                  = "2.6"
    val logback                    = "1.2.3"
    val log4j                      = "2.8.2"
    val scalaBcrypt                = "3.1"
    val jwtCore                    = "0.17.0"
    val jwtCirce                   = "0.18.0"
    val martinez                   = "0.5.0"
    val aspectJWeaver              = "1.9.2"
    val scalaTensorflow            = "0.4.1"
    val jcodec                     = "0.2.3"
  }

  val akkaHttp                   = "com.typesafe.akka"            %% "akka-http"         % version.akkaHttp
  val akkaHttpXml                = "com.typesafe.akka"            %% "akka-http-xml"     % version.akkaHttp
  val akkaActor                  = "com.typesafe.akka"            %% "akka-actor"        % version.akka
  val akkaStream                 = "com.typesafe.akka"            %% "akka-stream"       % version.akka
  val akkaHttpCirce              = "de.heikoseeberger"            %% "akka-http-circe"   % version.akkaHttpCirce
  val akkaHttpSwagger            = "com.github.swagger-akka-http" %% "swagger-akka-http" % version.akkaHttpSwagger
  val akkaSlf4j                  = "com.typesafe.akka"            %% "akka-slf4j"        % version.akka
  val circeCore                  = "io.circe"                     %% "circe-core"        % version.circe
  val circeGeneric               = "io.circe"                     %% "circe-generic"     % version.circe
  val circeParser                = "io.circe"                     %% "circe-parser"      % version.circe
  val scalaGuice                 = "net.codingwell"               %% "scala-guice"       % version.scalaGuice
  val scalaJsScripts             = "com.vmunier"                  %% "scalajs-scripts"   % version.scalaJsScripts
  val elastic4sCore              = "com.sksamuel.elastic4s"       %% "elastic4s-core"    % version.elastic4s
  val elastic4sHttp              = "com.sksamuel.elastic4s"       %% "elastic4s-http"    % version.elastic4s
  val elastic4sCirce             = "com.sksamuel.elastic4s"       %% "elastic4s-circe"   % version.elastic4s
  val logback                    = "ch.qos.logback"               % "logback-classic"    % version.logback
  val scalaBcrypt                = "com.github.t3hnar"            %% "scala-bcrypt"      % version.scalaBcrypt
  val jwtCore                    = "com.pauldijou"                %% "jwt-core"          % version.jwtCore
  val jwtCirce                   = "com.pauldijou"                %% "jwt-circe"         % version.jwtCirce
  val aspectJWeaver              = "org.aspectj"                  % "aspectjweaver"      % version.aspectJWeaver
  val jcodec                     = "org.jcodec"                   % "jcodec"             % version.jcodec
  val jcodecJavaSe               = "org.jcodec"                   % "jcodec-javase"      % version.jcodec

  // Only for test purpose
  val akkaTestKit       = "com.typesafe.akka"        %% "akka-testkit"        % version.akka         % Test
  val akkaStreamTestKit = "com.typesafe.akka"        %% "akka-stream-testkit" % version.akka         % Test
  val akkaHttpTestKit   = "com.typesafe.akka"        %% "akka-http-testkit"   % version.akkaHttp     % Test
  val scalaTest         = "org.scalatest"            %% "scalatest"           % version.scalaTest    % Test
  val mockitoScala      = "org.mockito"              %% "mockito-scala"       % version.mockitoScala % Test
  val elastic4sTestKit  = "com.sksamuel.elastic4s"   %% "elastic4s-testkit"   % version.elastic4s    % Test
  val elastic4sEmbedded = "com.sksamuel.elastic4s"   %% "elastic4s-embedded"  % version.elastic4s    % Test
  val commonsIO         = "commons-io"               % "commons-io"           % version.commonsIO    % Test
  val log4jCore         = "org.apache.logging.log4j" % "log4j-core"           % version.log4j        % Test
  val log4jApi          = "org.apache.logging.log4j" % "log4j-api"            % version.log4j        % Test
  val log4jApiImpl      = "org.apache.logging.log4j" % "log4j-slf4j-impl"     % version.log4j        % Test

  // monitoring
  val kamonAkka        = "io.kamon"                  %% "kamon-akka-http-2.5" % "1.0.1"
  val kamonDatadog     = "io.kamon"                  %% "kamon-datadog"       % "1.0.0"
}

// loads the server project at sbt startup
onLoad in Global := (onLoad in Global).value andThen { s: State => "project server" :: s
}

// -----------------------------------------------------------------------------
// common settings
// -----------------------------------------------------------------------------

lazy val commonSettings = Seq(
  scalaVersion := library.version.scala,
  organization := "org.lunatech",
  buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  buildInfoPackage := "lunatech",
  headerLicense := headerSettings,
  credentials += credentialsProvider(),
  fork in run := true,
  scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-language:_",
    "-target:jvm-1.8",
    "-encoding",
    "UTF-8",
    "-feature",
    "-Xfatal-warnings"
  ),
) ++ scalastyleSettings ++ scalafmtSettings ++ publishSettings

// -----------------------------------------------------------------------------
// modules settings
// -----------------------------------------------------------------------------

lazy val server = (project in file("server"))
  .enablePlugins(
    AutomateHeaderPlugin,
    SbtTwirl,
    JavaAppPackaging,
    DockerPlugin,
    BuildInfoPlugin,
    JavaAgent
  )
  .settings(commonSettings)
  .settings(
    parallelExecution in Test := false,
    packageName in Docker := "akka-http-example",
    daemonUser in Docker := "root",
    daemonGroup in Docker := "root",
    dockerRepository := None,
    dockerBaseImage := "parrotstream/ubuntu-java",
    dockerCommands ++= Seq(
      ExecCmd(
        "RUN",
        "chmod",
        "+x",
        s"${(defaultLinuxInstallLocation in Docker).value}/bin/${executableScriptName.value}"
      )
    ),
    PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value),
    PB.deleteTargetDirectory := false,
    javaAgents += library.aspectJWeaver,
    fork in run := false,
    mainClass in run := Some("org.lunatech.preprocessing.Main"),
    dependencyOverrides += library.log4jApi,
    libraryDependencies ++= Seq(
      library.akkaHttp,
      library.akkaHttpXml,
      library.akkaHttpSwagger,
      library.akkaHttpCirce,
      library.akkaStream,
      library.akkaActor,
      library.akkaStream,
      library.akkaSlf4j,
      library.circeCore,
      library.circeGeneric,
      library.circeParser,
      library.scalaGuice,
      library.scalaJsScripts,
      library.akkaTestKit,
      library.akkaStreamTestKit,
      library.akkaHttpTestKit,
      library.scalaTest,
      library.mockitoScala,
      library.elastic4sCore,
      library.elastic4sHttp,
      library.elastic4sCirce,
      library.elastic4sTestKit,
      library.elastic4sEmbedded,
      library.commonsIO,
      library.logback,
      library.log4jCore,
      library.log4jApi,
      library.log4jApiImpl,
      library.scalaBcrypt,
      library.jwtCore,
      library.jwtCirce,
      library.kamonAkka,
      library.kamonDatadog,
      library.jcodec,
      library.jcodecJavaSe
    ),
    WebKeys.packagePrefix in Assets := "public/",
    headerLicense := headerSettings
  )


// -----------------------------------------------------------------------------
// scalastyle settings
// -----------------------------------------------------------------------------

lazy val scalastyleSettings = Seq(
  scalastyleFailOnWarning := true
)

// -----------------------------------------------------------------------------
// header settings
// -----------------------------------------------------------------------------

lazy val headerSettings = Some(
  HeaderLicense.Custom(
    """|Copyright Lunatech 2019
       |""".stripMargin
  )
)

// -----------------------------------------------------------------------------
// scalafmt settings
// -----------------------------------------------------------------------------

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtOnCompile.in(Sbt) := true,
    scalafmtVersion := "1.4.0"
  )

// -----------------------------------------------------------------------------
// publish settings
// -----------------------------------------------------------------------------

val nexusHttpMethod     = Option(System.getenv("NEXUS_HTTP_METHOD")).getOrElse("https")
val nexusUrl            = Option(System.getenv("NEXUS_URL")).getOrElse("")
val nexusRepositoryPath = Option(System.getenv("NEXUS_REPOSITORY_PATH")).getOrElse("")
val nexusColonPort      = Option(System.getenv("NEXUS_PORT")).map(":" + _).getOrElse("")
val nexusUsername       = System.getenv("NEXUS_USERNAME")
val nexusPassword       = System.getenv("NEXUS_PASSWORD")
val nexusAddress        = s"$nexusHttpMethod://$nexusUrl$nexusColonPort"
val publishRepository = MavenRepository(
  "Sonatype Nexus Repository Manager",
  s"$nexusAddress/$nexusRepositoryPath"
)

def credentialsProvider(): Credentials = {
  val fileExists = (Path.userHome / ".sbt" / ".credentials").exists()

  if (fileExists) {
    Credentials(Path.userHome / ".sbt" / ".credentials")
  } else {
    Credentials(
      "Sonatype Nexus Repository Manager",
      nexusUrl,
      nexusUsername,
      nexusPassword
    )
  }
}

def isSnapshot: Boolean = nexusRepositoryPath.toLowerCase.contains("snapshot")

lazy val publishSettings = Seq(
  resolvers ++= Seq(
    "acdc-nexus" at s"$nexusAddress/repository/maven-public/"
  ),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := Some(publishRepository),
  // See; https://github.com/sbt/sbt/issues/3570
  updateOptions := updateOptions.value.withGigahorse(false)
)

// -----------------------------------------------------------------------------
// aliases
// -----------------------------------------------------------------------------

// SBT aliases to run multiple commands in a single call
//   Optionally add it:scalastyle if the project has integration tests
addCommandAlias(
  "styleCheck",
  "; scalafmt::test ; scalastyle ; test:scalastyle"
)

addCommandAlias(
  "scalafmt",
  "; server/scalafmt "
)

addCommandAlias(
  "clean",
  "; server/clean "
)

// addCommandAlias(
//   "test",
//   "; server/test ; sharedJVM/test"
// )

addCommandAlias(
  "test",
  "; server/test "
)
