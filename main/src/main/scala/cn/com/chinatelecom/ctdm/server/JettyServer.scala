package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.CtdmConf
import cn.com.chinatelecom.ctdm.utils.Logging
import org.eclipse.jetty.server.handler.{DefaultHandler, HandlerCollection}
import org.eclipse.jetty.server.{Server, ServerConnector}

class JettyServer(conf: CtdmConf) extends Logging {
  val server = new Server()
  server.setStopAtShutdown(true)

  private def createConnector() = {
    val connect = new ServerConnector(server)
    connect.setHost(conf.get(CtdmConf.SERVER_HOST))
    connect.setPort(conf.getInt(CtdmConf.SERVER_PORT))
    connect
  }

  private def usedHandlers = {
    Array(CtdmWebServer.getInstance(conf),
      ThriftServer.getInstance(conf),
      new DefaultHandler)
  }

  def start(): Unit = {
    info(s"start container")
    val connector = createConnector()
    server.setConnectors(Array(connector))
    val handlers = new HandlerCollection()
    usedHandlers.foreach(handlers.addHandler)
    server.setHandler(handlers)
    server.start()
    server.join()
  }

  def stop(): Unit = {
    server.stop()
  }

}
