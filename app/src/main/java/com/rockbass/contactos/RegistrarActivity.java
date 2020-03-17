package com.rockbass.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno,
            editTextEdad, editTextTelefono, editTextEmail;

    private Spinner spinner_Contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = findViewById(R.id.editTextApellidoMaterno);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinner_Contacto = findViewById(R.id.spinner_contactoConfianza);



        ArrayList<String> nombres = new ArrayList<>();

        for(Persona persona : Memory.PERSONAS){
            nombres.add(persona.nombre);
        }

        spinner_Contacto.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,nombres));


        FloatingActionButton fabRegistrar = findViewById(R.id.fab_registrar);
        fabRegistrar.setOnClickListener(
                view->{
                    Persona persona2 = new Persona();
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


                    Memory.PERSONAS.add(persona2);

                    finish();
                }
        );
    }
}
