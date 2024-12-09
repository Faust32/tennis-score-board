package utils;

import exceptions.HibernateException;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import model.Match;
import model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = buildConfiguration();
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new HibernateException("An error occurred while trying to load session factory.");
        }
    }

    private Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:default;DATABASE_TO_UPPER=false");
        configuration.setProperty("hibernate.connection.username", "db.username");
        configuration.setProperty("hibernate.connection.password", "db.password");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.org.jboss.logging.level", "DEBUG");
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Match.class);
        return configuration;
    }
}
