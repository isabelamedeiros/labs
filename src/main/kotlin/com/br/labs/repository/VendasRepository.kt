package com.br.labs.repository

import com.br.labs.model.Vendas
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface VendasRepository : JpaRepository<Vendas, Int> {
    fun findByIdVenda(idVenda: Int?): List<Vendas>

    @Query(
        """FROM Vendas v 
            WHERE v.dataVenda 
            BETWEEN :dataInicio AND :dataFim
            """)
    fun findByData(
        @Param("dataInicio") dataInicio: LocalDate,
        @Param("dataFim") dataFim: LocalDate
    ): List<Vendas>
}