package com.br.labs.service

import com.br.labs.dto.*
import com.br.labs.model.Produto
import com.br.labs.model.Usuario
import com.br.labs.model.Venda
import com.br.labs.model.VendaProduto
import com.br.labs.repository.ProdutoRepository
import com.br.labs.repository.UsuarioRepository
import com.br.labs.repository.VendaProdutoRepository
import com.br.labs.repository.VendaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.round

@Service
class VendaProdutoService(
    private val vendaProdutoRepository: VendaProdutoRepository,
    private val vendaRepository: VendaRepository,
    private val produtoRepository: ProdutoRepository,
    private val usuarioRepository: UsuarioRepository
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
            val vendasAgrupadas = vendasUsuario.groupBy { Vendas(it.idVenda, it.totalVenda, it.dataVenda) }
            val somatorioProdutos = vendasUsuario.groupBy { Produto(it.idProduto, it.valor) }

            VendaProdutoDTO(
                idUsuario = idUsuario,
                nomeUsuario = vendasUsuario.first().nomeUsuario,
                vendas = vendasAgrupadas.map {
                    VendasDTO(
                        idVenda = it.key.idVenda,
                        totalVenda = round( somatorioProdutos.keys.sumOf { it.valor }),
                        dataVenda = it.key.dataVenda,
                        produtos = it.value.map { item ->
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

    fun converterArquivos(arquivo: MultipartFile): List<Pedido> {
        return arquivo.inputStream.bufferedReader().readLines().map {
            Pedido(
                idUsuario = it.substring(0..9).trimStart { caracter -> caracter == '0' }.trim().takeIf { it.isNotEmpty() }?.toInt(),
                nome = it.substring(10..54).trimStart().trim(),
                idVendaProduto = it.substring(55..65).trimStart { c -> c == '0' }.trim().takeIf { it.isNotEmpty() }?.toInt(),
                idProduto = it.substring(67..77).trimStart { c -> c == '0' }.trim().takeIf { it.isNotEmpty() }?.toInt(),
                valorProduto = it.substring(78..86).toDouble(),
                dataCompra = LocalDate.parse(it.substring(87..94), DateTimeFormatter.ofPattern("yyyyMMdd"))
            )
        }
    }

    @Transactional
    fun salvarDados(arquivo: MultipartFile): List<Pedido> {
        val pedidos = converterArquivos(arquivo)
        pedidos.forEach { pedido ->
            val produto = produtoRepository.saveAndFlush(Produto(pedido.idProduto, pedido.valorProduto))
            val usuario = usuarioRepository.saveAndFlush(Usuario(pedido.idUsuario, pedido.nome))
            val venda = vendaRepository.saveAndFlush(Venda(null, pedido.dataCompra, null, usuario))
            val vendaProduto = vendaProdutoRepository.saveAndFlush(VendaProduto(venda, produto, pedido.idVendaProduto))
        }
        return pedidos
    }
}