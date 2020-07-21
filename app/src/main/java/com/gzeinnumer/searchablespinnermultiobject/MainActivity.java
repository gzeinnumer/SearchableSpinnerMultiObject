package com.gzeinnumer.searchablespinnermultiobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MyModel> items = new ArrayList<>();
    MyCustomSpinnerDialog spinnerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItems();

        spinnerDialog = new MyCustomSpinnerDialog(MainActivity.this, items,"Select Items");

        spinnerDialog.setMyOnClick(new MyCustomSpinnerDialog.MyOnClick() {
            @Override
            public void myOnClick(MyModel myModel, int pos) {
                Toast.makeText(getApplicationContext(),"_(name)_"+myModel.getName() +"_(id)_"+myModel.getId()+"_(posisi)_"+pos,Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
    }

    private void initItems() {
        for (int i=0; i<99; i++){
            items.add(new MyModel(i, "Item"+(1+i)));
        }
    }
}