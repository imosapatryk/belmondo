package com.example.paciu.belmondo.Discipline;

/**
 * Created by paciu on 13.05.2016.
 */
public interface DisciplineObservable {
    void addDisciplineChangedListener(DisciplineChangedListener disciplineChangedListener);
    void removeDisciplineChangedListener(DisciplineChangedListener disciplineChangedListener);
    void notifyDisciplineChangedListeners(Discipline discipline);
}
