package com.example.crispixmanusbackend.aiModel.model;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

@Component
public class YuManusFactory {
    @Resource
    private ToolCallback[] allTools;
    @Resource
    private ChatModel chatModel;

    /**
     * 如果需要历史对话记忆传chatid，可以创建这么一个工厂类封装Manus，当然对话记忆没做
     * @param chatId
     * @return
     */
    public YuManus create(String chatId) {
        return new YuManus(allTools, chatModel, chatId);
    }
}