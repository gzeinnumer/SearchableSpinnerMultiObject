package com.gzeinnumer.searchablespinnermultiobject;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomSpinnerDialog {
    ArrayList<MyModel> items;
    Activity context;
    String dTitle;
    AlertDialog alertDialog;
    int pos;

    private MyOnClick myOnClick;
    public void setMyOnClick(MyOnClick myOnClick) { this.myOnClick = myOnClick; }
    interface MyOnClick{ void myOnClick(MyModel data, int position);}

    public MyCustomSpinnerDialog(Activity activity, ArrayList<MyModel> items, String dialogTitle) {
        this.items = items;
        this.context = activity;
        this.dTitle = dialogTitle;
    }

    TextView rippleViewClose;
    TextView title;
    ListView listView;
    EditText searchBox;

    public void showSpinerDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);

        View view = context.getLayoutInflater().inflate(R.layout.mygzn_spinner_dialog_dialog_layout, (ViewGroup)null);
        rippleViewClose = view.findViewById(R.id.close);
        title = view.findViewById(R.id.spinerTitle);
        listView = view.findViewById(R.id.list);
        searchBox = view.findViewById(R.id.searchBox);

        title.setText(dTitle);
        List<String> itemsToShow = new ArrayList<String>();
        for (int i=0; i<items.size(); i++){
            itemsToShow.add(items.get(i).getName());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.mygzn_spinner_dialog_items_view, itemsToShow);
        listView.setAdapter(adapter);
        adb.setView(view);
        alertDialog = adb.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations_SmileWindow;
        alertDialog.setCancelable(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.text1);
                for(int j = 0; j < items.size(); ++j) {
                    if (t.getText().toString().equalsIgnoreCase(items.get(j).getName())) {
                        pos = j;
                    }
                }

                myOnClick.myOnClick(items.get(i), pos);
                alertDialog.dismiss();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(searchBox.getText().toString());
            }
        });
        rippleViewClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
