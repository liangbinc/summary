package com.lbc.mo.entity;

import java.util.Date;

public class ContainerStats {
    private Integer id;

    private String user;

    private String appId;

    private String containerName;

    private Date fetchTime;

    private Date startTime;

    private Date endTime;

    private String cpuTotalUsage;

    private String cpuSysUsage;

    private String preCpuTotalUsage;

    private String preCpuSysUsage;

    private Integer cpus;

    private String memUsage;

    private String memStatsCache;

    private String memLimit;

    private Integer currentCpu;

    private Integer currentGpu;

    private Integer currentMemory;

    private Integer gpuLabels;

    private Integer gpuRate0;

    private Integer gpuRate1;

    private Integer gpuRate2;

    private Integer gpuRate3;

    private Integer gpuRate4;

    private Integer gpuRate5;

    private Integer gpuRate6;

    private Integer gpuRate7;

    private String netI;

    private String netO;

    private String blkI;

    private String blkO;

    private String containerState;

    public Date getStartTime() {
        if (startTime != null) return (Date) startTime.clone();
        return null;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime == null ? null : (Date) startTime.clone();
    }

    public Date getEndTime() {
        if (endTime != null) return (Date) endTime.clone();
        return null;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime == null ? null : (Date) endTime.clone();
    }

    public String getContainerState() {
        return containerState;
    }

    public void setContainerState(String containerState) {
        this.containerState = containerState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName == null ? null : containerName.trim();
    }

    public Date getFetchTime() {
        if (fetchTime != null) return (Date) fetchTime.clone();
        return null;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime == null ? null : (Date) fetchTime.clone();
    }

    public String getCpuTotalUsage() {
        return cpuTotalUsage;
    }

    public void setCpuTotalUsage(String cpuTotalUsage) {
        this.cpuTotalUsage = cpuTotalUsage == null ? null : cpuTotalUsage.trim();
    }

    public String getCpuSysUsage() {
        return cpuSysUsage;
    }

    public void setCpuSysUsage(String cpuSysUsage) {
        this.cpuSysUsage = cpuSysUsage == null ? null : cpuSysUsage.trim();
    }

    public String getPreCpuTotalUsage() {
        return preCpuTotalUsage;
    }

    public void setPreCpuTotalUsage(String preCpuTotalUsage) {
        this.preCpuTotalUsage = preCpuTotalUsage == null ? null : preCpuTotalUsage.trim();
    }

    public String getPreCpuSysUsage() {
        return preCpuSysUsage;
    }

    public void setPreCpuSysUsage(String preCpuSysUsage) {
        this.preCpuSysUsage = preCpuSysUsage == null ? null : preCpuSysUsage.trim();
    }

    public Integer getCpus() {
        return cpus;
    }

    public void setCpus(Integer cpus) {
        this.cpus = cpus;
    }

    public String getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(String memUsage) {
        this.memUsage = memUsage == null ? null : memUsage.trim();
    }

    public String getMemStatsCache() {
        return memStatsCache;
    }

    public void setMemStatsCache(String memStatsCache) {
        this.memStatsCache = memStatsCache == null ? null : memStatsCache.trim();
    }

    public String getMemLimit() {
        return memLimit;
    }

    public void setMemLimit(String memLimit) {
        this.memLimit = memLimit == null ? null : memLimit.trim();
    }

    public Integer getCurrentCpu() {
        return currentCpu;
    }

    public void setCurrentCpu(Integer currentCpu) {
        this.currentCpu = currentCpu;
    }

    public Integer getCurrentGpu() {
        return currentGpu;
    }

    public void setCurrentGpu(Integer currentGpu) {
        this.currentGpu = currentGpu;
    }

    public Integer getCurrentMemory() {
        return currentMemory;
    }

    public void setCurrentMemory(Integer currentMemory) {
        this.currentMemory = currentMemory;
    }

    public Integer getGpuLabels() {
        return gpuLabels;
    }

    public void setGpuLabels(Integer gpuLabels) {
        this.gpuLabels = gpuLabels;
    }

    public Integer getGpuRate0() {
        return gpuRate0;
    }

    public void setGpuRate0(Integer gpuRate0) {
        this.gpuRate0 = gpuRate0;
    }

    public Integer getGpuRate1() {
        return gpuRate1;
    }

    public void setGpuRate1(Integer gpuRate1) {
        this.gpuRate1 = gpuRate1;
    }

    public Integer getGpuRate2() {
        return gpuRate2;
    }

    public void setGpuRate2(Integer gpuRate2) {
        this.gpuRate2 = gpuRate2;
    }

    public Integer getGpuRate3() {
        return gpuRate3;
    }

    public void setGpuRate3(Integer gpuRate3) {
        this.gpuRate3 = gpuRate3;
    }

    public Integer getGpuRate4() {
        return gpuRate4;
    }

    public void setGpuRate4(Integer gpuRate4) {
        this.gpuRate4 = gpuRate4;
    }

    public Integer getGpuRate5() {
        return gpuRate5;
    }

    public void setGpuRate5(Integer gpuRate5) {
        this.gpuRate5 = gpuRate5;
    }

    public Integer getGpuRate6() {
        return gpuRate6;
    }

    public void setGpuRate6(Integer gpuRate6) {
        this.gpuRate6 = gpuRate6;
    }

    public Integer getGpuRate7() {
        return gpuRate7;
    }

    public void setGpuRate7(Integer gpuRate7) {
        this.gpuRate7 = gpuRate7;
    }

    public String getNetI() {
        return netI;
    }

    public void setNetI(String netI) {
        this.netI = netI == null ? null : netI.trim();
    }

    public String getNetO() {
        return netO;
    }

    public void setNetO(String netO) {
        this.netO = netO == null ? null : netO.trim();
    }

    public String getBlkI() {
        return blkI;
    }

    public void setBlkI(String blkI) {
        this.blkI = blkI == null ? null : blkI.trim();
    }

    public String getBlkO() {
        return blkO;
    }

    public void setBlkO(String blkO) {
        this.blkO = blkO == null ? null : blkO.trim();
    }
}
