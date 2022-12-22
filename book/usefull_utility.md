## Http - аналог курла
command:

GET: (по умолчания на localhost)
```
http :5006/post/99
```

out:
```json
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/json
Date: Thu, 22 Dec 2022 06:47:33 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "body": "quo deleniti praesentium dicta non quod\naut est molestias\nmolestias et officia quis nihil\nitaque dolorem quia",
    "id": 99,
    "title": "temporibus sit alias delectus eligendi possimus magni"
}
```
## Apache Benchmark
Documentation: https://httpd.apache.org/docs/2.4/programs/ab.html

Install:
```shell
sudo apt install apache2-utils
```
Usage:
```shell
# `-n` - Кол-во запросов; `-c` Кол-во потоков
ab -n 10 -c 5 http://localhost:5006/post/1
```
