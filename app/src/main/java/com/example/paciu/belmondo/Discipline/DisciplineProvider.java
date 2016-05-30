package com.example.paciu.belmondo.Discipline;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.Discipline.DisciplineObservable;

/**
 * Created by paciu on 13.05.2016.
 */
public interface DisciplineProvider extends DisciplineObservable {
    void setCurrentDiscipline(Discipline discipline);
    Discipline getCurrentDiscipline();
}

