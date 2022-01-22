package com.jdawidowska.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jdawidowska.client.model.ApiEndpoints;
import com.jdawidowska.client.model.EquipmentItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableLayout table;

    private final int COLUMN_WIDTH = 175;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = findViewById(R.id.table);

        initTable();
    }

    public void initTable() {
        //czyszczenie tabeli przed jej wypelnieniem
        table.removeAllViews();

        //wykonanie zapytania do backendu
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ApiEndpoints.FIND_ALL_EQUIPMENT.toString();
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    //parsowanie odpowiedz na liste sprzetu
                    List<EquipmentItem> equipmentItemList = new Gson().fromJson(response, new TypeToken<List<EquipmentItem>>(){}.getType());

                    //przygotowanie naglowka tabeli
                    createTableHeader();

                    //dla kazdego sprzetu w liscie tworzymy jego rzad w tabeli
                    for(EquipmentItem equipmentItem : equipmentItemList){
                        createEquipmentTableRow(equipmentItem);
                    }
                },
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        );

        queue.add(request);
    }

    private void createEquipmentTableRow(EquipmentItem equipmentItem) {
        TableRow row = new TableRow(this);

        TextView name = new TextView(this);
        name.setText(equipmentItem.getEquipmentItem());
        name.setGravity(Gravity.LEFT);
        name.setMinWidth(COLUMN_WIDTH);
        name.setWidth(COLUMN_WIDTH);
        name.setMaxWidth(COLUMN_WIDTH);
        row.addView(name);

        TextView availableAmount = new TextView(this);
        availableAmount.setText(equipmentItem.getAvailableAmount().toString());
        availableAmount.setGravity(Gravity.CENTER);
        availableAmount.setMinWidth(COLUMN_WIDTH);
        availableAmount.setWidth(COLUMN_WIDTH);
        availableAmount.setMaxWidth(COLUMN_WIDTH);
        row.addView(availableAmount);

        TextView totalAmount = new TextView(this);
        totalAmount.setText(equipmentItem.getTotalAmount().toString());
        totalAmount.setGravity(Gravity.CENTER);
        totalAmount.setMinWidth(COLUMN_WIDTH);
        totalAmount.setWidth(COLUMN_WIDTH);
        totalAmount.setMaxWidth(COLUMN_WIDTH);
        row.addView(totalAmount);

        Button bLend = new Button(this);
        bLend.setText("-");
        bLend.setMinWidth(COLUMN_WIDTH);
        bLend.setWidth(COLUMN_WIDTH);
        bLend.setMaxWidth(COLUMN_WIDTH);
        bLend.setOnClickListener(e -> lendItem(equipmentItem.getId()));
        row.addView(bLend);

        Button bReturn = new Button(this);
        bReturn.setText("+");
        bReturn.setMinWidth(COLUMN_WIDTH);
        bReturn.setWidth(COLUMN_WIDTH);
        bReturn.setMaxWidth(COLUMN_WIDTH);
        bReturn.setOnClickListener(e -> returnItem(equipmentItem.getId()));
        row.addView(bReturn);

        table.addView(row);
    }

    private void createTableHeader() {
        TableRow headerRow = new TableRow(this);

        TextView headerName = new TextView(this);
        headerName.setText("NAME");
        headerName.setGravity(Gravity.LEFT);
        headerName.setMinWidth(COLUMN_WIDTH);
        headerName.setWidth(COLUMN_WIDTH);
        headerName.setMaxWidth(COLUMN_WIDTH);
        headerRow.addView(headerName);

        TextView headerAvailableAmount = new TextView(this);
        headerAvailableAmount.setText("AVAILABLE");
        headerAvailableAmount.setGravity(Gravity.CENTER);
        headerAvailableAmount.setMinWidth(COLUMN_WIDTH);
        headerAvailableAmount.setWidth(COLUMN_WIDTH);
        headerAvailableAmount.setMaxWidth(COLUMN_WIDTH);
        headerRow.addView(headerAvailableAmount);

        TextView headerTotalAmount = new TextView(this);
        headerTotalAmount.setText("TOTAL");
        headerTotalAmount.setGravity(Gravity.CENTER);
        headerTotalAmount.setMinWidth(COLUMN_WIDTH);
        headerTotalAmount.setWidth(COLUMN_WIDTH);
        headerTotalAmount.setMaxWidth(COLUMN_WIDTH);
        headerRow.addView(headerTotalAmount);

        table.addView(headerRow);
    }

    //id do zmiany url
    private void lendItem(Integer id){
        //wykonanie zapytania do backendu
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ApiEndpoints.LEND_EQUIPMENT.toString() + id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        );

        queue.add(stringRequest);

        //odswiezenie tabeli
        initTable();
    }

    private void returnItem(Integer id){
        //wykonanie zapytania do backendu
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ApiEndpoints.RETURN_EQUIPMENT.toString() + id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        );

        queue.add(stringRequest);

        //odswiezenie tabeli
        initTable();
    }
}