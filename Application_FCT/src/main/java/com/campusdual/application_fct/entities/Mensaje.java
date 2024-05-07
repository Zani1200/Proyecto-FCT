package com.campusdual.application_fct.entities;

import javax.persistence.*;

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int msj_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usu")
    private Usuario id_usu;
    @Column
    private String mensaje;

    public Mensaje(Usuario id_usu, String mensaje) {
        this.id_usu = id_usu;
        this.mensaje = mensaje;
    }

    public int getMsj_id() {
        return msj_id;
    }

    public void setMsj_id(int msj_id) {
        this.msj_id = msj_id;
    }

    public Usuario getId_usu() {
        return id_usu;
    }

    public void setId_usu(Usuario id_usu) {
        this.id_usu = id_usu;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
