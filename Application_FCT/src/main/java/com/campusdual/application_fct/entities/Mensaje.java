package com.campusdual.application_fct.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int msj_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_part")
    private Participantes id_part;
    @Column
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(Participantes id_part, String mensaje) {
        this.id_part = id_part;
        this.mensaje = mensaje;
    }

    public int getMsj_id() {
        return msj_id;
    }

    public void setMsj_id(int msj_id) {
        this.msj_id = msj_id;
    }

    public Participantes getId_part() {
        return id_part;
    }

    public void setId_part(Participantes id_part) {
        this.id_part = id_part;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
