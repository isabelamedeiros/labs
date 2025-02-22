package com.br.labs.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios", schema = "labs")
data class Usuarios(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", length = 10)
    val idUsuario: Int? = null,

    @Column(name = "usuario_nome", length = 45)
    val nomeUsuario: String
)








