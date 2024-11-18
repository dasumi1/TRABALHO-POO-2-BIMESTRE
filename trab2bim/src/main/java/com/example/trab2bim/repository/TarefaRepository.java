package com.example.trab2bim.repository;

import com.example.trab2bim.model.Prioridade;
import com.example.trab2bim.model.Status;
import com.example.trab2bim.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatusOrderByPrioridadeAsc(Status status);

    List<Tarefa> findByPrioridadeAndDataLimiteLessThan(Prioridade prioridade, LocalDateTime dataLimite);

    List<Tarefa> findByStatusNotAndDataLimiteLessThan(Status status, LocalDateTime dataLimite);
}

