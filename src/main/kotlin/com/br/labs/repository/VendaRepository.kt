package com.br.labs.repository

import com.br.labs.model.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaRepository : JpaRepository<Venda, Int>