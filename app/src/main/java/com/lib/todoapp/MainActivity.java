package com.lib.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private List<Task> tasksList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton addButton = findViewById(R.id.addbtn);
        ListView listView = findViewById(R.id.main_tasks);


        CheckBox checkBox = this.<CheckBox>findViewById(R.id.checkBox);
        // Task[] tasks = Task.tasks;



        //populate the listview with the tasks loaded from Shared Preferences
        setupSharedPrefs();
        SavetoSharedPreference();
        tasksList = loadTasksFromSharedPreferences();
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, R.layout.tasks_items, R.id.textViewName, tasksList);
        listView.setAdapter(adapter);


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this,
                        TaskDetailsActivity.class);
                intent.putExtra("task_id",position) ;//(int) id)
                startActivity(intent);

            }
        };

        listView.setOnItemClickListener(itemClickListener);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the destination activity you want to navigate to
                Intent intent = new Intent(MainActivity.this, addTaskActivity.class);

                // Start the new activity
                startActivity(intent);
            }
        });

    }
    public void SavetoSharedPreference() {

        // Create a list of tasks with your data
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Study", "Due", "11/11/2023", "Ai and Mobile"));
        tasks.add(new Task("Reading Qur'an", "Due", "11/11/2023", "Al-Anfal"));
        tasks.add(new Task("Feed The Cats", "Due", "11/11/2023", "The kittens"));

        //convert to JSON..
        Gson gson = new Gson();
        String tasksString = gson.toJson(tasks);

        //save the JSON string to SharedPreference..
        editor.putString(DATA, tasksString);
        editor.commit();
    }
    private List<Task> loadTasksFromSharedPreferences() {
        Gson gson = new Gson();
        String json = prefs.getString(DATA, "");

        return json.isEmpty() ? new ArrayList<>() : gson.fromJson(json, List.class);
    }
    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void saveTasksToSharedPreferences() {
        Gson gson = new Gson();
        String tasksString = gson.toJson(tasksList);
        editor.putString(DATA, tasksString);
        editor.apply();
    }

}
