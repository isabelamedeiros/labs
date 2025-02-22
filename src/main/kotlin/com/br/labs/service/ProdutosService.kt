package com.br.labs.service

import com.br.labs.model.Produtos
import com.br.labs.repository.ProdutosRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProdutosService(
    private val produtosRepository: ProdutosRepository
) {
    fun listarProdutosById(idProduto: Int): List<Produtos> {
        return produtosRepository.findByIdProduto(idProduto)
    }

    fun findByIdProdutos(idProduto: Int?): Boolean {
        val idProduto = produtosRepository.findByIdProduto(idProduto)
        if (idProduto !== null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um registro com os mesmos parâmetros.")
        return true
    }

    @Transactional
    fun salvarProdutos(produtos: Produtos): Produtos {
        findByIdProdutos(produtos.idProduto)
        return produtosRepository.saveAndFlush(produtos)
    }
}