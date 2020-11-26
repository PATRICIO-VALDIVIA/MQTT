package com.example.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {

    public static String MQTTHOST = "tailor.cloudmqtt.com";
    public static String USER = "vgahbxxj";
    public static String PASSWORD = "1hB0nkkCiq";
    MqttAndroidClient client;


    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(getBaseContext(), MQTTHOST, clientId);
        final MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USER);
        options.setPassword(PASSWORD.toCharArray());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clientId = MqttClient.generateClientId();
                MqttAndroidClient client =
                        new MqttAndroidClient(getBaseContext(), "tcp://broker.hivemq.com:1883",
                                clientId);

                try {
                    IMqttToken token = client.connect();
                    token.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            // We are connected
                            Toast.makeText(getBaseContext(), "Conectado", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            // Something went wrong e.g. connection timeout or firewall problems
                            Toast.makeText(getBaseContext(), "Fallo conexion", Toast.LENGTH_LONG).show();

                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}