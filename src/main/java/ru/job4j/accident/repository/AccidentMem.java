package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    private static final AtomicInteger ID = new AtomicInteger(2);

    public AccidentMem() {

        Accident accident1 = Accident.of("Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Виноградная д.1", AccidentType.of(1, "Две машины"));
        accident1.setId(1);
        accident1.addRule(Rule.of(1, "Статья 1"));
        accident1.addRule(Rule.of(2, "Статья 2"));
        Accident accident2 = Accident.of("Выезд на встречную полосу", "Выезд на встречную полосу",
                "ул. Абрикосовая д.1", AccidentType.of(1, "Две машины"));
        accident2.setId(2);
        accident2.addRule(Rule.of(3, "Статья 3"));

        accidents.put(1, accident1);
        accidents.put(2, accident2);

        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья 1"));
        rules.put(2, Rule.of(2, "Статья 2"));
        rules.put(3, Rule.of(3, "Статья 3"));
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return accidentTypes.values();
    }

    @Override
    public AccidentType findTypeById(int id) {
        return accidentTypes.get(id);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return rules.values();
    }

    @Override
    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
