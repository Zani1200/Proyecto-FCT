package com.campusdual.application_fct.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chat_id;
    @Column
    private int chat_port;
    @Column
    private String chat_host;
    @OneToMany(mappedBy = "id_chat", fetch = FetchType.LAZY)
    public List<Participantes> chat_part = new ArrayList<>();

    public Chat(int chat_id, int chat_port, String chat_host) {
        this.chat_id = chat_id;
        this.chat_port = chat_port;
        this.chat_host = chat_host;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getChat_port() {
        return chat_port;
    }

    public void setChat_port(int chat_port) {
        this.chat_port = chat_port;
    }

    public String getChat_host() {
        return chat_host;
    }

    public void setChat_host(String chat_host) {
        this.chat_host = chat_host;
    }

    public List<Participantes> getChat_part() {
        return chat_part;
    }

    public void setChat_part(List<Participantes> chat_part) {
        this.chat_part = chat_part;
    }
}
