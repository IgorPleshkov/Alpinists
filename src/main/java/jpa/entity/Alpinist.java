package jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alpinist")
@NamedQueries({
        @NamedQuery(name = "Alpinist.getAgeFromTo",
                query = "SELECT a FROM Alpinist a WHERE a.age >= :from AND a.age <= :to")
})
public class Alpinist extends BaseId {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column (nullable = false)
    private int age;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "alpinists")
    private List<AlpinistGroup> alpinistGroup;


    public Alpinist(String name, String address, int age) {
        setName(name);
        setAddress(address);
        setAge(age);
        this.name = name;
        this.address = address;
    }

    public Alpinist(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().length() < 3) throw new IllegalArgumentException("Имя не может быть меньше 3-х символов!");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.trim().length() < 5) throw new IllegalArgumentException("Адрес не может быть меньше 5-ти символов!");
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) throw new IllegalArgumentException("Возраст не м.б. меньше 18 лет!");
        this.age = age;
    }

    public String getAlpinistInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Имя: ").append(name).append(" Адрес: ").append(address);
        return sb.toString();
    }
}
