package com.example.trab2bim.service;

import com.example.trab2bim.model.*;
import com.example.trab2bim.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setStatus(Status.AFAZER);
        tarefa.setDataCriacao(LocalDateTime.now());
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return repository.findAll(Sort.by("status"));
    }

    public Tarefa moverTarefa(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

        switch (tarefa.getStatus()) {
            case AFAZER:
                tarefa.setStatus(Status.EMPROGRESSO);
                break;
            case EMPROGRESSO:
                tarefa.setStatus(Status.CONCLUIDO);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tarefa já concluída");
        }
        return repository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa novaTarefa) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

        tarefa.setTitulo(novaTarefa.getTitulo());
        tarefa.setDescricao(novaTarefa.getDescricao());
        tarefa.setPrioridade(novaTarefa.getPrioridade());
        tarefa.setDataLimite(novaTarefa.getDataLimite());
        return repository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        repository.deleteById(id);
    }

    public List<Tarefa> listarTarefasPorPrioridade(Status status) {
        return repository.findByStatusOrderByPrioridadeAsc(status);
    }

    public List<Tarefa> listarTarefasPorPrioridadeEDataLimite(Prioridade prioridade, LocalDateTime dataLimite) {
        return repository.findByPrioridadeAndDataLimiteLessThan(prioridade, dataLimite);
    }

    public List<Tarefa> listarTarefasAtrasadas() {
        return repository.findByStatusNotAndDataLimiteLessThan(Status.CONCLUIDO, LocalDateTime.now());
    }
}



