package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rockbass.contactos.bd.ClaseContacto;
import com.rockbass.contactos.bd.ConexionSQLiteHelper;
import com.rockbass.contactos.bd.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;

    private Spinner spinner_Contacto;

    private String nombreCompletoBD, emailBD, telefonoBD, contactoConfianzaBD;

    private int edadBD;

    private ArrayList <ClaseContacto> list_ContactoConfianza;
    private ArrayList <String> lista ;


    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        conn = new ConexionSQLiteHelper(this, "bd_contactos", null, 1);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinner_Contacto = findViewById(R.id.spinner_contactoConfianza);

        ConsultaContacto();


        List<String> listaPersonas = new ArrayList<>();

        List<Persona> PERSONAS = new ArrayList<>();

        spinner_Contacto.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,lista));

        FloatingActionButton fabRegistrar = findViewById(R.id.fab_registrar);
        fabRegistrar.setOnClickListener(
                view->{

                    registrarContacto();
                    finish();
                    /* Persona persona2 = new Persona();
                    persona2.nombre = editTextNombre.getText().toString();
                    persona2.apellidoPaterno = editTextApellidoPaterno.getText().toString();
                    persona2.apellidoMaterno = editTextApellidoMaterno.getText().toString();
                    persona2.edad = Integer.parseInt(editTextEdad.getText().toString());
                    persona2.telefono = editTextTelefono.getText().toString();
                    persona2.email = editTextEmail.getText().toString();

                    if(spinner_Contacto.getSelectedItem() == null){

                        persona2.contactoConfianza = " ";

                    }else{
                        persona2.contactoConfianza = spinner_Contacto.getSelectedItem().toString();
                    }


                    Memory.PERSONAS.add(persona2); */
                }
        );
    }

    private void registrarContacto() {

        SQLiteDatabase db = conn.getWritableDatabase();

        this.nombreCompletoBD = editTextNombre.getText().toString() + " " + editTextApellidoPaterno.getText().toString() + " " + editTextApellidoMaterno.getText().toString();


        if(spinner_Contacto.getSelectedItem() == null){
            this.contactoConfianzaBD = "";
        }else{
            this.contactoConfianzaBD = spinner_Contacto.getSelectedItem().toString();
        }

        this.telefonoBD = editTextEmail.getText().toString();
        this.emailBD = editTextEmail.getText().toString();
        this.edadBD = Integer.parseInt(editTextEdad.getText().toString());

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, this.nombreCompletoBD);
        values.put(Utilidades.CAMPO_TELEFONO, this.telefonoBD);
        values.put(Utilidades.CAMPO_EDAD, this.edadBD);
        values.put(Utilidades.CAMPO_EMAIL, this.emailBD);
        values.put(Utilidades.CAMPO_CONTACTO_CONFIANZA, this.contactoConfianzaBD);

        Long nombreResultante = db.insert(Utilidades.TABLA_CONTACTO,Utilidades.CAMPO_NOMBRE,values);

        db.close();
    }

    private void ConsultaContacto(){
        SQLiteDatabase db = conn.getReadableDatabase();
        ClaseContacto contacto1 = null;

        list_ContactoConfianza = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_CONTACTO,null);

        while (cursor.moveToNext()){
            contacto1=new ClaseContacto();
            contacto1.setNombre(cursor.getString(0));

            list_ContactoConfianza.add(contacto1);
        }

        lista=new ArrayList<String>();
        lista.add("Selecciona un contacto de confianza");

        for(int i=0; i<list_ContactoConfianza.size();i++){
            lista.add(list_ContactoConfianza.get(i).getNombre().toString());
        }

        db.close();

    }

}
