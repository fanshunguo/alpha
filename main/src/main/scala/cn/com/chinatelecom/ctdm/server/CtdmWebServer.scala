package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.CtdmConf
import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.webapp.WebAppContext

object CtdmWebServer {

  private var instance: Handler = _

  private def createWebServer(conf: CtdmConf): Unit = {
    val webApp = new WebAppContext()
    webApp.setContextPath(conf.get(CtdmConf.WEB_APP_CONTEXT_PATH))
    webApp.setWar(conf.get(CtdmConf.WEB_APP_WAR_PATH))

    instance = webApp
  }

  def getInstance(conf: CtdmConf): Handler = {
    if (instance == null) {
      createWebServer(conf)
    }
    instance
  }

}

