/*
 * The MIT License
 *
 * Copyright 2025 lop.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.showcase;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import java.io.File;

@Named("sys")
@ApplicationScoped
public class SystemInfo {

    /* ===========================
       SYSTEM PROPERTIES
       =========================== */
    public Map<String, String> getSystemProperties() {
        return (Map) System.getProperties();
    }

    /* ===========================
       CPU & MEMORY
       =========================== */
    public int getCpuCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public long getUsedMemory() {
        return getTotalMemory() - getFreeMemory();
    }

    public long getAvailableMemory() {
        return getMaxMemory() - getUsedMemory();
    }

    public double getMemoryUsagePercent() {
        return (double) getUsedMemory() / getMaxMemory() * 100.0;
    }

    /* ===========================
       JVM UPTIME
       =========================== */
    public String getUptime() {
        long ms = ManagementFactory.getRuntimeMXBean().getUptime();
        Duration d = Duration.ofMillis(ms);

        long h = d.toHours();
        long m = d.toMinutesPart();
        long s = d.toSecondsPart();

        return h + "h " + m + "m " + s + "s";
    }

    /* ===========================
       THREAD INFO
       =========================== */
    public int getThreadCount() {
        return ManagementFactory.getThreadMXBean().getThreadCount();
    }

    public int getPeakThreadCount() {
        return ManagementFactory.getThreadMXBean().getPeakThreadCount();
    }

    public long getTotalStartedThreads() {
        return ManagementFactory.getThreadMXBean().getTotalStartedThreadCount();
    }

    /* ===========================
       GARBAGE COLLECTOR INFO
       =========================== */
    public List<GarbageCollectorMXBean> getGcInfo() {
        return ManagementFactory.getGarbageCollectorMXBeans();
    }

    /* ===========================
       DISK INFO
       =========================== */
    public File[] getRoots() {
        return File.listRoots();
    }

    public long getDiskTotal(File root) {
        return root.getTotalSpace();
    }

    public long getDiskFree(File root) {
        return root.getFreeSpace();
    }

    public long getDiskUsed(File root) {
        return root.getTotalSpace() - root.getFreeSpace();
    }

    /* ===========================
       TIMESTAMP
       =========================== */
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
