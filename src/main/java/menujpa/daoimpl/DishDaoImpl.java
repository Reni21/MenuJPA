package menujpa.daoimpl;

import menujpa.util.JpaUtil;
import menujpa.dao.DishDao;
import menujpa.entity.Dish;

import javax.persistence.*;
import java.util.List;

public class DishDaoImpl implements DishDao {
    @Override
    public boolean addDish(Dish dish) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(dish);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            dish.setId(null);
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateDish(Dish dish) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(dish);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Dish findDish(Integer id) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        try {
            return entityManager.find(Dish.class, id);
        } catch (EntityNotFoundException ex) {
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Dish> findAllDishes() {
        EntityManager entityManager = JpaUtil.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT d FROM Dish d", Dish.class);
            List<Dish> dishes = (List<Dish>) query.getResultList();
            return dishes;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Dish> findByPriceRange(double minPrice, double maxPrice) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        try {
            Query query = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE (d.price BETWEEN :minPrice AND :maxPrice)", Dish.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            List<Dish> dishes = (List<Dish>) query.getResultList();
            return dishes;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Dish> findAllDiscounted() {
        EntityManager entityManager = JpaUtil.createEntityManager();
        try {
            Query query = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.discount IS NOT NULL", Dish.class);
            List<Dish> dishes = (List<Dish>) query.getResultList();
            return dishes;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean deleteDish(Dish src) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            if (entityManager.contains(src)) {
                entityManager.remove(src);
            } else {
                Dish dish = entityManager.getReference(Dish.class, src.getId());
                entityManager.remove(dish);
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean isDishExist(String name) {
        EntityManager entityManager = JpaUtil.createEntityManager();
        Query query = entityManager
                .createQuery("SELECT 1 FROM Dish d WHERE d.name = :name");
        query.setParameter("name", name);
        try {
            Integer data = (Integer) query.getSingleResult();
            return data == 1;
        } catch (NoResultException ex) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Integer> findAllIds() {
        EntityManager entityManager = JpaUtil.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT id FROM dish");
            List<Integer> ids = (List<Integer>) query.getResultList();
            return ids;
        } catch (NoResultException ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}
