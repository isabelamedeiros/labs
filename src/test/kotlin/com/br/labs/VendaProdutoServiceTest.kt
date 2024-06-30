package com.br.labs

import com.br.labs.dto.ProdutosDTO
import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.dto.VendaProdutoInterface
import com.br.labs.dto.VendasDTO
import com.br.labs.model.Produto
import com.br.labs.model.Usuario
import com.br.labs.model.Venda
import com.br.labs.model.VendaProduto
import com.br.labs.repository.VendaProdutoRepository
import com.br.labs.repository.VendaRepository
import com.br.labs.service.VendaProdutoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class VendaProdutoServiceTest {

    @Mock
    lateinit var vendaProdutoRepository: VendaProdutoRepository

    @Mock
    lateinit var vendaRepository: VendaRepository

    @InjectMocks
    lateinit var vendaProdutoService: VendaProdutoService

    @Test
    fun `Metodo listarPedidos deve retornar uma lista de usuarios com vendas e produtos`() {

        val mockVendaProdutoInterface = listOf(
            object : VendaProdutoInterface {
                override val idUsuario = 88
                override val nomeUsuario = "Terra Daniel"
                override val idVenda = 836
                override val totalVenda = BigDecimal("1899.02")
                override val dataVenda = LocalDate.parse("2021-09-09")
                override val idProduto = 3
                override val valor = BigDecimal("1899.02")
            })

        val mockVendaProdutoDTO = listOf(
            VendaProdutoDTO(
                idUsuario = 88,
                nomeUsuario = "Terra Daniel",
                vendas = listOf(
                    VendasDTO(
                        idVenda = 836,
                        totalVenda = BigDecimal("1899.02"),
                        dataVenda = LocalDate.parse("2021-09-09"),
                        produtos = listOf(
                            ProdutosDTO(
                                idProduto = 3,
                                valor = BigDecimal("1899.02")
                            )
                        )
                    )
                )
            )
        )

        whenever(vendaProdutoRepository.findVendaProdutos()).thenReturn(mockVendaProdutoInterface)
        val getService = vendaProdutoService.listarPedidos()
        assertEquals(mockVendaProdutoDTO, getService)
    }

    @Test
    fun `Metodo listarVendasById deve retornar uma lista de vendas pelo ID`() {
        val idVendaProduto = 1

        val mockVendaProduto = listOf(
            VendaProduto(
                idVenda = Venda(
                    835,
                    LocalDate.now(),
                    BigDecimal.valueOf(1888.02),
                    Usuario(
                        88,
                        "Terra Daniel"
                    )
                ),
                idProduto = Produto(
                    1,
                    BigDecimal.valueOf(1888.02)
                )
            )
        )

        `when`(vendaProdutoRepository.findByIdVendaProduto(idVendaProduto)).thenReturn(mockVendaProduto)
        val resultado = vendaProdutoService.listarVendasById(idVendaProduto)
        assertEquals(mockVendaProduto, resultado)
    }

    @Test
    fun `Metodo listarVendasById deve retornar uma lista vazia de vendas quando não houver ID`() {
        val idVendaProduto = 999

        `when`(vendaProdutoRepository.findByIdVendaProduto(idVendaProduto)).thenReturn(emptyList())
        val resultado = vendaProdutoService.listarVendasById(idVendaProduto)
        assertEquals(emptyList<VendaProduto>(), resultado)
    }

    @Test
    fun `Metodo listarVendasPorData deve retornar uma lista de vendas no intervalo de data escolhido`() {
        val idVendaProduto = 1

        val mockVendaPorData = listOf(
            Venda(
                    835,
                    LocalDate.now(),
                    BigDecimal.valueOf(1888.02),
                    Usuario(
                        88,
                        "Terra Daniel"
                    )
                )
            )

        `when`(vendaRepository.findByData(LocalDate.now(), LocalDate.now())).thenReturn(mockVendaPorData)
        val resultado = vendaProdutoService.listarVendasPorData(LocalDate.now(), LocalDate.now())
        assertEquals(mockVendaPorData, resultado)
    }


}