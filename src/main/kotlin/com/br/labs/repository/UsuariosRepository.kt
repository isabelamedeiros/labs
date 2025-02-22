package com.br.labs.repository

import com.br.labs.model.Usuarios
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuariosRepository : JpaRepository<Usuarios, Int> {
    fun findByIdUsuario(idUsuario: Int?): List<Usuarios>
}