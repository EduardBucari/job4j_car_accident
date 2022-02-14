package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem mem;

    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    public Collection<Accident> findAllAccidents() {
        return mem.findAllAccidents();
    }

    public void create(Accident accident) {
        mem.save(accident);
    }

    public Accident findById(int id) {
        return mem.findAccidentById(id);
    }
}