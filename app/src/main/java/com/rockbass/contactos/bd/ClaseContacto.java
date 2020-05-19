package com.rockbass.contactos.bd;

import java.io.Serializable;

public class ClaseContacto implements Serializable {

    private String nombre;
    private  String telefono;
    private String edad;
    private String email;
    private String contactoConfianza;

    public ClaseContacto(String nombre, String telefono, String edad, String email, String contactoConfianza) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.email = email;
        this.contactoConfianza = contactoConfianza;
    }

    public ClaseContacto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactoConfianza() {
        return contactoConfianza;
    }

    public void setContactoConfianza(String contactoConfianza) {
        this.contactoConfianza = contactoConfianza;
    }
}
