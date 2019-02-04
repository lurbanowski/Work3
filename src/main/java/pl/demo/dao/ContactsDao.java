package pl.demo.dao;

import pl.demo.model.Contacts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContactsDao {
    private final String dbName = "demo";
    private final String URL = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private static final String SAVE_CONTACTS = "INSERT INTO CONTACTS ( ID_CUSTOMER, type, contact) VALUES (?,?,?)";

     public Contacts saveContacts(Contacts contact, Long personId) {

        try (Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);//DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(SAVE_CONTACTS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setLong(1, personId);
            insertStm.setInt(2, contact.getType());
            insertStm.setString(3, contact.getValue());

            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    contact.setId(generatedKeys.getLong(1));
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
    } catch(Exception e){
        e.printStackTrace();
    }
     return null;
}

}
