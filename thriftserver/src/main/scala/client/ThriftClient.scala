package client

import com.telecom.dm.service.AdditionService.Client
import org.apache.thrift.transport.THttpClient
import protocol.Factory

object ThriftClient {

  private def getClient(prefix: String) = {
    val httpClient = new THttpClient(prefix)
    new Client(new Factory().getProtocol(httpClient))
  }

  def main(args: Array[String]): Unit = {
    val client = getClient("http://localhost:9099/thrift/add")
    println(client.add(12, 88))
  }

}
