package com.example.paciu.belmondo.Discipline;

import android.content.Context;

/**
 * Created by paciu on 13.05.2016.
 */
public abstract class DisciplinesFactory {
    public abstract Discipline create(Context context, String disciplineResourceName);
}
