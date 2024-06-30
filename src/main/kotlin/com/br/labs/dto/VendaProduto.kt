package com.br.labs.dto

import java.math.BigDecimal
import java.time.LocalDate

data class VendaProduto (
    val idUsuario: Int,
    val nomeUsuario: String,
    val vendas: List<Vendas>
)

data class Vendas (
    val idVenda: Int,
    val totalVenda: BigDecimal,
    val dataVenda: LocalDate,
    val produtos: List<Produtos>
)

data class Produtos (
    val idProduto: Int,
    val valor: BigDecimal
)
