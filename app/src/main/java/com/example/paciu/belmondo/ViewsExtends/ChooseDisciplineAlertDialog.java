package com.example.paciu.belmondo.ViewsExtends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.Discipline.DisciplineChangedListener;
import com.example.paciu.belmondo.Discipline.DisciplineProvider;
import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.ViewAdapters.ArrayAdapterWithIcons;

import java.util.List;

/**
 * Created by paciu on 09.04.2016.
 */
public class ChooseDisciplineAlertDialog {
    private List<Discipline> disciplines;
    private ArrayAdapterWithIcons arrayAdapterWithIcons;
    private Context context;
    private DisciplineProvider disciplineProvider;

    public ChooseDisciplineAlertDialog(Context context, List<Discipline> disciplineList, DisciplineProvider disciplineProvider) {
        this.context = context;
        this.disciplines = disciplineList;
        this.disciplineProvider = disciplineProvider;
        setupCompnonents();
    }

    protected void setupCompnonents(){
        arrayAdapterWithIcons = new ArrayAdapterWithIcons(context, R.layout.discipline_dialog_row, disciplines);
    }

    public void show(final MenuItem item){

        new AlertDialog.Builder(context).setTitle(R.string.choose_discipline_dialog_title)
                    .setAdapter(arrayAdapterWithIcons, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            item.setIcon(context.getDrawable(disciplines.get(which).getIconResId()));
                            callDisciplineChangedListener(disciplines.get(which));
                        }
                    })
                    .show();

    }

    protected void callDisciplineChangedListener(Discipline discipline){
        if(disciplineProvider != null){
            disciplineProvider.setCurrentDiscipline(discipline);
        }
    }

    public Context getContext() {
        return context;
    }

}
