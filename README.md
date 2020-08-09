Learn Scala: Simple HTTP API with Http4s and Tapir

```
Path: /math/{operation}/calculate
Request: {"x":10, "y":27}
Response: {"result":"37"}
```

Example query:

```shell
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"x":"10","y":"27"}' \
  http://localhost:8080/math/plus/calculate
```  

Prerequisites:

```shell
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk11
brew install sbt
```
