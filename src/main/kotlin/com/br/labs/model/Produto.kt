package com.br.labs.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "produtos", schema = "labs")
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id", length = 10)
    val idProduto: Int? = null,

    @Column(name = "valor", length = 12)
    val valor: BigDecimal
)