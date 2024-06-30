package com.br.labs.service

import com.br.labs.dto.ProdutosDTO
import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.dto.VendasDTO
import com.br.labs.model.VendaProduto
import com.br.labs.repository.VendaProdutoRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.sql.Date

@Service
class VendaProdutoService(
    private val vendaProdutoRepository: VendaProdutoRepository
) {
    fun listarDados(): List<VendaProduto> {
        return vendaProdutoRepository.findAll()
    }

    fun listarVendasById(idVendaProduto: Int): List<VendaProduto> {
        return vendaProdutoRepository.findByIdVendaProduto(idVendaProduto)
    }

    fun listarPedidos(): List<VendaProdutoDTO> {
        val resultados = vendaProdutoRepository.findVendaProdutos()

        val listaDTO = resultados.map { result ->
            val idUsuario = result[0] as Int
            val nomeUsuario = result[1] as String
            val idVenda = result[2] as Int
            val totalVenda = result[3] as BigDecimal
            val dataVendaSql = result[4] as Date
            val dataVenda = dataVendaSql.toLocalDate()
            val idProduto = result[5] as Int
            val valorProduto = result[6] as BigDecimal

            VendaProdutoDTO(
                idUsuario = idUsuario,
                nomeUsuario = nomeUsuario,
                vendas = listOf(
                    VendasDTO(
                        idVenda = idVenda,
                        totalVenda = totalVenda,
                        dataVenda = dataVenda,
                        produtos = listOf(
                            ProdutosDTO(
                                idProduto = idProduto,
                                valor = valorProduto
                            )
                        )
                    )
                )
            )
        }
        return listaDTO
    }


}
