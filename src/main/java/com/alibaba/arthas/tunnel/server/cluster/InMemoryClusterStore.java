package com.alibaba.arthas.tunnel.server.cluster;

import com.alibaba.arthas.tunnel.server.AgentClusterInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @author hengyunabc 2020-12-02
 */
public class InMemoryClusterStore implements TunnelClusterStore {

    private static final Cache<String, AgentClusterInfo> cache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).initialCapacity(20).build();


    @Override
    public AgentClusterInfo findAgent(String agentId) {
        return cache.getIfPresent(agentId);
    }

    @Override
    public void removeAgent(String agentId) {
        cache.invalidate(agentId);
    }

    @Override
    public void addAgent(String agentId, AgentClusterInfo info, long timeout, TimeUnit timeUnit) {
        cache.put(agentId, info);
    }

    @Override
    public Collection<String> allAgentIds() {
        return cache.asMap().keySet();
    }

    @Override
    public Map<String, AgentClusterInfo> agentInfo(String appName) {

        Map<String, AgentClusterInfo> result = new TreeMap<>();

        for (Entry<String, AgentClusterInfo> entry : cache.asMap().entrySet()) {
            String agentId = entry.getKey();
            if ("null".equals(appName) || agentId.startsWith(appName)) {
                result.put(agentId, entry.getValue());
            }
        }

        return result;

    }

}
