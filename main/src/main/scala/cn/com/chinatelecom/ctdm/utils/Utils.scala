package cn.com.chinatelecom.ctdm.utils

import java.io.{File, InputStreamReader}
import java.net.URL
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Properties

import scala.collection.JavaConverters._

object Utils {

  def getPropertiesFromFile(file: File): Map[String, String] = {
    loadProperties(file.toURI.toURL)
  }

  def loadProperties(url: URL): Map[String, String] = {
    val inReader = new InputStreamReader(url.openStream(), UTF_8)
    try {
      val properties = new Properties()
      properties.load(inReader)
      properties.stringPropertyNames().asScala.map { k =>
        (k, properties.getProperty(k).trim())
      }.toMap
    } finally {
      inReader.close()
    }
  }

}
