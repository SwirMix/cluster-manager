package org.cluster.dao.api;

import com.j256.ormlite.dao.CloseableIterator;
import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dao.Result;
import org.cluster.dao.Status;
import org.cluster.dao.internal.HostRq;
import org.cluster.dao.pagination.Pagination;
import org.cluster.dto.account.Account;
import org.cluster.dto.host.Host;
import org.cluster.dto.host.HostsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class InternalHostsApi implements HostService{
    private DataStorageConfiguration dataStorageConfiguration;

    public InternalHostsApi(){

    }

    public InternalHostsApi(DataStorageConfiguration dataStorageConfiguration) {
        this.dataStorageConfiguration = dataStorageConfiguration;
    }

    public DataStorageConfiguration getDataStorageConfiguration() {
        return dataStorageConfiguration;
    }

    public void setDataStorageConfiguration(DataStorageConfiguration dataStorageConfiguration) {
        this.dataStorageConfiguration = dataStorageConfiguration;
    }

    @Override
    public Result saveHost(HostRq hostRq, String login) {
        try {
            Account account = dataStorageConfiguration.getAccountService().getUniqueDataFor("login", login);
            try {
                if (account==null) throw new NullPointerException();
            } catch (Exception e) {
                return new Result(Status.ERROR, "invalid login: " + login);
            }
            Host host = new Host();
            host.setHost(hostRq.getHostIp());
            host.setDnsName(hostRq.getDnsName());
            host.setSsh_user(hostRq.getSsh_user());
            host.setSsh_pass(hostRq.getSsh_pass());
            host.setDescription(hostRq.getDescription());
            host.setCreator(account.getLogin());
            host.setCreateDate(new Date().getTime());

            dataStorageConfiguration.getHostsService().save(host);
            return new Result(Status.SUCCESS, "host was saved");
        } catch (SQLException throwables) {
            return new Result(Status.ERROR, "save host error ");
        }
    }

    @Override
    public Result updateHost(Host host) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        try {
            if (hostsService.idExist(Long.toString(host.getId()))){
                hostsService.save(host);
                return new Result(Status.SUCCESS, "информация о хосте обновлена.");
            } else {
                return new Result(Status.ERROR, "неизвестный id хоста. Данный хост не существует");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "ошибка проверки наличия хоста с id:" + host.getHost());
        }
    }

    @Override
    public Result deleteHost(Host host) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        try {
            hostsService.delete(host);
            return new Result(Status.SUCCESS, "Хост: " + host.getHost() + " был удален");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "внутренняя ошибка при попытке удаления хоста - " + host.getHost());
        }
    }

    @Override
    public Result deleteHost(long id) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        try {
            hostsService.deleteById(Long.toString(id));
            return new Result(Status.SUCCESS, "хост с id: " + id + " был удален");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "внутренняя ошибка при попытке удаления хоста c id - " + id);
        }
    }

    @Override
    public Result lockHost(long id, String job) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        try {
            Host host = hostsService.getUniqueDataFor("id", Long.toString(id));
            host.setUsed(job);
            hostsService.save(host);
            return new Result(Status.SUCCESS, "host c id - " + id + "взят в состав кластера " + job);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "ошибка обработке запроса на блокировку хоста с id: " + id);
        }
    }

    @Override
    public Result unlockHost(long id) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        try {
            Host host = hostsService.getUniqueDataFor("id", Long.toString(id));
            host.setUsed("free");
            hostsService.save(host);
            host = hostsService.getUniqueDataFor("id", Long.toString(id));
            if (!host.getUsed().equals("free")){
                unlockHost(id);
            }
            return new Result(Status.SUCCESS, "host c id - " + id + "возвращен в пул свободных машин");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "ошибка обработке запроса на разблокировку хоста с id: " + id);
        }
    }

    @Override
    public ArrayList<Host> getFreeHosts(long size) throws Exception {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        ArrayList<Host> hosts = new ArrayList<>();
        try {
            hosts = (ArrayList<Host>) hostsService.getDataFor("used","free", size);
            if (hosts.size()!=size){
                throw new Exception("такого количества хостов нет в свободном распоряжении. Попробуйте позднее." +
                        "Сейчас свободно: " + hosts.size());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hosts;
    }

    @Override
    public Result lockHostsForCluster(ArrayList<Host> cluster, String job) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();

        for (Host host : cluster) {
            if (!this.lockHost(host.getId(), job).getStatus().name().equals(Status.SUCCESS.name())){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.lockHost(host.getId(), job);
            }
        }
        try {
            ArrayList<Host> resultCluster = (ArrayList<Host>) hostsService.getDataFor("used", job);
            if (resultCluster.size()==cluster.size()){
                return new Result(Status.SUCCESS, "Все хосты взяты в состав кластера");
            } else {
                return new Result(Status.ERROR, "повторите попытку формирования кластера. Что-то пошло не так." +
                        " Результирующий размер кластера не соответствует заданному");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "повторите попытку формирования кластера. Что-то пошло не так.");
        }
    }

    @Override
    public Result unlockHosts(ArrayList<Host> cluster) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();

        for (Host host : cluster) {
            if (!this.unlockHost(host.getId()).getStatus().name().equals(Status.SUCCESS.name())){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.unlockHost(host.getId());
            }
        }

        return new Result(Status.SUCCESS, "хосты разблокированы");
    }

    @Override
    public ArrayList<Host> getHostsByJob(String job) {
        HostsService hostsService = dataStorageConfiguration.getHostsService();

        try {
            return (ArrayList<Host>) hostsService.getDataFor("used", job);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Pagination<Host> getPageData(int page, int size) throws SQLException {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        long count = hostsService.getPageCount(size);
        long skipCount = page * size;
        ArrayList<Host> data = new ArrayList<>();

        CloseableIterator<Host> iterator = hostsService.getDao().queryBuilder().iterator();
        for (int i = 0; i < skipCount-1; i++){
            iterator.next();
        }
        for (int i = 0; i < size; i++) {
            iterator.next();
            data.add(iterator.current());
        }

        Pagination<Host> pagination = new Pagination<Host>(page, count);
        pagination.setData(data);
        return pagination;
    }
}
