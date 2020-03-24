package com.rockbass.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener clickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAgregar = findViewById(R.id.fab_actualizar);
        buttonAgregar.setOnClickListener(
                (view)->{
                    Intent intent = new Intent(MainActivity.this, Contacto.class);
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
                    Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
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
}
