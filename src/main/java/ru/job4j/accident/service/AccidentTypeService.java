package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.*;

@Service
public class AccidentTypeService {

    private final AccidentHibernate hbm;

    public AccidentTypeService(AccidentHibernate hbm) {
        this.hbm = hbm;
    }


    public Collection<AccidentType> accidentTypes() {
        return hbm.findAllTypes();
    }

    public AccidentType findTypeById(int id) {
        return hbm.findTypeById(id);
    }

    public void setType(Accident accident) {
        accident.setAccidentType(hbm.findTypeById(accident.getAccidentType().getId()));
    }
}
