package com.br.labs.controller

import com.br.labs.dto.Pedido
import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.model.VendaProduto
import com.br.labs.service.VendaProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/labs")
class VendaProdutosController(
    private val vendaProdutoService: VendaProdutoService
) {

    @GetMapping("/obter-dados")
    fun obterListaPedidos(): List<VendaProduto> {
        return vendaProdutoService.listarDados()
    }

    @GetMapping("/listar-vendas-produtos")
    fun listarPedidos(): List<VendaProdutoDTO> {
        return vendaProdutoService.listarPedidos()
    }

    @PostMapping("/salvar-dados")
    fun salvarDados(
        @RequestParam("arquivo") arquivo: MultipartFile
    ): ResponseEntity<List<Pedido>> {
        return try {
            val arquivoConvertido = vendaProdutoService.salvarDados(arquivo)
            ResponseEntity(arquivoConvertido, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}