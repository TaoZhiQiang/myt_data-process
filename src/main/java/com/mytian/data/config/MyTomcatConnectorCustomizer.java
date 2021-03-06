package com.mytian.data.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Description:
 */
public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
    @Override
    public void customize(Connector connector) {
       Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        //设置最大连接数
        protocol.setMaxConnections(2000);
        //设置最大线程数
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);
    }
}
