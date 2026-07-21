package com.docker_networks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemMetricController {

    @GetMapping("/info")
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> info = new HashMap<>();
        
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            info.put("hostName", localHost.getHostName());
            info.put("hostAddress/IP", localHost.getHostAddress());
        } catch (Exception e) {
            info.put("error", "Could not resolve host info");
        }

        // JVM Metrics
        Runtime runtime = Runtime.getRuntime();
        info.put("availableProcessors", runtime.availableProcessors());
        info.put("freeMemoryMB", runtime.freeMemory() / (1024 * 1024));
        info.put("totalMemoryMB", runtime.totalMemory() / (1024 * 1024));

        return info;
    }
}