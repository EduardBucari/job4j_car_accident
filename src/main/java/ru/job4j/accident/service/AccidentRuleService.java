package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentRuleService {

    private final AccidentHibernate hbm;

    public AccidentRuleService(AccidentHibernate hbm) {
        this.hbm = hbm;
    }


    public Collection<Rule> findAllRules() {
        return hbm.findAllRules();
    }

    public Rule findRuleById(int id) {
        return hbm.findRuleById(id);
    }

    @Transactional
    public void addRules(Accident accident, String[] ids) {
        for (String id : ids) {
            accident.addRule(findRuleById(Integer.parseInt(id)));
        }
        hbm.save(accident);
    }
}
