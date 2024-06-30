package com.br.labs.controller

import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.model.Venda
import com.br.labs.model.VendaProduto
import com.br.labs.service.VendaProdutoService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

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

    @GetMapping("/listar-por-data")
    fun listarVendasPorData(
        @RequestParam("dataInicio") dataInicio: LocalDate,
        @RequestParam("dataFim") dataFim: LocalDate
    ): List<Venda> {
        return vendaProdutoService.listarVendasPorData(dataInicio, dataFim)
    }

    @GetMapping("/listar-produtos")
    fun listarPedidos(): List<VendaProdutoDTO> {
        return vendaProdutoService.listarPedidos()
    }
}