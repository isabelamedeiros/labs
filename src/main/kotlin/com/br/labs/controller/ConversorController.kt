package com.br.labs.controller

import com.br.labs.dto.Pedido
import com.br.labs.service.VendaProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/labs")
class ConversorController(
    private val vendaProdutoService: VendaProdutoService
) {

    @PostMapping("/converter-dados")
    fun conversorDados(
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