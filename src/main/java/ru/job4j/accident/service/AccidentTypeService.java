package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.*;

@Service
public class AccidentTypeService {

    private final AccidentTypeRepository atr;

    public AccidentTypeService(AccidentTypeRepository atr) {
        this.atr = atr;
    }


    public Collection<AccidentType> accidentTypes() {
        List<AccidentType> accidentTypes = new ArrayList<>();
        atr.findAll().forEach(accidentTypes::add);
        return accidentTypes;
    }

    public AccidentType findTypeById(int id) {
        return atr.findById(id).get();
    }

    public void setType(Accident accident) {
        accident.setAccidentType(atr.findById(accident.getAccidentType().getId()).get());
    }
}
