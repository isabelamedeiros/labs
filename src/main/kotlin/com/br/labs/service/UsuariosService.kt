package com.br.labs.service

import com.br.labs.model.Usuarios
import com.br.labs.repository.UsuariosRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsuariosService(
    private val usuariosRepository: UsuariosRepository
) {

    fun listarUsuarioById(idUsuario: Int): List<Usuarios> {
        return usuariosRepository.findByIdUsuario(idUsuario)
    }
    fun findByIdUsuario(idUsuario: Int?): Boolean {
        val idUsuario = usuariosRepository.findByIdUsuario(idUsuario)
        if (idUsuario !== null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um registro com os mesmos parâmetros.")
        return true
    }

    @Transactional
    fun salvarUsuarios(usuarios: Usuarios): Usuarios{
        findByIdUsuario(usuarios.idUsuario)
        return usuariosRepository.saveAndFlush(usuarios)
    }
}