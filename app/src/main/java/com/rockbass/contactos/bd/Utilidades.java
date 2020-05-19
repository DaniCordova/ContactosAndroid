package com.rockbass.contactos.bd;

public class Utilidades {

    public static final String TABLA_CONTACTO="contacto";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";
    public static final String CAMPO_EDAD="edad";
    public static final String CAMPO_EMAIL="email";
    public static final String CAMPO_CONTACTO_CONFIANZA="contacto_confianza";
    public static final String CREAR_TABLA_CONTACTO = "CREATE TABLE "+ TABLA_CONTACTO +" ("+ CAMPO_NOMBRE +" TEXT, "+ CAMPO_TELEFONO +" TEXT, "+ CAMPO_EDAD +" TEXT, "+ CAMPO_EMAIL +" TEXT, "+ CAMPO_CONTACTO_CONFIANZA +" TEXT)";

}
