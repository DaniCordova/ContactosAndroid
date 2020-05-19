package com.rockbass.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rockbass.contactos.bd.ClaseContacto;
import com.rockbass.contactos.bd.ConexionSQLiteHelper;
import com.rockbass.contactos.bd.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener clickListener;
    ConexionSQLiteHelper conn;
    ArrayList <ClaseContacto> listaContactoBD;
    ArrayList <String> listaContactoBD_String;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new ConexionSQLiteHelper(this, "bd_contactos", null, 1);

        ConsultarContactos();
        ListaContactoObtener();

        FloatingActionButton buttonAgregar = findViewById(R.id.fab_agregar);
        buttonAgregar.setOnClickListener(
                (view)->{
                    Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                    startActivity(intent);
                }

        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_contactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdapterPersona adapterPersona = new AdapterPersona();
        recyclerView.setAdapter(adapterPersona);
    }

    class AdapterPersona extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.persona_card, parent, false);

            return new RecyclerView.ViewHolder(view) {};
        }




        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            View view = holder.itemView;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Contacto.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });


            Persona persona = Memory.PERSONAS.get(position);

            TextView nombreCompleto, edad, telefono, email, contacto;
            nombreCompleto = view.findViewById(R.id.textViewNombreCompleto);
            edad = view.findViewById(R.id.textViewEdad);
            telefono = view.findViewById(R.id.textViewTelefono);
            email = view.findViewById(R.id.textViewEmail);
            contacto = view.findViewById(R.id.textViewContacto);

            nombreCompleto.setText(
                    String.format("%s %s %s",
                            persona.nombre,
                            persona.apellidoPaterno,
                            persona.apellidoMaterno)
            );

            edad.setText(Integer.toString(persona.edad));

            telefono.setText(persona.telefono);

            email.setText(persona.email);

            contacto.setText(persona.contactoConfianza);
        }

        @Override
        public int getItemCount() {
            return Memory.PERSONAS.size();
        }
    }

    private void ConsultarContactos(){
        SQLiteDatabase db=conn.getReadableDatabase();

        ClaseContacto contacto1 = null;
        listaContactoBD = new ArrayList<ClaseContacto>();

        Cursor cursor= db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CONTACTO,null);

        while (cursor.moveToNext()){
            contacto1 = new ClaseContacto();
            contacto1.setNombre(cursor.getString(0));
            listaContactoBD.add(contacto1);
        }


    }

    private void ListaContactoObtener(){
        listaContactoBD_String = new ArrayList<String>();

        for(int i=0;i<listaContactoBD_String.size();i++){
            listaContactoBD_String.add(listaContactoBD.get(i).getNombre());
        }
    }
}
