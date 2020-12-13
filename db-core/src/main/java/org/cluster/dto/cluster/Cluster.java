package org.cluster.dto.cluster;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.cluster.dto.host.Host;

import java.util.ArrayList;
import java.util.Date;

@DatabaseTable(tableName = "Cluster")
public class Cluster {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String clusterName;
    @DatabaseField
    private String description;
    @DatabaseField
    private String creator;
    @DatabaseField
    private long used;
    @DatabaseField
    private String hosts;
    @DatabaseField
    private long createDate;
    @DatabaseField
    private long destroyDate;

    public Date getDestroyDate() {
        return  new Date(destroyDate);
    }

    public long longDestroy(){
        return destroyDate;
    }

    public void setDestroyDate(long destroyDate) {
        this.destroyDate = destroyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setHosts(ArrayList<Host> hosts){
        StringBuilder stringBuilder = new StringBuilder();
        for (Host host : hosts) {
            stringBuilder.append(host.getHost() + ",");
        }
        this.hosts = stringBuilder.toString();
    }

    public Cluster(){
        this.createDate = new Date().getTime();
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public Date getCreateDate() {
        return new Date(createDate);
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
