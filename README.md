# spring-cloud-stream-join
Reproducible Steps:
1) Run zookeeper and kafka broker
2) Create the following topics
	.\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic person
	.\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic school

3) Run the stand-alone Producer.java to generate messages for person and school topics
4) Optional - To observe the messages within topics
	.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic person --key-deserializer org.apache.kafka.common.serialization.StringDeserializer --value-deserializer org.apache.kafka.common.serialization.StringDeserializer --property print.key=true --property key.separator="-" --property print.timestamp=true
	.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic school --key-deserializer org.apache.kafka.common.serialization.StringDeserializer --value-deserializer org.apache.kafka.common.serialization.StringDeserializer --property print.key=true --property key.separator="-" --property print.timestamp=true
5) Run KafkaStreamsTableJoin.java as the main application
6) Observe the result - The messages with composite-keys are never joined but the messages with simple primitive keys are joined.