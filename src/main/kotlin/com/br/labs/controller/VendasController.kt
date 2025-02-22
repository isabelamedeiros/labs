package com.br.labs.controller

import com.br.labs.model.Vendas
import com.br.labs.model.VendaProduto
import com.br.labs.repository.VendasRepository
import com.br.labs.service.VendaProdutoService
import com.br.labs.service.VendasService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/labs")
class VendasController(
    private val vendasService: VendasService,
    private val vendaProdutoService: VendaProdutoService,
    private val vendasRepository: VendasRepository
) {

    @GetMapping("/listar-vendas")
    fun listarTodasVendas(): List<Vendas> {
        return vendasRepository.findAll()
    }

    @GetMapping("/{idVendaProduto}/listar-vendas")
    fun listarVendaById(@PathVariable idVendaProduto: Int): List<VendaProduto> {
        return vendaProdutoService.listarVendasProdutoById(idVendaProduto)
    }

    @GetMapping("/listar-por-data")
    fun listarVendasPorData(
        @RequestParam("dataInicio") dataInicio: LocalDate,
        @RequestParam("dataFim") dataFim: LocalDate
    ): List<Vendas> {
        return vendaProdutoService.listarVendasPorData(dataInicio, dataFim)
    }

    @PostMapping("/salvar-vendas")
    fun salvarVendas(
        @RequestBody vendas: Vendas
    ): ResponseEntity<Vendas> {
        return try {
            val salvarVendas = vendasService.salvarVendas(vendas)
            ResponseEntity(salvarVendas, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}