package pl.demo.servis;
import com.opencsv.CSVReader;
import pl.demo.model.Contacts;
import pl.demo.model.Person;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCSV {

    public static List<Person> csvReader() {
        String csvFile = "/home/lukasz/Dokumenty/CodersLabs/Work3/file/dane-osoby.txt";

        List<Person> persons = new ArrayList<>();

        /**metoda czyta dane w formacie CSV, buduje z nich dwie listy.
         * Posłuzyłem się biblioteką CSVReader
         * Czytam pierwsze 4 indexy bez sprawdzania.
         * Pozostałe 4 indeksy są to kontakty. Sprawdzam jej przy pomocy regexu, a jabber identyfikuję po długości String.
         * Przy sprawdzaniu zapisuję do listy którą posłużę się do zapisu do bazy danych.**/
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Person person = new Person();
                List<Contacts> contacts = new ArrayList<>();
                String emailPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
                String phoneNumberPattern = "^(?:\\(?\\+?48)?(?:[-\\.\\(\\)\\s]*(\\d)){9}\\)?$";

                person.setName(line[0]);
                person.setSurname(line[1]);
                person.setAge(line[2]);
                person.setCity(line[3]);
                     for (int j = 4; j < line.length; j++) {
                        Contacts contact = new Contacts();
                        if (line[j].matches(emailPattern) && line[j] != null) {
                            contact.setType(1);
                            contact.setValue(line[j]);
                        } else if (line[j].matches(phoneNumberPattern) && line[j] != null) {
                            contact.setType(2);
                            contact.setValue(line[j]);
                        } else if (line[j].length() == 3 && line[j] != null) {
                            contact.setType(3);
                            contact.setValue(line[j]);
                        } else if(line[j].equals(" ") || line[j].equals("") || line[j].isEmpty() ) {
                            contact.setType(0);
                            contact.setValue(null);
                        }
                        contacts.add(contact);
                    }
                person.setListContacts(contacts);
                persons.add(person);
            }
        }catch(IOException e){
                    e.printStackTrace();
                }
                return persons;
    }
}
