package com.example.crispixmanusbackend.aiModel.model;


import com.example.crispixmanusbackend.aiModel.advisor.MyLoggerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;

/**
 * 拥有自主规划能力的超级智能体实现类
 */
public class YuManus extends ToolCallAgent {

    public YuManus(ToolCallback[] allTools, ChatModel chatModel, String chatId) {
        super(allTools);
        this.setName("Crispix Agent");
        String SYSTEM_PROMPT = """  
                你是Crispix Agent，一名专业的图像领域AI助手，致力于解决用户提出的任何任务。  
                你可以调用多种工具，高效完成复杂请求。  
                
                # 重要指令：  
                1. 每次工具调用后，工具的返回结果会以编号（如#1、#2）和摘要的形式出现在对话历史中。  
                2. 在后续推理和工具调用时，优先复用历史工具结果（如图片列表、文本等），避免重复调用同一工具获取相同资源。  
                3. 如需引用历史工具结果，请直接使用编号（如“请用#1中的图片”）。  
                4. 只有在所有历史结果都不满足需求时，才考虑再次调用同一工具。  
                5. 工具调用后请用自然语言总结结果，并建议下一步。  
                
                ## 回复格式要求：  
                请在每一步回复时，严格区分“推理过程”和“最终答复”，并用如下格式输出：  
                推理过程: ...  
                最终答复: ...  
                 **最终答复的内容必须按照markdown的格式输出，尤其是图片内容必须是![描述](url)。**
                请始终遵循以上规则，帮助用户高效完成任务。  
                """;
        this.setSystemPrompt(SYSTEM_PROMPT);
        String NEXT_STEP_PROMPT = """  
                请根据用户需求，先分析并写出你的推理过程，再给出最终答复。格式如下：  
                推理过程: ...  
                最终答复: ...  
                """;
        this.setNextStepPrompt(NEXT_STEP_PROMPT);
        this.setMaxSteps(10);
        InMemoryChatMemoryRepository inMemoryChatMemoryRepository = new InMemoryChatMemoryRepository();
        // 初始化客户端
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(new MyLoggerAdvisor())
                .build();  
        this.setChatClient(chatClient);  
    }  
}
