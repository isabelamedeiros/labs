package com.br.labs.model

import jakarta.persistence.*

@Entity
@Table(name = "venda_produtos", schema = "labs")
data class VendaProduto(
    @ManyToOne
    @JoinColumn(name = "venda_id")
    val idVenda: Venda,

    @ManyToOne
    @JoinColumn(name = "produto_id")
    val idProduto: Produto,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_produto_id", length = 10)
    val idVendaProduto: Int? = null
)