package com.br.labs.dto

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

data class Vendas(
    val idVenda: Int,
    val totalVenda: BigDecimal?,
    val dataVenda: LocalDate?
) : Serializable

data class VendasDTO(
    val idVenda: Int,
    val totalVenda: Double?,
    val dataVenda: LocalDate?,
    val produtos: List<ProdutosDTO>
) : Serializable
