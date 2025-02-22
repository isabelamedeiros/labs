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
    val valor: Double
}

data class VendaProdutoDTO(
    val idUsuario: Int,
    val nomeUsuario: String,
    val vendas: List<VendasDTO>
) : Serializable

data class Pedido(
    val idUsuario: Int?,
    val nome: String,
    val idVendaProduto: Int?,
    val idProduto: Int?,
    val valorProduto: Double,
    val dataCompra: LocalDate
)
