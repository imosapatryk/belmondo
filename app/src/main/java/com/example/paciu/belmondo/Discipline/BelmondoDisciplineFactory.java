package com.example.paciu.belmondo.Discipline;

import android.content.Context;

import com.example.paciu.belmondo.Discipline.DisciplinesTypes.Cycling;
import com.example.paciu.belmondo.Discipline.DisciplinesTypes.NoDiscipline;
import com.example.paciu.belmondo.Discipline.DisciplinesTypes.Running;
import com.example.paciu.belmondo.Discipline.DisciplinesTypes.Walking;

/**
 * Created by paciu on 13.05.2016.
 */
public class BelmondoDisciplineFactory extends DisciplinesFactory{

    @Override
    public Discipline create(Context context, String disciplineResourceName) {
        String name = disciplineResourceName.toLowerCase();
        if(name.equals("walking")){
            return new Walking(context);
        } else if(name.equals("running")){
            return new Running(context);
        } else if(name.equals("cycling")){
            return new Cycling(context);
        }
        return new NoDiscipline();
    }
}
