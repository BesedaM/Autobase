package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.entity.user.User;

public interface UserInterface extends EntityDAO<User> {

    User getUserByLoginAndPassword(String login, byte[] password);

    User getUserByLogin(String login);

    void updatePassword(int id, byte[] password);
}
