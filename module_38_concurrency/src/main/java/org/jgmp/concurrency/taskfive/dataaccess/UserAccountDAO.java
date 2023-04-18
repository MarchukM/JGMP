package org.jgmp.concurrency.taskfive.dataaccess;

import org.jgmp.concurrency.taskfive.model.UserAccount;
import org.jgmp.concurrency.taskfive.service.ExchangeService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.jgmp.concurrency.taskfive.data.Data.lockMap;

public class UserAccountDAO {

    private static volatile UserAccountDAO instance;

    private UserAccountDAO() {
    }

    public static UserAccountDAO getInstance() {
        if (instance == null) {
            synchronized (ExchangeService.class) {
                if (instance == null) {
                    instance = new UserAccountDAO();
                }
            }
        }
        return instance;
    }

    public static final String EXTENSION = ".ser";

    public void writeUserAccount(UserAccount userAccount) {
        lockMap.get(userAccount.getFullName()).lock();
        String fileName = userAccount.getFirstName() + "_" + userAccount.getLastName() + EXTENSION;
        try (ObjectOutputStream objectOut = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            objectOut.writeObject(userAccount);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lockMap.get(userAccount.getFullName()).unlock();
        }
    }

    public UserAccount readUserAccount(String userName) {
        lockMap.get(userName).lock();

        String fileName = userName + EXTENSION;
        UserAccount userAccount = null;
        try (ObjectInputStream objectIn = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));) {
            userAccount = (UserAccount) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lockMap.get(userName).unlock();
        }
        return userAccount;
    }
}
