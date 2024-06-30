package com.br.labs.service

import com.br.labs.dto.ProdutosDTO
import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.dto.VendasDTO
import com.br.labs.model.Venda
import com.br.labs.model.VendaProduto
import com.br.labs.repository.VendaProdutoRepository
import com.br.labs.repository.VendaRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VendaProdutoService(
    private val vendaProdutoRepository: VendaProdutoRepository,
    private val vendaRepository: VendaRepository
) {
    fun listarDados(): List<VendaProduto> {
        return vendaProdutoRepository.findAll()
    }

    fun listarVendasById(idVendaProduto: Int): List<VendaProduto> {
        return vendaProdutoRepository.findByIdVendaProduto(idVendaProduto)
    }

    fun listarVendasPorData(dataInicio: LocalDate, dataFim: LocalDate): List<Venda> {
        return vendaRepository.findByData(dataInicio, dataFim)
    }

    fun listarPedidos(): List<VendaProdutoDTO> {
        val resultados = vendaProdutoRepository.findVendaProdutos()

        val resultadoAgrupado = resultados.groupBy { it.idUsuario }

        return resultadoAgrupado.map { (idUsuario, vendasUsuario) ->
            val vendasAgrupadas = vendasUsuario.groupBy { it.idVenda }

            VendaProdutoDTO(
                idUsuario = idUsuario,
                nomeUsuario = vendasUsuario.first().nomeUsuario,
                vendas = vendasAgrupadas.map { (idVenda, itensVenda) ->
                    VendasDTO(
                        idVenda = idVenda,
                        totalVenda = itensVenda.first().totalVenda,
                        dataVenda = itensVenda.first().dataVenda,
                        produtos = itensVenda.map { item ->
                            ProdutosDTO(
                                idProduto = item.idProduto,
                                valor = item.valor
                            )
                        }
                    )
                }
            )
        }
    }
}