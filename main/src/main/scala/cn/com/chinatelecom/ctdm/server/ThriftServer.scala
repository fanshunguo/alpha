package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.CtdmConf
import com.telecom.dm.service.AdditionService
import org.apache.thrift.TProcessor
import org.apache.thrift.server.TServlet
import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import protocol.Factory
import service.AdditionServiceHandler

object ThriftServer {

  private var instance: Handler = _

  private class CtdmThriftServlet(conf: CtdmConf, processor: TProcessor, factory: Factory)
    extends TServlet(processor, factory) {}

  private def createServer(conf: CtdmConf): Unit = {
    val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/thrift")
    context.addServlet(
      new ServletHolder(
        new CtdmThriftServlet(conf,
          new AdditionService.Processor(new AdditionServiceHandler),
          new Factory)), "/*")
    instance = context
  }

  def getInstance(conf: CtdmConf): Handler = {
    if (instance == null) {
      createServer(conf)
    }
    instance
  }

}
