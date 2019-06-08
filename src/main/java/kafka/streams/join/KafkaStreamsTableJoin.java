package kafka.streams.join;
import kafka.streams.serde.PersonSerde;
import kafka.streams.serde.SchoolSerde;
import kafka.streams.serde.UniqueIdKeySerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.Duration;

@SpringBootApplication
public class KafkaStreamsTableJoin {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsTableJoin.class, args);
	}

	@EnableBinding(KStreamProcessorX.class)
	public static class KStreamToTableJoinApplication {


		@StreamListener
		public void process(@Input("person") KStream<PersonKey, Person> persons,
											 @Input("school") KStream<SchoolKey, School> schools
											 ) {

			//Messages with composite-keys e.g pojo UniqueIdKey.java
			persons.selectKey((PersonKey, Person) -> new UniqueIdKey(PersonKey.getId())).peek((key, value) -> System.out.println("Personkey1= " + key + ", PersonValue1= " + value))
					.join(
							schools.selectKey((SchoolKey, School) -> new UniqueIdKey(SchoolKey.getId())).peek((key, value) -> System.out.println("SchoolKey1= " + key + ", SchoolValue1= " + value)),
							(person, school) -> {
								System.out.println("person1= " + person + ", school1= " + school); //**This never gets called**
								return null;
							},
							JoinWindows.of(Duration.ofSeconds(5)),
							Joined.with(
									new UniqueIdKeySerde(),
									new PersonSerde(),
									new SchoolSerde())

			);

			//Messages with primitive keys e.g String
			persons.selectKey((PersonKey, Person) -> PersonKey.getId()).peek((key, value) -> System.out.println("Personkey2= " + key + ", PersonValue2= " + value))
					.join(
							schools.selectKey((SchoolKey, School) -> SchoolKey.getId()).peek((key, value) -> System.out.println("Schoolkey2= " + key + ", SchoolValue2= " + value)),
							(person, school) -> {
								System.out.println("person2= " + person + ", school2= " + school); //**This one works fine**
								return null;
							},
							JoinWindows.of(Duration.ofSeconds(5)),
							Joined.with(
									Serdes.Integer(),
									new PersonSerde(),
									new SchoolSerde())

					);

			//Messages with composite-keys e.g pojo UniqueIdKey.java
			persons.selectKey((PersonKey, Person) -> new UniqueIdKey(PersonKey.getId())).peek((key, value) -> System.out.println("Personkey3= " + key + ", PersonValue3= " + value))
					.join(
							schools.selectKey((SchoolKey, School) -> new UniqueIdKey(SchoolKey.getId())).peek((key, value) -> System.out.println("SchoolKey3= " + key + ", SchoolValue3= " + value)),
							new Joiner(),  //**This never gets called**
							JoinWindows.of(Duration.ofSeconds(5)),
							Joined.with(
									new UniqueIdKeySerde(),
									new PersonSerde(),
									new SchoolSerde())

					);
		}
	}

	interface KStreamProcessorX {

		@Input("person")
		KStream<?, ?> inputPersonKStream();

		@Input("school")
		KStream<?, ?> inputSchoolKStream();
	}
}
