package cn.com.chinatelecom.ctdm.server

import cn.com.chinatelecom.ctdm.CtdmConf
import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.webapp.WebAppContext

object VueWebServer {
  private var instance: Handler = _

  private def createWebServer(conf: CtdmConf): Unit = {
//    val resource = new ResourceHandler()
//    resource.setDirectoriesListed(true)
//    resource.setResourceBase(conf.get(CtdmConf.VUE_RESOURCE_PATH))
//    resource.setWelcomeFiles(Array("index.html"))

//    val resources = new ResourceCollection()
//    resources.setResources(Array(
//      new PathResource(new File(conf.get(CtdmConf.VUE_RESOURCE_PATH))),
//      new PathResource(new File("/home/fan/workspace/demos/alpha/webapp/src/main/menu/dist/static/js")),
//      new PathResource(new File("/home/fan/workspace/demos/alpha/webapp/src/main/menu/dist/static/css"))))

    val webApp = new WebAppContext()
    webApp.setContextPath(conf.get(CtdmConf.VUE_WEB_APP_PATH))
//    webApp.setBaseResource(resources)
//    webApp.setHandler(resource)
    webApp.setResourceBase(conf.get(CtdmConf.VUE_RESOURCE_PATH))
//    webApp.setWelcomeFiles(Array("index.html"))

    instance = webApp
  }

  def getInstance(conf: CtdmConf): Handler = {
    if (instance == null) {
      createWebServer(conf)
    }
    instance
  }

}
