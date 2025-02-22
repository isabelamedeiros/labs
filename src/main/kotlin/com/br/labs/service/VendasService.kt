package com.br.labs.service

import com.br.labs.model.Vendas
import com.br.labs.repository.VendasRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class VendasService(
    private val vendasRepository: VendasRepository
) {

    fun listarVendasPorData(dataInicio: LocalDate, dataFim: LocalDate): List<Vendas> {
        return vendasRepository.findByData(dataInicio, dataFim)
    }

    fun findByIdVendas(idVenda: Int?): Boolean {
        val idVenda = vendasRepository.findByIdVenda(idVenda)
        if (idVenda !== null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um registro com os mesmos parâmetros.")
        return true
    }

    @Transactional
    fun salvarVendas(vendas: Vendas): Vendas {
        findByIdVendas(vendas.idVenda)
        return vendasRepository.saveAndFlush(vendas)
    }

}