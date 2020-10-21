name := "json_reader_nasedkin"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.4.7" % Provided,
  "org.json4s" %% "json4s-native" % "3.6.10"
)