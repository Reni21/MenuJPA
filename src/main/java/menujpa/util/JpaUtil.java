package menujpa.util;

import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@NoArgsConstructor
public class JpaUtil {
    private static volatile EntityManagerFactory entMngFactory;

    private static EntityManagerFactory getEntMngFactoryInstance(){
        if(entMngFactory == null){
            synchronized (EntityManagerFactory.class) {
                if (entMngFactory == null) {
                    entMngFactory = Persistence.createEntityManagerFactory("RestaurantMenuJPA");
                }
            }
        }
        return entMngFactory;
    }

    public static EntityManager createEntityManager() {
        return getEntMngFactoryInstance().createEntityManager();
    }

    public static void closeEntMngFactory() {
        getEntMngFactoryInstance().close();
    }

}
