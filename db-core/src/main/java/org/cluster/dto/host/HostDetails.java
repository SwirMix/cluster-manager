package org.cluster.dto.host;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Host")
public class HostDetails {
    @DatabaseField(generatedId = false)
    private long id;
    @DatabaseField
    private String cpu;//информация о ЦПЦ
    @DatabaseField
    private String ram;
    @DatabaseField
    private String storage;
    @DatabaseField
    private String type;//тип машины. Хардовая, виртуальная и т.п.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
