package com.br.labs.repository

import com.br.labs.model.VendaProduto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaProdutoRepository : JpaRepository<VendaProduto, Int>