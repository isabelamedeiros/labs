package com.br.labs.repository

import com.br.labs.dto.VendaProdutoDTO
import com.br.labs.dto.VendaProdutoInterface
import com.br.labs.model.VendaProduto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VendaProdutoRepository : JpaRepository<VendaProduto, Int> {

    fun findByIdVendaProduto(idVendaProduto: Int): List<VendaProduto>

    @Query(
        """SELECT u.usuario_id AS idUsuario,
                     u.usuario_nome AS nomeUsuario,
                     v.venda_id AS idVenda,
                     v.total AS totalVenda,
                     v.data_venda AS dataVenda,
                     p.produto_id AS idProduto,
                     p.valor AS valor
              FROM labs.usuarios u
              INNER JOIN labs.vendas v ON u.usuario_id = v.usuario_id
              INNER JOIN labs.venda_produtos vp ON vp.venda_id = v.venda_id
              INNER JOIN labs.produtos p ON p.produto_id = vp.produto_id
              ORDER BY v.data_venda""", nativeQuery = true
    )
    fun findVendaProdutos(): List<VendaProdutoInterface>


}