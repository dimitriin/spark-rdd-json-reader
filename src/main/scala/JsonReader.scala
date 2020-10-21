import org.apache.spark.sql.SparkSession
import org.json4s._
import org.json4s.jackson.JsonMethods._

object JsonReader extends App {
  if (args.length == 0) {
    println("Data file path argument missed")
    System.exit(1)
  }

  val session = SparkSession.builder().getOrCreate()

  import session.implicits._

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
