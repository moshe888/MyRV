package com.example.myrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private ArrayList<DataModel> dataset;
private LinearLayoutManager LayoutManager;
private  CustemAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.resycleViewCon);

        LayoutManager = new LinearLayoutManager(  this);
        recyclerView. setLayoutManager (LayoutManager) ;

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataset = new ArrayList<DataModel>();

     for(int i =0; i < MyData.namearray.length ; i++)
     {
         dataset.add(new DataModel(MyData.namearray[i],
                 MyData.versionArray[i],MyData.id_[i], MyData.dronableArroy[i]));

     }

          adapter = new CustemAdapter(dataset);
     recyclerView.setAdapter(adapter);

    }
}