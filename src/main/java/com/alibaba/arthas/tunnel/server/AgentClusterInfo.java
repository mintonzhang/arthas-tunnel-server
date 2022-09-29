package com.alibaba.arthas.tunnel.server;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hengyunabc 2020-10-30
 */
@Getter
@Setter
public class AgentClusterInfo {
    /**
     * agent本身以哪个ip连接到 tunnel server
     */
    private String host;
    private int port;
    private String arthasVersion;

    /**
     * agent 连接到的 tunnel server 的ip
     */
    private String clientConnectHost;

    private String appName;


    public AgentClusterInfo(AgentInfo agentInfo, String clientConnectHost) {
        this.host = agentInfo.getHost();
        this.port = agentInfo.getPort();
        this.arthasVersion = agentInfo.getArthasVersion();
        this.appName = agentInfo.getAppName();
        this.clientConnectHost = clientConnectHost;
    }

}
