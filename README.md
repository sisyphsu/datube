# nakedata

## 性能问题

在服务器内网环境中，16B的网络传输耗时约等于CPU运算1us。
尽管网卡可能是千兆等，但是对于多路复用的长连接而言，百兆吞吐量足矣。

在公网的环境中，1B的网络传输耗时约等于CPU运算1us。

## Benifit

1. Higher compression ratio.
2. Better scalability, based on standard JSON.
3. More lightweight, ProtoBuf may need several KB/MB.

