package dao;



import jpa.entity.AlpinistGroup;
import jpa.entity.Mountain;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class AlpinistGroupDao implements Dao<AlpinistGroup, Integer> {

    private EntityManager manager;

    public AlpinistGroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(AlpinistGroup entity) throws SQLException {
        manager.persist(entity);
    }

    @Override
    public void update(AlpinistGroup entity) throws SQLException {
        manager.merge(entity);
    }

    @Override
    public void removeByPK(Integer id) throws SQLException {
        AlpinistGroup alpinistGroup = getByPK(id);
        if (alpinistGroup != null){
            manager.remove(alpinistGroup);
        }
    }

    @Override
    public AlpinistGroup getByPK(Integer id) throws SQLException {
        return manager.find(AlpinistGroup.class, id);
    }

    @Override
    public List<AlpinistGroup> getAll() throws SQLException {
        Query query = manager.createQuery("SELECT a FROM AlpinistGroup a");
        List<AlpinistGroup> alpinistGroup = query.getResultList();
        return  alpinistGroup;
    }

    public List<AlpinistGroup> getForNameMount(Mountain mountain){
        TypedQuery<AlpinistGroup> query = manager.createNamedQuery("AlpinistGroup.getForNameMount", AlpinistGroup.class);
        query.setParameter("mountain", mountain);
        List<AlpinistGroup> alpinistsGroup = query.getResultList();
        return alpinistsGroup;
    }

    public List<AlpinistGroup> getGroupIsComleted(Boolean groupIsCompleted){
        TypedQuery<AlpinistGroup> query = manager.createNamedQuery("AlpinistGroup.getGroupIsComleted", AlpinistGroup.class);
        query.setParameter("param", groupIsCompleted);
        List<AlpinistGroup> alpinistsGroup = query.getResultList();
        return alpinistsGroup;
    }
}

