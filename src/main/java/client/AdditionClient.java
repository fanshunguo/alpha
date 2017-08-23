package client;

import com.telecom.dm.service.AdditionService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class AdditionClient {
    public static void main(String[] args) {
        TTransport transport = new TSocket("localhost", 9090);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            AdditionService.Client client = new AdditionService.Client(protocol);
            System.out.println(client.add(66, 34));
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
