package pl.demo.model;

public class Contacts {
    private Long id;
    private Person person;
    private String value;
    private int type;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Person getPerson() {return person;}
    public void setPerson(Person person) {this.person = person;}
    public int getType() {return type;}
    public void setType(int type) {this.type = type;}
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", person=" + person.getId() +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
