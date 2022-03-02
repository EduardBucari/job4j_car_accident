package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RulesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentRuleService {

    private final RulesRepository rr;
    private final AccidentRepository ar;

    public AccidentRuleService(RulesRepository rr, AccidentRepository ar) {
        this.rr = rr;
        this.ar = ar;
    }


    public Collection<Rule> findAllRules() {
        List<Rule> rules = new ArrayList<>();
        rr.findAll().forEach(rules::add);
        return rules;
    }

    public Rule findRuleById(int id) {
        return rr.findById(id).get();
    }

    public void addRules(Accident accident, String[] ids) {
        for (String id : ids) {
            accident.addRule(findRuleById(Integer.parseInt(id)));
        }

        ar.save(accident);
    }
}
