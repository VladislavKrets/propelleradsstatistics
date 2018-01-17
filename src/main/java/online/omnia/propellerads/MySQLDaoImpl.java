package online.omnia.propellerads;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Created by lollipop on 26.09.2017.
 */
public class MySQLDaoImpl {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static MySQLDaoImpl instance;

    static {
        configuration = new Configuration()
                .addAnnotatedClass(AccountsEntity.class)
                .addAnnotatedClass(SourceStatisticsEntity.class)
                .configure("/hibernate.cfg.xml");
        Map<String, String> properties = Utils.iniFileReader();
        configuration.setProperty("hibernate.connection.password", properties.get("password"));
        configuration.setProperty("hibernate.connection.username", properties.get("username"));
        String jdbcURL = (properties.get("url")
                .startsWith("jdbc:mysql://") ? properties.get("url") : "jdbc:mysql://" + properties.get("url"));
        String url = (!jdbcURL.endsWith("/") ? jdbcURL + "/" : jdbcURL) + properties.get("dbname");
        configuration.setProperty("hibernate.connection.url", url);

        while (true) {
            try {
                sessionFactory = configuration.buildSessionFactory();
                break;
            } catch (PersistenceException e) {

                try {
                    System.out.println("Can't connect to db");
                    System.out.println("Waiting for 30 seconds");
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    public void addSourceStatistics(SourceStatisticsEntity sourceStatisticsEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(sourceStatisticsEntity);
        session.getTransaction().commit();
        session.close();
    }
    public List<AccountsEntity> getAccountsEntities(String type) {
        Session session = sessionFactory.openSession();
        List<AccountsEntity> accountsEntities;
        while (true) {
            try {
                accountsEntities = session.createQuery("from AccountsEntity acc where acc.type=:accType", AccountsEntity.class)
                        .setParameter("accType", type)
                        .getResultList();
                break;
            } catch (PersistenceException e) {
                try {
                    System.out.println("Can't connect to db");
                    System.out.println("Waiting for 30 seconds");
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        session.close();
        return accountsEntities;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static MySQLDaoImpl getInstance() {
        if (instance == null) instance = new MySQLDaoImpl();
        return instance;
    }
}
