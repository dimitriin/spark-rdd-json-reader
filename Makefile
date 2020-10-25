PROJECT_DIR ?= $(shell pwd)
SPARK_DIR ?= ${PROJECT_DIR}/.spark
WINEMAG_PATH ?= ${PROJECT_DIR}/.data/winemag-data-130k-v2.json

submit:
	{$SPARK_DIR}/bin/spark-submit --master local[*] --class com.example.JsonReader \
	./target/scala-2.11/json_reader_nasedkin-assembly-0.1.jar ${WINEMAG_PATH}
.PHONY: submit