package com.br.labs.repository

import com.br.labs.model.Produtos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutosRepository : JpaRepository<Produtos, Int> {
    fun findByIdProduto(idProduto: Int?): List<Produtos>
}