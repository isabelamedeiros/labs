package com.br.labs.controller

import com.br.labs.model.Usuarios
import com.br.labs.repository.UsuariosRepository
import com.br.labs.service.UsuariosService
import com.br.labs.service.VendaProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/labs")
class UsuariosController(
    private val vendaProdutoService: VendaProdutoService,
    private val usuariosService: UsuariosService,
    private val usuariosRepository: UsuariosRepository
) {

    @GetMapping("/listar-usuarios")
    fun listarTodosUsuarios(): List<Usuarios> {
        return usuariosRepository.findAll()
    }

    @GetMapping("/{idCliente}/listar-usuarios")
    fun listarUsuariosById(@PathVariable idCliente: Int): List<Usuarios> {
        return usuariosService.listarUsuarioById(idCliente)
    }

    @PostMapping("/salvar-usuarios")
    fun salvarUsuarios(
        @RequestBody usuarios: Usuarios
    ): ResponseEntity<Usuarios> {
        return try {
            val salvarUsuarios = usuariosService.salvarUsuarios(usuarios)
            ResponseEntity(salvarUsuarios, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}