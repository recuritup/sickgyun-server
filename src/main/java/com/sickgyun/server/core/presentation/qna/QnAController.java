package com.sickgyun.server.core.presentation.qna;

import com.sickgyun.server.core.presentation.qna.dto.CreateQnARequest;
import com.sickgyun.server.core.presentation.qna.dto.QnAResponse;
import com.sickgyun.server.core.service.qna.CommandQnAService;
import com.sickgyun.server.core.service.qna.QueryQnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnAController {

    private final CommandQnAService commandQnAService;
    private final QueryQnAService queryQnAService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createQnA(@RequestBody CreateQnARequest request) {
        commandQnAService.createQnA(request);
    }

    @GetMapping
    public List<QnAResponse> findAll(@RequestParam(name = "category", required = false, defaultValue = "ALL") String category) {
        if (category.equals("ALL")) {
            return queryQnAService.findAll();
        }
        return queryQnAService.findAllByCategory(category);
    }

    @GetMapping("/{qna-id}")
    public QnAResponse findOne(@PathVariable(name = "qna-id") Long qnAId) {
        return queryQnAService.findOne(qnAId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{qna-id}")
    public void updateQnA(
            @PathVariable(name = "qna-id") Long qnAId,
            @RequestBody CreateQnARequest request
    ) {
        commandQnAService.updateQnA(qnAId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{qna-id}")
    public void deleteQnA(@PathVariable(name = "qna-id") Long qnAId) {
        commandQnAService.deleteQnA(qnAId);
    }
}
