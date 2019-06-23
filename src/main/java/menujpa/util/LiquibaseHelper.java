package menujpa.util;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;

import javax.persistence.EntityManager;

public class LiquibaseHelper {

    public void runScripts() {

        EntityManager em = JpaUtil.createEntityManager();
        Session session = em.unwrap(Session.class); // get hibernate session object, because we use hibernate provider
        session.doWork(connection -> { //
            try {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new Liquibase("db/changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());
            } catch (LiquibaseException e) {
                System.err.println("Migration error occurred " + e.getMessage()); // Migration script - when we manipulate with DB data before start
                System.exit(1);
            }
        });
        em.close();

    }
}
