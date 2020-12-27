package dao;

import jpa.entity.AlpinistGroup;
import jpa.entity.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;


public class MountainDao implements Dao<Mountain, Integer>{

    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain entity) throws SQLException {
        manager.persist(entity);
    }

    @Override
    public void update(Mountain entity) throws SQLException {
        manager.merge(entity);
    }

    @Override
    public void removeByPK(Integer id) throws SQLException {
       Mountain mountain = getByPK(id);
       if (mountain != null){
       manager.remove(mountain);
       }
    }

    @Override
    public Mountain getByPK(Integer id) throws SQLException {
        return manager.find(Mountain.class, id);
    }

    @Override
    public List<Mountain> getAll() throws SQLException {
        Query query = manager.createQuery("SELECT m FROM Mountain m");
        List<Mountain> mountains = query.getResultList();
        return  mountains;
    }

    public List<Mountain> getForNameCountry(String country) throws SQLException {
        TypedQuery<Mountain> query = manager.createNamedQuery("Mountain.getFromCountry", Mountain.class);
        query.setParameter("name", country);
        List<Mountain> alpinistsGroup = query.getResultList();
        return alpinistsGroup;
    }
}
