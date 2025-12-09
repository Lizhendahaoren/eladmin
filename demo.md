flowchart TD
    %% 后台服务模块
    subgraph BACKEND["后台服务模块"]
        direction TB
        A1[WebSocket 服务 / 任务调度] -->|下发任务| B1[Python EXE 客户端]
        B1 -->|回传状态、接口响应、日志| A1
    end

    %% EXE 客户端模块
    subgraph EXE["Python EXE 客户端模块"]
        direction TB
        B1 --> C1[扫描本地 cookies 文件夹]
        C1 --> D1{账号 Cookie 是否存在?}
        
        D1 -- 有 --> E1[加载本地 Cookie]
        D1 -- 无 --> F1[打开浏览器登录（手机号+验证码）]
        F1 --> G1[保存 Cookie 到本地 JSON 文件]
        
        E1 --> H1[检测 Cookie 有效性]
        G1 --> H1
        
        H1 --> I1[接收后台下发任务（账号 + 操作）]
        I1 --> J1{Cookie 是否有效?}
        
        J1 -- 有效 --> K1[调接口执行任务（带 Cookie）]
        J1 -- 过期 --> F1
        
        K1 --> L1[任务完成 → 回传结果 + 记录日志]
        L1 --> M1[等待下一任务 / 轮询后台任务]
    end

    %% 多账号管理模块
    subgraph MULTI_ACCOUNT["多账号管理 / Cookie 管理"]
        direction TB
        N1[每个账号独立 JSON 文件保存 Cookie]
        O1[EXE 根据任务指定账号调用接口]
        P1[批量管理和轮询多个账号]
        
        N1 --> O1 --> P1
        P1 --> C1
    end

    %% 自动化扩展模块
    subgraph EXTEND["自动化扩展功能"]
        direction TB
        Q1[后台刷新 Cookie 延长有效期]
        R1[支持批量轮询多个账号任务]
        S1[支持日志记录和状态回传]
    end
