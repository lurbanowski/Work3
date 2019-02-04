package pl.demo.dao;

import pl.demo.model.Person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PersonDao {
    private final String dbName = "demo";
    private final String URL = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private static final String SAVE_PERSON = "INSERT INTO CUSTOMERS ( NAME, SURNAME, Age) VALUES (?,?,?)";
    ContactsDao cd = new ContactsDao();

    public Person savePerson(List<Person> person) {
            try (Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
                 PreparedStatement insertStm2 = connection.prepareStatement(SAVE_PERSON, PreparedStatement.RETURN_GENERATED_KEYS)) {

                for (int p = 0; p < person.size(); p++) {
                    insertStm2.setString(1, person.get(p).getName());
                    insertStm2.setString(2, person.get(p).getSurname());
                    insertStm2.setString(3, person.get(p).getAge());

                    int result = insertStm2.executeUpdate();
                    if (result != 1) {
                        throw new RuntimeException("Execute update returned " + result);
                    }
                    try (ResultSet generatedKeys = insertStm2.getGeneratedKeys()) {
                        if (generatedKeys.first()) {
                            person.get(p).setId(generatedKeys.getLong(1));
                        } else {
                            throw new RuntimeException("Generated key was not found");
                        }
                    }

                    for (int i = 0; i < person.get(p).getListContacts().size(); i++) {
                        cd.saveContacts(person.get(p).getListContacts().get(i), person.get(p).getId());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
}
