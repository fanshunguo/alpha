package cn.com.chinatelecom.ctdm

import java.io.File
import java.lang.{Boolean => JBoolean, Long => JLong}

import cn.com.chinatelecom.ctdm.common.ClientConf
import cn.com.chinatelecom.ctdm.common.ClientConf.ConfEntry
import cn.com.chinatelecom.ctdm.utils.Utils

class CtdmConf(loadDefaults: Boolean) extends ClientConf[CtdmConf](null) {

  lazy val defaultEnv: Map[String, String] = loadDefaultEnv

  def this() = this(true)

  if (loadDefaults) {
    loadFromMap(defaultEnv)
    loadFromMap(sys.props)
  }

  val configDir: Option[File] = {
    sys.env.get("CTDM_CONF_DIR")
      .orElse(sys.env.get("CTDM_HOME").map(path => s"$path${File.separator}conf"))
      .map(new File(_))
      .filter(_.exists())
  }

  def loadFromFile(name: String): CtdmConf = {
    getConfigFile(name)
      .map(Utils.getPropertiesFromFile)
      .foreach(loadFromMap)
    this
  }

  private def loadDefaultEnv: Map[String, String] = {
    Option(new File("conf/env.conf"))
      .map(f => if (f.exists()) Utils.getPropertiesFromFile(f) else Map[String, String]())
      .getOrElse(Map[String, String]())
  }

  private def getConfigFile(name: String): Option[File] = {
    configDir.map(new File(_, name)).filter(_.exists())
  }

  private def loadFromMap(map: Iterable[(String, String)]): Unit = {
    map.foreach { case (k, v) =>
      if (k.startsWith("ctdm.")) {
        set(k, v)
      }
    }
  }

}

object CtdmConf {

  case class Entry(override val key: String, override val dflt: AnyRef) extends ConfEntry

  object Entry {
    def apply(key: String, dflt: Boolean): Entry = Entry(key, dflt: JBoolean)
    def apply(key: String, dflt: Int): Entry = Entry(key, dflt: Integer)
    def apply(key: String, dflt: Long): Entry = Entry(key, dflt: JLong)
  }

  val SERVER_HOST = Entry("ctdm.server.host", "0.0.0.0")
  val SERVER_PORT = Entry("ctdm.server.port", 9099)

}