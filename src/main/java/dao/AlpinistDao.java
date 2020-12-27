package dao;

import jpa.entity.Alpinist;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class AlpinistDao implements Dao<Alpinist, Integer> {

    private EntityManager manager;

    public AlpinistDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Alpinist entity) throws SQLException {
        manager.persist(entity);
    }

    @Override
    public void update(Alpinist entity) throws SQLException {
        manager.merge(entity);
    }

    @Override
    public void removeByPK(Integer id) throws SQLException {
        Alpinist alpinist = getByPK(id);
        if (alpinist != null){
            manager.remove(alpinist);
        }
    }

    @Override
    public Alpinist getByPK(Integer id) throws SQLException {
        return manager.find(Alpinist.class, id);
    }

    @Override
    public List<Alpinist> getAll() throws SQLException {
        Query query = manager.createQuery("SELECT a FROM Alpinist a");
        List<Alpinist> alpinists = query.getResultList();
        return  alpinists;
    }

    public List<Alpinist> getAgeFromTo(int from, int to) throws  SQLException {
        TypedQuery<Alpinist> query = manager.createNamedQuery("Alpinist.getAgeFromTo", Alpinist.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Alpinist> alpinists = query.getResultList();
        return alpinists;
    }
}
