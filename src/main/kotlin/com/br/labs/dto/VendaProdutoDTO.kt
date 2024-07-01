package com.br.labs.dto

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

interface VendaProdutoInterface {
    val idUsuario: Int
    val nomeUsuario: String
    val idVenda: Int
    val totalVenda: BigDecimal
    val dataVenda: LocalDate
    val idProduto: Int
    val valor: BigDecimal
}

data class VendaProdutoDTO(
    val idUsuario: Int,
    val nomeUsuario: String,
    val vendas: List<VendasDTO>
) : Serializable

data class Vendas(
    val idVenda: Int,
    val totalVenda: BigDecimal,
    val dataVenda: LocalDate
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


