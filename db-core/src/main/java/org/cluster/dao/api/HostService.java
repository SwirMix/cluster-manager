package org.cluster.dao.api;

import org.cluster.dao.Result;
import org.cluster.dao.internal.HostRq;
import org.cluster.dao.pagination.Pagination;
import org.cluster.dto.host.Host;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HostService {
    /**
     * @param hostRq - тело веб запроса
     * @param login - login текущего пользователя, отправляющего запрос
     * @return описание результата отработки сохранения
     */
    public Result saveHost(HostRq hostRq, String login);

    /**
     * @param host - обновление информации о хосте
     * @return
     */
    public Result updateHost(Host host);

    /**
     * @param host - удаляемый хост
     * @return
     */
    public Result deleteHost(Host host);

    /**
     * @param id - id удаляемого хоста
     * @return
     */
    public Result deleteHost(long id);

    /**
     * @param id - указываем id хоста который хотим взять в пользование.
     * @param clusterName - передаем имя кластера, который сейчас берет хост в работу
     * @return
     */
    public Result lockHost(long id, String clusterName);

    /**
     * @param id - указываем id хоста который хотим осавободить
     * @return
     */
    public Result unlockHost(long id);

    /**
     * @param size - размер необходимого кластера
     * @return возвращаем хосты которые сейчас свободны и которые можно взять в работу.
     */
    public ArrayList<Host> getFreeHosts(long size) throws Exception;

    /**
     * @param cluster - список хостов которые мы берем для кластера
     * @param clusterName - имя кластера который сейчас занимает данные хосты
     * @return результат отработки запроса
     */
    public Result lockHostsForCluster(ArrayList<Host> cluster, String clusterName);

    /**
     * @param cluster - освобождение хостов
     * @return
     */
    public Result unlockHosts(ArrayList<Host> cluster);

    /**
     * @param job - id задачи которая заблокировала хосты
     * @return
     */
    public ArrayList<Host> getHostsByJob(String job);

    /**
     * @param page
     * @param size
     * @return
     * @throws SQLException
     */
    Pagination<Host> getPageData(int page, int size) throws SQLException;
}
