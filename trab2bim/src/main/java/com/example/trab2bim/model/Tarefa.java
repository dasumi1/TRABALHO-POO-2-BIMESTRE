package com.example.trab2bim.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;  // Título obrigatório

    private String descricao; // Descrição opcional

    @Column(nullable = false)
    private LocalDateTime dataCriacao;  // Data de criação gerada automaticamente

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // A Fazer, Em Progresso, Concluído

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade; // Baixa, Média, Alta

    private LocalDateTime dataLimite;  // Data limite (opcional)
}

