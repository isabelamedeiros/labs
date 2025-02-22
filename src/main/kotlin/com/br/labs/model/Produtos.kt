package com.br.labs.model

import jakarta.persistence.*

@Entity
@Table(name = "produtos", schema = "labs")
data class Produtos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id", length = 10)
    val idProduto: Int? = null,

    @Column(name = "valor", length = 12)
    val valor: Double
)