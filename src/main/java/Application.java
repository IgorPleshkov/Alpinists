import dao.AlpinistDao;
import dao.AlpinistGroupDao;
import dao.MountainDao;
import jpa.entity.Alpinist;
import jpa.entity.AlpinistGroup;
import jpa.entity.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Mountain mountain1 = new Mountain("Эльбрус", "Россия", 5642);
        Mountain mountain2 = new Mountain("Арарат", "Турция", 5137);
        Mountain mountain3 = new Mountain("Эверест", "Непал", 8848);

        Alpinist alpinist1 = new Alpinist("Василий", "ул. Советская 11", 25);
        Alpinist alpinist2 = new Alpinist("Петр", "ул. Строителей 3",33);
        Alpinist alpinist3 = new Alpinist("Николай", "ул. Чайковского 12", 45);
        Alpinist alpinist4 = new Alpinist("Иван", "ул. Прибрежная 9", 39);
        Alpinist alpinist5 = new Alpinist("Павел", "ул. Гороховая 14", 55);
        Alpinist alpinist6 = new Alpinist("Владимир", "ул. Бехтерева 4",27);
        Alpinist alpinist7 = new Alpinist("Максим", "ул. Полевая 2", 61);

        List<Alpinist> group1 = new ArrayList<>();
        group1.add(alpinist1);
        group1.add(alpinist2);
        group1.add(alpinist3);
        AlpinistGroup alpinistGroup1 = new AlpinistGroup(group1, mountain1, LocalDate.now(), 2, false);

        List<Alpinist> group2 = new ArrayList<>();
        group2.add(alpinist2);
        group2.add(alpinist4);
        group2.add(alpinist5);
        AlpinistGroup alpinistGroup2 = new AlpinistGroup(group2, mountain2, LocalDate.now(), 3, true);

        List<Alpinist> group3 = new ArrayList<>();
        group3.add(alpinist1);
        group3.add(alpinist5);
        group3.add(alpinist6);
        group3.add(alpinist7);
        AlpinistGroup alpinistGroup3 = new AlpinistGroup(group3, mountain3, LocalDate.now(), 5, true);

        //добавление в БД

        AlpinistDao alpinistDao = new AlpinistDao(entityManager);
        alpinistDao.add(alpinist1);
        alpinistDao.add(alpinist2);
        alpinistDao.add(alpinist3);
        alpinistDao.add(alpinist4);
        alpinistDao.add(alpinist5);
        alpinistDao.add(alpinist6);
        alpinistDao.add(alpinist7);

        MountainDao mountainDao = new MountainDao(entityManager);
        mountainDao.add(mountain1);
        mountainDao.add(mountain2);
        mountainDao.add(mountain3);

        AlpinistGroupDao groupDao = new AlpinistGroupDao(entityManager);
        groupDao.add(alpinistGroup1);
        groupDao.add(alpinistGroup2);
        groupDao.add(alpinistGroup3);

        entityManager.getTransaction().commit();

       //запросы к БД

        System.out.println(alpinistDao.getAll());
        System.out.println(mountainDao.getAll());
        System.out.println(groupDao.getAll());

        System.out.println(alpinistDao.getAgeFromTo(25, 30));
        System.out.println(groupDao.getForNameMount(mountain1));
        System.out.println(mountainDao.getForNameCountry("Россия"));
        System.out.println(groupDao.getGroupIsComleted(true));


    }
}
