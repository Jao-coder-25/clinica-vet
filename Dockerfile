FROM ubuntu:latest
LABEL authors="João Luiz"

ENTRYPOINT ["top", "-b"]