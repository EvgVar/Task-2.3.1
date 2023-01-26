package crudapp.dao;

import crudapp.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void updateUser(long id, @NotNull User newUserData) {
        User shouldBeUpdatedUser = entityManager.find(User.class, id);
        shouldBeUpdatedUser.setName(newUserData.getName());
        shouldBeUpdatedUser.setSex(newUserData.getSex());
        shouldBeUpdatedUser.setAge(newUserData.getAge());
    }
}
