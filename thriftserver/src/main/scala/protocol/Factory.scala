package protocol

import org.apache.thrift.protocol.{TBinaryProtocol, TJSONProtocol, TProtocol, TProtocolFactory}
import org.apache.thrift.transport.TTransport

class Factory extends TProtocolFactory {
  override def getProtocol(trans: TTransport): TProtocol = {
     new TJSONProtocol(trans)
//    new TBinaryProtocol(trans)
  }
}
