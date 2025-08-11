package com.example.crispixmanusbackend.aiModel.tool;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 集中的工具注册类
 */
@Configuration
public class ToolRegistration {

    @Value("${search-api.api-key}")
    private String searchApiKey;

    @Bean
    public ToolCallback[] allTools() {

        ImageEditTool imageEditTool = new ImageEditTool();
        TerminateTool terminateTool = new TerminateTool();
        WebImageSearchTool webImageSearchTool = new WebImageSearchTool(searchApiKey);
        return ToolCallbacks.from(
                imageEditTool,
                terminateTool,
                webImageSearchTool
        );
    }
}