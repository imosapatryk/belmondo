package com.example.paciu.belmondo.Discipline;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paciu on 13.05.2016.
 */
public class BelmondoDisciplineProvider implements DisciplineProvider {
    private Set<DisciplineChangedListener> disciplineChangedListenerSet;
    private Discipline currentDiscipline;

    public BelmondoDisciplineProvider() {
        this.disciplineChangedListenerSet = new HashSet<>();
    }

    @Override
    public void setCurrentDiscipline(Discipline discipline) {
        currentDiscipline = discipline;
        notifyDisciplineChangedListeners(currentDiscipline);
    }

    @Override
    public Discipline getCurrentDiscipline() {
        return currentDiscipline;
    }

    @Override
    public void addDisciplineChangedListener(DisciplineChangedListener disciplineChangedListener) {
        disciplineChangedListenerSet.add(disciplineChangedListener);
    }

    @Override
    public void removeDisciplineChangedListener(DisciplineChangedListener disciplineChangedListener) {
        disciplineChangedListenerSet.remove(disciplineChangedListener);
    }

    @Override
    public void notifyDisciplineChangedListeners(Discipline discipline) {
        for(DisciplineChangedListener l : disciplineChangedListenerSet){
            if(l != null){
                l.onDisciplineChanged(discipline);
            }
        }
    }
}
