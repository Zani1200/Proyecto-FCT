package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Usuario;

import java.util.List;

public interface ConsultasGeneral {

    Usuario validarUsuario(String nombre, String contrasenha);

}
