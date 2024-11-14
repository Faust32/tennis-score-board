package utils;

import score.GameScore;
import lombok.experimental.UtilityClass;
import model.Match;
import model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "db.url");
        configuration.setProperty("hibernate.connection.username", "db.username");
        configuration.setProperty("hibernate.connection.password", "db.password");
        configuration.setProperty("connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Match.class);
        configuration.addAnnotatedClass(GameScore.class);
        return configuration;
    }
}
