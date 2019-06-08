# spring-cloud-stream-join

.\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic person
.\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic school

Run Producer.java to generate messages for person and school topics

.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic person --key-deserializer org.apache.kafka.common.serialization.StringDeserializer --value-deserializer org.apache.kafka.common.serialization.StringDeserializer --property print.key=true --property key.separator="-" --property print.timestamp=true
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic school --key-deserializer org.apache.kafka.common.serialization.StringDeserializer --value-deserializer org.apache.kafka.common.serialization.StringDeserializer --property print.key=true --property key.separator="-" --property print.timestamp=true
