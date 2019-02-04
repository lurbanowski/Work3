package pl.demo.model;

import java.util.List;
public class Person {
    private Long id;
    private String name;
    private String surname;
    private String age;
    private String city;
    List<Contacts> listContacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Contacts> getListContacts() {
        return listContacts;
    }

    public void setListContacts(List<Contacts> listContacts) {
        this.listContacts = listContacts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", city='" + city + '\'' +
                ", listContacts=" + listContacts +
                '}';
    }
}
