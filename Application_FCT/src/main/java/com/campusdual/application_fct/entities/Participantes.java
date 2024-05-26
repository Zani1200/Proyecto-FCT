package com.campusdual.application_fct.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Participantes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int part_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usu")
    private Usuario id_usu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat")
    private Chat id_chat;
    @OneToMany(mappedBy = "id_part", fetch = FetchType.LAZY)
    public List<Mensaje> part_mensaje_list = new ArrayList<>();

    public Participantes(Usuario id_usu, Chat id_chat) {
        this.id_usu = id_usu;
        this.id_chat = id_chat;
    }



    public Participantes(int part_id) {
        this.part_id = part_id;
    }

    public Participantes(Usuario id_usu) {
        this.id_usu = id_usu;
    }

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public Usuario getId_usu() {
        return id_usu;
    }

    public void setId_usu(Usuario id_usu) {
        this.id_usu = id_usu;
    }

    public Chat getId_chat() {
        return id_chat;
    }

    public void setId_chat(Chat id_chat) {
        this.id_chat = id_chat;
    }

    public List<Mensaje> getPart_mensaje_list() {
        return part_mensaje_list;
    }

    public void setPart_mensaje_list(List<Mensaje> part_mensaje_list) {
        this.part_mensaje_list = part_mensaje_list;
    }

    @Override
    public String toString() {
        return "Participantes{" +
                "part_id=" + part_id +
                ", id_usu=" + id_usu +
                ", id_chat=" + id_chat +
                '}';
    }
}
