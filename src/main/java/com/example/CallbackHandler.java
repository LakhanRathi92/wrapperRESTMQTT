/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author lakhan
 */
public class CallbackHandler implements MqttCallback {
        @Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}
