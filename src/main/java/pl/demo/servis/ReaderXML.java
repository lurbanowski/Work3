package pl.demo.servis;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.demo.model.Contacts;
import pl.demo.model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderXML {

    public static List<Person> xmlReader() throws Exception {
        List<Person> persons = new ArrayList<>();
        try {
            /**Czyta i buduje dokument**/
            Document doc = getDocument();
            /**pobieram elementy root do zbudowania obiektu person i list obiektów contacts**/
            NodeList personList = doc.getElementsByTagName("person");
            NodeList contactsList= doc.getElementsByTagName("contacts");
            /**dokument wpuszczam do pętli, ilośc obrotów == ilości elementów person**/
           for (int a = 0; a < personList.getLength(); a++) {
                 /**listuję po licie dzieci elementu "contacts", gdy natrafie na element, to zapisuję obiekt Contacts contact
                * i dodaję do listy Contacts contacts. Liste obiektów Contacts contacts zapisuję w obiekcie Person.
                * Person ma listę kontaktów, każdy kontakt, czli pojedyńczy mail, phone jest oddzielnym obiektem.*/
               NodeList contactsNodeTags = contactsList.item(a).getChildNodes();
               List<Contacts> contacts = new ArrayList<>();
               createCentactXML(contactsNodeTags, contacts);
               NodeList licznikPerson = personList.item(a).getChildNodes();
               createPersonXML(persons, contacts, licznikPerson);
           }
        } catch (NullPointerException f) {
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**zwraca listę obiektów person, z zapisanymi odpowiednio polami person, oraz z listę obieków Contacts contacts.
          Tak przygotowaną listę można użyć do zapisu do bazy**/
        return persons;
    }

    private static void createPersonXML(List<Person> persons, List<Contacts> contacts, NodeList licznikPerson) {
        Person person = new Person();
        for(int j=0;j<licznikPerson.getLength();j++) {
            String nodeContext = licznikPerson.item(j).getTextContent();
            String nodeName = licznikPerson.item(j).getNodeName();
            if (licznikPerson.item(j)!=null && nodeName.equals("name")) {
                person.setName(nodeContext);
            } else if (licznikPerson.item(j)!=null && nodeName.equals("surname")) {
                person.setSurname(nodeContext);
            } else if (licznikPerson.item(j)!=null && nodeName.equals("age")) {
                person.setAge(nodeContext);
            } else if (licznikPerson.item(j)!=null && nodeName.equals("city")) {
                person.setCity(nodeContext);
            }
        }
        person.setListContacts(contacts);
        persons.add(person);
    }

    private static void createCentactXML(NodeList contactsNodeTags, List<Contacts> contacts) {
        for(int i =0; i<contactsNodeTags.getLength();i++){
            Contacts contact = new Contacts();
            if(contactsNodeTags.item(i)!=null && contactsNodeTags.item(i).getNodeName().equals("email")){
              contact.setValue(contactsNodeTags.item(i).getTextContent());
              contact.setType(1);
             contacts.add(contact);
            } else if(contactsNodeTags.item(i)!=null && contactsNodeTags.item(i).getNodeName().equals("phone")){
                contact.setValue(contactsNodeTags.item(i).getTextContent());
                contact.setType(2);
                contacts.add(contact);
            } else if(contactsNodeTags.item(i)!=null && contactsNodeTags.item(i).getNodeName().equals("icq")) {
                contact.setValue(contactsNodeTags.item(i).getTextContent());
                contact.setType(0);
                contacts.add(contact);
            } else if(contactsNodeTags.item(i)!=null && contactsNodeTags.item(i).getNodeName().equals("jabber")){
                contact.setValue(contactsNodeTags.item(i).getTextContent());
                contact.setType(3);
                contacts.add(contact);
             }
        }
    }

    private static Document getDocument() throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File("/home/lukasz/Dokumenty/CodersLabs/Work3/file/dane-osoby.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
