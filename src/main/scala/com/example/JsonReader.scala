package com.example

import org.apache.spark.sql.SparkSession
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

object JsonReader extends App {
  if (args.length == 0) {
    println("Data file path argument missed")
    System.exit(1)
  }

  val session = SparkSession.builder().getOrCreate()

  val rowsRdd = session.sparkContext.textFile(args(0))

  implicit val formats: DefaultFormats.type = DefaultFormats

  case class DataRow(
                      id: Option[Int] = None,
                      country: Option[String] = None,
                      points: Option[Int] = None,
                      price: Option[Double] = None,
                      title: Option[String] = None,
                      variety: Option[String] = None,
                      winery: Option[String] = None
                    )

  rowsRdd.map(s => parse(s)).map(j => j.extract[DataRow]).foreach(println)
}
