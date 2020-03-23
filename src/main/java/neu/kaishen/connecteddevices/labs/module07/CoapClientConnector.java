package neu.kaishen.connecteddevices.labs.module07;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

public class CoapClientConnector {
	URI uri = null;
	CoapClient client =null;
	public CoapClientConnector() {
		// TODO Auto-generated constructor stub
	}

	public void start() throws URISyntaxException {
		uri = new URI("localhost:5683/temp");
		client = new CoapClient(uri);
		CoapResponse response = client.get();
		System.out.println(response.getResponseText());
	}
}
