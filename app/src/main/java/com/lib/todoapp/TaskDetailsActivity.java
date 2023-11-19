package com.lib.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
        int id = (int) intent.getExtras().get("task_id");
        Task task = Task.tasks[id];
        Task[] tasks = Task.tasks;
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, R.layout.tasks_items, R.id.textViewName, tasks);

        EditText txtName = findViewById(R.id.txtName);
        EditText txtStatus = findViewById(R.id.txtStatus);
        EditText txtDueDate = findViewById(R.id.txtDueDate);
        EditText txtDesc = findViewById(R.id.txtDesc);
        Button editButton = findViewById(R.id.editButton);
        txtName.setText(task.getName());
        txtDesc.setText(task.getDescription());
        txtDueDate.setText(task.getDueDate());
        txtStatus.setText(task.getStatus());

        editButton.setOnClickListener(view -> {
            String editedName = txtName.getText().toString();
            String editedDesc = txtDesc.getText().toString();
            String editedDueDate = txtDueDate.getText().toString();
            String editedStatus = txtStatus.getText().toString();

            task.setName(editedName);
            task.setDescription(editedDesc);
            task.setDueDate(editedDueDate);
            task.setStatus(editedStatus);
            adapter.notifyDataSetChanged();

            showToast("Saved!");
            finish(); //return to the main
        });
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

