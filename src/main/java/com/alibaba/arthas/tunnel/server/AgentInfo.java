package com.alibaba.arthas.tunnel.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hengyunabc 2019-08-27
 */
@Getter
@Setter
public class AgentInfo {

    @JsonIgnore
    private ChannelHandlerContext channelHandlerContext;
    private String host;
    private int port;
    private String arthasVersion;

    private String appName;

}
