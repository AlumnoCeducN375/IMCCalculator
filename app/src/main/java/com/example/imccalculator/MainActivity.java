package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nombrePersona;
    private EditText pesoPersona;
    private EditText alturaPersona;
    private EditText imcPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombrePersona = (EditText) findViewById(R.id.txtNombre);
        pesoPersona=(EditText) findViewById(R.id.txtPeso);
        alturaPersona=(EditText) findViewById(R.id.txtAltura);
        imcPersona=(EditText) findViewById(R.id.txtIMC);
    }

    public void calcularIMC (View view)
    {
        double peso =Double.parseDouble(pesoPersona.getText().toString());
        double altura = Double.parseDouble(alturaPersona.getText().toString());
        double alturaPotencia = altura * altura;
        double calculoIMC = ((peso/alturaPotencia));
        imcPersona.setText(""+calculoIMC);

        if (calculoIMC<=18.5)
        {
            Toast.makeText(this, "Usted esta Bajo Peso", Toast.LENGTH_SHORT).show();
        }
        if (calculoIMC>18.5 && calculoIMC<=24.9)
        {
            Toast.makeText(this, "Usted esta entre los valores Normales o de peso saludable", Toast.LENGTH_SHORT).show();
        }
        if (calculoIMC>24.9 && calculoIMC<=29.9)
        {
            Toast.makeText(this, "Usted tiene Sobrepeso", Toast.LENGTH_SHORT).show();
        }
        if (calculoIMC>29.9)
        {
            Toast.makeText(this, "Usted tiene Obesidad", Toast.LENGTH_SHORT).show();
        }
    }

    public void crearTabla (View view)
    {
        ConexionDB baseDeDatos = new ConexionDB(this,"Tabla",null,1);
        SQLiteDatabase editableDB = baseDeDatos.getWritableDatabase();
        String name = nombrePersona.getText().toString();
        String weight = pesoPersona.getText().toString();
        String height = alturaPersona.getText().toString();
        String imc = imcPersona.getText().toString();

        ContentValues paquetedeImc = new ContentValues();
        paquetedeImc.put("nombre", name);
        paquetedeImc.put("peso", weight);
        paquetedeImc.put("altura",height);
        paquetedeImc.put("imc", imc);

        editableDB.insert("usuarioIMC", null, paquetedeImc);
        editableDB.close();
        Toast.makeText(this, "IMC Guardado Correctamente", Toast.LENGTH_SHORT).show();
    }



}