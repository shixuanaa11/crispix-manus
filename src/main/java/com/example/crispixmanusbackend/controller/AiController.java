package com.example.crispixmanusbackend.controller;


import com.example.crispixmanusbackend.aiModel.model.YuManus;
import com.example.crispixmanusbackend.aiModel.model.YuManusFactory;
import com.example.crispixmanusbackend.common.BaseResponse;
import com.example.crispixmanusbackend.common.ResultUtils;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/ai")
public class AiController {


    @Resource
    private YuManusFactory yuManusFactory;

    /**
     * 流式调用 Manus 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/manus/chat")
    public SseEmitter doChatWithManus(String message, String chatId) {
        YuManus yuManus = yuManusFactory.create(chatId);
        return yuManus.runStream(message);
    }


}
