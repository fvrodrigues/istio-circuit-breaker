package br.com.votorantim.istio.backend.controller;

import br.com.votorantim.istio.backend.client.BackendClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class SampleController {

    private List<String> actions = Arrays.asList("success", "success-slow", "client-error", "server-error");

    private final BackendClient client;

    @GetMapping
    @SneakyThrows
    public ResponseEntity get(@RequestParam("action") String action, @RequestParam(name = "loop", defaultValue = "1") Integer loop){
        log.info("Acesso ao getSuccess");

        loop = loop < 0 || loop > 10 ? 1 : loop;

        List<Response> responses = new ArrayList<>(loop);
        for (var i = 0; i< loop; i++){
            responses.add(new Response(client.getMessage(action)));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/available")
    public ResponseEntity availableActions() {
        return ResponseEntity.ok(actions);
    }

    @AllArgsConstructor
    class Response {
        public String message;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity exception(HttpClientErrorException e){
        return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode().getReasonPhrase()));
    }

}
