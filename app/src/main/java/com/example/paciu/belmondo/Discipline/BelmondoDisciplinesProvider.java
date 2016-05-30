package com.example.paciu.belmondo.Discipline;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by paciu on 10.04.2016.
 */
public class BelmondoDisciplinesProvider implements DisciplinesProvider{

    private List<Discipline> disciplines;
    private Context context;
    private int disciplineNameArrayId;
    private DisciplinesFactory disciplinesFactory;

    public BelmondoDisciplinesProvider(Context context, DisciplinesFactory disciplinesFactory, int stringArrayIdWithDisciplinesNames){
        this.context = context;
        this.disciplinesFactory = disciplinesFactory;
        this.disciplineNameArrayId = stringArrayIdWithDisciplinesNames;
        this.disciplines = new ArrayList<>();
        addDisciplinesFromSystemResources(context);
    }

    protected void addDisciplinesFromSystemResources(Context context) {
        String [] disciplinesNames = context.getResources().getStringArray(disciplineNameArrayId);
        for(int i = 0; i < disciplinesNames.length; i++){
            Discipline discipline = disciplinesFactory.create(context, disciplinesNames[i]);
            disciplines.add(discipline);
        }
    }

    public Iterator<Discipline> getDisciplinesIterator(){
        return disciplines.iterator();
    }
}
