package com.example.trab2bim.controller;

import com.example.trab2bim.model.Prioridade;
import com.example.trab2bim.model.Status;
import com.example.trab2bim.model.Tarefa;
import com.example.trab2bim.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return service.criarTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return service.listarTarefas();
    }

    @PutMapping("/{id}/mover")
    public Tarefa moverTarefa(@PathVariable Long id) {
        return service.moverTarefa(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return service.atualizarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Long id) {
        service.excluirTarefa(id);
    }

    @GetMapping("/prioridade/{status}")
    public List<Tarefa> listarTarefasPorPrioridade(@PathVariable Status status) {
        return service.listarTarefasPorPrioridade(status);
    }

    @GetMapping("/filtrar")
    public List<Tarefa> listarTarefasPorPrioridadeEDataLimite(
            @RequestParam Prioridade prioridade,
            @RequestParam String dataLimite) {
        return service.listarTarefasPorPrioridadeEDataLimite(
                prioridade,
                LocalDateTime.parse(dataLimite));
    }

    @GetMapping("/atrasadas")
    public List<Tarefa> listarTarefasAtrasadas() {
        return service.listarTarefasAtrasadas();
    }
}



