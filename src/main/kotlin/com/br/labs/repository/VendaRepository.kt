package com.br.labs.repository

import com.br.labs.model.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface VendaRepository : JpaRepository<Venda, Int> {

    @Query(
        """FROM Venda v 
            WHERE v.dataVenda 
            BETWEEN :dataInicio AND :dataFim
            """)
    fun findByData(
        @Param("dataInicio") dataInicio: LocalDate,
        @Param("dataFim") dataFim: LocalDate
    ): List<Venda>
}