package org.cluster.dao.pagination;

import java.util.ArrayList;

public class Pagination<V>{
    private int current;
    private long totalPages;
    private long totalItems;
    private ArrayList<V> data;

    public Pagination(int current, long totalPages){
        this.current = current;
        this.totalPages = totalPages;
        this.data = new ArrayList<V>();
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<V> getData() {
        return data;
    }

    public void setData(ArrayList<V> data) {
        this.data = data;
    }
}
