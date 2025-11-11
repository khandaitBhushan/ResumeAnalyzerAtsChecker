package com.org.ResumeAnalyzer;

import org.antlr.v4.runtime.misc.Pair;
import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    public final ChatClient chatClient;

    public Tika tika = new Tika();

    @Value("${resume.prompt.analyze}")
    private String promptForAnalyze;

    @Value("${resume.prompt.ats-check}")
    private String promptForAtsCheck;


    @Autowired
    public ResumeController (OpenAiChatModel openAiChatModel){
        this.chatClient = ChatClient.create(openAiChatModel);
    }

    @PostMapping("/analyzer")
    public ResponseEntity<?> analyzer(@RequestParam("file")MultipartFile file)
    {
        try {
            String content = tika.parseToString(file.getInputStream());

            String aiResponse = chatClient.prompt()
                    .user(promptForAnalyze.formatted(content))
                    .call()
                    .content();
            Map<String, Object> response = new HashMap<>();
            response.put("analysis", cleanJsonResponse(aiResponse));
            response.put("status", "success");
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(createErrorResponse("Error in parsing file : "+file.getName()));
        }
    }

    @PostMapping("/ats-check")
    public ResponseEntity<?> atsCheck(@RequestParam("file")MultipartFile file,
                                   @RequestParam("jd")String jobDescription )
    {
        try {
            String resumeText = tika.parseToString(file.getInputStream());

            String aiResponse = chatClient.prompt()
                    .user(promptForAtsCheck.formatted(resumeText, jobDescription))
                    .call()
                    .content();
            Map<String, Object> response = new HashMap<>();
            response.put("analysis", cleanJsonResponse(aiResponse));
            response.put("status", "success");
            return ResponseEntity.ok().header("Content-Type", "application/json").body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(createErrorResponse("Error! Unable to parse resume: "+file.getName()));
        }
    }

    private String cleanJsonResponse(String aiResponse) {
        if (aiResponse == null) {
            return "{}";
        }
        String cleaned = aiResponse.replaceAll("```json\\n?", "").replaceAll("\\n?```", "").trim();
        if (cleaned.isEmpty()) {
            return "{}";
        }

        return cleaned;
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        errorResponse.put("status", "error");
        return errorResponse;
    }

}
