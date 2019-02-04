package pl.demo;

import pl.demo.dao.ContactsDao;
import pl.demo.dao.PersonDao;
import pl.demo.servis.ReaderCSV;
import pl.demo.servis.ReaderXML;

public class Main {
    public static void main(String[] args) throws Exception {

        PersonDao m = new PersonDao();
        m.savePerson(ReaderCSV.csvReader());
        m.savePerson(ReaderXML.readerBis());

    }
}
