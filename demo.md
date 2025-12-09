<script type="module">
import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.esm.min.mjs';
mermaid.initialize({ startOnLoad: true });
</script>

<div class="mermaid">
flowchart TD
    A[EXE 启动] --> B[扫描 Cookie]
    B --> C{Cookie 是否存在?}
    C -- 有 --> D[加载 Cookie]
    C -- 无 --> E[浏览器登录]
    D --> F[检测 Cookie]
    E --> F
    F --> G[执行任务]
    G --> H[回传结果]
</div>
