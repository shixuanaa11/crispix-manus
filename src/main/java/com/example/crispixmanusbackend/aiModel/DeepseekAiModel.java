package com.example.crispixmanusbackend.aiModel;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * author: axuan
 *  模型测试类
 */
@Configuration
@Slf4j
public class DeepseekAiModel {


    private final ChatClient chatClient;
    /**
     * 工具调用
     */
    @Resource
    private ToolCallback[] allTool;


    private static final String SYSTEM_PROMPT="你是一个图像专业领域大师，你可以帮助用户给进行各种图片处理，比如生图 改图 修图 变清晰等s";

    /**
     * 初始化chatClient
     * 对模型进行系统预设
     * @param chatModel 模型
     */
    public DeepseekAiModel(ChatModel chatModel){
        chatClient = ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .build();

    }

    /**
     * 给外界的对话接口，可以传历史消息，对话id
     *
     * @param message 历史消息
     * @param chatId  对话id
     * @return
     */
    public Flux<String> doChat(String message, String chatId) {
        try {
            Flux<String> flux = chatClient
                    .prompt()
                    .user(message)
                    .toolCallbacks(allTool)
                    .advisors(spec -> spec.param(ChatMemory.DEFAULT_CONVERSATION_ID, chatId)
                            .param(ChatMemory.CONVERSATION_ID, 10))
                    .stream().content();
//            String content = response.getResult().getOutput().getText();
            log.info("content: {}", flux);
            return flux;
        } catch (Exception e) {
            log.error("调用ai服务失败: {}", e.getMessage());

            // Return a friendly timeout message instead of throwing an exception


            // For other errors, return a generic error message
            return null;
//            log.error("调用ai服务失败"+e.getMessage());
//            throw new BesinessException(ErrorCode.SYSTEM_ERROR,"调用ai服务失败");
        }
    }




}
