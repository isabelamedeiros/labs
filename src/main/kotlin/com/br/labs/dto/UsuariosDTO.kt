package com.br.labs.dto

import java.io.Serializable

data class UsuariosDTO(
    val idUsuario: Int,
    val nomeUsuario: String,
    val vendas: List<VendasDTO>
) : Serializable