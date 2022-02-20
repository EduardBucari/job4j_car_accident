package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository ar;

    public AccidentService(AccidentRepository ar) {
        this.ar = ar;
    }

    public Collection<Accident> findAllAccidents() {
        List<Accident> res = new ArrayList<>();
        ar.findAll().forEach(res::add);
        return res;
    }

    public Accident findById(int id) {
        return ar.findById(id).get();
    }
}