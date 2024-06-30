package com.br.labs.dto

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

data class VendaProdutoDTO(
    val idUsuario: Int,
    val nomeUsuario: String,
    val vendas: List<VendasDTO>
) : Serializable

data class VendasDTO(
    val idVenda: Int,
    val totalVenda: BigDecimal,
    val dataVenda: LocalDate,
    val produtos: List<ProdutosDTO>
) : Serializable

data class ProdutosDTO(
    val idProduto: Int,
    val valor: BigDecimal
) : Serializable


