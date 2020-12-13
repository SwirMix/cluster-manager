package org.cluster;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.cluster.dao.pagination.Pagination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoService<V> {
    protected String url;
    protected ConnectionSource source;
    protected Dao<V, String> dao;

    public Dao<V, String> getDao(){
        return dao;
    }

    public long getCount() throws SQLException {
        return dao.countOf();
    }

    public AbstractDaoService(String dbUrl) throws SQLException {
        this.url = dbUrl;
        source = new JdbcConnectionSource(this.url);
        dao = daoInit(source);
    }

    public abstract Dao<V, String> daoInit(ConnectionSource source) throws SQLException;

    public List<V> getAll() throws SQLException {
        return dao.queryForAll();
    }
    public void save(V object) throws SQLException {
        dao.createOrUpdate(object);
    }
    public void delete(V object) throws SQLException {
        dao.delete(object);
    }
    public V getUniqueDataFor(String key, String value) throws SQLException {
        V object = null;
        try {
            object = dao.queryForEq(key, value).get(0);
        } catch (Exception e){

        }
        return object;
    }

    public List<V> getDataFor(String key, String value) throws SQLException {
        return dao.queryForEq(key, value);
    }
    public List<V> getDataFor(String key, String value, long limit) throws SQLException {
        QueryBuilder builder = dao.queryBuilder();
        builder.limit(limit).where().eq(key, value);
        return dao.query(builder.prepare());
    }

    public long getPageCount(int pageSize) throws SQLException {
        long items = getCount();
        double pages = new Double(items).doubleValue() / new Double(pageSize).doubleValue();
        System.out.println(pages);
        Long result = new Double(Math.ceil(pages)).longValue();
        return result;
    }

    public boolean idExist(String id) throws SQLException {
        return dao.idExists(id);
    }

    public void deleteById(String id) throws SQLException {
        dao.deleteById(id);
    }

    public void deleteByUniqueFiled(String key, String value) throws SQLException {
        V object = null;
        try {
            object = dao.queryForEq(key, value).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dao.delete(object);
    }

    public Pagination<V> getPageData(int page, int size) throws SQLException {
        long count = getPageCount(size);
        long skipCount = (page-1) * size;
        ArrayList<V> data = new ArrayList<>();
        long total = dao.countOf();

        CloseableIterator<V> iterator = dao.queryBuilder().iterator();
        for (int i = 0; i < skipCount-1; i++){
           iterator.next();
        }
        for (int i = 0; i < size; i++) {
            try {
                iterator.next();
                data.add(iterator.current());
            } catch (Exception e) {
                Pagination<V> pagination = new Pagination<V>(page, count);
                pagination.setData(data);
                pagination.setTotalItems(total);
                return pagination;
            }

        }

        Pagination<V> pagination = new Pagination<V>(page, count);
        pagination.setData(data);
        return pagination;
    }

    public Pagination<V> getPageDataByField(int page, int size, String key, String value) throws SQLException {
        long count = getPageCount(size);
        long skipCount = (page-1) * size;
        ArrayList<V> data = new ArrayList<>();
        long total = dao.countOf();

        CloseableIterator<V> iterator = dao.queryBuilder().iterator();
        for (int i = 0; i < skipCount-1; i++){
            iterator.next();
        }
        for (int i = 0; i < size; i++) {
            try {
                iterator.next();
                data.add(iterator.current());
            } catch (Exception e) {
                Pagination<V> pagination = new Pagination<V>(page, count);
                pagination.setData(data);
                pagination.setTotalItems(total);
                return pagination;
            }

        }

        Pagination<V> pagination = new Pagination<V>(page, count);
        pagination.setData(data);
        return pagination;
    }
}
