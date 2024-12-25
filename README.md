# 局域网文件传输工具

一个基于 Flask 和 WebSocket 的局域网文件传输和聊天工具。

## 功能

- 实时聊天
- 文件传输
- 用户在线状态显示

## 安装

1. 安装依赖：
```bash
pip install -r requirements.txt
```

2. 运行应用：
```bash
python app.py
```

3. 访问应用：
- 在浏览器中打开 `http://localhost:8848`
- 局域网内其他用户可以通过 `http://你的IP地址:8848` 访问

## 注意事项

1. 确保防火墙允许程序运行
2. 确保在同一局域网内
3. 如果遇到端口占用，请修改 `app.py` 中的端口号 