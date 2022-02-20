package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentHibernate hbm;

    public AccidentService(AccidentHibernate hbm) {
        this.hbm = hbm;
    }

    public Collection<Accident> findAllAccidents() {
        return hbm.findAllAccidents();
    }

    public Accident findById(int id) {
        return hbm.findAccidentById(id);
    }
}