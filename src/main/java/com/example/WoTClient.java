package com.example;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author lakhan
 * Single MQTT connection, multiple subscriptions against each query 
 * 
 */
public class WoTClient {
        static MqttAsyncClient sampleClient;
	static String WoTBroker;
	static String clientId;
	static MemoryPersistence persistence;
	static int qos = 0;
	
        static {	
	    WoTBroker    = "tcp://0.0.0.0:1883";
	    clientId     = "RestFullWebService";
	    persistence = new MemoryPersistence();
	    try {
	        sampleClient = new MqttAsyncClient(WoTBroker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        System.out.println("Connecting to broker: "+WoTBroker);
	        sampleClient.connect(connOpts);
	        while(!sampleClient.isConnected())
                       ;
                System.out.println("Connected");
	    } catch(MqttException me) {
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        me.printStackTrace();
	    }
	}
        
        public boolean handleSubscribe(String query){
		try {
			sampleClient.subscribe("$SYS/SPARQL/"+query, qos);
		} catch (MqttException e) {
			e.printStackTrace();
			return false;
		}
                sampleClient.setCallback(new MqttCallback(){
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        String[] topics = topic.split("/");
                        String q = topics[2];
                        String res = message.toString();
                        StoreMap.putResult(q, res);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
		return true;
	}

}
