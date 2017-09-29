package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.CtdmConf

class CtdmSystem {

}

object CtdmSystem {
  def main(args: Array[String]): Unit = {
    val conf = new CtdmConf().loadFromFile("ctdm.conf")
    val server = new JettyServer(conf)
    server.start()
  }
}
