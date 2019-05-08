// If you prefer using Scala.js 0.6.x, uncomment the following plugins instead:
addSbtPlugin("com.vmunier"  % "sbt-web-scalajs" % "1.0.8-0.6")
addSbtPlugin("org.scala-js" % "sbt-scalajs"     % "0.6.23")

//addSbtPlugin("com.lihaoyi"               % "workbench"                 % "0.4.0")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.12.0")

// fast development turnaround when using sbt ~reStart
addSbtPlugin("io.spray"                % "sbt-revolver"             % "0.9.1")
addSbtPlugin("com.eed3si9n"            % "sbt-assembly"             % "0.14.7")
addSbtPlugin("com.typesafe.sbt"        % "sbt-twirl"                % "1.3.15")
addSbtPlugin("org.portable-scala"      % "sbt-scalajs-crossproject" % "0.5.0")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.6")

// Coding style
addSbtPlugin("org.scalastyle"    %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.wartremover"   % "sbt-wartremover"        % "2.2.1")
addSbtPlugin("de.heikoseeberger" % "sbt-header"             % "5.0.0")
addSbtPlugin("com.lucidchart"    % "sbt-scalafmt"           % "1.15")

// CI/CD
addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.1.11")

// Get build version
addSbtPlugin("com.eed3si9n"            % "sbt-buildinfo"            % "0.9.0")

// kamon integration
addSbtPlugin("io.kamon" % "sbt-aspectj-runner" % "1.1.0")
addSbtPlugin("com.lightbend.sbt" % "sbt-javaagent" % "0.1.4")

// protobuf
addSbtPlugin("com.thesamet"      % "sbt-protoc" % "0.99.19")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.2"
