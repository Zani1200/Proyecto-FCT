package com.campusdual.application_fct.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chat_id;
    @Column
    private String chat_nombre;
    @Column
    private String chat_foto;
    @Column
    private int chat_puerto;
    @OneToMany(mappedBy = "id_chat", fetch = FetchType.LAZY)
    public List<Participantes> chat_part = new ArrayList<>();

    public Chat() {
    }

    public Chat(int chat_id, String chat_nombre, String chat_foto, int chat_puerto) {
        this.chat_id = chat_id;
        this.chat_nombre = chat_nombre;
        this.chat_foto = chat_foto;
        this.chat_puerto = chat_puerto;
    }

    public Chat(String chat_nombre, String chat_foto, int chat_puerto) {
        this.chat_nombre = chat_nombre;
        this.chat_foto = chat_foto;
        this.chat_puerto = chat_puerto;
    }

    public Chat(int chat_id, String chat_nombre, String chat_foto) {
        this.chat_id = chat_id;
        this.chat_nombre = chat_nombre;
        this.chat_foto = chat_foto;
    }

    public Chat(String chat_nombre, String chat_foto) {
        this.chat_nombre = chat_nombre;
        this.chat_foto = chat_foto;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_nombre() {
        return chat_nombre;
    }

    public void setChat_nombre(String chat_nombre) {
        this.chat_nombre = chat_nombre;
    }

    public String getChat_foto() {
        return chat_foto;
    }

    public void setChat_foto(String chat_foto) {
        this.chat_foto = chat_foto;
    }

    public int getChat_puerto() {
        return chat_puerto;
    }
    public void setChat_puerto(int chat_puerto) {
        this.chat_puerto = chat_puerto;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chat_id=" + chat_id +
                ", chat_nombre='" + chat_nombre + '\'' +
                ", chat_foto='" + chat_foto + '\'' +
                ", chat_puerto=" + chat_puerto +
                '}';
    }
}
