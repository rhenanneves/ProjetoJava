package com.example.Controller;

import com.example.Model.Funcionario;
import com.example.Model.Gerente;

public class LoginController {
    // Autentica o usuário com base no nome e senha
    public boolean autenticarUsuario(String usuario, String senha) {
        // Lógica de autenticação (ex.: verificar no banco de dados)
        // Aqui seria uma simulação. No mundo real, deve-se verificar no banco.
        return ("gerente".equals(usuario) && "senha123".equals(senha)) || ("funcionario".equals(usuario) && "senha123".equals(senha));
    }

    // Verifica se o usuário é gerente
    public boolean isGerente(String usuario) {
        return "gerente".equals(usuario);
    }
}
