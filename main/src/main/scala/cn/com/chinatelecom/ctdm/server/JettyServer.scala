package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.utils.Logging
import org.eclipse.jetty.server.Server

class JettyServer extends Logging {
  val server = new Server()

  server.setStopAtShutdown(true)

}
