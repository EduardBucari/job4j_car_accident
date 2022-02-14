package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private static final AccidentMem INST = new AccidentMem();
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(2);


    private AccidentMem() {
        Accident accident1 = Accident.of("Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Виноградная д.1");
        accident1.setId(1);
        Accident accident2 = Accident.of("Выезд на встречную полосу", "Выезд на встречную полосу",
                "ул. Абрикосовая д.1");
        accident2.setId(2);
        accidents.put(1, accident1);
        accidents.put(2, accident2);
    }

    public static AccidentMem instOf() {
        return INST;
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
}
