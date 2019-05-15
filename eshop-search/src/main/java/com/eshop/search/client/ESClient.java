package com.eshop.search.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ESClient {

    private static TransportClient client = null;

    public static TransportClient getClient() throws Exception{
        // 设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.198.131"), 9300));
        return client;
    }

    public static void close(){
        if(client!=null){
            client.close();
        }
    }
}
