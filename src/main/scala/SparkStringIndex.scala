import org.apache.spark.sql.SparkSession

class SparkStringIndex {

}

object SparkStringIndex {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.ml.feature.StringIndexer

    val spark = SparkSession.builder().master("local").getOrCreate()
    //    val spark = new SQLContext(sc)
    val df = spark.createDataFrame(
      Seq((0, "a"), (1, "b"), (2, "c"), (3, "a"), (4, "a"), (5, "c"))
    ).toDF("id", "category")

    val df2 = spark.createDataFrame(
      Seq((0, "a"), (1, "b"), (2, "c"), (5, "c"))
    ).toDF("id", "category")

    val indexer = new StringIndexer()
      .setInputCol("category")
      .setOutputCol("categoryIndex")

    val indexed = indexer.fit(df).transform(df2)
    indexed.show()
  }
}
