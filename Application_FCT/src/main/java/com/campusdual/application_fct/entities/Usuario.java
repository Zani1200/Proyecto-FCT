package com.campusdual.application_fct.entities;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usu_id;
    @Column
    private String usu_nombre;
    @Column
    private String usu_contrasenha;
    @Column
    private String usu_foto;
    @Column
    private int usu_activo;
    @OneToMany(mappedBy = "id_usu", fetch = FetchType.LAZY)
    public List<Participantes> usu_part = new ArrayList<>();

    public Usuario() {
    }
    public Usuario(int usu_id, String usu_nombre, String usu_contrasenha, String usu_foto, int usu_activo) throws IOException {
        this.usu_id = usu_id;
        this.usu_nombre = usu_nombre;
        this.usu_contrasenha = usu_contrasenha;
        this.usu_foto = usu_foto;
        this.usu_activo = usu_activo;
    }

    public Usuario(int usu_id) {
        this.usu_id = usu_id;
    }

    public Usuario(String usu_nombre, String usu_foto, int usu_activo) {
        this.usu_nombre = usu_nombre;
        this.usu_foto = usu_foto;
        this.usu_activo = usu_activo;
    }

    public Usuario(String usu_nombre, String usu_contrasenha, String usu_foto, int usu_activo) {
        this.usu_nombre = usu_nombre;
        this.usu_contrasenha = usu_contrasenha;
        this.usu_foto = usu_foto;
        this.usu_activo = usu_activo;
    }

    public Usuario(int usu_id, String usu_nombre, String usu_foto) {
        this.usu_id = usu_id;
        this.usu_nombre = usu_nombre;
        this.usu_foto = usu_foto;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_contrasenha() {
        return usu_contrasenha;
    }

    public void setUsu_contrasenha(String usu_contrasenha) {
        this.usu_contrasenha = usu_contrasenha;
    }

    public String getUsu_foto() {
        return usu_foto;
    }

    public void setUsu_foto(String usu_foto) {
        this.usu_foto = usu_foto;
    }

    public int getUsu_activo() {
        return usu_activo;
    }
    public void setUsu_activo(int usu_activo) {
        this.usu_activo = usu_activo;
    }

    public List<Participantes> getUsu_part() {
        return usu_part;
    }

    public void setUsu_part(List<Participantes> usu_part) {
        this.usu_part = usu_part;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usu_id=" + usu_id +
                ", usu_nombre='" + usu_nombre + '\'' +
                ", usu_contrasenha='" + usu_contrasenha + '\'' +
                ", usu_foto='" + usu_foto + '\'' +
                ", usu_activo=" + usu_activo +
                '}';
    }


}
