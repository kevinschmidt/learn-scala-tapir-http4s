// sbt-scalariform
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

// Dependencies
val Http4sVersion = "0.21.7"
val CirceVersion = "0.13.0"
val CirceEnumeratumVersion = "1.6.1"
val TapirVersion = "0.16.11"
val LogbackVersion = "1.2.3"
val Specs2Version = "4.10.2"

val rootDependencies = Seq(
  "org.http4s"                  %% "http4s-blaze-server"      % Http4sVersion,
  "org.http4s"                  %% "http4s-circe"             % Http4sVersion,
  "org.http4s"                  %% "http4s-dsl"               % Http4sVersion,
  "io.circe"                    %% "circe-generic"            % CirceVersion,
  "com.beachape"                %% "enumeratum-circe"         % CirceEnumeratumVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-core"               % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server"      % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s"  % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-enumeratum"         % TapirVersion,
  "io.circe"                    %% "circe-generic"            % CirceVersion,
  "com.beachape"                %% "enumeratum-circe"         % CirceEnumeratumVersion,
  "ch.qos.logback"              %  "logback-classic"          % LogbackVersion
)
val testDependencies = Seq (
  "org.specs2"                  %% "specs2-core"              % Specs2Version % "test",
)
val dependencies =
  rootDependencies ++
    testDependencies

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:_",
  "-Xlint:_"
)

val formatting =
  FormattingPreferences()
    .setPreference(AlignParameters, false)
    .setPreference(AlignSingleLineCaseStatements, false)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40)
    .setPreference(CompactControlReadability, false)
    .setPreference(CompactStringConcatenation, false)
    .setPreference(DanglingCloseParenthesis, Force)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(FormatXml, true)
    .setPreference(IndentLocalDefs, false)
    .setPreference(IndentPackageBlocks, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
    .setPreference(PreserveSpaceBeforeArguments, false)
    .setPreference(RewriteArrowSymbols, false)
    .setPreference(SingleCasePatternOnNewline, false)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesAroundMultiImports, false)
    .setPreference(SpacesWithinPatternBinders, true)

val settings = Seq(
  name := "TapirHttp4s",
  organization := "learnscala",
  scalaVersion := "2.13.2",
  scalaBinaryVersion := "2.13",
  libraryDependencies ++= dependencies,
  // build info
  buildInfoKeys := Seq[BuildInfoKey](name, version),
  buildInfoPackage := "learnscala.tapirhttp4s",
  // formatting
  ScalariformKeys.preferences := formatting
)

lazy val root =
  project
    .in(file("."))
    .enablePlugins(BuildInfoPlugin)
    .settings(settings:_*)