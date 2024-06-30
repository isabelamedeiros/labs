package com.br.labs.controller

import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.model.VendaProduto
import com.br.labs.service.VendaProdutoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/labs")
class VendaProdutosController(
    private val vendaProdutoService: VendaProdutoService
) {

    @GetMapping("/obter-dados")
    fun obterListaPedidos(): List<VendaProduto> {
        return vendaProdutoService.listarDados()
    }

    @GetMapping("/{idVendaProduto}/listar-vendas")
    fun listarVendaById(@PathVariable idVendaProduto: Int): List<VendaProduto> {
        return vendaProdutoService.listarVendasById(idVendaProduto)
    }

    @GetMapping("/listar-produtos")
    fun listarPedidos(): List<VendaProdutoDTO> {
        return vendaProdutoService.listarPedidos()
    }
}