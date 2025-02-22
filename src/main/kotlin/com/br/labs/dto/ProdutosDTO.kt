package com.br.labs.dto

import java.io.Serializable

data class ProdutosDTO(
    val idProduto: Int,
    val valor: Double
) : Serializable
