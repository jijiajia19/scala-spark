package com.jacle.sstream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder

object FilterStream
{
  def main(args: Array[String]): Unit =
  {
    val conf=new SparkConf().setMaster("local[4]").setAppName("filterStreaming")
    val ssconf=new StreamingContext(conf,Seconds(2))

    val groupId="realtime_ss1"

    val kafkaParam=Map[String,String](
      "metadata.broker.list" ->"s202:9092,s203:9092,s204:9092",
      "serializer.class" -> "kafka.serializer.StringEncoder",
      "groupid"->groupId,
      "fetch.message.max.bytes" -> "10485760"
    )

    val topics = Set("sspark")
    val stream=KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssconf,kafkaParam,topics)
    print(stream+"--------------")
    stream.map(x=>x._2).print()

    ssconf.start()
    ssconf.awaitTermination()


  }

}
