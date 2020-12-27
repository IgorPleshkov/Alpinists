package jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mountain")
@NamedQueries({
        @NamedQuery(name = "Mountain.getFromCountry",
                query = "SELECT a FROM Mountain a WHERE a.country = :name")
})

public class Mountain extends BaseId {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private int height;
    @OneToMany(mappedBy = "mountain")
    private List<AlpinistGroup> groupForUpList = new ArrayList<>();

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);

        this.name = name;
        this.country = country;
        this.height = height;
    }

    public Mountain(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().length() < 4) throw new IllegalArgumentException("Наименование не может быть меньше 4 символов!");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.trim().length() < 4) throw new IllegalArgumentException("Страна не может быть меньше 4 символов!");
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 100) throw new IllegalArgumentException("Высота не может быть меньше 100м!");
        this.height = height;
    }

    public String getMountainInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Наименование: ").append(name).
                                    append("\nСтрана: ").
                                    append(country).
                                    append("\nВысота: ").
                                    append(height);
        return sb.toString();
    }
}
