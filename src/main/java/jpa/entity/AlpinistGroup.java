package jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alpinistGroup")
@NamedQueries({
        @NamedQuery(name = "AlpinistGroup.getForNameMount",
                query = "SELECT a FROM AlpinistGroup a WHERE a.mountain = :mountain"),
        @NamedQuery(name = "AlpinistGroup.getGroupIsComleted",
                query = "SELECT a FROM AlpinistGroup a WHERE a.groupIsCompleted = :param")

})
public class AlpinistGroup extends BaseId {
    private boolean groupIsCompleted;
    @ManyToMany
    private List<Alpinist> alpinists = new ArrayList<>();
    @ManyToOne
    private Mountain mountain;
    @Column(nullable = false)
    private LocalDate dateClimbing; //дата восхождения
    @Column(nullable = false)
    private int durationClimbing; //продолжительность восхождения в днях


    public AlpinistGroup (List<Alpinist> alpinists, Mountain mountain, LocalDate dateClimbing, int durationClimbing, boolean groupIsCompleted){
        this.alpinists = alpinists;
        this.mountain = mountain;
        this.dateClimbing = dateClimbing;
        this.durationClimbing = durationClimbing;
        this.groupIsCompleted = groupIsCompleted;
    }

    public AlpinistGroup(){};

    public boolean getGroupIsCompleted() {
        return groupIsCompleted;
    }



}
