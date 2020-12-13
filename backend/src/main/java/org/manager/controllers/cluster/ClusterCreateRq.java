package org.manager.controllers.cluster;

import java.util.ArrayList;

public class ClusterCreateRq {
    private int size;
    private String name;
    private ArrayList<String> hosts;
    private String desc;
    private long time;
    private Time type;

    public Time getType() {
        return type;
    }

    public void setType(Time type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getHosts() {
        return hosts;
    }

    public void setHosts(ArrayList<String> hosts) {
        this.hosts = hosts;
    }

    public enum Time{
        MINUTES,
        HOURS,
        DAYS
    }
}
