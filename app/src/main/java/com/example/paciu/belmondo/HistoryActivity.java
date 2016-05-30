package com.example.paciu.belmondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.paciu.belmondo.Result.Result;
import com.example.paciu.belmondo.Result.ResultModelAdapter;
import com.example.paciu.belmondo.SqliteDataSource.Results.ResultDataSource;

import com.example.paciu.belmondo.SqliteDataSource.Results.ResultModel;
import com.example.paciu.belmondo.ViewAdapters.HistoryListArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Result> resultList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        loadResultList();
        setupToolbar();
        setupHistoryListView();
    }

    protected void setupToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.history_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setupHistoryListView(){
        HistoryListArrayAdapter adapter = new HistoryListArrayAdapter(getApplicationContext(),R.layout.history_list_row, resultList);
        listView = (ListView)findViewById(R.id.history_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            createMainActivityIfRequestedOnBack();
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        createMainActivityIfRequestedOnBack();
        finish();
    }

    protected void createMainActivityIfRequestedOnBack(){
        boolean shouldCreateMainActivity = getIntent().getBooleanExtra("CREATE_NEW_MAINACTIVITY", false);
        if(shouldCreateMainActivity){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    protected void loadResultList(){
        ResultDataSource resultDataSource = new ResultDataSource(getApplicationContext());
        resultDataSource.openDB();
        int userId = AppProfileManager.getInstance().getProfile().getId();
        List<ResultModel> resultModels = resultDataSource.getAllById(userId);
        resultDataSource.closeDB();

        resultList = new ArrayList<>();
        for(ResultModel r : resultModels){
            resultList.add(new ResultModelAdapter(r));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Result result = (Result)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, ResultDetailsActivity.class);
        intent.putExtra(ResultDetailsActivity.RESULT, result);
        startActivity(intent);
    }
}
