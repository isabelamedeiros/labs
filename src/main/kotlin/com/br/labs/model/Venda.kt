package com.br.labs.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "vendas", schema = "labs")
data class Venda(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id", length = 10)
    val idVenda: Int? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_venda", length = 8)
    val dataVenda: LocalDate,

    @Column(name = "total")
    val totalVenda: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    val idUsuario: Usuario
)