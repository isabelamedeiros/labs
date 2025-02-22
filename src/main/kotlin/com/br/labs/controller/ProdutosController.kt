package com.br.labs.controller

import com.br.labs.model.Produtos
import com.br.labs.repository.ProdutosRepository
import com.br.labs.service.ProdutosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/labs")
class ProdutosController(
    private val produtoService: ProdutosService,
    private val produtosRepository: ProdutosRepository
) {
    @GetMapping("/listar-produtos")
    fun listarTodosProdutos(): List<Produtos> {
        return produtosRepository.findAll()
    }

    @GetMapping("/{idProduto}/listar-produtos")
    fun listarProdutosById(@PathVariable idProduto: Int): List<Produtos> {
        return produtoService.listarProdutosById(idProduto)
    }

    @PostMapping("/salvar-produtos")
    fun salvarProdutos(
        @RequestBody produtos: Produtos
    ): ResponseEntity<Produtos> {
        return try {
            val salvarProdutos = produtoService.salvarProdutos(produtos)
            ResponseEntity(salvarProdutos, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}