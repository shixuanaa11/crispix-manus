package com.example.crispixmanusbackend.aiModel.tool;

import org.springframework.ai.tool.annotation.Tool;

/**
 * 终止任务的工具
 * 交给ai让其自主判断需不需要终止
 */
public class TerminateTool {
  
    @Tool(description = """  
            Terminate the interaction when the request is met OR if the assistant cannot proceed further with the task.  
            "When you have finished all the tasks, call this tool to end the work.  
            """)  
    public String doTerminate() {  
        return "任务结束";  
    }  
}
