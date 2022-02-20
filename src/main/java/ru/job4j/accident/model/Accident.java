package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "accidents")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne (cascade = CascadeType.DETACH)
    @JoinColumn(name = "accident_type_id")
    private AccidentType accidentType;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Rule> rules = new HashSet<>();

    public static Accident of(String name, String text, String address, AccidentType accidentType) {
        Accident accident = new Accident();
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.accidentType = accidentType;
        return accident;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Accident.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("text='" + text + "'")
                .add("address='" + address + "'")
                .add("accidentType=" + accidentType.getName())
                .add("acc list" + rules)
                .toString();
    }
}
