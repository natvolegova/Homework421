package com.example.homework421;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView main_content;
    private LinearLayout add_layout;
    private FloatingActionButton fab;
    private Button add_task;
    private EditText input_title;
    private EditText input_subtitle;
    private Spinner list_task;

    // Наш адаптер
    private ItemsDataAdapter adapter;
    //параметр для картинок
    private int[] image_src = {R.drawable.ic_laptop_24px, R.drawable.ic_phone_android_24px, R.drawable.ic_tablet_android_24px};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //добавили адаптер
        adapter = new ItemsDataAdapter(this, null);
        main_content.setAdapter(adapter);

        //при нажатии на кнопку открываем экран для добавления новой задачи
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                add_layout.setVisibility(view.VISIBLE);
                fab.setVisibility(view.GONE);
                toolbar.setTitle(getString(R.string.new_task));
            }
        });

        //обрабатывваем задачу, добавляем новую запись в список
        add_task.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                //получили значения
                String title = input_title.getText().toString();
                String subtitle = input_subtitle.getText().toString();
                int param_task = (int) list_task.getSelectedItemId();
                //добавим дополнительну проверку на введенные значения
                if (title.equals("") && subtitle.equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.msg_error_add), Toast.LENGTH_LONG).show();
                } else {
                    //добавили новую запись
                    adapter.addItem(new ItemData(title, subtitle, image_src[param_task], false));
                    //очистили значения инпутов
                    input_title.setText("");
                    input_subtitle.setText("");
                    list_task.setSelection(0);
                    //закрыли слой, перешли на основной слой
                    add_layout.setVisibility(view.GONE);
                    fab.setVisibility(view.VISIBLE);
                    toolbar.setTitle(getString(R.string.app_name));
                }
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main_content = findViewById(R.id.main_content);
        add_layout = findViewById(R.id.add_layout);
        fab = findViewById(R.id.fab);
        add_task = findViewById(R.id.add_task);
        input_title = findViewById(R.id.input_title);
        input_subtitle = findViewById(R.id.input_subtitle);
        list_task = findViewById(R.id.list_task);
    }
}
